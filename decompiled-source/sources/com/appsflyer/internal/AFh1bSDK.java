package com.appsflyer.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes.dex */
public final class AFh1bSDK extends AFh1cSDK {
    private String AFInAppEventParameterName;
    private Network values;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AFh1bSDK(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "");
        this.AFInAppEventParameterName = "unknown";
        AFa1tSDK aFa1tSDK = new AFa1tSDK();
        ConnectivityManager connectivityManager = this.AFInAppEventType;
        if (connectivityManager != null) {
            connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().build(), aFa1tSDK);
        }
    }

    /* loaded from: classes.dex */
    public static final class AFa1tSDK extends ConnectivityManager.NetworkCallback {
        AFa1tSDK() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onAvailable(Network network) {
            Intrinsics.checkNotNullParameter(network, "");
            AFh1bSDK.this.values = network;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLost(Network network) {
            Intrinsics.checkNotNullParameter(network, "");
            AFh1bSDK.this.values = network;
            AFh1bSDK.this.AFInAppEventParameterName = "NetworkLost";
        }
    }

    @Override // com.appsflyer.internal.AFh1cSDK
    protected final String values() {
        Network network = this.values;
        if (network != null) {
            ConnectivityManager connectivityManager = this.AFInAppEventType;
            NetworkCapabilities networkCapabilities = connectivityManager != null ? connectivityManager.getNetworkCapabilities(network) : null;
            if (networkCapabilities != null && networkCapabilities != null) {
                if (networkCapabilities.hasTransport(1)) {
                    return "WIFI";
                }
                if (networkCapabilities.hasTransport(0)) {
                    return "MOBILE";
                }
            }
        }
        return "unknown";
    }

    @Override // com.appsflyer.internal.AFh1cSDK
    public final boolean AFInAppEventType() {
        Network network = this.values;
        if (network != null) {
            if (!(!Intrinsics.areEqual(this.AFInAppEventParameterName, "NetworkLost"))) {
                network = null;
            }
            if (network != null) {
                ConnectivityManager connectivityManager = this.AFInAppEventType;
                NetworkCapabilities networkCapabilities = connectivityManager != null ? connectivityManager.getNetworkCapabilities(network) : null;
                if (networkCapabilities != null) {
                    return valueOf(networkCapabilities);
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private static boolean valueOf(NetworkCapabilities networkCapabilities) {
        return (networkCapabilities == null || !networkCapabilities.hasTransport(4) || networkCapabilities.hasCapability(15)) ? false : true;
    }
}
