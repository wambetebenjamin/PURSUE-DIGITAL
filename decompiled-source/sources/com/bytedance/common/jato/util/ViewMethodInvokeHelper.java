package com.bytedance.common.jato.util;
/* loaded from: classes2.dex */
public final class ViewMethodInvokeHelper {
    public static final String TAG = "ViewMethodInvokeHelper";

    private static native void getViewConstructMethod(Class<?> cls);

    private static native void invokeViewConstruct(Class<?> cls, Object obj, Object obj2, Object obj3);

    public static boolean invokeViewConstructByAttrs(Class<?> cls, Object obj, Object obj2, Object obj3) {
        if (com.bytedance.common.jato.d.a()) {
            invokeViewConstruct(cls, obj, obj2, obj3);
        }
        return com.bytedance.common.jato.d.a();
    }

    public static boolean warmUpViewConstructMethod(Class<?> cls) {
        if (com.bytedance.common.jato.d.a()) {
            getViewConstructMethod(cls);
        }
        return com.bytedance.common.jato.d.a();
    }
}
