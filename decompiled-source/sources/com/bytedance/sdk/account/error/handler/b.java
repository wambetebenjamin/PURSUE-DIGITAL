package com.bytedance.sdk.account.error.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* compiled from: ErrorHandlerCenter.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static Map<Integer, a> f12347a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private static List<a> f12348b = new ArrayList();

    public static void a(int i2, a aVar) {
        f12347a.put(Integer.valueOf(i2), aVar);
    }

    public static void a(a... aVarArr) {
        Collections.addAll(f12348b, aVarArr);
    }

    public static a a(int... iArr) {
        for (int i2 : iArr) {
            a aVar = f12347a.get(Integer.valueOf(i2));
            if (aVar != null) {
                return aVar;
            }
        }
        return null;
    }

    public static Map<Integer, a> a() {
        return f12347a;
    }

    public static List<a> b() {
        return f12348b;
    }
}
