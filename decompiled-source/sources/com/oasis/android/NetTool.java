package com.oasis.android;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import com.oasis.Logger.Logger;
/* loaded from: classes9.dex */
public class NetTool {
    public static final int NETWORK_2G = 2;
    public static final int NETWORK_3G = 3;
    public static final int NETWORK_4G = 4;
    public static final int NETWORK_MOBILE = 5;
    public static final int NETWORK_NONE = 0;
    public static final int NETWORK_WIFI = 1;

    /* renamed from: a  reason: collision with root package name */
    private static boolean f17384a = false;
    public static int signalStrengthLevel;

    /* loaded from: classes9.dex */
    class a extends PhoneStateListener {
        a() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            try {
                NetTool.signalStrengthLevel = ((Integer) signalStrength.getClass().getMethod("getLevel", new Class[0]).invoke(signalStrength, new Object[0])).intValue();
            } catch (Exception e2) {
                Logger.e("JavaError", "signalStrength call getLevel Exception: " + e2.getMessage());
                NetTool.signalStrengthLevel = 0;
            }
        }
    }

    public static int getNetworkState() {
        NetworkInfo activeNetworkInfo;
        NetworkInfo.State state;
        Context applicationContext = ActivityManager.getActivity().getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
            return 0;
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo == null || (state = networkInfo.getState()) == null || !(state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
            switch (((TelephonyManager) applicationContext.getSystemService("phone")).getNetworkType()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return 2;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return 3;
                case 13:
                    return 4;
                default:
                    return 5;
            }
        }
        return 1;
    }

    public static String getOperatorName() {
        return ((TelephonyManager) ActivityManager.getActivity().getApplicationContext().getSystemService("phone")).getSimOperatorName();
    }

    public static int getSignalStrength() {
        Activity activity = ActivityManager.getActivity();
        if (!f17384a) {
            try {
                try {
                    ((TelephonyManager) activity.getSystemService("phone")).listen(new a(), 256);
                } catch (Exception e2) {
                    Logger.e("JavaError", "getSignalStrength Listen Exception: " + e2.getMessage());
                }
            } finally {
                f17384a = true;
            }
        }
        return signalStrengthLevel;
    }

    public static int getWifiStrength() {
        return WifiManager.calculateSignalLevel(((WifiManager) ActivityManager.getActivity().getApplicationContext().getSystemService("wifi")).getConnectionInfo().getRssi(), 5);
    }

    public static boolean isNetConnected() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) ActivityManager.getActivity().getApplicationContext().getSystemService("connectivity");
        return connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
    }

    public static synchronized boolean isWifiConnected() {
        NetworkInfo activeNetworkInfo;
        int type;
        synchronized (NetTool.class) {
            ConnectivityManager connectivityManager = (ConnectivityManager) ActivityManager.getActivity().getApplicationContext().getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !((type = activeNetworkInfo.getType()) == 1 || type == 9)) {
                return false;
            }
            return activeNetworkInfo.isConnected();
        }
    }
}
