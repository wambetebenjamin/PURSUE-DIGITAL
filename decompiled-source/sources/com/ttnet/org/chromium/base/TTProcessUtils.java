package com.ttnet.org.chromium.base;

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
/* loaded from: classes9.dex */
public class TTProcessUtils {
    public static final String MESSAGE_PROCESS_SERVICE_SUFFIX = ":pushservice";
    public static final String MESSAGE_PROCESS_SUFFIX = ":push";
    private static volatile String sCurProcessName;

    public static boolean isMessageProcess(Context context) {
        String curProcessName = getCurProcessName(context);
        if (curProcessName != null) {
            return curProcessName.endsWith(":push") || curProcessName.endsWith(":pushservice");
        }
        return false;
    }

    public static boolean isMainProcess(Context context) {
        String curProcessName = getCurProcessName(context);
        return (curProcessName == null || !curProcessName.contains(CertificateUtil.DELIMITER)) && curProcessName != null && curProcessName.equals(context.getPackageName());
    }

    public static String getCurProcessName(Context context) {
        if (!TextUtils.isEmpty(sCurProcessName)) {
            return sCurProcessName;
        }
        sCurProcessName = getCurrentProcessNameFromApplication();
        if (!TextUtils.isEmpty(sCurProcessName)) {
            return sCurProcessName;
        }
        sCurProcessName = getCurrentProcessNameFromActivityThread();
        if (!TextUtils.isEmpty(sCurProcessName)) {
            return sCurProcessName;
        }
        sCurProcessName = getCurProcessNameFromProc();
        if (!TextUtils.isEmpty(sCurProcessName)) {
            return sCurProcessName;
        }
        sCurProcessName = getCurProcessNameFromActivityManager(context);
        return sCurProcessName;
    }

    private static String getCurrentProcessNameFromApplication() {
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

    private static String getCurrentProcessNameFromActivityThread() {
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

    private static String getCurProcessNameFromProc() {
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

    private static String getCurProcessNameFromActivityManager(Context context) {
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
}
