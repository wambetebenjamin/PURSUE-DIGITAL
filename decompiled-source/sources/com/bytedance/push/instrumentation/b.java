package com.bytedance.push.instrumentation;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
/* compiled from: PushInstrumentationManager.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static volatile b f11389a;

    /* renamed from: b  reason: collision with root package name */
    private Map<String, a> f11390b = new HashMap();

    public static b a() {
        if (f11389a == null) {
            synchronized (b.class) {
                if (f11389a == null) {
                    f11389a = new b();
                }
            }
        }
        return f11389a;
    }

    private b() {
    }

    public void a(String str, a aVar) {
        if (TextUtils.isEmpty(str) || aVar == null) {
            return;
        }
        this.f11390b.put(str, aVar);
    }

    public a a(String str) {
        return this.f11390b.get(str);
    }
}
