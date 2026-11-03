package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.net.X509Util;
/* JADX INFO: Access modifiers changed from: package-private */
@MainDex
/* loaded from: classes9.dex */
public class X509UtilJni implements X509Util.Natives {
    public static final JniStaticTestMocker<X509Util.Natives> TEST_HOOKS = new JniStaticTestMocker<X509Util.Natives>() { // from class: com.ttnet.org.chromium.net.X509UtilJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(X509Util.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                X509Util.Natives unused = X509UtilJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static X509Util.Natives testInstance;

    X509UtilJni() {
    }

    @Override // com.ttnet.org.chromium.net.X509Util.Natives
    public void notifyKeyChainChanged() {
        GEN_JNI.com_ttnet_org_chromium_net_X509Util_notifyKeyChainChanged();
    }

    public static X509Util.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            X509Util.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.net.X509Util.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(true);
        return new X509UtilJni();
    }
}
