package com.bytedance.bdinstall;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: Utils.java */
/* loaded from: classes.dex */
public class ax {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3757a = 13;

    /* renamed from: b  reason: collision with root package name */
    public static final int f3758b = 128;

    /* renamed from: c  reason: collision with root package name */
    private static String f3759c = null;

    /* renamed from: d  reason: collision with root package name */
    private static volatile Boolean f3760d = null;

    /* renamed from: e  reason: collision with root package name */
    private static final char[] f3761e = "0123456789ABCDEF".toCharArray();

    /* renamed from: f  reason: collision with root package name */
    private static final String f3762f = "&";

    /* renamed from: g  reason: collision with root package name */
    private static final String f3763g = "=";

    /* renamed from: h  reason: collision with root package name */
    private static final String f3764h = "ISO-8859-1";

    /* JADX WARN: Code restructure failed: missing block: B:20:0x004d, code lost:
        com.bytedance.bdinstall.ax.f3759c = r1.processName;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.String a(android.content.Context r3) {
        /*
            java.lang.String r0 = com.bytedance.bdinstall.ax.f3759c
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L9
            return r0
        L9:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L1c
            java.lang.String r0 = android.app.Application.getProcessName()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L1c
            com.bytedance.bdinstall.ax.f3759c = r0
            return r0
        L1c:
            java.lang.String r0 = a()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L29
            com.bytedance.bdinstall.ax.f3759c = r0
            return r0
        L29:
            int r0 = android.os.Process.myPid()     // Catch: java.lang.Exception -> L52
            java.lang.String r1 = "activity"
            java.lang.Object r3 = r3.getSystemService(r1)     // Catch: java.lang.Exception -> L52
            android.app.ActivityManager r3 = (android.app.ActivityManager) r3     // Catch: java.lang.Exception -> L52
            java.util.List r3 = r3.getRunningAppProcesses()     // Catch: java.lang.Exception -> L52
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Exception -> L52
        L3d:
            boolean r1 = r3.hasNext()     // Catch: java.lang.Exception -> L52
            if (r1 == 0) goto L56
            java.lang.Object r1 = r3.next()     // Catch: java.lang.Exception -> L52
            android.app.ActivityManager$RunningAppProcessInfo r1 = (android.app.ActivityManager.RunningAppProcessInfo) r1     // Catch: java.lang.Exception -> L52
            int r2 = r1.pid     // Catch: java.lang.Exception -> L52
            if (r2 != r0) goto L3d
            java.lang.String r3 = r1.processName     // Catch: java.lang.Exception -> L52
            com.bytedance.bdinstall.ax.f3759c = r3     // Catch: java.lang.Exception -> L52
            goto L56
        L52:
            r3 = move-exception
            r3.printStackTrace()
        L56:
            java.lang.String r3 = b()
            com.bytedance.bdinstall.ax.f3759c = r3
            boolean r3 = com.bytedance.bdinstall.u.a()
            if (r3 == 0) goto L7a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r0 = "getProcessName, "
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r0 = com.bytedance.bdinstall.ax.f3759c
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
            com.bytedance.bdinstall.u.a(r3)
        L7a:
            java.lang.String r3 = com.bytedance.bdinstall.ax.f3759c
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdinstall.ax.a(android.content.Context):java.lang.String");
    }

    public static String a() {
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread").getDeclaredMethod("currentProcessName", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            if (invoke instanceof String) {
                return (String) invoke;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean b(Context context) {
        if (f3760d == null) {
            Objects.requireNonNull(context, "context is null");
            f3760d = Boolean.valueOf(TextUtils.equals(a(context), context.getPackageName()));
        }
        return f3760d.booleanValue();
    }

    public static boolean a(String str) {
        int length = str != null ? str.length() : 0;
        if (length < 13 || length > 128) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if ((charAt < '0' || charAt > '9') && ((charAt < 'a' || charAt > 'f') && ((charAt < 'A' || charAt > 'F') && charAt != '-'))) {
                return false;
            }
        }
        return true;
    }

    private static String b() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/cmdline"), "iso-8859-1"));
        } catch (Throwable unused) {
            bufferedReader = null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                int read = bufferedReader.read();
                if (read <= 0) {
                    break;
                }
                sb.append((char) read);
            }
            String sb2 = sb.toString();
            try {
                bufferedReader.close();
            } catch (Exception unused2) {
            }
            return sb2;
        } catch (Throwable unused3) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception unused4) {
                }
            }
            return null;
        }
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null) {
            return;
        }
        try {
            synchronized (com.bytedance.bdinstall.loader.k.class) {
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    jSONObject.put(next, jSONObject2.opt(next));
                }
            }
        } catch (JSONException e2) {
            u.a(e2);
        }
    }

    static String a(HashMap<String, String> hashMap, String str) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String b2 = b(entry.getKey(), str);
            String value = entry.getValue();
            String b3 = value != null ? b(value, str) : "";
            if (sb.length() > 0) {
                sb.append(f3762f);
            }
            sb.append(b2);
            sb.append(f3763g);
            sb.append(b3);
        }
        return sb.toString();
    }

    private static String b(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public static boolean a(String str, String str2) {
        return (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) || (str != null && str.equals(str2));
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        jSONObject.put(str, str2);
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str) || str.equalsIgnoreCase("unknown") || str.equalsIgnoreCase("None")) {
            return true;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) != '0') {
                return false;
            }
        }
        return true;
    }

    public static boolean c(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str) || "unknown".equalsIgnoreCase(str) || "Null".equalsIgnoreCase(str)) {
            return false;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= str.length()) {
                z = true;
                break;
            } else if (str.charAt(i2) != '0') {
                break;
            } else {
                i2++;
            }
        }
        return !z;
    }

    public static void a(SharedPreferences sharedPreferences, String[] strArr) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        boolean z = false;
        for (String str : strArr) {
            if (sharedPreferences.contains(str)) {
                edit.remove(str);
                z = true;
            }
        }
        if (z) {
            edit.apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(boolean z) {
        f3760d = Boolean.valueOf(z);
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Context c(Context context) {
        return ((context instanceof Application) || context == null) ? context : context.getApplicationContext();
    }

    public static String a(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = bArr[i2] & 255;
            int i4 = i2 * 2;
            char[] cArr2 = f3761e;
            cArr[i4] = cArr2[i3 >>> 4];
            cArr[i4 + 1] = cArr2[i3 & 15];
        }
        return new String(cArr);
    }
}
