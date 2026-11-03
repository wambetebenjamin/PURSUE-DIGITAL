package com.bytedance.pipo.service.manager.iap;

import com.bytedance.pipo.iap.common.ability.e;
import com.bytedance.pipo.iap.model.AbstractPurchaseHistory;
import java.util.List;
/* loaded from: classes2.dex */
public interface QueryPurchaseHistoryCallback {
    void onResponse(e eVar, List<AbstractPurchaseHistory> list);
}
