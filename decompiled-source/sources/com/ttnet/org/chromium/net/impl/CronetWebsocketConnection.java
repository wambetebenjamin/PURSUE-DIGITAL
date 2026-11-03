package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.NativeClassQualifiedName;
import com.ttnet.org.chromium.net.TTWebsocketConnection;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
@JNINamespace("cronet")
/* loaded from: classes9.dex */
public final class CronetWebsocketConnection extends TTWebsocketConnection {
    private static final String TAG = "CronetWebsocketConnection";
    private int mAppId;
    private String mAppKey;
    private String mAppToken;
    private int mAppVersion;
    private final TTWebsocketConnection.Callback mCallback;
    private long mDeviceId;
    private final Executor mExecutor;
    private int mFpid;
    private Map<String, String> mHeaders;
    private long mInstallId;
    private Map<String, String> mParams;
    private final CronetUrlRequestContext mRequestContext;
    private String mSessionId;
    private boolean mSharedConnection;
    private AtomicInteger mState;
    private List<String> mUrlList;
    private boolean mUseFrontier;
    private long mWebsocketAdapter;
    private final Object mWebsocketAdapterLock;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        @NativeClassQualifiedName("CronetWebsocketConnectionAdapter")
        void addGetParam(long j2, CronetWebsocketConnection cronetWebsocketConnection, String str, String str2);

        @NativeClassQualifiedName("CronetWebsocketConnectionAdapter")
        void addHeader(long j2, CronetWebsocketConnection cronetWebsocketConnection, String str, String str2);

        @NativeClassQualifiedName("CronetWebsocketConnectionAdapter")
        void addUrl(long j2, CronetWebsocketConnection cronetWebsocketConnection, String str);

        long createWebsocketConnectionAdapter(CronetWebsocketConnection cronetWebsocketConnection, long j2);

        @NativeClassQualifiedName("CronetWebsocketConnectionAdapter")
        void destroy(long j2, CronetWebsocketConnection cronetWebsocketConnection);

        @NativeClassQualifiedName("CronetWebsocketConnectionAdapter")
        void sendBinary(long j2, CronetWebsocketConnection cronetWebsocketConnection, ByteBuffer byteBuffer);

        @NativeClassQualifiedName("CronetWebsocketConnectionAdapter")
        void sendText(long j2, CronetWebsocketConnection cronetWebsocketConnection, String str);

        @NativeClassQualifiedName("CronetWebsocketConnectionAdapter")
        void startWithFrontier(long j2, CronetWebsocketConnection cronetWebsocketConnection, int i2, String str, long j3, int i3, long j4, String str2, int i4, String str3, boolean z);

        @NativeClassQualifiedName("CronetWebsocketConnectionAdapter")
        void startWithWSChannel(long j2, CronetWebsocketConnection cronetWebsocketConnection, boolean z);

