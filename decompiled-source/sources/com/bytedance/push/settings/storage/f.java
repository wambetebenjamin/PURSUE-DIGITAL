package com.bytedance.push.settings.storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: ProcessorPool.java */
/* loaded from: classes2.dex */
final class f {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, e> f11902a = new ConcurrentHashMap();

    f() {
    }

    static {
        register(new a());
    }

    public static e get(String str) {
        return f11902a.get(str);
    }

    public static void register(e eVar) {
        f11902a.put(eVar.getMethodName(), eVar);
    }
}
