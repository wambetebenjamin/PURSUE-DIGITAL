package com.bytedance.geckox.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import java.util.List;
/* compiled from: AppUtils.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final long f9360a = -1;

    public static String getVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e2) {
            com.bytedance.geckox.logger.b.w(com.bytedance.geckox.d.f8984a, "getVersion:", e2);
            return "null";
        }
    }

    public static String a(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            return applicationInfo == null ? "" : applicationInfo.loadLabel(packageManager).toString();
        } catch (Throwable th) {
            com.bytedance.geckox.logger.b.w(com.bytedance.geckox.d.f8984a, "getApplicationName:", th);
            return "";
        }
    }

    public static long b(Context context) {
        try {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            return memoryInfo.availMem;
        } catch (Exception e2) {
            com.bytedance.geckox.logger.b.w(com.bytedance.geckox.d.f8984a, "getFreeMem:", e2);
            return 0L;
        }
    }

    public static String c(Context context) {
        int myPid;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            myPid = Process.myPid();
            runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        } catch (Exception e2) {
            com.bytedance.geckox.logger.b.w(com.bytedance.geckox.d.f8984a, "getProcessName:", e2);
        }
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public static int a() {
        return Process.myPid();
    }

    public static long b() {
        return Thread.currentThread().getId();
    }

    public static String c() {
        long a2 = Environment.getDataDirectory() != null ? a(Environment.getDataDirectory().getPath()) : -1L;
        if (a2 >= 1073741824) {
            return String.format("%.1f GB", Float.valueOf(((float) a2) / ((float) 1073741824)));
        }
        if (a2 >= 1048576) {
            float f2 = ((float) a2) / ((float) 1048576);
            return String.format(f2 > 100.0f ? "%.0f MB" : "%.1f MB", Float.valueOf(f2));
        } else if (a2 > 1024) {
            float f3 = ((float) a2) / ((float) 1024);
            return String.format(f3 > 100.0f ? "%.0f KB" : "%.1f KB", Float.valueOf(f3));
        } else {
            return String.format("%d B", Long.valueOf(a2));
        }
    }

    private static long a(String str) {
        try {
            StatFs statFs = new StatFs(str);
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
            }
            return statFs.getBlockSize() * statFs.getAvailableBlocks();
        } catch (IllegalArgumentException unused) {
            return -1L;
        }
    }
}
