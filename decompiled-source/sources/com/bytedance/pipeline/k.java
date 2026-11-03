package com.bytedance.pipeline;

import com.bytedance.pipeline.j;
import com.bytedance.pipeline.n;
import java.util.List;
/* compiled from: RetryBranchInterceptor.java */
/* loaded from: classes2.dex */
public abstract class k<IN, OUT> extends n<IN, OUT> {

    /* renamed from: a  reason: collision with root package name */
    private String f10032a;

    protected abstract String a(b<OUT> bVar, IN in);

    protected abstract String a(b<OUT> bVar, IN in, Throwable th, String str);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bytedance.pipeline.d
    public final Object a_(b<OUT> bVar, IN in) {
        this.f10032a = a(new o(bVar), in);
        n.a aVar = b().get(this.f10032a);
        while (aVar != null) {
            List<i> list = aVar.f10036a;
            try {
                Object a2 = c.a(list, bVar, this).a((b) in);
                return !a(list) ? a2 : bVar.a((b<OUT>) a2);
            } catch (j.a e2) {
                this.f10032a = a(new o(bVar), in, e2.getCause(), this.f10032a);
                aVar = b().get(this.f10032a);
            } catch (Throwable th) {
                this.f10032a = a(new o(bVar), in, th, this.f10032a);
                aVar = b().get(this.f10032a);
            }
        }
        throw new IllegalArgumentException("can not found branch，branch name is：" + this.f10032a);
    }

    public String a() {
        return this.f10032a;
    }
}
