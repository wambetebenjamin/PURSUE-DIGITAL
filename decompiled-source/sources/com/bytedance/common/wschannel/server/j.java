package com.bytedance.common.wschannel.server;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
/* compiled from: NetworkUtils.java */
/* loaded from: classes2.dex */
public class j {
    public static boolean a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isAvailable();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static a b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (1 == type) {
                    return a.WIFI;
                }
                if (type == 0) {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (telephonyManager == null) {
                        return a.MOBILE;
                    }
                    if (telephonyManager.getNetworkType() != 3 && telephonyManager.getNetworkType() != 5 && telephonyManager.getNetworkType() != 6 && telephonyManager.getNetworkType() != 8 && telephonyManager.getNetworkType() != 9 && telephonyManager.getNetworkType() != 10 && telephonyManager.getNetworkType() != 12 && telephonyManager.getNetworkType() != 14 && telephonyManager.getNetworkType() != 15) {
                        if (telephonyManager.getNetworkType() == 13) {
                            return a.MOBILE_4G;
                        }
                        return a.MOBILE;
                    }
                    return a.MOBILE_3G;
                }
                return a.MOBILE;
            }
            return a.NONE;
        } catch (Throwable unused) {
            return a.MOBILE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(Context context) {
        boolean a2 = a(context);
        a b2 = b(context);
        if (a2) {
            if (a.WIFI == b2) {
                return 3;
            }
            return a.NONE != b2 ? 4 : 1;
        }
        return 2;
    }

    /* compiled from: NetworkUtils.java */
    /* loaded from: classes2.dex */
    public enum a {
        NONE(0),
        MOBILE(1),
        MOBILE_2G(2),
        MOBILE_3G(3),
        WIFI(4),
        MOBILE_4G(5);
        

        /* renamed from: g  reason: collision with root package name */
        final int f5978g;

        a(int i2) {
            this.f5978g = i2;
        }

        public int a() {
            return this.f5978g;
        }
    }
}
