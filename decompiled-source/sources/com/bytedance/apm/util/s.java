package com.bytedance.apm.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import androidx.core.content.ContextCompat;
import com.bytedance.common.utility.l;
/* compiled from: NetUtils.java */
/* loaded from: classes.dex */
public class s {
    public static final boolean DEBUG_MOBILE = false;
    public static final int NET_DEFAULT = -10000;

    /* renamed from: a  reason: collision with root package name */
    private static l.c f1713a;

    /* renamed from: b  reason: collision with root package name */
    private static l.b f1714b = l.b.UNKNOWN;

    static void a(l.b bVar) {
        f1714b = bVar;
    }

    public static void setNetworkTypeInterceptor(l.c cVar) {
        f1713a = cVar;
    }

    public static int getMobileNetworkType(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ContextCompat.checkSelfPermission(com.bytedance.apm.c.getContext(), "android.permission.ACCESS_NETWORK_STATE") == 0 ? ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo() : null;
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == 0) {
                return ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
            }
        } catch (Throwable unused) {
        }
        return -10000;
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isWifi(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                l.c cVar = f1713a;
                return (cVar == null || cVar.getNetworkType() == l.b.NONE) ? 1 == activeNetworkInfo.getType() : f1713a.getNetworkType() == l.b.WIFI;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
