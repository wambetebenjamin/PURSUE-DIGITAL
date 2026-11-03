package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.net.impl.CronetLibraryLoader;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class CronetLibraryLoaderJni implements CronetLibraryLoader.Natives {
    public static final JniStaticTestMocker<CronetLibraryLoader.Natives> TEST_HOOKS = new JniStaticTestMocker<CronetLibraryLoader.Natives>() { // from class: com.ttnet.org.chromium.net.impl.CronetLibraryLoaderJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(CronetLibraryLoader.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                CronetLibraryLoader.Natives unused = CronetLibraryLoaderJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static CronetLibraryLoader.Natives testInstance;

    CronetLibraryLoaderJni() {
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetLibraryLoader.Natives
    public void cronetInitOnInitThread() {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetLibraryLoader_cronetInitOnInitThread();
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetLibraryLoader.Natives
    public String getCronetVersion() {
        return GEN_JNI.com_ttnet_org_chromium_net_impl_CronetLibraryLoader_getCronetVersion();
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetLibraryLoader.Natives
    public void createNetworkChangeNotifierOnInitThread() {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetLibraryLoader_createNetworkChangeNotifierOnInitThread();
    }

    public static CronetLibraryLoader.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            CronetLibraryLoader.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.net.impl.CronetLibraryLoader.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new CronetLibraryLoaderJni();
    }
}
