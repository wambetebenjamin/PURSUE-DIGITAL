package com.squareup.wire;

import com.google.common.base.Ascii;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import okio.BufferedSource;
import okio.ByteString;
/* loaded from: classes9.dex */
public final class ProtoReader {
    private static final int FIELD_ENCODING_MASK = 7;
    private static final int RECURSION_LIMIT = 65;
    private static final int STATE_END_GROUP = 4;
    private static final int STATE_FIXED32 = 5;
    private static final int STATE_FIXED64 = 1;
    private static final int STATE_LENGTH_DELIMITED = 2;
    private static final int STATE_PACKED_TAG = 7;
    private static final int STATE_START_GROUP = 3;
    private static final int STATE_TAG = 6;
    private static final int STATE_VARINT = 0;
    static final int TAG_FIELD_ENCODING_BITS = 3;
    private FieldEncoding nextFieldEncoding;
    private int recursionDepth;
    private final BufferedSource source;
    private long pos = 0;
    private long limit = Long.MAX_VALUE;
    private int state = 2;
    private int tag = -1;
    private long pushedLimit = -1;

    public ProtoReader(BufferedSource bufferedSource) {
        this.source = bufferedSource;
    }

    public long beginMessage() throws IOException {
        if (this.state != 2) {
            throw new IllegalStateException("Unexpected call to beginMessage()");
        }
        int i2 = this.recursionDepth + 1;
        this.recursionDepth = i2;
        if (i2 > 65) {
            throw new IOException("Wire recursion limit exceeded");
        }
        long j2 = this.pushedLimit;
        this.pushedLimit = -1L;
        this.state = 6;
        return j2;
    }

    public void endMessage(long j2) throws IOException {
        if (this.state != 6) {
            throw new IllegalStateException("Unexpected call to endMessage()");
        }
        int i2 = this.recursionDepth - 1;
        this.recursionDepth = i2;
        if (i2 < 0 || this.pushedLimit != -1) {
            throw new IllegalStateException("No corresponding call to beginMessage()");
        }
        if (this.pos != this.limit && i2 != 0) {
            throw new IOException("Expected to end at " + this.limit + " but was " + this.pos);
        }
        this.limit = j2;
    }

    public int nextTag() throws IOException {
        int i2 = this.state;
        if (i2 == 7) {
            this.state = 2;
            return this.tag;
        } else if (i2 != 6) {
            throw new IllegalStateException("Unexpected call to nextTag()");
        } else {
            while (this.pos < this.limit && !this.source.exhausted()) {
                int internalReadVarint32 = internalReadVarint32();
                if (internalReadVarint32 == 0) {
                    throw new ProtocolException("Unexpected tag 0");
                }
                int i3 = internalReadVarint32 >> 3;
                this.tag = i3;
                int i4 = internalReadVarint32 & 7;
                if (i4 == 0) {
                    this.nextFieldEncoding = FieldEncoding.VARINT;
                    this.state = 0;
                    return this.tag;
                } else if (i4 == 1) {
                    this.nextFieldEncoding = FieldEncoding.FIXED64;
                    this.state = 1;
                    return this.tag;
                } else if (i4 == 2) {
                    this.nextFieldEncoding = FieldEncoding.LENGTH_DELIMITED;
                    this.state = 2;
                    int internalReadVarint322 = internalReadVarint32();
                    if (internalReadVarint322 < 0) {
                        throw new ProtocolException("Negative length: " + internalReadVarint322);
                    }
                    if (this.pushedLimit != -1) {
                        throw new IllegalStateException();
                    }
                    long j2 = this.limit;
                    this.pushedLimit = j2;
                    long j3 = this.pos + internalReadVarint322;
                    this.limit = j3;
                    if (j3 > j2) {
                        throw new EOFException();
                    }
                    return this.tag;
                } else if (i4 != 3) {
                    if (i4 != 4) {
                        if (i4 == 5) {
                            this.nextFieldEncoding = FieldEncoding.FIXED32;
                            this.state = 5;
                            return this.tag;
                        }
                        throw new ProtocolException("Unexpected field encoding: " + i4);
                    }
                    throw new ProtocolException("Unexpected end group");
                } else {
                    skipGroup(i3);
                }
            }
            return -1;
        }
    }

    public FieldEncoding peekFieldEncoding() {
        return this.nextFieldEncoding;
    }

    public void skip() throws IOException {
        int i2 = this.state;
        if (i2 == 0) {
            readVarint64();
        } else if (i2 == 1) {
            readFixed64();
        } else if (i2 == 2) {
            this.source.skip(beforeLengthDelimitedScalar());
        } else if (i2 == 5) {
            readFixed32();
        } else {
            throw new IllegalStateException("Unexpected call to skip()");
        }
    }

