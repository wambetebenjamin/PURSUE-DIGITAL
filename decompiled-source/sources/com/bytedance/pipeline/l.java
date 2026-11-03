package com.bytedance.pipeline;

import com.bytedance.pipeline.j;
/* compiled from: RetryInterceptor.java */
/* loaded from: classes2.dex */
public abstract class l<IN, OUT> extends d<IN, OUT> {

    /* renamed from: a  reason: collision with root package name */
    private IN f10033a;

    protected abstract boolean a(Throwable th);

    @Override // com.bytedance.pipeline.d
    public final Object a_(b<OUT> bVar, IN in) {
        this.f10033a = in;
        try {
            return bVar.a((b<OUT>) b());
        } catch (j.a e2) {
            return a(bVar, in, e2.getCause());
        } catch (Throwable th) {
            return a(bVar, in, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final IN h() {
        return this.f10033a;
    }

    private Object a(b<OUT> bVar, IN in, Throwable th) {
        while (a(th)) {
            try {
                return bVar.a((b<OUT>) b());
            } catch (j.a e2) {
                th = e2.getCause();
            } catch (Throwable th2) {
                th = th2;
            }
        }
        throw th;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [IN, OUT] */
    protected OUT b() {
        return this.f10033a;
    }
}
