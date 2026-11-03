package com.bytedance.pipeline;

import com.bytedance.geckox.b;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RealInterceptorChain.java */
/* loaded from: classes2.dex */
public final class j implements b, Serializable {

    /* renamed from: a  reason: collision with root package name */
    protected f f10027a;

    /* renamed from: b  reason: collision with root package name */
    private int f10028b;

    /* renamed from: c  reason: collision with root package name */
    private List<i> f10029c;

    /* renamed from: d  reason: collision with root package name */
    private d f10030d;

    /* renamed from: e  reason: collision with root package name */
    private Map<String, Object> f10031e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(List<i> list, int i2, f fVar, d dVar) {
        HashMap hashMap = new HashMap();
        this.f10031e = hashMap;
        this.f10029c = list;
        this.f10028b = i2;
        this.f10027a = fVar;
        this.f10030d = dVar;
        hashMap.put(b.g.f8908a, 1);
        this.f10031e.put(b.g.f8909b, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bytedance.pipeline.b
    public Object a(Object obj) {
        d dVar = this.f10030d;
        if (dVar != null) {
            dVar.f10010f = obj;
            this.f10030d.f();
        }
        if (this.f10028b >= this.f10029c.size()) {
            return obj;
        }
        i iVar = this.f10029c.get(this.f10028b);
        Class<? extends d> a2 = iVar.a();
        d dVar2 = (d) this.f10027a.a(a2);
        if (dVar2 == null) {
            throw new IllegalArgumentException("interceptor == null , index = " + obj + " , class: " + a2);
        }
        com.bytedance.pipeline.listener.a b2 = iVar.b();
        j jVar = new j(this.f10029c, this.f10028b + 1, this.f10027a, dVar2);
        jVar.a(this.f10031e);
        dVar2.a(jVar, this.f10030d, obj, b2, iVar.c());
        dVar2.d();
        try {
            Object a_ = dVar2.a_(jVar, obj);
            dVar2.e();
            return a_;
        } catch (a e2) {
            dVar2.d(e2.getCause());
            throw e2;
        } catch (Throwable th) {
            dVar2.c(th);
            throw new a(th);
        }
    }

    @Override // com.bytedance.pipeline.b
    public Object a() {
        if (this.f10030d != null) {
            try {
                i iVar = this.f10029c.get(this.f10028b - 1);
                com.bytedance.pipeline.listener.a b2 = iVar.b();
                j jVar = new j(this.f10029c, this.f10028b, this.f10027a, this.f10030d);
                jVar.a(this.f10031e);
                IN in = this.f10030d.f10009e;
                d dVar = this.f10030d;
                dVar.a(jVar, dVar.f10008d, this.f10030d.f10010f, b2, iVar.c());
                d dVar2 = this.f10030d;
                Object a_ = dVar2.a_(dVar2.g(), in);
                this.f10030d.e();
                return a_;
            } catch (a e2) {
                this.f10030d.d(e2.getCause());
                throw e2;
            } catch (Throwable th) {
                this.f10030d.c(th);
                throw new a(th);
            }
        }
        return null;
    }

    @Override // com.bytedance.pipeline.b
    public Object b() {
        this.f10028b = 0;
        this.f10030d = null;
        return a((Object) null);
    }

    @Override // com.bytedance.pipeline.b
    public void a(String str, Object obj) {
        this.f10031e.put(str, obj);
    }

    @Override // com.bytedance.pipeline.b
    public Object a(String str) {
        return this.f10031e.get(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Map<String, Object> map) {
        this.f10031e = map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map<String, Object> c() {
        return this.f10031e;
    }

    @Override // com.bytedance.pipeline.b
    public Object a(Class cls) {
        d d2 = d(cls);
        if (d2 == null) {
            throw new IllegalArgumentException("can not find pre Interceptor , class:" + cls);
        }
        return d2.f10009e;
    }

    @Override // com.bytedance.pipeline.b
    public Object b(Class cls) {
        d d2 = d(cls);
        if (d2 == null) {
            throw new IllegalArgumentException("can not find pre Interceptor , class:" + cls);
        }
        return d2.f10010f;
    }

    @Override // com.bytedance.pipeline.b
    public Object c(Class cls) {
        d d2 = d(cls);
        if (d2 != null) {
            return d2;
        }
        throw new IllegalArgumentException("can not find pre Interceptor , class:" + cls);
    }

    private d d(Class cls) {
        d dVar = this.f10030d;
        while (dVar != null && dVar.getClass() != cls) {
            dVar = dVar.f10008d;
        }
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RealInterceptorChain.java */
    /* loaded from: classes2.dex */
    public static final class a extends Exception {
        a(Throwable th) {
            super(th);
        }
    }
}
