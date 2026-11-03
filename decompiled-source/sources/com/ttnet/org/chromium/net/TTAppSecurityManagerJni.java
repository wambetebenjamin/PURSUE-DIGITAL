package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.net.TTAppSecurityManager;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class TTAppSecurityManagerJni implements TTAppSecurityManager.Natives {
    public static final JniStaticTestMocker<TTAppSecurityManager.Natives> TEST_HOOKS = new JniStaticTestMocker<TTAppSecurityManager.Natives>() { // from class: com.ttnet.org.chromium.net.TTAppSecurityManagerJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(TTAppSecurityManager.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                TTAppSecurityManager.Natives unused = TTAppSecurityManagerJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static TTAppSecurityManager.Natives testInstance;

    TTAppSecurityManagerJni() {
    }

    @Override // com.ttnet.org.chromium.net.TTAppSecurityManager.Natives
    public boolean isUserPrivacyRestrictionEnabled() {
        return GEN_JNI.com_ttnet_org_chromium_net_TTAppSecurityManager_isUserPrivacyRestrictionEnabled();
    }

    @Override // com.ttnet.org.chromium.net.TTAppSecurityManager.Natives
    public boolean isTTPRestrictionEnabled() {
        return GEN_JNI.com_ttnet_org_chromium_net_TTAppSecurityManager_isTTPRestrictionEnabled();
    }

    public static TTAppSecurityManager.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            TTAppSecurityManager.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.net.TTAppSecurityManager.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new TTAppSecurityManagerJni();
    }
}
