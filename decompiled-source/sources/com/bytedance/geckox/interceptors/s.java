package com.bytedance.geckox.interceptors;

import android.util.Pair;
import com.bytedance.geckox.model.UpdatePackage;
/* compiled from: TerminalInterceptor.java */
/* loaded from: classes2.dex */
public class s extends com.bytedance.pipeline.d<Pair<UpdatePackage, Long>, Pair<UpdatePackage, Long>> {
    @Override // com.bytedance.pipeline.d
    /* renamed from: a */
    public Object a_(com.bytedance.pipeline.b<Pair<UpdatePackage, Long>> bVar, Pair<UpdatePackage, Long> pair) {
        com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "update success:", pair);
        return bVar.a((com.bytedance.pipeline.b<Pair<UpdatePackage, Long>>) pair);
    }
}
