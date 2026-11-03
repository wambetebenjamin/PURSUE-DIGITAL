package com.google.firebase.encoders.proto;

import java.io.OutputStream;
/* loaded from: classes9.dex */
final class LengthCountingOutputStream extends OutputStream {
    private long length = 0;

    @Override // java.io.OutputStream
    public void write(int i2) {
        this.length++;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        this.length += bArr.length;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        int i4;
        if (i2 < 0 || i2 > bArr.length || i3 < 0 || (i4 = i2 + i3) > bArr.length || i4 < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.length += i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getLength() {
        return this.length;
    }
}
