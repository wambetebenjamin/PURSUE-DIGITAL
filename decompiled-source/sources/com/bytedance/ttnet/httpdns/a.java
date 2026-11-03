package com.bytedance.ttnet.httpdns;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: TTDnsOuterService.java */
/* loaded from: classes8.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static a f15315a;

    /* renamed from: b  reason: collision with root package name */
    private Map<String, b> f15316b = new ConcurrentHashMap();

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (f15315a == null) {
                f15315a = new a();
            }
            aVar = f15315a;
        }
        return aVar;
    }

    public c a(String str, int i2, Map<String, String> map) {
        b bVar = new b(str, i2, map);
        this.f15316b.put(bVar.c(), bVar);
        bVar.g();
        bVar.d();
        this.f15316b.remove(bVar.c());
        return bVar.f();
    }

    public void a(String str, String str2, int i2, int i3, int i4, List<String> list, String str3) {
        b bVar = this.f15316b.get(str);
        if (bVar != null) {
            bVar.a(new c(i2, i3, i4, list, str3));
            bVar.e();
            this.f15316b.remove(str);
        }
    }
}
