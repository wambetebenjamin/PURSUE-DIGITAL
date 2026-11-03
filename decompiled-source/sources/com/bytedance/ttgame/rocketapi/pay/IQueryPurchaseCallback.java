package com.bytedance.ttgame.rocketapi.pay;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public interface IQueryPurchaseCallback {
    void onFailed(GSDKError gSDKError);

    void onSuccess(RocketPurchaseRecord rocketPurchaseRecord);
}
