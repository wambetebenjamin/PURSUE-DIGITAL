package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.net.impl.TTCronetNetExpRequest;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class TTCronetNetExpRequestJni implements TTCronetNetExpRequest.Natives {
    public static final JniStaticTestMocker<TTCronetNetExpRequest.Natives> TEST_HOOKS = new JniStaticTestMocker<TTCronetNetExpRequest.Natives>() { // from class: com.ttnet.org.chromium.net.impl.TTCronetNetExpRequestJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(TTCronetNetExpRequest.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                TTCronetNetExpRequest.Natives unused = TTCronetNetExpRequestJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static TTCronetNetExpRequest.Natives testInstance;

    TTCronetNetExpRequestJni() {
    }

    @Override // com.ttnet.org.chromium.net.impl.TTCronetNetExpRequest.Natives
    public long createRequestAdapter(TTCronetNetExpRequest tTCronetNetExpRequest, long j2, int i2, String[] strArr, int i3, int i4, int i5) {
        return GEN_JNI.com_ttnet_org_chromium_net_impl_TTCronetNetExpRequest_createRequestAdapter(tTCronetNetExpRequest, j2, i2, strArr, i3, i4, i5);
    }

    @Override // com.ttnet.org.chromium.net.impl.TTCronetNetExpRequest.Natives
    public void start(long j2, TTCronetNetExpRequest tTCronetNetExpRequest) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_TTCronetNetExpRequest_start(j2, tTCronetNetExpRequest);
    }

    @Override // com.ttnet.org.chromium.net.impl.TTCronetNetExpRequest.Natives
    public void destroy(long j2, TTCronetNetExpRequest tTCronetNetExpRequest) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_TTCronetNetExpRequest_destroy(j2, tTCronetNetExpRequest);
    }

    @Override // com.ttnet.org.chromium.net.impl.TTCronetNetExpRequest.Natives
    public void doExtraCommand(long j2, TTCronetNetExpRequest tTCronetNetExpRequest, String str, String str2) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_TTCronetNetExpRequest_doExtraCommand(j2, tTCronetNetExpRequest, str, str2);
    }

    public static TTCronetNetExpRequest.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            TTCronetNetExpRequest.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.net.impl.TTCronetNetExpRequest.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new TTCronetNetExpRequestJni();
    }
}
