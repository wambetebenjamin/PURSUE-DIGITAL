package com.ttnet.org.chromium.net.impl;

import android.os.Build;
import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.NativeClassQualifiedName;
import com.ttnet.org.chromium.net.CronetException;
import com.ttnet.org.chromium.net.InlineExecutionProhibitedException;
import com.ttnet.org.chromium.net.RequestFinishedInfo;
import com.ttnet.org.chromium.net.UploadDataProvider;
import com.ttnet.org.chromium.net.UrlRequest;
import com.ttnet.org.chromium.net.impl.CronetLogger;
import com.ttnet.org.chromium.net.impl.VersionSafeCallbacks;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
@JNINamespace("cronet")
/* loaded from: classes9.dex */
public final class CronetUrlRequest extends UrlRequestBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private long mAddCookieCostUs;
    private final boolean mAllowDirectExecutor;
    private String mAuthCertPassword;
    private String mAuthCertUsername;
    private long mBizReadResponseBodyInterval;
    private final VersionSafeCallbacks.UrlRequestCallback mCallback;
    private final int mCronetEngineId;
    private String mCronetMapKey;
    private long mCurrentReadResponseBodyStart;
    private final boolean mDisableCache;
    private final boolean mDisableConnectionMigration;
    private CronetException mException;
    private final Executor mExecutor;
    private String mFinalUrl;
    private int mFinishedReason;
    private long mFirstReadResponseBodyStart;
    private final int mIdempotency;
    private String mInitialMethod;
    private final String mInitialUrl;
    private long mLastReadResponseBodyEnd;
    private final CronetLogger mLogger;
    private CronetMetrics mMetrics;
    private final long mNetworkHandle;
    private Runnable mOnDestroyedCallbackForTesting;
    private OnReadCompletedRunnable mOnReadCompletedTask;
    private final int mPriority;
    private boolean mQuicConnectionMigrationAttempted;
    private boolean mQuicConnectionMigrationSuccessful;
    private long mReadResponseBodyDuration;
    private final Collection<Object> mRequestAnnotations;
    private final CronetUrlRequestContext mRequestContext;
    private int mRequestCookieSource;
    private final VersionSafeCallbacks.RequestFinishedInfoListener mRequestFinishedListener;
    private int mRequestFlag;
    private final HeadersList mRequestHeaders;
    private int mRequestTimeout;
    private int mRequestTypeFlags;
    private long mResponseBodyReadTimes;
    private UrlResponseInfoImpl mResponseInfo;
    private long mSaveCookieCostUs;
    private int mSocketConnectTimeout;
    private int mSocketReadTimeout;
    private int mSocketWriteTimeout;
    private boolean mStarted;
    private long mThrottleNetSpeed;
    private final int mTrafficStatsTag;
    private final boolean mTrafficStatsTagSet;
    private final int mTrafficStatsUid;
    private final boolean mTrafficStatsUidSet;
    private CronetUploadDataStream mUploadDataStream;
    private final List<String> mUrlChain;
    private long mUrlRequestAdapter;
    private final Object mUrlRequestAdapterLock = new Object();
    private boolean mWaitingOnRead;
    private boolean mWaitingOnRedirect;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        @NativeClassQualifiedName("CronetURLRequestAdapter")
        void abortWhenUploadException(long j2, CronetUrlRequest cronetUrlRequest);

        @NativeClassQualifiedName("CronetURLRequestAdapter")
        void addRequestCookieHeader(long j2, CronetUrlRequest cronetUrlRequest, String str, String str2);

        @NativeClassQualifiedName("CronetURLRequestAdapter")
        boolean addRequestHeader(long j2, CronetUrlRequest cronetUrlRequest, String str, String str2);

        @NativeClassQualifiedName("CronetURLRequestAdapter")
        void appTimeout(long j2, CronetUrlRequest cronetUrlRequest);

        long createRequestAdapter(CronetUrlRequest cronetUrlRequest, long j2, String str, int i2, int i3, boolean z, boolean z2, boolean z3, int i4, boolean z4, int i5, int i6, long j3);

        @NativeClassQualifiedName("CronetURLRequestAdapter")
        void destroy(long j2, CronetUrlRequest cronetUrlRequest, boolean z);

        @NativeClassQualifiedName("CronetURLRequestAdapter")
        void followDeferredRedirect(long j2, CronetUrlRequest cronetUrlRequest);

        @NativeClassQualifiedName("CronetURLRequestAdapter")
        void getStatus(long j2, CronetUrlRequest cronetUrlRequest, VersionSafeCallbacks.UrlRequestStatusListener urlRequestStatusListener);

        @NativeClassQualifiedName("CronetURLRequestAdapter")
        boolean readData(long j2, CronetUrlRequest cronetUrlRequest, ByteBuffer byteBuffer, int i2, int i3);

        @NativeClassQualifiedName("CronetURLRequestAdapter")
        void removeRequestCookieHeader(long j2, CronetUrlRequest cronetUrlRequest);

        @NativeClassQualifiedName("CronetURLRequestAdapter")
        void setAuthCredentials(long j2, CronetUrlRequest cronetUrlRequest, String str, String str2);

        @NativeClassQualifiedName("CronetURLRequestAdapter")
        boolean setHttpMethod(long j2, CronetUrlRequest cronetUrlRequest, String str);

        @NativeClassQualifiedName("CronetURLRequestAdapter")
        void setRequestTimeout(long j2, CronetUrlRequest cronetUrlRequest, int i2);

        @NativeClassQualifiedName("CronetURLRequestAdapter")
        void setRequestTypeFlags(long j2, CronetUrlRequest cronetUrlRequest, int i2);

        @NativeClassQualifiedName("CronetURLRequestAdapter")
        void setSocketTimeout(long j2, CronetUrlRequest cronetUrlRequest, int i2, int i3, int i4);

        @NativeClassQualifiedName("CronetURLRequestAdapter")
        void setThrottleNetSpeed(long j2, CronetUrlRequest cronetUrlRequest, long j3);

        @NativeClassQualifiedName("CronetURLRequestAdapter")
        void start(long j2, CronetUrlRequest cronetUrlRequest);
    }

    private static int convertIdempotency(int i2) {
        int i3 = 1;
        if (i2 != 1) {
            i3 = 2;
            if (i2 != 2) {
                return 0;
            }
        }
        return i3;
    }

    private static int convertRequestPriority(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    return i2 != 4 ? 4 : 5;
                }
                return 3;
            }
            return 2;
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class HeadersList extends ArrayList<Map.Entry<String, String>> {
        HeadersList() {
        }
    }

    /* loaded from: classes9.dex */
    private final class OnReadCompletedRunnable implements Runnable {
        ByteBuffer mByteBuffer;

        private OnReadCompletedRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CronetUrlRequest.this.checkCallingThread();
            ByteBuffer byteBuffer = this.mByteBuffer;
            this.mByteBuffer = null;
            try {
                synchronized (CronetUrlRequest.this.mUrlRequestAdapterLock) {
                    if (CronetUrlRequest.this.isDoneLocked()) {
                        return;
                    }
                    CronetUrlRequest.this.mWaitingOnRead = true;
                    VersionSafeCallbacks.UrlRequestCallback urlRequestCallback = CronetUrlRequest.this.mCallback;
                    CronetUrlRequest cronetUrlRequest = CronetUrlRequest.this;
                    urlRequestCallback.onReadCompleted(cronetUrlRequest, cronetUrlRequest.mResponseInfo, byteBuffer);
                }
            } catch (Exception e2) {
                CronetUrlRequest.this.onCallbackException(e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CronetUrlRequest(CronetUrlRequestContext cronetUrlRequestContext, String str, int i2, UrlRequest.Callback callback, Executor executor, Collection<Object> collection, boolean z, boolean z2, boolean z3, boolean z4, int i3, boolean z5, int i4, RequestFinishedInfo.Listener listener, int i5, long j2) {
        ArrayList arrayList = new ArrayList();
        this.mUrlChain = arrayList;
        this.mRequestHeaders = new HeadersList();
        this.mResponseBodyReadTimes = 0L;
        this.mFirstReadResponseBodyStart = -1L;
        this.mCurrentReadResponseBodyStart = -1L;
        this.mLastReadResponseBodyEnd = -1L;
        this.mReadResponseBodyDuration = -1L;
        this.mBizReadResponseBodyInterval = -1L;
        Objects.requireNonNull(str, "URL is required");
        Objects.requireNonNull(callback, "Listener is required");
        Objects.requireNonNull(executor, "Executor is required");
        this.mAllowDirectExecutor = z3;
        this.mRequestContext = cronetUrlRequestContext;
        this.mCronetEngineId = cronetUrlRequestContext.getCronetEngineId();
        this.mLogger = cronetUrlRequestContext.getCronetLogger();
        this.mInitialUrl = str;
        arrayList.add(str);
        this.mPriority = convertRequestPriority(i2);
        this.mCallback = new VersionSafeCallbacks.UrlRequestCallback(callback);
        this.mExecutor = executor;
        this.mRequestAnnotations = collection;
        this.mDisableCache = z;
        this.mDisableConnectionMigration = z2;
        this.mTrafficStatsTagSet = z4;
        this.mTrafficStatsTag = i3;
        this.mTrafficStatsUidSet = z5;
        this.mTrafficStatsUid = i4;
        this.mRequestFinishedListener = listener != null ? new VersionSafeCallbacks.RequestFinishedInfoListener(listener) : null;
        this.mIdempotency = convertIdempotency(i5);
        this.mNetworkHandle = j2;
    }

    @Override // com.ttnet.org.chromium.net.impl.UrlRequestBase
    public void setHttpMethod(String str) {
        checkNotStarted();
        Objects.requireNonNull(str, "Method is required.");
        this.mInitialMethod = str;
    }

    @Override // com.ttnet.org.chromium.net.impl.UrlRequestBase
    public void addHeader(String str, String str2) {
        checkNotStarted();
        Objects.requireNonNull(str, "Invalid header name.");
        Objects.requireNonNull(str2, "Invalid header value.");
        this.mRequestHeaders.add(new AbstractMap.SimpleImmutableEntry(str, str2));
    }

    @Override // com.ttnet.org.chromium.net.impl.UrlRequestBase
    public void setUploadDataProvider(UploadDataProvider uploadDataProvider, Executor executor) {
        Objects.requireNonNull(uploadDataProvider, "Invalid UploadDataProvider.");
        if (this.mInitialMethod == null) {
            this.mInitialMethod = "POST";
        }
        this.mUploadDataStream = new CronetUploadDataStream(uploadDataProvider, executor, this);
    }

    @Override // com.ttnet.org.chromium.net.impl.UrlRequestBase
    public void setSocketConnectTimeout(int i2) {
        this.mSocketConnectTimeout = i2;
    }

    @Override // com.ttnet.org.chromium.net.impl.UrlRequestBase
    public void setSocketReadTimeout(int i2) {
        this.mSocketReadTimeout = i2;
    }

    @Override // com.ttnet.org.chromium.net.impl.UrlRequestBase
    public void setSocketWriteTimeout(int i2) {
        this.mSocketWriteTimeout = i2;
    }

    @Override // com.ttnet.org.chromium.net.impl.UrlRequestBase
    public void setRequestTimeout(int i2) {
        this.mRequestTimeout = i2;
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void setThrottleNetSpeed(long j2) {
        synchronized (this.mUrlRequestAdapterLock) {
            this.mThrottleNetSpeed = j2;
            if (this.mUrlRequestAdapter != 0) {
                CronetUrlRequestJni.get().setThrottleNetSpeed(this.mUrlRequestAdapter, this, j2);
            }
        }
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void setRequestTypeFlags(int i2) {
        this.mRequestTypeFlags = i2 | this.mRequestTypeFlags;
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public String getFinalUrl() {
        return this.mFinalUrl;
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void AddRequestCookieHeader(String str, String str2) {
        synchronized (this.mUrlRequestAdapterLock) {
            if (this.mUrlRequestAdapter == 0) {
                return;
            }
            CronetUrlRequestJni.get().addRequestCookieHeader(this.mUrlRequestAdapter, this, str, str2);
        }
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void RemoveRequestCookieHeader() {
        synchronized (this.mUrlRequestAdapterLock) {
            if (this.mUrlRequestAdapter == 0) {
                return;
            }
            CronetUrlRequestJni.get().removeRequestCookieHeader(this.mUrlRequestAdapter, this);
        }
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void appTimeout() {
        CronetUrlRequestMapping.RemoveRequest(this.mCronetMapKey);
        synchronized (this.mUrlRequestAdapterLock) {
            if (this.mUrlRequestAdapter == 0) {
                return;
            }
            CronetUrlRequestJni.get().appTimeout(this.mUrlRequestAdapter, this);
        }
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void setRequestFlag(int i2) {
        this.mRequestFlag = i2;
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void setAddCookieCostUs(long j2) {
        this.mAddCookieCostUs = j2;
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void setSaveCookieCostUs(long j2) {
        this.mSaveCookieCostUs = j2;
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void setRequestCookieSource(int i2) {
        this.mRequestCookieSource = i2;
    }

    private RequestFinishedInfo getRequestFinishedInfo() {
        return new RequestFinishedInfoImpl(this.mInitialUrl, this.mRequestAnnotations, this.mMetrics, this.mFinishedReason, this.mResponseInfo, this.mException);
    }

    private String[] addSecurityFactor(String str, String[] strArr) {
        HashMap hashMap = new HashMap();
        int i2 = 0;
        for (int i3 = 0; i3 < strArr.length; i3 += 2) {
            hashMap.put(strArr[i3].toLowerCase(Locale.US), strArr[i3 + 1]);
        }
        Map<String, String> onCallToAddSecurityFactor = this.mRequestContext.onCallToAddSecurityFactor(str, hashMap);
        if (onCallToAddSecurityFactor == null) {
            return null;
        }
        String[] strArr2 = new String[onCallToAddSecurityFactor.size() * 2];
        for (Map.Entry<String, String> entry : onCallToAddSecurityFactor.entrySet()) {
            strArr2[i2] = entry.getKey();
            strArr2[i2 + 1] = entry.getValue();
            i2 += 2;
        }
        return strArr2;
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void setAuthCredentials(String str, String str2) {
        this.mAuthCertUsername = str;
        this.mAuthCertPassword = str2;
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void cancelWithException(String str, Throwable th) {
        failWithException(new CronetExceptionImpl(str, th));
    }

    private void RecordReadResponseBodyStart() {
        this.mResponseBodyReadTimes++;
        long nanoTime = System.nanoTime();
        this.mCurrentReadResponseBodyStart = nanoTime;
        if (this.mFirstReadResponseBodyStart == -1) {
            this.mFirstReadResponseBodyStart = nanoTime;
        }
        long j2 = this.mLastReadResponseBodyEnd;
        if (j2 != -1) {
            if (this.mBizReadResponseBodyInterval == -1) {
                this.mBizReadResponseBodyInterval = 0L;
            }
            this.mBizReadResponseBodyInterval += nanoTime - j2;
        }
    }

    private void MaybeRecordReadResponseBodyEnd() {
        if (this.mCurrentReadResponseBodyStart != -1) {
            long nanoTime = System.nanoTime();
            this.mLastReadResponseBodyEnd = nanoTime;
            if (this.mReadResponseBodyDuration == -1) {
                this.mReadResponseBodyDuration = 0L;
            }
            this.mReadResponseBodyDuration += nanoTime - this.mCurrentReadResponseBodyStart;
            this.mCurrentReadResponseBodyStart = -1L;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void start() {
        Object obj;
        CronetUrlRequest cronetUrlRequest;
        int i2;
        Object obj2 = this.mUrlRequestAdapterLock;
        synchronized (obj2) {
            try {
                try {
                    checkNotStarted();
                    try {
                        try {
                            obj = obj2;
                            try {
                                cronetUrlRequest = this;
                                try {
                                    cronetUrlRequest.mUrlRequestAdapter = CronetUrlRequestJni.get().createRequestAdapter(this, this.mRequestContext.getUrlRequestContextAdapter(), this.mInitialUrl, this.mPriority, this.mRequestFlag, this.mDisableCache, this.mDisableConnectionMigration, this.mTrafficStatsTagSet, this.mTrafficStatsTag, this.mTrafficStatsUidSet, this.mTrafficStatsUid, this.mIdempotency, this.mNetworkHandle);
                                    cronetUrlRequest.mRequestContext.onRequestStarted();
                                    if (cronetUrlRequest.mInitialMethod != null && !CronetUrlRequestJni.get().setHttpMethod(cronetUrlRequest.mUrlRequestAdapter, cronetUrlRequest, cronetUrlRequest.mInitialMethod)) {
                                        throw new IllegalArgumentException("Invalid http method " + cronetUrlRequest.mInitialMethod);
                                    }
                                    Iterator<Map.Entry<String, String>> it = cronetUrlRequest.mRequestHeaders.iterator();
                                    boolean z = false;
                                    while (it.hasNext()) {
                                        Map.Entry<String, String> next = it.next();
                                        if (next.getKey().equalsIgnoreCase("Content-Type") && !next.getValue().isEmpty()) {
                                            z = true;
                                        }
                                        if (next.getKey().equalsIgnoreCase("Tt-Map-Key") && !next.getValue().isEmpty()) {
                                            cronetUrlRequest.mCronetMapKey = next.getValue();
                                        }
                                        if (!CronetUrlRequestJni.get().addRequestHeader(cronetUrlRequest.mUrlRequestAdapter, this, next.getKey(), next.getValue())) {
                                            throw new IllegalArgumentException("Invalid header " + next.getKey() + "=" + next.getValue());
                                        }
                                    }
                                    CronetUrlRequestJni.get().setSocketTimeout(cronetUrlRequest.mUrlRequestAdapter, this, cronetUrlRequest.mSocketConnectTimeout, cronetUrlRequest.mSocketReadTimeout, cronetUrlRequest.mSocketWriteTimeout);
                                    Natives natives = CronetUrlRequestJni.get();
                                    long j2 = cronetUrlRequest.mUrlRequestAdapter;
                                    natives.setRequestTimeout(j2, cronetUrlRequest, cronetUrlRequest.mRequestTimeout);
                                    long j3 = j2;
                                    if (cronetUrlRequest.mThrottleNetSpeed > 0) {
                                        Natives natives2 = CronetUrlRequestJni.get();
                                        natives2.setThrottleNetSpeed(cronetUrlRequest.mUrlRequestAdapter, this, cronetUrlRequest.mThrottleNetSpeed);
                                        j3 = natives2;
                                    }
                                    int i3 = j3;
                                    if (cronetUrlRequest.mRequestTypeFlags > 0) {
                                        Natives natives3 = CronetUrlRequestJni.get();
                                        long j4 = cronetUrlRequest.mUrlRequestAdapter;
                                        natives3.setRequestTypeFlags(j4, cronetUrlRequest, cronetUrlRequest.mRequestTypeFlags);
                                        i3 = j4;
                                    }
                                    i2 = i3;
                                    if (cronetUrlRequest.mAuthCertUsername != null) {
                                        i2 = i3;
                                        if (cronetUrlRequest.mAuthCertPassword != null) {
                                            Natives natives4 = CronetUrlRequestJni.get();
                                            natives4.setAuthCredentials(cronetUrlRequest.mUrlRequestAdapter, this, cronetUrlRequest.mAuthCertUsername, cronetUrlRequest.mAuthCertPassword);
                                            i2 = natives4;
                                        }
                                    }
                                    CronetUploadDataStream cronetUploadDataStream = cronetUrlRequest.mUploadDataStream;
                                    if (cronetUploadDataStream != null) {
                                        try {
                                            if (!z) {
                                                throw new IllegalArgumentException("Requests with upload data must have a Content-Type.");
                                            }
                                            cronetUrlRequest.mStarted = true;
                                            cronetUploadDataStream.postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetUrlRequest.1
                                                @Override // java.lang.Runnable
                                                public void run() {
                                                    CronetUrlRequest.this.mUploadDataStream.initializeWithRequest();
                                                    synchronized (CronetUrlRequest.this.mUrlRequestAdapterLock) {
                                                        if (CronetUrlRequest.this.isDoneLocked()) {
                                                            return;
                                                        }
                                                        CronetUrlRequest.this.mUploadDataStream.attachNativeAdapterToRequest(CronetUrlRequest.this.mUrlRequestAdapter);
                                                        CronetUrlRequest.this.startInternalLocked();
                                                    }
                                                }
                                            });
                                            return;
                                        } catch (RuntimeException e2) {
                                            e = e2;
                                            cronetUrlRequest.destroyRequestAdapterLocked(i2);
                                            throw e;
                                        }
                                    }
                                    cronetUrlRequest.mStarted = true;
                                    startInternalLocked();
                                } catch (RuntimeException e3) {
                                    e = e3;
                                    i2 = 1;
                                    cronetUrlRequest.destroyRequestAdapterLocked(i2);
                                    throw e;
                                }
                            } catch (RuntimeException e4) {
                                e = e4;
                                i2 = 1;
                                cronetUrlRequest = this;
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        } catch (RuntimeException e5) {
                            e = e5;
                            cronetUrlRequest = this;
                        } catch (Throwable th2) {
                            th = th2;
                            obj = obj2;
                        }
                    } catch (RuntimeException e6) {
                        e = e6;
                        cronetUrlRequest = this;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                th = th4;
                obj = obj2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startInternalLocked() {
        String str = this.mCronetMapKey;
        if (str != null) {
            CronetUrlRequestMapping.AddRequest(str, this);
        }
        CronetUrlRequestJni.get().start(this.mUrlRequestAdapter, this);
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void followRedirect() {
        synchronized (this.mUrlRequestAdapterLock) {
            if (!this.mWaitingOnRedirect) {
                throw new IllegalStateException("No redirect to follow.");
            }
            this.mWaitingOnRedirect = false;
            if (isDoneLocked()) {
                return;
            }
            CronetUrlRequestJni.get().followDeferredRedirect(this.mUrlRequestAdapter, this);
        }
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void read(ByteBuffer byteBuffer) {
        Preconditions.checkHasRemaining(byteBuffer);
        Preconditions.checkDirect(byteBuffer);
        synchronized (this.mUrlRequestAdapterLock) {
            if (!this.mWaitingOnRead) {
                throw new IllegalStateException("Unexpected read attempt.");
            }
            this.mWaitingOnRead = false;
            if (isDoneLocked()) {
                return;
            }
            RecordReadResponseBodyStart();
            if (CronetUrlRequestJni.get().readData(this.mUrlRequestAdapter, this, byteBuffer, byteBuffer.position(), byteBuffer.limit())) {
                return;
            }
            this.mWaitingOnRead = true;
            MaybeRecordReadResponseBodyEnd();
            throw new IllegalArgumentException("Unable to call native read");
        }
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void cancel() {
        synchronized (this.mUrlRequestAdapterLock) {
            if (!isDoneLocked() && this.mStarted) {
                destroyRequestAdapterLocked(2);
            }
        }
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public boolean isDone() {
        boolean isDoneLocked;
        synchronized (this.mUrlRequestAdapterLock) {
            isDoneLocked = isDoneLocked();
        }
        return isDoneLocked;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDoneLocked() {
        return this.mStarted && this.mUrlRequestAdapter == 0;
    }

    @Override // com.ttnet.org.chromium.net.UrlRequest
    public void getStatus(UrlRequest.StatusListener statusListener) {
        final VersionSafeCallbacks.UrlRequestStatusListener urlRequestStatusListener = new VersionSafeCallbacks.UrlRequestStatusListener(statusListener);
        synchronized (this.mUrlRequestAdapterLock) {
            if (this.mUrlRequestAdapter != 0) {
                CronetUrlRequestJni.get().getStatus(this.mUrlRequestAdapter, this, urlRequestStatusListener);
            } else {
                postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetUrlRequest.2
                    @Override // java.lang.Runnable
                    public void run() {
                        urlRequestStatusListener.onStatus(-1);
                    }
                });
            }
        }
    }

    public void setOnDestroyedCallbackForTesting(Runnable runnable) {
        synchronized (this.mUrlRequestAdapterLock) {
            this.mOnDestroyedCallbackForTesting = runnable;
        }
    }

    public void setOnDestroyedUploadCallbackForTesting(Runnable runnable) {
        this.mUploadDataStream.setOnDestroyedCallbackForTesting(runnable);
    }

    public long getUrlRequestAdapterForTesting() {
        long j2;
        synchronized (this.mUrlRequestAdapterLock) {
            j2 = this.mUrlRequestAdapter;
        }
        return j2;
    }

    private void postTaskToExecutor(Runnable runnable) {
        try {
            this.mExecutor.execute(runnable);
        } catch (RejectedExecutionException e2) {
            Log.e(CronetUrlRequestContext.LOG_TAG, "Exception posting task to executor", e2);
            failWithException(new CronetExceptionImpl("Exception posting task to executor", e2));
        }
    }

    static long estimateHeadersSizeInBytes(Map<String, List<String>> map) {
        long j2 = 0;
        if (map == null) {
            return 0L;
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key != null) {
                j2 += key.length();
            }
            if (entry.getValue() != null) {
                for (String str : entry.getValue()) {
                    j2 += str.length();
                }
            }
        }
        return j2;
    }

    static long estimateHeadersSizeInBytes(HeadersList headersList) {
        long j2 = 0;
        if (headersList == null) {
            return 0L;
        }
        Iterator<Map.Entry<String, String>> it = headersList.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            String key = next.getKey();
            if (key != null) {
                j2 += key.length();
            }
            if (next.getValue() != null) {
                j2 += next.getValue().length();
            }
        }
        return j2;
    }

    private UrlResponseInfoImpl prepareResponseInfoOnNetworkThread(int i2, String str, String[] strArr, boolean z, String str2, String str3, long j2) {
        HeadersList headersList = new HeadersList();
        for (int i3 = 0; i3 < strArr.length; i3 += 2) {
            headersList.add(new AbstractMap.SimpleImmutableEntry(strArr[i3], strArr[i3 + 1]));
        }
        return new UrlResponseInfoImpl(new ArrayList(this.mUrlChain), i2, str, headersList, z, str2, str3, j2);
    }

    private void checkNotStarted() {
        synchronized (this.mUrlRequestAdapterLock) {
            if (this.mStarted || isDoneLocked()) {
                throw new IllegalStateException("Request is already started.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyRequestAdapterLocked(int i2) {
        this.mFinishedReason = i2;
        if (this.mUrlRequestAdapter == 0) {
            return;
        }
        this.mRequestContext.onRequestDestroyed();
        CronetUrlRequestJni.get().destroy(this.mUrlRequestAdapter, this, i2 == 2);
        this.mUrlRequestAdapter = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCallbackException(Exception exc) {
        CallbackExceptionImpl callbackExceptionImpl = new CallbackExceptionImpl("Exception received from UrlRequest.Callback", exc);
        Log.e(CronetUrlRequestContext.LOG_TAG, "Exception in CalledByNative method", exc);
        failWithException(callbackExceptionImpl);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onUploadException(Throwable th) {
        Log.e(CronetUrlRequestContext.LOG_TAG, "Exception in upload method", th);
        synchronized (this.mUrlRequestAdapterLock) {
            if (this.mUrlRequestAdapter == 0) {
                return;
            }
            CronetUrlRequestJni.get().abortWhenUploadException(this.mUrlRequestAdapter, this);
        }
    }

    private void failWithException(CronetException cronetException) {
        synchronized (this.mUrlRequestAdapterLock) {
            if (isDoneLocked()) {
                return;
            }
            this.mException = cronetException;
            destroyRequestAdapterLocked(1);
        }
    }

    private void onRedirectReceived(final String str, int i2, String str2, String[] strArr, boolean z, String str3, String str4, long j2, final String str5) {
        final UrlResponseInfoImpl prepareResponseInfoOnNetworkThread = prepareResponseInfoOnNetworkThread(i2, str2, strArr, z, str3, str4, j2);
        this.mResponseInfo = prepareResponseInfoOnNetworkThread;
        this.mUrlChain.add(str);
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetUrlRequest.3
            @Override // java.lang.Runnable
            public void run() {
                CronetUrlRequest.this.checkCallingThread();
                synchronized (CronetUrlRequest.this.mUrlRequestAdapterLock) {
                    if (CronetUrlRequest.this.isDoneLocked()) {
                        return;
                    }
                    CronetUrlRequest.this.mWaitingOnRedirect = true;
                    try {
                        CronetUrlRequest.this.mCallback.onRedirectReceived(CronetUrlRequest.this, prepareResponseInfoOnNetworkThread, str, str5);
                    } catch (Exception e2) {
                        CronetUrlRequest.this.onCallbackException(e2);
                    }
                }
            }
        });
    }

    private void onResponseStarted(int i2, String str, String[] strArr, boolean z, String str2, String str3, long j2, final String str4) {
        this.mResponseInfo = prepareResponseInfoOnNetworkThread(i2, str, strArr, z, str2, str3, j2);
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetUrlRequest.4
            @Override // java.lang.Runnable
            public void run() {
                CronetUrlRequest.this.checkCallingThread();
                synchronized (CronetUrlRequest.this.mUrlRequestAdapterLock) {
                    if (CronetUrlRequest.this.isDoneLocked()) {
                        return;
                    }
                    CronetUrlRequest.this.mWaitingOnRead = true;
                    try {
                        VersionSafeCallbacks.UrlRequestCallback urlRequestCallback = CronetUrlRequest.this.mCallback;
                        CronetUrlRequest cronetUrlRequest = CronetUrlRequest.this;
                        urlRequestCallback.onResponseStarted(cronetUrlRequest, cronetUrlRequest.mResponseInfo, str4);
                    } catch (Exception e2) {
                        CronetUrlRequest.this.onCallbackException(e2);
                    }
                }
            }
        });
    }

    private void onReadCompleted(ByteBuffer byteBuffer, int i2, int i3, int i4, long j2) {
        this.mResponseInfo.setReceivedByteCount(j2);
        if (byteBuffer.position() != i3 || byteBuffer.limit() != i4) {
            failWithException(new CronetExceptionImpl("ByteBuffer modified externally during read", null));
            return;
        }
        if (this.mOnReadCompletedTask == null) {
            this.mOnReadCompletedTask = new OnReadCompletedRunnable();
        }
        byteBuffer.position(i3 + i2);
        this.mOnReadCompletedTask.mByteBuffer = byteBuffer;
        MaybeRecordReadResponseBodyEnd();
        postTaskToExecutor(this.mOnReadCompletedTask);
    }

    private void onSucceeded(long j2) {
        this.mResponseInfo.setReceivedByteCount(j2);
        CronetUrlRequestMapping.RemoveRequest(this.mCronetMapKey);
        Runnable runnable = new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetUrlRequest.5
            @Override // java.lang.Runnable
            public void run() {
                synchronized (CronetUrlRequest.this.mUrlRequestAdapterLock) {
                    if (CronetUrlRequest.this.isDoneLocked()) {
                        return;
                    }
                    CronetUrlRequest.this.destroyRequestAdapterLocked(0);
                    try {
                        VersionSafeCallbacks.UrlRequestCallback urlRequestCallback = CronetUrlRequest.this.mCallback;
                        CronetUrlRequest cronetUrlRequest = CronetUrlRequest.this;
                        urlRequestCallback.onSucceeded(cronetUrlRequest, cronetUrlRequest.mResponseInfo);
                        CronetUrlRequest.this.maybeReportMetrics();
                    } catch (Exception e2) {
                        Log.e(CronetUrlRequestContext.LOG_TAG, "Exception in onSucceeded method", e2);
                    }
                }
            }
        };
        MaybeRecordReadResponseBodyEnd();
        postTaskToExecutor(runnable);
    }

    private void onError(int i2, int i3, int i4, String str, long j2) {
        UrlResponseInfoImpl urlResponseInfoImpl = this.mResponseInfo;
        if (urlResponseInfoImpl != null) {
            urlResponseInfoImpl.setReceivedByteCount(j2);
        }
        CronetUrlRequestMapping.RemoveRequest(this.mCronetMapKey);
        if (i2 == 10 || i2 == 3) {
            failWithException(new QuicExceptionImpl("Exception in CronetUrlRequest: " + str, i2, i3, i4));
            return;
        }
        failWithException(new NetworkExceptionImpl("Exception in CronetUrlRequest: " + str, mapUrlRequestErrorToApiErrorCode(i2), i3));
    }

    private void onCanceled() {
        CronetUrlRequestMapping.RemoveRequest(this.mCronetMapKey);
        Runnable runnable = new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetUrlRequest.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    VersionSafeCallbacks.UrlRequestCallback urlRequestCallback = CronetUrlRequest.this.mCallback;
                    CronetUrlRequest cronetUrlRequest = CronetUrlRequest.this;
                    urlRequestCallback.onCanceled(cronetUrlRequest, cronetUrlRequest.mResponseInfo);
                    CronetUrlRequest.this.maybeReportMetrics();
                } catch (Exception e2) {
                    Log.e(CronetUrlRequestContext.LOG_TAG, "Exception in onCanceled method", e2);
                }
            }
        };
        MaybeRecordReadResponseBodyEnd();
        postTaskToExecutor(runnable);
    }

    private void onStatus(final VersionSafeCallbacks.UrlRequestStatusListener urlRequestStatusListener, final int i2) {
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetUrlRequest.7
            @Override // java.lang.Runnable
            public void run() {
                urlRequestStatusListener.onStatus(UrlRequestBase.convertLoadState(i2));
            }
        });
    }

    private void onMetricsCollected(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, boolean z, long j15, long j16, String str, long j17, long j18, String str2, String str3, String str4, String str5, boolean z2, boolean z3, int i2, boolean z4, boolean z5) {
        synchronized (this.mUrlRequestAdapterLock) {
            if (this.mMetrics != null) {
                throw new IllegalStateException("Metrics collection should only happen once.");
            }
            CronetMetrics cronetMetrics = new CronetMetrics(j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, z, j15, j16, str, j17, j18, str2, str3, this.mAddCookieCostUs, this.mSaveCookieCostUs, this.mRequestCookieSource, z2, z3, i2);
            this.mMetrics = cronetMetrics;
            cronetMetrics.setRequestLog(str5);
            this.mFinalUrl = str4;
            this.mQuicConnectionMigrationAttempted = z4;
            this.mQuicConnectionMigrationSuccessful = z5;
        }
    }

    private void onNativeAdapterDestroyed() {
        synchronized (this.mUrlRequestAdapterLock) {
            Runnable runnable = this.mOnDestroyedCallbackForTesting;
            if (runnable != null) {
                runnable.run();
            }
            if (this.mException == null) {
                return;
            }
            Runnable runnable2 = new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetUrlRequest.8
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        VersionSafeCallbacks.UrlRequestCallback urlRequestCallback = CronetUrlRequest.this.mCallback;
                        CronetUrlRequest cronetUrlRequest = CronetUrlRequest.this;
                        urlRequestCallback.onFailed(cronetUrlRequest, cronetUrlRequest.mResponseInfo, CronetUrlRequest.this.mException);
                        CronetUrlRequest.this.maybeReportMetrics();
                    } catch (Exception e2) {
                        Log.e(CronetUrlRequestContext.LOG_TAG, "Exception in onFailed method", e2);
                    }
                }
            };
            try {
                MaybeRecordReadResponseBodyEnd();
                this.mExecutor.execute(runnable2);
            } catch (RejectedExecutionException e2) {
                Log.e(CronetUrlRequestContext.LOG_TAG, "Exception posting task to executor", e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void checkCallingThread() {
        if (!this.mAllowDirectExecutor && this.mRequestContext.isNetworkThread(Thread.currentThread())) {
            throw new InlineExecutionProhibitedException();
        }
    }

    private int mapUrlRequestErrorToApiErrorCode(int i2) {
        switch (i2) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 9;
            case 10:
                return 10;
            case 11:
                return 11;
            default:
                Log.e(CronetUrlRequestContext.LOG_TAG, "Unknown error code: " + i2, new Object[0]);
                return i2;
        }
    }

    private CronetLogger.CronetTrafficInfo buildCronetTrafficInfo() {
        Map<String, List<String>> emptyMap;
        int i2;
        String str;
        long estimateHeadersSizeInBytes;
        long max;
        long estimateHeadersSizeInBytes2;
        long max2;
        Duration ofSeconds;
        Duration ofSeconds2;
        UrlResponseInfoImpl urlResponseInfoImpl = this.mResponseInfo;
        boolean z = false;
        if (urlResponseInfoImpl != null) {
            emptyMap = urlResponseInfoImpl.getAllHeaders();
            str = this.mResponseInfo.getNegotiatedProtocol();
            i2 = this.mResponseInfo.getHttpStatusCode();
            z = this.mResponseInfo.wasCached();
        } else {
            emptyMap = Collections.emptyMap();
            i2 = 0;
            str = "";
        }
        long longValue = this.mMetrics.getSentByteCount().longValue();
        if (z && longValue == 0) {
            estimateHeadersSizeInBytes = 0;
            max = 0;
        } else {
            estimateHeadersSizeInBytes = estimateHeadersSizeInBytes(this.mRequestHeaders);
            max = Math.max(0L, longValue - estimateHeadersSizeInBytes);
        }
        long longValue2 = this.mMetrics.getReceivedByteCount().longValue();
        if (z && longValue2 == 0) {
            estimateHeadersSizeInBytes2 = 0;
            max2 = 0;
        } else {
            estimateHeadersSizeInBytes2 = estimateHeadersSizeInBytes(emptyMap);
            max2 = Math.max(0L, longValue2 - estimateHeadersSizeInBytes2);
        }
        if (this.mMetrics.getRequestStart() != null && this.mMetrics.getResponseStart() != null) {
            ofSeconds = Duration.ofMillis(this.mMetrics.getResponseStart().getTime() - this.mMetrics.getRequestStart().getTime());
        } else {
            ofSeconds = Duration.ofSeconds(0L);
        }
        Duration duration = ofSeconds;
        if (this.mMetrics.getRequestStart() != null && this.mMetrics.getRequestEnd() != null) {
            ofSeconds2 = Duration.ofMillis(this.mMetrics.getRequestEnd().getTime() - this.mMetrics.getRequestStart().getTime());
        } else {
            ofSeconds2 = Duration.ofSeconds(0L);
        }
        return new CronetLogger.CronetTrafficInfo(estimateHeadersSizeInBytes, max, estimateHeadersSizeInBytes2, max2, i2, duration, ofSeconds2, str, this.mQuicConnectionMigrationAttempted, this.mQuicConnectionMigrationSuccessful);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeReportMetrics() {
        if (this.mMetrics != null) {
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    this.mLogger.logCronetTrafficInfo(this.mCronetEngineId, buildCronetTrafficInfo());
                } catch (RuntimeException e2) {
                    Log.e(CronetUrlRequestContext.LOG_TAG, "Error while trying to log CronetTrafficInfo: ", e2);
                }
            }
            this.mMetrics.setReadResponseBodyMetrics(this.mResponseBodyReadTimes, this.mFirstReadResponseBodyStart, this.mLastReadResponseBodyEnd, this.mReadResponseBodyDuration, this.mBizReadResponseBodyInterval);
            final RequestFinishedInfoImpl requestFinishedInfoImpl = new RequestFinishedInfoImpl(this.mInitialUrl, this.mRequestAnnotations, this.mMetrics, this.mFinishedReason, this.mResponseInfo, this.mException);
            this.mRequestContext.reportRequestFinished(requestFinishedInfoImpl);
            VersionSafeCallbacks.RequestFinishedInfoListener requestFinishedInfoListener = this.mRequestFinishedListener;
            if (requestFinishedInfoListener != null) {
                try {
                    requestFinishedInfoListener.getExecutor().execute(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetUrlRequest.9
                        @Override // java.lang.Runnable
                        public void run() {
                            CronetUrlRequest.this.mRequestFinishedListener.onRequestFinished(requestFinishedInfoImpl);
                        }
                    });
                } catch (RejectedExecutionException e3) {
                    Log.e(CronetUrlRequestContext.LOG_TAG, "Exception posting task to executor", e3);
                }
            }
        }
    }
}
