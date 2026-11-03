package com.bytedance.pipo.service.manager.iap;

import com.bytedance.pipo.iap.common.ability.e;
import com.bytedance.pipo.iap.model.AbsIapChannelOrderData;
import java.util.List;
/* loaded from: classes2.dex */
public interface QueryPurchasesCallback {
    void onResponse(e eVar, List<AbsIapChannelOrderData> list);
}
