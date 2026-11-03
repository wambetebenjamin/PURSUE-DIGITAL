package com.bytedance.applog.log;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
/* compiled from: LogProcessorHolder.java */
/* loaded from: classes.dex */
public class m {

    /* renamed from: a  reason: collision with root package name */
    private static final CopyOnWriteArraySet<i> f2782a = new CopyOnWriteArraySet<>();

    /* renamed from: b  reason: collision with root package name */
    private static final Map<String, i> f2783b = new ConcurrentHashMap();

    public static void a(i iVar) {
        f2782a.add(iVar);
    }

    public static void a(String str, i iVar) {
        f2783b.put(str, iVar);
    }

    public static boolean a() {
        return f2782a.isEmpty() && f2783b.isEmpty();
    }

    public static Iterator<i> b() {
        return f2782a.iterator();
    }

    public static Iterator<i> c() {
        return f2783b.values().iterator();
    }

    public static i a(String str) {
        if (b(str)) {
            return f2783b.get(str);
        }
        return null;
    }

    public static void a(j jVar) {
        if (jVar == null || f2782a.isEmpty()) {
            return;
        }
        Iterator<i> b2 = b();
        while (b2.hasNext()) {
            b2.next().a(jVar);
        }
    }

    private static boolean b(String str) {
        return str != null && str.length() > 0;
    }
}
