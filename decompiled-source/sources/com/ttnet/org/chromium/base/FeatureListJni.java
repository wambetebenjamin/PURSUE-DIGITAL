package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.FeatureList;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
@MainDex
/* loaded from: classes9.dex */
public class FeatureListJni implements FeatureList.Natives {
    public static final JniStaticTestMocker<FeatureList.Natives> TEST_HOOKS = new JniStaticTestMocker<FeatureList.Natives>() { // from class: com.ttnet.org.chromium.base.FeatureListJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(FeatureList.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                FeatureList.Natives unused = FeatureListJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static FeatureList.Natives testInstance;

    @Override // com.ttnet.org.chromium.base.FeatureList.Natives
    public boolean isInitialized() {
        return GEN_JNI.com_ttnet_org_chromium_base_FeatureList_isInitialized();
    }

    public static FeatureList.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            FeatureList.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.FeatureList.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(true);
        return new FeatureListJni();
    }
}
