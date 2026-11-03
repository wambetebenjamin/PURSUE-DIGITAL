package com.bytedance.apm.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.bytedance.common.utility.l;
/* compiled from: NetTypeUtils.java */
/* loaded from: classes.dex */
public class r {
    public static final boolean DEBUG_MOBILE = false;

    public static l.b getNetworkType(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (1 == type) {
                    return l.b.WIFI;
                }
                if (type == 0) {
                    switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            return l.b.MOBILE_3G;
                        case 4:
                        case 7:
                        case 11:
                        default:
                            return l.b.MOBILE;
                        case 13:
                            return l.b.MOBILE_4G;
                    }
                }
                return l.b.MOBILE;
            }
            return l.b.NONE;
        } catch (Throwable unused) {
            return l.b.MOBILE;
        }
    }
}
