package com.bytedance.common.jato.util;

import android.os.Build;
import java.io.File;
/* compiled from: DeviceInfoUtils.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static int f5047a;

    /* renamed from: b  reason: collision with root package name */
    private static int f5048b;

    /* renamed from: c  reason: collision with root package name */
    private static final String f5049c;

    /* renamed from: d  reason: collision with root package name */
    private static volatile Boolean f5050d;

    /* renamed from: e  reason: collision with root package name */
    private static int f5051e;

    private static native int nGetArtApexVersion();

    public static native long nGetMemSize();

    static {
        com.bytedance.common.jato.d.loadLibrary();
        f5047a = -1;
        f5048b = -1;
        f5051e = -1;
        f5049c = System.getProperty("java.vm.version", "");
    }

    public static synchronized boolean isEmulatorTrans2Arm() {
        synchronized (b.class) {
            int i2 = f5047a;
            if (i2 != -1) {
                return i2 > 0;
            }
            if (new File("/system/lib/arm/nb/libc.so").exists()) {
                f5047a = 1;
            } else {
                f5047a = 0;
            }
            return f5047a > 0;
        }
    }

    public static synchronized boolean isEmulator() {
        synchronized (b.class) {
            int i2 = f5048b;
            if (i2 != -1) {
                return i2 > 0;
            }
            if (getSystemProperty("ro.kernel.qemu").equals("1")) {
                f5048b = 1;
            } else {
                f5048b = 0;
            }
            return f5048b > 0;
        }
    }

    public static synchronized String getSystemProperty(String str) {
        String str2;
        synchronized (b.class) {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str2 = (String) cls.getDeclaredMethod("get", String.class).invoke(cls, str);
        }
        return str2;
    }

    public static int a() {
        try {
            return Integer.parseInt(getSystemProperty("ro.build.version.preview_sdk"));
        } catch (Exception unused) {
            return 0;
        }
    }

    public static boolean b() {
        try {
            String str = f5049c;
            if (str == null || str.length() < 1) {
                return true;
            }
            return Integer.parseInt(String.valueOf(str.charAt(0))) >= 2;
        } catch (Exception unused) {
            return true;
        }
    }

    public static int c() {
        int i2 = f5051e;
        if (i2 > 0) {
            return i2;
        }
        if (Build.VERSION.SDK_INT < 31) {
            f5051e = Build.VERSION.SDK_INT;
        } else {
            f5051e = nGetArtApexVersion();
        }
        return f5051e;
    }

    public static boolean d() {
        if (f5050d != null) {
            return f5050d.booleanValue();
        }
        int i2 = 11;
        String[] strArr = {"/data/local/su", "/data/local/bin/su", "/data/local/xbin/su", "/system/xbin/su", "/system/bin/su", "/system/bin/.ext/su", "/system/bin/failsafe/su", "/system/sd/xbin/su", "/system/usr/we-need-root/su", "/sbin/su", "/su/bin/su"};
        for (int i3 = 0; i3 < i2; i3++) {
            if (new File(strArr[i3]).exists()) {
                Boolean bool = true;
                f5050d = bool;
                return bool.booleanValue();
            }
            continue;
        }
        Boolean bool2 = false;
        f5050d = bool2;
        return bool2.booleanValue();
    }
}
