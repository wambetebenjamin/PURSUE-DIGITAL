package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.BaseFeatureList;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
@MainDex
/* loaded from: classes9.dex */
public class BaseFeatureListJni implements BaseFeatureList.Natives {
    public static final JniStaticTestMocker<BaseFeatureList.Natives> TEST_HOOKS = new JniStaticTestMocker<BaseFeatureList.Natives>() { // from class: com.ttnet.org.chromium.base.BaseFeatureListJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(BaseFeatureList.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                BaseFeatureList.Natives unused = BaseFeatureListJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static BaseFeatureList.Natives testInstance;

    BaseFeatureListJni() {
    }

    @Override // com.ttnet.org.chromium.base.BaseFeatureList.Natives
    public boolean isEnabled(String str) {
        return GEN_JNI.com_ttnet_org_chromium_base_BaseFeatureList_isEnabled(str);
    }

    public static BaseFeatureList.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            BaseFeatureList.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.BaseFeatureList.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(true);
        return new BaseFeatureListJni();
    }
}
