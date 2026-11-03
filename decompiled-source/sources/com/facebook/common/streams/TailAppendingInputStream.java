package com.facebook.common.streams;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
/* loaded from: classes8.dex */
public class TailAppendingInputStream extends FilterInputStream {
    private int mMarkedTailOffset;
    private final byte[] mTail;
    private int mTailOffset;

    public TailAppendingInputStream(InputStream inputStream, byte[] bArr) {
        super(inputStream);
        Objects.requireNonNull(inputStream);
        Objects.requireNonNull(bArr);
        this.mTail = bArr;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int read = this.in.read();
        return read != -1 ? read : readNextTailByte();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        int read = this.in.read(bArr, i2, i3);
        if (read != -1) {
            return read;
        }
        int i4 = 0;
        if (i3 == 0) {
            return 0;
        }
        while (i4 < i3) {
            int readNextTailByte = readNextTailByte();
            if (readNextTailByte == -1) {
                break;
            }
            bArr[i2 + i4] = (byte) readNextTailByte;
            i4++;
        }
        if (i4 > 0) {
            return i4;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() {
        if (this.in.markSupported()) {
            this.in.reset();
            this.mTailOffset = this.mMarkedTailOffset;
            return;
        }
        throw new IOException("mark is not supported");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i2) {
        if (this.in.markSupported()) {
            super.mark(i2);
            this.mMarkedTailOffset = this.mTailOffset;
        }
    }

    private int readNextTailByte() {
        int i2 = this.mTailOffset;
        byte[] bArr = this.mTail;
        if (i2 >= bArr.length) {
            return -1;
        }
        this.mTailOffset = i2 + 1;
        return bArr[i2] & 255;
    }
}
