package com.ttnet.org.chromium.net.impl;

import com.bytedance.retrofit2.HttpMethodContrants;
import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.NativeClassQualifiedName;
import com.ttnet.org.chromium.net.BidirectionalStream;
import com.ttnet.org.chromium.net.CronetException;
import com.ttnet.org.chromium.net.ExperimentalBidirectionalStream;
import com.ttnet.org.chromium.net.RequestFinishedInfo;
import com.ttnet.org.chromium.net.impl.UrlResponseInfoImpl;
import com.ttnet.org.chromium.net.impl.VersionSafeCallbacks;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
@JNINamespace("cronet")
/* loaded from: classes9.dex */
public class CronetBidirectionalStream extends ExperimentalBidirectionalStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final VersionSafeCallbacks.BidirectionalStreamCallback mCallback;
    private final boolean mDelayRequestHeadersUntilFirstFlush;
    private boolean mEndOfStreamWritten;
    private CronetException mException;
    private final Executor mExecutor;
    private final String mInitialMethod;
    private final int mInitialPriority;
    private final String mInitialUrl;
    private RequestFinishedInfo.Metrics mMetrics;
    private long mNativeStream;
    private final long mNetworkHandle;
    private Runnable mOnDestroyedCallbackForTesting;
    private OnReadCompletedRunnable mOnReadCompletedTask;
    private final Collection<Object> mRequestAnnotations;
    private final CronetUrlRequestContext mRequestContext;
    private final String[] mRequestHeaders;
    private boolean mRequestHeadersSent;
    private UrlResponseInfoImpl mResponseInfo;
    private final int mTrafficStatsTag;
    private final boolean mTrafficStatsTagSet;
    private final int mTrafficStatsUid;
    private final boolean mTrafficStatsUidSet;
    private final Object mNativeStreamLock = new Object();
    private int mReadState = 0;
    private int mWriteState = 0;
    private LinkedList<ByteBuffer> mPendingData = new LinkedList<>();
    private LinkedList<ByteBuffer> mFlushData = new LinkedList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        long createBidirectionalStream(CronetBidirectionalStream cronetBidirectionalStream, long j2, boolean z, boolean z2, int i2, boolean z3, int i3, long j3);

        @NativeClassQualifiedName("CronetBidirectionalStreamAdapter")
        void destroy(long j2, CronetBidirectionalStream cronetBidirectionalStream, boolean z);

        @NativeClassQualifiedName("CronetBidirectionalStreamAdapter")
        boolean readData(long j2, CronetBidirectionalStream cronetBidirectionalStream, ByteBuffer byteBuffer, int i2, int i3);

        @NativeClassQualifiedName("CronetBidirectionalStreamAdapter")
        void sendRequestHeaders(long j2, CronetBidirectionalStream cronetBidirectionalStream);

        @NativeClassQualifiedName("CronetBidirectionalStreamAdapter")
        int start(long j2, CronetBidirectionalStream cronetBidirectionalStream, String str, int i2, String str2, String[] strArr, boolean z);

        @NativeClassQualifiedName("CronetBidirectionalStreamAdapter")
        boolean writevData(long j2, CronetBidirectionalStream cronetBidirectionalStream, ByteBuffer[] byteBufferArr, int[] iArr, int[] iArr2, boolean z);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    private @interface State {
        public static final int CANCELED = 5;
        public static final int ERROR = 6;
        public static final int NOT_STARTED = 0;
        public static final int READING = 3;
        public static final int READING_DONE = 4;
        public static final int STARTED = 1;
        public static final int SUCCESS = 7;
        public static final int WAITING_FOR_FLUSH = 8;
        public static final int WAITING_FOR_READ = 2;
        public static final int WRITING = 9;
        public static final int WRITING_DONE = 10;
    }

    /* loaded from: classes9.dex */
    private final class OnReadCompletedRunnable implements Runnable {
        ByteBuffer mByteBuffer;
        boolean mEndOfStream;

        private OnReadCompletedRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ByteBuffer byteBuffer = this.mByteBuffer;
                this.mByteBuffer = null;
                synchronized (CronetBidirectionalStream.this.mNativeStreamLock) {
                    if (CronetBidirectionalStream.this.isDoneLocked()) {
                        return;
                    }
                    boolean z = false;
                    if (this.mEndOfStream) {
                        CronetBidirectionalStream.this.mReadState = 4;
                        if (CronetBidirectionalStream.this.mWriteState == 10) {
                            z = true;
                        }
                    } else {
                        CronetBidirectionalStream.this.mReadState = 2;
                    }
                    VersionSafeCallbacks.BidirectionalStreamCallback bidirectionalStreamCallback = CronetBidirectionalStream.this.mCallback;
                    CronetBidirectionalStream cronetBidirectionalStream = CronetBidirectionalStream.this;
                    bidirectionalStreamCallback.onReadCompleted(cronetBidirectionalStream, cronetBidirectionalStream.mResponseInfo, byteBuffer, this.mEndOfStream);
                    if (z) {
                        CronetBidirectionalStream.this.maybeOnSucceededOnExecutor();
                    }
                }
            } catch (Exception e2) {
                CronetBidirectionalStream.this.onCallbackException(e2);
            }
        }
    }

    /* loaded from: classes9.dex */
    private final class OnWriteCompletedRunnable implements Runnable {
        private ByteBuffer mByteBuffer;
        private final boolean mEndOfStream;

        OnWriteCompletedRunnable(ByteBuffer byteBuffer, boolean z) {
            this.mByteBuffer = byteBuffer;
            this.mEndOfStream = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ByteBuffer byteBuffer = this.mByteBuffer;
                this.mByteBuffer = null;
                synchronized (CronetBidirectionalStream.this.mNativeStreamLock) {
                    if (CronetBidirectionalStream.this.isDoneLocked()) {
                        return;
                    }
                    boolean z = false;
                    if (this.mEndOfStream) {
                        CronetBidirectionalStream.this.mWriteState = 10;
                        if (CronetBidirectionalStream.this.mReadState == 4) {
                            z = true;
                        }
                    }
                    VersionSafeCallbacks.BidirectionalStreamCallback bidirectionalStreamCallback = CronetBidirectionalStream.this.mCallback;
                    CronetBidirectionalStream cronetBidirectionalStream = CronetBidirectionalStream.this;
                    bidirectionalStreamCallback.onWriteCompleted(cronetBidirectionalStream, cronetBidirectionalStream.mResponseInfo, byteBuffer, this.mEndOfStream);
                    if (z) {
                        CronetBidirectionalStream.this.maybeOnSucceededOnExecutor();
                    }
                }
            } catch (Exception e2) {
                CronetBidirectionalStream.this.onCallbackException(e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CronetBidirectionalStream(CronetUrlRequestContext cronetUrlRequestContext, String str, int i2, BidirectionalStream.Callback callback, Executor executor, String str2, List<Map.Entry<String, String>> list, boolean z, Collection<Object> collection, boolean z2, int i3, boolean z3, int i4, long j2) {
        this.mRequestContext = cronetUrlRequestContext;
        this.mInitialUrl = str;
        this.mInitialPriority = convertStreamPriority(i2);
        this.mCallback = new VersionSafeCallbacks.BidirectionalStreamCallback(callback);
        this.mExecutor = executor;
        this.mInitialMethod = str2;
        this.mRequestHeaders = stringsFromHeaderList(list);
        this.mDelayRequestHeadersUntilFirstFlush = z;
        this.mRequestAnnotations = collection;
        this.mTrafficStatsTagSet = z2;
        this.mTrafficStatsTag = i3;
        this.mTrafficStatsUidSet = z3;
        this.mTrafficStatsUid = i4;
        this.mNetworkHandle = j2;
    }

    @Override // com.ttnet.org.chromium.net.BidirectionalStream
    public void start() {
        synchronized (this.mNativeStreamLock) {
            if (this.mReadState != 0) {
                throw new IllegalStateException("Stream is already started.");
            }
            try {
                this.mNativeStream = CronetBidirectionalStreamJni.get().createBidirectionalStream(this, this.mRequestContext.getUrlRequestContextAdapter(), !this.mDelayRequestHeadersUntilFirstFlush, this.mTrafficStatsTagSet, this.mTrafficStatsTag, this.mTrafficStatsUidSet, this.mTrafficStatsUid, this.mNetworkHandle);
                this.mRequestContext.onRequestStarted();
                Natives natives = CronetBidirectionalStreamJni.get();
                long j2 = this.mNativeStream;
                String str = this.mInitialUrl;
                int i2 = this.mInitialPriority;
                String str2 = this.mInitialMethod;
                int start = natives.start(j2, this, str, i2, str2, this.mRequestHeaders, !doesMethodAllowWriteData(str2));
                if (start == -1) {
                    throw new IllegalArgumentException("Invalid http method " + this.mInitialMethod);
                }
                if (start > 0) {
                    int i3 = start - 1;
                    throw new IllegalArgumentException("Invalid header " + this.mRequestHeaders[i3] + "=" + this.mRequestHeaders[i3 + 1]);
                } else {
                    this.mWriteState = 1;
                    this.mReadState = 1;
                }
            } catch (RuntimeException e2) {
                destroyNativeStreamLocked(false);
                throw e2;
            }
        }
    }

    @Override // com.ttnet.org.chromium.net.BidirectionalStream
    public void read(ByteBuffer byteBuffer) {
        synchronized (this.mNativeStreamLock) {
            Preconditions.checkHasRemaining(byteBuffer);
            Preconditions.checkDirect(byteBuffer);
            if (this.mReadState != 2) {
                throw new IllegalStateException("Unexpected read attempt.");
            }
            if (isDoneLocked()) {
                return;
            }
            if (this.mOnReadCompletedTask == null) {
                this.mOnReadCompletedTask = new OnReadCompletedRunnable();
            }
            this.mReadState = 3;
            if (CronetBidirectionalStreamJni.get().readData(this.mNativeStream, this, byteBuffer, byteBuffer.position(), byteBuffer.limit())) {
                return;
            }
            this.mReadState = 2;
            throw new IllegalArgumentException("Unable to call native read");
        }
    }

    @Override // com.ttnet.org.chromium.net.BidirectionalStream
    public void write(ByteBuffer byteBuffer, boolean z) {
        synchronized (this.mNativeStreamLock) {
            Preconditions.checkDirect(byteBuffer);
            if (!byteBuffer.hasRemaining() && !z) {
                throw new IllegalArgumentException("Empty buffer before end of stream.");
            }
            if (this.mEndOfStreamWritten) {
                throw new IllegalArgumentException("Write after writing end of stream.");
            }
            if (isDoneLocked()) {
                return;
            }
            this.mPendingData.add(byteBuffer);
            if (z) {
                this.mEndOfStreamWritten = true;
            }
        }
    }

    @Override // com.ttnet.org.chromium.net.BidirectionalStream
    public void flush() {
        int i2;
        synchronized (this.mNativeStreamLock) {
            if (!isDoneLocked() && ((i2 = this.mWriteState) == 8 || i2 == 9)) {
                if (this.mPendingData.isEmpty() && this.mFlushData.isEmpty()) {
                    if (!this.mRequestHeadersSent) {
                        this.mRequestHeadersSent = true;
                        CronetBidirectionalStreamJni.get().sendRequestHeaders(this.mNativeStream, this);
                        if (!doesMethodAllowWriteData(this.mInitialMethod)) {
                            this.mWriteState = 10;
                        }
                    }
                    return;
                }
                if (!this.mPendingData.isEmpty()) {
                    this.mFlushData.addAll(this.mPendingData);
                    this.mPendingData.clear();
                }
                if (this.mWriteState == 9) {
                    return;
                }
                sendFlushDataLocked();
            }
        }
    }

    private void sendFlushDataLocked() {
        int size = this.mFlushData.size();
        ByteBuffer[] byteBufferArr = new ByteBuffer[size];
        int[] iArr = new int[size];
        int[] iArr2 = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            ByteBuffer poll = this.mFlushData.poll();
            byteBufferArr[i2] = poll;
            iArr[i2] = poll.position();
            iArr2[i2] = poll.limit();
        }
        this.mWriteState = 9;
        this.mRequestHeadersSent = true;
        if (CronetBidirectionalStreamJni.get().writevData(this.mNativeStream, this, byteBufferArr, iArr, iArr2, this.mEndOfStreamWritten && this.mPendingData.isEmpty())) {
            return;
        }
        this.mWriteState = 8;
        throw new IllegalArgumentException("Unable to call native writev.");
    }

    public List<ByteBuffer> getPendingDataForTesting() {
        LinkedList linkedList;
        synchronized (this.mNativeStreamLock) {
            linkedList = new LinkedList();
            Iterator<ByteBuffer> it = this.mPendingData.iterator();
            while (it.hasNext()) {
                linkedList.add(it.next().asReadOnlyBuffer());
            }
        }
        return linkedList;
    }

    public List<ByteBuffer> getFlushDataForTesting() {
        LinkedList linkedList;
        synchronized (this.mNativeStreamLock) {
            linkedList = new LinkedList();
            Iterator<ByteBuffer> it = this.mFlushData.iterator();
            while (it.hasNext()) {
                linkedList.add(it.next().asReadOnlyBuffer());
            }
        }
        return linkedList;
    }

    @Override // com.ttnet.org.chromium.net.BidirectionalStream
    public void cancel() {
        synchronized (this.mNativeStreamLock) {
            if (!isDoneLocked() && this.mReadState != 0) {
                this.mWriteState = 5;
                this.mReadState = 5;
                destroyNativeStreamLocked(true);
            }
        }
    }

    @Override // com.ttnet.org.chromium.net.BidirectionalStream
    public boolean isDone() {
        boolean isDoneLocked;
        synchronized (this.mNativeStreamLock) {
            isDoneLocked = isDoneLocked();
        }
        return isDoneLocked;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDoneLocked() {
        return this.mReadState != 0 && this.mNativeStream == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeOnSucceededOnExecutor() {
        synchronized (this.mNativeStreamLock) {
            if (isDoneLocked()) {
                return;
            }
            if (this.mWriteState == 10 && this.mReadState == 4) {
                this.mWriteState = 7;
                this.mReadState = 7;
                destroyNativeStreamLocked(false);
                try {
                    this.mCallback.onSucceeded(this, this.mResponseInfo);
                } catch (Exception e2) {
                    Log.e(CronetUrlRequestContext.LOG_TAG, "Exception in onSucceeded method", e2);
                }
            }
        }
    }

    private void onStreamReady(final boolean z) {
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetBidirectionalStream.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (CronetBidirectionalStream.this.mNativeStreamLock) {
                    if (CronetBidirectionalStream.this.isDoneLocked()) {
                        return;
                    }
                    CronetBidirectionalStream.this.mRequestHeadersSent = z;
                    CronetBidirectionalStream.this.mReadState = 2;
                    if (CronetBidirectionalStream.doesMethodAllowWriteData(CronetBidirectionalStream.this.mInitialMethod) || !CronetBidirectionalStream.this.mRequestHeadersSent) {
                        CronetBidirectionalStream.this.mWriteState = 8;
                    } else {
                        CronetBidirectionalStream.this.mWriteState = 10;
                    }
                    try {
                        CronetBidirectionalStream.this.mCallback.onStreamReady(CronetBidirectionalStream.this);
                    } catch (Exception e2) {
                        CronetBidirectionalStream.this.onCallbackException(e2);
                    }
                }
            }
        });
    }

    private void onResponseHeadersReceived(int i2, String str, String[] strArr, long j2) {
        try {
            this.mResponseInfo = prepareResponseInfoOnNetworkThread(i2, str, strArr, j2);
            postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetBidirectionalStream.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (CronetBidirectionalStream.this.mNativeStreamLock) {
                        if (CronetBidirectionalStream.this.isDoneLocked()) {
                            return;
                        }
                        CronetBidirectionalStream.this.mReadState = 2;
                        try {
                            VersionSafeCallbacks.BidirectionalStreamCallback bidirectionalStreamCallback = CronetBidirectionalStream.this.mCallback;
                            CronetBidirectionalStream cronetBidirectionalStream = CronetBidirectionalStream.this;
                            bidirectionalStreamCallback.onResponseHeadersReceived(cronetBidirectionalStream, cronetBidirectionalStream.mResponseInfo);
                        } catch (Exception e2) {
                            CronetBidirectionalStream.this.onCallbackException(e2);
                        }
                    }
                }
            });
        } catch (Exception unused) {
            failWithException(new CronetExceptionImpl("Cannot prepare ResponseInfo", null));
        }
    }

    private void onReadCompleted(ByteBuffer byteBuffer, int i2, int i3, int i4, long j2) {
        int i5;
        this.mResponseInfo.setReceivedByteCount(j2);
        if (byteBuffer.position() != i3 || byteBuffer.limit() != i4) {
            failWithException(new CronetExceptionImpl("ByteBuffer modified externally during read", null));
        } else if (i2 < 0 || (i5 = i3 + i2) > i4) {
            failWithException(new CronetExceptionImpl("Invalid number of bytes read", null));
        } else {
            byteBuffer.position(i5);
            this.mOnReadCompletedTask.mByteBuffer = byteBuffer;
            this.mOnReadCompletedTask.mEndOfStream = i2 == 0;
            postTaskToExecutor(this.mOnReadCompletedTask);
        }
    }

    private void onWritevCompleted(ByteBuffer[] byteBufferArr, int[] iArr, int[] iArr2, boolean z) {
        synchronized (this.mNativeStreamLock) {
            if (isDoneLocked()) {
                return;
            }
            this.mWriteState = 8;
            if (!this.mFlushData.isEmpty()) {
                sendFlushDataLocked();
            }
            for (int i2 = 0; i2 < byteBufferArr.length; i2++) {
                ByteBuffer byteBuffer = byteBufferArr[i2];
                if (byteBuffer.position() != iArr[i2] || byteBuffer.limit() != iArr2[i2]) {
                    failWithException(new CronetExceptionImpl("ByteBuffer modified externally during write", null));
                    return;
                }
                byteBuffer.position(byteBuffer.limit());
                boolean z2 = true;
                if (!z || i2 != byteBufferArr.length - 1) {
                    z2 = false;
                }
                postTaskToExecutor(new OnWriteCompletedRunnable(byteBuffer, z2));
            }
        }
    }

    private void onResponseTrailersReceived(String[] strArr) {
        final UrlResponseInfoImpl.HeaderBlockImpl headerBlockImpl = new UrlResponseInfoImpl.HeaderBlockImpl(headersListFromStrings(strArr));
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetBidirectionalStream.3
            @Override // java.lang.Runnable
            public void run() {
                synchronized (CronetBidirectionalStream.this.mNativeStreamLock) {
                    if (CronetBidirectionalStream.this.isDoneLocked()) {
                        return;
                    }
                    try {
                        VersionSafeCallbacks.BidirectionalStreamCallback bidirectionalStreamCallback = CronetBidirectionalStream.this.mCallback;
                        CronetBidirectionalStream cronetBidirectionalStream = CronetBidirectionalStream.this;
                        bidirectionalStreamCallback.onResponseTrailersReceived(cronetBidirectionalStream, cronetBidirectionalStream.mResponseInfo, headerBlockImpl);
                    } catch (Exception e2) {
                        CronetBidirectionalStream.this.onCallbackException(e2);
                    }
                }
            }
        });
    }

    private void onError(int i2, int i3, int i4, String str, long j2) {
        UrlResponseInfoImpl urlResponseInfoImpl = this.mResponseInfo;
        if (urlResponseInfoImpl != null) {
            urlResponseInfoImpl.setReceivedByteCount(j2);
        }
        if (i2 == 10 || i2 == 3) {
            failWithException(new QuicExceptionImpl("Exception in BidirectionalStream: " + str, i2, i3, i4));
        } else {
            failWithException(new BidirectionalStreamNetworkException("Exception in BidirectionalStream: " + str, i2, i3));
        }
    }

    private void onCanceled() {
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetBidirectionalStream.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    VersionSafeCallbacks.BidirectionalStreamCallback bidirectionalStreamCallback = CronetBidirectionalStream.this.mCallback;
                    CronetBidirectionalStream cronetBidirectionalStream = CronetBidirectionalStream.this;
                    bidirectionalStreamCallback.onCanceled(cronetBidirectionalStream, cronetBidirectionalStream.mResponseInfo);
                } catch (Exception e2) {
                    Log.e(CronetUrlRequestContext.LOG_TAG, "Exception in onCanceled method", e2);
                }
            }
        });
    }

    private void onMetricsCollected(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, boolean z, long j15, long j16, String str, long j17) {
        synchronized (this.mNativeStreamLock) {
            if (this.mMetrics != null) {
                throw new IllegalStateException("Metrics collection should only happen once.");
            }
            this.mMetrics = new CronetMetrics(j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, z, j15, j16, str, j17, 0L, "", "", 0L, 0L, -1, false, false, -1);
            int i2 = this.mReadState;
            this.mRequestContext.reportRequestFinished(new RequestFinishedInfoImpl(this.mInitialUrl, this.mRequestAnnotations, this.mMetrics, i2 == 7 ? 0 : i2 == 5 ? 2 : 1, this.mResponseInfo, this.mException));
        }
    }

    public void setOnDestroyedCallbackForTesting(Runnable runnable) {
        this.mOnDestroyedCallbackForTesting = runnable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean doesMethodAllowWriteData(String str) {
        return (str.equals("GET") || str.equals(HttpMethodContrants.HEAD)) ? false : true;
    }

    private static ArrayList<Map.Entry<String, String>> headersListFromStrings(String[] strArr) {
        ArrayList<Map.Entry<String, String>> arrayList = new ArrayList<>(strArr.length / 2);
        for (int i2 = 0; i2 < strArr.length; i2 += 2) {
            arrayList.add(new AbstractMap.SimpleImmutableEntry(strArr[i2], strArr[i2 + 1]));
        }
        return arrayList;
    }

    private static String[] stringsFromHeaderList(List<Map.Entry<String, String>> list) {
        String[] strArr = new String[list.size() * 2];
        int i2 = 0;
        for (Map.Entry<String, String> entry : list) {
            int i3 = i2 + 1;
            strArr[i2] = entry.getKey();
            i2 = i3 + 1;
            strArr[i3] = entry.getValue();
        }
        return strArr;
    }

    private static int convertStreamPriority(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 == 4) {
                            return 5;
                        }
                        throw new IllegalArgumentException("Invalid stream priority.");
                    }
                    return 4;
                }
                return 3;
            }
            return 2;
        }
        return 1;
    }

    private void postTaskToExecutor(Runnable runnable) {
        try {
            this.mExecutor.execute(runnable);
        } catch (RejectedExecutionException e2) {
            Log.e(CronetUrlRequestContext.LOG_TAG, "Exception posting task to executor", e2);
            synchronized (this.mNativeStreamLock) {
                this.mWriteState = 6;
                this.mReadState = 6;
                destroyNativeStreamLocked(false);
            }
        }
    }

    private UrlResponseInfoImpl prepareResponseInfoOnNetworkThread(int i2, String str, String[] strArr, long j2) {
        return new UrlResponseInfoImpl(Arrays.asList(this.mInitialUrl), i2, "", headersListFromStrings(strArr), false, str, null, j2);
    }

    private void destroyNativeStreamLocked(boolean z) {
        Log.i(CronetUrlRequestContext.LOG_TAG, "destroyNativeStreamLocked " + toString(), new Object[0]);
        if (this.mNativeStream == 0) {
            return;
        }
        CronetBidirectionalStreamJni.get().destroy(this.mNativeStream, this, z);
        this.mRequestContext.onRequestDestroyed();
        this.mNativeStream = 0L;
        Runnable runnable = this.mOnDestroyedCallbackForTesting;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void failWithExceptionOnExecutor(CronetException cronetException) {
        this.mException = cronetException;
        synchronized (this.mNativeStreamLock) {
            if (isDoneLocked()) {
                return;
            }
            this.mWriteState = 6;
            this.mReadState = 6;
            destroyNativeStreamLocked(false);
            try {
                this.mCallback.onFailed(this, this.mResponseInfo, cronetException);
            } catch (Exception e2) {
                Log.e(CronetUrlRequestContext.LOG_TAG, "Exception notifying of failed request", e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCallbackException(Exception exc) {
        CallbackExceptionImpl callbackExceptionImpl = new CallbackExceptionImpl("CalledByNative method has thrown an exception", exc);
        Log.e(CronetUrlRequestContext.LOG_TAG, "Exception in CalledByNative method", exc);
        failWithExceptionOnExecutor(callbackExceptionImpl);
    }

    private void failWithException(final CronetException cronetException) {
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetBidirectionalStream.5
            @Override // java.lang.Runnable
            public void run() {
                CronetBidirectionalStream.this.failWithExceptionOnExecutor(cronetException);
            }
        });
    }
}
