package com.bytedance.platform.godzilla.common;

import android.util.Log;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
/* compiled from: Logger.java */
/* loaded from: classes2.dex */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    private static a f11058a = a.INFO;
    public static boolean isDebug = false;

    /* renamed from: b  reason: collision with root package name */
    private static i f11059b = new i() { // from class: com.bytedance.platform.godzilla.common.k.1
        @Override // com.bytedance.platform.godzilla.common.i
        public void println(String str, String str2, a aVar) {
            Log.println(a(aVar), str, str2);
        }

        private int a(a aVar) {
            int i2 = AnonymousClass2.f11060a[aVar.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        return i2 != 4 ? 2 : 4;
                    }
                    return 5;
                }
                return 6;
            }
            return 3;
        }
    };

    /* compiled from: Logger.java */
    /* loaded from: classes2.dex */
    public enum a {
        DEBUG,
        INFO,
        WARNING,
        ERROR,
        NONE
    }

    private k() {
    }

    public static boolean debug() {
        return isDebug;
    }

    public static void setLogLevel(a aVar) {
        f11058a = aVar;
        if (aVar == a.DEBUG) {
            isDebug = true;
        }
    }

    public static void setLog(i iVar) {
        f11059b = iVar;
    }

    public static void e(String str, String str2, Object... objArr) {
        println(str, String.format(str2, objArr), a.ERROR);
    }

    public static void e(String str, String str2) {
        println(str, str2, a.ERROR);
    }

    public static void w(String str, String str2, Object... objArr) {
        println(str, String.format(str2, objArr), a.WARNING);
    }

    public static void w(String str, String str2) {
        println(str, str2, a.WARNING);
    }

    public static void i(String str, String str2, Object... objArr) {
        println(str, String.format(str2, objArr), a.INFO);
    }

    public static void i(String str, String str2) {
        println(str, str2, a.INFO);
    }

    public static void d(String str, String str2, Object... objArr) {
        println(str, String.format(str2, objArr), a.DEBUG);
    }

    public static void d(String str, String str2) {
        println(str, str2, a.DEBUG);
    }

    public static void println(String str, String str2, a aVar) {
        if (aVar.ordinal() >= f11058a.ordinal()) {
            f11059b.println(str, str2, aVar);
        }
    }

    public static void printStackTrace(String str, Throwable th) {
        printStackTrace(str, th, null);
    }

    public static void printStackTrace(String str, Throwable th, String str2) {
        println(str, (str2 == null ? "" : str2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE) + th.getMessage() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + a(th), a.ERROR);
    }

    private static String a(Throwable th) {
        String str = "";
        if (th == null) {
            return "";
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace != null) {
            for (int i2 = 0; i2 < stackTrace.length; i2++) {
                str = str + stackTrace[i2].toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Logger.java */
    /* renamed from: com.bytedance.platform.godzilla.common.k$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f11060a;

        static {
            int[] iArr = new int[a.values().length];
            f11060a = iArr;
            try {
                iArr[a.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11060a[a.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11060a[a.WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f11060a[a.INFO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
