package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.SysUtils;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
@MainDex
/* loaded from: classes9.dex */
public class SysUtilsJni implements SysUtils.Natives {
    public static final JniStaticTestMocker<SysUtils.Natives> TEST_HOOKS = new JniStaticTestMocker<SysUtils.Natives>() { // from class: com.ttnet.org.chromium.base.SysUtilsJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(SysUtils.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                SysUtils.Natives unused = SysUtilsJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static SysUtils.Natives testInstance;

    SysUtilsJni() {
    }

    @Override // com.ttnet.org.chromium.base.SysUtils.Natives
    public void logPageFaultCountToTracing() {
        GEN_JNI.com_ttnet_org_chromium_base_SysUtils_logPageFaultCountToTracing();
    }

    public static SysUtils.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            SysUtils.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.SysUtils.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(true);
        return new SysUtilsJni();
    }
}
