package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import com.bytedance.apm.battery.config.a;
import com.bytedance.common.utility.n;
import com.facebook.imageutils.JfifUtil;
import com.google.common.base.Ascii;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes10.dex */
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {
    private static final byte[] DIGITS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    static final int REPLACEMENT_CHARACTER = 65533;
    @Nullable
    Segment head;
    long size;

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer buffer() {
        return this;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // okio.BufferedSink
    public BufferedSink emit() {
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer emitCompleteSegments() {
        return this;
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
    }

    @Override // okio.BufferedSource
    public Buffer getBuffer() {
        return this;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    public final long size() {
        return this.size;
    }

    @Override // okio.BufferedSink
    public OutputStream outputStream() {
        return new OutputStream() { // from class: okio.Buffer.1
            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
            }

            @Override // java.io.OutputStream
            public void write(int i2) {
                Buffer.this.writeByte((int) ((byte) i2));
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i2, int i3) {
                Buffer.this.write(bArr, i2, i3);
            }

            public String toString() {
                return Buffer.this + ".outputStream()";
            }
        };
    }

    @Override // okio.BufferedSource
    public boolean exhausted() {
        return this.size == 0;
    }

    @Override // okio.BufferedSource
    public void require(long j2) throws EOFException {
        if (this.size < j2) {
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public boolean request(long j2) {
        return this.size >= j2;
    }

    @Override // okio.BufferedSource
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override // okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() { // from class: okio.Buffer.2
            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.InputStream
            public int read() {
                if (Buffer.this.size > 0) {
                    return Buffer.this.readByte() & 255;
                }
                return -1;
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i2, int i3) {
                return Buffer.this.read(bArr, i2, i3);
            }

            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(Buffer.this.size, 2147483647L);
            }

            public String toString() {
                return Buffer.this + ".inputStream()";
            }
        };
    }

    public final Buffer copyTo(OutputStream outputStream) throws IOException {
        return copyTo(outputStream, 0L, this.size);
    }

    public final Buffer copyTo(OutputStream outputStream, long j2, long j3) throws IOException {
        int i2;
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, j2, j3);
        if (j3 == 0) {
            return this;
        }
        Segment segment = this.head;
        while (j2 >= segment.limit - segment.pos) {
            j2 -= segment.limit - segment.pos;
            segment = segment.next;
        }
        while (j3 > 0) {
            int min = (int) Math.min(segment.limit - i2, j3);
            outputStream.write(segment.data, (int) (segment.pos + j2), min);
            j3 -= min;
            segment = segment.next;
            j2 = 0;
        }
        return this;
    }

    public final Buffer copyTo(Buffer buffer, long j2, long j3) {
        if (buffer == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, j2, j3);
        if (j3 == 0) {
            return this;
        }
        buffer.size += j3;
        Segment segment = this.head;
        while (j2 >= segment.limit - segment.pos) {
            j2 -= segment.limit - segment.pos;
            segment = segment.next;
        }
        while (j3 > 0) {
            Segment sharedCopy = segment.sharedCopy();
            sharedCopy.pos = (int) (sharedCopy.pos + j2);
            sharedCopy.limit = Math.min(sharedCopy.pos + ((int) j3), sharedCopy.limit);
            Segment segment2 = buffer.head;
            if (segment2 == null) {
                sharedCopy.prev = sharedCopy;
                sharedCopy.next = sharedCopy;
                buffer.head = sharedCopy;
            } else {
                segment2.prev.push(sharedCopy);
            }
            j3 -= sharedCopy.limit - sharedCopy.pos;
            segment = segment.next;
            j2 = 0;
        }
        return this;
    }

    public final Buffer writeTo(OutputStream outputStream) throws IOException {
        return writeTo(outputStream, this.size);
    }

    public final Buffer writeTo(OutputStream outputStream, long j2) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, 0L, j2);
        Segment segment = this.head;
        while (j2 > 0) {
            int min = (int) Math.min(j2, segment.limit - segment.pos);
            outputStream.write(segment.data, segment.pos, min);
            segment.pos += min;
            long j3 = min;
            this.size -= j3;
            j2 -= j3;
            if (segment.pos == segment.limit) {
                Segment pop = segment.pop();
                this.head = pop;
                SegmentPool.recycle(segment);
                segment = pop;
            }
        }
        return this;
    }

    public final Buffer readFrom(InputStream inputStream) throws IOException {
        readFrom(inputStream, Long.MAX_VALUE, true);
        return this;
    }

    public final Buffer readFrom(InputStream inputStream, long j2) throws IOException {
        if (j2 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }
        readFrom(inputStream, j2, false);
        return this;
    }

    private void readFrom(InputStream inputStream, long j2, boolean z) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        while (true) {
            if (j2 <= 0 && !z) {
                return;
            }
            Segment writableSegment = writableSegment(1);
            int read = inputStream.read(writableSegment.data, writableSegment.limit, (int) Math.min(j2, 8192 - writableSegment.limit));
            if (read == -1) {
                if (!z) {
                    throw new EOFException();
                }
                return;
            }
            writableSegment.limit += read;
            long j3 = read;
            this.size += j3;
            j2 -= j3;
        }
    }

    public final long completeSegmentByteCount() {
        long j2 = this.size;
        if (j2 == 0) {
            return 0L;
        }
        Segment segment = this.head.prev;
        return (segment.limit >= 8192 || !segment.owner) ? j2 : j2 - (segment.limit - segment.pos);
    }

    @Override // okio.BufferedSource
    public byte readByte() {
        if (this.size == 0) {
            throw new IllegalStateException("size == 0");
        }
        Segment segment = this.head;
        int i2 = segment.pos;
        int i3 = segment.limit;
        int i4 = i2 + 1;
        byte b2 = segment.data[i2];
        this.size--;
        if (i4 == i3) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i4;
        }
        return b2;
    }

    public final byte getByte(long j2) {
        Util.checkOffsetAndCount(this.size, j2, 1L);
        long j3 = this.size;
        if (j3 - j2 > j2) {
            Segment segment = this.head;
            while (true) {
                long j4 = segment.limit - segment.pos;
                if (j2 >= j4) {
                    j2 -= j4;
                    segment = segment.next;
                } else {
                    return segment.data[segment.pos + ((int) j2)];
                }
            }
        } else {
            long j5 = j2 - j3;
            Segment segment2 = this.head;
            do {
                segment2 = segment2.prev;
                j5 += segment2.limit - segment2.pos;
            } while (j5 < 0);
            return segment2.data[segment2.pos + ((int) j5)];
        }
    }

    @Override // okio.BufferedSource
    public short readShort() {
        if (this.size < 2) {
            throw new IllegalStateException("size < 2: " + this.size);
        }
        Segment segment = this.head;
        int i2 = segment.pos;
        int i3 = segment.limit;
        if (i3 - i2 < 2) {
            return (short) (((readByte() & 255) << 8) | (readByte() & 255));
        }
        byte[] bArr = segment.data;
        int i4 = i2 + 1;
        int i5 = i4 + 1;
        int i6 = ((bArr[i2] & 255) << 8) | (bArr[i4] & 255);
        this.size -= 2;
        if (i5 == i3) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i5;
        }
        return (short) i6;
    }

    @Override // okio.BufferedSource
    public int readInt() {
        if (this.size < 4) {
            throw new IllegalStateException("size < 4: " + this.size);
        }
        Segment segment = this.head;
        int i2 = segment.pos;
        int i3 = segment.limit;
        if (i3 - i2 < 4) {
            return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
        }
        byte[] bArr = segment.data;
        int i4 = i2 + 1;
        int i5 = i4 + 1;
        int i6 = ((bArr[i2] & 255) << 24) | ((bArr[i4] & 255) << 16);
        int i7 = i5 + 1;
        int i8 = i6 | ((bArr[i5] & 255) << 8);
        int i9 = i7 + 1;
        int i10 = i8 | (bArr[i7] & 255);
        this.size -= 4;
        if (i9 == i3) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i9;
        }
        return i10;
    }

    @Override // okio.BufferedSource
    public long readLong() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        if (this.size < 8) {
            throw new IllegalStateException("size < 8: " + this.size);
        }
        Segment segment = this.head;
        int i9 = segment.pos;
        int i10 = segment.limit;
        if (i10 - i9 < 8) {
            return ((readInt() & 4294967295L) << 32) | (4294967295L & readInt());
        }
        byte[] bArr = segment.data;
        int i11 = i9 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1;
        long j2 = (bArr[i8] & 255) | ((bArr[i9] & 255) << 56) | ((bArr[i2] & 255) << 48) | ((bArr[i3] & 255) << 40) | ((bArr[i4] & 255) << 32) | ((bArr[i5] & 255) << 24) | ((bArr[i6] & 255) << 16) | ((bArr[i7] & 255) << 8);
        this.size -= 8;
        if (i11 == i10) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i11;
        }
        return j2;
    }

    @Override // okio.BufferedSource
    public short readShortLe() {
        return Util.reverseBytesShort(readShort());
    }

    @Override // okio.BufferedSource
    public int readIntLe() {
        return Util.reverseBytesInt(readInt());
    }

    @Override // okio.BufferedSource
    public long readLongLe() {
        return Util.reverseBytesLong(readLong());
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003c, code lost:
        r1 = new okio.Buffer().writeDecimalLong(r3).writeByte((int) r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0049, code lost:
        if (r8 != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004b, code lost:
        r1.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006a, code lost:
        throw new java.lang.NumberFormatException("Number too large: " + r1.readUtf8());
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00bc, code lost:
        r17.size -= r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00c2, code lost:
        if (r8 == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00c6, code lost:
        return -r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:?, code lost:
        return r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ac  */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long readDecimalLong() {
        /*
            r17 = this;
            r0 = r17
            long r1 = r0.size
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto Lc7
            r1 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            r5 = -7
            r7 = 0
            r8 = r7
            r9 = r8
        L14:
            okio.Segment r10 = r0.head
            byte[] r11 = r10.data
            int r12 = r10.pos
            int r13 = r10.limit
        L1c:
            if (r12 >= r13) goto La0
            r15 = r11[r12]
            r14 = 48
            if (r15 < r14) goto L6b
            r14 = 57
            if (r15 > r14) goto L6b
            int r14 = 48 - r15
            int r16 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r16 < 0) goto L3c
            if (r16 != 0) goto L36
            long r1 = (long) r14
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 >= 0) goto L36
            goto L3c
        L36:
            r1 = 10
            long r3 = r3 * r1
            long r1 = (long) r14
            long r3 = r3 + r1
            goto L75
        L3c:
            okio.Buffer r1 = new okio.Buffer
            r1.<init>()
            okio.Buffer r1 = r1.writeDecimalLong(r3)
            okio.Buffer r1 = r1.writeByte(r15)
            if (r8 != 0) goto L4e
            r1.readByte()
        L4e:
            java.lang.NumberFormatException r2 = new java.lang.NumberFormatException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Number too large: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r1 = r1.readUtf8()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            throw r2
        L6b:
            r1 = 45
            if (r15 != r1) goto L7f
            if (r7 != 0) goto L7f
            r1 = 1
            long r5 = r5 - r1
            r8 = 1
        L75:
            int r12 = r12 + 1
            int r7 = r7 + 1
            r1 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            goto L1c
        L7f:
            if (r7 == 0) goto L83
            r9 = 1
            goto La0
        L83:
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Expected leading [0-9] or '-' character but was 0x"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = java.lang.Integer.toHexString(r15)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        La0:
            if (r12 != r13) goto Lac
            okio.Segment r1 = r10.pop()
            r0.head = r1
            okio.SegmentPool.recycle(r10)
            goto Lae
        Lac:
            r10.pos = r12
        Lae:
            if (r9 != 0) goto Lbc
            okio.Segment r1 = r0.head
            if (r1 != 0) goto Lb5
            goto Lbc
        Lb5:
            r1 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            goto L14
        Lbc:
            long r1 = r0.size
            long r5 = (long) r7
            long r1 = r1 - r5
            r0.size = r1
            if (r8 == 0) goto Lc5
            goto Lc6
        Lc5:
            long r3 = -r3
        Lc6:
            return r3
        Lc7:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "size == 0"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readDecimalLong():long");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a9 A[EDGE_INSN: B:42:0x00a9->B:38:0x00a9 ?: BREAK  , SYNTHETIC] */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long readHexadecimalUnsignedLong() {
        /*
            r14 = this;
            long r0 = r14.size
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto Lb0
            r0 = 0
            r1 = r0
            r4 = r2
        Lb:
            okio.Segment r6 = r14.head
            byte[] r7 = r6.data
            int r8 = r6.pos
            int r9 = r6.limit
        L13:
            if (r8 >= r9) goto L95
            r10 = r7[r8]
            r11 = 48
            if (r10 < r11) goto L22
            r11 = 57
            if (r10 > r11) goto L22
            int r11 = r10 + (-48)
            goto L3a
        L22:
            r11 = 97
            if (r10 < r11) goto L2f
            r11 = 102(0x66, float:1.43E-43)
            if (r10 > r11) goto L2f
            int r11 = r10 + (-97)
        L2c:
            int r11 = r11 + 10
            goto L3a
        L2f:
            r11 = 65
            if (r10 < r11) goto L74
            r11 = 70
            if (r10 > r11) goto L74
            int r11 = r10 + (-65)
            goto L2c
        L3a:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 != 0) goto L4a
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L13
        L4a:
            okio.Buffer r0 = new okio.Buffer
            r0.<init>()
            okio.Buffer r0 = r0.writeHexadecimalUnsignedLong(r4)
            okio.Buffer r0 = r0.writeByte(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Number too large: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r0 = r0.readUtf8()
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L74:
            if (r0 == 0) goto L78
            r1 = 1
            goto L95
        L78:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was 0x"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = java.lang.Integer.toHexString(r10)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L95:
            if (r8 != r9) goto La1
            okio.Segment r7 = r6.pop()
            r14.head = r7
            okio.SegmentPool.recycle(r6)
            goto La3
        La1:
            r6.pos = r8
        La3:
            if (r1 != 0) goto La9
            okio.Segment r6 = r14.head
            if (r6 != 0) goto Lb
        La9:
            long r1 = r14.size
            long r6 = (long) r0
            long r1 = r1 - r6
            r14.size = r1
            return r4
        Lb0:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "size == 0"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readHexadecimalUnsignedLong():long");
    }

    @Override // okio.BufferedSource
    public ByteString readByteString() {
        return new ByteString(readByteArray());
    }

    @Override // okio.BufferedSource
    public ByteString readByteString(long j2) throws EOFException {
        return new ByteString(readByteArray(j2));
    }

    @Override // okio.BufferedSource
    public int select(Options options) {
        int selectPrefix = selectPrefix(options, false);
        if (selectPrefix == -1) {
            return -1;
        }
        try {
            skip(options.byteStrings[selectPrefix].size());
            return selectPrefix;
        } catch (EOFException unused) {
            throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0055, code lost:
        if (r19 == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0057, code lost:
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0058, code lost:
        return r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int selectPrefix(okio.Options r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 161
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.selectPrefix(okio.Options, boolean):int");
    }

    @Override // okio.BufferedSource
    public void readFully(Buffer buffer, long j2) throws EOFException {
        long j3 = this.size;
        if (j3 < j2) {
            buffer.write(this, j3);
            throw new EOFException();
        } else {
            buffer.write(this, j2);
        }
    }

    @Override // okio.BufferedSource
    public long readAll(Sink sink) throws IOException {
        long j2 = this.size;
        if (j2 > 0) {
            sink.write(this, j2);
        }
        return j2;
    }

    @Override // okio.BufferedSource
    public String readUtf8() {
        try {
            return readString(this.size, Util.UTF_8);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // okio.BufferedSource
    public String readUtf8(long j2) throws EOFException {
        return readString(j2, Util.UTF_8);
    }

    @Override // okio.BufferedSource
    public String readString(Charset charset) {
        try {
            return readString(this.size, charset);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // okio.BufferedSource
    public String readString(long j2, Charset charset) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0L, j2);
        if (charset != null) {
            if (j2 <= 2147483647L) {
                if (j2 == 0) {
                    return "";
                }
                Segment segment = this.head;
                if (segment.pos + j2 > segment.limit) {
                    return new String(readByteArray(j2), charset);
                }
                String str = new String(segment.data, segment.pos, (int) j2, charset);
                segment.pos = (int) (segment.pos + j2);
                this.size -= j2;
                if (segment.pos == segment.limit) {
                    this.head = segment.pop();
                    SegmentPool.recycle(segment);
                }
                return str;
            }
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j2);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // okio.BufferedSource
    @Nullable
    public String readUtf8Line() throws EOFException {
        long indexOf = indexOf((byte) 10);
        if (indexOf == -1) {
            long j2 = this.size;
            if (j2 != 0) {
                return readUtf8(j2);
            }
            return null;
        }
        return readUtf8Line(indexOf);
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict() throws EOFException {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict(long j2) throws EOFException {
        if (j2 < 0) {
            throw new IllegalArgumentException("limit < 0: " + j2);
        }
        long j3 = j2 != Long.MAX_VALUE ? j2 + 1 : Long.MAX_VALUE;
        long indexOf = indexOf((byte) 10, 0L, j3);
        if (indexOf != -1) {
            return readUtf8Line(indexOf);
        }
        if (j3 < size() && getByte(j3 - 1) == 13 && getByte(j3) == 10) {
            return readUtf8Line(j3);
        }
        Buffer buffer = new Buffer();
        copyTo(buffer, 0L, Math.min(32L, size()));
        throw new EOFException("\\n not found: limit=" + Math.min(size(), j2) + " content=" + buffer.readByteString().hex() + (char) 8230);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String readUtf8Line(long j2) throws EOFException {
        if (j2 > 0) {
            long j3 = j2 - 1;
            if (getByte(j3) == 13) {
                String readUtf8 = readUtf8(j3);
                skip(2L);
                return readUtf8;
            }
        }
        String readUtf82 = readUtf8(j2);
        skip(1L);
        return readUtf82;
    }

    @Override // okio.BufferedSource
    public int readUtf8CodePoint() throws EOFException {
        int i2;
        int i3;
        int i4;
        if (this.size == 0) {
            throw new EOFException();
        }
        byte b2 = getByte(0L);
        if ((b2 & 128) == 0) {
            i2 = b2 & Byte.MAX_VALUE;
            i4 = 0;
            i3 = 1;
        } else if ((b2 & 224) == 192) {
            i2 = b2 & Ascii.US;
            i3 = 2;
            i4 = 128;
        } else if ((b2 & 240) == 224) {
            i2 = b2 & Ascii.SI;
            i3 = 3;
            i4 = 2048;
        } else if ((b2 & 248) != 240) {
            skip(1L);
            return REPLACEMENT_CHARACTER;
        } else {
            i2 = b2 & 7;
            i3 = 4;
            i4 = 65536;
        }
        long j2 = i3;
        if (this.size < j2) {
            throw new EOFException("size < " + i3 + ": " + this.size + " (to read code point prefixed 0x" + Integer.toHexString(b2) + ")");
        }
        for (int i5 = 1; i5 < i3; i5++) {
            long j3 = i5;
            byte b3 = getByte(j3);
            if ((b3 & 192) != 128) {
                skip(j3);
                return REPLACEMENT_CHARACTER;
            }
            i2 = (i2 << 6) | (b3 & 63);
        }
        skip(j2);
        return i2 > 1114111 ? REPLACEMENT_CHARACTER : ((i2 < 55296 || i2 > 57343) && i2 >= i4) ? i2 : REPLACEMENT_CHARACTER;
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray() {
        try {
            return readByteArray(this.size);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray(long j2) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0L, j2);
        if (j2 > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j2);
        }
        byte[] bArr = new byte[(int) j2];
        readFully(bArr);
        return bArr;
    }

    @Override // okio.BufferedSource
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] bArr) throws EOFException {
        int i2 = 0;
        while (i2 < bArr.length) {
            int read = read(bArr, i2, bArr.length - i2);
            if (read == -1) {
                throw new EOFException();
            }
            i2 += read;
        }
    }

    @Override // okio.BufferedSource
    public int read(byte[] bArr, int i2, int i3) {
        Util.checkOffsetAndCount(bArr.length, i2, i3);
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(i3, segment.limit - segment.pos);
        System.arraycopy(segment.data, segment.pos, bArr, i2, min);
        segment.pos += min;
        this.size -= min;
        if (segment.pos == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), segment.limit - segment.pos);
        byteBuffer.put(segment.data, segment.pos, min);
        segment.pos += min;
        this.size -= min;
        if (segment.pos == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    public final void clear() {
        try {
            skip(this.size);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // okio.BufferedSource
    public void skip(long j2) throws EOFException {
        Segment segment;
        while (j2 > 0) {
            if (this.head == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j2, segment.limit - this.head.pos);
            long j3 = min;
            this.size -= j3;
            j2 -= j3;
            this.head.pos += min;
            if (this.head.pos == this.head.limit) {
                Segment segment2 = this.head;
                this.head = segment2.pop();
                SegmentPool.recycle(segment2);
            }
        }
    }

    @Override // okio.BufferedSink
    public Buffer write(ByteString byteString) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        byteString.write(this);
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String str) {
        return writeUtf8(str, 0, str.length());
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String str, int i2, int i3) {
        if (str != null) {
            if (i2 >= 0) {
                if (i3 < i2) {
                    throw new IllegalArgumentException("endIndex < beginIndex: " + i3 + " < " + i2);
                }
                if (i3 <= str.length()) {
                    while (i2 < i3) {
                        char charAt = str.charAt(i2);
                        if (charAt < 128) {
                            Segment writableSegment = writableSegment(1);
                            byte[] bArr = writableSegment.data;
                            int i4 = writableSegment.limit - i2;
                            int min = Math.min(i3, 8192 - i4);
                            int i5 = i2 + 1;
                            bArr[i2 + i4] = (byte) charAt;
                            while (i5 < min) {
                                char charAt2 = str.charAt(i5);
                                if (charAt2 >= 128) {
                                    break;
                                }
                                bArr[i5 + i4] = (byte) charAt2;
                                i5++;
                            }
                            int i6 = (i4 + i5) - writableSegment.limit;
                            writableSegment.limit += i6;
                            this.size += i6;
                            i2 = i5;
                        } else {
                            if (charAt < 2048) {
                                writeByte((charAt >> 6) | JfifUtil.MARKER_SOFn);
                                writeByte((charAt & '?') | 128);
                            } else if (charAt < 55296 || charAt > 57343) {
                                writeByte((charAt >> '\f') | 224);
                                writeByte(((charAt >> 6) & 63) | 128);
                                writeByte((charAt & '?') | 128);
                            } else {
                                int i7 = i2 + 1;
                                char charAt3 = i7 < i3 ? str.charAt(i7) : (char) 0;
                                if (charAt > 56319 || charAt3 < 56320 || charAt3 > 57343) {
                                    writeByte(63);
                                    i2 = i7;
                                } else {
                                    int i8 = (((charAt & 10239) << 10) | (9215 & charAt3)) + 65536;
                                    writeByte((i8 >> 18) | a.MAX_TOTAL_LOC_REQUEST_TIME_10_MINS_SECOND);
                                    writeByte(((i8 >> 12) & 63) | 128);
                                    writeByte(((i8 >> 6) & 63) | 128);
                                    writeByte((i8 & 63) | 128);
                                    i2 += 2;
                                }
                            }
                            i2++;
                        }
                    }
                    return this;
                }
                throw new IllegalArgumentException("endIndex > string.length: " + i3 + " > " + str.length());
            }
            throw new IllegalArgumentException("beginIndex < 0: " + i2);
        }
        throw new IllegalArgumentException("string == null");
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8CodePoint(int i2) {
        if (i2 < 128) {
            writeByte(i2);
        } else if (i2 < 2048) {
            writeByte((i2 >> 6) | JfifUtil.MARKER_SOFn);
            writeByte((i2 & 63) | 128);
        } else if (i2 < 65536) {
            if (i2 >= 55296 && i2 <= 57343) {
                writeByte(63);
            } else {
                writeByte((i2 >> 12) | 224);
                writeByte(((i2 >> 6) & 63) | 128);
                writeByte((i2 & 63) | 128);
            }
        } else if (i2 <= 1114111) {
            writeByte((i2 >> 18) | a.MAX_TOTAL_LOC_REQUEST_TIME_10_MINS_SECOND);
            writeByte(((i2 >> 12) & 63) | 128);
            writeByte(((i2 >> 6) & 63) | 128);
            writeByte((i2 & 63) | 128);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i2));
        }
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeString(String str, Charset charset) {
        return writeString(str, 0, str.length(), charset);
    }

    @Override // okio.BufferedSink
    public Buffer writeString(String str, int i2, int i3, Charset charset) {
        if (str != null) {
            if (i2 >= 0) {
                if (i3 < i2) {
                    throw new IllegalArgumentException("endIndex < beginIndex: " + i3 + " < " + i2);
                }
                if (i3 <= str.length()) {
                    if (charset == null) {
                        throw new IllegalArgumentException("charset == null");
                    }
                    if (charset.equals(Util.UTF_8)) {
                        return writeUtf8(str, i2, i3);
                    }
                    byte[] bytes = str.substring(i2, i3).getBytes(charset);
                    return write(bytes, 0, bytes.length);
                }
                throw new IllegalArgumentException("endIndex > string.length: " + i3 + " > " + str.length());
            }
            throw new IllegalAccessError("beginIndex < 0: " + i2);
        }
        throw new IllegalArgumentException("string == null");
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        return write(bArr, 0, bArr.length);
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] bArr, int i2, int i3) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j2 = i3;
        Util.checkOffsetAndCount(bArr.length, i2, j2);
        int i4 = i3 + i2;
        while (i2 < i4) {
            Segment writableSegment = writableSegment(1);
            int min = Math.min(i4 - i2, 8192 - writableSegment.limit);
            System.arraycopy(bArr, i2, writableSegment.data, writableSegment.limit, min);
            i2 += min;
            writableSegment.limit += min;
        }
        this.size += j2;
        return this;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        int remaining = byteBuffer.remaining();
        int i2 = remaining;
        while (i2 > 0) {
            Segment writableSegment = writableSegment(1);
            int min = Math.min(i2, 8192 - writableSegment.limit);
            byteBuffer.get(writableSegment.data, writableSegment.limit, min);
            i2 -= min;
            writableSegment.limit += min;
        }
        this.size += remaining;
        return remaining;
    }

    @Override // okio.BufferedSink
    public long writeAll(Source source) throws IOException {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j2 = 0;
        while (true) {
            long read = source.read(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (read == -1) {
                return j2;
            }
            j2 += read;
        }
    }

    @Override // okio.BufferedSink
    public BufferedSink write(Source source, long j2) throws IOException {
        while (j2 > 0) {
            long read = source.read(this, j2);
            if (read == -1) {
                throw new EOFException();
            }
            j2 -= read;
        }
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeByte(int i2) {
        Segment writableSegment = writableSegment(1);
        byte[] bArr = writableSegment.data;
        int i3 = writableSegment.limit;
        writableSegment.limit = i3 + 1;
        bArr[i3] = (byte) i2;
        this.size++;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeShort(int i2) {
        Segment writableSegment = writableSegment(2);
        byte[] bArr = writableSegment.data;
        int i3 = writableSegment.limit;
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 8) & 255);
        bArr[i4] = (byte) (i2 & 255);
        writableSegment.limit = i4 + 1;
        this.size += 2;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeShortLe(int i2) {
        return writeShort((int) Util.reverseBytesShort((short) i2));
    }

    @Override // okio.BufferedSink
    public Buffer writeInt(int i2) {
        Segment writableSegment = writableSegment(4);
        byte[] bArr = writableSegment.data;
        int i3 = writableSegment.limit;
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 24) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i2 >>> 16) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((i2 >>> 8) & 255);
        bArr[i6] = (byte) (i2 & 255);
        writableSegment.limit = i6 + 1;
        this.size += 4;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeIntLe(int i2) {
        return writeInt(Util.reverseBytesInt(i2));
    }

    @Override // okio.BufferedSink
    public Buffer writeLong(long j2) {
        Segment writableSegment = writableSegment(8);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((j2 >>> 56) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((j2 >>> 48) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((j2 >>> 40) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((j2 >>> 32) & 255);
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((j2 >>> 24) & 255);
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((j2 >>> 16) & 255);
        int i9 = i8 + 1;
        bArr[i8] = (byte) ((j2 >>> 8) & 255);
        bArr[i9] = (byte) (j2 & 255);
        writableSegment.limit = i9 + 1;
        this.size += 8;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeLongLe(long j2) {
        return writeLong(Util.reverseBytesLong(j2));
    }

    @Override // okio.BufferedSink
    public Buffer writeDecimalLong(long j2) {
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (i2 == 0) {
            return writeByte(48);
        }
        boolean z = false;
        int i3 = 1;
        if (i2 < 0) {
            j2 = -j2;
            if (j2 < 0) {
                return writeUtf8("-9223372036854775808");
            }
            z = true;
        }
        if (j2 >= 100000000) {
            i3 = j2 < 1000000000000L ? j2 < 10000000000L ? j2 < 1000000000 ? 9 : 10 : j2 < 100000000000L ? 11 : 12 : j2 < 1000000000000000L ? j2 < 10000000000000L ? 13 : j2 < 100000000000000L ? 14 : 15 : j2 < 100000000000000000L ? j2 < 10000000000000000L ? 16 : 17 : j2 < 1000000000000000000L ? 18 : 19;
        } else if (j2 >= 10000) {
            i3 = j2 < 1000000 ? j2 < 100000 ? 5 : 6 : j2 < 10000000 ? 7 : 8;
        } else if (j2 >= 100) {
            i3 = j2 < 1000 ? 3 : 4;
        } else if (j2 >= 10) {
            i3 = 2;
        }
        if (z) {
            i3++;
        }
        Segment writableSegment = writableSegment(i3);
        byte[] bArr = writableSegment.data;
        int i4 = writableSegment.limit + i3;
        while (j2 != 0) {
            i4--;
            bArr[i4] = DIGITS[(int) (j2 % 10)];
            j2 /= 10;
        }
        if (z) {
            bArr[i4 - 1] = 45;
        }
        writableSegment.limit += i3;
        this.size += i3;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeHexadecimalUnsignedLong(long j2) {
        if (j2 == 0) {
            return writeByte(48);
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j2)) / 4) + 1;
        Segment writableSegment = writableSegment(numberOfTrailingZeros);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        for (int i3 = (writableSegment.limit + numberOfTrailingZeros) - 1; i3 >= i2; i3--) {
            bArr[i3] = DIGITS[(int) (15 & j2)];
            j2 >>>= 4;
        }
        writableSegment.limit += numberOfTrailingZeros;
        this.size += numberOfTrailingZeros;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Segment writableSegment(int i2) {
        if (i2 < 1 || i2 > 8192) {
            throw new IllegalArgumentException();
        }
        Segment segment = this.head;
        if (segment == null) {
            Segment take = SegmentPool.take();
            this.head = take;
            take.prev = take;
            take.next = take;
            return take;
        }
        Segment segment2 = segment.prev;
        return (segment2.limit + i2 > 8192 || !segment2.owner) ? segment2.push(SegmentPool.take()) : segment2;
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j2) {
        if (buffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (buffer == this) {
            throw new IllegalArgumentException("source == this");
        }
        Util.checkOffsetAndCount(buffer.size, 0L, j2);
        while (j2 > 0) {
            if (j2 < buffer.head.limit - buffer.head.pos) {
                Segment segment = this.head;
                Segment segment2 = segment != null ? segment.prev : null;
                if (segment2 != null && segment2.owner) {
                    if ((segment2.limit + j2) - (segment2.shared ? 0 : segment2.pos) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                        buffer.head.writeTo(segment2, (int) j2);
                        buffer.size -= j2;
                        this.size += j2;
                        return;
                    }
                }
                buffer.head = buffer.head.split((int) j2);
            }
            Segment segment3 = buffer.head;
            long j3 = segment3.limit - segment3.pos;
            buffer.head = segment3.pop();
            Segment segment4 = this.head;
            if (segment4 == null) {
                this.head = segment3;
                segment3.prev = segment3;
                segment3.next = segment3;
            } else {
                segment4.prev.push(segment3).compact();
            }
            buffer.size -= j3;
            this.size += j3;
            j2 -= j3;
        }
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j2) {
        if (buffer != null) {
            if (j2 < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j2);
            }
            long j3 = this.size;
            if (j3 == 0) {
                return -1L;
            }
            if (j2 > j3) {
                j2 = j3;
            }
            buffer.write(this, j2);
            return j2;
        }
        throw new IllegalArgumentException("sink == null");
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b2) {
        return indexOf(b2, 0L, Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b2, long j2) {
        return indexOf(b2, j2, Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b2, long j2, long j3) {
        Segment segment;
        long j4 = 0;
        if (j2 < 0 || j3 < j2) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.size), Long.valueOf(j2), Long.valueOf(j3)));
        }
        long j5 = this.size;
        long j6 = j3 > j5 ? j5 : j3;
        if (j2 == j6 || (segment = this.head) == null) {
            return -1L;
        }
        if (j5 - j2 < j2) {
            while (j5 > j2) {
                segment = segment.prev;
                j5 -= segment.limit - segment.pos;
            }
        } else {
            while (true) {
                long j7 = (segment.limit - segment.pos) + j4;
                if (j7 >= j2) {
                    break;
                }
                segment = segment.next;
                j4 = j7;
            }
            j5 = j4;
        }
        long j8 = j2;
        while (j5 < j6) {
            byte[] bArr = segment.data;
            int min = (int) Math.min(segment.limit, (segment.pos + j6) - j5);
            for (int i2 = (int) ((segment.pos + j8) - j5); i2 < min; i2++) {
                if (bArr[i2] == b2) {
                    return (i2 - segment.pos) + j5;
                }
            }
            j5 += segment.limit - segment.pos;
            segment = segment.next;
            j8 = j5;
        }
        return -1L;
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString byteString) throws IOException {
        return indexOf(byteString, 0L);
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString byteString, long j2) throws IOException {
        byte[] bArr;
        if (byteString.size() != 0) {
            long j3 = 0;
            if (j2 < 0) {
                throw new IllegalArgumentException("fromIndex < 0");
            }
            Segment segment = this.head;
            long j4 = -1;
            if (segment == null) {
                return -1L;
            }
            long j5 = this.size;
            if (j5 - j2 < j2) {
                while (j5 > j2) {
                    segment = segment.prev;
                    j5 -= segment.limit - segment.pos;
                }
            } else {
                while (true) {
                    long j6 = (segment.limit - segment.pos) + j3;
                    if (j6 >= j2) {
                        break;
                    }
                    segment = segment.next;
                    j3 = j6;
                }
                j5 = j3;
            }
            byte b2 = byteString.getByte(0);
            int size = byteString.size();
            long j7 = 1 + (this.size - size);
            long j8 = j2;
            Segment segment2 = segment;
            long j9 = j5;
            while (j9 < j7) {
                byte[] bArr2 = segment2.data;
                int min = (int) Math.min(segment2.limit, (segment2.pos + j7) - j9);
                int i2 = (int) ((segment2.pos + j8) - j9);
                while (i2 < min) {
                    if (bArr2[i2] == b2) {
                        bArr = bArr2;
                        if (rangeEquals(segment2, i2 + 1, byteString, 1, size)) {
                            return (i2 - segment2.pos) + j9;
                        }
                    } else {
                        bArr = bArr2;
                    }
                    i2++;
                    bArr2 = bArr;
                }
                j9 += segment2.limit - segment2.pos;
                segment2 = segment2.next;
                j8 = j9;
                j4 = -1;
            }
            return j4;
        }
        throw new IllegalArgumentException("bytes is empty");
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString byteString) {
        return indexOfElement(byteString, 0L);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString byteString, long j2) {
        int i2;
        int i3;
        long j3 = 0;
        if (j2 < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        Segment segment = this.head;
        if (segment == null) {
            return -1L;
        }
        long j4 = this.size;
        if (j4 - j2 < j2) {
            while (j4 > j2) {
                segment = segment.prev;
                j4 -= segment.limit - segment.pos;
            }
        } else {
            while (true) {
                long j5 = (segment.limit - segment.pos) + j3;
                if (j5 >= j2) {
                    break;
                }
                segment = segment.next;
                j3 = j5;
            }
            j4 = j3;
        }
        if (byteString.size() == 2) {
            byte b2 = byteString.getByte(0);
            byte b3 = byteString.getByte(1);
            while (j4 < this.size) {
                byte[] bArr = segment.data;
                i2 = (int) ((segment.pos + j2) - j4);
                int i4 = segment.limit;
                while (i2 < i4) {
                    byte b4 = bArr[i2];
                    if (b4 == b2 || b4 == b3) {
                        i3 = segment.pos;
                        return (i2 - i3) + j4;
                    }
                    i2++;
                }
                j4 += segment.limit - segment.pos;
                segment = segment.next;
                j2 = j4;
            }
            return -1L;
        }
        byte[] internalArray = byteString.internalArray();
        while (j4 < this.size) {
            byte[] bArr2 = segment.data;
            i2 = (int) ((segment.pos + j2) - j4);
            int i5 = segment.limit;
            while (i2 < i5) {
                byte b5 = bArr2[i2];
                for (byte b6 : internalArray) {
                    if (b5 == b6) {
                        i3 = segment.pos;
                        return (i2 - i3) + j4;
                    }
                }
                i2++;
            }
            j4 += segment.limit - segment.pos;
            segment = segment.next;
            j2 = j4;
        }
        return -1L;
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j2, ByteString byteString) {
        return rangeEquals(j2, byteString, 0, byteString.size());
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j2, ByteString byteString, int i2, int i3) {
        if (j2 < 0 || i2 < 0 || i3 < 0 || this.size - j2 < i3 || byteString.size() - i2 < i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (getByte(i4 + j2) != byteString.getByte(i2 + i4)) {
                return false;
            }
        }
        return true;
    }

    private boolean rangeEquals(Segment segment, int i2, ByteString byteString, int i3, int i4) {
        int i5 = segment.limit;
        byte[] bArr = segment.data;
        while (i3 < i4) {
            if (i2 == i5) {
                segment = segment.next;
                byte[] bArr2 = segment.data;
                bArr = bArr2;
                i2 = segment.pos;
                i5 = segment.limit;
            }
            if (bArr[i2] != byteString.getByte(i3)) {
                return false;
            }
            i2++;
            i3++;
        }
        return true;
    }

    @Override // okio.Source
    public Timeout timeout() {
        return Timeout.NONE;
    }

    List<Integer> segmentSizes() {
        if (this.head == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(this.head.limit - this.head.pos));
        Segment segment = this.head;
        while (true) {
            segment = segment.next;
            if (segment == this.head) {
                return arrayList;
            }
            arrayList.add(Integer.valueOf(segment.limit - segment.pos));
        }
    }

    public final ByteString md5() {
        return digest("MD5");
    }

    public final ByteString sha1() {
        return digest(n.SHA_1);
    }

    public final ByteString sha256() {
        return digest(n.SHA_256);
    }

    public final ByteString sha512() {
        return digest("SHA-512");
    }

    private ByteString digest(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            Segment segment = this.head;
            if (segment != null) {
                messageDigest.update(segment.data, this.head.pos, this.head.limit - this.head.pos);
                Segment segment2 = this.head;
                while (true) {
                    segment2 = segment2.next;
                    if (segment2 == this.head) {
                        break;
                    }
                    messageDigest.update(segment2.data, segment2.pos, segment2.limit - segment2.pos);
                }
            }
            return ByteString.of(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    public final ByteString hmacSha1(ByteString byteString) {
        return hmac("HmacSHA1", byteString);
    }

    public final ByteString hmacSha256(ByteString byteString) {
        return hmac("HmacSHA256", byteString);
    }

    public final ByteString hmacSha512(ByteString byteString) {
        return hmac("HmacSHA512", byteString);
    }

    private ByteString hmac(String str, ByteString byteString) {
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            Segment segment = this.head;
            if (segment != null) {
                mac.update(segment.data, this.head.pos, this.head.limit - this.head.pos);
                Segment segment2 = this.head;
                while (true) {
                    segment2 = segment2.next;
                    if (segment2 == this.head) {
                        break;
                    }
                    mac.update(segment2.data, segment2.pos, segment2.limit - segment2.pos);
                }
            }
            return ByteString.of(mac.doFinal());
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Buffer) {
            Buffer buffer = (Buffer) obj;
            long j2 = this.size;
            if (j2 != buffer.size) {
                return false;
            }
            long j3 = 0;
            if (j2 == 0) {
                return true;
            }
            Segment segment = this.head;
            Segment segment2 = buffer.head;
            int i2 = segment.pos;
            int i3 = segment2.pos;
            while (j3 < this.size) {
                long min = Math.min(segment.limit - i2, segment2.limit - i3);
                int i4 = 0;
                while (i4 < min) {
                    int i5 = i2 + 1;
                    int i6 = i3 + 1;
                    if (segment.data[i2] != segment2.data[i3]) {
                        return false;
                    }
                    i4++;
                    i2 = i5;
                    i3 = i6;
                }
                if (i2 == segment.limit) {
                    segment = segment.next;
                    i2 = segment.pos;
                }
                if (i3 == segment2.limit) {
                    segment2 = segment2.next;
                    i3 = segment2.pos;
                }
                j3 += min;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        Segment segment = this.head;
        if (segment == null) {
            return 0;
        }
        int i2 = 1;
        do {
            int i3 = segment.limit;
            for (int i4 = segment.pos; i4 < i3; i4++) {
                i2 = (i2 * 31) + segment.data[i4];
            }
            segment = segment.next;
        } while (segment != this.head);
        return i2;
    }

    public String toString() {
        return snapshot().toString();
    }

    public Buffer clone() {
        Buffer buffer = new Buffer();
        if (this.size == 0) {
            return buffer;
        }
        Segment sharedCopy = this.head.sharedCopy();
        buffer.head = sharedCopy;
        sharedCopy.prev = sharedCopy;
        sharedCopy.next = sharedCopy;
        Segment segment = this.head;
        while (true) {
            segment = segment.next;
            if (segment != this.head) {
                buffer.head.prev.push(segment.sharedCopy());
            } else {
                buffer.size = this.size;
                return buffer;
            }
        }
    }

    public final ByteString snapshot() {
        long j2 = this.size;
        if (j2 > 2147483647L) {
            throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.size);
        }
        return snapshot((int) j2);
    }

    public final ByteString snapshot(int i2) {
        if (i2 == 0) {
            return ByteString.EMPTY;
        }
        return new SegmentedByteString(this, i2);
    }

    public final UnsafeCursor readUnsafe() {
        return readUnsafe(new UnsafeCursor());
    }

    public final UnsafeCursor readUnsafe(UnsafeCursor unsafeCursor) {
        if (unsafeCursor.buffer != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        unsafeCursor.buffer = this;
        unsafeCursor.readWrite = false;
        return unsafeCursor;
    }

    public final UnsafeCursor readAndWriteUnsafe() {
        return readAndWriteUnsafe(new UnsafeCursor());
    }

    public final UnsafeCursor readAndWriteUnsafe(UnsafeCursor unsafeCursor) {
        if (unsafeCursor.buffer != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        unsafeCursor.buffer = this;
        unsafeCursor.readWrite = true;
        return unsafeCursor;
    }

    /* loaded from: classes10.dex */
    public static final class UnsafeCursor implements Closeable {
        public Buffer buffer;
        public byte[] data;
        public boolean readWrite;
        private Segment segment;
        public long offset = -1;
        public int start = -1;
        public int end = -1;

        public final int next() {
            if (this.offset == this.buffer.size) {
                throw new IllegalStateException();
            }
            long j2 = this.offset;
            return j2 == -1 ? seek(0L) : seek(j2 + (this.end - this.start));
        }

        public final int seek(long j2) {
            int i2 = (j2 > (-1L) ? 1 : (j2 == (-1L) ? 0 : -1));
            if (i2 < 0 || j2 > this.buffer.size) {
                throw new ArrayIndexOutOfBoundsException(String.format("offset=%s > size=%s", Long.valueOf(j2), Long.valueOf(this.buffer.size)));
            }
            if (i2 == 0 || j2 == this.buffer.size) {
                this.segment = null;
                this.offset = j2;
                this.data = null;
                this.start = -1;
                this.end = -1;
                return -1;
            }
            long j3 = 0;
            long j4 = this.buffer.size;
            Segment segment = this.buffer.head;
            Segment segment2 = this.buffer.head;
            Segment segment3 = this.segment;
            if (segment3 != null) {
                long j5 = this.offset - (this.start - segment3.pos);
                if (j5 > j2) {
                    segment2 = this.segment;
                    j4 = j5;
                } else {
                    segment = this.segment;
                    j3 = j5;
                }
            }
            if (j4 - j2 > j2 - j3) {
                while (j2 >= (segment.limit - segment.pos) + j3) {
                    j3 += segment.limit - segment.pos;
                    segment = segment.next;
                }
            } else {
                while (j4 > j2) {
                    segment2 = segment2.prev;
                    j4 -= segment2.limit - segment2.pos;
                }
                j3 = j4;
                segment = segment2;
            }
            if (this.readWrite && segment.shared) {
                Segment unsharedCopy = segment.unsharedCopy();
                if (this.buffer.head == segment) {
                    this.buffer.head = unsharedCopy;
                }
                segment = segment.push(unsharedCopy);
                segment.prev.pop();
            }
            this.segment = segment;
            this.offset = j2;
            this.data = segment.data;
            this.start = segment.pos + ((int) (j2 - j3));
            int i3 = segment.limit;
            this.end = i3;
            return i3 - this.start;
        }

        public final long resizeBuffer(long j2) {
            Buffer buffer = this.buffer;
            if (buffer == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            if (!this.readWrite) {
                throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers");
            }
            long j3 = buffer.size;
            int i2 = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
            if (i2 <= 0) {
                if (j2 < 0) {
                    throw new IllegalArgumentException("newSize < 0: " + j2);
                }
                long j4 = j3 - j2;
                while (true) {
                    if (j4 <= 0) {
                        break;
                    }
                    Segment segment = this.buffer.head.prev;
                    long j5 = segment.limit - segment.pos;
                    if (j5 <= j4) {
                        this.buffer.head = segment.pop();
                        SegmentPool.recycle(segment);
                        j4 -= j5;
                    } else {
                        segment.limit = (int) (segment.limit - j4);
                        break;
                    }
                }
                this.segment = null;
                this.offset = j2;
                this.data = null;
                this.start = -1;
                this.end = -1;
            } else if (i2 > 0) {
                long j6 = j2 - j3;
                boolean z = true;
                while (j6 > 0) {
                    Segment writableSegment = this.buffer.writableSegment(1);
                    int min = (int) Math.min(j6, 8192 - writableSegment.limit);
                    writableSegment.limit += min;
                    j6 -= min;
                    if (z) {
                        this.segment = writableSegment;
                        this.offset = j3;
                        this.data = writableSegment.data;
                        this.start = writableSegment.limit - min;
                        this.end = writableSegment.limit;
                        z = false;
                    }
                }
            }
            this.buffer.size = j2;
            return j3;
        }

        public final long expandBuffer(int i2) {
            if (i2 > 0) {
                if (i2 > 8192) {
                    throw new IllegalArgumentException("minByteCount > Segment.SIZE: " + i2);
                }
                Buffer buffer = this.buffer;
                if (buffer == null) {
                    throw new IllegalStateException("not attached to a buffer");
                }
                if (!this.readWrite) {
                    throw new IllegalStateException("expandBuffer() only permitted for read/write buffers");
                }
                long j2 = buffer.size;
                Segment writableSegment = this.buffer.writableSegment(i2);
                int i3 = 8192 - writableSegment.limit;
                writableSegment.limit = 8192;
                long j3 = i3;
                this.buffer.size = j2 + j3;
                this.segment = writableSegment;
                this.offset = j2;
                this.data = writableSegment.data;
                this.start = 8192 - i3;
                this.end = 8192;
                return j3;
            }
            throw new IllegalArgumentException("minByteCount <= 0: " + i2);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.buffer == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            this.buffer = null;
            this.segment = null;
            this.offset = -1L;
            this.data = null;
            this.start = -1;
            this.end = -1;
        }
    }
}
