package com.bytedance.crash.util;

import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* compiled from: Device.java */
@Deprecated
/* loaded from: classes2.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f7295a = false;

    /* renamed from: b  reason: collision with root package name */
    private static int f7296b = -1;

    /* renamed from: c  reason: collision with root package name */
    private static final Pattern f7297c = Pattern.compile("^0-([\\d]+)$");

    /* renamed from: d  reason: collision with root package name */
    static boolean f7298d = false;

    private g() {
    }

    public static boolean isEmui(String str) {
        if (TextUtils.isEmpty(str)) {
            str = getEmuiInfo();
        }
        String lowerCase = str.toLowerCase(Locale.getDefault());
        if (TextUtils.isEmpty(str) || !(lowerCase.startsWith("emotionui") || lowerCase.startsWith("magicui"))) {
            return isHWDevice();
        }
        return true;
    }

    public static String getEmuiInfo() {
        return a("ro.build.version.emui");
    }

    public static boolean isHWDevice() {
        try {
            if (TextUtils.isEmpty(Build.BRAND) || !Build.BRAND.toLowerCase(Locale.getDefault()).startsWith("hua")) {
                if (TextUtils.isEmpty(Build.MANUFACTURER)) {
                    return false;
                }
                if (!Build.MANUFACTURER.toLowerCase(Locale.getDefault()).startsWith("hua")) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isMiui() {
        if (!f7295a) {
            try {
                if (Class.forName("miui.os.Build") != null) {
                    f7298d = true;
                }
            } catch (Exception unused) {
            }
            f7295a = true;
        }
        return f7298d;
    }

    public static boolean isFlyme() {
        return Build.DISPLAY.contains("Flyme") || Build.USER.equals("flyme");
    }

    private static String a(String str) {
        return ab.getSystemProperty(str);
    }

    public static int getCpuCoreNumbers() {
        int i2 = f7296b;
        if (i2 > 0) {
            return i2;
        }
        int b2 = b("/sys/devices/system/cpu/possible");
        if (b2 <= 0) {
            b2 = b("/sys/devices/system/cpu/present");
        }
        if (b2 <= 0) {
            b2 = d("/sys/devices/system/cpu/");
        }
        if (b2 <= 0) {
            b2 = Runtime.getRuntime().availableProcessors();
        }
        if (b2 <= 0) {
            b2 = 1;
        }
        f7296b = b2;
        return b2;
    }

    private static int b(String str) {
        BufferedReader bufferedReader;
        String readLine;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str)));
                try {
                    readLine = bufferedReader.readLine();
                } catch (Throwable unused) {
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                        return -1;
                    }
                    return -1;
                }
            } catch (Throwable unused2) {
            }
            if (readLine == null) {
                bufferedReader.close();
                return -1;
            }
            int c2 = c(readLine);
            try {
                bufferedReader.close();
            } catch (Throwable unused3) {
            }
            return c2;
        } catch (Throwable unused4) {
            return -1;
        }
    }

    private static int c(String str) {
        Matcher matcher = f7297c.matcher(str);
        if (matcher.matches()) {
            try {
                return Integer.parseInt(matcher.group(1)) + 1;
            } catch (NumberFormatException unused) {
            }
        }
        return -1;
    }

    private static int d(String str) {
        try {
            File[] listFiles = new File(str).listFiles(new FilenameFilter() { // from class: com.bytedance.crash.util.g.1

                /* renamed from: a  reason: collision with root package name */
                private final Pattern f7299a = Pattern.compile("^cpu[\\d]+$");

                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str2) {
                    return this.f7299a.matcher(str2).matches();
                }
            });
            if (listFiles == null || listFiles.length <= 0) {
                return -1;
            }
            return listFiles.length;
        } catch (Throwable unused) {
            return -1;
        }
    }
}
