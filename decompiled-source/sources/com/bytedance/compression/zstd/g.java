package com.bytedance.compression.zstd;
/* compiled from: ZstdException.java */
/* loaded from: classes2.dex */
public class g extends RuntimeException {

    /* renamed from: a  reason: collision with root package name */
    private long f6036a;

    public g(long j2) {
        this(Zstd.getErrorCode(j2), Zstd.getErrorName(j2));
    }

    public g(long j2, String str) {
        super(str);
        this.f6036a = j2;
    }

    public long a() {
        return this.f6036a;
    }
}
