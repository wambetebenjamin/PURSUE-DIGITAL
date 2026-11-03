package com.ttnet.org.chromium.net.impl;

import android.text.TextUtils;
import android.util.Log;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.NativeClassQualifiedName;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
@JNINamespace("cronet")
/* loaded from: classes9.dex */
public class CronetFrontierClient {
    private static final int STATE_BUILDING = 1;
    private static final int STATE_CLOSE = 4;
    private static final int STATE_ESTABLISHED = 2;
    private static final int STATE_FAILED = 3;
    private static final int STATE_NONE = 0;
    private static final String TAG = "CronetFrontierClient";
    private int mAppId;
    private String mAppKey;
    private String mAppVersion;
    private IConnectionCallback mCallback;
    private String mDeviceId;
    private String mEnv;
    private int mFpid;
    private long mFrontierClientAdapter;
    private final Object mFrontierClientAdapterLock;
    private Map<String, String> mHeaders;
    private String mHost;
    private TransportMode mMode;
    private String mPath;
    private int mPingInterval;
    private int mPort;
    private boolean mProxy;
    private String mQuery;
    private Map<Integer, TTServiceInfo> mServiceInfoMap;
    private AtomicInteger mState;
    private int mTimeout;
    private List<Integer> mUniqueServiceIdList;

    /* loaded from: classes9.dex */
    public interface IConnectionCallback {
        void onConnectionError(int i2, String str, String str2);

        void onConnectionStateChanged(int i2, String str);

        void onTrafficChanged(String str, long j2, long j3, boolean z);
    }

    /* loaded from: classes9.dex */
    public interface IServiceMessageReceiver {
        void onError(int i2, int i3, String str);

        void onReceivedAck(int i2, long j2, String str, Boolean bool);

        void onReceivedMessage(int i2, Map<String, String> map, byte[] bArr);

        void onServiceReady(int i2, String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        @NativeClassQualifiedName("CronetFrontierClientAdapter")
        void addHeader(long j2, CronetFrontierClient cronetFrontierClient, String str, String str2);

        @NativeClassQualifiedName("CronetFrontierClientAdapter")
        void addServiceId(long j2, CronetFrontierClient cronetFrontierClient, int i2);

        @NativeClassQualifiedName("CronetFrontierClientAdapter")
        void configParams(long j2, CronetFrontierClient cronetFrontierClient, int i2, int i3, String str, int i4, String str2, String str3, String str4, String str5, int i5, int i6, int i7, boolean z, String str6, String str7);

        long createFrontierClientAdapter(CronetFrontierClient cronetFrontierClient);

        @NativeClassQualifiedName("CronetFrontierClientAdapter")
        void destroy(long j2, CronetFrontierClient cronetFrontierClient);

        @NativeClassQualifiedName("CronetFrontierClientAdapter")
        boolean isServiceReady(long j2, CronetFrontierClient cronetFrontierClient, int i2);

        @NativeClassQualifiedName("CronetFrontierClientAdapter")
        void registerService(long j2, CronetFrontierClient cronetFrontierClient, String[] strArr, ByteBuffer byteBuffer, int i2, int i3, boolean z);

        @NativeClassQualifiedName("CronetFrontierClientAdapter")
        void reportAppStateChange(long j2, CronetFrontierClient cronetFrontierClient, boolean z);

        @NativeClassQualifiedName("CronetFrontierClientAdapter")
        void sendMessage(long j2, CronetFrontierClient cronetFrontierClient, int i2, String[] strArr, ByteBuffer byteBuffer);

        @NativeClassQualifiedName("CronetFrontierClientAdapter")
        void unregisterService(long j2, CronetFrontierClient cronetFrontierClient, int i2);
    }

    public void setMaxPacketSize(int i2, int i3) {
    }

    public void setMsgCursor(int i2, int i3) {
    }

    private CronetFrontierClient(Builder builder) {
        this.mMode = TransportMode.UNKNOWN;
        this.mServiceInfoMap = new ConcurrentHashMap();
        this.mFrontierClientAdapterLock = new Object();
        this.mState = new AtomicInteger(0);
        this.mAppId = builder.appId;
        this.mFpid = builder.fpid;
        this.mHost = builder.host;
        this.mPort = builder.port;
        this.mAppVersion = builder.appVersion;
        this.mDeviceId = builder.deviceId;
        this.mAppKey = builder.appKey;
        this.mEnv = builder.env;
        this.mPingInterval = builder.pingInterval;
        this.mTimeout = builder.timeout;
        this.mHeaders = builder.headers;
        this.mMode = builder.mode;
        this.mCallback = builder.callback;
        this.mUniqueServiceIdList = builder.uniqueServiceIdList;
        this.mQuery = builder.query;
        this.mPath = builder.path;
        this.mProxy = builder.proxy;
        createNativeFrontierClient();
    }

