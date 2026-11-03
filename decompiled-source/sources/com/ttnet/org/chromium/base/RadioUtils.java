package com.ttnet.org.chromium.base;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Process;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.compat.ApiHelperForM;
import com.ttnet.org.chromium.base.compat.ApiHelperForP;
@JNINamespace("base::android")
/* loaded from: classes9.dex */
public class RadioUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Boolean sHaveAccessNetworkState;
    private static Boolean sHaveAccessWifiState;

    private RadioUtils() {
    }

    private static boolean isSupported() {
        return Build.VERSION.SDK_INT >= 28 && haveAccessNetworkState() && haveAccessWifiState();
    }

    private static boolean haveAccessNetworkState() {
        if (sHaveAccessNetworkState == null) {
            sHaveAccessNetworkState = Boolean.valueOf(ApiCompatibilityUtils.checkPermission(ContextUtils.getApplicationContext(), "android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) == 0);
        }
        return sHaveAccessNetworkState.booleanValue();
    }

    private static boolean haveAccessWifiState() {
        if (sHaveAccessWifiState == null) {
            sHaveAccessWifiState = Boolean.valueOf(ApiCompatibilityUtils.checkPermission(ContextUtils.getApplicationContext(), "android.permission.ACCESS_WIFI_STATE", Process.myPid(), Process.myUid()) == 0);
        }
        return sHaveAccessWifiState.booleanValue();
    }

    private static boolean isWifiConnected() {
        TraceEvent scoped = TraceEvent.scoped("RadioUtils::isWifiConnected");
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity");
            Network activeNetwork = ApiHelperForM.getActiveNetwork(connectivityManager);
            if (activeNetwork == null) {
                if (scoped != null) {
                    scoped.close();
                }
                return false;
            }
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            if (networkCapabilities == null) {
                if (scoped != null) {
                    scoped.close();
                }
                return false;
            }
            boolean hasTransport = networkCapabilities.hasTransport(1);
            if (scoped != null) {
                scoped.close();
            }
            return hasTransport;
        } catch (Throwable th) {
            if (scoped != null) {
                try {
                    scoped.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private static int getCellSignalLevel() {
        TraceEvent scoped = TraceEvent.scoped("RadioUtils::getCellSignalLevel");
        try {
            int i2 = -1;
            try {
                SignalStrength signalStrength = ApiHelperForP.getSignalStrength((TelephonyManager) ContextUtils.getApplicationContext().getSystemService("phone"));
                if (signalStrength != null) {
                    i2 = signalStrength.getLevel();
                }
            } catch (SecurityException unused) {
            }
            if (scoped != null) {
                scoped.close();
            }
            return i2;
        } catch (Throwable th) {
            if (scoped != null) {
                try {
                    scoped.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private static int getCellDataActivity() {
        TraceEvent scoped = TraceEvent.scoped("RadioUtils::getCellDataActivity");
        try {
            try {
                int dataActivity = ((TelephonyManager) ContextUtils.getApplicationContext().getSystemService("phone")).getDataActivity();
                if (scoped != null) {
                    scoped.close();
                }
                return dataActivity;
            } catch (SecurityException unused) {
                if (scoped != null) {
                    scoped.close();
                }
                return -1;
            }
        } catch (Throwable th) {
            if (scoped != null) {
                try {
                    scoped.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
