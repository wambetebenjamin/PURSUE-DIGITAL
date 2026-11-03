package com.bytedance.compression.zstd;

import java.nio.ByteBuffer;
/* compiled from: NoPool.java */
/* loaded from: classes2.dex */
public class e implements b {

    /* renamed from: a  reason: collision with root package name */
    public static final b f6035a = new e();

    @Override // com.bytedance.compression.zstd.b
    public void a(ByteBuffer byteBuffer) {
    }

    private e() {
    }

    @Override // com.bytedance.compression.zstd.b
    public ByteBuffer a(int i2) {
        return ByteBuffer.allocate(i2);
    }
}
