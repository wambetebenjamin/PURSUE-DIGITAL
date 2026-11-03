package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class MemoryPooledByteBuffer implements PooledByteBuffer {
    CloseableReference<MemoryChunk> mBufRef;
    private final int mSize;

    public MemoryPooledByteBuffer(CloseableReference<MemoryChunk> closeableReference, int i2) {
        Preconditions.checkNotNull(closeableReference);
        Preconditions.checkArgument(i2 >= 0 && i2 <= closeableReference.get().getSize());
        this.mBufRef = closeableReference.m410clone();
        this.mSize = i2;
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    public synchronized int size() {
        ensureValid();
        return this.mSize;
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    public synchronized byte read(int i2) {
        ensureValid();
        boolean z = true;
        Preconditions.checkArgument(i2 >= 0);
        if (i2 >= this.mSize) {
            z = false;
        }
        Preconditions.checkArgument(z);
        return this.mBufRef.get().read(i2);
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    public synchronized int read(int i2, byte[] bArr, int i3, int i4) {
        ensureValid();
        Preconditions.checkArgument(i2 + i4 <= this.mSize);
        return this.mBufRef.get().read(i2, bArr, i3, i4);
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    public synchronized long getNativePtr() {
        ensureValid();
        return this.mBufRef.get().getNativePtr();
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    @Nullable
    public synchronized ByteBuffer getByteBuffer() {
        return this.mBufRef.get().getByteBuffer();
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    public synchronized boolean isClosed() {
        return !CloseableReference.isValid(this.mBufRef);
    }

    @Override // com.facebook.common.memory.PooledByteBuffer, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        CloseableReference.closeSafely(this.mBufRef);
        this.mBufRef = null;
    }

    synchronized void ensureValid() {
        if (isClosed()) {
            throw new PooledByteBuffer.ClosedException();
        }
    }
}
