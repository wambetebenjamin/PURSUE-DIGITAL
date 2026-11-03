package com.ttnet.org.chromium.net.impl;

import android.text.TextUtils;
import android.util.Log;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.NativeClassQualifiedName;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
@JNINamespace("cronet")
/* loaded from: classes9.dex */
public class TTFrontierConnection {
    private static final int STATE_BUILDING = 1;
    private static final int STATE_CLOSE = 4;
    private static final int STATE_ESTABLISHED = 2;
    private static final int STATE_FAILED = 3;
    private static final int STATE_NONE = 0;
    private static final String TAG = "TTFrontierConnection";
    private int mAppId;
    private String mAppKey;
    private int mAppVersion;
    private BaseFrontierCallback mCallback;
    private long mDeviceId;
    private final Executor mExecutor;
    private int mFpid;
    private long mFrontierConnectionAdapter;
    private Map<String, String> mHeaders;
    private long mInstallId;
    private Map<String, String> mParams;
    private final CronetUrlRequestContext mRequestContext;
    private String mSessionId;
    private List<Integer> mUniqueServiceIdList;
    private List<String> mUrlList;
    private boolean mUseProxy;
    private final Object mFrontierConnectionAdapterLock = new Object();
    private AtomicInteger mState = new AtomicInteger(0);

    /* loaded from: classes9.dex */
    public static abstract class BaseFrontierCallback {
        public abstract void onAllServiceReady(TTFrontierConnection tTFrontierConnection, boolean z);

        public abstract void onConnectionError(TTFrontierConnection tTFrontierConnection, int i2, String str, String str2);

        public abstract void onConnectionMode(TTFrontierConnection tTFrontierConnection, int i2);

        public abstract void onConnectionStateChanged(TTFrontierConnection tTFrontierConnection, int i2, String str, String str2);

        public void onFeedbackLog(TTFrontierConnection tTFrontierConnection, String str) {
        }

        public abstract void onMessageReceived(TTFrontierConnection tTFrontierConnection, int i2, ByteBuffer byteBuffer, Map<String, String> map, boolean z);

        public void onTrafficChanged(TTFrontierConnection tTFrontierConnection, String str, long j2, long j3, boolean z) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        @NativeClassQualifiedName("TTFrontierConnectionAdapter")
        void addHeader(long j2, TTFrontierConnection tTFrontierConnection, String str, String str2);

        @NativeClassQualifiedName("TTFrontierConnectionAdapter")
        void addParam(long j2, TTFrontierConnection tTFrontierConnection, String str, String str2);

        @NativeClassQualifiedName("TTFrontierConnectionAdapter")
        void addServiceId(long j2, TTFrontierConnection tTFrontierConnection, int i2);

        @NativeClassQualifiedName("TTFrontierConnectionAdapter")
        void addUrl(long j2, TTFrontierConnection tTFrontierConnection, String str);

        long createFrontierConnectionAdapter(TTFrontierConnection tTFrontierConnection, long j2);

        @NativeClassQualifiedName("TTFrontierConnectionAdapter")
        void destroy(long j2, TTFrontierConnection tTFrontierConnection);

        @NativeClassQualifiedName("TTFrontierConnectionAdapter")
        void reportAppStateChange(long j2, TTFrontierConnection tTFrontierConnection, boolean z);

        @NativeClassQualifiedName("TTFrontierConnectionAdapter")
        void sendMessage(long j2, TTFrontierConnection tTFrontierConnection, int i2, String[] strArr, ByteBuffer byteBuffer);

        @NativeClassQualifiedName("TTFrontierConnectionAdapter")
        void startFrontierConnection(long j2, TTFrontierConnection tTFrontierConnection, int i2, int i3, int i4, long j3, String str, long j4, String str2, boolean z);

        @NativeClassQualifiedName("TTFrontierConnectionAdapter")
        void stopConnection(long j2, TTFrontierConnection tTFrontierConnection);
    }

