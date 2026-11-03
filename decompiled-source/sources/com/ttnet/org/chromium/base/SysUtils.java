package com.ttnet.org.chromium.base;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.StrictMode;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.metrics.RecordHistogram;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@JNINamespace("base::android")
@MainDex
/* loaded from: classes9.dex */
public class SysUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ANDROID_LOW_MEMORY_DEVICE_THRESHOLD_MB = 512;
    private static final int ANDROID_O_LOW_MEMORY_DEVICE_THRESHOLD_MB = 1024;
    private static final int BYTES_PER_GIGABYTE = 1073741824;
    private static final long HIGH_END_DEVICE_DISK_CAPACITY_GB = 24;
    private static final String TAG = "SysUtils";
    private static Integer sAmountOfPhysicalMemoryKB;
    private static Boolean sHighEndDiskDevice;
    private static Boolean sLowEndDevice;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        void logPageFaultCountToTracing();
    }

    private SysUtils() {
    }

    private static int detectAmountOfPhysicalMemoryKB() {
        Pattern compile = Pattern.compile("^MemTotal:\\s+([0-9]+) kB$");
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                FileReader fileReader = new FileReader("/proc/meminfo");
                try {
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            android.util.Log.w(TAG, "/proc/meminfo lacks a MemTotal entry?");
                            break;
                        }
                        Matcher matcher = compile.matcher(readLine);
                        if (matcher.find()) {
                            int parseInt = Integer.parseInt(matcher.group(1));
                            if (parseInt <= 1024) {
                                android.util.Log.w(TAG, "Invalid /proc/meminfo total size in kB: " + matcher.group(1));
                            } else {
                                bufferedReader.close();
                                return parseInt;
                            }
                        }
                    }
                    bufferedReader.close();
                } finally {
                    fileReader.close();
                }
            } finally {
                StrictMode.setThreadPolicy(allowThreadDiskReads);
            }
        } catch (Exception e2) {
            android.util.Log.w(TAG, "Cannot get total physical size from /proc/meminfo", e2);
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        return 0;
    }

    public static boolean isLowEndDevice() {
        if (sLowEndDevice == null) {
            sLowEndDevice = Boolean.valueOf(detectLowEndDevice());
        }
        return sLowEndDevice.booleanValue();
    }

    public static int amountOfPhysicalMemoryKB() {
        if (sAmountOfPhysicalMemoryKB == null) {
            sAmountOfPhysicalMemoryKB = Integer.valueOf(detectAmountOfPhysicalMemoryKB());
        }
        return sAmountOfPhysicalMemoryKB.intValue();
    }

    public static boolean isCurrentlyLowMemory() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) ContextUtils.getApplicationContext().getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.lowMemory;
    }

    public static void resetForTesting() {
        sLowEndDevice = null;
        sAmountOfPhysicalMemoryKB = null;
    }

    public static boolean hasCamera(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.hasSystemFeature("android.hardware.camera") || packageManager.hasSystemFeature("android.hardware.camera.any");
    }

    private static boolean detectLowEndDevice() {
        if (CommandLine.getInstance().hasSwitch(BaseSwitches.ENABLE_LOW_END_DEVICE_MODE)) {
            return true;
        }
        if (CommandLine.getInstance().hasSwitch(BaseSwitches.DISABLE_LOW_END_DEVICE_MODE)) {
            return false;
        }
        Integer valueOf = Integer.valueOf(detectAmountOfPhysicalMemoryKB());
        sAmountOfPhysicalMemoryKB = valueOf;
        boolean z = valueOf.intValue() > 0 && (Build.VERSION.SDK_INT < 26 ? sAmountOfPhysicalMemoryKB.intValue() / 1024 <= 512 : sAmountOfPhysicalMemoryKB.intValue() / 1024 <= 1024);
        RecordHistogram.recordBooleanHistogram("Android.SysUtilsLowEndMatches", z == ((ContextUtils.getApplicationContext() == null || Build.VERSION.SDK_INT < 19) ? false : ((ActivityManager) ContextUtils.getApplicationContext().getSystemService("activity")).isLowRamDevice()));
        return z;
    }

    public static void logPageFaultCountToTracing() {
        SysUtilsJni.get().logPageFaultCountToTracing();
    }

    public static boolean isHighEndDiskDevice() {
        if (sHighEndDiskDevice == null) {
            sHighEndDiskDevice = Boolean.valueOf(detectHighEndDiskDevice());
        }
        return sHighEndDiskDevice.booleanValue();
    }

    private static boolean detectHighEndDiskDevice() {
        try {
            StrictModeContext allowDiskReads = StrictModeContext.allowDiskReads();
            boolean z = new StatFs(Environment.getDataDirectory().getAbsolutePath()).getTotalBytes() / 1073741824 >= HIGH_END_DEVICE_DISK_CAPACITY_GB;
            if (allowDiskReads != null) {
                allowDiskReads.close();
            }
            return z;
        } catch (IllegalArgumentException e2) {
            android.util.Log.v(TAG, "Cannot get disk data capacity", e2);
            return false;
        }
    }

    public static void setAmountOfPhysicalMemoryKBForTesting(int i2) {
        sAmountOfPhysicalMemoryKB = Integer.valueOf(i2);
    }

    public static boolean isAndroidGo() {
        return isLowEndDevice() && Build.VERSION.SDK_INT >= 26;
    }
}
