package com.bytedance.ttgame.sdk.module.utils;

import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.internal.security.CertificateUtil;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
/* loaded from: classes8.dex */
public class DeviceInfo {
    private static final int BUFFER_SIZE = 128;
    private static final int CPU_COEFFICIENT = 1000;
    private static final FileFilter CPU_FILTER = new FileFilter() { // from class: com.bytedance.ttgame.sdk.module.utils.DeviceInfo.1
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            String name = file.getName();
            if (name.startsWith("cpu")) {
                for (int i2 = 3; i2 < name.length(); i2++) {
                    if (!Character.isDigit(name.charAt(i2))) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
    };
    public static final int DEVICEINFO_UNKNOWN = -1;
    private static final int FILE_BUFFER_SIZE = 1024;
    private static final String G2 = "2G";
    private static final String G3 = "3G";
    private static final String G4 = "4G";
    private static final int INVALID_VALUE = -1;
    private static final int MEMORY_COEFFICIENT = 1024;
    private static final String MOBILE = "Mobile";
    private static final String TELECOM = "Telecom";
    private static final String UNICOM = "Unicom";
    private static final String UNKNOWN = "unknown";
    private static final String WIFI = "wifi";

    private DeviceInfo() {
    }

    public static String getFlashMemoryType() {
        try {
            File file = new File("/proc/fs/ext4/");
            if (file.exists() && file.isDirectory() && file.list() != null) {
                for (String str : file.list()) {
                    if (str.contains("sda")) {
                        return "UFS";
                    }
                }
                return "eMMC";
            }
            return "eMMC";
        } catch (SecurityException unused) {
            return "eMMC";
        }
    }

    public static int getNumberOfCPUCores() {
        if (Build.VERSION.SDK_INT <= 10) {
            return 1;
        }
        try {
            int coresFromFileInfo = getCoresFromFileInfo("/sys/devices/system/cpu/possible");
            if (coresFromFileInfo == -1) {
                coresFromFileInfo = getCoresFromFileInfo("/sys/devices/system/cpu/present");
            }
            return coresFromFileInfo == -1 ? getCoresFromCPUFileList() : coresFromFileInfo;
        } catch (NullPointerException | SecurityException unused) {
            return -1;
        }
    }

    private static int getCoresFromFileInfo(String str) {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(str);
            try {
                int coresFromFileString = getCoresFromFileString(new BufferedReader(new InputStreamReader(fileInputStream2)).readLine());
                try {
                    fileInputStream2.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return coresFromFileString;
            } catch (IOException unused) {
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                        return -1;
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        return -1;
                    }
                }
                return -1;
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    static int getCoresFromFileString(String str) {
        if (str == null || !str.matches("0-[\\d]+$")) {
            return -1;
        }
        return Integer.valueOf(str.substring(2)).intValue() + 1;
    }

    private static int getCoresFromCPUFileList() {
        return new File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).length;
    }

    public static int getCPUMaxFreqKHz() {
        int i2 = -1;
        int i3 = -1;
        for (int i4 = 0; i4 < getNumberOfCPUCores(); i4++) {
            try {
                File file = new File("/sys/devices/system/cpu/cpu" + i4 + "/cpufreq/cpuinfo_max_freq");
                if (file.exists()) {
                    byte[] bArr = new byte[128];
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        fileInputStream.read(bArr);
                        int i5 = 0;
                        while (Character.isDigit(bArr[i5]) && i5 < 128) {
                            i5++;
                        }
                        Integer valueOf = Integer.valueOf(Integer.parseInt(new String(bArr, 0, i5)));
                        if (valueOf.intValue() > i3) {
                            i3 = valueOf.intValue();
                        }
                    } catch (NumberFormatException unused) {
                    }
                    fileInputStream.close();
                }
            } catch (IOException unused2) {
            }
        }
        if (i3 == -1) {
            FileInputStream fileInputStream2 = new FileInputStream("/proc/cpuinfo");
            int parseFileForValue = parseFileForValue("cpu MHz", fileInputStream2) * 1000;
            if (parseFileForValue > i3) {
                i3 = parseFileForValue;
            }
            fileInputStream2.close();
        }
        i2 = i3;
        return String.valueOf(i2).length() >= 6 ? i2 / 1000 : i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long getTotalMemory(android.content.Context r6) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = -1
            r3 = 16
            if (r0 < r3) goto L1d
            android.app.ActivityManager$MemoryInfo r0 = new android.app.ActivityManager$MemoryInfo
            r0.<init>()
            java.lang.String r3 = "activity"
            java.lang.Object r6 = r6.getSystemService(r3)
            android.app.ActivityManager r6 = (android.app.ActivityManager) r6
            if (r6 == 0) goto L1d
            r6.getMemoryInfo(r0)
            long r3 = r0.totalMem
            goto L1e
        L1d:
            r3 = r1
        L1e:
            int r6 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r6 != 0) goto L47
            r6 = 0
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L44
            java.lang.String r1 = "/proc/meminfo"
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L44
            java.lang.String r6 = "MemTotal"
            int r6 = parseFileForValue(r6, r0)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            long r1 = (long) r6
            r3 = 1024(0x400, double:5.06E-321)
            long r3 = r3 * r1
            closeSafety(r0)
            goto L47
        L38:
            r6 = move-exception
            goto L40
        L3a:
            r6 = r0
            goto L44
        L3c:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
        L40:
            closeSafety(r0)
            throw r6
        L44:
            closeSafety(r6)
        L47:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.ttgame.sdk.module.utils.DeviceInfo.getTotalMemory(android.content.Context):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long getAvailableMemory(android.content.Context r6) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = -1
            r3 = 16
            if (r0 < r3) goto L1d
            android.app.ActivityManager$MemoryInfo r0 = new android.app.ActivityManager$MemoryInfo
            r0.<init>()
            java.lang.String r3 = "activity"
            java.lang.Object r6 = r6.getSystemService(r3)
            android.app.ActivityManager r6 = (android.app.ActivityManager) r6
            if (r6 == 0) goto L1d
            r6.getMemoryInfo(r0)
            long r3 = r0.availMem
            goto L1e
        L1d:
            r3 = r1
        L1e:
            int r6 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r6 != 0) goto L47
            r6 = 0
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L44
            java.lang.String r1 = "/proc/meminfo"
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L44
            java.lang.String r6 = "MemAvailable"
            int r6 = parseFileForValue(r6, r0)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            long r1 = (long) r6
            r3 = 1024(0x400, double:5.06E-321)
            long r3 = r3 * r1
            closeSafety(r0)
            goto L47
        L38:
            r6 = move-exception
            goto L40
        L3a:
            r6 = r0
            goto L44
        L3c:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
        L40:
            closeSafety(r0)
            throw r6
        L44:
            closeSafety(r6)
        L47:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.ttgame.sdk.module.utils.DeviceInfo.getAvailableMemory(android.content.Context):long");
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0036, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int parseFileForValue(java.lang.String r6, java.io.FileInputStream r7) {
        /*
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]
            int r7 = r7.read(r0)     // Catch: java.lang.Throwable -> L39
            r1 = 0
        L9:
            if (r1 >= r7) goto L39
            r2 = r0[r1]     // Catch: java.lang.Throwable -> L39
            r3 = 10
            if (r2 == r3) goto L13
            if (r1 != 0) goto L36
        L13:
            r2 = r0[r1]     // Catch: java.lang.Throwable -> L39
            if (r2 != r3) goto L19
            int r1 = r1 + 1
        L19:
            r2 = r1
        L1a:
            if (r2 >= r7) goto L36
            int r3 = r2 - r1
            r4 = r0[r2]     // Catch: java.lang.Throwable -> L39
            char r5 = r6.charAt(r3)     // Catch: java.lang.Throwable -> L39
            if (r4 != r5) goto L36
            int r4 = r6.length()     // Catch: java.lang.Throwable -> L39
            int r4 = r4 + (-1)
            if (r3 != r4) goto L33
            int r6 = extractValue(r0, r2)     // Catch: java.lang.Throwable -> L39
            return r6
        L33:
            int r2 = r2 + 1
            goto L1a
        L36:
            int r1 = r1 + 1
            goto L9
        L39:
            r6 = -1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.ttgame.sdk.module.utils.DeviceInfo.parseFileForValue(java.lang.String, java.io.FileInputStream):int");
    }

    private static int extractValue(byte[] bArr, int i2) {
        while (i2 < bArr.length && bArr[i2] != 10) {
            if (Character.isDigit(bArr[i2])) {
                int i3 = i2 + 1;
                while (i3 < bArr.length && Character.isDigit(bArr[i3])) {
                    i3++;
                }
                return Integer.parseInt(new String(bArr, 0, i2, i3 - i2));
            }
            i2++;
        }
        return -1;
    }

    public static long getAvailableInternalStorageSize() {
        if (Environment.getRootDirectory() != null) {
            return getDirectoryAvailableSize(Environment.getRootDirectory().getPath());
        }
        return -1L;
    }

    public static long getTotalInternalStorageSize() {
        if (Environment.getRootDirectory() != null) {
            return getDirectoryTotalSize(Environment.getRootDirectory().getPath());
        }
        return -1L;
    }

    public static long getAvailableExternalStorageSize() {
        if (!isExternalStorageMounted() || Environment.getExternalStorageDirectory() == null) {
            return -1L;
        }
        return getDirectoryAvailableSize(Environment.getExternalStorageDirectory().getPath());
    }

    public static long getTotalExternalStorageSize() {
        if (!isExternalStorageMounted() || Environment.getExternalStorageDirectory() == null) {
            return -1L;
        }
        return getDirectoryTotalSize(Environment.getExternalStorageDirectory().getPath());
    }

    public static long getAppStorageSize(Context context) {
        if (context == null) {
            return -1L;
        }
        try {
            String packageName = context.getPackageName();
            if (TextUtils.isEmpty(packageName)) {
                return -1L;
            }
            File file = new File("/data/data/".concat(String.valueOf(packageName)));
            if (file.exists()) {
                long directorySize = getDirectorySize(file);
                File file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + packageName);
                return directorySize + (file2.exists() ? getDirectorySize(file2) : 0L);
            }
            return -1L;
        } catch (Throwable th) {
            th.printStackTrace();
            return -1L;
        }
    }

    private static long getDirectoryAvailableSize(String str) {
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

    private static long getDirectoryTotalSize(String str) {
        try {
            StatFs statFs = new StatFs(str);
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getBlockSizeLong() * statFs.getBlockCountLong();
            }
            return statFs.getBlockSize() * statFs.getBlockCount();
        } catch (IllegalArgumentException unused) {
            return -1L;
        }
    }

    private static long getDirectorySize(File file) {
        long length;
        File[] listFiles = file.listFiles();
        long j2 = 0;
        if (listFiles == null) {
            return 0L;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                length = getDirectorySize(file2);
            } else {
                length = file2.length();
            }
            j2 += length;
        }
        return j2;
    }

    private static boolean isExternalStorageMounted() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    private static String getCpuModel() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"));
            while (true) {
                try {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        } else if (readLine.contains(CertificateUtil.DELIMITER)) {
                            String[] split = readLine.split(CertificateUtil.DELIMITER);
                            if (split[0].contains("Hardware")) {
                                String str = split[1];
                                closeSafety(bufferedReader);
                                return str;
                            }
                        }
                    } catch (IOException e2) {
                        e = e2;
                        e.printStackTrace();
                        closeSafety(bufferedReader);
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    closeSafety(bufferedReader2);
                    throw th;
                }
            }
        } catch (IOException e3) {
            e = e3;
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
            closeSafety(bufferedReader2);
            throw th;
        }
    }

    private static void closeSafety(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static ScreenInfo getScreenInfo(Context context) {
        ScreenInfo screenInfo = new ScreenInfo();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            screenInfo.dpi = displayMetrics.densityDpi;
            screenInfo.width = displayMetrics.widthPixels;
            screenInfo.height = displayMetrics.heightPixels;
        }
        return screenInfo;
    }

    public static CPUInfo getCpuInfo(Context context) {
        CPUInfo cPUInfo = new CPUInfo();
        cPUInfo.model = getCpuModel();
        cPUInfo.freq = getCPUMaxFreqKHz() * 1000;
        cPUInfo.coreNums = getNumberOfCPUCores();
        return cPUInfo;
    }

    public static StorageInfo getStorageInfo(Context context) {
        StorageInfo storageInfo = new StorageInfo();
        storageInfo.appStorageSize = getAppStorageSize(context);
        storageInfo.availableExternalSize = getAvailableExternalStorageSize();
        storageInfo.availableInternalSize = getAvailableInternalStorageSize();
        storageInfo.totalExternalSize = getTotalExternalStorageSize();
        storageInfo.totalInternalSize = getTotalInternalStorageSize();
        return storageInfo;
    }

    public static MemoryInfo getMemoryInfo(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        memoryInfo.totalSize = getTotalMemory(context);
        memoryInfo.availableSize = getAvailableMemory(context);
        return memoryInfo;
    }

    /* loaded from: classes8.dex */
    public static class GPUInfo {
        private String glExtensions;
        private String glRenderer;
        private String glVendor;
        private String glVersion;

        public String getGlRenderer() {
            return this.glRenderer;
        }

        public String getGlVendor() {
            return this.glVendor;
        }

        public String getGlVersion() {
            return this.glVersion;
        }

        public String getGlExtensions() {
            return this.glExtensions;
        }
    }

    /* loaded from: classes8.dex */
    public static class CPUInfo {
        private int coreNums;
        private int freq;
        private String model;

        public String getModel() {
            return this.model;
        }

        public int getFreq() {
            return this.freq;
        }

        public int getCoreNums() {
            return this.coreNums;
        }
    }

    /* loaded from: classes8.dex */
    public static class ScreenInfo {
        private int dpi;
        private int height;
        private int width;

        public int getDpi() {
            return this.dpi;
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }
    }

    /* loaded from: classes8.dex */
    public static class StorageInfo {
        private long appStorageSize;
        private long availableExternalSize;
        private long availableInternalSize;
        private long totalExternalSize;
        private long totalInternalSize;

        public long getAvailableExternalSize() {
            return this.availableExternalSize;
        }

        public long getTotalExternalSize() {
            return this.totalExternalSize;
        }

        public long getAvailableInternalSize() {
            return this.availableInternalSize;
        }

        public long getTotalInternalSize() {
            return this.totalInternalSize;
        }

        public long getAppStorageSize() {
            return this.appStorageSize;
        }
    }

    /* loaded from: classes8.dex */
    public static class MemoryInfo {
        private long availableSize;
        private long totalSize;

        public long getTotalSize() {
            return this.totalSize;
        }

        public long getAvailableSize() {
            return this.availableSize;
        }
    }

    public static String getSupportABIS() {
        return SupportABISCompat.getSupportABIS();
    }

    /* loaded from: classes8.dex */
    static class SupportABISCompat {
        static final BaseImpl IMPL;

        SupportABISCompat() {
        }

        public static String getSupportABIS() {
            return IMPL.getSupportABIS();
        }

        static {
            if (Build.VERSION.SDK_INT >= 21) {
                IMPL = new LolilopImpl();
            } else {
                IMPL = new BaseImpl();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes8.dex */
        public static class BaseImpl {
            private BaseImpl() {
            }

            public String getSupportABIS() {
                return Build.CPU_ABI + "_" + Build.CPU_ABI2;
            }
        }

        /* loaded from: classes8.dex */
        static class LolilopImpl extends BaseImpl {
            private LolilopImpl() {
                super();
            }

            @Override // com.bytedance.ttgame.sdk.module.utils.DeviceInfo.SupportABISCompat.BaseImpl
            public String getSupportABIS() {
                String[] strArr;
                String str = "";
                if (Build.SUPPORTED_ABIS == null) {
                    return "";
                }
                for (int i2 = 0; i2 < Build.SUPPORTED_ABIS.length; i2++) {
                    str = str + strArr[i2] + "_";
                }
                return str;
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class ThreadOOMInfo {
        private long vmPeak = -1;
        private long vmSize = -1;
        private long maxFdCount = -1;
        private long fdCount = -1;
        private int threadCount = -1;

        public long getVmPeak() {
            return this.vmPeak;
        }

        public long getVmSize() {
            return this.vmSize;
        }

        public long getMaxFdCount() {
            return this.maxFdCount;
        }

        public long getFdCount() {
            return this.fdCount;
        }

        public int getThreadCount() {
            return this.threadCount;
        }
    }

    public static ThreadOOMInfo getThreadOOMInfo() {
        ThreadOOMInfo threadOOMInfo = new ThreadOOMInfo();
        try {
            threadOOMInfo.vmPeak = getVmPeak();
            threadOOMInfo.vmSize = getVmSize();
            threadOOMInfo.fdCount = getFdCount();
            threadOOMInfo.threadCount = getThreadCount();
            threadOOMInfo.maxFdCount = getMaxFdCount();
            return threadOOMInfo;
        } catch (Exception unused) {
            return null;
        }
    }

    private static int getVmPeak() {
        FileInputStream fileInputStream;
        Throwable th;
        Exception e2;
        try {
            fileInputStream = new FileInputStream("/proc/" + Process.myPid() + "/status");
            try {
                try {
                    int parseFileForValue = parseFileForValue("VmPeak", fileInputStream);
                    closeSafety(fileInputStream);
                    return parseFileForValue;
                } catch (Exception e3) {
                    e2 = e3;
                    e2.printStackTrace();
                    closeSafety(fileInputStream);
                    return -1;
                }
            } catch (Throwable th2) {
                th = th2;
                closeSafety(fileInputStream);
                throw th;
            }
        } catch (Exception e4) {
            fileInputStream = null;
            e2 = e4;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            closeSafety(fileInputStream);
            throw th;
        }
    }

    private static int getVmSize() {
        FileInputStream fileInputStream;
        Throwable th;
        Exception e2;
        try {
            fileInputStream = new FileInputStream("/proc/" + Process.myPid() + "/status");
            try {
                try {
                    int parseFileForValue = parseFileForValue("VmSize", fileInputStream);
                    closeSafety(fileInputStream);
                    return parseFileForValue;
                } catch (Exception e3) {
                    e2 = e3;
                    e2.printStackTrace();
                    closeSafety(fileInputStream);
                    return -1;
                }
            } catch (Throwable th2) {
                th = th2;
                closeSafety(fileInputStream);
                throw th;
            }
        } catch (Exception e4) {
            fileInputStream = null;
            e2 = e4;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            closeSafety(fileInputStream);
            throw th;
        }
    }

    private static int getFdCount() {
        File[] listFiles;
        File file = new File("/proc/" + Process.myPid() + "/fd");
        if (!file.isDirectory() || (listFiles = file.listFiles()) == null) {
            return -1;
        }
        return listFiles.length;
    }

    private static int getThreadCount() {
        FileInputStream fileInputStream;
        Throwable th;
        Exception e2;
        try {
            fileInputStream = new FileInputStream("/proc/" + Process.myPid() + "/status");
            try {
                try {
                    int parseFileForValue = parseFileForValue("Threads", fileInputStream);
                    closeSafety(fileInputStream);
                    return parseFileForValue;
                } catch (Exception e3) {
                    e2 = e3;
                    e2.printStackTrace();
                    closeSafety(fileInputStream);
                    return -1;
                }
            } catch (Throwable th2) {
                th = th2;
                closeSafety(fileInputStream);
                throw th;
            }
        } catch (Exception e4) {
            fileInputStream = null;
            e2 = e4;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            closeSafety(fileInputStream);
            throw th;
        }
    }

    private static int getMaxFdCount() {
        FileInputStream fileInputStream;
        Throwable th;
        Exception e2;
        try {
            fileInputStream = new FileInputStream("/proc/" + Process.myPid() + "/limits");
            try {
                try {
                    int parseFileForValue = parseFileForValue("Max open files", fileInputStream);
                    closeSafety(fileInputStream);
                    return parseFileForValue;
                } catch (Exception e3) {
                    e2 = e3;
                    e2.printStackTrace();
                    closeSafety(fileInputStream);
                    return -1;
                }
            } catch (Throwable th2) {
                th = th2;
                closeSafety(fileInputStream);
                throw th;
            }
        } catch (Exception e4) {
            fileInputStream = null;
            e2 = e4;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            closeSafety(fileInputStream);
            throw th;
        }
    }

    public static boolean isRooted() {
        String str = Build.TAGS;
        if (str == null || !str.contains("test-keys")) {
            try {
                if (new File("/system/app/Superuser.apk").exists()) {
                    return true;
                }
            } catch (Exception unused) {
            }
            return canExecuteCommand("/system/xbin/which su") || canExecuteCommand("/system/bin/which su") || canExecuteCommand("which su") || canExecuteCommand("busybox which su");
        }
        return true;
    }

    private static boolean canExecuteCommand(String str) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(str);
            boolean z = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null;
            if (process != null) {
                process.destroy();
            }
            return z;
        } catch (Exception unused) {
            if (process != null) {
                process.destroy();
            }
            return false;
        } catch (Throwable th) {
            if (process != null) {
                process.destroy();
            }
            throw th;
        }
    }

    public static String getRomTotalSize(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return Formatter.formatFileSize(context, statFs.getBlockSize() * statFs.getBlockCount());
    }

    public static String getRomAvailableSize(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return Formatter.formatFileSize(context, statFs.getBlockSize() * statFs.getAvailableBlocks());
    }

    public static String getCarrier(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
    }

    public static String getNetworkType(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (1 == type) {
                    return "wifi";
                }
                if (type == 0) {
                    switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            return G3;
                        case 4:
                        case 7:
                        case 11:
                        default:
                            return G2;
                        case 13:
                            return G4;
                    }
                }
                return G2;
            }
            return "unknown";
        } catch (Throwable unused) {
            return G2;
        }
    }

    public static int getBattery(Context context) {
        return context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("level", -1);
    }

    public static String getSystemLanguage() {
        Configuration configuration = Resources.getSystem().getConfiguration();
        if (Build.VERSION.SDK_INT >= 24) {
            return configuration.getLocales().get(0).toString();
        }
        return configuration.locale.toString();
    }

    public static boolean isScopedStorageModel() {
        return Build.VERSION.SDK_INT >= 29 && !Environment.isExternalStorageLegacy();
    }
}
