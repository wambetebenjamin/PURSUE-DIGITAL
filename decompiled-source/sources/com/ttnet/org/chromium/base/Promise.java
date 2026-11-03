package com.ttnet.org.chromium.base;

import android.os.Handler;
import com.ttnet.org.chromium.base.Promise;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes9.dex */
public class Promise<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Exception mRejectReason;
    private T mResult;
    private boolean mThrowingRejectionHandler;
    private int mState = 0;
    private final List<Callback<T>> mFulfillCallbacks = new LinkedList();
    private final List<Callback<Exception>> mRejectCallbacks = new LinkedList();
    private final Thread mThread = Thread.currentThread();
    private final Handler mHandler = new Handler();

    /* loaded from: classes9.dex */
    public interface AsyncFunction<A, RT> extends Function<A, Promise<RT>> {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    private @interface PromiseState {
        public static final int FULFILLED = 1;
        public static final int REJECTED = 2;
        public static final int UNFULFILLED = 0;
    }

    private void checkThread() {
    }

    /* loaded from: classes9.dex */
    public static class UnhandledRejectionException extends RuntimeException {
        public UnhandledRejectionException(String str, Throwable th) {
            super(str, th);
        }
    }

    public void then(Callback<T> callback) {
        checkThread();
        if (this.mThrowingRejectionHandler) {
            thenInner(callback);
            return;
        }
        then(callback, new Callback() { // from class: com.ttnet.org.chromium.base.Promise$$ExternalSyntheticLambda4
            @Override // com.ttnet.org.chromium.base.Callback
            public final void onResult(Object obj) {
                Promise.lambda$then$0((Exception) obj);
            }
        });
        this.mThrowingRejectionHandler = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$then$0(Exception exc) {
        throw new UnhandledRejectionException("Promise was rejected without a rejection handler.", exc);
    }

    public void then(Callback<T> callback, Callback<Exception> callback2) {
        checkThread();
        thenInner(callback);
        exceptInner(callback2);
    }

    public void except(Callback<Exception> callback) {
        checkThread();
        exceptInner(callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void thenInner(Callback<T> callback) {
        int i2 = this.mState;
        if (i2 == 1) {
            postCallbackToLooper(callback, this.mResult);
        } else if (i2 == 0) {
            this.mFulfillCallbacks.add(callback);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void exceptInner(Callback<Exception> callback) {
        int i2 = this.mState;
        if (i2 == 2) {
            postCallbackToLooper(callback, this.mRejectReason);
        } else if (i2 == 0) {
            this.mRejectCallbacks.add(callback);
        }
    }

    public <RT> Promise<RT> then(final Function<T, RT> function) {
        checkThread();
        final Promise<RT> promise = new Promise<>();
        thenInner(new Callback() { // from class: com.ttnet.org.chromium.base.Promise$$ExternalSyntheticLambda3
            @Override // com.ttnet.org.chromium.base.Callback
            public final void onResult(Object obj) {
                Promise.lambda$then$1(Promise.this, function, obj);
            }
        });
        Objects.requireNonNull(promise);
        exceptInner(new Promise$$ExternalSyntheticLambda1(promise));
        return promise;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void lambda$then$1(Promise promise, Function function, Object obj) {
        try {
            promise.fulfill(function.apply(obj));
        } catch (Exception e2) {
            promise.reject(e2);
        }
    }

    public <RT> Promise<RT> then(final AsyncFunction<T, RT> asyncFunction) {
        checkThread();
        final Promise<RT> promise = new Promise<>();
        thenInner(new Callback() { // from class: com.ttnet.org.chromium.base.Promise$$ExternalSyntheticLambda0
            @Override // com.ttnet.org.chromium.base.Callback
            public final void onResult(Object obj) {
                Promise.lambda$then$2(Promise.AsyncFunction.this, promise, obj);
            }
        });
        Objects.requireNonNull(promise);
        exceptInner(new Promise$$ExternalSyntheticLambda1(promise));
        return promise;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$then$2(AsyncFunction asyncFunction, final Promise promise, Object obj) {
        try {
            Objects.requireNonNull(promise);
            Callback<T> callback = new Callback() { // from class: com.ttnet.org.chromium.base.Promise$$ExternalSyntheticLambda2
                @Override // com.ttnet.org.chromium.base.Callback
                public final void onResult(Object obj2) {
                    Promise.this.fulfill(obj2);
                }
            };
            Objects.requireNonNull(promise);
            asyncFunction.apply(obj).then(callback, new Promise$$ExternalSyntheticLambda1(promise));
        } catch (Exception e2) {
            promise.reject(e2);
        }
    }

    public void fulfill(T t) {
        checkThread();
        this.mState = 1;
        this.mResult = t;
        Iterator<Callback<T>> it = this.mFulfillCallbacks.iterator();
        while (it.hasNext()) {
            postCallbackToLooper((Callback<T>) it.next(), t);
        }
        this.mFulfillCallbacks.clear();
    }

    public void reject(Exception exc) {
        checkThread();
        this.mState = 2;
        this.mRejectReason = exc;
        Iterator<Callback<Exception>> it = this.mRejectCallbacks.iterator();
        while (it.hasNext()) {
            postCallbackToLooper(it.next(), exc);
        }
        this.mRejectCallbacks.clear();
    }

    public void reject() {
        reject(null);
    }

    public boolean isFulfilled() {
        checkThread();
        return this.mState == 1;
    }

    public boolean isRejected() {
        checkThread();
        return this.mState == 2;
    }

    public T getResult() {
        return this.mResult;
    }

    public static <T> Promise<T> fulfilled(T t) {
        Promise<T> promise = new Promise<>();
        promise.fulfill(t);
        return promise;
    }

    public static <T> Promise<T> rejected() {
        Promise<T> promise = new Promise<>();
        promise.reject();
        return promise;
    }

    private <S> void postCallbackToLooper(Callback<S> callback, S s) {
        this.mHandler.post(callback.bind(s));
    }
}
