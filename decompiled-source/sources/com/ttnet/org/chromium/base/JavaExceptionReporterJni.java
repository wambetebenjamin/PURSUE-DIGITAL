package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.JavaExceptionReporter;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
@MainDex
/* loaded from: classes9.dex */
public class JavaExceptionReporterJni implements JavaExceptionReporter.Natives {
    public static final JniStaticTestMocker<JavaExceptionReporter.Natives> TEST_HOOKS = new JniStaticTestMocker<JavaExceptionReporter.Natives>() { // from class: com.ttnet.org.chromium.base.JavaExceptionReporterJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(JavaExceptionReporter.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                JavaExceptionReporter.Natives unused = JavaExceptionReporterJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static JavaExceptionReporter.Natives testInstance;

    JavaExceptionReporterJni() {
    }

    @Override // com.ttnet.org.chromium.base.JavaExceptionReporter.Natives
    public void reportJavaException(boolean z, Throwable th) {
        GEN_JNI.com_ttnet_org_chromium_base_JavaExceptionReporter_reportJavaException(z, th);
    }

    @Override // com.ttnet.org.chromium.base.JavaExceptionReporter.Natives
    public void reportJavaStackTrace(String str) {
        GEN_JNI.com_ttnet_org_chromium_base_JavaExceptionReporter_reportJavaStackTrace(str);
    }

    public static JavaExceptionReporter.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            JavaExceptionReporter.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.JavaExceptionReporter.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(true);
        return new JavaExceptionReporterJni();
    }
}
