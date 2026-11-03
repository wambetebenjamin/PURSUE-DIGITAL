package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.ImportantFileWriterAndroid;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class ImportantFileWriterAndroidJni implements ImportantFileWriterAndroid.Natives {
    public static final JniStaticTestMocker<ImportantFileWriterAndroid.Natives> TEST_HOOKS = new JniStaticTestMocker<ImportantFileWriterAndroid.Natives>() { // from class: com.ttnet.org.chromium.base.ImportantFileWriterAndroidJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(ImportantFileWriterAndroid.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                ImportantFileWriterAndroid.Natives unused = ImportantFileWriterAndroidJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static ImportantFileWriterAndroid.Natives testInstance;

    ImportantFileWriterAndroidJni() {
    }

    @Override // com.ttnet.org.chromium.base.ImportantFileWriterAndroid.Natives
    public boolean writeFileAtomically(String str, byte[] bArr) {
        return GEN_JNI.com_ttnet_org_chromium_base_ImportantFileWriterAndroid_writeFileAtomically(str, bArr);
    }

    public static ImportantFileWriterAndroid.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            ImportantFileWriterAndroid.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.ImportantFileWriterAndroid.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new ImportantFileWriterAndroidJni();
    }
}
