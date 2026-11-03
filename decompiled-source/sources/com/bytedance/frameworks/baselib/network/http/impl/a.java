package com.bytedance.frameworks.baselib.network.http.impl;

import android.text.TextUtils;
import com.bytedance.common.utility.o;
import com.bytedance.librarian.c;
import java.net.CookieManager;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/* compiled from: CookieManagerWrap.java */
/* loaded from: classes2.dex */
public class a extends CookieManager {

    /* renamed from: c  reason: collision with root package name */
    private static final String f7846c = "Set-cookie";

    /* renamed from: d  reason: collision with root package name */
    private static final String f7847d = "Set-cookie2";

    /* renamed from: a  reason: collision with root package name */
    private c f7848a;

    /* renamed from: b  reason: collision with root package name */
    private b f7849b;

    public a() {
        this(null, null);
    }

    public a(c cVar, b bVar) {
        this.f7848a = cVar == null ? new d() : cVar;
        this.f7849b = bVar == null ? b.f7852c : bVar;
    }

    @Override // java.net.CookieManager, java.net.CookieHandler
    public Map<String, List<String>> get(URI uri, Map<String, List<String>> map) {
        if (uri == null || map == null) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList();
        for (e eVar : this.f7848a.a(uri)) {
            if (e.a(eVar, uri) && e.b(eVar, uri) && e.c(eVar, uri)) {
                arrayList.add(eVar);
            }
        }
        return a(arrayList);
    }

    private static Map<String, List<String>> a(List<e> list) {
        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 1;
        for (e eVar : list) {
            i2 = Math.min(i2, eVar.l());
        }
        if (i2 == 1) {
            sb.append("$Version=\"1\"; ");
        }
        sb.append(list.get(0).toString());
        for (int i3 = 1; i3 < list.size(); i3++) {
            sb.append("; ").append(list.get(i3).toString());
        }
        return Collections.singletonMap("Cookie", Collections.singletonList(sb.toString()));
    }

    @Override // java.net.CookieManager, java.net.CookieHandler
    public void put(URI uri, Map<String, List<String>> map) {
        if (uri == null || map == null) {
            throw new IllegalArgumentException();
        }
        b(uri, a(map));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.net.URI r4, java.util.List<java.lang.String> r5) {
        /*
            r3 = this;
            if (r4 == 0) goto L2c
            if (r5 == 0) goto L2c
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r5 = r5.iterator()
        Ld:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L28
            java.lang.Object r1 = r5.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = com.bytedance.common.utility.o.isEmpty(r1)
            if (r2 == 0) goto L20
            goto Ld
        L20:
            java.util.List r1 = com.bytedance.frameworks.baselib.network.http.impl.e.a(r1)     // Catch: java.lang.IllegalArgumentException -> Ld
            r0.addAll(r1)     // Catch: java.lang.IllegalArgumentException -> Ld
            goto Ld
        L28:
            r3.b(r4, r0)
            return
        L2c:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.baselib.network.http.impl.a.a(java.net.URI, java.util.List):void");
    }

    private void b(URI uri, List<e> list) {
        if (com.bytedance.common.utility.i.isEmpty(list)) {
            return;
        }
        for (e eVar : list) {
            if (TextUtils.isEmpty(eVar.d())) {
                eVar.d(uri.getHost());
            }
            if (TextUtils.isEmpty(eVar.g())) {
                eVar.e(a(uri.getPath()));
            }
            if ("".equals(eVar.h())) {
                eVar.f(Integer.toString(h.a(uri.getScheme(), uri.getPort())));
            } else if (eVar.h() != null && !e.c(eVar, uri)) {
            }
            if (this.f7849b.a(uri, eVar)) {
                this.f7848a.a(uri, eVar);
            }
        }
    }

    public void a(URI uri, String str) {
        if (uri == null || o.isEmpty(str)) {
            return;
        }
        List<e> list = null;
        try {
            list = e.a(str);
        } catch (IllegalArgumentException unused) {
        }
        b(uri, list);
    }

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? c.a.SEPARATOR : str.substring(0, str.lastIndexOf(47) + 1);
    }

    private static List<e> a(Map<String, List<String>> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key != null && (key.equalsIgnoreCase(f7846c) || key.equalsIgnoreCase(f7847d))) {
                for (String str : entry.getValue()) {
                    try {
                        for (e eVar : e.a(str)) {
                            arrayList.add(eVar);
                        }
                    } catch (IllegalArgumentException unused) {
                    }
                }
            }
        }
        return arrayList;
    }

    public void a(b bVar) {
        if (bVar != null) {
            this.f7849b = bVar;
        }
    }
}
