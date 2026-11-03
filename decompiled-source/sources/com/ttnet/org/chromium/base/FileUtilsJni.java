package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.FileUtils;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* loaded from: classes9.dex */
public class FileUtilsJni implements FileUtils.Natives {
    public static final JniStaticTestMocker<FileUtils.Natives> TEST_HOOKS = new JniStaticTestMocker<FileUtils.Natives>() { // from class: com.ttnet.org.chromium.base.FileUtilsJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(FileUtils.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                FileUtils.Natives unused = FileUtilsJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static FileUtils.Natives testInstance;

    @Override // com.ttnet.org.chromium.base.FileUtils.Natives
    public String getAbsoluteFilePath(String str) {
        return GEN_JNI.com_ttnet_org_chromium_base_FileUtils_getAbsoluteFilePath(str);
    }

    public static FileUtils.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            FileUtils.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.FileUtils.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new FileUtilsJni();
    }
}
