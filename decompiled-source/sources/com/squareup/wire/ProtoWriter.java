package com.squareup.wire;

import java.io.IOException;
import okio.BufferedSink;
import okio.ByteString;
/* loaded from: classes9.dex */
public final class ProtoWriter {
    private final BufferedSink sink;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeZigZag32(int i2) {
        return (-(i2 & 1)) ^ (i2 >>> 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long decodeZigZag64(long j2) {
        return (-(j2 & 1)) ^ (j2 >>> 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int encodeZigZag32(int i2) {
        return (i2 >> 31) ^ (i2 << 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long encodeZigZag64(long j2) {
        return (j2 >> 63) ^ (j2 << 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int varint32Size(int i2) {
        if ((i2 & (-128)) == 0) {
            return 1;
        }
        if ((i2 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i2) == 0) {
            return 3;
        }
        return (i2 & (-268435456)) == 0 ? 4 : 5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int varint64Size(long j2) {
        if (((-128) & j2) == 0) {
            return 1;
        }
        if (((-16384) & j2) == 0) {
            return 2;
        }
        if (((-2097152) & j2) == 0) {
            return 3;
        }
        if (((-268435456) & j2) == 0) {
            return 4;
        }
        if (((-34359738368L) & j2) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j2) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j2) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j2) == 0) {
            return 8;
        }
        return (j2 & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    private static int makeTag(int i2, FieldEncoding fieldEncoding) {
        return (i2 << 3) | fieldEncoding.value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int tagSize(int i2) {
        return varint32Size(makeTag(i2, FieldEncoding.VARINT));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int utf8Length(String str) {
        int i2;
        int length = str.length();
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            char charAt = str.charAt(i3);
            if (charAt >= 128) {
                if (charAt < 2048) {
                    i4 += 2;
                } else if (charAt < 55296 || charAt > 57343) {
                    i4 += 3;
                } else if (charAt <= 56319 && (i2 = i3 + 1) < length && str.charAt(i2) >= 56320 && str.charAt(i2) <= 57343) {
                    i4 += 4;
                    i3 = i2;
                }
                i3++;
            }
            i4++;
            i3++;
        }
        return i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int int32Size(int i2) {
        if (i2 >= 0) {
            return varint32Size(i2);
        }
        return 10;
    }

    public ProtoWriter(BufferedSink bufferedSink) {
        this.sink = bufferedSink;
    }

    public void writeBytes(ByteString byteString) throws IOException {
        this.sink.write(byteString);
    }

    public void writeString(String str) throws IOException {
        this.sink.writeUtf8(str);
    }

    public void writeTag(int i2, FieldEncoding fieldEncoding) throws IOException {
        writeVarint32(makeTag(i2, fieldEncoding));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeSignedVarint32(int i2) throws IOException {
        if (i2 >= 0) {
            writeVarint32(i2);
        } else {
            writeVarint64(i2);
        }
    }

    public void writeVarint32(int i2) throws IOException {
        while ((i2 & (-128)) != 0) {
            this.sink.writeByte((i2 & 127) | 128);
            i2 >>>= 7;
        }
        this.sink.writeByte(i2);
    }

    public void writeVarint64(long j2) throws IOException {
        while (((-128) & j2) != 0) {
            this.sink.writeByte((((int) j2) & 127) | 128);
            j2 >>>= 7;
        }
        this.sink.writeByte((int) j2);
    }

    public void writeFixed32(int i2) throws IOException {
        this.sink.writeIntLe(i2);
    }

    public void writeFixed64(long j2) throws IOException {
        this.sink.writeLongLe(j2);
    }
}
