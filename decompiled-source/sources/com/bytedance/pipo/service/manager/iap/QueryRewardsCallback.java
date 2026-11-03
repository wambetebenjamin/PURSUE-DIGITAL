package com.bytedance.pipo.service.manager.iap;

import com.bytedance.pipo.iap.common.ability.e;
import com.bytedance.pipo.iap.model.AbsIapProduct;
import java.util.List;
/* loaded from: classes2.dex */
public interface QueryRewardsCallback {
    void onResponse(e eVar, List<AbsIapProduct> list);
}
