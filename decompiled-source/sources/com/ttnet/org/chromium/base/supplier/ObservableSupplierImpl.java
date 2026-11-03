package com.ttnet.org.chromium.base.supplier;

import android.os.Handler;
import com.ttnet.org.chromium.base.Callback;
import com.ttnet.org.chromium.base.ObserverList;
import java.util.Iterator;
/* loaded from: classes9.dex */
public class ObservableSupplierImpl<E> implements ObservableSupplier<E> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static boolean sIgnoreThreadChecksForTesting;
    private E mObject;
    private final Thread mThread = Thread.currentThread();
    private final Handler mHandler = new Handler();
    private final ObserverList<Callback<E>> mObservers = new ObserverList<>();

    private void checkThread() {
    }

    @Override // com.ttnet.org.chromium.base.supplier.ObservableSupplier
    public E addObserver(final Callback<E> callback) {
        checkThread();
        this.mObservers.addObserver(callback);
        final E e2 = this.mObject;
        if (e2 != null) {
            this.mHandler.post(new Runnable() { // from class: com.ttnet.org.chromium.base.supplier.ObservableSupplierImpl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ObservableSupplierImpl.this.m714xbb3c5488(e2, callback);
                }
            });
        }
        return this.mObject;
    }

    /* renamed from: lambda$addObserver$0$com-ttnet-org-chromium-base-supplier-ObservableSupplierImpl  reason: not valid java name */
    public /* synthetic */ void m714xbb3c5488(Object obj, Callback callback) {
        if (this.mObject == obj && this.mObservers.hasObserver(callback)) {
            callback.onResult(this.mObject);
        }
    }

    @Override // com.ttnet.org.chromium.base.supplier.ObservableSupplier
    public void removeObserver(Callback<E> callback) {
        checkThread();
        this.mObservers.removeObserver(callback);
    }

    public void set(E e2) {
        checkThread();
        if (e2 == this.mObject) {
            return;
        }
        this.mObject = e2;
        Iterator<Callback<E>> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().onResult(this.mObject);
        }
    }

    @Override // com.ttnet.org.chromium.base.supplier.Supplier
    public E get() {
        checkThread();
        return this.mObject;
    }

    public static void setIgnoreThreadChecksForTesting(boolean z) {
        sIgnoreThreadChecksForTesting = z;
    }
}
