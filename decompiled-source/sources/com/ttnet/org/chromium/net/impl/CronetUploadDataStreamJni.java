package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.net.impl.CronetUploadDataStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class CronetUploadDataStreamJni implements CronetUploadDataStream.Natives {
    public static final JniStaticTestMocker<CronetUploadDataStream.Natives> TEST_HOOKS = new JniStaticTestMocker<CronetUploadDataStream.Natives>() { // from class: com.ttnet.org.chromium.net.impl.CronetUploadDataStreamJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(CronetUploadDataStream.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                CronetUploadDataStream.Natives unused = CronetUploadDataStreamJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static CronetUploadDataStream.Natives testInstance;

    CronetUploadDataStreamJni() {
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetUploadDataStream.Natives
    public long attachUploadDataToRequest(CronetUploadDataStream cronetUploadDataStream, long j2, long j3) {
        return GEN_JNI.com_ttnet_org_chromium_net_impl_CronetUploadDataStream_attachUploadDataToRequest(cronetUploadDataStream, j2, j3);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetUploadDataStream.Natives
    public long createAdapterForTesting(CronetUploadDataStream cronetUploadDataStream) {
        return GEN_JNI.com_ttnet_org_chromium_net_impl_CronetUploadDataStream_createAdapterForTesting(cronetUploadDataStream);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetUploadDataStream.Natives
    public long createUploadDataStreamForTesting(CronetUploadDataStream cronetUploadDataStream, long j2, long j3) {
        return GEN_JNI.com_ttnet_org_chromium_net_impl_CronetUploadDataStream_createUploadDataStreamForTesting(cronetUploadDataStream, j2, j3);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetUploadDataStream.Natives
    public void onReadSucceeded(long j2, CronetUploadDataStream cronetUploadDataStream, int i2, boolean z) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetUploadDataStream_onReadSucceeded(j2, cronetUploadDataStream, i2, z);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetUploadDataStream.Natives
    public void onRewindSucceeded(long j2, CronetUploadDataStream cronetUploadDataStream) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetUploadDataStream_onRewindSucceeded(j2, cronetUploadDataStream);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetUploadDataStream.Natives
    public void destroy(long j2) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetUploadDataStream_destroy(j2);
    }

    public static CronetUploadDataStream.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            CronetUploadDataStream.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.net.impl.CronetUploadDataStream.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new CronetUploadDataStreamJni();
    }
}
