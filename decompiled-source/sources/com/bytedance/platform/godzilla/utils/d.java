package com.bytedance.platform.godzilla.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
/* compiled from: HackHelper.java */
/* loaded from: classes2.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private static com.bytedance.platform.godzilla.common.j f11095a;

    private d() {
    }

    public static void init(com.bytedance.platform.godzilla.common.j jVar) {
        f11095a = jVar;
    }

    public static com.bytedance.platform.godzilla.common.j getInstance() {
        return f11095a;
    }

    public static Field getField(Class<?> cls, String str) {
        a();
        return f11095a.getField(cls, str);
    }

    public static Method getMethod(Class<?> cls, String str, Class... clsArr) {
        a();
        return f11095a.getMethod(cls, str, clsArr);
    }

    private static void a() {
        Objects.requireNonNull(f11095a, "please call Godzilla.init method pass in a IReflectHackHelper instance");
    }
}
