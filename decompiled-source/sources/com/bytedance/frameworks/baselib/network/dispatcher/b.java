package com.bytedance.frameworks.baselib.network.dispatcher;

import com.bytedance.common.utility.o;
import com.bytedance.frameworks.baselib.network.dispatcher.f;
import com.bytedance.retrofit2.RetrofitMetrics;
import com.bytedance.retrofit2.SsRunnable;
/* compiled from: ApiTask.java */
/* loaded from: classes2.dex */
public class b extends e {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f7516a;

    /* renamed from: b  reason: collision with root package name */
    private final Runnable f7517b;

    /* renamed from: g  reason: collision with root package name */
    private final f.a f7518g;

    /* renamed from: h  reason: collision with root package name */
    private final String f7519h;

    public b(String str, f.a aVar, int i2, Runnable runnable, boolean z) {
        this.f7518g = aVar;
        str = o.isEmpty(str) ? getClass().getSimpleName() : str;
        this.f7525e = i2;
        this.f7519h = str;
        this.f7517b = runnable;
        this.f7516a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f7517b == null || i()) {
            return;
        }
        this.f7517b.run();
    }

    public String a() {
        return this.f7519h;
    }

    public boolean b() {
        return this.f7516a;
    }

    @Override // com.bytedance.frameworks.baselib.network.dispatcher.f
    public f.a c() {
        return this.f7518g;
    }

    @Override // com.bytedance.frameworks.baselib.network.dispatcher.f
    public int d() {
        return this.f7526f;
    }

    @Override // com.bytedance.frameworks.baselib.network.dispatcher.e
    /* renamed from: a */
    public b b(int i2) {
        this.f7526f = i2;
        return this;
    }

    public void c(int i2) {
        RetrofitMetrics retrofitMetrics;
        Runnable runnable = this.f7517b;
        if (!(runnable instanceof SsRunnable) || (retrofitMetrics = ((SsRunnable) runnable).getRetrofitMetrics()) == null) {
            return;
        }
        retrofitMetrics.setBlockingQueueSize(i2);
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
