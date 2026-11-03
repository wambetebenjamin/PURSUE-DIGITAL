package com.bytedance.apm.trace.mapping;

import java.util.HashMap;
/* compiled from: ApmPageLoadMappingTool.java */
/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static HashMap<String, Integer> f1601a = new HashMap<>();

    static {
        a();
    }

    public static void put(String str, int i2) {
        f1601a.put(str, Integer.valueOf(i2));
    }

    private static void a() {
        put("xx", 12334);
    }

    public static HashMap<String, Integer> getsPageViewIdMap() {
        return f1601a;
    }

    public static Integer queryViewId(String str) {
        return f1601a.get(str);
    }
}
