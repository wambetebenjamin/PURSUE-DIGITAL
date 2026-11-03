package com.bytedance.bdinstall.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: SystemPropertiesWithCache.java */
/* loaded from: classes.dex */
public class u {

    /* renamed from: a  reason: collision with root package name */
    private static final t f4084a = new t();

    /* renamed from: b  reason: collision with root package name */
    private static final Map<String, Object> f4085b = new ConcurrentHashMap();

    public static Object a(String str) {
        Map<String, Object> map = f4085b;
        Object obj = map.get(str);
        if (obj != null) {
            return obj;
        }
        String a2 = f4084a.a(str);
        if (a2 != null) {
            map.put(str, a2);
        }
        return a2;
    }
}
