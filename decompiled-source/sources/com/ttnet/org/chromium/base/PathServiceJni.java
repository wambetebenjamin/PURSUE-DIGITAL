package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.PathService;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class PathServiceJni implements PathService.Natives {
    public static final JniStaticTestMocker<PathService.Natives> TEST_HOOKS = new JniStaticTestMocker<PathService.Natives>() { // from class: com.ttnet.org.chromium.base.PathServiceJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(PathService.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                PathService.Natives unused = PathServiceJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static PathService.Natives testInstance;

    PathServiceJni() {
    }

    @Override // com.ttnet.org.chromium.base.PathService.Natives
    public void override(int i2, String str) {
        GEN_JNI.com_ttnet_org_chromium_base_PathService_override(i2, str);
    }

    public static PathService.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            PathService.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.PathService.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new PathServiceJni();
    }
}
