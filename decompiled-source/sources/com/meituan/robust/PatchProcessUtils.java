package com.meituan.robust;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
/* loaded from: classes9.dex */
public class PatchProcessUtils {
    public static final String MESSAGE_PROCESS_SUFFIX = ":push";
    private static String sCurProcessName;

    public static boolean isMessageProcess(Context context) {
        String curProcessName = getCurProcessName(context);
        return curProcessName != null && curProcessName.endsWith(":push");
    }

    public static boolean isMainProcess(Context context) {
        String curProcessName = getCurProcessName(context);
        return (curProcessName == null || !curProcessName.contains(CertificateUtil.DELIMITER)) && curProcessName != null && curProcessName.equals(context.getPackageName());
    }

    public static String getCurProcessName(Context context) {
        String str = sCurProcessName;
        if (TextUtils.isEmpty(str)) {
            try {
                int myPid = Process.myPid();
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                    if (runningAppProcessInfo.pid == myPid) {
                        String str2 = runningAppProcessInfo.processName;
                        sCurProcessName = str2;
                        return str2;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            String curProcessNameFromProc = getCurProcessNameFromProc();
            sCurProcessName = curProcessNameFromProc;
            return curProcessNameFromProc;
        }
        return str;
    }

    private static String getCurProcessNameFromProc() {
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
