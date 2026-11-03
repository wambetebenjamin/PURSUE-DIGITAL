package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.net.NetworkChangeNotifier;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class NetworkChangeNotifierJni implements NetworkChangeNotifier.Natives {
    public static final JniStaticTestMocker<NetworkChangeNotifier.Natives> TEST_HOOKS = new JniStaticTestMocker<NetworkChangeNotifier.Natives>() { // from class: com.ttnet.org.chromium.net.NetworkChangeNotifierJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(NetworkChangeNotifier.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                NetworkChangeNotifier.Natives unused = NetworkChangeNotifierJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static NetworkChangeNotifier.Natives testInstance;

    NetworkChangeNotifierJni() {
    }

    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifier.Natives
    public void notifyConnectionTypeChanged(long j2, NetworkChangeNotifier networkChangeNotifier, int i2, long j3) {
        GEN_JNI.com_ttnet_org_chromium_net_NetworkChangeNotifier_notifyConnectionTypeChanged(j2, networkChangeNotifier, i2, j3);
    }

    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifier.Natives
    public void notifyConnectionCostChanged(long j2, NetworkChangeNotifier networkChangeNotifier, int i2) {
        GEN_JNI.com_ttnet_org_chromium_net_NetworkChangeNotifier_notifyConnectionCostChanged(j2, networkChangeNotifier, i2);
    }

    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifier.Natives
    public void notifyMaxBandwidthChanged(long j2, NetworkChangeNotifier networkChangeNotifier, int i2) {
        GEN_JNI.com_ttnet_org_chromium_net_NetworkChangeNotifier_notifyMaxBandwidthChanged(j2, networkChangeNotifier, i2);
    }

    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifier.Natives
    public void notifyOfNetworkConnect(long j2, NetworkChangeNotifier networkChangeNotifier, long j3, boolean z, int i2) {
        GEN_JNI.com_ttnet_org_chromium_net_NetworkChangeNotifier_notifyOfNetworkConnect(j2, networkChangeNotifier, j3, z, i2);
    }

    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifier.Natives
    public void notifyOfNetworkSoonToDisconnect(long j2, NetworkChangeNotifier networkChangeNotifier, long j3) {
        GEN_JNI.com_ttnet_org_chromium_net_NetworkChangeNotifier_notifyOfNetworkSoonToDisconnect(j2, networkChangeNotifier, j3);
    }

    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifier.Natives
    public void notifyOfNetworkDisconnect(long j2, NetworkChangeNotifier networkChangeNotifier, boolean z, long j3) {
        GEN_JNI.com_ttnet_org_chromium_net_NetworkChangeNotifier_notifyOfNetworkDisconnect(j2, networkChangeNotifier, z, j3);
    }

    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifier.Natives
    public void notifyPurgeActiveNetworkList(long j2, NetworkChangeNotifier networkChangeNotifier, long[] jArr) {
        GEN_JNI.com_ttnet_org_chromium_net_NetworkChangeNotifier_notifyPurgeActiveNetworkList(j2, networkChangeNotifier, jArr);
    }

    public static NetworkChangeNotifier.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            NetworkChangeNotifier.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.net.NetworkChangeNotifier.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new NetworkChangeNotifierJni();
    }
}
