package com.bytedance.common.jato.dex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;
/* compiled from: VerifierModeSwitch.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: b  reason: collision with root package name */
    private static int f4653b = 0;

    /* renamed from: c  reason: collision with root package name */
    private static volatile boolean f4654c = false;

    /* renamed from: d  reason: collision with root package name */
    public static final int f4655d = 34;

    /* renamed from: e  reason: collision with root package name */
    public static final int f4656e = 35;

    /* renamed from: f  reason: collision with root package name */
    private static boolean f4657f = false;

    /* compiled from: VerifierModeSwitch.java */
    /* renamed from: com.bytedance.common.jato.dex.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0058a {
        void a(int i2);
    }

    public static synchronized void a(Context context, boolean z) {
        synchronized (a.class) {
            a(context.getApplicationInfo(), z);
        }
    }

    public static synchronized void a(ApplicationInfo applicationInfo, boolean z) {
        synchronized (a.class) {
            if (!com.bytedance.common.jato.util.b.isEmulator() && !com.bytedance.common.jato.util.b.isEmulatorTrans2Arm()) {
                if (f4654c) {
                    return;
                }
                f4657f = z;
                f4653b = applicationInfo.targetSdkVersion;
                f4654c = true;
                return;
            }
            f4654c = false;
        }
    }

    public static int a() {
        if (!f4654c) {
            Log.e("jato", "none_class_verify fail", new IllegalStateException("not init"));
            return -1;
        }
        int i2 = Build.VERSION.SDK_INT;
        int c2 = com.bytedance.common.jato.util.b.c();
        int i3 = 0;
        boolean z = com.bytedance.common.jato.util.b.a() != 0;
        if (f4657f && c2 != i2 && c2 >= 35) {
            i2 = c2;
        }
        if (i2 >= 35 || (z && i2 == 34)) {
            i3 = DexTricksNativeHolder.verifyNone15();
        } else if (i2 >= 21) {
            i3 = DexTricksNativeHolder.verifyNone(f4653b);
        } else if (i2 == 19 && !com.bytedance.common.jato.util.b.b()) {
            i3 = DexTricksNativeHolder.dvmVerifyNone();
        } else {
            Log.e("jato", "none_class_verify fail because Android OS version not support");
        }
        if (i3 != 0) {
            Log.e("jato", "none_class_verify disable fail", new IllegalStateException("result: " + i3));
        }
        return i3;
    }

    public static void enableVerifier() {
        if (!f4654c) {
            Log.e("jato", "none_class_verify fail", new IllegalStateException("not init"));
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        int c2 = com.bytedance.common.jato.util.b.c();
        int i3 = 0;
        boolean z = com.bytedance.common.jato.util.b.a() != 0;
        if (f4657f && c2 != i2 && c2 >= 35) {
            i2 = c2;
        }
        if (i2 >= 35 || (z && i2 == 34)) {
            i3 = DexTricksNativeHolder.verifyEnable15();
        } else if (i2 >= 21) {
            i3 = DexTricksNativeHolder.verifyEnable();
        } else if (i2 == 19 && !com.bytedance.common.jato.util.b.b()) {
            i3 = DexTricksNativeHolder.dvmVerifyEnable();
        } else {
            Log.e("jato", "none_class_verify fail because Android OS version not support");
        }
        if (i3 != 0) {
            Log.e("jato", "none_class_verify enable fail", new IllegalStateException("result: " + i3));
        }
    }
}
