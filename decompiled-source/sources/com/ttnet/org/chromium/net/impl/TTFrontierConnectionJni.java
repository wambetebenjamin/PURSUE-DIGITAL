package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
import com.ttnet.org.chromium.net.impl.TTFrontierConnection;
import java.nio.ByteBuffer;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class TTFrontierConnectionJni implements TTFrontierConnection.Natives {
    public static final JniStaticTestMocker<TTFrontierConnection.Natives> TEST_HOOKS = new JniStaticTestMocker<TTFrontierConnection.Natives>() { // from class: com.ttnet.org.chromium.net.impl.TTFrontierConnectionJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(TTFrontierConnection.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                TTFrontierConnection.Natives unused = TTFrontierConnectionJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static TTFrontierConnection.Natives testInstance;

    TTFrontierConnectionJni() {
    }

    @Override // com.ttnet.org.chromium.net.impl.TTFrontierConnection.Natives
    public long createFrontierConnectionAdapter(TTFrontierConnection tTFrontierConnection, long j2) {
        return GEN_JNI.com_ttnet_org_chromium_net_impl_TTFrontierConnection_createFrontierConnectionAdapter(tTFrontierConnection, j2);
    }

    @Override // com.ttnet.org.chromium.net.impl.TTFrontierConnection.Natives
    public void startFrontierConnection(long j2, TTFrontierConnection tTFrontierConnection, int i2, int i3, int i4, long j3, String str, long j4, String str2, boolean z) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_TTFrontierConnection_startFrontierConnection(j2, tTFrontierConnection, i2, i3, i4, j3, str, j4, str2, z);
    }

    @Override // com.ttnet.org.chromium.net.impl.TTFrontierConnection.Natives
    public void sendMessage(long j2, TTFrontierConnection tTFrontierConnection, int i2, String[] strArr, ByteBuffer byteBuffer) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_TTFrontierConnection_sendMessage(j2, tTFrontierConnection, i2, strArr, byteBuffer);
    }

    @Override // com.ttnet.org.chromium.net.impl.TTFrontierConnection.Natives
    public void reportAppStateChange(long j2, TTFrontierConnection tTFrontierConnection, boolean z) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_TTFrontierConnection_reportAppStateChange(j2, tTFrontierConnection, z);
    }

    @Override // com.ttnet.org.chromium.net.impl.TTFrontierConnection.Natives
    public void stopConnection(long j2, TTFrontierConnection tTFrontierConnection) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_TTFrontierConnection_stopConnection(j2, tTFrontierConnection);
    }

    @Override // com.ttnet.org.chromium.net.impl.TTFrontierConnection.Natives
    public void destroy(long j2, TTFrontierConnection tTFrontierConnection) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_TTFrontierConnection_destroy(j2, tTFrontierConnection);
    }

    @Override // com.ttnet.org.chromium.net.impl.TTFrontierConnection.Natives
    public void addUrl(long j2, TTFrontierConnection tTFrontierConnection, String str) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_TTFrontierConnection_addUrl(j2, tTFrontierConnection, str);
    }

    @Override // com.ttnet.org.chromium.net.impl.TTFrontierConnection.Natives
    public void addHeader(long j2, TTFrontierConnection tTFrontierConnection, String str, String str2) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_TTFrontierConnection_addHeader(j2, tTFrontierConnection, str, str2);
    }

    @Override // com.ttnet.org.chromium.net.impl.TTFrontierConnection.Natives
    public void addParam(long j2, TTFrontierConnection tTFrontierConnection, String str, String str2) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_TTFrontierConnection_addParam(j2, tTFrontierConnection, str, str2);
    }

    @Override // com.ttnet.org.chromium.net.impl.TTFrontierConnection.Natives
    public void addServiceId(long j2, TTFrontierConnection tTFrontierConnection, int i2) {
        GEN_JNI.com_ttnet_org_chromium_net_impl_TTFrontierConnection_addServiceId(j2, tTFrontierConnection, i2);
    }

    public static TTFrontierConnection.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            TTFrontierConnection.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.net.impl.TTFrontierConnection.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new TTFrontierConnectionJni();
    }
}
