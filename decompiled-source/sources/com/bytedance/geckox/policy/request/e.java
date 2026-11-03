package com.bytedance.geckox.policy.request;

import java.util.concurrent.ConcurrentHashMap;
/* compiled from: ThrottleRequestPolicy.java */
/* loaded from: classes2.dex */
public class e implements b {

    /* renamed from: a  reason: collision with root package name */
    private static ConcurrentHashMap<String, Long> f9291a = new ConcurrentHashMap<>();

    /* renamed from: b  reason: collision with root package name */
    private boolean f9292b;

    /* renamed from: c  reason: collision with root package name */
    private String f9293c;

    /* renamed from: d  reason: collision with root package name */
    private com.bytedance.geckox.statistic.model.b f9294d;

    @Override // com.bytedance.geckox.policy.request.b
    public boolean b() {
        return false;
    }

    @Override // com.bytedance.geckox.policy.request.b
    public void d() {
    }

    public e(boolean z, String str, com.bytedance.geckox.statistic.model.b bVar) {
        this.f9292b = z;
        this.f9293c = str;
        this.f9294d = bVar;
    }

    @Override // com.bytedance.geckox.policy.request.b
    public void a() {
        if (this.f9292b && f9291a.containsKey(this.f9293c)) {
            if (System.currentTimeMillis() - f9291a.get(this.f9293c).longValue() <= 600000) {
                com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, this.f9293c + ":gecko update request control-throttle hit", null);
                this.f9294d.f9354i = 1;
                this.f9294d.f9355j = 600;
                throw new com.bytedance.pipeline.exception.e(600, "repeat gecko update request", new Throwable("Only one request with the same parameters is allowed in 600s"));
            }
            f9291a.remove(this.f9293c);
        }
    }

    @Override // com.bytedance.geckox.policy.request.b
    public void c() {
        f9291a.put(this.f9293c, Long.valueOf(System.currentTimeMillis()));
    }
}
