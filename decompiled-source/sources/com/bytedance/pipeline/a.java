package com.bytedance.pipeline;

import com.bytedance.pipeline.n;
import java.util.List;
/* compiled from: BranchInterceptor.java */
/* loaded from: classes2.dex */
public abstract class a<IN, OUT> extends n<IN, OUT> {
    protected abstract String a(b<OUT> bVar, IN in);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bytedance.pipeline.d
    public final Object a_(b<OUT> bVar, IN in) {
        String a2 = a(new o(bVar), in);
        n.a aVar = b().get(a2);
        if (aVar == null) {
            throw new IllegalArgumentException("can not found branch, branch name is：" + a2);
        }
        List<i> list = aVar.f10036a;
        Object a3 = c.a(list, bVar, this).a((b) in);
        return !a(list) ? a3 : bVar.a((b<OUT>) a3);
    }
}
