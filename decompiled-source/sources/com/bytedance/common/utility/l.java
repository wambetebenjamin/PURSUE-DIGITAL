package com.bytedance.common.utility;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Pair;
import com.bytedance.ttgame.sdk.module.utils.NetworkChangeManager;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
/* compiled from: NetworkUtils.java */
/* loaded from: classes2.dex */
public final class l {

    /* renamed from: a  reason: collision with root package name */
    static final boolean f5290a = false;

    /* renamed from: b  reason: collision with root package name */
    private static c f5291b = null;

    /* renamed from: e  reason: collision with root package name */
    private static final long f5294e = 2000;

    /* renamed from: g  reason: collision with root package name */
    private static final int f5296g = 3;

    /* renamed from: h  reason: collision with root package name */
    private static final int f5297h = 2400;

    /* renamed from: i  reason: collision with root package name */
    private static final int f5298i = 2500;

    /* renamed from: j  reason: collision with root package name */
    private static final int f5299j = 4900;
    private static final int k = 5900;
    private static final String l = "&";
    private static final String m = "=";
    private static final String n = "ISO-8859-1";

    /* renamed from: c  reason: collision with root package name */
    private static b f5292c = b.UNKNOWN;

    /* renamed from: d  reason: collision with root package name */
    private static boolean f5293d = false;

    /* renamed from: f  reason: collision with root package name */
    private static long f5295f = 0;

    /* compiled from: NetworkUtils.java */
    /* loaded from: classes2.dex */
    public interface c {
        b getNetworkType();
    }

    public static boolean is24GHz(int i2) {
        return i2 > f5297h && i2 < f5298i;
    }

    public static boolean is5GHz(int i2) {
        return i2 > f5299j && i2 < k;
    }

    private l() {
    }

    /* compiled from: NetworkUtils.java */
    /* loaded from: classes2.dex */
    public enum a {
        NONE(0),
        GZIP(1),
        DEFLATER(2);
        

        /* renamed from: a  reason: collision with root package name */
        final int f5301a;

        a(int i2) {
            this.f5301a = i2;
        }
    }

    /* compiled from: NetworkUtils.java */
    /* loaded from: classes2.dex */
    public enum b {
        UNKNOWN(-1),
        NONE(0),
        MOBILE(1),
        MOBILE_2G(2),
        MOBILE_3G(3),
        WIFI(4),
        MOBILE_4G(5),
        MOBILE_5G(6),
        WIFI_24GHZ(7),
        WIFI_5GHZ(8),
        MOBILE_3G_H(9),
        MOBILE_3G_HP(10);
        

        /* renamed from: a  reason: collision with root package name */
        final int f5303a;

        b(int i2) {
            this.f5303a = i2;
        }

        public int getValue() {
            return this.f5303a;
        }

        public boolean is2G() {
            return this == MOBILE || this == MOBILE_2G;
        }

        public boolean isWifi() {
            return this == WIFI;
        }

        public boolean is4GOrHigher() {
            return this == MOBILE_4G || this == MOBILE_5G;
        }

        public boolean is3GOrHigher() {
            return this == MOBILE_3G || this == MOBILE_3G_H || this == MOBILE_3G_HP || this == MOBILE_4G || this == MOBILE_5G;
        }

