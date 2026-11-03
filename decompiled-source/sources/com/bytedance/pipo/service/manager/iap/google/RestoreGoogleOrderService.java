package com.bytedance.pipo.service.manager.iap.google;

import com.bytedance.pipo.iap.common.ability.model.enums.PayType;
/* loaded from: classes2.dex */
public interface RestoreGoogleOrderService {
    void onFailedFinishedValidateReceipt(PayType payType, String str);

    void onSuccessFinishedValidateReceipt(String str);

    void startValidateIfNeeded();
}
