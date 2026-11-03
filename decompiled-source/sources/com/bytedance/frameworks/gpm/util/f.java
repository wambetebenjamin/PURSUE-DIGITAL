package com.bytedance.frameworks.gpm.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
/* compiled from: NetUtil.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8552a = "unknown";

    /* renamed from: b  reason: collision with root package name */
    private static final String f8553b = "WIFI";

    /* renamed from: c  reason: collision with root package name */
    private static final String f8554c = "2G";

    /* renamed from: d  reason: collision with root package name */
    private static final String f8555d = "3G";

    /* renamed from: e  reason: collision with root package name */
    private static final String f8556e = "4G";

    /* renamed from: f  reason: collision with root package name */
    private static final String f8557f = "5G";

    public static String getNetWorkType(Context context) {
        NetworkInfo networkInfo;
        NetworkInfo.State state;
        if (context == null) {
            return "unknown";
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(1)) == null || (state = networkInfo.getState()) == null || !(state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                int networkType = telephonyManager.getNetworkType();
                if (networkType != 20) {
                    switch (networkType) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            return f8554c;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            return f8555d;
                        case 13:
                            return f8556e;
                        default:
                            return "unknown";
                    }
                }
                return f8557f;
            }
            return "unknown";
        }
        return f8553b;
    }

    public static String getIPAddress(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "";
        }
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress() && !nextElement.isLinkLocalAddress()) {
                        return nextElement.getHostAddress();
                    }
                }
            }
            return "";
        } catch (SocketException unused) {
            return "";
        }
    }
}
