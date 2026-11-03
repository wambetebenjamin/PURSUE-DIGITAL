package com.bytedance.ttgame.sdk.module.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.List;
import timber.log.Timber;
/* loaded from: classes8.dex */
public class ProcessUtils {
    private static final String TAG = "ProcessUtils";
    private static String runningProcessName;
    private static String sCurProcessName;

    public static boolean isInMainProcess(Context context) {
        if (context == null) {
            return false;
        }
        if (!TextUtils.isEmpty(runningProcessName)) {
            return runningProcessName.equals(getProcessName(context));
        }
        return context.getPackageName().equals(getProcessName(context));
    }

    public static void changeMainRunningProcessName(String str) {
        Timber.tag(TAG).d("indicate new main process name: ".concat(String.valueOf(str)), new Object[0]);
        runningProcessName = str;
    }

    public static String getProcessName(Context context) {
        if (!TextUtils.isEmpty(sCurProcessName)) {
            return sCurProcessName;
        }
        String currentProcessNameByApplication = getCurrentProcessNameByApplication();
        sCurProcessName = currentProcessNameByApplication;
        if (!TextUtils.isEmpty(currentProcessNameByApplication)) {
            return sCurProcessName;
        }
        String currentProcessNameByActivityThread = getCurrentProcessNameByActivityThread();
        sCurProcessName = currentProcessNameByActivityThread;
        if (!TextUtils.isEmpty(currentProcessNameByActivityThread)) {
            return sCurProcessName;
        }
        String curProcessNameFromProc = getCurProcessNameFromProc();
        sCurProcessName = curProcessNameFromProc;
        if (!TextUtils.isEmpty(curProcessNameFromProc)) {
            return sCurProcessName;
        }
        String currentProcessNameByActivityManager = getCurrentProcessNameByActivityManager(context);
        sCurProcessName = currentProcessNameByActivityManager;
        if (!TextUtils.isEmpty(currentProcessNameByActivityManager)) {
            return sCurProcessName;
        }
        return sCurProcessName;
    }

    private static String getCurrentProcessNameByApplication() {
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

    private static String getCurrentProcessNameByActivityThread() {
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

    private static String getCurrentProcessNameByActivityManager(Context context) {
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

    public static void killCurrentProcess() {
        Process.killProcess(Process.myPid());
    }

    public static boolean isInThanosHunterProcess(Context context) {
        if (context == null) {
            return false;
        }
        return getProcessName(context).equals(context.getPackageName() + ":hunter");
    }
}
