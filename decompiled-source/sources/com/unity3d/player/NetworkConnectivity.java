package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
/* loaded from: classes10.dex */
public class NetworkConnectivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    protected ConnectivityManager f18857a;

    public NetworkConnectivity(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.f18857a = connectivityManager;
        if (connectivityManager == null) {
            AbstractC0445z.Log(6, "NetworkConnectivity: ConnectivityManager not found");
        }
    }

    public void a() {
    }

    public int b() {
        NetworkCapabilities networkCapabilities;
        ConnectivityManager connectivityManager = this.f18857a;
        if (connectivityManager == null) {
            return 0;
        }
        if (PlatformSupport.MARSHMALLOW_SUPPORT) {
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork != null && (networkCapabilities = this.f18857a.getNetworkCapabilities(activeNetwork)) != null) {
                return networkCapabilities.hasTransport(0) ? 1 : 2;
            }
        } else {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return activeNetworkInfo.getType() == 0 ? 1 : 2;
            }
        }
        return 0;
    }
}
