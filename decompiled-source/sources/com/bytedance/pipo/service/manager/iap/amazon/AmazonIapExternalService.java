package com.bytedance.pipo.service.manager.iap.amazon;

import android.app.Activity;
import com.bytedance.pipo.iap.common.ability.listener.b;
import com.bytedance.pipo.iap.common.ability.state.a;
import com.bytedance.pipo.iap.common.ability.support.service.c;
import com.bytedance.pipo.iap.model.AbsIapProduct;
import com.bytedance.pipo.payment.common.lib.callback.f;
import com.bytedance.pipo.service.manager.annotation.ExternalService;
import com.bytedance.pipo.service.manager.iap.google.ConsumeIapProductListener;
import java.util.List;
@ExternalService
/* loaded from: classes2.dex */
public interface AmazonIapExternalService {
    void consumeProduct(String str, ConsumeIapProductListener consumeIapProductListener);

    a getAmazonState(c cVar, Activity activity);

    void getAmazonUserId(com.bytedance.pipo.iap.listenter.a aVar);

    void init(b bVar);

    boolean isSupportAmazonPay();

    void queryProductDetails(List<String> list, boolean z, f<AbsIapProduct> fVar);

    void queryUnAckEdOrderFromChannel(com.bytedance.pipo.iap.listenter.b bVar);
}
