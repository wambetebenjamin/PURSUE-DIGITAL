package com.ttnet.org.chromium.base.supplier;

import com.ttnet.org.chromium.base.Callback;
/* loaded from: classes9.dex */
public interface OneshotSupplier<T> extends Supplier<T> {
    T onAvailable(Callback<T> callback);
}
