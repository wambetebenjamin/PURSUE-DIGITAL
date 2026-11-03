package com.bytedance.ttgame.main.internal.device;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import com.bytedance.ttgame.main.internal.NetStateManager;
import com.bytedance.ttgame.rocketapi.callback.NetStateCallback;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: NetStateChangeManager.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0003J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0003J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/bytedance/ttgame/main/internal/device/NetStateChangeManager;", "", "()V", "lastNetState", "", "mNetStateBroadcast", "Lcom/bytedance/ttgame/main/internal/device/NetStateBroadcast;", "networkCallback", "Landroid/net/ConnectivityManager$NetworkCallback;", "netStateRegisterReceiver", "", "context", "Landroid/content/Context;", "netStateCallback", "Lcom/bytedance/ttgame/rocketapi/callback/NetStateCallback;", "netStateUnregisterReceiver", "registerCallback", "registerReceiver", "unregisterCallback", "unregisterReceiver", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NetStateChangeManager {
    public static final NetStateChangeManager INSTANCE = new NetStateChangeManager();
    private static int lastNetState = Integer.MAX_VALUE;
    private static NetStateBroadcast mNetStateBroadcast;
    private static ConnectivityManager.NetworkCallback networkCallback;

    private NetStateChangeManager() {
    }

    public final void netStateRegisterReceiver(Context context, NetStateCallback netStateCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(netStateCallback, "netStateCallback");
        if (Build.VERSION.SDK_INT >= 21) {
            registerCallback(context, netStateCallback);
        } else {
            registerReceiver(context, netStateCallback);
        }
    }

    public final void netStateUnregisterReceiver(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT >= 21) {
            unregisterCallback(context);
        } else {
            unregisterReceiver(context);
        }
    }

    private final void registerCallback(final Context context, final NetStateCallback netStateCallback) {
        Object systemService = context.getSystemService("connectivity");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        NetworkRequest build = new NetworkRequest.Builder().addTransportType(0).addTransportType(1).build();
        if (networkCallback == null) {
            networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.bytedance.ttgame.main.internal.device.NetStateChangeManager$registerCallback$1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                    int i2;
                    Intrinsics.checkNotNullParameter(network, "network");
                    Intrinsics.checkNotNullParameter(networkCapabilities, "networkCapabilities");
                    super.onCapabilitiesChanged(network, networkCapabilities);
                    int currentNetState = NetStateManager.INSTANCE.getCurrentNetState(context);
                    i2 = NetStateChangeManager.lastNetState;
                    if (currentNetState != i2) {
                        netStateCallback.stateChanged(currentNetState);
                        NetStateChangeManager netStateChangeManager = NetStateChangeManager.INSTANCE;
                        NetStateChangeManager.lastNetState = currentNetState;
                    }
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(Network network) {
                    int i2;
                    Intrinsics.checkNotNullParameter(network, "network");
                    super.onLost(network);
                    int currentNetState = NetStateManager.INSTANCE.getCurrentNetState(context);
                    i2 = NetStateChangeManager.lastNetState;
                    if (currentNetState != i2) {
                        netStateCallback.stateChanged(currentNetState);
                        NetStateChangeManager netStateChangeManager = NetStateChangeManager.INSTANCE;
                        NetStateChangeManager.lastNetState = currentNetState;
                    }
                }
            };
        }
        ConnectivityManager.NetworkCallback networkCallback2 = networkCallback;
        if (networkCallback2 != null) {
            connectivityManager.registerNetworkCallback(build, networkCallback2);
        }
    }

    private final void unregisterCallback(Context context) {
        ConnectivityManager.NetworkCallback networkCallback2 = networkCallback;
        if (networkCallback2 != null) {
            Object systemService = context.getSystemService("connectivity");
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
            ((ConnectivityManager) systemService).unregisterNetworkCallback(networkCallback2);
            networkCallback = null;
        }
    }

    private final void registerReceiver(Context context, NetStateCallback netStateCallback) {
        if (mNetStateBroadcast == null) {
            mNetStateBroadcast = new NetStateBroadcast(netStateCallback);
        }
        Context applicationContext = context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 26) {
            NetStateBroadcast netStateBroadcast = mNetStateBroadcast;
            applicationContext.registerReceiver(netStateBroadcast, netStateBroadcast != null ? netStateBroadcast.getIntentFilter() : null, 2);
            return;
        }
        NetStateBroadcast netStateBroadcast2 = mNetStateBroadcast;
        applicationContext.registerReceiver(netStateBroadcast2, netStateBroadcast2 != null ? netStateBroadcast2.getIntentFilter() : null);
    }

    private final void unregisterReceiver(Context context) {
        Context applicationContext = context.getApplicationContext();
        NetStateBroadcast netStateBroadcast = mNetStateBroadcast;
        if (netStateBroadcast != null) {
            applicationContext.unregisterReceiver(netStateBroadcast);
            mNetStateBroadcast = null;
        }
    }
}
