package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.net.impl.CronetWebsocketConnection;
import java.nio.ByteBuffer;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class CronetWebsocketConnectionJni implements CronetWebsocketConnection.Natives {
    public static final JniStaticTestMocker<CronetWebsocketConnection.Natives> TEST_HOOKS = new JniStaticTestMocker<CronetWebsocketConnection.Natives>() { // from class: com.ttnet.org.chromium.net.impl.CronetWebsocketConnectionJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(CronetWebsocketConnection.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                CronetWebsocketConnection.Natives unused = CronetWebsocketConnectionJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static CronetWebsocketConnection.Natives testInstance;

    CronetWebsocketConnectionJni() {
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.Natives
    public long createWebsocketConnectionAdapter(CronetWebsocketConnection cronetWebsocketConnection, long j2) {
        return GEN_JNI.com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_createWebsocketConnectionAdapter(cronetWebsocketConnection, j2);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.Natives
    public void startWithFrontier(long j2, CronetWebsocketConnection cronetWebsocketConnection, int i2, String str, long j3, int i3, long j4, String str2, int i4, String str3, boolean z) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_startWithFrontier(j2, cronetWebsocketConnection, i2, str, j3, i3, j4, str2, i4, str3, z);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.Natives
    public void startWithWSChannel(long j2, CronetWebsocketConnection cronetWebsocketConnection, boolean z) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_startWithWSChannel(j2, cronetWebsocketConnection, z);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.Natives
    public void stop(long j2, CronetWebsocketConnection cronetWebsocketConnection) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_stop(j2, cronetWebsocketConnection);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.Natives
    public void sendText(long j2, CronetWebsocketConnection cronetWebsocketConnection, String str) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_sendText(j2, cronetWebsocketConnection, str);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.Natives
    public void sendBinary(long j2, CronetWebsocketConnection cronetWebsocketConnection, ByteBuffer byteBuffer) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_sendBinary(j2, cronetWebsocketConnection, byteBuffer);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.Natives
    public void destroy(long j2, CronetWebsocketConnection cronetWebsocketConnection) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_destroy(j2, cronetWebsocketConnection);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.Natives
    public void addUrl(long j2, CronetWebsocketConnection cronetWebsocketConnection, String str) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_addUrl(j2, cronetWebsocketConnection, str);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.Natives
    public void addHeader(long j2, CronetWebsocketConnection cronetWebsocketConnection, String str, String str2) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_addHeader(j2, cronetWebsocketConnection, str, str2);
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.Natives
    public void addGetParam(long j2, CronetWebsocketConnection cronetWebsocketConnection, String str, String str2) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_CronetWebsocketConnection_addGetParam(j2, cronetWebsocketConnection, str, str2);
    }

    public static CronetWebsocketConnection.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            CronetWebsocketConnection.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new CronetWebsocketConnectionJni();
    }
}
