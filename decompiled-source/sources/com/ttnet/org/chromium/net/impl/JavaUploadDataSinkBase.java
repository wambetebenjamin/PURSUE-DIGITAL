package com.ttnet.org.chromium.net.impl;

import android.support.v4.media.session.PlaybackStateCompat;
import com.ttnet.org.chromium.net.UploadDataProvider;
import com.ttnet.org.chromium.net.UploadDataSink;
import com.ttnet.org.chromium.net.impl.JavaUrlRequestUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes9.dex */
public abstract class JavaUploadDataSinkBase extends UploadDataSink {
    public static final int DEFAULT_UPLOAD_BUFFER_SIZE = 8192;
    private ByteBuffer mBuffer;
    private final Executor mExecutor;
    private final AtomicInteger mSinkState = new AtomicInteger(3);
    private long mTotalBytes;
    private final UploadDataProvider mUploadProvider;
    private final Executor mUserUploadExecutor;
    private long mWrittenBytes;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    @interface SinkState {
        public static final int AWAITING_READ_RESULT = 0;
        public static final int AWAITING_REWIND_RESULT = 1;
        public static final int NOT_STARTED = 3;
        public static final int UPLOADING = 2;
    }

    protected abstract void finish();

    protected abstract Runnable getErrorSettingRunnable(JavaUrlRequestUtils.CheckedRunnable checkedRunnable);

    protected abstract Runnable getUploadErrorSettingRunnable(JavaUrlRequestUtils.CheckedRunnable checkedRunnable);

    protected abstract void initializeRead();

    protected abstract void initializeStart(long j2);

    protected abstract int processSuccessfulRead(ByteBuffer byteBuffer);

    protected abstract void processUploadError(Throwable th);

    static /* synthetic */ long access$214(JavaUploadDataSinkBase javaUploadDataSinkBase, long j2) {
        long j3 = javaUploadDataSinkBase.mWrittenBytes + j2;
        javaUploadDataSinkBase.mWrittenBytes = j3;
        return j3;
    }

