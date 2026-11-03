package com.bytedance.pipeline;

import java.util.concurrent.atomic.AtomicLong;
/* compiled from: Interceptor.java */
/* loaded from: classes2.dex */
public abstract class d<IN, OUT> {

    /* renamed from: a  reason: collision with root package name */
    private static AtomicLong f10005a = new AtomicLong();

    /* renamed from: b  reason: collision with root package name */
    private com.bytedance.pipeline.listener.a f10006b;

    /* renamed from: c  reason: collision with root package name */
    private b f10007c;

    /* renamed from: d  reason: collision with root package name */
    d f10008d;

    /* renamed from: e  reason: collision with root package name */
    IN f10009e;

    /* renamed from: f  reason: collision with root package name */
    OUT f10010f;

    /* renamed from: g  reason: collision with root package name */
    private long f10011g;

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Object... objArr) {
    }

    public abstract Object a_(b<OUT> bVar, IN in);

    protected void b(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(b bVar, d dVar, IN in, com.bytedance.pipeline.listener.a aVar, Object[] objArr) {
        this.f10007c = new o(bVar);
        this.f10008d = dVar;
        this.f10009e = in;
        this.f10006b = aVar;
        a();
        a(objArr);
    }

    private void a() {
        d dVar = this.f10008d;
        if (dVar != null) {
            this.f10011g = dVar.f10011g;
            return;
        }
        long andIncrement = f10005a.getAndIncrement();
        this.f10011g = andIncrement;
        if (andIncrement < 0) {
            throw new RuntimeException("Pipeline ID use up!");
        }
    }

    public long c() {
        return this.f10011g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d() {
        com.bytedance.pipeline.listener.a aVar = this.f10006b;
        if (aVar == null) {
            return;
        }
        aVar.d(this.f10007c, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e() {
        com.bytedance.pipeline.listener.a aVar = this.f10006b;
        if (aVar == null) {
            return;
        }
        aVar.f(this.f10007c, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void f() {
        com.bytedance.pipeline.listener.a aVar = this.f10006b;
        if (aVar == null) {
            return;
        }
        aVar.e(this.f10007c, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(Throwable th) {
        com.bytedance.pipeline.listener.a aVar = this.f10006b;
        if (aVar == null) {
            return;
        }
        aVar.c(this.f10007c, this, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(Throwable th) {
        com.bytedance.pipeline.listener.a aVar = this.f10006b;
        if (aVar == null) {
            return;
        }
        aVar.e(this.f10007c, this, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void e(Throwable th) {
        com.bytedance.pipeline.listener.a aVar = this.f10006b;
        if (aVar == null) {
            return;
        }
        aVar.d(this.f10007c, this, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public b g() {
        return this.f10007c;
    }
}
