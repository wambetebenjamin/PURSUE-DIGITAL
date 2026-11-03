package com.bytedance.geckox.policy.request;

import com.bytedance.geckox.b;
/* compiled from: ExceptionRequestPolicy.java */
/* loaded from: classes2.dex */
public class a implements b {

    /* renamed from: a  reason: collision with root package name */
    private static int f9273a;

    /* renamed from: b  reason: collision with root package name */
    private static long f9274b;

    /* renamed from: c  reason: collision with root package name */
    private static long f9275c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f9276d = false;

    /* renamed from: e  reason: collision with root package name */
    private com.bytedance.geckox.statistic.model.b f9277e;

    /* renamed from: f  reason: collision with root package name */
    private int f9278f;

    @Override // com.bytedance.geckox.policy.request.b
    public boolean b() {
        return false;
    }

    public a(int i2, com.bytedance.geckox.statistic.model.b bVar) {
        this.f9278f = i2;
        this.f9277e = bVar;
    }

    @Override // com.bytedance.geckox.policy.request.b
    public synchronized void a() {
        if (System.currentTimeMillis() - f9274b > 60000) {
            f9273a = 0;
        }
        if (f9273a == 3 && this.f9278f == 1) {
            this.f9276d = true;
            f9275c = System.currentTimeMillis();
        } else {
            this.f9276d = false;
        }
        if (this.f9276d && System.currentTimeMillis() - f9275c <= 60000) {
            com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "gecko update request exception hit");
            this.f9277e.f9354i = 1;
            this.f9277e.f9355j = b.InterfaceC0127b.f8891b;
            throw new com.bytedance.pipeline.exception.e(b.InterfaceC0127b.f8891b, "gecko update request is intercepted", new Throwable("gecko update request failed more than 3 times"));
        }
        this.f9276d = false;
    }

    @Override // com.bytedance.geckox.policy.request.b
    public synchronized void c() {
        f9273a = 0;
    }

    @Override // com.bytedance.geckox.policy.request.b
    public synchronized void d() {
        f9273a++;
        f9274b = System.currentTimeMillis();
    }
}
