package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.CpuFeatures;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class CpuFeaturesJni implements CpuFeatures.Natives {
    public static final JniStaticTestMocker<CpuFeatures.Natives> TEST_HOOKS = new JniStaticTestMocker<CpuFeatures.Natives>() { // from class: com.ttnet.org.chromium.base.CpuFeaturesJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(CpuFeatures.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                CpuFeatures.Natives unused = CpuFeaturesJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static CpuFeatures.Natives testInstance;

    CpuFeaturesJni() {
    }

    @Override // com.ttnet.org.chromium.base.CpuFeatures.Natives
    public int getCoreCount() {
        return GEN_JNI.com_ttnet_org_chromium_base_CpuFeatures_getCoreCount();
    }

    @Override // com.ttnet.org.chromium.base.CpuFeatures.Natives
    public long getCpuFeatures() {
        return GEN_JNI.com_ttnet_org_chromium_base_CpuFeatures_getCpuFeatures();
    }

    public static CpuFeatures.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            CpuFeatures.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.CpuFeatures.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new CpuFeaturesJni();
    }
}
