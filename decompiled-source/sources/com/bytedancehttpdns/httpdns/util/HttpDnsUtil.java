package com.bytedancehttpdns.httpdns.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import java.util.List;
/* loaded from: classes8.dex */
public class HttpDnsUtil {
    private static final String TAG = "veHttpDns";
    public static final int TYPE_NONE = -1;
    public static final int TYPE_UNKNOWN = -2;

    public static boolean isValidHost(String str) {
        if (str != null) {
            char[] charArray = str.toCharArray();
            if (charArray.length > 0 && charArray.length <= 255) {
                for (char c2 : charArray) {
                    if ((c2 < 'A' || c2 > 'Z') && ((c2 < 'a' || c2 > 'z') && !((c2 >= '0' && c2 <= '9') || c2 == '.' || c2 == '-'))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static int getNetType(Context context) {
        if (context == null) {
            return -2;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                if (Build.VERSION.SDK_INT >= 29) {
                    NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                    if (networkCapabilities != null) {
                        if (networkCapabilities.hasTransport(0)) {
                            return 0;
                        }
                        if (networkCapabilities.hasTransport(1)) {
                            return 1;
                        }
                        if (networkCapabilities.hasTransport(3)) {
                            return 3;
                        }
                        if (networkCapabilities.hasTransport(2)) {
                            return 2;
                        }
                        if (networkCapabilities.hasTransport(4)) {
                            return 4;
                        }
                        if (networkCapabilities.hasTransport(5)) {
                            return 5;
                        }
                        return networkCapabilities.hasTransport(6) ? 6 : -1;
                    }
                    return -2;
                }
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                    return -1;
                }
                return activeNetworkInfo.getType();
            }
        } catch (Throwable unused) {
        }
        return -2;
    }

    public static String transHostList2String(List<String> list) {
        if (list == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < list.size(); i2++) {
            sb.append(list.get(i2));
            if (i2 != list.size() - 1) {
                sb.append(',');
            }
        }
        return sb.toString();
    }
}