        public boolean isAvailable() {
            return (this == UNKNOWN || this == NONE) ? false : true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(b bVar) {
        f5292c = bVar;
    }

    public static void setNetworkTypeInterceptor(c cVar) {
        f5291b = cVar;
    }

    public static boolean is2G(Context context) {
        b networkType = getNetworkType(context);
        return networkType == b.MOBILE || networkType == b.MOBILE_2G;
    }

    public static boolean isWifi(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                c cVar = f5291b;
                return (cVar == null || cVar.getNetworkType() == b.NONE) ? 1 == activeNetworkInfo.getType() : f5291b.getNetworkType() == b.WIFI;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
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

    public static b getNetworkTypeFast(Context context) {
        a(context);
        b(context);
        return f5292c;
    }

    public static boolean is2GFast(Context context) {
        a(context);
        b(context);
        return f5292c.is2G();
    }

    public static boolean isWifiFast(Context context) {
        a(context);
        b(context);
        return f5292c.isWifi();
    }

    public static boolean isNetworkAvailableFast(Context context) {
        a(context);
        b(context);
        return f5292c.isAvailable();
    }

    private static void a(Context context) {
        c cVar = f5291b;
        if (cVar != null && cVar.getNetworkType() != b.NONE) {
            f5292c = f5291b.getNetworkType();
            return;
        }
        c(context);
        if (f5292c == b.UNKNOWN) {
            f5292c = getNetworkType(context);
        }
    }

    private static void b(Context context) {
        if (System.currentTimeMillis() - f5295f > 2000) {
            f5292c = getNetworkType(context);
            f5295f = System.currentTimeMillis();
        }
    }

    private static void c(Context context) {
        if (f5293d || context == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkChangeManager.ACTION);
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        context.getApplicationContext().registerReceiver(new NetworkConnectChangeReceiver(), intentFilter);
        f5293d = true;
    }

    public static b getNetworkType(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (1 == type) {
                    return b.WIFI;
                }
                if (type == 0) {
                    int networkType = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
                    if (networkType != 3) {
                        if (networkType == 20) {
                            return b.MOBILE_5G;
                        }
                        if (networkType != 5 && networkType != 6) {
                            switch (networkType) {
                                case 8:
                                case 9:
                                case 10:
                                    break;
                                default:
                                    switch (networkType) {
                                        case 12:
                                        case 14:
                                        case 15:
                                            break;
                                        case 13:
                                            return b.MOBILE_4G;
                                        default:
                                            return b.MOBILE;
                                    }
                            }
                        }
                    }
                    return b.MOBILE_3G;
                }
                return b.MOBILE;
            }
            return b.NONE;
        } catch (Throwable unused) {
            return b.MOBILE;
        }
    }

    public static String getNetworkTypeDetail(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (1 == type) {
                    return isWifi5GHz(context) ? "wifi5g" : "wifi";
                } else if (type == 0) {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    switch (telephonyManager.getNetworkType()) {
                        case 1:
                            return "gprs";
                        case 2:
                            return "edge";
                        case 3:
                            return "umts";
                        case 4:
                            return "cdma";
                        case 5:
                            return "evdo_0";
                        case 6:
                            return "evdo_a";
                        case 7:
                            return "1xrtt";
                        case 8:
                            return "hsdpa";
                        case 9:
                            return "hsupa";
                        case 10:
                            return "hspa";
                        case 11:
                            return "iden";
                        case 12:
                            return "evdo_b";
                        case 13:
                            return "lte";
                        case 14:
                            return "ehrpd";
                        case 15:
                            return "hspap";
                        case 16:
                            return "gsm";
                        case 17:
                            return "td_scdma";
                        case 18:
                        default:
                            return String.valueOf(telephonyManager.getNetworkType());
                        case 19:
                            return "lte_ca";
                        case 20:
                            return "nr";
                    }
                } else {
                    return "unknown";
                }
            }
            return "none";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    public static boolean isWifi5GHz(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            return is5GHz(((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getFrequency());
        }
        return false;
    }

    public static String getNetworkAccessType(Context context) {
        return getNetworkAccessType(getNetworkType(context));
    }

    public static String getNetworkAccessType(b bVar) {
        return bVar == b.WIFI ? "wifi" : bVar == b.WIFI_24GHZ ? "wifi24ghz" : bVar == b.WIFI_5GHZ ? "wifi5ghz" : bVar == b.MOBILE_2G ? "2g" : bVar == b.MOBILE_3G ? "3g" : bVar == b.MOBILE_3G_H ? "3gh" : bVar == b.MOBILE_3G_HP ? "3ghp" : bVar == b.MOBILE_4G ? "4g" : bVar == b.MOBILE_5G ? "5g" : bVar == b.MOBILE ? "mobile" : "";
    }

    public static String format(List<Pair<String, String>> list, String str) {
        StringBuilder sb = new StringBuilder();
        for (Pair<String, String> pair : list) {
            String a2 = a((String) pair.first, str);
            String str2 = (String) pair.second;
            String a3 = str2 != null ? a(str2, str) : "";
            if (sb.length() > 0) {
                sb.append(l);
            }
            sb.append(a2);
            sb.append(m);
            sb.append(a3);
        }
        return sb.toString();
    }

    private static String a(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalArgumentException(e2);
        }
    }
}
