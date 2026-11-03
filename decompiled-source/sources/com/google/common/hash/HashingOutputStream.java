package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public final class HashingOutputStream extends FilterOutputStream {
    private final Hasher hasher;

    public HashingOutputStream(HashFunction hashFunction, OutputStream outputStream) {
        super((OutputStream) Preconditions.checkNotNull(outputStream));
        this.hasher = (Hasher) Preconditions.checkNotNull(hashFunction.newHasher());
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i2) throws IOException {
        this.hasher.putByte((byte) i2);
        this.out.write(i2);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) throws IOException {
        this.hasher.putBytes(bArr, i2, i3);
        this.out.write(bArr, i2, i3);
    }

    public HashCode hash() {
        return this.hasher.hash();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
    }
}