    TTFrontierConnection(CronetUrlRequestContext cronetUrlRequestContext, BaseFrontierCallback baseFrontierCallback, Executor executor, Builder builder) {
        this.mRequestContext = cronetUrlRequestContext;
        this.mCallback = baseFrontierCallback;
        this.mExecutor = executor;
        this.mUrlList = builder.urls;
        this.mAppId = builder.appId;
        this.mFpid = builder.fpid;
        this.mAppVersion = builder.appVersion;
        this.mDeviceId = builder.deviceId;
        this.mAppKey = builder.appKey;
        this.mSessionId = builder.sessionId;
        this.mInstallId = builder.installId;
        this.mParams = builder.params;
        this.mHeaders = builder.headers;
        this.mUniqueServiceIdList = builder.uniqueServiceIdList;
        this.mUseProxy = builder.useProxy;
        createNativeFrontierConnection();
    }

    public static Builder create(List<String> list, int i2, int i3, int i4, long j2, String str) {
        return new Builder(list, i2, i3, i4, j2, str);
    }

    /* loaded from: classes9.dex */
    public static final class Builder {
        private int appId;
        private String appKey;
        private int appVersion;
        private BaseFrontierCallback callback;
        private long deviceId;
        private int fpid;
        private Map<String, String> headers;
        private long installId;
        private Map<String, String> params;
        private String sessionId;
        private List<Integer> uniqueServiceIdList;
        private List<String> urls;
        private boolean useProxy;

        Builder(List<String> list, int i2, int i3, int i4, long j2, String str) {
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException("urls must not be empty.");
            }
            this.urls = list;
            this.appId = i2;
            this.fpid = i3;
            this.appVersion = i4;
            this.deviceId = j2;
            this.appKey = str;
        }

        public Builder sessionId(String str) {
            this.sessionId = str;
            return this;
        }

        public Builder installId(long j2) {
            this.installId = j2;
            return this;
        }

        public Builder headers(Map<String, String> map) {
            this.headers = map;
            return this;
        }

        public Builder params(Map<String, String> map) {
            this.params = map;
            return this;
        }

        public Builder uniqueServiceIdList(List<Integer> list) {
            this.uniqueServiceIdList = list;
            return this;
        }

        public Builder proxy(boolean z) {
            this.useProxy = z;
            return this;
        }

