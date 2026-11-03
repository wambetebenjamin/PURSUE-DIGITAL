package com.bytedance.crash.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.bytedance.crash.Global;
/* compiled from: Net.java */
/* loaded from: classes2.dex */
public final class x {

    /* renamed from: a  reason: collision with root package name */
    private static final int f7316a = 0;

    /* renamed from: b  reason: collision with root package name */
    private static final int f7317b = 1;

    /* renamed from: c  reason: collision with root package name */
    private static final int f7318c = 2;

    /* renamed from: d  reason: collision with root package name */
    private static final int f7319d = 3;

    /* renamed from: e  reason: collision with root package name */
    private static final int f7320e = 4;

    /* renamed from: f  reason: collision with root package name */
    private static final int f7321f = 5;

    /* renamed from: g  reason: collision with root package name */
    private static final int f7322g = 6;

    /* renamed from: h  reason: collision with root package name */
    private static final int f7323h = 7;

    /* renamed from: i  reason: collision with root package name */
    private static final String[] f7324i = {"unknown", "wifi", "mobile", "2g", "3g", "4g", "5g"};

    public static String a() {
        int c2 = c();
        if (c2 >= 7) {
            c2 = 0;
        }
        return f7324i[c2];
    }

    private static int c() {
        ConnectivityManager b2;
        NetworkInfo activeNetworkInfo;
        try {
            Context c2 = Global.c();
            if (c2 != null && (b2 = b(c2)) != null && (activeNetworkInfo = b2.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (1 == type) {
                    return 1;
                }
                if (type == 0) {
                    switch (a(c2).getNetworkType()) {
                        case 2:
                            return 3;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            return 4;
                        case 4:
                        case 7:
                        case 11:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        default:
                            return 2;
                        case 13:
                            return 5;
                        case 20:
                            return 6;
                    }
                }
                return 2;
            }
        } catch (Throwable unused) {
        }
        return 0;
    }

    private static TelephonyManager a(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    private static ConnectivityManager b(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    public static boolean b() {
        NetworkInfo activeNetworkInfo;
        Context c2 = Global.c();
        if (c2 == null) {
            return false;
        }
        try {
            ConnectivityManager b2 = b(c2);
            if (b2 == null || (activeNetworkInfo = b2.getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isAvailable();
        } catch (Exception e2) {
            com.bytedance.crash.diagnose.b.a(e2);
        }
        return false;
    }
}
