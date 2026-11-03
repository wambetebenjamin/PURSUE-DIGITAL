package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.JavaHandlerThread;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
@MainDex
/* loaded from: classes9.dex */
public class JavaHandlerThreadJni implements JavaHandlerThread.Natives {
    public static final JniStaticTestMocker<JavaHandlerThread.Natives> TEST_HOOKS = new JniStaticTestMocker<JavaHandlerThread.Natives>() { // from class: com.ttnet.org.chromium.base.JavaHandlerThreadJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(JavaHandlerThread.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                JavaHandlerThread.Natives unused = JavaHandlerThreadJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static JavaHandlerThread.Natives testInstance;

    JavaHandlerThreadJni() {
    }

    @Override // com.ttnet.org.chromium.base.JavaHandlerThread.Natives
    public void initializeThread(long j2, long j3) {
        GEN_JNI.com_ttnet_org_chromium_base_JavaHandlerThread_initializeThread(j2, j3);
    }

    @Override // com.ttnet.org.chromium.base.JavaHandlerThread.Natives
    public void onLooperStopped(long j2) {
        GEN_JNI.com_ttnet_org_chromium_base_JavaHandlerThread_onLooperStopped(j2);
    }

    public static JavaHandlerThread.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            JavaHandlerThread.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.JavaHandlerThread.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(true);
        return new JavaHandlerThreadJni();
    }
}