        public TTFrontierConnection build(CronetUrlRequestContext cronetUrlRequestContext, BaseFrontierCallback baseFrontierCallback, Executor executor) {
            return new TTFrontierConnection(cronetUrlRequestContext, baseFrontierCallback, executor, this);
        }
    }

    public void startConnection() {
        synchronized (this.mFrontierConnectionAdapterLock) {
            try {
                try {
                    if (this.mFrontierConnectionAdapter == 0) {
                        return;
                    }
                    TTFrontierConnectionJni.get().startFrontierConnection(this.mFrontierConnectionAdapter, this, this.mAppId, this.mFpid, this.mAppVersion, this.mDeviceId, this.mAppKey, this.mInstallId, this.mSessionId, this.mUseProxy);
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

    public void stopConnection() {
        synchronized (this.mFrontierConnectionAdapterLock) {
            if (this.mFrontierConnectionAdapter == 0) {
                return;
            }
            TTFrontierConnectionJni.get().stopConnection(this.mFrontierConnectionAdapter, this);
        }
    }

    public void sendMessage(int i2, String[] strArr, ByteBuffer byteBuffer) {
        synchronized (this.mFrontierConnectionAdapterLock) {
            if (this.mFrontierConnectionAdapter == 0) {
                return;
            }
            TTFrontierConnectionJni.get().sendMessage(this.mFrontierConnectionAdapter, this, i2, strArr, byteBuffer);
        }
    }

    public void destroyConnection() {
        synchronized (this.mFrontierConnectionAdapterLock) {
            if (this.mFrontierConnectionAdapter == 0) {
                return;
            }
            TTFrontierConnectionJni.get().destroy(this.mFrontierConnectionAdapter, this);
            this.mFrontierConnectionAdapter = 0L;
        }
    }

    public void reportAppStateChange(boolean z) {
        synchronized (this.mFrontierConnectionAdapterLock) {
            if (this.mFrontierConnectionAdapter == 0) {
                return;
            }
            TTFrontierConnectionJni.get().reportAppStateChange(this.mFrontierConnectionAdapter, this, z);
        }
    }

    private void createNativeFrontierConnection() {
        synchronized (this.mFrontierConnectionAdapterLock) {
            if (this.mFrontierConnectionAdapter == 0) {
                this.mFrontierConnectionAdapter = TTFrontierConnectionJni.get().createFrontierConnectionAdapter(this, this.mRequestContext.getUrlRequestContextAdapter());
            }
            for (String str : this.mUrlList) {
                TTFrontierConnectionJni.get().addUrl(this.mFrontierConnectionAdapter, this, str);
            }
            Map<String, String> map = this.mParams;
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    TTFrontierConnectionJni.get().addParam(this.mFrontierConnectionAdapter, this, entry.getKey(), entry.getValue());
                }
            }
            Map<String, String> map2 = this.mHeaders;
            if (map2 != null) {
                for (Map.Entry<String, String> entry2 : map2.entrySet()) {
                    TTFrontierConnectionJni.get().addHeader(this.mFrontierConnectionAdapter, this, entry2.getKey(), entry2.getValue());
                }
            }
            List<Integer> list = this.mUniqueServiceIdList;
            if (list != null) {
                for (Integer num : list) {
                    TTFrontierConnectionJni.get().addServiceId(this.mFrontierConnectionAdapter, this, num.intValue());
                }
            }
        }
    }

    private void onReceivedMessage(int i2, String[] strArr, ByteBuffer byteBuffer, boolean z) {
        if (strArr == null) {
            Log.e(TAG, "metaInfo is null");
            return;
        }
        int length = strArr.length;
        if (length % 2 != 0) {
            Log.e(TAG, "length is not even number:" + length);
            return;
        }
        HashMap hashMap = new HashMap();
        int i3 = 0;
        while (true) {
            int i4 = i3 + 1;
            if (i4 < length) {
                if (!TextUtils.isEmpty(strArr[i3]) && !TextUtils.isEmpty(strArr[i4])) {
                    hashMap.put(strArr[i3], strArr[i4]);
                }
                i3 += 2;
            } else {
                try {
                    this.mCallback.onMessageReceived(this, i2, byteBuffer, hashMap, z);
                    return;
                } catch (Exception e2) {
                    Log.e(TAG, "Exception in callback: ", e2);
                    return;
                }
            }
        }
    }

    private void onConnectionStateChanged(final int i2, final String str, final String str2) {
        Log.v(TAG, "onConnectionStateChanged: state = " + i2 + ", url = " + str + ", log = " + str2);
        this.mState.set(i2);
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.TTFrontierConnection.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    TTFrontierConnection.this.mCallback.onConnectionStateChanged(TTFrontierConnection.this, i2, str, str2);
                } catch (Exception e2) {
                    Log.e(TTFrontierConnection.TAG, "Exception in callback: ", e2);
                }
            }
        });
    }

    private void onConnectionError(final int i2, final String str, final String str2) {
        Log.e(TAG, "onConnectionError: " + str2);
        this.mState.set(i2);
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.TTFrontierConnection.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    TTFrontierConnection.this.mCallback.onConnectionError(TTFrontierConnection.this, i2, str, str2);
                } catch (Exception e2) {
                    Log.e(TTFrontierConnection.TAG, "Exception in callback: ", e2);
                }
            }
        });
    }

    private void onAllServiceReady(boolean z) {
        Log.v(TAG, "onAllServiceReady: isPPDirect = " + z);
        try {
            this.mCallback.onAllServiceReady(this, z);
        } catch (Exception e2) {
            Log.e(TAG, "Exception in callback: ", e2);
        }
    }

    private void onConnectionMode(int i2) {
        Log.v(TAG, "onConnectionMode: mode = " + i2);
        try {
            this.mCallback.onConnectionMode(this, i2);
        } catch (Exception e2) {
            Log.e(TAG, "Exception in callback: ", e2);
        }
    }

    private void onTrafficChanged(final String str, final long j2, final long j3, final boolean z) {
        Log.v(TAG, "OnTrafficChanged");
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.TTFrontierConnection.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    TTFrontierConnection.this.mCallback.onTrafficChanged(TTFrontierConnection.this, str, j2, j3, z);
                } catch (Exception e2) {
                    Log.e(TTFrontierConnection.TAG, "Exception in callback: ", e2);
                }
            }
        });
    }

    private void postTaskToExecutor(Runnable runnable) {
        try {
            this.mExecutor.execute(runnable);
        } catch (RejectedExecutionException e2) {
            Log.e(TAG, "Exception posting task to executor", e2);
        }
    }

    public boolean isConnected() {
        return this.mState.get() == 2;
    }
}
