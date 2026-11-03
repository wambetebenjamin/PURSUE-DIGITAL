package com.bytedance.crash.general;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.media.session.PlaybackStateCompat;
import android.system.Os;
import android.system.OsConstants;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.bytedance.crash.Global;
import com.bytedance.crash.util.k;
import com.bytedance.crash.util.s;
import com.bytedance.ttgame.module.webview.api.WebViewConfig;
import java.io.BufferedReader;
import java.io.FileReader;
/* compiled from: HardwareInfoHelper.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6762a = "unknown";

    /* renamed from: b  reason: collision with root package name */
    private static final long f6763b = 100;

    public static long a() {
        return 1000 / i();
    }

    private static long i() {
        long j2 = -1;
        if (Build.VERSION.SDK_INT >= 21) {
            j2 = Os.sysconf(OsConstants._SC_CLK_TCK);
        } else {
            try {
                int b2 = s.b("libcore.io.OsConstants", "_SC_CLK_TCK");
                Object c2 = s.c("libcore.io.Libcore", "os");
                if (c2 != null) {
                    j2 = ((Long) s.a("libcore.io.Os", "sysconf", (Class<?>[]) new Class[]{Integer.TYPE}).invoke(c2, Integer.valueOf(b2))).longValue();
                }
            } catch (Throwable unused) {
            }
        }
        if (j2 > 0) {
            return j2;
        }
        return 100L;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0047 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String b() {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L36
            r2 = 21
            if (r1 < r2) goto L2d
            java.lang.String[] r1 = android.os.Build.SUPPORTED_ABIS     // Catch: java.lang.Exception -> L36
            int r2 = r1.length     // Catch: java.lang.Exception -> L36
            if (r2 <= 0) goto L3d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L36
            r2.<init>()     // Catch: java.lang.Exception -> L36
            r0 = 0
        L16:
            int r3 = r1.length     // Catch: java.lang.Exception -> L2b
            if (r0 >= r3) goto L3c
            r3 = r1[r0]     // Catch: java.lang.Exception -> L2b
            r2.append(r3)     // Catch: java.lang.Exception -> L2b
            int r3 = r1.length     // Catch: java.lang.Exception -> L2b
            int r3 = r3 + (-1)
            if (r0 == r3) goto L28
            java.lang.String r3 = ", "
            r2.append(r3)     // Catch: java.lang.Exception -> L2b
        L28:
            int r0 = r0 + 1
            goto L16
        L2b:
            r0 = move-exception
            goto L39
        L2d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L36
            java.lang.String r2 = android.os.Build.CPU_ABI     // Catch: java.lang.Exception -> L36
            r1.<init>(r2)     // Catch: java.lang.Exception -> L36
            r0 = r1
            goto L3d
        L36:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L39:
            com.bytedance.crash.util.y.w(r0)
        L3c:
            r0 = r2
        L3d:
            java.lang.String r0 = r0.toString()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L49
            java.lang.String r0 = "unknown"
        L49:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.crash.general.b.b():java.lang.String");
    }

    public static String c() {
        return TextUtils.isEmpty(Build.HARDWARE) ? "unknown" : Build.HARDWARE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0034, code lost:
        r0 = r1[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0037, code lost:
        r2.close();
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003e, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003f, code lost:
        com.bytedance.crash.util.y.e(r1);
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0073 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0084 A[Catch: IOException -> 0x0080, TRY_LEAVE, TryCatch #6 {IOException -> 0x0080, blocks: (B:51:0x007c, B:55:0x0084), top: B:62:0x007c }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x007c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String d() {
        /*
            java.lang.String r0 = ":"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L59
            java.lang.String r3 = "/proc/cpuinfo"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L59
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L52
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L52
        Lf:
            java.lang.String r1 = r3.readLine()     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            if (r1 == 0) goto L43
            boolean r4 = r1.contains(r0)     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            if (r4 == 0) goto Lf
            java.lang.String[] r1 = r1.split(r0)     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            r4 = 0
            r5 = r1[r4]     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            java.lang.String r6 = "Hardware"
            boolean r5 = r5.contains(r6)     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            if (r5 != 0) goto L34
            r4 = r1[r4]     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            java.lang.String r5 = "model name"
            boolean r4 = r4.contains(r5)     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            if (r4 == 0) goto Lf
        L34:
            r0 = 1
            r0 = r1[r0]     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            r2.close()     // Catch: java.io.IOException -> L3e
            r3.close()     // Catch: java.io.IOException -> L3e
            goto L42
        L3e:
            r1 = move-exception
            com.bytedance.crash.util.y.e(r1)
        L42:
            return r0
        L43:
            r2.close()     // Catch: java.io.IOException -> L64
            r3.close()     // Catch: java.io.IOException -> L64
            goto L6f
        L4a:
            r0 = move-exception
            goto L50
        L4c:
            r0 = move-exception
            goto L54
        L4e:
            r0 = move-exception
            r3 = r1
        L50:
            r1 = r2
            goto L7a
        L52:
            r0 = move-exception
            r3 = r1
        L54:
            r1 = r2
            goto L5b
        L56:
            r0 = move-exception
            r3 = r1
            goto L7a
        L59:
            r0 = move-exception
            r3 = r1
        L5b:
            com.bytedance.crash.util.y.e(r0)     // Catch: java.lang.Throwable -> L79
            if (r1 == 0) goto L66
            r1.close()     // Catch: java.io.IOException -> L64
            goto L66
        L64:
            r0 = move-exception
            goto L6c
        L66:
            if (r3 == 0) goto L6f
            r3.close()     // Catch: java.io.IOException -> L64
            goto L6f
        L6c:
            com.bytedance.crash.util.y.e(r0)
        L6f:
            java.lang.String r0 = android.os.Build.BOARD
            if (r0 != 0) goto L76
            java.lang.String r0 = "unknown"
            goto L78
        L76:
            java.lang.String r0 = android.os.Build.BOARD
        L78:
            return r0
        L79:
            r0 = move-exception
        L7a:
            if (r1 == 0) goto L82
            r1.close()     // Catch: java.io.IOException -> L80
            goto L82
        L80:
            r1 = move-exception
            goto L88
        L82:
            if (r3 == 0) goto L8b
            r3.close()     // Catch: java.io.IOException -> L80
            goto L8b
        L88:
            com.bytedance.crash.util.y.e(r1)
        L8b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.crash.general.b.d():java.lang.String");
    }

    public static String e() {
        return TextUtils.isEmpty(Build.MANUFACTURER) ? "unknown" : Build.MANUFACTURER;
    }

    public static String f() {
        return TextUtils.isEmpty(Build.BRAND) ? "unknown" : Build.BRAND;
    }

    public static String g() {
        String str = Build.BRAND;
        String str2 = Build.MODEL;
        return TextUtils.isEmpty(str2) ? str == null ? "unknown" : str : (str == null || str2.contains(str)) ? str2 : str + ' ' + str2;
    }

    public static String a(DisplayMetrics displayMetrics) {
        return displayMetrics.heightPixels + WebViewConfig.LOCATION_X + displayMetrics.widthPixels;
    }

    public static int b(DisplayMetrics displayMetrics) {
        return displayMetrics.densityDpi;
    }

    public static String c(DisplayMetrics displayMetrics) {
        int i2 = displayMetrics.densityDpi;
        return i2 != 120 ? i2 != 240 ? i2 != 320 ? "mdpi" : "xhdpi" : "hdpi" : "ldpi";
    }

    private static long j() {
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/proc/meminfo"));
            try {
                String readLine = bufferedReader2.readLine();
                if (readLine == null || !readLine.startsWith("MemTotal:")) {
                    k.a(bufferedReader2);
                    return 0L;
                }
                long parseLong = Long.parseLong(readLine.substring(9).trim().split(" ")[0]) * 1024;
                k.a(bufferedReader2);
                return parseLong;
            } catch (Throwable unused) {
                bufferedReader = bufferedReader2;
                k.a(bufferedReader);
                return 0L;
            }
        } catch (Throwable unused2) {
        }
    }

    private static long k() {
        Context c2 = Global.c();
        if (c2 != null) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) c2.getSystemService("activity")).getMemoryInfo(memoryInfo);
            return memoryInfo.totalMem;
        }
        return 0L;
    }

    static String a(long j2) {
        if (j2 <= 0) {
            return "Invalid";
        }
        long[] jArr = {256, 512, 1024, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, 3072, 4096, 5120, 6144, PlaybackStateCompat.ACTION_PLAY_FROM_URI, 10240, 12288, 16384, 24576, PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID, 49152, PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH, PlaybackStateCompat.ACTION_PREPARE_FROM_URI, PlaybackStateCompat.ACTION_SET_REPEAT_MODE, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED, 1048576};
        String[] strArr = {"256MB", "512MB", "1GB", "2GB", "3GB", "4GB", "5GB", "6GB", "8GB", "10GB", "12GB", "16GB", "24GB", "32GB", "48GB", "64GB", "128GB", "256GB", "512GB", "1TB"};
        for (int i2 = 0; i2 < 20; i2++) {
            if (j2 < jArr[i2] * 1024 * 1024) {
                return strArr[i2];
            }
        }
        return "1TB+";
    }

    public static String h() {
        long j2 = j();
        if (j2 == 0) {
            j2 = k();
        }
        return a(j2);
    }
}
