package com.unity3d.player;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class A extends ConnectivityManager.NetworkCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ NetworkConnectivityNougat f18800a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public A(NetworkConnectivityNougat networkConnectivityNougat) {
        this.f18800a = networkConnectivityNougat;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onAvailable(Network network) {
        super.onAvailable(network);
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        NetworkConnectivityNougat networkConnectivityNougat;
        int i2;
        super.onCapabilitiesChanged(network, networkCapabilities);
        if (networkCapabilities.hasTransport(0)) {
            networkConnectivityNougat = this.f18800a;
            i2 = 1;
        } else {
            networkConnectivityNougat = this.f18800a;
            i2 = 2;
        }
        networkConnectivityNougat.f18858b = i2;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network network) {
        super.onLost(network);
        this.f18800a.f18858b = 0;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onUnavailable() {
        super.onUnavailable();
        this.f18800a.f18858b = 0;
    }
}
