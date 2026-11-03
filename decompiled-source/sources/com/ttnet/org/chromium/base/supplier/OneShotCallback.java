package com.ttnet.org.chromium.base.supplier;

import com.ttnet.org.chromium.base.Callback;
import java.lang.ref.WeakReference;
/* loaded from: classes9.dex */
public class OneShotCallback<E> {
    private final Callback<E> mCallback;
    private final Callback<E> mCallbackWrapper;
    private final WeakReference<ObservableSupplier<E>> mWeakSupplier;

    public OneShotCallback(ObservableSupplier<E> observableSupplier, Callback<E> callback) {
        CallbackWrapper callbackWrapper = new CallbackWrapper();
        this.mCallbackWrapper = callbackWrapper;
        this.mWeakSupplier = new WeakReference<>(observableSupplier);
        this.mCallback = callback;
        observableSupplier.addObserver(callbackWrapper);
    }

    /* loaded from: classes9.dex */
    private class CallbackWrapper implements Callback<E> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        private CallbackWrapper() {
        }

        @Override // com.ttnet.org.chromium.base.Callback
        public void onResult(E e2) {
            OneShotCallback.this.mCallback.onResult(e2);
            ((ObservableSupplier) OneShotCallback.this.mWeakSupplier.get()).removeObserver(OneShotCallback.this.mCallbackWrapper);
        }
    }
}
