package com.ss.android;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.common.utility.Logger;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
/* compiled from: LogHelper.java */
/* loaded from: classes9.dex */
public class e {
    public static void a(String str, String str2) {
        a(2, str, str2, null);
    }

    public static void b(String str, String str2) {
        a(3, str, str2, null);
    }

    public static void c(String str, String str2) {
        a(4, str, str2, null);
    }

    public static void d(String str, String str2) {
        a(str, str2, null);
    }

    public static void a(String str, String str2, Throwable th) {
        a(5, str, str2, th);
    }

    public static void e(String str, String str2) {
        b(str, str2, null);
    }

    public static void b(String str, String str2, Throwable th) {
        a(6, str, str2, th);
    }

    public static void a(int i2, String str, String str2, Throwable th) {
        if (th != null) {
            if (TextUtils.isEmpty(str2)) {
                str2 = Log.getStackTraceString(th);
            } else {
                str2 = str2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + Log.getStackTraceString(th);
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (com.ss.android.account.f.e() != null) {
            com.ss.android.account.f.e().a(i2, str, str2);
            return;
        }
        switch (i2) {
            case 2:
                Logger.v(str, str2);
                return;
            case 3:
                Logger.d(str, str2);
                return;
            case 4:
                Logger.i(str, str2);
                return;
            case 5:
                Logger.w(str, str2);
                return;
            case 6:
            case 7:
                Logger.e(str, str2);
                return;
            default:
                return;
        }
    }
}
