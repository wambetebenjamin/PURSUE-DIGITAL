package com.bytedance.pipo.service.manager.iap;

import com.bytedance.pipo.iap.common.ability.e;
import com.bytedance.pipo.iap.model.AbsIapProduct;
import java.util.Map;
/* loaded from: classes2.dex */
public interface QueryAbsIapProductCallback {
    void onResponse(e eVar, AbsIapProduct absIapProduct, Map<String, String> map, Boolean bool);
}