    private void skipGroup(int i2) throws IOException {
        while (this.pos < this.limit && !this.source.exhausted()) {
            int internalReadVarint32 = internalReadVarint32();
            if (internalReadVarint32 == 0) {
                throw new ProtocolException("Unexpected tag 0");
            }
            int i3 = internalReadVarint32 >> 3;
            int i4 = internalReadVarint32 & 7;
            if (i4 == 0) {
                this.state = 0;
                readVarint64();
            } else if (i4 == 1) {
                this.state = 1;
                readFixed64();
            } else if (i4 == 2) {
                long internalReadVarint322 = internalReadVarint32();
                this.pos += internalReadVarint322;
                this.source.skip(internalReadVarint322);
            } else if (i4 == 3) {
                skipGroup(i3);
            } else if (i4 == 4) {
                if (i3 != i2) {
                    throw new ProtocolException("Unexpected end group");
                }
                return;
            } else if (i4 == 5) {
                this.state = 5;
                readFixed32();
            } else {
                throw new ProtocolException("Unexpected field encoding: " + i4);
            }
        }
        throw new EOFException();
    }

    public ByteString readBytes() throws IOException {
        long beforeLengthDelimitedScalar = beforeLengthDelimitedScalar();
        this.source.require(beforeLengthDelimitedScalar);
        return this.source.readByteString(beforeLengthDelimitedScalar);
    }

    public String readString() throws IOException {
        long beforeLengthDelimitedScalar = beforeLengthDelimitedScalar();
        this.source.require(beforeLengthDelimitedScalar);
        return this.source.readUtf8(beforeLengthDelimitedScalar);
    }

    public int readVarint32() throws IOException {
        int i2 = this.state;
        if (i2 != 0 && i2 != 2) {
            throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.state);
        }
        int internalReadVarint32 = internalReadVarint32();
        afterPackableScalar(0);
        return internalReadVarint32;
    }

    private int internalReadVarint32() throws IOException {
        int i2;
        this.source.require(1L);
        this.pos++;
        byte readByte = this.source.readByte();
        if (readByte >= 0) {
            return readByte;
        }
        int i3 = readByte & Byte.MAX_VALUE;
        this.source.require(1L);
        this.pos++;
        byte readByte2 = this.source.readByte();
        if (readByte2 >= 0) {
            i2 = readByte2 << 7;
        } else {
            i3 |= (readByte2 & Byte.MAX_VALUE) << 7;
            this.source.require(1L);
            this.pos++;
            byte readByte3 = this.source.readByte();
            if (readByte3 >= 0) {
                i2 = readByte3 << Ascii.SO;
            } else {
                i3 |= (readByte3 & Byte.MAX_VALUE) << 14;
                this.source.require(1L);
                this.pos++;
                byte readByte4 = this.source.readByte();
                if (readByte4 < 0) {
                    int i4 = i3 | ((readByte4 & Byte.MAX_VALUE) << 21);
                    this.source.require(1L);
                    this.pos++;
                    byte readByte5 = this.source.readByte();
                    int i5 = i4 | (readByte5 << Ascii.FS);
                    if (readByte5 < 0) {
                        for (int i6 = 0; i6 < 5; i6++) {
                            this.source.require(1L);
                            this.pos++;
                            if (this.source.readByte() >= 0) {
                                return i5;
                            }
                        }
                        throw new ProtocolException("Malformed VARINT");
                    }
                    return i5;
                }
                i2 = readByte4 << Ascii.NAK;
            }
        }
        return i3 | i2;
    }

    public long readVarint64() throws IOException {
        byte readByte;
        int i2 = this.state;
        if (i2 != 0 && i2 != 2) {
            throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.state);
        }
        long j2 = 0;
        for (int i3 = 0; i3 < 64; i3 += 7) {
            this.source.require(1L);
            this.pos++;
            j2 |= (readByte & Byte.MAX_VALUE) << i3;
            if ((this.source.readByte() & 128) == 0) {
                afterPackableScalar(0);
                return j2;
            }
        }
        throw new ProtocolException("WireInput encountered a malformed varint");
    }

    public int readFixed32() throws IOException {
        int i2 = this.state;
        if (i2 != 5 && i2 != 2) {
            throw new ProtocolException("Expected FIXED32 or LENGTH_DELIMITED but was " + this.state);
        }
        this.source.require(4L);
        this.pos += 4;
        int readIntLe = this.source.readIntLe();
        afterPackableScalar(5);
        return readIntLe;
    }

    public long readFixed64() throws IOException {
        int i2 = this.state;
        if (i2 != 1 && i2 != 2) {
            throw new ProtocolException("Expected FIXED64 or LENGTH_DELIMITED but was " + this.state);
        }
        this.source.require(8L);
        this.pos += 8;
        long readLongLe = this.source.readLongLe();
        afterPackableScalar(1);
        return readLongLe;
    }

    private void afterPackableScalar(int i2) throws IOException {
        if (this.state == i2) {
            this.state = 6;
            return;
        }
        long j2 = this.pos;
        long j3 = this.limit;
        if (j2 > j3) {
            throw new IOException("Expected to end at " + this.limit + " but was " + this.pos);
        }
        if (j2 == j3) {
            this.limit = this.pushedLimit;
            this.pushedLimit = -1L;
            this.state = 6;
            return;
        }
        this.state = 7;
    }

    private long beforeLengthDelimitedScalar() throws IOException {
        if (this.state != 2) {
            throw new ProtocolException("Expected LENGTH_DELIMITED but was " + this.state);
        }
        long j2 = this.limit - this.pos;
        this.source.require(j2);
        this.state = 6;
        this.pos = this.limit;
        this.limit = this.pushedLimit;
        this.pushedLimit = -1L;
        return j2;
    }
}
