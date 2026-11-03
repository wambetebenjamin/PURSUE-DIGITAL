package com.bytedance.ttgame.module.account.toutiao.account.api;

import android.app.Activity;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.module.pay.api.OrderResponse;
import com.bytedance.ttgame.rocketapi.pay.RocketPayInfo;
/* loaded from: classes4.dex */
public interface ITTCloudGamePayService extends IModuleApi {
    void cloudGamePay(Activity activity, OrderResponse orderResponse, RocketPayInfo rocketPayInfo, ITTCloudGamePayListener iTTCloudGamePayListener);

    boolean isCloudRuntime();
}
