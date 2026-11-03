package com.bytedance.frameworks.gpm.util;

import android.os.Build;
import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CPUUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final FileFilter f8544a = new FileFilter() { // from class: com.bytedance.frameworks.gpm.util.CPUUtil.1
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

    /* renamed from: b  reason: collision with root package name */
    private static b f8545b = new b();

    private static native String getCPUModelFromProperty();

    public static int getCPUMaxFreq() {
        int b2 = b();
        return b2 == 0 ? a() : b2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0020, code lost:
        r1 = com.bytedance.frameworks.gpm.util.c.a(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int a() {
        /*
            r0 = 0
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L28
            java.lang.String r3 = "/proc/cpuinfo"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L28
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L25
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L25
        Le:
            java.lang.String r0 = r3.readLine()     // Catch: java.lang.Throwable -> L26
            if (r0 == 0) goto L21
            java.lang.String r4 = "cpu MHz"
            boolean r4 = r0.contains(r4)     // Catch: java.lang.Throwable -> L26
            if (r4 == 0) goto Le
            int r0 = com.bytedance.frameworks.gpm.util.c.a(r0)     // Catch: java.lang.Throwable -> L26
            r1 = r0
        L21:
            com.bytedance.frameworks.gpm.util.c.a(r2)
            goto L2c
        L25:
            r3 = r0
        L26:
            r0 = r2
            goto L29
        L28:
            r3 = r0
        L29:
            com.bytedance.frameworks.gpm.util.c.a(r0)
        L2c:
            com.bytedance.frameworks.gpm.util.c.a(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.gpm.util.CPUUtil.a():int");
    }

    private static int b() {
        BufferedReader bufferedReader;
        int i2 = 0;
        for (int i3 = 0; i3 < getCPUCores(); i3++) {
            FileReader fileReader = null;
            try {
                FileReader fileReader2 = new FileReader("/sys/devices/system/cpu/cpu" + i3 + "/cpufreq/cpuinfo_max_freq");
                try {
                    bufferedReader = new BufferedReader(fileReader2);
                } catch (Throwable unused) {
                    bufferedReader = null;
                }
                try {
                    String readLine = bufferedReader.readLine();
                    if (!TextUtils.isEmpty(readLine)) {
                        i2 = Math.max(i2, c.a(readLine) / 1000);
                    }
                    c.a(fileReader2);
                } catch (Throwable unused2) {
                    fileReader = fileReader2;
                    c.a(fileReader);
                    c.a(bufferedReader);
                }
            } catch (Throwable unused3) {
                bufferedReader = null;
            }
            c.a(bufferedReader);
        }
        return i2;
    }

    public static int getCPUCores() {
        if (Build.VERSION.SDK_INT <= 10) {
            return 1;
        }
        int a2 = a("/sys/devices/system/cpu/possible");
        if (a2 == 0) {
            a2 = a("/sys/devices/system/cpu/present");
        }
        return a2 == 0 ? c() : a2;
    }

    private static int a(String str) {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        FileReader fileReader2 = null;
        int i2 = 0;
        try {
            fileReader = new FileReader(str);
            try {
                bufferedReader = new BufferedReader(fileReader);
            } catch (IOException unused) {
                bufferedReader = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
            try {
                String readLine = bufferedReader.readLine();
                if (!TextUtils.isEmpty(readLine) && readLine.startsWith("0-")) {
                    i2 = c.a(readLine.substring(2)) + 1;
                }
                c.a(fileReader);
            } catch (IOException unused2) {
                fileReader2 = fileReader;
                c.a(fileReader2);
                c.a(bufferedReader);
                return i2;
            } catch (Throwable th3) {
                th = th3;
                c.a(fileReader);
                c.a(bufferedReader);
                throw th;
            }
        } catch (IOException unused3) {
            bufferedReader = null;
        } catch (Throwable th4) {
            fileReader = null;
            th = th4;
            bufferedReader = null;
        }
        c.a(bufferedReader);
        return i2;
    }

    private static int c() {
        File[] listFiles = new File("/sys/devices/system/cpu/").listFiles(f8544a);
        if (listFiles != null) {
            return listFiles.length;
        }
        return 0;
    }

    public static String getCPUModel() {
        BufferedReader bufferedReader;
        String str = "unknown";
        boolean z = false;
        FileReader fileReader = null;
        try {
            FileReader fileReader2 = new FileReader("/proc/cpuinfo");
            try {
                bufferedReader = new BufferedReader(fileReader2);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            if (readLine.contains("Hardware") && readLine.contains(CertificateUtil.DELIMITER)) {
                                str = readLine.substring(readLine.indexOf(58) + 1).trim();
                                z = true;
                                break;
                            }
                        } else {
                            break;
                        }
                    } catch (IOException unused) {
                        fileReader = fileReader2;
                        c.a(fileReader);
                        c.a(bufferedReader);
                        return str;
                    } catch (Throwable th) {
                        th = th;
                        fileReader = fileReader2;
                        c.a(fileReader);
                        c.a(bufferedReader);
                        throw th;
                    }
                }
                if (!z) {
                    str = getCPUModelFromProperty();
                }
                c.a(fileReader2);
            } catch (IOException unused2) {
                bufferedReader = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (IOException unused3) {
            bufferedReader = null;
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
        }
        c.a(bufferedReader);
        return str;
    }

    private static String d() {
        if (Build.VERSION.SDK_INT >= 21) {
            return Build.SUPPORTED_ABIS[0];
        }
        return Build.CPU_ABI;
    }

    public static boolean supportX86() {
        String d2 = d();
        return !TextUtils.isEmpty(d2) && d2.toLowerCase().contains("x86");
    }

    public static String getCPUUsage() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("usage", (int) (f8545b.a() * 100.0d));
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }
}
