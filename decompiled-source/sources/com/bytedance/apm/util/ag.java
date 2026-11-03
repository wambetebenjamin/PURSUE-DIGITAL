package com.bytedance.apm.util;

import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
/* compiled from: StackUtils.java */
/* loaded from: classes.dex */
public class ag {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1671a = 40;

    public static String getThrowableStack(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        Throwable cause = th.getCause();
        if (cause != null) {
            cause.printStackTrace(printWriter);
            Throwable cause2 = cause.getCause();
            if (cause2 != null) {
                cause2.printStackTrace(printWriter);
            }
        }
        String stringWriter2 = stringWriter.toString();
        printWriter.close();
        return stringWriter2.length() > 1024 ? stringWriter2.substring(0, 1024) : stringWriter2;
    }

    public static String getStack(Throwable th) {
        return getStackLimit(th, 30);
    }

    public static String getStackLimit(Throwable th, int i2) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        for (StackTraceElement stackTraceElement : stackTrace) {
            i3++;
            sb.append("\tat " + stackTraceElement.getClassName()).append(c.a.DOT).append(stackTraceElement.getMethodName()).append("(").append(stackTraceElement.getFileName()).append(CertificateUtil.DELIMITER).append(stackTraceElement.getLineNumber()).append(")\n");
            if (i3 > i2) {
                break;
            }
        }
        return sb.toString();
    }

    public static String getStackTraces(StackTraceElement[] stackTraceElementArr) {
        if (stackTraceElementArr == null || stackTraceElementArr.length <= 0) {
            throw new IllegalArgumentException("stackTraceElements must not be null or empty");
        }
        StringBuilder sb = new StringBuilder(stackTraceElementArr.length * 30);
        for (int i2 = 0; i2 < stackTraceElementArr.length && i2 <= 40; i2++) {
            if ((i2 != 0 || !"getThreadStackTrace".equals(stackTraceElementArr[0].getMethodName())) && (i2 != 1 || !"getStackTrace".equals(stackTraceElementArr[1].getMethodName()))) {
                sb.append("\tat " + stackTraceElementArr[i2].getClassName()).append(c.a.DOT).append(stackTraceElementArr[i2].getMethodName()).append("(").append(stackTraceElementArr[i2].getFileName()).append(CertificateUtil.DELIMITER).append(stackTraceElementArr[i2].getLineNumber()).append(")\n");
            }
        }
        return sb.toString();
    }
}
