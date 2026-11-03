package com.bytedance.frameworks.baselib.network.dispatcher;

import com.bytedance.common.utility.o;
import com.bytedance.frameworks.baselib.network.dispatcher.f;
/* compiled from: ApiThread.java */
/* loaded from: classes2.dex */
public abstract class c extends e {

    /* renamed from: a  reason: collision with root package name */
    protected static i f7520a;

    /* renamed from: b  reason: collision with root package name */
    protected final f.a f7521b;

    /* renamed from: g  reason: collision with root package name */
    private final String f7522g;

    public boolean b() {
        return false;
    }

    @Override // java.lang.Runnable
    public void run() {
    }

    public static void a(i iVar) {
        f7520a = iVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public c(String str, f.a aVar) {
        this.f7521b = aVar;
        this.f7522g = o.isEmpty(str) ? getClass().getSimpleName() : str;
    }

    public String a() {
        return this.f7522g;
    }

    public final void e() {
        if (this.f7523c.compareAndSet(false, true)) {
            if (f7520a == null) {
                f7520a = i.c();
            }
            if (b()) {
                f7520a.a(this);
            } else {
                f7520a.b(this);
            }
        }
    }

    @Override // com.bytedance.frameworks.baselib.network.dispatcher.f
    public f.a c() {
        return this.f7521b;
    }

    @Override // com.bytedance.frameworks.baselib.network.dispatcher.f
    public int d() {
        return this.f7526f;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(f fVar) {
        f.a c2 = c();
        f.a c3 = fVar.c();
        if (c2 == null) {
            c2 = f.a.NORMAL;
        }
        if (c3 == null) {
            c3 = f.a.NORMAL;
        }
        return c2 == c3 ? d() - fVar.d() : c3.ordinal() - c2.ordinal();
    }
}
