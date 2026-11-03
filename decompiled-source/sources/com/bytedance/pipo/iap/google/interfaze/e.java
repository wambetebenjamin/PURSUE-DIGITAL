package com.bytedance.pipo.iap.google.interfaze;

import com.bytedance.pipo.iap.common.ability.model.OrderData;
/* compiled from: IRestoreOrderService.java */
/* loaded from: classes2.dex */
public interface e {
    void onGoogleCallback(OrderData orderData);

    void onGooglePanelDismiss(OrderData orderData);
}
