package com.bytedance.crash.util;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.crash.Global;
import com.facebook.internal.security.CertificateUtil;
import java.io.FileInputStream;
/* compiled from: App.java */
/* loaded from: classes2.dex */
public final class b {

    /* renamed from: b  reason: collision with root package name */
    private static String f7289b;

    public static String a() {
        if (TextUtils.isEmpty(f7289b)) {
            f7289b = c();
        }
        return f7289b;
    }

    public static void setCurProcessName(String str) {
        f7289b = str;
    }

    public static boolean isMainProcess(Context context, String str) {
        if (str == null || str.contains(CertificateUtil.DELIMITER)) {
            return false;
        }
        if (context == null) {
            context = Global.c();
        }
        if (context == null) {
            return false;
        }
        if (str.equals(context.getPackageName())) {
            return true;
        }
        return str.equals(context.getApplicationInfo().processName);
    }

    public static boolean b() {
        return isMainProcess(Global.c());
    }

    public static boolean isMainProcess(Context context) {
        return isMainProcess(context, a());
    }

    private static String c() {
        FileInputStream fileInputStream;
        int read;
        StringBuilder sb = new StringBuilder();
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream("/proc/self/cmdline");
        } catch (Exception unused) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[64];
            do {
                read = fileInputStream.read(bArr, 0, 64);
                for (int i2 = 0; i2 < read; i2++) {
                    char c2 = (char) bArr[i2];
                    if (c2 != 0 && c2 != '\n') {
                        sb.append(c2);
                    }
                    read = -1;
                    continue;
                    break;
                }
            } while (read > 0);
            k.a(fileInputStream);
        } catch (Exception unused2) {
            fileInputStream2 = fileInputStream;
            k.a(fileInputStream2);
            return sb.toString();
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            k.a(fileInputStream2);
            throw th;
        }
        return sb.toString();
    }

    public static String getCurProcessName(Context context) {
        if (!TextUtils.isEmpty(f7289b)) {
            return f7289b;
        }
        String c2 = c();
        f7289b = c2;
        return c2 == null ? "" : c2;
    }

    public static String getCurProcessName() {
        String str = f7289b;
        return str != null ? str : "";
    }
}
