package com.bytedance.apm6.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import androidx.core.content.ContextCompat;
/* compiled from: NetUtils.java */
/* loaded from: classes.dex */
public class h {
    public static final int NET_DEFAULT = -10000;

    public static boolean isNetworkTrulyAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static int getMobileNetworkType(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE") == 0 ? ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo() : null;
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == 0) {
                return ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
            }
        } catch (Throwable unused) {
        }
        return -10000;
    }
}
