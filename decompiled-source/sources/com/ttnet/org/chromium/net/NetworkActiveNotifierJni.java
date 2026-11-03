package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.net.NetworkActiveNotifier;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class NetworkActiveNotifierJni implements NetworkActiveNotifier.Natives {
    public static final JniStaticTestMocker<NetworkActiveNotifier.Natives> TEST_HOOKS = new JniStaticTestMocker<NetworkActiveNotifier.Natives>() { // from class: com.ttnet.org.chromium.net.NetworkActiveNotifierJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(NetworkActiveNotifier.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                NetworkActiveNotifier.Natives unused = NetworkActiveNotifierJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static NetworkActiveNotifier.Natives testInstance;

    NetworkActiveNotifierJni() {
    }

    @Override // com.ttnet.org.chromium.net.NetworkActiveNotifier.Natives
    public void notifyOfDefaultNetworkActive(long j2) {
        GEN_JNI.com_ttnet_org_chromium_net_NetworkActiveNotifier_notifyOfDefaultNetworkActive(j2);
    }

    public static NetworkActiveNotifier.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            NetworkActiveNotifier.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.net.NetworkActiveNotifier.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new NetworkActiveNotifierJni();
    }
}
