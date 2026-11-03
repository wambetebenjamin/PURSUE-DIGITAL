package com.bytedance.push.settings.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.List;
/* compiled from: SettingsUtils.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static String f11911a;

    /* renamed from: b  reason: collision with root package name */
    private static Boolean f11912b;

    public static boolean a(Context context) {
        Boolean bool = f11912b;
        if (bool != null) {
            return bool.booleanValue();
        }
        String curProcessName = getCurProcessName(context);
        boolean z = false;
        if (curProcessName == null || !curProcessName.contains(CertificateUtil.DELIMITER)) {
            if (curProcessName != null && curProcessName.equals(context.getPackageName())) {
                z = true;
            }
            Boolean valueOf = Boolean.valueOf(z);
            f11912b = valueOf;
            return valueOf.booleanValue();
        }
        return false;
    }

    public static String getCurProcessName(Context context) {
        if (!TextUtils.isEmpty(f11911a)) {
            return f11911a;
        }
        String b2 = b();
        f11911a = b2;
        if (!TextUtils.isEmpty(b2)) {
            return f11911a;
        }
        String c2 = c();
        f11911a = c2;
        if (!TextUtils.isEmpty(c2)) {
            return f11911a;
        }
        String a2 = a();
        f11911a = a2;
        if (!TextUtils.isEmpty(a2)) {
            return f11911a;
        }
        String b3 = b(context);
        f11911a = b3;
        return b3;
    }

    private static String b() {
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                return Application.getProcessName();
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private static String c() {
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader()).getDeclaredMethod("currentProcessName", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            if (invoke instanceof String) {
                return (String) invoke;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static String b(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (context == null) {
            return null;
        }
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return null;
    }

    private static String a() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/cmdline"), "iso-8859-1"));
        } catch (Throwable unused) {
            bufferedReader = null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                int read = bufferedReader.read();
                if (read <= 0) {
                    break;
                }
                sb.append((char) read);
            }
            String sb2 = sb.toString();
            try {
                bufferedReader.close();
            } catch (Exception unused2) {
            }
            return sb2;
        } catch (Throwable unused3) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception unused4) {
                }
            }
            return null;
        }
    }
}
