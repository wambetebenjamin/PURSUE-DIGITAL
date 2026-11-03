package com.bytedance.ug.sdk.share.impl.utils;

import android.util.Log;
import com.bytedance.librarian.c;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
/* compiled from: Logger.java */
/* loaded from: classes8.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private static final String f16181a = "Logger";

    /* renamed from: b  reason: collision with root package name */
    private static int f16182b = 4;

    /* renamed from: c  reason: collision with root package name */
    private static a f16183c = b.a();

    public static void setLogLevel(int i2) {
        f16182b = i2;
    }

    public static int getLogLevel() {
        return f16182b;
    }

    public static boolean debug() {
        return f16182b <= 3;
    }

    public static void v(String str) {
        v(f16181a, str);
    }

    public static void v(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (f16183c.isLoggable(2)) {
            f16183c.logV(str, str2);
        }
        com.bytedance.ug.sdk.share.impl.utils.a.v(str, str2);
    }

    public static void v(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (f16183c.isLoggable(2)) {
            f16183c.logV(str, str2, th);
        }
        com.bytedance.ug.sdk.share.impl.utils.a.v(str, str2);
    }

    public static void d(String str) {
        d(f16181a, str);
    }

    public static void d(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (f16183c.isLoggable(3)) {
            f16183c.logD(str, str2);
        }
        com.bytedance.ug.sdk.share.impl.utils.a.d(str, str2);
    }

    public static void d(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (f16183c.isLoggable(3)) {
            f16183c.logD(str, str2, th);
        }
        com.bytedance.ug.sdk.share.impl.utils.a.d(str, str2);
    }

    public static void i(String str) {
        i(f16181a, str);
    }

    public static void i(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (f16183c.isLoggable(4)) {
            f16183c.logI(str, str2);
        }
        com.bytedance.ug.sdk.share.impl.utils.a.i(str, str2);
    }

    public static void i(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (f16183c.isLoggable(4)) {
            f16183c.logI(str, str2, th);
        }
        com.bytedance.ug.sdk.share.impl.utils.a.i(str, str2);
    }

    public static void w(String str) {
        w(f16181a, str);
    }

    public static void w(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (f16183c.isLoggable(5)) {
            f16183c.logW(str, str2);
        }
        com.bytedance.ug.sdk.share.impl.utils.a.w(str, str2);
    }

    public static void w(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (f16183c.isLoggable(5)) {
            f16183c.logW(str, str2, th);
        }
        com.bytedance.ug.sdk.share.impl.utils.a.w(str, str2);
    }

    public static void e(String str) {
        e(f16181a, str);
    }

    public static void e(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (f16183c.isLoggable(6)) {
            f16183c.logE(str, str2);
        }
        com.bytedance.ug.sdk.share.impl.utils.a.e(str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (f16183c.isLoggable(6)) {
            f16183c.logE(str, str2, th);
        }
        com.bytedance.ug.sdk.share.impl.utils.a.e(str, str2);
    }

    @Deprecated
    public static void k(String str) {
        k(f16181a, str);
    }

    @Deprecated
    public static void k(String str, String str2) {
        if (f16183c.isLoggable(3)) {
            f16183c.logK(str, str2);
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
                sb.append(a(stackTrace[i3].getClassName()));
                sb.append(c.a.DOT);
                sb.append(stackTrace[i3].getMethodName());
            }
            v(str, sb.toString());
        }
    }

    private static String a(String str) {
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

    public static void registerLogHandler(a aVar) {
        f16183c = aVar;
    }

    /* compiled from: Logger.java */
    /* loaded from: classes8.dex */
    public static abstract class a {
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
            return j.getLogLevel() < i2;
        }
    }

    /* compiled from: Logger.java */
    /* loaded from: classes8.dex */
    private static class b extends a {
        private b() {
        }

        static b a() {
            return a.f16184a;
        }

        @Override // com.bytedance.ug.sdk.share.impl.utils.j.a
        public void logV(String str, String str2) {
            Log.v(str, str2);
        }

        @Override // com.bytedance.ug.sdk.share.impl.utils.j.a
        public void logV(String str, String str2, Throwable th) {
            Log.v(str, str2, th);
        }

        @Override // com.bytedance.ug.sdk.share.impl.utils.j.a
        public void logD(String str, String str2) {
            Log.d(str, str2);
        }

        @Override // com.bytedance.ug.sdk.share.impl.utils.j.a
        public void logD(String str, String str2, Throwable th) {
            Log.d(str, str2, th);
        }

        @Override // com.bytedance.ug.sdk.share.impl.utils.j.a
        public void logI(String str, String str2) {
            Log.i(str, str2);
        }

        @Override // com.bytedance.ug.sdk.share.impl.utils.j.a
        public void logI(String str, String str2, Throwable th) {
            Log.i(str, str2, th);
        }

        @Override // com.bytedance.ug.sdk.share.impl.utils.j.a
        public void logE(String str, String str2, Throwable th) {
            Log.e(str, str2, th);
        }

        @Override // com.bytedance.ug.sdk.share.impl.utils.j.a
        public void logE(String str, String str2) {
            Log.e(str, str2);
        }

        @Override // com.bytedance.ug.sdk.share.impl.utils.j.a
        public void logW(String str, String str2) {
            Log.w(str, str2);
        }

        @Override // com.bytedance.ug.sdk.share.impl.utils.j.a
        public void logW(String str, String str2, Throwable th) {
            Log.w(str, str2, th);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: Logger.java */
        /* loaded from: classes8.dex */
        public static class a {

            /* renamed from: a  reason: collision with root package name */
            private static final b f16184a = new b();

            private a() {
            }
        }
    }
}
