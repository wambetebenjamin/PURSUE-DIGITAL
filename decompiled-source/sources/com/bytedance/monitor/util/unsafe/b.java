package com.bytedance.monitor.util.unsafe;

import java.lang.reflect.Field;
/* compiled from: UnsafeProxy.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static a f9718a;

    /* renamed from: b  reason: collision with root package name */
    private static Class f9719b;

    /* renamed from: c  reason: collision with root package name */
    private static Object f9720c;

    static {
        try {
            f9718a = a.getInstance();
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            f9719b = cls;
            f9720c = f9718a.getUnsafe(cls);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    public static long objectFieldOffset(Field field) {
        Object invokeMethod = f9718a.invokeMethod(f9718a.getMethod(f9719b, "objectFieldOffset", Field.class), f9720c, field);
        if (invokeMethod == null) {
            return 0L;
        }
        return ((Long) invokeMethod).longValue();
    }

    public static long putObject(Object obj, long j2, Object obj2) {
        Object invokeMethod = f9718a.invokeMethod(f9718a.getMethod(f9719b, "putObject", Object.class, Long.TYPE, Object.class), f9720c, obj, Long.valueOf(j2), obj2);
        if (invokeMethod == null) {
            return 0L;
        }
        return ((Long) invokeMethod).longValue();
    }
}
