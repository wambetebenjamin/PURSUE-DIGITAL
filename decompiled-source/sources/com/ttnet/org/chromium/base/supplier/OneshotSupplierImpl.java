package com.ttnet.org.chromium.base.supplier;

import com.ttnet.org.chromium.base.Callback;
import com.ttnet.org.chromium.base.Promise;
import com.ttnet.org.chromium.base.ThreadUtils;
/* loaded from: classes9.dex */
public class OneshotSupplierImpl<T> implements OneshotSupplier<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Promise<T> mPromise = new Promise<>();
    private final ThreadUtils.ThreadChecker mThreadChecker = new ThreadUtils.ThreadChecker();

    @Override // com.ttnet.org.chromium.base.supplier.OneshotSupplier
    public T onAvailable(Callback<T> callback) {
        this.mThreadChecker.assertOnValidThread();
        this.mPromise.then(callback);
        return get();
    }

    @Override // com.ttnet.org.chromium.base.supplier.Supplier
    public T get() {
        this.mThreadChecker.assertOnValidThread();
        if (this.mPromise.isFulfilled()) {
            return this.mPromise.getResult();
        }
        return null;
    }

    public void set(T t) {
        this.mThreadChecker.assertOnValidThread();
        this.mPromise.fulfill(t);
    }
}
