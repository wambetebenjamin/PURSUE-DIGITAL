package com.facebook.common.memory;

import java.io.InputStream;
/* loaded from: classes8.dex */
public interface PooledByteBufferFactory {
    PooledByteBuffer newByteBuffer(int i2);

    PooledByteBuffer newByteBuffer(InputStream inputStream);

    PooledByteBuffer newByteBuffer(InputStream inputStream, int i2);

    PooledByteBuffer newByteBuffer(InputStream inputStream, int i2, String str);

    PooledByteBuffer newByteBuffer(byte[] bArr);

    PooledByteBufferOutputStream newOutputStream();

    PooledByteBufferOutputStream newOutputStream(int i2);
}
