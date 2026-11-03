package com.bytedance.ttgame.rocketapi.pay;

import com.bytedance.ttgame.base.GSDKError;
import java.util.List;
/* loaded from: classes7.dex */
public interface IQueryProductsCallback {
    void onFailed(GSDKError gSDKError);

    void onSuccess(List<RocketProductInfo> list);
}
