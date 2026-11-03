package com.bytedance.geckox.policy.request;
/* compiled from: RequestPolicyManager.java */
/* loaded from: classes2.dex */
public class c implements b {

    /* renamed from: a  reason: collision with root package name */
    e f9279a;

    /* renamed from: b  reason: collision with root package name */
    a f9280b;

    /* renamed from: c  reason: collision with root package name */
    d f9281c;

    @Override // com.bytedance.geckox.policy.request.b
    public boolean b() {
        return false;
    }

    public c a(e eVar) {
        this.f9279a = eVar;
        return this;
    }

    public c a(a aVar) {
        this.f9280b = aVar;
        return this;
    }

    public c a(d dVar) {
        this.f9281c = dVar;
        return this;
    }

    public e e() {
        return this.f9279a;
    }

    public a f() {
        return this.f9280b;
    }

    public d g() {
        return this.f9281c;
    }

    @Override // com.bytedance.geckox.policy.request.b
    public void a() {
        e eVar = this.f9279a;
        if (eVar != null) {
            eVar.a();
        }
        d dVar = this.f9281c;
        if (dVar != null) {
            dVar.a();
        }
        a aVar = this.f9280b;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // com.bytedance.geckox.policy.request.b
    public void c() {
        e eVar = this.f9279a;
        if (eVar != null) {
            eVar.c();
        }
        d dVar = this.f9281c;
        if (dVar != null) {
            dVar.c();
        }
        a aVar = this.f9280b;
        if (aVar != null) {
            aVar.c();
        }
    }

    @Override // com.bytedance.geckox.policy.request.b
    public void d() {
        e eVar = this.f9279a;
        if (eVar != null) {
            eVar.d();
        }
        a aVar = this.f9280b;
        if (aVar != null) {
            aVar.d();
        }
        d dVar = this.f9281c;
        if (dVar != null) {
            dVar.d();
        }
    }
}
