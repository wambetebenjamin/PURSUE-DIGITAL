package com.bytedance.frameworks.baselib.network.http.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/* compiled from: UrlBuilder.java */
/* loaded from: classes2.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, List<String>> f8357a;

    /* renamed from: b  reason: collision with root package name */
    private String f8358b;

    public o(String str) {
        this.f8357a = new LinkedHashMap();
        this.f8358b = str;
    }

    public o() {
        this.f8357a = new LinkedHashMap();
        this.f8358b = null;
    }

    public void a(String str) {
        this.f8358b = str;
    }

    public String a() {
        return this.f8358b;
    }

    public void a(String str, int i2) {
        List<String> list = this.f8357a.get(str);
        if (list == null) {
            list = new LinkedList<>();
        }
        list.add(String.valueOf(i2));
        this.f8357a.put(str, list);
    }

    public void a(String str, long j2) {
        List<String> list = this.f8357a.get(str);
        if (list == null) {
            list = new LinkedList<>();
        }
        list.add(String.valueOf(j2));
        this.f8357a.put(str, list);
    }

    public void a(String str, double d2) {
        List<String> list = this.f8357a.get(str);
        if (list == null) {
            list = new LinkedList<>();
        }
        list.add(String.valueOf(d2));
        this.f8357a.put(str, list);
    }

    public void a(String str, String str2) {
        List<String> list = this.f8357a.get(str);
        if (list == null) {
            list = new LinkedList<>();
        }
        list.add(String.valueOf(str2));
        this.f8357a.put(str, list);
    }

    public Map<String, String> b() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Map<String, List<String>> map = this.f8357a;
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, List<String>> entry : this.f8357a.entrySet()) {
                String key = entry.getKey();
                if (!com.bytedance.common.utility.o.isEmpty(key)) {
                    List<String> value = entry.getValue();
                    linkedHashMap.put(key, (value == null || value.size() <= 0) ? "" : value.get(0));
                }
            }
        }
        return linkedHashMap;
    }

    public Map<String, List<String>> c() {
        return this.f8357a;
    }

    public String d() {
        if (this.f8357a.isEmpty()) {
            return this.f8358b;
        }
        String a2 = p.a(this.f8357a, "UTF-8");
        String str = this.f8358b;
        if (str == null || str.length() == 0) {
            return a2;
        }
        if (this.f8358b.indexOf(63) >= 0) {
            return this.f8358b + "&" + a2;
        }
        return this.f8358b + "?" + a2;
    }

    public String toString() {
        return d();
    }
}
