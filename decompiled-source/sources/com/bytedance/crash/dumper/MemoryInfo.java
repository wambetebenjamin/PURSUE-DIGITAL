package com.bytedance.crash.dumper;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import com.bytedance.crash.Global;
import com.bytedance.crash.util.u;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class MemoryInfo {
    public static void a(File file) {
        Context c2 = Global.c();
        if (c2 != null) {
            ActivityManager activityManager = (ActivityManager) c2.getSystemService("activity");
            DevMemoryInfo.dump(file);
            SysMemoryInfo.dump(file, activityManager);
            AppMemoryInfo.dump(file, activityManager);
        }
    }

    public static void a(JSONObject jSONObject, File file) {
        AppMemoryInfo appMemoryInfo;
        SysMemoryInfo sysMemoryInfo;
        DevMemoryInfo devMemoryInfo;
        JSONObject jSONObject2;
        AppMemoryInfo appMemoryInfo2;
        SysMemoryInfo sysMemoryInfo2;
        ActivityManager activityManager;
        if (file != null) {
            appMemoryInfo2 = AppMemoryInfo.load(file);
            sysMemoryInfo2 = SysMemoryInfo.load(file);
            devMemoryInfo = DevMemoryInfo.load(file);
            jSONObject2 = b(file);
        } else {
            Context c2 = Global.c();
            if (c2 == null || (activityManager = (ActivityManager) c2.getSystemService("activity")) == null) {
                appMemoryInfo = null;
                sysMemoryInfo = null;
            } else {
                appMemoryInfo = new AppMemoryInfo(activityManager);
                sysMemoryInfo = new SysMemoryInfo(activityManager);
            }
            SysMemoryInfo sysMemoryInfo3 = sysMemoryInfo;
            devMemoryInfo = new DevMemoryInfo();
            jSONObject2 = null;
            appMemoryInfo2 = appMemoryInfo;
            sysMemoryInfo2 = sysMemoryInfo3;
        }
        if (appMemoryInfo2 != null) {
            appMemoryInfo2.pushTo(jSONObject);
        }
        if (sysMemoryInfo2 != null) {
            sysMemoryInfo2.pushTo(jSONObject);
        }
        if (devMemoryInfo != null) {
            devMemoryInfo.pushTo(jSONObject);
        }
        if (jSONObject2 != null) {
            a(jSONObject, jSONObject2);
        }
    }

    public static void a(JSONObject jSONObject) {
        a(jSONObject, (File) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class AppMemoryInfo implements Serializable {
        private static final String FILE_NAME = "app_memory.inf";
        private static final long serialVersionUID = 1;
        private int mLargeMemoryClass;
        private int mMemoryClass;
        private final long mRuntimeFree;
        private final long mRuntimeMax;
        private final long mRuntimeTotal;
        private final long mNativeHeapSize = Debug.getNativeHeapSize();
        private final long mNativeHeapAllocSize = Debug.getNativeHeapAllocatedSize();
        private final long mNativeHeapFreeSize = Debug.getNativeHeapFreeSize();

        public static void dump(File file, ActivityManager activityManager) {
            u.a(new File(file, FILE_NAME), new AppMemoryInfo(activityManager));
        }

        public static AppMemoryInfo load(File file) {
            return (AppMemoryInfo) u.a(new File(file, FILE_NAME));
        }

        AppMemoryInfo(ActivityManager activityManager) {
            Runtime runtime = Runtime.getRuntime();
            this.mRuntimeMax = runtime.maxMemory();
            this.mRuntimeFree = runtime.freeMemory();
            this.mRuntimeTotal = runtime.totalMemory();
            if (activityManager != null) {
                this.mMemoryClass = activityManager.getMemoryClass();
                this.mLargeMemoryClass = activityManager.getLargeMemoryClass();
            }
        }

        void pushTo(JSONObject jSONObject) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("native_heap_size", this.mNativeHeapSize);
                jSONObject2.put("native_heap_alloc_size", this.mNativeHeapAllocSize);
                jSONObject2.put("native_heap_free_size", this.mNativeHeapFreeSize);
                jSONObject2.put("max_memory", this.mRuntimeMax);
                jSONObject2.put("free_memory", this.mRuntimeFree);
                jSONObject2.put("total_memory", this.mRuntimeTotal);
                jSONObject2.put("memory_class", this.mMemoryClass);
                jSONObject2.put("large_memory_class", this.mLargeMemoryClass);
                jSONObject.put("app_memory_info", jSONObject2);
            } catch (JSONException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class SysMemoryInfo implements Serializable {
        private static final String FILE_NAME = "sys_memory.inf";
        private static final long UNIT_SIZE = 536870912;
        private static final long serialVersionUID = 1;
        private final long mAvailMem;
        private final boolean mLowMemory;
        private final long mThreshold;
        private final long mTotalMem;

        static void dump(File file, ActivityManager activityManager) {
            if (activityManager != null) {
                u.a(new File(file, FILE_NAME), new SysMemoryInfo(activityManager));
            }
        }

        static SysMemoryInfo load(File file) {
            return (SysMemoryInfo) u.a(new File(file, FILE_NAME));
        }

        SysMemoryInfo(ActivityManager activityManager) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            this.mLowMemory = memoryInfo.lowMemory;
            this.mThreshold = memoryInfo.threshold;
            this.mAvailMem = memoryInfo.availMem;
            this.mTotalMem = memoryInfo.totalMem;
        }

        void pushTo(JSONObject jSONObject) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("lowMemory", this.mLowMemory);
                jSONObject2.put("threshold", this.mThreshold);
                jSONObject2.put("availMem", this.mAvailMem);
                jSONObject2.put("totalMem", this.mTotalMem);
                jSONObject.put("sys_memory_info", jSONObject2);
            } catch (JSONException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class DevMemoryInfo implements Serializable {
        private static final String FILE_NAME = "dev_memory.inf";
        private static final long KB = 1024;
        private static final long serialVersionUID = 1;
        private final long mDalvikPrivateDirty;
        private final long mDalvikPss;
        private final long mDalvikSharedDirty;
        private final long mNativePrivateDirty;
        private final long mNativePss;
        private final long mNativeSharedDirty;
        private final long mOtherPrivateDirty;
        private final long mOtherPss;
        private final long mOtherSharedDirty;
        private final long mSummaryGraphics;
        private final long mTotalPrivateClean;
        private final long mTotalPrivateDirty;
        private final long mTotalPss;
        private final long mTotalSharedClean;
        private final long mTotalSharedDirty;
        private final long mTotalSwappablePss;

        public static void dump(File file) {
            u.a(new File(file, FILE_NAME), new DevMemoryInfo());
        }

        public static DevMemoryInfo load(File file) {
            return (DevMemoryInfo) u.a(new File(file, FILE_NAME));
        }

        DevMemoryInfo() {
            Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
            this.mDalvikPss = memoryInfo.dalvikPss * 1024;
            this.mDalvikSharedDirty = memoryInfo.dalvikSharedDirty * 1024;
            this.mDalvikPrivateDirty = memoryInfo.dalvikPrivateDirty * 1024;
            this.mNativePss = memoryInfo.nativePss * 1024;
            this.mNativeSharedDirty = memoryInfo.nativeSharedDirty * 1024;
            this.mNativePrivateDirty = memoryInfo.nativePrivateDirty * 1024;
            this.mOtherPss = memoryInfo.otherPss * 1024;
            this.mOtherSharedDirty = memoryInfo.otherSharedDirty * 1024;
            this.mOtherPrivateDirty = memoryInfo.otherPrivateDirty * 1024;
            this.mTotalPss = memoryInfo.getTotalPss() * 1024;
            if (Build.VERSION.SDK_INT >= 19) {
                this.mTotalSharedClean = memoryInfo.getTotalSharedClean() * 1024;
                this.mTotalPrivateClean = memoryInfo.getTotalPrivateClean() * 1024;
                this.mTotalSwappablePss = memoryInfo.getTotalSwappablePss() * 1024;
            } else {
                this.mTotalSharedClean = 0L;
                this.mTotalPrivateClean = 0L;
                this.mTotalSwappablePss = 0L;
            }
            this.mTotalSharedDirty = memoryInfo.getTotalSharedDirty() * 1024;
            this.mTotalPrivateDirty = memoryInfo.getTotalPrivateDirty() * 1024;
            this.mSummaryGraphics = getSummaryGraphics(memoryInfo);
        }

        private static long getSummaryGraphics(Debug.MemoryInfo memoryInfo) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    return Integer.parseInt(memoryInfo.getMemoryStat(com.bytedance.apm.constant.i.GRAPHICS_MEM)) * 1024;
                } catch (Exception unused) {
                    return 0L;
                }
            }
            return 0L;
        }

        public void pushTo(JSONObject jSONObject) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("dalvikPss", this.mDalvikPss);
                jSONObject2.put("dalvikSharedDirty", this.mDalvikSharedDirty);
                jSONObject2.put("dalvikPrivateDirty", this.mDalvikPrivateDirty);
                jSONObject2.put("nativePss", this.mNativePss);
                jSONObject2.put("nativeSharedDirty", this.mNativeSharedDirty);
                jSONObject2.put("nativePrivateDirty", this.mNativePrivateDirty);
                jSONObject2.put("otherPss", this.mOtherPss);
                jSONObject2.put("otherSharedDirty", this.mOtherSharedDirty);
                jSONObject2.put("otherPrivateDirty", this.mOtherPrivateDirty);
                jSONObject2.put("totalPss", this.mTotalPss);
                jSONObject2.put("totalPrivateClean", this.mTotalPrivateClean);
                jSONObject2.put("totalSharedClean", this.mTotalSharedClean);
                jSONObject2.put("totalSwappablePss", this.mTotalSwappablePss);
                jSONObject2.put("totalSharedDirty", this.mTotalSharedDirty);
                jSONObject2.put("totalPrivateDirty", this.mTotalPrivateDirty);
                jSONObject2.put(com.bytedance.apm.constant.i.GRAPHICS_MEM, this.mSummaryGraphics);
                jSONObject.put("memory_info", jSONObject2);
            } catch (JSONException unused) {
            }
        }
    }

    private static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject optJSONObject = jSONObject.optJSONObject("memory_info");
        if (optJSONObject == null) {
            try {
                jSONObject.putOpt("memory_info", jSONObject2);
            } catch (JSONException unused) {
            }
        } else if (optJSONObject.has("graphicPss")) {
        } else {
            com.bytedance.crash.util.q.assignIfNoExist(optJSONObject, jSONObject2);
        }
    }

    private static JSONObject b(File file) {
        JSONObject jSONObject = new JSONObject();
        File file2 = new File(file, "sys_memtrack.txt");
        if (file2.exists()) {
            BufferedReader bufferedReader = null;
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file2));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        } else if (!"".equals(readLine)) {
                            String[] split = readLine.split(" ");
                            if (split.length == 3) {
                                String str = split[0];
                                jSONObject.putOpt(str.substring(0, str.length() - 1), Long.valueOf(Long.parseLong(split[1]) * 1024));
                            }
                        }
                    } catch (Throwable unused) {
                        bufferedReader = bufferedReader2;
                        com.bytedance.crash.util.p.close(bufferedReader);
                        return jSONObject;
                    }
                }
                com.bytedance.crash.util.p.close(bufferedReader2);
            } catch (Throwable unused2) {
            }
        }
        return jSONObject;
    }

    public static void b(JSONObject jSONObject, File file) {
        BufferedReader bufferedReader;
        String readLine;
        File file2 = new File(file, "maps_size.txt");
        if (file2.exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader(file2));
                readLine = bufferedReader.readLine();
            } catch (Throwable unused) {
            }
            if (readLine == null) {
                return;
            }
            String[] split = readLine.trim().split(" ");
            if (split.length < 2) {
                return;
            }
            JSONArray jSONArray = new JSONArray();
            while (true) {
                String readLine2 = bufferedReader.readLine();
                if (readLine2 == null) {
                    break;
                }
                String[] split2 = readLine2.trim().split(" ");
                if (split2.length == split.length) {
                    JSONObject jSONObject2 = new JSONObject();
                    for (int i2 = 0; i2 < split.length; i2++) {
                        jSONObject2.put(split[i2], split2[i2]);
                    }
                    jSONArray.put(jSONObject2);
                }
            }
            bufferedReader.close();
            jSONObject.put("tagged_maps_size", jSONArray);
            com.bytedance.crash.util.m.deleteFile(file2);
        }
    }
}
