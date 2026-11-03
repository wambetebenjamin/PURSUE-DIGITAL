package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.net.GURLUtils;
/* loaded from: classes9.dex */
public class GURLUtilsJni implements GURLUtils.Natives {
    public static final JniStaticTestMocker<GURLUtils.Natives> TEST_HOOKS = new JniStaticTestMocker<GURLUtils.Natives>() { // from class: com.ttnet.org.chromium.net.GURLUtilsJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(GURLUtils.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                GURLUtils.Natives unused = GURLUtilsJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static GURLUtils.Natives testInstance;

    @Override // com.ttnet.org.chromium.net.GURLUtils.Natives
    public String getOrigin(String str) {
        return GEN_JNI.com_ttnet_org_chromium_net_GURLUtils_getOrigin(str);
    }

    public static GURLUtils.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            GURLUtils.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.net.GURLUtils.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new GURLUtilsJni();
    }
}