    /* loaded from: classes9.dex */
    public enum TransportMode {
        UNKNOWN(-1),
        QUIC(0),
        TLS(1),
        HTTP2(2),
        SPDY(3);
        
        final int mode;

        TransportMode(int i2) {
            this.mode = i2;
        }

        public int getValue() {
            return this.mode;
        }
    }

    /* loaded from: classes9.dex */
    public static final class Builder {
        private int appId;
        private String appKey;
        private String appVersion;
        private IConnectionCallback callback;
        private String deviceId;
        private String env;
        private int fpid;
        private Map<String, String> headers;
        private String host;
        private TransportMode mode = TransportMode.UNKNOWN;
        private String path;
        private int pingInterval;
        private int port;
        private boolean proxy;
        private String query;
        private int timeout;
        private List<Integer> uniqueServiceIdList;

        Builder(int i2, int i3, String str, int i4, String str2, String str3, String str4) {
            if (i2 <= 0 || i3 <= 0 || i4 <= 0) {
                throw new IllegalArgumentException("Appid, fpid and port must be set greater than 0.");
            }
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
                throw new IllegalArgumentException("Host, appVersion, appKey and deviceId must not be empty.");
            }
            this.appId = i2;
            this.fpid = i3;
            this.port = i4;
            this.host = str;
            this.appVersion = str2;
            this.deviceId = str3;
            this.appKey = str4;
        }

        public static Builder create(int i2, int i3, String str, int i4, String str2, String str3, String str4) {
            return new Builder(i2, i3, str, i4, str2, str3, str4);
        }

        public Builder env(String str) {
            this.env = str;
            return this;
        }

        public Builder pingInterval(int i2) {
            this.pingInterval = i2;
            return this;
        }

        public Builder timeout(int i2) {
            this.timeout = i2;
            return this;
        }

        public Builder headers(Map<String, String> map) {
            this.headers = map;
            return this;
        }

        public Builder transportMode(TransportMode transportMode) {
            this.mode = transportMode;
            return this;
        }

        public Builder callback(IConnectionCallback iConnectionCallback) {
            this.callback = iConnectionCallback;
            return this;
        }

        public Builder uniqueServiceIdList(List<Integer> list) {
            this.uniqueServiceIdList = list;
            return this;
        }

        public Builder proxy(boolean z) {
            this.proxy = z;
            return this;
        }

        public Builder query(String str) {
            this.query = str;
            return this;
        }

        public Builder path(String str) {
            this.path = str;
            return this;
        }

        public CronetFrontierClient build() {
            return new CronetFrontierClient(this);
        }
    }

    public void registerService(TTServiceInfo tTServiceInfo, ByteBuffer byteBuffer, String[] strArr) {
        synchronized (this.mFrontierClientAdapterLock) {
            if (this.mFrontierClientAdapter == 0) {
                return;
            }
            this.mServiceInfoMap.put(Integer.valueOf(tTServiceInfo.identity), tTServiceInfo);
            CronetFrontierClientJni.get().registerService(this.mFrontierClientAdapter, this, strArr, byteBuffer, tTServiceInfo.identity, tTServiceInfo.priority.getValue(), tTServiceInfo.sequence);
        }
    }

    public void unregisterService(int i2) {
        synchronized (this.mFrontierClientAdapterLock) {
            if (this.mFrontierClientAdapter == 0) {
                return;
            }
            this.mServiceInfoMap.remove(Integer.valueOf(i2));
            CronetFrontierClientJni.get().unregisterService(this.mFrontierClientAdapter, this, i2);
        }
    }

    public boolean isServiceExisted(int i2) {
        return this.mServiceInfoMap.containsKey(Integer.valueOf(i2));
    }

    public TTServiceInfo getServiceInfoById(int i2) {
        return this.mServiceInfoMap.get(Integer.valueOf(i2));
    }

    public void sendMessage(int i2, String[] strArr, ByteBuffer byteBuffer) {
        synchronized (this.mFrontierClientAdapterLock) {
            if (this.mFrontierClientAdapter == 0) {
                return;
            }
            CronetFrontierClientJni.get().sendMessage(this.mFrontierClientAdapter, this, i2, strArr, byteBuffer);
        }
    }

    public boolean isServiceReady(int i2) {
        synchronized (this.mFrontierClientAdapterLock) {
            if (this.mFrontierClientAdapter == 0) {
                return false;
            }
            TTServiceInfo tTServiceInfo = this.mServiceInfoMap.get(Integer.valueOf(i2));
            if (tTServiceInfo == null) {
                return false;
            }
            return tTServiceInfo.connected;
        }
    }

