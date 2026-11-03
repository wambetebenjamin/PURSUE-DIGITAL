package com.bytedance.apm.util;

import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/* compiled from: AppUtils.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static Boolean f1678a;
    public static boolean sIsMainProcess;
    public static String sProcessName;

    public static Application getApplication(Context context) {
        if (context == null) {
            return null;
        }
        if (!(context instanceof Application)) {
            context = context.getApplicationContext();
        }
        return (Application) context;
    }

    public static String getProcessName(int i2) {
        BufferedReader bufferedReader;
        if (!TextUtils.isEmpty(sProcessName)) {
            return sProcessName;
        }
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/" + i2 + "/cmdline"));
            try {
                String readLine = bufferedReader.readLine();
                if (!TextUtils.isEmpty(readLine)) {
                    readLine = readLine.trim();
                }
                sProcessName = readLine;
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return readLine;
            } catch (Throwable th) {
                th = th;
                try {
                    th.printStackTrace();
                    return null;
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
    }

    public static boolean isMainProcess(Context context) {
        String processName = getProcessName(Process.myPid());
        return (processName == null || !processName.contains(CertificateUtil.DELIMITER)) && processName != null && processName.equals(context.getPackageName());
    }

    public static boolean isDebug(Context context) {
        Boolean bool = f1678a;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (context == null) {
            return false;
        }
        try {
            Boolean valueOf = Boolean.valueOf((context.getApplicationInfo().flags & 2) != 0);
            f1678a = valueOf;
            return valueOf.booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }
}
