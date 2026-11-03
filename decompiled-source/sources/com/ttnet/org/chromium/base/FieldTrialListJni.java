package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.FieldTrialList;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
@MainDex
/* loaded from: classes9.dex */
public class FieldTrialListJni implements FieldTrialList.Natives {
    public static final JniStaticTestMocker<FieldTrialList.Natives> TEST_HOOKS = new JniStaticTestMocker<FieldTrialList.Natives>() { // from class: com.ttnet.org.chromium.base.FieldTrialListJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(FieldTrialList.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                FieldTrialList.Natives unused = FieldTrialListJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static FieldTrialList.Natives testInstance;

    FieldTrialListJni() {
    }

    @Override // com.ttnet.org.chromium.base.FieldTrialList.Natives
    public String findFullName(String str) {
        return GEN_JNI.com_ttnet_org_chromium_base_FieldTrialList_findFullName(str);
    }

    @Override // com.ttnet.org.chromium.base.FieldTrialList.Natives
    public boolean trialExists(String str) {
        return GEN_JNI.com_ttnet_org_chromium_base_FieldTrialList_trialExists(str);
    }

    @Override // com.ttnet.org.chromium.base.FieldTrialList.Natives
    public String getVariationParameter(String str, String str2) {
        return GEN_JNI.com_ttnet_org_chromium_base_FieldTrialList_getVariationParameter(str, str2);
    }

    @Override // com.ttnet.org.chromium.base.FieldTrialList.Natives
    public void logActiveTrials() {
        GEN_JNI.com_ttnet_org_chromium_base_FieldTrialList_logActiveTrials();
    }

    @Override // com.ttnet.org.chromium.base.FieldTrialList.Natives
    public boolean createFieldTrial(String str, String str2) {
        return GEN_JNI.com_ttnet_org_chromium_base_FieldTrialList_createFieldTrial(str, str2);
    }

    public static FieldTrialList.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            FieldTrialList.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.FieldTrialList.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(true);
        return new FieldTrialListJni();
    }
}
