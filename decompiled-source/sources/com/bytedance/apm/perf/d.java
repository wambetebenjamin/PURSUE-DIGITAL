package com.bytedance.apm.perf;

import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* compiled from: PerfCollectUtils.java */
/* loaded from: classes.dex */
public class d {
    public static com.bytedance.apm.perf.entity.b getMemory(Context context) {
        com.bytedance.apm.perf.entity.b bVar = new com.bytedance.apm.perf.entity.b();
        try {
            long j2 = Runtime.getRuntime().totalMemory();
            long freeMemory = Runtime.getRuntime().freeMemory();
            bVar.javaTotalMemory = j2;
            bVar.javaFreeMemory = freeMemory;
            bVar.javaUsedMemory = j2 - freeMemory;
            Debug.MemoryInfo pidMemoryInfo = com.bytedance.apm.util.d.getPidMemoryInfo(Process.myPid(), context);
            if (pidMemoryInfo != null) {
                int i2 = pidMemoryInfo.dalvikPss;
                int i3 = pidMemoryInfo.nativePss;
                int totalPss = pidMemoryInfo.getTotalPss();
                if (Build.VERSION.SDK_INT >= 23) {
                    try {
                        bVar.graphics = Integer.parseInt(pidMemoryInfo.getMemoryStat(com.bytedance.apm.constant.i.GRAPHICS_MEM)) * 1024;
                        bVar.javaHeap = Integer.parseInt(pidMemoryInfo.getMemoryStat(com.bytedance.apm.constant.i.MEM_JAVA_HEAP)) * 1024;
                        bVar.nativeHeap = Integer.parseInt(pidMemoryInfo.getMemoryStat(com.bytedance.apm.constant.i.MEM_NATIVE_HEAP)) * 1024;
                        bVar.code = Integer.parseInt(pidMemoryInfo.getMemoryStat(com.bytedance.apm.constant.i.MEM_CODE)) * 1024;
                        bVar.stack = Integer.parseInt(pidMemoryInfo.getMemoryStat(com.bytedance.apm.constant.i.MEM_STACK)) * 1024;
                        bVar.privateOther = Integer.parseInt(pidMemoryInfo.getMemoryStat(com.bytedance.apm.constant.i.MEM_PRIVATE_OTHER)) * 1024;
                        bVar.system = Integer.parseInt(pidMemoryInfo.getMemoryStat(com.bytedance.apm.constant.i.MEM_SYSTEM)) * 1024;
                        bVar.totalPss = Integer.parseInt(pidMemoryInfo.getMemoryStat(com.bytedance.apm.constant.i.MEM_TOTAL_PSS)) * 1024;
                        bVar.totalSwap = Integer.parseInt(pidMemoryInfo.getMemoryStat(com.bytedance.apm.constant.i.MEM_TOTAL_SWAP)) * 1024;
                    } catch (Exception unused) {
                    }
                }
                bVar.pssDalvik = i2 * 1024;
                bVar.pssNative = i3 * 1024;
                bVar.pssTotal = totalPss * 1024;
                bVar.dalvikPrivateDirty = pidMemoryInfo.dalvikPrivateDirty * 1024;
                bVar.dalvikSharedDirty = pidMemoryInfo.dalvikSharedDirty * 1024;
                bVar.otherPss = pidMemoryInfo.otherPss * 1024;
                bVar.otherPrivateDirty = pidMemoryInfo.otherPrivateDirty * 1024;
                bVar.otherSharedDirty = pidMemoryInfo.otherSharedDirty * 1024;
                bVar.totalSwappablePss = pidMemoryInfo.getTotalSwappablePss() * 1024;
                bVar.totalPrivateDirty = pidMemoryInfo.getTotalPrivateDirty() * 1024;
                bVar.totalSharedClean = pidMemoryInfo.getTotalSharedClean() * 1024;
            }
            bVar.vmSize = com.bytedance.apm.util.d.getVmSize() * 1024;
        } catch (Exception unused2) {
        }
        return bVar;
    }

    public static com.bytedance.apm.perf.entity.a getCpuRate() {
        com.bytedance.apm.perf.entity.a aVar = new com.bytedance.apm.perf.entity.a();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long appCPUTime = com.bytedance.apm.util.d.getAppCPUTime();
            double d2 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            boolean isProcStatCanRead = com.bytedance.apm.util.d.isProcStatCanRead();
            long totalCPUTime = isProcStatCanRead ? com.bytedance.apm.util.d.getTotalCPUTime() : 0L;
            try {
                Thread.sleep(360L);
            } catch (InterruptedException unused) {
            }
            long appCPUTime2 = com.bytedance.apm.util.d.getAppCPUTime();
            if (isProcStatCanRead) {
                long totalCPUTime2 = com.bytedance.apm.util.d.getTotalCPUTime() - totalCPUTime;
                if (totalCPUTime2 > 0) {
                    d2 = (((float) appCPUTime2) - ((float) appCPUTime)) / ((float) totalCPUTime2);
                }
            }
            aVar.cpuAppRate = d2;
            aVar.cpuAppSpeed = (((appCPUTime2 - appCPUTime) * 1000.0d) / (System.currentTimeMillis() - currentTimeMillis)) / com.bytedance.apm.util.d.getScClkTck(100L);
        } catch (Exception unused2) {
        }
        return aVar;
    }
}
