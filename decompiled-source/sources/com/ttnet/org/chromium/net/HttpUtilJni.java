package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.net.HttpUtil;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class HttpUtilJni implements HttpUtil.Natives {
    public static final JniStaticTestMocker<HttpUtil.Natives> TEST_HOOKS = new JniStaticTestMocker<HttpUtil.Natives>() { // from class: com.ttnet.org.chromium.net.HttpUtilJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(HttpUtil.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                HttpUtil.Natives unused = HttpUtilJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static HttpUtil.Natives testInstance;

    HttpUtilJni() {
    }

    @Override // com.ttnet.org.chromium.net.HttpUtil.Natives
    public boolean isAllowedHeader(String str, String str2) {
        return GEN_JNI.com_ttnet_org_chromium_net_HttpUtil_isAllowedHeader(str, str2);
    }

    public static HttpUtil.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            HttpUtil.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.net.HttpUtil.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new HttpUtilJni();
    }
}
