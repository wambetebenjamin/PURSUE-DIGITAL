package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.ApplicationStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class ApplicationStatusJni implements ApplicationStatus.Natives {
    public static final JniStaticTestMocker<ApplicationStatus.Natives> TEST_HOOKS = new JniStaticTestMocker<ApplicationStatus.Natives>() { // from class: com.ttnet.org.chromium.base.ApplicationStatusJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(ApplicationStatus.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                ApplicationStatus.Natives unused = ApplicationStatusJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static ApplicationStatus.Natives testInstance;

    ApplicationStatusJni() {
    }

    @Override // com.ttnet.org.chromium.base.ApplicationStatus.Natives
    public void onApplicationStateChange(int i2) {
        GEN_JNI.com_ttnet_org_chromium_base_ApplicationStatus_onApplicationStateChange(i2);
    }

    public static ApplicationStatus.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            ApplicationStatus.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.ApplicationStatus.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new ApplicationStatusJni();
    }
}
