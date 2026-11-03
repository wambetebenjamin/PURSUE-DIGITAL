package com.ttnet.org.chromium.base;

import com.facebook.internal.security.CertificateUtil;
import com.meituan.robust.Constants;
import java.util.Locale;
/* loaded from: classes9.dex */
public class Log {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static final String sDeprecatedTagPrefix = "cr.";
    private static final String sTagPrefix = "cr_";

    private static boolean isDebug() {
        return false;
    }

    private Log() {
    }

    private static String formatLog(String str, Throwable th, Object... objArr) {
        return objArr != null ? ((th != null || objArr.length <= 0) && objArr.length <= 1) ? str : String.format(Locale.US, str, objArr) : str;
    }

    public static String normalizeTag(String str) {
        if (str.startsWith(sTagPrefix)) {
            return str;
        }
        return sTagPrefix + str.substring(str.startsWith(sDeprecatedTagPrefix) ? 3 : 0, str.length());
    }

    private static String formatLogWithStack(String str, Throwable th, Object... objArr) {
        return Constants.ARRAY_TYPE + getCallOrigin() + "] " + formatLog(str, th, objArr);
    }

    public static boolean isLoggable(String str, int i2) {
        if (isDebug() || i2 > 4) {
            return android.util.Log.isLoggable(str, i2);
        }
        return false;
    }

    public static void v(String str, String str2, Object... objArr) {
        if (isDebug()) {
            Throwable throwableToLog = getThrowableToLog(objArr);
            String formatLogWithStack = formatLogWithStack(str2, throwableToLog, objArr);
            if (throwableToLog != null) {
                android.util.Log.v(normalizeTag(str), formatLogWithStack, throwableToLog);
            } else {
                android.util.Log.v(normalizeTag(str), formatLogWithStack);
            }
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (isDebug()) {
            Throwable throwableToLog = getThrowableToLog(objArr);
            String formatLogWithStack = formatLogWithStack(str2, throwableToLog, objArr);
            if (throwableToLog != null) {
                android.util.Log.d(normalizeTag(str), formatLogWithStack, throwableToLog);
            } else {
                android.util.Log.d(normalizeTag(str), formatLogWithStack);
            }
        }
    }

    public static void i(String str, String str2, Object... objArr) {
        Throwable throwableToLog = getThrowableToLog(objArr);
        String formatLog = formatLog(str2, throwableToLog, objArr);
        if (throwableToLog != null) {
            android.util.Log.i(normalizeTag(str), formatLog, throwableToLog);
        } else {
            android.util.Log.i(normalizeTag(str), formatLog);
        }
    }

    public static void w(String str, String str2, Object... objArr) {
        Throwable throwableToLog = getThrowableToLog(objArr);
        String formatLog = formatLog(str2, throwableToLog, objArr);
        if (throwableToLog != null) {
            android.util.Log.w(normalizeTag(str), formatLog, throwableToLog);
        } else {
            android.util.Log.w(normalizeTag(str), formatLog);
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        Throwable throwableToLog = getThrowableToLog(objArr);
        String formatLog = formatLog(str2, throwableToLog, objArr);
        if (throwableToLog != null) {
            android.util.Log.e(normalizeTag(str), formatLog, throwableToLog);
        } else {
            android.util.Log.e(normalizeTag(str), formatLog);
        }
    }

    public static void wtf(String str, String str2, Object... objArr) {
        Throwable throwableToLog = getThrowableToLog(objArr);
        String formatLog = formatLog(str2, throwableToLog, objArr);
        if (throwableToLog != null) {
            android.util.Log.wtf(normalizeTag(str), formatLog, throwableToLog);
        } else {
            android.util.Log.wtf(normalizeTag(str), formatLog);
        }
    }

    public static String getStackTraceString(Throwable th) {
        return android.util.Log.getStackTraceString(th);
    }

    private static Throwable getThrowableToLog(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return null;
        }
        Object obj = objArr[objArr.length - 1];
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        return null;
    }

    private static String getCallOrigin() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String name = Log.class.getName();
        int i2 = 0;
        while (true) {
            if (i2 >= stackTrace.length) {
                break;
            } else if (stackTrace[i2].getClassName().equals(name)) {
                i2 += 3;
                break;
            } else {
                i2++;
            }
        }
        return stackTrace[i2].getFileName() + CertificateUtil.DELIMITER + stackTrace[i2].getLineNumber();
    }
}
