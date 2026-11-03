package com.bytedance.apm6.util;

import android.app.Application;
import android.content.Context;
/* compiled from: ApmBaseContext.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f2373a = false;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f2374b = false;

    /* renamed from: c  reason: collision with root package name */
    private static Application f2375c;

    public static void setDebugMode(boolean z) {
        f2373a = z;
    }

    public static boolean isDebugMode() {
        return f2373a && !f2374b;
    }

    public static void setSystraceMode(boolean z) {
        f2374b = z;
    }

    public static boolean isSystraceMode() {
        return f2374b;
    }

    public static Application getContext() {
        return f2375c;
    }

    public static void setContext(Context context) {
        if (context != null) {
            f2375c = a(context);
        }
    }

    private static Application a(Context context) {
        if (context == null) {
            return null;
        }
        if (!(context instanceof Application)) {
            context = context.getApplicationContext();
        }
        return (Application) context;
    }
}
