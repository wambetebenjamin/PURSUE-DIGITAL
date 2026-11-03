package com.bytedance.common.utility;

import android.util.Log;
import com.bytedance.librarian.c;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
/* loaded from: classes2.dex */
public class Logger {
    private static final String TAG = "Logger";
    private static int mLevel = 4;
    private static b sLogWriter = a.a();

    public static void setLogLevel(int i2) {
        mLevel = i2;
    }

    public static int getLogLevel() {
        return mLevel;
    }

    public static boolean debug() {
        return mLevel <= 3;
    }

    public static void v(String str) {
        v(TAG, str);
    }

    public static void v(String str, String str2) {
        if (str2 != null && sLogWriter.isLoggable(2)) {
            sLogWriter.logV(str, str2);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (!(str2 == null && th == null) && sLogWriter.isLoggable(2)) {
            sLogWriter.logV(str, str2, th);
        }
    }

    public static void d(String str) {
        d(TAG, str);
    }

    public static void d(String str, String str2) {
        if (str2 != null && sLogWriter.isLoggable(3)) {
            sLogWriter.logD(str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (!(str2 == null && th == null) && sLogWriter.isLoggable(3)) {
            sLogWriter.logD(str, str2, th);
        }
    }

    public static void i(String str) {
        i(TAG, str);
    }

    public static void i(String str, String str2) {
        if (str2 != null && sLogWriter.isLoggable(4)) {
            sLogWriter.logI(str, str2);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (!(str2 == null && th == null) && sLogWriter.isLoggable(4)) {
            sLogWriter.logI(str, str2, th);
        }
    }

    public static void w(String str) {
        w(TAG, str);
    }

    public static void w(String str, String str2) {
        if (str2 != null && sLogWriter.isLoggable(5)) {
            sLogWriter.logW(str, str2);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (!(str2 == null && th == null) && sLogWriter.isLoggable(5)) {
            sLogWriter.logW(str, str2, th);
        }
    }

    public static void e(String str) {
        e(TAG, str);
    }

    public static void e(String str, String str2) {
        if (str2 != null && sLogWriter.isLoggable(6)) {
            sLogWriter.logE(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (!(str2 == null && th == null) && sLogWriter.isLoggable(6)) {
            sLogWriter.logE(str, str2, th);
        }
    }

    @Deprecated
    public static void k(String str) {
        k(TAG, str);
    }

    @Deprecated
    public static void k(String str, String str2) {
        if (sLogWriter.isLoggable(3)) {
            sLogWriter.logK(str, str2);
        }
    }

    public static void st(String str, int i2) {
        try {
            throw new Exception();
        } catch (Exception e2) {
            StackTraceElement[] stackTrace = e2.getStackTrace();
            StringBuilder sb = new StringBuilder();
            for (int i3 = 1; i3 < Math.min(i2, stackTrace.length); i3++) {
                if (i3 > 1) {
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                }
                sb.append(getSimpleClassName(stackTrace[i3].getClassName()));
                sb.append(c.a.DOT);
                sb.append(stackTrace[i3].getMethodName());
            }
            v(str, sb.toString());
        }
    }

    private static String getSimpleClassName(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf < 0 ? str : str.substring(lastIndexOf + 1);
    }

    public static void throwException(Throwable th) {
        if (th == null) {
            return;
        }
        th.printStackTrace();
        if (debug()) {
            throw new RuntimeException("Error! Now in debug, we alert to you to correct it !", th);
        }
    }

    public static void alertErrorInfo(String str) {
        if (debug()) {
            throw new IllegalStateException(str);
        }
    }

    public static void registerLogHandler(b bVar) {
        sLogWriter = bVar;
    }

    /* loaded from: classes2.dex */
    public static abstract class b {
        public void logD(String str, String str2) {
        }

        public void logD(String str, String str2, Throwable th) {
        }

        public void logE(String str, String str2) {
        }

        public void logE(String str, String str2, Throwable th) {
        }

        public void logI(String str, String str2) {
        }

        public void logI(String str, String str2, Throwable th) {
        }

        public void logK(String str, String str2) {
        }

        public void logV(String str, String str2) {
        }

        public void logV(String str, String str2, Throwable th) {
        }

        public void logW(String str, String str2) {
        }

        public void logW(String str, String str2, Throwable th) {
        }

        public boolean isLoggable(int i2) {
            return Logger.getLogLevel() <= i2;
        }
    }

    /* loaded from: classes2.dex */
    private static final class a extends b {
        private a() {
        }

        static a a() {
            return C0072a.f5176a;
        }

        @Override // com.bytedance.common.utility.Logger.b
        public void logV(String str, String str2) {
            Log.v(str, str2);
        }

        @Override // com.bytedance.common.utility.Logger.b
        public void logV(String str, String str2, Throwable th) {
            Log.v(str, str2, th);
        }

        @Override // com.bytedance.common.utility.Logger.b
        public void logD(String str, String str2) {
            Log.d(str, str2);
        }

        @Override // com.bytedance.common.utility.Logger.b
        public void logD(String str, String str2, Throwable th) {
            Log.d(str, str2, th);
        }

        @Override // com.bytedance.common.utility.Logger.b
        public void logI(String str, String str2) {
            Log.i(str, str2);
        }

        @Override // com.bytedance.common.utility.Logger.b
        public void logI(String str, String str2, Throwable th) {
            Log.i(str, str2, th);
        }

        @Override // com.bytedance.common.utility.Logger.b
        public void logE(String str, String str2, Throwable th) {
            Log.e(str, str2, th);
        }

        @Override // com.bytedance.common.utility.Logger.b
        public void logE(String str, String str2) {
            Log.e(str, str2);
        }

        @Override // com.bytedance.common.utility.Logger.b
        public void logW(String str, String str2) {
            Log.w(str, str2);
        }

        @Override // com.bytedance.common.utility.Logger.b
        public void logW(String str, String str2, Throwable th) {
            Log.w(str, str2, th);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: com.bytedance.common.utility.Logger$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static class C0072a {

            /* renamed from: a  reason: collision with root package name */
            private static final a f5176a = new a();

            private C0072a() {
            }
        }
    }
}
