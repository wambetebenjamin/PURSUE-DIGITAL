package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.Features;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
@MainDex
/* loaded from: classes9.dex */
public class FeaturesJni implements Features.Natives {
    public static final JniStaticTestMocker<Features.Natives> TEST_HOOKS = new JniStaticTestMocker<Features.Natives>() { // from class: com.ttnet.org.chromium.base.FeaturesJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(Features.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                Features.Natives unused = FeaturesJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static Features.Natives testInstance;

    FeaturesJni() {
    }

    @Override // com.ttnet.org.chromium.base.Features.Natives
    public boolean isEnabled(long j2) {
        return GEN_JNI.com_ttnet_org_chromium_base_Features_isEnabled(j2);
    }

    @Override // com.ttnet.org.chromium.base.Features.Natives
    public boolean getFieldTrialParamByFeatureAsBoolean(long j2, String str, boolean z) {
        return GEN_JNI.com_ttnet_org_chromium_base_Features_getFieldTrialParamByFeatureAsBoolean(j2, str, z);
    }

    public static Features.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            Features.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.Features.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(true);
        return new FeaturesJni();
    }
}
