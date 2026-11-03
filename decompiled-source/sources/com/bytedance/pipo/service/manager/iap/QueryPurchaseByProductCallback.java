package com.bytedance.pipo.service.manager.iap;

import com.bytedance.pipo.iap.common.ability.e;
import com.bytedance.pipo.iap.model.AbsIapChannelOrderData;
/* loaded from: classes2.dex */
public interface QueryPurchaseByProductCallback {
    void onResponse(e eVar, AbsIapChannelOrderData absIapChannelOrderData);
}
