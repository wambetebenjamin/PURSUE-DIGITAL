package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.net.AndroidNetworkLibrary;
/* JADX INFO: Access modifiers changed from: package-private */
@MainDex
/* loaded from: classes9.dex */
public class AndroidNetworkLibraryJni implements AndroidNetworkLibrary.Natives {
    public static final JniStaticTestMocker<AndroidNetworkLibrary.Natives> TEST_HOOKS = new JniStaticTestMocker<AndroidNetworkLibrary.Natives>() { // from class: com.ttnet.org.chromium.net.AndroidNetworkLibraryJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(AndroidNetworkLibrary.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                AndroidNetworkLibrary.Natives unused = AndroidNetworkLibraryJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static AndroidNetworkLibrary.Natives testInstance;

    AndroidNetworkLibraryJni() {
    }

    @Override // com.ttnet.org.chromium.net.AndroidNetworkLibrary.Natives
    public void onCellularAlwaysUp(boolean z, int i2) {
        GEN_JNI.com_ttnet_org_chromium_net_AndroidNetworkLibrary_onCellularAlwaysUp(z, i2);
    }

    public static AndroidNetworkLibrary.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            AndroidNetworkLibrary.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.net.AndroidNetworkLibrary.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(true);
        return new AndroidNetworkLibraryJni();
    }
}
