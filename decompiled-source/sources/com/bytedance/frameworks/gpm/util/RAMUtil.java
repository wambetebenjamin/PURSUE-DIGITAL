package com.bytedance.frameworks.gpm.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import com.bytedance.frameworks.gpm.GPMMonitor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class RAMUtil {
    static native int getManagedHeapSize();

    static native int getManagedHeapUsedSize();

    public static int getRAMSize() {
        BufferedReader bufferedReader;
        Throwable th;
        FileReader fileReader;
        IOException e2;
        try {
            try {
                fileReader = new FileReader("/proc/meminfo");
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                bufferedReader = new BufferedReader(fileReader);
                try {
                    int a2 = c.a(bufferedReader.readLine());
                    c.a(fileReader);
                    c.a(bufferedReader);
                    return a2;
                } catch (IOException e3) {
                    e2 = e3;
                    GPMMonitor.loge("ram size:" + e2.toString());
                    c.a(fileReader);
                    c.a(bufferedReader);
                    return 0;
                }
            } catch (IOException e4) {
                bufferedReader = null;
                e2 = e4;
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
                c.a(fileReader);
                c.a(bufferedReader);
                throw th;
            }
        } catch (IOException e5) {
            bufferedReader = null;
            e2 = e5;
            fileReader = null;
        } catch (Throwable th4) {
            bufferedReader = null;
            th = th4;
            fileReader = null;
        }
    }

    public static String getMemoryUsage(Context context) {
        JSONObject a2;
        return (context == null || (a2 = a(context)) == null) ? "" : a2.toString();
    }

    private static JSONObject a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return null;
            }
            Debug.MemoryInfo[] processMemoryInfo = activityManager.getProcessMemoryInfo(new int[]{Process.myPid()});
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("totalPss", processMemoryInfo[0].getTotalPss());
            jSONObject.put("dalvikPss", processMemoryInfo[0].dalvikPss);
            jSONObject.put("nativePss", processMemoryInfo[0].nativePss);
            long j2 = Runtime.getRuntime().totalMemory() / 1024;
            long freeMemory = Runtime.getRuntime().freeMemory() / 1024;
            jSONObject.put("jvmTotal", j2);
            jSONObject.put("jvmFree", freeMemory);
            jSONObject.put("jvmUsed", j2 - (freeMemory / 1024));
            if (Build.VERSION.SDK_INT >= 29) {
                Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
                Debug.getMemoryInfo(memoryInfo);
                jSONObject.put("totalPssDebug", memoryInfo.getTotalPss());
                jSONObject.put("dalvikPssDebug", memoryInfo.dalvikPss);
                jSONObject.put("nativePssDebug", memoryInfo.nativePss);
            }
            return jSONObject;
        } catch (Throwable th) {
            GPMMonitor.loge("memory usage:" + th.toString());
            return null;
        }
    }

    public static void gcAndFinalize() {
        try {
            System.gc();
            System.runFinalization();
            System.gc();
        } catch (Throwable unused) {
        }
    }
}
