package com.bytedance.apm6.util.proc;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.bytedance.apm6.util.e;
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
/* compiled from: CommonProcUtil.java */
/* loaded from: classes.dex */
public class a {
    public static final int BUFFER_SIZE = 8192;
    public static final String CACHE = "cache";
    public static final long DEFAULT_CLOCK_TICKS_PER_SECOND = 100;
    public static final long KB = 1024;
    public static final String MEMORY_INFO_PATH = "/proc/meminfo";
    public static final String OUTSIDE_STORAGE = "/Android/data/";
    public static final String PROC_STAT_FILE = "/proc/stat";

    /* renamed from: a  reason: collision with root package name */
    private static final int f2410a = -1;

    /* renamed from: b  reason: collision with root package name */
    private static long f2411b = -1;

    /* renamed from: c  reason: collision with root package name */
    private static int f2412c;

    public static boolean isProcStatCanRead() {
        int i2 = f2412c;
        if (i2 != 0) {
            return i2 == 1;
        }
        File file = new File("/proc/stat");
        if (file.exists() && file.canRead()) {
            f2412c = 1;
            return true;
        }
        f2412c = 2;
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
                e.safeClose(bufferedReader2);
                return parseLong;
            } catch (Throwable unused) {
                bufferedReader = bufferedReader2;
                e.safeClose(bufferedReader);
                return -1L;
            }
        } catch (Throwable unused2) {
        }
    }

    public static long getAppCPUTime() {
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/stat")), 1000);
            try {
                String readLine = bufferedReader2.readLine();
                bufferedReader2.close();
                String[] split = readLine.split(" ");
                long parseLong = Long.parseLong(split[13]) + Long.parseLong(split[14]);
                e.safeClose(bufferedReader2);
                return parseLong;
            } catch (Throwable unused) {
                bufferedReader = bufferedReader2;
                e.safeClose(bufferedReader);
                return -1L;
            }
        } catch (Throwable unused2) {
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
                e.safeClose(bufferedReader2);
                return parseLong;
            } catch (Exception unused) {
                bufferedReader = bufferedReader2;
                e.safeClose(bufferedReader);
                return -1L;
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
                e.safeClose(bufferedReader);
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
            e.safeClose(bufferedReader);
            return parseLong;
        } catch (Exception unused2) {
            bufferedReader2 = bufferedReader;
            e.safeClose(bufferedReader2);
            return -1L;
        } catch (Throwable th3) {
            th = th3;
            e.safeClose(bufferedReader);
            throw th;
        }
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
                    e.safeClose(bufferedReader2);
                    return j2;
                } catch (Throwable th2) {
                    th = th2;
                    e.safeClose(bufferedReader);
                    throw th;
                }
            }
            bufferedReader.close();
            j2 = Long.parseLong(str.split(" ")[0].trim());
            e.safeClose(bufferedReader);
        } catch (Exception unused2) {
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
        }
        return j2;
    }
}