    public JavaUploadDataSinkBase(final Executor executor, Executor executor2, UploadDataProvider uploadDataProvider) {
        this.mUserUploadExecutor = new Executor() { // from class: com.ttnet.org.chromium.net.impl.JavaUploadDataSinkBase.1
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                try {
                    executor.execute(runnable);
                } catch (RejectedExecutionException e2) {
                    JavaUploadDataSinkBase.this.processUploadError(e2);
                }
            }
        };
        this.mExecutor = executor2;
        this.mUploadProvider = uploadDataProvider;
    }

    @Override // com.ttnet.org.chromium.net.UploadDataSink
    public void onReadSucceeded(final boolean z) {
        if (!this.mSinkState.compareAndSet(0, 2)) {
            throw new IllegalStateException("onReadSucceeded() called when not awaiting a read result; in state: " + this.mSinkState.get());
        }
        this.mExecutor.execute(getErrorSettingRunnable(new JavaUrlRequestUtils.CheckedRunnable() { // from class: com.ttnet.org.chromium.net.impl.JavaUploadDataSinkBase.2
            @Override // com.ttnet.org.chromium.net.impl.JavaUrlRequestUtils.CheckedRunnable
            public void run() {
                JavaUploadDataSinkBase.this.mBuffer.flip();
                if (JavaUploadDataSinkBase.this.mTotalBytes != -1 && JavaUploadDataSinkBase.this.mTotalBytes - JavaUploadDataSinkBase.this.mWrittenBytes < JavaUploadDataSinkBase.this.mBuffer.remaining()) {
                    JavaUploadDataSinkBase.this.processUploadError(new IllegalArgumentException(String.format(Locale.getDefault(), "Read upload data length %d exceeds expected length %d", Long.valueOf(JavaUploadDataSinkBase.this.mWrittenBytes + JavaUploadDataSinkBase.this.mBuffer.remaining()), Long.valueOf(JavaUploadDataSinkBase.this.mTotalBytes))));
                    return;
                }
                JavaUploadDataSinkBase javaUploadDataSinkBase = JavaUploadDataSinkBase.this;
                JavaUploadDataSinkBase.access$214(javaUploadDataSinkBase, javaUploadDataSinkBase.processSuccessfulRead(javaUploadDataSinkBase.mBuffer));
                if (JavaUploadDataSinkBase.this.mWrittenBytes < JavaUploadDataSinkBase.this.mTotalBytes || (JavaUploadDataSinkBase.this.mTotalBytes == -1 && !z)) {
                    JavaUploadDataSinkBase.this.mBuffer.clear();
                    JavaUploadDataSinkBase.this.mSinkState.set(0);
                    JavaUploadDataSinkBase.this.executeOnUploadExecutor(new JavaUrlRequestUtils.CheckedRunnable() { // from class: com.ttnet.org.chromium.net.impl.JavaUploadDataSinkBase.2.1
                        @Override // com.ttnet.org.chromium.net.impl.JavaUrlRequestUtils.CheckedRunnable
                        public void run() {
                            JavaUploadDataSinkBase.this.mUploadProvider.read(JavaUploadDataSinkBase.this, JavaUploadDataSinkBase.this.mBuffer);
                        }
                    });
                } else if (JavaUploadDataSinkBase.this.mTotalBytes != -1) {
                    if (JavaUploadDataSinkBase.this.mTotalBytes == JavaUploadDataSinkBase.this.mWrittenBytes) {
                        JavaUploadDataSinkBase.this.finish();
                    } else {
                        JavaUploadDataSinkBase.this.processUploadError(new IllegalArgumentException(String.format(Locale.getDefault(), "Read upload data length %d exceeds expected length %d", Long.valueOf(JavaUploadDataSinkBase.this.mWrittenBytes), Long.valueOf(JavaUploadDataSinkBase.this.mTotalBytes))));
                    }
                } else {
                    JavaUploadDataSinkBase.this.finish();
                }
            }
        }));
    }

    @Override // com.ttnet.org.chromium.net.UploadDataSink
    public void onRewindSucceeded() {
        if (!this.mSinkState.compareAndSet(1, 2)) {
            throw new IllegalStateException("onRewindSucceeded() called when not awaiting a rewind; in state: " + this.mSinkState.get());
        }
        startRead();
    }

    @Override // com.ttnet.org.chromium.net.UploadDataSink
    public void onReadError(Exception exc) {
        processUploadError(exc);
    }

    @Override // com.ttnet.org.chromium.net.UploadDataSink
    public void onRewindError(Exception exc) {
        processUploadError(exc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRead() {
        this.mExecutor.execute(getErrorSettingRunnable(new JavaUrlRequestUtils.CheckedRunnable() { // from class: com.ttnet.org.chromium.net.impl.JavaUploadDataSinkBase.3
            @Override // com.ttnet.org.chromium.net.impl.JavaUrlRequestUtils.CheckedRunnable
            public void run() {
                JavaUploadDataSinkBase.this.initializeRead();
                JavaUploadDataSinkBase.this.mSinkState.set(0);
                JavaUploadDataSinkBase.this.executeOnUploadExecutor(new JavaUrlRequestUtils.CheckedRunnable() { // from class: com.ttnet.org.chromium.net.impl.JavaUploadDataSinkBase.3.1
                    @Override // com.ttnet.org.chromium.net.impl.JavaUrlRequestUtils.CheckedRunnable
                    public void run() {
                        JavaUploadDataSinkBase.this.mUploadProvider.read(JavaUploadDataSinkBase.this, JavaUploadDataSinkBase.this.mBuffer);
                    }
                });
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeOnUploadExecutor(JavaUrlRequestUtils.CheckedRunnable checkedRunnable) {
        try {
            this.mUserUploadExecutor.execute(getUploadErrorSettingRunnable(checkedRunnable));
        } catch (RejectedExecutionException e2) {
            processUploadError(e2);
        }
    }

    public void start(final boolean z) {
        executeOnUploadExecutor(new JavaUrlRequestUtils.CheckedRunnable() { // from class: com.ttnet.org.chromium.net.impl.JavaUploadDataSinkBase.4
            @Override // com.ttnet.org.chromium.net.impl.JavaUrlRequestUtils.CheckedRunnable
            public void run() {
                JavaUploadDataSinkBase javaUploadDataSinkBase = JavaUploadDataSinkBase.this;
                javaUploadDataSinkBase.mTotalBytes = javaUploadDataSinkBase.mUploadProvider.getLength();
                if (JavaUploadDataSinkBase.this.mTotalBytes != 0) {
                    if (JavaUploadDataSinkBase.this.mTotalBytes > 0 && JavaUploadDataSinkBase.this.mTotalBytes < PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                        JavaUploadDataSinkBase javaUploadDataSinkBase2 = JavaUploadDataSinkBase.this;
                        javaUploadDataSinkBase2.mBuffer = ByteBuffer.allocateDirect(((int) javaUploadDataSinkBase2.mTotalBytes) + 1);
                    } else {
                        JavaUploadDataSinkBase.this.mBuffer = ByteBuffer.allocateDirect(8192);
                    }
                    JavaUploadDataSinkBase javaUploadDataSinkBase3 = JavaUploadDataSinkBase.this;
                    javaUploadDataSinkBase3.initializeStart(javaUploadDataSinkBase3.mTotalBytes);
                    if (z) {
                        JavaUploadDataSinkBase.this.startRead();
                        return;
                    }
                    JavaUploadDataSinkBase.this.mSinkState.set(1);
                    JavaUploadDataSinkBase.this.mUploadProvider.rewind(JavaUploadDataSinkBase.this);
                    return;
                }
                JavaUploadDataSinkBase.this.finish();
            }
        });
    }
}
