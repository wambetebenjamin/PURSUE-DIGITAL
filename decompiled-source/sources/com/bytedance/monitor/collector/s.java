package com.bytedance.monitor.collector;

import android.text.TextUtils;
import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
/* compiled from: Util.java */
/* loaded from: classes2.dex */
public class s {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9646a = "Invalid Stack\n";

    /* renamed from: b  reason: collision with root package name */
    public static boolean f9647b = false;
    public static boolean enable = false;

    private s() {
    }

    public static String parseMessageKey(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return "unknown message";
        }
        try {
            String[] split = str.split(CertificateUtil.DELIMITER);
            String str3 = split.length == 2 ? split[1] : "";
            if (str.contains("{") && str.contains("}")) {
                str2 = str.split("\\{")[0];
                try {
                    str = str2 + str.split("\\}")[1];
                } catch (Throwable unused) {
                    return str2;
                }
            } else {
                str2 = str;
            }
            if (str.contains("@")) {
                String[] split2 = str.split("@");
                if (split2.length > 1) {
                    str = split2[0];
                }
            }
            if (str.contains("(") && str.contains(")") && !str.endsWith(" null")) {
                String[] split3 = str.split("\\(");
                if (split3.length > 1) {
                    str = split3[1];
                }
                str = str.replace(")", "");
            }
            if (str.startsWith(" ")) {
                str = str.replace(" ", "");
            }
            return str + str3;
        } catch (Throwable unused2) {
            return str;
        }
    }

    public static String stackToString(StackTraceElement[] stackTraceElementArr) {
        if (stackTraceElementArr == null) {
            return f9646a;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            i2++;
            sb.append("\tat " + stackTraceElement.getClassName()).append(c.a.DOT).append(stackTraceElement.getMethodName()).append("(").append(stackTraceElement.getFileName()).append(CertificateUtil.DELIMITER).append(stackTraceElement.getLineNumber()).append(")\n");
            if (i2 > 40) {
                break;
            }
        }
        return sb.toString();
    }

    public static String a(StackTraceElement[] stackTraceElementArr, String str) {
        if (stackTraceElementArr == null) {
            return f9646a;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        boolean z = false;
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            i2++;
            if (!z) {
                if (stackTraceElement.getMethodName().equals(str)) {
                    z = true;
                }
            } else {
                sb.append("at " + stackTraceElement.getClassName()).append(c.a.DOT).append(stackTraceElement.getMethodName()).append("(").append(stackTraceElement.getFileName()).append(CertificateUtil.DELIMITER).append(stackTraceElement.getLineNumber()).append(")\n");
                if (i2 > 40) {
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static String a(String str) {
        int indexOf;
        int indexOf2;
        return (TextUtils.isEmpty(str) || (indexOf = str.indexOf("at ") + 3) >= (indexOf2 = str.indexOf(40))) ? "unknownMethodName" : str.substring(indexOf, indexOf2);
    }

    public static String stackToStringWithESC(StackTraceElement[] stackTraceElementArr) {
        if (stackTraceElementArr == null) {
            return f9646a;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            i2++;
            sb.append("\\tat " + stackTraceElement.getClassName()).append(c.a.DOT).append(stackTraceElement.getMethodName()).append("(").append(stackTraceElement.getFileName()).append(CertificateUtil.DELIMITER).append(stackTraceElement.getLineNumber()).append(")\\n");
            if (i2 > 40) {
                break;
            }
        }
        return sb.toString();
    }
}
