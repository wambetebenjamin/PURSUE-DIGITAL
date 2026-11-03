package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.NativeClassQualifiedName;
import com.ttnet.org.chromium.net.UploadDataProvider;
import com.ttnet.org.chromium.net.UploadDataSink;
import com.ttnet.org.chromium.net.impl.VersionSafeCallbacks;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
@JNINamespace("cronet")
/* loaded from: classes9.dex */
public final class CronetUploadDataStream extends UploadDataSink {
    private static final String TAG = "CronetUploadDataStream";
    private ByteBuffer mByteBuffer;
    private long mByteBufferLimit;
    private final VersionSafeCallbacks.UploadDataProviderWrapper mDataProvider;
    private boolean mDestroyAdapterPostponed;
    private final Executor mExecutor;
    private long mLength;
    private Runnable mOnDestroyedCallbackForTesting;
    private long mRemainingLength;
    private final CronetUrlRequest mRequest;
    private long mUploadDataStreamAdapter;
    private final Runnable mReadTask = new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetUploadDataStream.1
        static final /* synthetic */ boolean $assertionsDisabled = false;

        @Override // java.lang.Runnable
        public void run() {
            synchronized (CronetUploadDataStream.this.mLock) {
                if (CronetUploadDataStream.this.mUploadDataStreamAdapter == 0) {
                    return;
                }
                CronetUploadDataStream.this.checkState(3);
                if (CronetUploadDataStream.this.mByteBuffer != null) {
                    CronetUploadDataStream.this.mInWhichUserCallback = 0;
                    try {
                        CronetUploadDataStream.this.checkCallingThread();
                        VersionSafeCallbacks.UploadDataProviderWrapper uploadDataProviderWrapper = CronetUploadDataStream.this.mDataProvider;
                        CronetUploadDataStream cronetUploadDataStream = CronetUploadDataStream.this;
                        uploadDataProviderWrapper.read(cronetUploadDataStream, cronetUploadDataStream.mByteBuffer);
                        return;
                    } catch (Exception e2) {
                        CronetUploadDataStream.this.onError(e2);
                        return;
                    }
                }
                throw new IllegalStateException("Unexpected readData call. Buffer is null");
            }
        }
    };
    private final Object mLock = new Object();
    private int mInWhichUserCallback = 3;
    private volatile boolean mClosed = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        long attachUploadDataToRequest(CronetUploadDataStream cronetUploadDataStream, long j2, long j3);

        long createAdapterForTesting(CronetUploadDataStream cronetUploadDataStream);

        long createUploadDataStreamForTesting(CronetUploadDataStream cronetUploadDataStream, long j2, long j3);

        @NativeClassQualifiedName("CronetUploadDataStreamAdapter")
        void destroy(long j2);

        @NativeClassQualifiedName("CronetUploadDataStreamAdapter")
        void onReadSucceeded(long j2, CronetUploadDataStream cronetUploadDataStream, int i2, boolean z);

        @NativeClassQualifiedName("CronetUploadDataStreamAdapter")
        void onRewindSucceeded(long j2, CronetUploadDataStream cronetUploadDataStream);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    private @interface UserCallback {
        public static final int GET_LENGTH = 2;
        public static final int NOT_IN_CALLBACK = 3;
        public static final int READ = 0;
        public static final int REWIND = 1;
    }

    public CronetUploadDataStream(UploadDataProvider uploadDataProvider, Executor executor, CronetUrlRequest cronetUrlRequest) {
        this.mExecutor = executor;
        this.mDataProvider = new VersionSafeCallbacks.UploadDataProviderWrapper(uploadDataProvider);
        this.mRequest = cronetUrlRequest;
    }

    void readData(ByteBuffer byteBuffer) {
        this.mByteBuffer = byteBuffer;
        this.mByteBufferLimit = byteBuffer.limit();
        postTaskToExecutor(this.mReadTask);
    }

    void rewind() {
        postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetUploadDataStream.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (CronetUploadDataStream.this.mLock) {
                    if (CronetUploadDataStream.this.mUploadDataStreamAdapter == 0) {
                        return;
                    }
                    CronetUploadDataStream.this.checkState(3);
                    CronetUploadDataStream.this.mInWhichUserCallback = 1;
                    try {
                        CronetUploadDataStream.this.checkCallingThread();
                        CronetUploadDataStream.this.mDataProvider.rewind(CronetUploadDataStream.this);
                    } catch (Exception e2) {
                        CronetUploadDataStream.this.onError(e2);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkCallingThread() {
        this.mRequest.checkCallingThread();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkState(int i2) {
        if (this.mInWhichUserCallback != i2) {
            throw new IllegalStateException("Expected " + i2 + ", but was " + this.mInWhichUserCallback);
        }
    }

    void onUploadDataStreamDestroyed() {
        destroyAdapter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onError(Throwable th) {
        boolean z;
        synchronized (this.mLock) {
            int i2 = this.mInWhichUserCallback;
            if (i2 == 3) {
                throw new IllegalStateException("There is no read or rewind or length check in progress.");
            }
            z = i2 == 2;
            this.mInWhichUserCallback = 3;
            this.mByteBuffer = null;
            destroyAdapterIfPostponed();
        }
        if (z) {
            try {
                this.mDataProvider.close();
                this.mClosed = true;
            } catch (Exception e2) {
                Log.e(TAG, "Failure closing data provider", e2);
            }
        }
        this.mRequest.onUploadException(th);
    }

    @Override // com.ttnet.org.chromium.net.UploadDataSink
    public void onReadSucceeded(boolean z) {
        synchronized (this.mLock) {
            checkState(0);
            if (this.mByteBufferLimit != this.mByteBuffer.limit()) {
                throw new IllegalStateException("ByteBuffer limit changed");
            }
            if (z && this.mLength >= 0) {
                throw new IllegalArgumentException("Non-chunked upload can't have last chunk");
            }
            int position = this.mByteBuffer.position();
            long j2 = this.mRemainingLength - position;
            this.mRemainingLength = j2;
            if (j2 < 0 && this.mLength >= 0) {
                throw new IllegalArgumentException(String.format("Read upload data length %d exceeds expected length %d", Long.valueOf(this.mLength - this.mRemainingLength), Long.valueOf(this.mLength)));
            }
            this.mByteBuffer.position(0);
            this.mByteBuffer = null;
            this.mInWhichUserCallback = 3;
            destroyAdapterIfPostponed();
            if (this.mUploadDataStreamAdapter == 0) {
                return;
            }
            CronetUploadDataStreamJni.get().onReadSucceeded(this.mUploadDataStreamAdapter, this, position, z);
        }
    }

    @Override // com.ttnet.org.chromium.net.UploadDataSink
    public void onReadError(Exception exc) {
        synchronized (this.mLock) {
            checkState(0);
            onError(exc);
        }
    }

    @Override // com.ttnet.org.chromium.net.UploadDataSink
    public void onRewindSucceeded() {
        synchronized (this.mLock) {
            checkState(1);
            this.mInWhichUserCallback = 3;
            this.mRemainingLength = this.mLength;
            if (this.mUploadDataStreamAdapter == 0) {
                return;
            }
            CronetUploadDataStreamJni.get().onRewindSucceeded(this.mUploadDataStreamAdapter, this);
        }
    }

    @Override // com.ttnet.org.chromium.net.UploadDataSink
    public void onRewindError(Exception exc) {
        synchronized (this.mLock) {
            checkState(1);
            onError(exc);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postTaskToExecutor(Runnable runnable) {
        try {
            this.mExecutor.execute(runnable);
        } catch (Throwable th) {
            CronetUrlRequest cronetUrlRequest = this.mRequest;
            if (cronetUrlRequest != null) {
                cronetUrlRequest.onUploadException(th);
                return;
            }
            throw new IllegalStateException("Unexpected request usage, caught in CronetUploadDataStream, caused by " + th);
        }
    }

    private void destroyAdapter() {
        synchronized (this.mLock) {
            if (this.mInWhichUserCallback == 0) {
                this.mDestroyAdapterPostponed = true;
            } else if (this.mUploadDataStreamAdapter == 0) {
            } else {
                CronetUploadDataStreamJni.get().destroy(this.mUploadDataStreamAdapter);
                this.mUploadDataStreamAdapter = 0L;
                Runnable runnable = this.mOnDestroyedCallbackForTesting;
                if (runnable != null) {
                    runnable.run();
                }
                postTaskToExecutor(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetUploadDataStream.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            CronetUploadDataStream.this.checkCallingThread();
                            if (CronetUploadDataStream.this.mClosed) {
                                return;
                            }
                            CronetUploadDataStream.this.mDataProvider.close();
                        } catch (Exception e2) {
                            Log.e(CronetUploadDataStream.TAG, "Exception thrown when closing", e2);
                        }
                    }
                });
            }
        }
    }

    private void destroyAdapterIfPostponed() {
        synchronized (this.mLock) {
            if (this.mInWhichUserCallback == 0) {
                throw new IllegalStateException("Method should not be called when read has not completed.");
            }
            if (this.mDestroyAdapterPostponed) {
                destroyAdapter();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initializeWithRequest() {
        synchronized (this.mLock) {
            this.mInWhichUserCallback = 2;
        }
        try {
            this.mRequest.checkCallingThread();
            long length = this.mDataProvider.getLength();
            this.mLength = length;
            this.mRemainingLength = length;
        } catch (Throwable th) {
            onError(th);
        }
        synchronized (this.mLock) {
            this.mInWhichUserCallback = 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void attachNativeAdapterToRequest(long j2) {
        synchronized (this.mLock) {
            this.mUploadDataStreamAdapter = CronetUploadDataStreamJni.get().attachUploadDataToRequest(this, j2, this.mLength);
        }
    }

    public long createUploadDataStreamForTesting() {
        long createUploadDataStreamForTesting;
        synchronized (this.mLock) {
            this.mUploadDataStreamAdapter = CronetUploadDataStreamJni.get().createAdapterForTesting(this);
            long length = this.mDataProvider.getLength();
            this.mLength = length;
            this.mRemainingLength = length;
            createUploadDataStreamForTesting = CronetUploadDataStreamJni.get().createUploadDataStreamForTesting(this, this.mLength, this.mUploadDataStreamAdapter);
        }
        return createUploadDataStreamForTesting;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnDestroyedCallbackForTesting(Runnable runnable) {
        this.mOnDestroyedCallbackForTesting = runnable;
    }
}
