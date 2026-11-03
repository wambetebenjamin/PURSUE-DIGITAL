package com.ttnet.org.chromium.net;

import android.net.ConnectivityManager;
import com.bytedance.apm.perf.traffic.a;
import com.ttnet.org.chromium.base.ContextUtils;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.NativeClassQualifiedName;
@JNINamespace(a.KEY_TRAFFIC_STATUS_NET)
/* loaded from: classes9.dex */
public class NetworkActiveNotifier implements ConnectivityManager.OnNetworkActiveListener {
    private boolean mAreNotificationsEnabled;
    private final ConnectivityManager mConnectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity");
    private final long mNativeNetworkActiveObserver;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        @NativeClassQualifiedName("NetworkChangeNotifierDelegateAndroid")
        void notifyOfDefaultNetworkActive(long j2);
    }

    public static NetworkActiveNotifier build(long j2) {
        return new NetworkActiveNotifier(j2);
    }

    public void enableNotifications() {
        this.mAreNotificationsEnabled = true;
        this.mConnectivityManager.addDefaultNetworkActiveListener(this);
    }

    public void disableNotifications() {
        this.mAreNotificationsEnabled = false;
        this.mConnectivityManager.removeDefaultNetworkActiveListener(this);
    }

    public boolean isDefaultNetworkActive() {
        return this.mConnectivityManager.isDefaultNetworkActive();
    }

    @Override // android.net.ConnectivityManager.OnNetworkActiveListener
    public void onNetworkActive() {
        NetworkActiveNotifierJni.get().notifyOfDefaultNetworkActive(this.mNativeNetworkActiveObserver);
    }

    public void fakeDefaultNetworkActive() {
        if (this.mAreNotificationsEnabled) {
            onNetworkActive();
        }
    }

    private NetworkActiveNotifier(long j2) {
        this.mNativeNetworkActiveObserver = j2;
    }
}