    public void destroy() {
        synchronized (this.mFrontierClientAdapterLock) {
            if (this.mFrontierClientAdapter == 0) {
                return;
            }
            CronetFrontierClientJni.get().destroy(this.mFrontierClientAdapter, this);
            this.mFrontierClientAdapter = 0L;
        }
    }

    public void reportAppStateChange(boolean z) {
        synchronized (this.mFrontierClientAdapterLock) {
            if (this.mFrontierClientAdapter == 0) {
                return;
            }
            CronetFrontierClientJni.get().reportAppStateChange(this.mFrontierClientAdapter, this, z);
        }
    }

    private void createNativeFrontierClient() {
        synchronized (this.mFrontierClientAdapterLock) {
            try {
                try {
                    if (this.mFrontierClientAdapter == 0) {
                        this.mFrontierClientAdapter = CronetFrontierClientJni.get().createFrontierClientAdapter(this);
                    }
                    Map<String, String> map = this.mHeaders;
                    if (map != null) {
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            CronetFrontierClientJni.get().addHeader(this.mFrontierClientAdapter, this, entry.getKey(), entry.getValue());
                        }
                    }
                    List<Integer> list = this.mUniqueServiceIdList;
                    if (list != null) {
                        for (Integer num : list) {
                            CronetFrontierClientJni.get().addServiceId(this.mFrontierClientAdapter, this, num.intValue());
                        }
                    }
                    CronetFrontierClientJni.get().configParams(this.mFrontierClientAdapter, this, this.mAppId, this.mFpid, this.mHost, this.mPort, this.mAppVersion, this.mDeviceId, this.mAppKey, this.mEnv, this.mPingInterval, this.mTimeout, this.mMode.getValue(), this.mProxy, this.mQuery, this.mPath);
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    private void onServiceReady(int i2, String str) {
        TTServiceInfo tTServiceInfo = this.mServiceInfoMap.get(Integer.valueOf(i2));
        if (tTServiceInfo == null) {
            return;
        }
        tTServiceInfo.connected = true;
        tTServiceInfo.listener.onServiceReady(i2, str);
    }

    private void onReceivedMessage(int i2, String[] strArr, ByteBuffer byteBuffer, int i3) {
        TTServiceInfo tTServiceInfo = this.mServiceInfoMap.get(Integer.valueOf(i2));
        if (tTServiceInfo == null) {
            return;
        }
        int length = strArr.length;
        if (length % 2 != 0) {
            Log.e(TAG, "length is not even number:" + length);
            return;
        }
        HashMap hashMap = new HashMap();
        for (int i4 = 0; i4 < length; i4 += 2) {
            if (!TextUtils.isEmpty(strArr[i4])) {
                int i5 = i4 + 1;
                if (!TextUtils.isEmpty(strArr[i5])) {
                    hashMap.put(strArr[i4], strArr[i5]);
                }
            }
        }
        byte[] bArr = new byte[byteBuffer.capacity()];
        byteBuffer.get(bArr);
        if (i3 >= 0) {
            i2 = i3;
        }
        tTServiceInfo.listener.onReceivedMessage(i2, hashMap, bArr);
    }

    private void onReceivedAck(int i2, long j2, String str, boolean z) {
        TTServiceInfo tTServiceInfo;
        if (z || (tTServiceInfo = this.mServiceInfoMap.get(Integer.valueOf(i2))) == null) {
            return;
        }
        tTServiceInfo.listener.onReceivedAck(i2, j2, str, Boolean.valueOf(z));
    }

    private void onError(int i2, int i3, String str) {
        TTServiceInfo tTServiceInfo = this.mServiceInfoMap.get(Integer.valueOf(i2));
        if (tTServiceInfo == null) {
            return;
        }
        tTServiceInfo.connected = false;
        tTServiceInfo.listener.onError(i2, i3, str);
    }

    private void onConnectionStateChanged(int i2, String str) {
        Log.v(TAG, "onConnectionStateChanged: state = " + i2 + ", url = " + str);
        this.mState.set(i2);
        try {
            this.mCallback.onConnectionStateChanged(i2, str);
        } catch (Exception e2) {
            Log.e(TAG, "Exception in callback: ", e2);
        }
    }

    private void onConnectionError(int i2, String str, String str2) {
        Log.e(TAG, "onConnectionError: " + str2);
        this.mState.set(i2);
        try {
            this.mCallback.onConnectionError(i2, str, str2);
        } catch (Exception e2) {
            Log.e(TAG, "Exception in callback: ", e2);
        }
    }

    private void onTrafficChanged(String str, long j2, long j3, boolean z) {
        Log.v(TAG, "OnTrafficChanged");
        try {
            this.mCallback.onTrafficChanged(str, j2, j3, z);
        } catch (Exception e2) {
            Log.e(TAG, "Exception in callback: ", e2);
        }
    }

    public boolean isConnected() {
        return this.mState.get() == 2;
    }
}
