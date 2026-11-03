package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.net.HttpNegotiateAuthenticator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class HttpNegotiateAuthenticatorJni implements HttpNegotiateAuthenticator.Natives {
    public static final JniStaticTestMocker<HttpNegotiateAuthenticator.Natives> TEST_HOOKS = new JniStaticTestMocker<HttpNegotiateAuthenticator.Natives>() { // from class: com.ttnet.org.chromium.net.HttpNegotiateAuthenticatorJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(HttpNegotiateAuthenticator.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                HttpNegotiateAuthenticator.Natives unused = HttpNegotiateAuthenticatorJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static HttpNegotiateAuthenticator.Natives testInstance;

    HttpNegotiateAuthenticatorJni() {
    }

    @Override // com.ttnet.org.chromium.net.HttpNegotiateAuthenticator.Natives
    public void setResult(long j2, HttpNegotiateAuthenticator httpNegotiateAuthenticator, int i2, String str) {
        GEN_JNI.com_ttnet_org_chromium_net_HttpNegotiateAuthenticator_setResult(j2, httpNegotiateAuthenticator, i2, str);
    }

    public static HttpNegotiateAuthenticator.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            HttpNegotiateAuthenticator.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.net.HttpNegotiateAuthenticator.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new HttpNegotiateAuthenticatorJni();
    }
}
