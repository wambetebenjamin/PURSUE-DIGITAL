package com.bytedance.news.common.settings.internal;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
/* compiled from: ToolUtils.java */
/* loaded from: classes2.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    private static String f9822a;

    public static boolean isMainProcess(Context context) {
        String curProcessName = getCurProcessName(context);
        return (curProcessName == null || !curProcessName.contains(CertificateUtil.DELIMITER)) && curProcessName != null && curProcessName.equals(context.getPackageName());
    }

    public static String getCurProcessName(Context context) {
        String str = f9822a;
        if (TextUtils.isEmpty(str)) {
            try {
                int myPid = Process.myPid();
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                    if (runningAppProcessInfo.pid == myPid) {
                        String str2 = runningAppProcessInfo.processName;
                        f9822a = str2;
                        return str2;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            String a2 = a();
            f9822a = a2;
            return a2;
        }
        return str;
    }

    private static String a() {
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
}
