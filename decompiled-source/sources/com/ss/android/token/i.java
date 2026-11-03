package com.ss.android.token;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* compiled from: TokenUtils.java */
/* loaded from: classes9.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static String f18586a;

    public static String getTopDomain(String str) {
        String[] split;
        String str2 = "";
        try {
            str2 = new URI(str).getHost().toLowerCase();
            Matcher matcher = Pattern.compile("[^\\.]+(\\.com|\\.net|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.app|\\.edu)").matcher(str2);
            if (matcher.find()) {
                return matcher.group();
            }
        } catch (Exception e2) {
            e.monitorError(e2);
        }
        try {
            if (TextUtils.isEmpty(str2) || (split = str2.split("\\.")) == null || split.length <= 1) {
                return null;
            }
            return split[split.length - 2] + c.a.DOT + split[split.length - 1];
        } catch (Exception e3) {
            e.monitorError(e3);
            return null;
        }
    }

    public static boolean isInDomainList(String str, Set<String> set) {
        if (!TextUtils.isEmpty(str) && set != null && !set.isEmpty()) {
            try {
                String lowerCase = new URI(str).getHost().toLowerCase();
                if (TextUtils.isEmpty(lowerCase)) {
                    return false;
                }
                Iterator<String> it = set.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (!TextUtils.isEmpty(next)) {
                        if (lowerCase.equals(next)) {
                            return true;
                        }
                        if (!next.startsWith(c.a.DOT)) {
                            next = c.a.DOT + next;
                        }
                        if (lowerCase.endsWith(next)) {
                            return true;
                        }
                    }
                }
            } catch (Exception e2) {
                e.monitorError(e2);
            }
        }
        return false;
    }

    public static boolean isMainProcess(Context context) {
        String curProcessName = getCurProcessName(context);
        if (TextUtils.isEmpty(curProcessName)) {
            return false;
        }
        try {
            return curProcessName.equals(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).processName);
        } catch (Throwable th) {
            d.a("Process", "isMainProcess exception" + Log.getStackTraceString(th));
            return !curProcessName.contains(CertificateUtil.DELIMITER) && curProcessName.equals(context.getPackageName());
        }
    }

    public static String getCurProcessName(Context context) {
        if (!TextUtils.isEmpty(f18586a)) {
            return f18586a;
        }
        f18586a = b();
        d.a("Process", "getCurProcessName: " + f18586a);
        if (!TextUtils.isEmpty(f18586a)) {
            return f18586a;
        }
        f18586a = c();
        d.a("Process", "getCurrentProcessNameByActivityThread: " + f18586a);
        if (!TextUtils.isEmpty(f18586a)) {
            return f18586a;
        }
        f18586a = a();
        d.a("Process", "getCurProcessNameFromProc: " + f18586a);
        if (!TextUtils.isEmpty(f18586a)) {
            return f18586a;
        }
        f18586a = a(context);
        d.a("Process", "getCurrentProcessNameByActivityManager: " + f18586a);
        return f18586a;
    }

    private static String b() {
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

    private static String c() {
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

    private static String a() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/cmdline"), "iso-8859-1"));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    int read = bufferedReader.read();
                    if (read <= 0) {
                        break;
                    }
                    sb.append((char) read);
                }
                d.a("Process", "get processName = " + sb.toString());
                String sb2 = sb.toString();
                try {
                    bufferedReader.close();
                } catch (Exception e2) {
                    e.monitorError(e2);
                }
                return sb2;
            } catch (Throwable th) {
                th = th;
                try {
                    e.monitorError(th);
                    return null;
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e3) {
                            e.monitorError(e3);
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
    }

    private static String a(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (context == null) {
            return null;
        }
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return null;
    }
}
