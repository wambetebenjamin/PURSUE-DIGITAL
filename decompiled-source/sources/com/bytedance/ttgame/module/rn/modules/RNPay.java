package com.bytedance.ttgame.module.rn.modules;

import android.app.Activity;
import com.bytedance.react.framework.modules.BridgeLogUtil;
import com.bytedance.sdk.account.platform.base.i;
import com.bytedance.ttgame.module.rn.utils.PayUtils;
import com.bytedance.ttgame.rocketapi.pay.IPayCallback;
import com.bytedance.ttgame.rocketapi.pay.RocketPayInfo;
import com.bytedance.ttgame.rocketapi.pay.RocketPayResult;
import java.util.HashMap;
/* loaded from: classes6.dex */
public class RNPay implements IPayInterface {
    private String MODULE_NAME;

    @Override // com.bytedance.ttgame.module.rn.modules.IPayInterface
    public void pay(final Activity activity, final HashMap hashMap, final IRNCallback iRNCallback) {
        if (activity == null) {
            iRNCallback.onError(i.a.ERROR_CODE_NOT_SUPPORT, "activity is null");
            BridgeLogUtil.reportError(getName() + "-pay", "", "activity is null");
        } else if (!PayUtils.canPay()) {
            iRNCallback.onError(i.a.ERROR_CODE_UNKNOW, "pay service cannot use");
            BridgeLogUtil.reportError(getName() + "-pay", "", "pay service cannot use");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: com.bytedance.ttgame.module.rn.modules.RNPay.1
                @Override // java.lang.Runnable
                public void run() {
                    RocketPayInfo convertToPayInfo = PayUtils.convertToPayInfo(hashMap);
                    if (convertToPayInfo == null) {
                        iRNCallback.onError(i.a.ERROR_CODE_NOT_SUPPORT, "params is null");
                        BridgeLogUtil.reportError(RNPay.this.getName() + "-pay", "", "params is null");
                        return;
                    }
                    BridgeLogUtil.reportBegin(RNPay.this.getName() + "-pay", "" + hashMap.toString());
                    PayUtils.pay(activity, convertToPayInfo, new IPayCallback<RocketPayResult>() { // from class: com.bytedance.ttgame.module.rn.modules.RNPay.1.1
                        @Override // com.bytedance.ttgame.rocketapi.pay.IPayCallback
                        public void onSuccess(RocketPayResult rocketPayResult) {
                            BridgeLogUtil.reportEnd(RNPay.this.getName() + "-pay", "" + hashMap.toString());
                            iRNCallback.onSuccess(PayUtils.convertResult(rocketPayResult));
                        }

                        @Override // com.bytedance.ttgame.rocketapi.pay.IPayCallback
                        public void onFailed(RocketPayResult rocketPayResult) {
                            if (rocketPayResult != null) {
                                BridgeLogUtil.reportError(RNPay.this.getName() + "-pay", "" + hashMap.toString(), rocketPayResult.toString());
                            } else {
                                BridgeLogUtil.reportError(RNPay.this.getName() + "-pay", "" + hashMap.toString(), "error is null");
                            }
                            iRNCallback.onSuccess(PayUtils.convertResult(rocketPayResult));
                        }
                    });
                }
            });
        }
    }

    @Override // com.bytedance.ttgame.module.rn.modules.IPayInterface
    public void setName(String str) {
        this.MODULE_NAME = str;
    }

    public String getName() {
        return this.MODULE_NAME;
    }
}
