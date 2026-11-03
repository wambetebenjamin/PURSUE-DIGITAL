package com.google.android.play.core.assetpacks.internal;

import java.io.IOException;
import java.io.InputStream;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class ao extends an {

    /* renamed from: a  reason: collision with root package name */
    private final an f16812a;

    /* renamed from: b  reason: collision with root package name */
    private final long f16813b;

    /* renamed from: c  reason: collision with root package name */
    private final long f16814c;

    public ao(an anVar, long j2, long j3) {
        this.f16812a = anVar;
        long d2 = d(j2);
        this.f16813b = d2;
        this.f16814c = d(d2 + j3);
    }

    private final long d(long j2) {
        if (j2 < 0) {
            return 0L;
        }
        return j2 > this.f16812a.a() ? this.f16812a.a() : j2;
    }

    @Override // com.google.android.play.core.assetpacks.internal.an
    public final long a() {
        return this.f16814c - this.f16813b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.play.core.assetpacks.internal.an
    public final InputStream b(long j2, long j3) throws IOException {
        long d2 = d(this.f16813b);
        return this.f16812a.b(d2, d(j3 + d2) - d2);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
    }
}
