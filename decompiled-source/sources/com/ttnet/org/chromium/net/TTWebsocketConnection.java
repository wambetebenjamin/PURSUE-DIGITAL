package com.ttnet.org.chromium.net;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public abstract class TTWebsocketConnection {

    /* loaded from: classes9.dex */
    public static abstract class Builder {
        public abstract TTWebsocketConnection build();

        public abstract Builder setAppId(int i2);

        public abstract Builder setAppKey(String str);

        public abstract Builder setAppToken(String str);

        public abstract Builder setAppVersion(int i2);

        public abstract Builder setCustomizedHeaders(Map<String, String> map);

        public abstract Builder setCustomizedParams(Map<String, String> map);

        public abstract Builder setDeviceId(long j2);

        public abstract Builder setFpid(int i2);

        public abstract Builder setInstallId(long j2);

        public abstract Builder setSessionId(String str);

        public abstract Builder setSharedConnection(boolean z);

        public abstract Builder setUrlList(List<String> list);

        public abstract Builder useFrontierProtocol(boolean z);
    }

    /* loaded from: classes9.dex */
    public static abstract class Callback {
        public abstract void onConnectionError(TTWebsocketConnection tTWebsocketConnection, int i2, String str, String str2);

        public abstract void onConnectionStateChanged(TTWebsocketConnection tTWebsocketConnection, int i2, String str);

        public void onFeedbackLog(TTWebsocketConnection tTWebsocketConnection, String str) {
        }

        public abstract void onMessageReceived(TTWebsocketConnection tTWebsocketConnection, ByteBuffer byteBuffer, int i2);

        public void onTrafficChanged(TTWebsocketConnection tTWebsocketConnection, String str, long j2, long j3, boolean z) {
        }
    }

    public abstract void asyncSendBinary(ByteBuffer byteBuffer);

    public abstract void asyncSendText(String str);

    public abstract void destroyConnection();

    public abstract boolean isConnected();

    public abstract void startConnection();

    public abstract void stopConnection();

    /* loaded from: classes9.dex */
    public static class Status {
        public static final int ConnectClosed = 3;
        public static final int ConnectFailed = 2;
        public static final int ConnectUnknown = -1;
        public static final int Connected = 4;
        public static final int Connecting = 0;
        public static final int Disconnecting = 1;

        private Status() {
        }
    }
}
