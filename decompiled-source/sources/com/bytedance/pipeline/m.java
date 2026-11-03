package com.bytedance.pipeline;

import com.bytedance.pipeline.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* compiled from: SerialInterceptor.java */
/* loaded from: classes2.dex */
public class m extends d<List<?>, Object> {
    @Override // com.bytedance.pipeline.d
    /* renamed from: a */
    public Object a_(b<Object> bVar, List<?> list) {
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList();
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(bVar.a((b<Object>) it.next()));
            } catch (j.a e2) {
                arrayList2.add(e2.getCause());
            } catch (Throwable th) {
                arrayList2.add(th);
            }
        }
        if (arrayList2.isEmpty()) {
            return arrayList;
        }
        throw new com.bytedance.pipeline.exception.f(arrayList2);
    }
}
