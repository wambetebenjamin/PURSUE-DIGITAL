package com.unity3d.player;

import android.content.Context;
import android.net.ConnectivityManager;
/* loaded from: classes10.dex */
public class NetworkConnectivityNougat extends NetworkConnectivity {

    /* renamed from: b  reason: collision with root package name */
    private int f18858b;

    /* renamed from: c  reason: collision with root package name */
    private final ConnectivityManager.NetworkCallback f18859c;

    public NetworkConnectivityNougat(Context context) {
        super(context);
        this.f18858b = 0;
        A a2 = new A(this);
        this.f18859c = a2;
        if (this.f18857a == null) {
            return;
        }
        this.f18858b = super.b();
        this.f18857a.registerDefaultNetworkCallback(a2);
    }

    @Override // com.unity3d.player.NetworkConnectivity
    public final void a() {
        ConnectivityManager connectivityManager = this.f18857a;
        if (connectivityManager == null) {
            return;
        }
        connectivityManager.unregisterNetworkCallback(this.f18859c);
    }

    @Override // com.unity3d.player.NetworkConnectivity
    public final int b() {
        return this.f18858b;
    }
}
