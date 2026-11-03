package com.bytedance.frameworks.baselib.network.http.util;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.bytedance.common.utility.Logger;
import com.facebook.internal.security.CertificateUtil;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: ProcessUtils.java */
/* loaded from: classes2.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8351a = ":push";

    /* renamed from: b  reason: collision with root package name */
    public static final String f8352b = ":pushservice";

    /* renamed from: c  reason: collision with root package name */
    private static final String f8353c = ":miniapp";

    /* renamed from: d  reason: collision with root package name */
    private static AtomicInteger f8354d = new AtomicInteger(0);

    /* renamed from: e  reason: collision with root package name */
    private static volatile String f8355e = null;

    public static boolean a(Context context) {
        String e2 = e(context);
        if (e2 != null) {
            return e2.endsWith(":push") || e2.endsWith(":pushservice");
        }
        return false;
    }

    public static void a(int i2) {
        f8354d.set(i2);
    }

    public static int a() {
        return f8354d.get();
    }

    public static boolean b(Context context) {
        if (Logger.debug()) {
            Logger.d("ProcessUtils", "TTNet ProcessFlag: " + f8354d);
        }
        int a2 = a();
        if (a2 != 1) {
            if (a2 != 2) {
                return c(context);
            }
            return false;
        }
        return true;
    }

    public static boolean c(Context context) {
        String e2 = e(context);
        return (e2 == null || !e2.contains(CertificateUtil.DELIMITER)) && e2 != null && e2.equals(context.getPackageName());
    }

    public static boolean d(Context context) {
        String e2 = e(context);
        return e2 != null && e2.contains(f8353c);
    }

    public static String e(Context context) {
        if (!com.bytedance.common.utility.o.isEmpty(f8355e)) {
            return f8355e;
        }
        f8355e = c();
        if (!com.bytedance.common.utility.o.isEmpty(f8355e)) {
            return f8355e;
        }
        f8355e = d();
        if (!com.bytedance.common.utility.o.isEmpty(f8355e)) {
            return f8355e;
        }
        f8355e = b();
        if (!com.bytedance.common.utility.o.isEmpty(f8355e)) {
            return f8355e;
        }
        f8355e = f(context);
        return f8355e;
    }

    private static String c() {
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

    private static String d() {
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

    private static String f(Context context) {
        String str = null;
        if (context == null) {
            return null;
        }
        try {
            int myPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    if (Logger.debug()) {
                        Logger.d("Process", "processName = " + runningAppProcessInfo.processName);
                    }
                    str = runningAppProcessInfo.processName;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str;
    }

    private static String b() {
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
            if (Logger.debug()) {
                Logger.d("Process", "get processName = " + sb.toString());
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
