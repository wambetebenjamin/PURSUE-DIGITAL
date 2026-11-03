package com.bytedance.geckox.statistic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* compiled from: StatisticDataManager.java */
/* loaded from: classes2.dex */
class b {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<Long, b> f9326a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private final Map<String, com.bytedance.geckox.statistic.model.a> f9327b = new HashMap();

    b() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b a(long j2) {
        b bVar;
        Map<Long, b> map = f9326a;
        synchronized (map) {
            bVar = map.get(Long.valueOf(j2));
            if (bVar == null) {
                bVar = new b();
                map.put(Long.valueOf(j2), bVar);
            }
        }
        return bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.bytedance.geckox.statistic.model.a a(String str) {
        com.bytedance.geckox.statistic.model.a aVar;
        synchronized (this.f9327b) {
            aVar = this.f9327b.get(str);
            if (aVar == null) {
                aVar = new com.bytedance.geckox.statistic.model.a();
                this.f9327b.put(str, aVar);
            }
        }
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<com.bytedance.geckox.statistic.model.a> a() {
        ArrayList arrayList;
        synchronized (this.f9327b) {
            arrayList = new ArrayList(this.f9327b.values());
        }
        return arrayList;
    }
}
