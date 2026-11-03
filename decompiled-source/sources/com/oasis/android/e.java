package com.oasis.android;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.util.List;
/* loaded from: classes9.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static String f17459a;

    public static String a() {
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

    public static String a(Context context) {
        if (TextUtils.isEmpty(f17459a)) {
            String b2 = b();
            f17459a = b2;
            if (TextUtils.isEmpty(b2)) {
                String a2 = a();
                f17459a = a2;
                if (TextUtils.isEmpty(a2)) {
                    String b3 = b(context);
                    f17459a = b3;
                    return b3;
                }
                return f17459a;
            }
            return f17459a;
        }
        return f17459a;
    }

    public static String b() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        return null;
    }

    public static String b(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (context == null) {
            return null;
        }
        int myPid = Process.myPid();
        android.app.ActivityManager activityManager = (android.app.ActivityManager) context.getSystemService("activity");
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return null;
    }
}
