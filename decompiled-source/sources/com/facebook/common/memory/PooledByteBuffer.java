package com.facebook.common.memory;

import java.io.Closeable;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public interface PooledByteBuffer extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    @Nullable
    ByteBuffer getByteBuffer();

    long getNativePtr();

    boolean isClosed();

    byte read(int i2);

    int read(int i2, byte[] bArr, int i3, int i4);

    int size();

    /* loaded from: classes8.dex */
    public static class ClosedException extends RuntimeException {
        public ClosedException() {
            super("Invalid bytebuf. Already closed");
        }
    }
}
