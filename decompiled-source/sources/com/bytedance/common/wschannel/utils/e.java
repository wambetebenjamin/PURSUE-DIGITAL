package com.bytedance.common.wschannel.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.o;
import com.facebook.internal.security.CertificateUtil;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: Utils.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static String f6008a;

    public static String a(byte[] bArr) {
        return a(bArr, " ");
    }

    public static String a(byte[] bArr, String str) {
        StringBuilder sb = new StringBuilder("");
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
            sb.append(str);
        }
        return sb.toString().toUpperCase().trim();
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b2 : digest) {
                String hexString = Integer.toHexString(b2 & 255);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a(long j2) {
        return DateFormat.getDateTimeInstance().format(new Date(j2));
    }

    public static boolean a(Context context) {
        String b2 = b(context);
        return (b2 == null || !b2.contains(CertificateUtil.DELIMITER)) && b2 != null && b2.equals(context.getPackageName());
    }

    public static boolean a(Context context, String str) {
        return (str == null || !str.contains(CertificateUtil.DELIMITER)) && str != null && str.equals(context.getPackageName());
    }

    public static String b(Context context) {
        String str = f6008a;
        if (o.isEmpty(str)) {
            String a2 = a();
            f6008a = a2;
            if (!o.isEmpty(a2)) {
                return f6008a;
            }
            String b2 = b();
            f6008a = b2;
            if (!o.isEmpty(b2)) {
                return f6008a;
            }
            String c2 = c();
            f6008a = c2;
            if (!o.isEmpty(c2)) {
                return f6008a;
            }
            String c3 = c(context);
            f6008a = c3;
            return c3;
        }
        return str;
    }

    private static String a() {
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                return Application.getProcessName();
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private static String b() {
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader()).getDeclaredMethod("currentProcessName", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            if (invoke instanceof String) {
                return (String) invoke;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static String c(Context context) {
        String str = null;
        if (context == null) {
            return null;
        }
        try {
            int myPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    if (Logger.debug()) {
                        Logger.d("Process", "processName = " + runningAppProcessInfo.processName);
                    }
                    str = runningAppProcessInfo.processName;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str;
    }

    private static String c() {
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
            if (Logger.debug()) {
                Logger.d("Process", "get processName = " + sb.toString());
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

    public static boolean b(String str) {
        return str != null && str.endsWith(":push");
    }

    public static boolean a(Context context, Class<? extends Service> cls) {
        if (context != null && cls != null) {
            try {
                for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
                    if (runningServiceInfo != null && runningServiceInfo.service != null && TextUtils.equals(cls.getName(), runningServiceInfo.service.getClassName())) {
                        return true;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static JSONObject a(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey())) {
                    try {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return jSONObject;
    }

    public static Map<String, String> a(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject == null) {
            return hashMap;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (!TextUtils.isEmpty(next)) {
                Object opt = jSONObject.opt(next);
                hashMap.put(next, opt == null ? null : opt.toString());
            }
        }
        return hashMap;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0032, code lost:
        if (r2 == null) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.lang.Throwable r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L4
            return r0
        L4:
            java.io.StringWriter r1 = new java.io.StringWriter     // Catch: java.lang.Throwable -> L2d
            r1.<init>()     // Catch: java.lang.Throwable -> L2d
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch: java.lang.Throwable -> L2d
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L2d
            r3.printStackTrace(r2)     // Catch: java.lang.Throwable -> L2b
            java.lang.Throwable r3 = r3.getCause()     // Catch: java.lang.Throwable -> L2b
            if (r3 == 0) goto L23
            r3.printStackTrace(r2)     // Catch: java.lang.Throwable -> L2b
            java.lang.Throwable r3 = r3.getCause()     // Catch: java.lang.Throwable -> L2b
            if (r3 == 0) goto L23
            r3.printStackTrace(r2)     // Catch: java.lang.Throwable -> L2b
        L23:
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> L2b
        L27:
            r2.close()
            goto L35
        L2b:
            r3 = move-exception
            goto L2f
        L2d:
            r3 = move-exception
            r2 = r0
        L2f:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L36
            if (r2 == 0) goto L35
            goto L27
        L35:
            return r0
        L36:
            r3 = move-exception
            if (r2 == 0) goto L3c
            r2.close()
        L3c:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.wschannel.utils.e.a(java.lang.Throwable):java.lang.String");
    }
}
