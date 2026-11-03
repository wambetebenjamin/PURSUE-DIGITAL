package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.net.impl.CronetBidirectionalStream;
import java.nio.ByteBuffer;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class CronetBidirectionalStreamJni implements CronetBidirectionalStream.Natives {
    public static final JniStaticTestMocker<CronetBidirectionalStream.Natives> TEST_HOOKS = new JniStaticTestMocker<CronetBidirectionalStream.Natives>() { // from class: com.ttnet.org.chromium.net.impl.CronetBidirectionalStreamJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(CronetBidirectionalStream.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                CronetBidirectionalStream.Natives unused = CronetBidirectionalStreamJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static CronetBidirectionalStream.Natives testInstance;

    CronetBidirectionalStreamJni() {
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetBidirectionalStream.Natives
    public long createBidirectionalStream(CronetBidirectionalStream cronetBidirectionalStream, long j2, boolean z, boolean z2, int i2, boolean z3, int i3, long j3) {
        return GEN_JNI.com_ttnet_org_chromium_net_impl_CronetBidirectionalStream_createBidirectionalStream(cronetBidirectionalStream, j2, z, z2, i2, z3, i3, j3);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetBidirectionalStream.Natives
    public int start(long j2, CronetBidirectionalStream cronetBidirectionalStream, String str, int i2, String str2, String[] strArr, boolean z) {
        return GEN_JNI.com_ttnet_org_chromium_net_impl_CronetBidirectionalStream_start(j2, cronetBidirectionalStream, str, i2, str2, strArr, z);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetBidirectionalStream.Natives
    public void sendRequestHeaders(long j2, CronetBidirectionalStream cronetBidirectionalStream) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetBidirectionalStream_sendRequestHeaders(j2, cronetBidirectionalStream);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetBidirectionalStream.Natives
    public boolean readData(long j2, CronetBidirectionalStream cronetBidirectionalStream, ByteBuffer byteBuffer, int i2, int i3) {
        return GEN_JNI.com_ttnet_org_chromium_net_impl_CronetBidirectionalStream_readData(j2, cronetBidirectionalStream, byteBuffer, i2, i3);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetBidirectionalStream.Natives
    public boolean writevData(long j2, CronetBidirectionalStream cronetBidirectionalStream, ByteBuffer[] byteBufferArr, int[] iArr, int[] iArr2, boolean z) {
        return GEN_JNI.com_ttnet_org_chromium_net_impl_CronetBidirectionalStream_writevData(j2, cronetBidirectionalStream, byteBufferArr, iArr, iArr2, z);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetBidirectionalStream.Natives
    public void destroy(long j2, CronetBidirectionalStream cronetBidirectionalStream, boolean z) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetBidirectionalStream_destroy(j2, cronetBidirectionalStream, z);
    }

    public static CronetBidirectionalStream.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            CronetBidirectionalStream.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.net.impl.CronetBidirectionalStream.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new CronetBidirectionalStreamJni();
    }
}
