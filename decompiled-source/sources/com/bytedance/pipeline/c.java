package com.bytedance.pipeline;

import com.bytedance.pipeline.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* compiled from: Factory.java */
/* loaded from: classes2.dex */
public class c {
    static <IN> b<IN> a(List<i> list, f fVar, d dVar) {
        if (list == null) {
            throw new IllegalArgumentException("interceptors == null !");
        }
        if (fVar == null) {
            fVar = new f.a();
        }
        return new j(Collections.unmodifiableList(new ArrayList(list)), 0, fVar, dVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <IN> b<IN> a(List<i> list, b bVar, d dVar) {
        if (list == null) {
            throw new IllegalArgumentException("interceptors == null !");
        }
        j jVar = (j) bVar;
        f fVar = jVar.f10027a;
        if (fVar == null) {
            fVar = new f.a();
        }
        j jVar2 = new j(Collections.unmodifiableList(new ArrayList(list)), 0, fVar, dVar);
        jVar2.a(jVar.c());
        return jVar2;
    }

    public static <IN> b<IN> a(List<i> list, f fVar) {
        return a(list, fVar, (d) null);
    }
}
