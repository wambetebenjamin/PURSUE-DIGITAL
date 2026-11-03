package com.bytedance.pipo.payment.common.lib.callback;

import com.bytedance.pipo.iap.model.AbsResult;
import com.bytedance.pipo.iap.model.IapPaymentMethod;
/* compiled from: InitCallback.java */
/* loaded from: classes2.dex */
public interface c {
    default void a(IapPaymentMethod iapPaymentMethod) {
    }

    default void a(IapPaymentMethod iapPaymentMethod, AbsResult absResult) {
    }
}
