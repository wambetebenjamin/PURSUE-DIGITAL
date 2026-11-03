package com.bytedance.apm.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.system.Os;
import android.system.OsConstants;
import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: CommonMonitorUtil.java */
/* loaded from: classes.dex */
public class d {
    public static final int BUFFER_SIZE = 8192;
    public static final String CACHE = "cache";
    public static final long DEFAULT_CLOCK_TICKS_PER_SECOND = 100;
    public static final long KB = 1024;
    public static final String MEMORY_INFO_PATH = "/proc/meminfo";
    public static final String OUTSIDE_STORAGE = "/Android/data/";
    public static final String PROC_STAT_FILE = "/proc/stat";

    /* renamed from: a  reason: collision with root package name */
    private static final int f1679a = -1;

    /* renamed from: b  reason: collision with root package name */
    private static final String f1680b = "/sys/devices/system/cpu/cpu";

    /* renamed from: c  reason: collision with root package name */
    private static final String f1681c = "/cpufreq/stats/time_in_state";

    /* renamed from: d  reason: collision with root package name */
    private static int f1682d = 0;

    /* renamed from: e  reason: collision with root package name */
    private static long f1683e = -1;

    /* renamed from: f  reason: collision with root package name */
    private static int f1684f = 0;

    /* renamed from: g  reason: collision with root package name */
    private static long f1685g = -1;

    public static boolean isProcStatCanRead() {
        int i2 = f1684f;
        if (i2 != 0) {
            return i2 == 1;
        }
        File file = new File("/proc/stat");
        if (file.exists() && file.canRead()) {
            f1684f = 1;
            return true;
        }
        f1684f = 2;
        return false;
    }

