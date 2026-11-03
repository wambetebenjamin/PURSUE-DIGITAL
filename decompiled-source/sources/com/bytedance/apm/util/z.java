package com.bytedance.apm.util;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
/* compiled from: ProcessUtils.java */
/* loaded from: classes.dex */
public class z {

    /* renamed from: a  reason: collision with root package name */
    private static String f1717a;

    public static String getCurProcessName(Context context) {
        String str = f1717a;
        if (TextUtils.isEmpty(str)) {
            String a2 = a();
            f1717a = a2;
            return a2 == null ? "" : a2;
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
            StringBuilder sb = new StringBuilder(32);
            while (true) {
                int read = bufferedReader.read();
                if (read > 0) {
                    sb.append((char) read);
                } else {
                    String sb2 = sb.toString();
                    com.bytedance.monitor.util.c.safeClose(bufferedReader);
                    return sb2;
                }
            }
        } catch (Throwable unused2) {
            com.bytedance.monitor.util.c.safeClose(bufferedReader);
            return null;
        }
    }
}
