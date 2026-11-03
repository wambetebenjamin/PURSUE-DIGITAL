package com.bytedance.push.utils;

import android.content.Context;
import android.text.TextUtils;
/* compiled from: Logger.java */
/* loaded from: classes2.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f12034a = false;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f12035b = false;

    /* renamed from: c  reason: collision with root package name */
    private static int f12036c = 3;

    /* renamed from: e  reason: collision with root package name */
    private static com.bytedance.push.log.a f12038e = new com.bytedance.push.log.d();

    /* renamed from: f  reason: collision with root package name */
    private static String f12039f = "";

    /* renamed from: d  reason: collision with root package name */
    private static boolean f12037d = false;

    public static void a(com.bytedance.push.log.a aVar) {
        f12038e = aVar;
        if (aVar instanceof com.bytedance.push.log.d) {
            return;
        }
        f12037d = true;
    }

    public static String a() {
        return f12039f;
    }

    public static void a(Context context, boolean z) {
        f12034a = z;
        if (TextUtils.isEmpty(f12039f)) {
            f12039f = "BDPush-" + com.ss.android.message.util.b.a(context);
        }
    }

    public static void setLogLevel(int i2) {
        f12036c = i2;
    }

    public static void setDebuggable(boolean z) {
        f12035b = z;
    }

    public static void d(String str) {
        d("", str);
    }

    public static void d(String str, String str2) {
        if (f12034a) {
            com.ss.android.agilelogger.a.d(f12039f, str + "\t>>>\t" + str2);
        } else if (f12036c <= 3) {
            if (f12037d || f12035b) {
                f12038e.d(f12039f, str + "\t>>>\t" + str2);
            }
        }
    }

    public static void d(String str, String str2, Throwable th) {
        d(f12039f, str + "\t>>>\t" + str2 + th.getMessage());
    }

    public static void e(String str) {
        e("", str);
    }

    public static void e(String str, String str2) {
        if (f12034a) {
            com.ss.android.agilelogger.a.e(f12039f, str + "\t>>>\t" + str2);
        } else if (f12036c <= 6) {
            if (f12037d || f12035b) {
                f12038e.e(f12039f, str + "\t>>> " + str2);
            }
        }
    }

    public static void e(String str, String str2, Throwable th) {
        e(f12039f, str + "\t>>>\t" + str2 + th.getMessage());
    }

    public static boolean debug() {
        return f12035b;
    }

    public static void i(String str) {
        i("", str);
    }

    public static void i(String str, String str2) {
        if (f12034a) {
            com.ss.android.agilelogger.a.i(f12039f, str + "\t>>>\t" + str2);
        } else if (f12036c <= 4) {
            if (f12037d || f12035b) {
                f12038e.i(f12039f, str + "\t>>>\t" + str2);
            }
        }
    }

    public static void i(String str, String str2, Throwable th) {
        i(f12039f, str + "\t>>>\t" + str2 + "\t>>>\t" + (th == null ? "null" : th.getMessage()));
    }

    public static void v(String str) {
        v("", str);
    }

    public static void v(String str, String str2) {
        if (f12034a) {
            com.ss.android.agilelogger.a.v(f12039f, str + "\t>>>\t" + str2);
        } else if (f12036c <= 2) {
            if (f12037d || f12035b) {
                f12038e.v(f12039f, str + "\t>>>\t" + str2);
            }
        }
    }

    public static void w(String str, String str2) {
        if (f12034a) {
            com.ss.android.agilelogger.a.w(f12039f, str + "\t>>>\t" + str2);
        } else if (f12036c <= 5) {
            if (f12037d || f12035b) {
                f12038e.w(f12039f, str + "\t>>>\t" + str2);
            }
        }
    }
}