    public static long getTotalCPUTime() {
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")), 1000);
            try {
                String readLine = bufferedReader2.readLine();
                bufferedReader2.close();
                String[] split = readLine.split(" ");
                long parseLong = Long.parseLong(split[2]) + Long.parseLong(split[3]) + Long.parseLong(split[4]) + Long.parseLong(split[6]) + Long.parseLong(split[5]) + Long.parseLong(split[7]) + Long.parseLong(split[8]);
                com.bytedance.monitor.util.c.safeClose(bufferedReader2);
                return parseLong;
            } catch (Throwable unused) {
                bufferedReader = bufferedReader2;
                com.bytedance.monitor.util.c.safeClose(bufferedReader);
                return -1L;
            }
        } catch (Throwable unused2) {
        }
    }

    public static long getTotalCPUTimeByTimeInStat() {
        int cpuCoreNum = getCpuCoreNum();
        long j2 = -1;
        if (cpuCoreNum <= 0) {
            return -1L;
        }
        for (int i2 = 0; i2 < cpuCoreNum; i2++) {
            BufferedReader bufferedReader = null;
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(f1680b + i2 + f1681c), 50);
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null && !readLine.isEmpty()) {
                            String[] split = readLine.split("\\s+");
                            if (split.length == 2) {
                                j2 += Long.parseLong(split[1]);
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        throw th;
                    }
                }
                try {
                    bufferedReader2.close();
                } catch (Throwable unused) {
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return j2;
    }

    public static synchronized int getCpuCoreNum() {
        BufferedReader bufferedReader;
        Throwable th;
        synchronized (d.class) {
            int i2 = f1682d;
            if (i2 != 0) {
                return i2;
            }
            int i3 = 0;
            try {
                try {
                    bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 50);
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            } else if (readLine.startsWith("processor")) {
                                i3++;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            throw th;
                        }
                    }
                    bufferedReader.close();
                    f1682d = i3;
                } catch (Throwable th3) {
                    bufferedReader = null;
                    th = th3;
                }
            } catch (Throwable unused) {
            }
            return f1682d;
        }
    }

    public static long getAppCPUTime() {
        return a(Process.myPid());
    }

    public static long a(int i2) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + i2 + "/stat")), 1000);
        } catch (Throwable unused) {
        }
        try {
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            String[] split = readLine.split(" ");
            long parseLong = Long.parseLong(split[13]) + Long.parseLong(split[14]) + Long.parseLong(split[15]) + Long.parseLong(split[16]);
            com.bytedance.monitor.util.c.safeClose(bufferedReader);
            return parseLong;
        } catch (Throwable unused2) {
            bufferedReader2 = bufferedReader;
            com.bytedance.monitor.util.c.safeClose(bufferedReader2);
            return -1L;
        }
    }

    public static long getAppCpuActiveTime() {
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/stat")), 1000);
            try {
                String readLine = bufferedReader2.readLine();
                bufferedReader2.close();
                String[] split = readLine.split(" ");
                long parseLong = Long.parseLong(split[13]) + Long.parseLong(split[14]);
                com.bytedance.monitor.util.c.safeClose(bufferedReader2);
                return parseLong;
            } catch (Exception unused) {
                bufferedReader = bufferedReader2;
                com.bytedance.monitor.util.c.safeClose(bufferedReader);
                return -1L;
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
                com.bytedance.monitor.util.c.safeClose(bufferedReader);
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static long getVmSize() {
        try {
            String[] split = getStringFromFile(String.format("/proc/%s/status", Integer.valueOf(getMyPid()))).trim().split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            for (String str : split) {
                if (str.startsWith("VmSize")) {
                    Matcher matcher = Pattern.compile("\\d+").matcher(str);
                    if (matcher.find()) {
                        return Long.parseLong(matcher.group());
                    }
                }
            }
            if (split.length > 12) {
                Matcher matcher2 = Pattern.compile("\\d+").matcher(split[12]);
                if (matcher2.find()) {
                    return Long.parseLong(matcher2.group());
                }
            }
        } catch (Exception unused) {
        }
        return -1L;
    }

    public static int getMyPid() {
        return Process.myPid();
    }

    public static String getStringFromFile(String str) {
        FileInputStream fileInputStream;
        Throwable th;
        try {
            fileInputStream = new FileInputStream(new File(str));
            try {
                String convertStreamToString = convertStreamToString(fileInputStream);
                fileInputStream.close();
                return convertStreamToString;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
        }
    }

    public static String convertStreamToString(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                        sb.append(readLine).append('\n');
                    } else {
                        bufferedReader2.close();
                        return sb.toString();
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static long getIdleCPUTime() {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")), 1000);
        } catch (Exception unused) {
        } catch (Throwable th2) {
            bufferedReader = null;
            th = th2;
        }
        try {
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            long parseLong = Long.parseLong(readLine.split(" ")[5]);
            com.bytedance.monitor.util.c.safeClose(bufferedReader);
            return parseLong;
        } catch (Exception unused2) {
            bufferedReader2 = bufferedReader;
            com.bytedance.monitor.util.c.safeClose(bufferedReader2);
            return -1L;
        } catch (Throwable th3) {
            th = th3;
            com.bytedance.monitor.util.c.safeClose(bufferedReader);
            throw th;
        }
    }

    public static int getAPPMemLimit(Context context) {
        ActivityManager activityManager;
        if (context == null || (activityManager = (ActivityManager) context.getSystemService("activity")) == null) {
            return -1;
        }
        return activityManager.getMemoryClass();
    }

    public static long getRuntimeMaxMemory() {
        if (f1685g == -1) {
            try {
                f1685g = Runtime.getRuntime().maxMemory();
            } catch (Exception unused) {
            }
        }
        return f1685g;
    }

    public static int getPidMemorySize(int i2, Context context) {
        if (context == null) {
            return -1;
        }
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return -1;
            }
            return activityManager.getProcessMemoryInfo(new int[]{i2})[0].dalvikPss;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static Debug.MemoryInfo getPidMemoryInfo(int i2, Context context) {
        if (context == null) {
            return null;
        }
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return null;
            }
            return activityManager.getProcessMemoryInfo(new int[]{i2})[0];
        } catch (Exception unused) {
            return null;
        }
    }

    public static long getFreeMemory(Context context) {
        if (context == null) {
            return -1L;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return -1L;
        }
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / 1024;
    }

    public static long getTotalMemory(Context context) {
        BufferedReader bufferedReader;
        Throwable th;
        long j2 = -1;
        if (context == null) {
            return -1L;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return -1L;
            }
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo.totalMem / 1024;
        }
        BufferedReader bufferedReader2 = null;
        try {
            String str = "";
            bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        if (readLine.contains("MemTotal")) {
                            str = readLine.split(CertificateUtil.DELIMITER)[1].trim();
                            break;
                        }
                    } else {
                        break;
                    }
                } catch (Exception unused) {
                    bufferedReader2 = bufferedReader;
                    com.bytedance.monitor.util.c.safeClose(bufferedReader2);
                    return j2;
                } catch (Throwable th2) {
                    th = th2;
                    com.bytedance.monitor.util.c.safeClose(bufferedReader);
                    throw th;
                }
            }
            bufferedReader.close();
            j2 = Long.parseLong(str.split(" ")[0].trim());
            com.bytedance.monitor.util.c.safeClose(bufferedReader);
        } catch (Exception unused2) {
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
        }
        return j2;
    }

    public static void getMemMonitorInfo(JSONObject jSONObject, Context context, int i2) {
        ActivityManager activityManager;
        if (jSONObject == null || context == null || (activityManager = (ActivityManager) context.getSystemService("activity")) == null) {
            return;
        }
        Debug.MemoryInfo[] processMemoryInfo = activityManager.getProcessMemoryInfo(new int[]{i2});
        try {
            jSONObject.put("total_mem", getTotalMemory(context));
            jSONObject.put("mem_class", getAPPMemLimit(context));
            jSONObject.put("dalvik_used_mem", processMemoryInfo[0].dalvikPss / 1024);
            jSONObject.put("native_heap_size", processMemoryInfo[0].nativePss / 1024);
        } catch (JSONException unused) {
        }
    }

    public static long getUsedStorageSize(Context context) {
        File file;
        if (context == null) {
            return -1L;
        }
        try {
            file = new File(context.getFilesDir().getParent());
        } catch (Throwable unused) {
        }
        if (file.exists()) {
            long fileDirSize = getFileDirSize(file);
            File externalFilesDir = context.getExternalFilesDir(null);
            if (externalFilesDir != null) {
                File parentFile = externalFilesDir.getParentFile();
                return fileDirSize + (parentFile.exists() ? getFileDirSize(parentFile) : 0L);
            }
            return -1L;
        }
        return -1L;
    }

    public static long getDiskTotalSize() {
        return a() + getFileTotalSize(Environment.getRootDirectory());
    }

    protected static long a() {
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                return getFileTotalSize(i.getExternalRootDir(com.bytedance.apm.c.getContext()));
            }
            return 0L;
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static long getFileTotalSize(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getTotalBytes();
            }
            return statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static long getFileDirSize(File file) {
        long length;
        File[] listFiles = file.listFiles();
        long j2 = 0;
        if (listFiles == null) {
            return 0L;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                length = getFileDirSize(file2);
            } else {
                length = file2.length();
            }
            j2 += length;
        }
        return j2;
    }

    public static long getUsedCacheSize(Context context) {
        if (context == null) {
            return -1L;
        }
        try {
            if (TextUtils.isEmpty(context.getPackageName())) {
                return -1L;
            }
            File cacheDir = context.getCacheDir();
            long fileDirSize = cacheDir.exists() ? getFileDirSize(cacheDir) : 0L;
            File externalCacheDir = com.bytedance.apm.c.getContext().getExternalCacheDir();
            return fileDirSize + (externalCacheDir.exists() ? getFileDirSize(externalCacheDir) : 0L);
        } catch (Throwable unused) {
            return -1L;
        }
    }

    public static v<Long, Long> getTrafficBytes(Context context) {
        int myUid = Process.myUid();
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        for (String str : al.a(new File("/proc/net/xt_qtaguid/stats"), com.bytedance.platform.godzilla.common.c.CHARSET_UTF_8)) {
            String[] split = str.split(" ");
            try {
                if (myUid == Integer.parseInt(split[3])) {
                    long parseLong = Long.parseLong(split[5]);
                    long parseLong2 = Long.parseLong(split[7]);
                    if (Long.valueOf(split[4]).longValue() == 0) {
                        j4 += parseLong;
                        j5 += parseLong2;
                    } else {
                        j2 += parseLong;
                        j3 += parseLong2;
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        long j6 = j2 + j3;
        long j7 = j4 + j5;
        if (j6 < 0) {
            j6 = 0;
        }
        return new v<>(Long.valueOf(j6), Long.valueOf(j7 >= 0 ? j7 : 0L));
    }

    public static long getScClkTck(long j2) {
        long a2;
        if (f1683e == -1) {
            if (Build.VERSION.SDK_INT >= 21) {
                a2 = Os.sysconf(OsConstants._SC_CLK_TCK);
            } else {
                a2 = Build.VERSION.SDK_INT >= 14 ? a(j2) : j2;
            }
            if (a2 > 0) {
                j2 = a2;
            }
            f1683e = j2;
        }
        return f1683e;
    }

    private static long a(long j2) {
        try {
            int i2 = Class.forName("libcore.io.OsConstants").getField("_SC_CLK_TCK").getInt(null);
            Class<?> cls = Class.forName("libcore.io.Libcore");
            Class<?> cls2 = Class.forName("libcore.io.Os");
            return ((Long) cls2.getMethod("sysconf", Integer.TYPE).invoke(cls.getField("os").get(null), Integer.valueOf(i2))).longValue();
        } catch (Exception unused) {
            return j2;
        }
    }
}
