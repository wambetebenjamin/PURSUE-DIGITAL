package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.net.impl.CronetFrontierClient;
import java.nio.ByteBuffer;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class CronetFrontierClientJni implements CronetFrontierClient.Natives {
    public static final JniStaticTestMocker<CronetFrontierClient.Natives> TEST_HOOKS = new JniStaticTestMocker<CronetFrontierClient.Natives>() { // from class: com.ttnet.org.chromium.net.impl.CronetFrontierClientJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(CronetFrontierClient.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                CronetFrontierClient.Natives unused = CronetFrontierClientJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static CronetFrontierClient.Natives testInstance;

    CronetFrontierClientJni() {
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetFrontierClient.Natives
    public long createFrontierClientAdapter(CronetFrontierClient cronetFrontierClient) {
        return GEN_JNI.com_ttnet_org_chromium_net_impl_CronetFrontierClient_createFrontierClientAdapter(cronetFrontierClient);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetFrontierClient.Natives
    public void addHeader(long j2, CronetFrontierClient cronetFrontierClient, String str, String str2) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetFrontierClient_addHeader(j2, cronetFrontierClient, str, str2);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetFrontierClient.Natives
    public void addServiceId(long j2, CronetFrontierClient cronetFrontierClient, int i2) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetFrontierClient_addServiceId(j2, cronetFrontierClient, i2);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetFrontierClient.Natives
    public void configParams(long j2, CronetFrontierClient cronetFrontierClient, int i2, int i3, String str, int i4, String str2, String str3, String str4, String str5, int i5, int i6, int i7, boolean z, String str6, String str7) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetFrontierClient_configParams(j2, cronetFrontierClient, i2, i3, str, i4, str2, str3, str4, str5, i5, i6, i7, z, str6, str7);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetFrontierClient.Natives
    public void registerService(long j2, CronetFrontierClient cronetFrontierClient, String[] strArr, ByteBuffer byteBuffer, int i2, int i3, boolean z) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetFrontierClient_registerService(j2, cronetFrontierClient, strArr, byteBuffer, i2, i3, z);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetFrontierClient.Natives
    public void unregisterService(long j2, CronetFrontierClient cronetFrontierClient, int i2) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetFrontierClient_unregisterService(j2, cronetFrontierClient, i2);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetFrontierClient.Natives
    public boolean isServiceReady(long j2, CronetFrontierClient cronetFrontierClient, int i2) {
        return GEN_JNI.com_ttnet_org_chromium_net_impl_CronetFrontierClient_isServiceReady(j2, cronetFrontierClient, i2);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetFrontierClient.Natives
    public void sendMessage(long j2, CronetFrontierClient cronetFrontierClient, int i2, String[] strArr, ByteBuffer byteBuffer) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetFrontierClient_sendMessage(j2, cronetFrontierClient, i2, strArr, byteBuffer);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetFrontierClient.Natives
    public void reportAppStateChange(long j2, CronetFrontierClient cronetFrontierClient, boolean z) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetFrontierClient_reportAppStateChange(j2, cronetFrontierClient, z);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetFrontierClient.Natives
    public void destroy(long j2, CronetFrontierClient cronetFrontierClient) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetFrontierClient_destroy(j2, cronetFrontierClient);
    }

    public static CronetFrontierClient.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            CronetFrontierClient.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.net.impl.CronetFrontierClient.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new CronetFrontierClientJni();
    }
}