        @NativeClassQualifiedName("CronetWebsocketConnectionAdapter")
        void stop(long j2, CronetWebsocketConnection cronetWebsocketConnection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CronetWebsocketConnection(CronetUrlRequestContext cronetUrlRequestContext, TTWebsocketConnection.Callback callback, Executor executor, List<String> list, int i2, String str, long j2, int i3, long j3, String str2, int i4, String str3, Map<String, String> map, Map<String, String> map2, boolean z) {
        this.mUseFrontier = true;
        this.mWebsocketAdapterLock = new Object();
        this.mState = new AtomicInteger(-1);
        this.mRequestContext = cronetUrlRequestContext;
        this.mCallback = callback;
        this.mExecutor = executor;
        this.mUrlList = list;
        this.mAppId = i2;
        this.mAppKey = str;
        this.mDeviceId = j2;
        this.mFpid = i3;
        this.mInstallId = j3;
        this.mSessionId = str2;
        this.mAppVersion = i4;
        this.mAppToken = str3;
        this.mParams = map;
        this.mHeaders = map2;
        this.mSharedConnection = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CronetWebsocketConnection(CronetUrlRequestContext cronetUrlRequestContext, TTWebsocketConnection.Callback callback, Executor executor, List<String> list, Map<String, String> map, Map<String, String> map2, boolean z) {
        this.mUseFrontier = true;
        this.mWebsocketAdapterLock = new Object();
        this.mState = new AtomicInteger(-1);
        this.mRequestContext = cronetUrlRequestContext;
        this.mCallback = callback;
        this.mExecutor = executor;
        this.mUrlList = list;
        this.mParams = map;
        this.mHeaders = map2;
        this.mSharedConnection = z;
        this.mUseFrontier = false;
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection
    public void startConnection() {
        Object obj;
        Object obj2 = this.mWebsocketAdapterLock;
        synchronized (obj2) {
            try {
                try {
                    if (this.mWebsocketAdapter == 0) {
                        this.mWebsocketAdapter = CronetWebsocketConnectionJni.get().createWebsocketConnectionAdapter(this, this.mRequestContext.getUrlRequestContextAdapter());
                    }
                    for (String str : this.mUrlList) {
                        CronetWebsocketConnectionJni.get().addUrl(this.mWebsocketAdapter, this, str);
                    }
                    Map<String, String> map = this.mParams;
                    if (map != null) {
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            CronetWebsocketConnectionJni.get().addGetParam(this.mWebsocketAdapter, this, entry.getKey(), entry.getValue());
                        }
                    }
                    Map<String, String> map2 = this.mHeaders;
                    if (map2 != null) {
                        for (Map.Entry<String, String> entry2 : map2.entrySet()) {
                            CronetWebsocketConnectionJni.get().addHeader(this.mWebsocketAdapter, this, entry2.getKey(), entry2.getValue());
                        }
                    }
                    try {
                        if (this.mUseFrontier) {
                            try {
                                obj = obj2;
                                CronetWebsocketConnectionJni.get().startWithFrontier(this.mWebsocketAdapter, this, this.mAppId, this.mAppKey, this.mDeviceId, this.mFpid, this.mInstallId, this.mSessionId, this.mAppVersion, this.mAppToken, this.mSharedConnection);
                            } catch (Throwable th) {
                                th = th;
                                obj = obj2;
                                throw th;
                            }
                        } else {
                            obj = obj2;
                            CronetWebsocketConnectionJni.get().startWithWSChannel(this.mWebsocketAdapter, this, this.mSharedConnection);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    obj = obj2;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection
    public void stopConnection() {
        synchronized (this.mWebsocketAdapterLock) {
            if (this.mWebsocketAdapter == 0) {
                return;
            }
            CronetWebsocketConnectionJni.get().stop(this.mWebsocketAdapter, this);
        }
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection
    public void asyncSendText(String str) {
        synchronized (this.mWebsocketAdapterLock) {
            if (this.mWebsocketAdapter == 0) {
                return;
            }
            CronetWebsocketConnectionJni.get().sendText(this.mWebsocketAdapter, this, str);
        }
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection
    public void asyncSendBinary(ByteBuffer byteBuffer) {
        synchronized (this.mWebsocketAdapterLock) {
            if (this.mWebsocketAdapter == 0) {
                return;
            }
            CronetWebsocketConnectionJni.get().sendBinary(this.mWebsocketAdapter, this, byteBuffer);
        }
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection
    public void destroyConnection() {
        synchronized (this.mWebsocketAdapterLock) {
            if (this.mWebsocketAdapter == 0) {
                return;
            }
            CronetWebsocketConnectionJni.get().destroy(this.mWebsocketAdapter, this);
            this.mWebsocketAdapter = 0L;
        }
    }

    @Override // com.ttnet.org.chromium.net.TTWebsocketConnection
    public boolean isConnected() {
        return this.mState.get() == 4;
    }

    private void onConnectionStateChanged(final int i2, final String str) {
        Log.d(TAG, "onConnectionStateChanged: state = " + i2 + ", url = " + str, new Object[0]);
        this.mState.set(i2);
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CronetWebsocketConnection.this.mCallback.onConnectionStateChanged(CronetWebsocketConnection.this, i2, str);
                } catch (Exception e2) {
                    Log.e(CronetWebsocketConnection.TAG, "Exception in callback: ", e2);
                }
            }
        });
    }

    private void onConnectionError(final int i2, final String str, final String str2) {
        Log.d(TAG, "onConnectionError: " + str2, new Object[0]);
        this.mState.set(i2);
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CronetWebsocketConnection.this.mCallback.onConnectionError(CronetWebsocketConnection.this, i2, str, str2);
                } catch (Exception e2) {
                    Log.e(CronetWebsocketConnection.TAG, "Exception in callback: ", e2);
                }
            }
        });
    }

    private void onMessageReceived(ByteBuffer byteBuffer, final int i2) {
        Log.v(TAG, "onMessageReceived", new Object[0]);
        final ByteBuffer clone = clone(byteBuffer);
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CronetWebsocketConnection.this.mCallback.onMessageReceived(CronetWebsocketConnection.this, clone, i2);
                } catch (Exception e2) {
                    Log.e(CronetWebsocketConnection.TAG, "Exception in callback: ", e2);
                }
            }
        });
    }

    private void onFeedbackLog(final String str) {
        Log.v(TAG, "OnFeedbackLog", new Object[0]);
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CronetWebsocketConnection.this.mCallback.onFeedbackLog(CronetWebsocketConnection.this, str);
                } catch (Exception e2) {
                    Log.e(CronetWebsocketConnection.TAG, "Exception in callback: ", e2);
                }
            }
        });
    }

    private void onTrafficChanged(final String str, final long j2, final long j3, final boolean z) {
        Log.v(TAG, "OnTrafficChanged", new Object[0]);
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetWebsocketConnection.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CronetWebsocketConnection.this.mCallback.onTrafficChanged(CronetWebsocketConnection.this, str, j2, j3, z);
                } catch (Exception e2) {
                    Log.e(CronetWebsocketConnection.TAG, "Exception in callback: ", e2);
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

    private ByteBuffer clone(ByteBuffer byteBuffer) {
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.capacity());
        byteBuffer.rewind();
        allocate.put(byteBuffer);
        allocate.flip();
        return allocate;
    }
}
