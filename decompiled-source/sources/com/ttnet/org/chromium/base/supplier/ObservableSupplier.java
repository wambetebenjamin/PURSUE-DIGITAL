package com.ttnet.org.chromium.base.supplier;

import com.ttnet.org.chromium.base.Callback;
/* loaded from: classes9.dex */
public interface ObservableSupplier<E> extends Supplier<E> {
    E addObserver(Callback<E> callback);

    void removeObserver(Callback<E> callback);
}
