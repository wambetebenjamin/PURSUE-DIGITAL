package gsdk.impl.dynamic.DEFAULT;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
/* compiled from: RomUtils.java */
/* loaded from: classes10.dex */
public final class ap {

    /* renamed from: a  reason: collision with root package name */
    private static a f20104a;

    public static boolean a() {
        return "huawei".equals(e().f20105a);
    }

    public static boolean b() {
        return "vivo".equals(e().f20105a);
    }

    public static boolean c() {
        return "xiaomi".equals(e().f20105a);
    }

    public static boolean d() {
        return "oppo".equals(e().f20105a);
    }

    public static a e() {
        a aVar = f20104a;
        if (aVar != null) {
            return aVar;
        }
        f20104a = new a();
        String g2 = g();
        String f2 = f();
        if (!a(g2, f2, "huawei")) {
            if (!a(g2, f2, "vivo")) {
                if (!a(g2, f2, "xiaomi")) {
                    if (a(g2, f2, "oppo")) {
                        f20104a.f20105a = "oppo";
                        f20104a.f20106b = a("ro.build.version.opporom");
                        return f20104a;
                    }
                    f20104a.f20105a = f2;
                    f20104a.f20106b = a("");
                    return f20104a;
                }
                f20104a.f20105a = "xiaomi";
                f20104a.f20106b = a("ro.build.version.incremental");
                return f20104a;
            }
            f20104a.f20105a = "vivo";
            f20104a.f20106b = a("ro.vivo.os.build.display.id");
            return f20104a;
        }
        f20104a.f20105a = "huawei";
        String a2 = a("ro.build.version.emui");
        String[] split = a2.split("_");
        if (split.length > 1) {
            f20104a.f20106b = split[1];
        } else {
            f20104a.f20106b = a2;
        }
        return f20104a;
    }

    private static boolean a(String str, String str2, String... strArr) {
        for (String str3 : strArr) {
            if (str.contains(str3) || str2.contains(str3)) {
                return true;
            }
        }
        return false;
    }

    private static String f() {
        try {
            String str = Build.MANUFACTURER;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    private static String g() {
        try {
            String str = Build.BRAND;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    private static String a(String str) {
        String b2 = !TextUtils.isEmpty(str) ? b(str) : "";
        if (TextUtils.isEmpty(b2) || b2.equals("unknown")) {
            try {
                String str2 = Build.DISPLAY;
                if (!TextUtils.isEmpty(str2)) {
                    b2 = str2.toLowerCase();
                }
            } catch (Throwable unused) {
            }
        }
        return TextUtils.isEmpty(b2) ? "unknown" : b2;
    }

    private static String b(String str) {
        String c2 = c(str);
        if (TextUtils.isEmpty(c2)) {
            String d2 = d(str);
            return (TextUtils.isEmpty(d2) && Build.VERSION.SDK_INT < 28) ? e(str) : d2;
        }
        return c2;
    }

    private static String c(String str) {
        BufferedReader bufferedReader;
        String readLine;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
            } catch (IOException unused) {
                return "";
            }
        } catch (IOException unused2) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            readLine = bufferedReader.readLine();
        } catch (IOException unused3) {
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
                return "";
            }
            return "";
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
        if (readLine != null) {
            try {
                bufferedReader.close();
            } catch (IOException unused5) {
            }
            return readLine;
        }
        bufferedReader.close();
        return "";
    }

    private static String d(String str) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            return properties.getProperty(str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    private static String e(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    /* compiled from: RomUtils.java */
    /* loaded from: classes10.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private String f20105a;

        /* renamed from: b  reason: collision with root package name */
        private String f20106b;

        public String toString() {
            return "RomInfo{name=" + this.f20105a + ", version=" + this.f20106b + "}";
        }
    }
}
