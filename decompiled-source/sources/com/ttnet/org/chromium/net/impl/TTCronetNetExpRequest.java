package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.NativeClassQualifiedName;
import com.ttnet.org.chromium.net.TTNetDiagnosisRequest;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
@JNINamespace("cronet")
/* loaded from: classes9.dex */
public final class TTCronetNetExpRequest extends TTNetDiagnosisRequest {
    private static final String TAG = "TTCronetNetExpRequest";
    private final TTNetDiagnosisRequest.Callback mCallback;
    private Executor mExecutor;
    private int mMultiNetAction;
    private int mNetDetectActions;
    private long mNetDiagnosisRequestAdapter;
    private final Object mNetDiagnosisRequestAdapterLock = new Object();
    private CronetUrlRequestContext mRequestContext;
    private int mRequestType;
    private boolean mStarted;
    private List<String> mTargets;
    private int mTimeoutMs;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        long createRequestAdapter(TTCronetNetExpRequest tTCronetNetExpRequest, long j2, int i2, String[] strArr, int i3, int i4, int i5);

        @NativeClassQualifiedName("TTCronetNetExpRequestAdapter")
        void destroy(long j2, TTCronetNetExpRequest tTCronetNetExpRequest);

        @NativeClassQualifiedName("TTCronetNetExpRequestAdapter")
        void doExtraCommand(long j2, TTCronetNetExpRequest tTCronetNetExpRequest, String str, String str2);

        @NativeClassQualifiedName("TTCronetNetExpRequestAdapter")
        void start(long j2, TTCronetNetExpRequest tTCronetNetExpRequest);
    }

    public TTCronetNetExpRequest(CronetUrlRequestContext cronetUrlRequestContext, TTNetDiagnosisRequest.Callback callback, Executor executor, int i2, List<String> list, int i3, int i4, int i5) {
        this.mRequestContext = cronetUrlRequestContext;
        this.mCallback = callback;
        this.mExecutor = executor;
        this.mRequestType = i2;
        this.mTargets = list;
        this.mNetDetectActions = i3;
        this.mMultiNetAction = i4;
        this.mTimeoutMs = i5;
    }

    @Override // com.ttnet.org.chromium.net.TTNetDiagnosisRequest
    public void start() {
        synchronized (this.mNetDiagnosisRequestAdapterLock) {
            if (this.mStarted) {
                return;
            }
            Natives natives = TTCronetNetExpRequestJni.get();
            long urlRequestContextAdapter = this.mRequestContext.getUrlRequestContextAdapter();
            int i2 = this.mRequestType;
            List<String> list = this.mTargets;
            long createRequestAdapter = natives.createRequestAdapter(this, urlRequestContextAdapter, i2, (String[]) list.toArray(new String[list.size()]), this.mNetDetectActions, this.mMultiNetAction, this.mTimeoutMs);
            this.mNetDiagnosisRequestAdapter = createRequestAdapter;
            if (createRequestAdapter == 0) {
                throw new NullPointerException("Create native net exp request adapter failed.");
            }
            this.mStarted = true;
            TTCronetNetExpRequestJni.get().start(this.mNetDiagnosisRequestAdapter, this);
        }
    }

    @Override // com.ttnet.org.chromium.net.TTNetDiagnosisRequest
    public void cancel() {
        synchronized (this.mNetDiagnosisRequestAdapterLock) {
            if (!isDoneLocked() && this.mStarted) {
                destroyRequestAdapterLocked();
            }
        }
    }

    @Override // com.ttnet.org.chromium.net.TTNetDiagnosisRequest
    public void doExtraCommand(String str, String str2) {
        synchronized (this.mNetDiagnosisRequestAdapterLock) {
            if (!isDoneLocked() && this.mStarted) {
                TTCronetNetExpRequestJni.get().doExtraCommand(this.mNetDiagnosisRequestAdapter, this, str, str2);
            }
        }
    }

    private void postTaskToExecutor(Runnable runnable) {
        try {
            Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(runnable);
            } else {
                new Thread(runnable, "NetExpCallback").start();
            }
        } catch (RejectedExecutionException e2) {
            Log.e(TAG, "Exception posting task to executor", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyRequestAdapterLocked() {
        if (this.mNetDiagnosisRequestAdapter == 0) {
            return;
        }
        TTCronetNetExpRequestJni.get().destroy(this.mNetDiagnosisRequestAdapter, this);
        this.mNetDiagnosisRequestAdapter = 0L;
    }

    private void onNetExpRequestComplete(final String str, final boolean z) {
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.TTCronetNetExpRequest.1
            @Override // java.lang.Runnable
            public void run() {
                if (!z) {
                    synchronized (TTCronetNetExpRequest.this.mNetDiagnosisRequestAdapterLock) {
                        if (!TTCronetNetExpRequest.this.isDoneLocked()) {
                            TTCronetNetExpRequest.this.destroyRequestAdapterLocked();
                        }
                    }
                }
                try {
                    TTCronetNetExpRequest.this.mCallback.onNetDiagnosisRequestComplete(TTCronetNetExpRequest.this, str);
                } catch (Exception e2) {
                    Log.e(TTCronetNetExpRequest.TAG, "Exception in callback: ", e2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDoneLocked() {
        return this.mStarted && this.mNetDiagnosisRequestAdapter == 0;
    }
}
