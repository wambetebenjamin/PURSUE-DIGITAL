package com.bytedance.ttgame.module.rn.utils;

import android.app.Activity;
import com.bytedance.ttgame.channel.account.ErrorConstants;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.module.pay.api.IPayService;
import com.bytedance.ttgame.module.rn.modules.RNSafeMapUtils;
import com.bytedance.ttgame.rocketapi.pay.IPayCallback;
import com.bytedance.ttgame.rocketapi.pay.RocketPayInfo;
import com.bytedance.ttgame.rocketapi.pay.RocketPayResult;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import java.util.HashMap;
/* loaded from: classes6.dex */
public class PayUtils {
    private static IPayService getPayService() {
        return (IPayService) ModuleManager.INSTANCE.getService(IPayService.class);
    }

    public static void pay(Activity activity, RocketPayInfo rocketPayInfo, IPayCallback<RocketPayResult> iPayCallback) {
        if (getPayService() != null) {
            getPayService().pay(activity, rocketPayInfo, iPayCallback);
        }
    }

    public static boolean canPay() {
        return getPayService() != null;
    }

    public static RocketPayInfo convertToPayInfo(HashMap hashMap) {
        if (hashMap == null) {
            return null;
        }
        RocketPayInfo rocketPayInfo = new RocketPayInfo();
        rocketPayInfo.setSdkOpenId(RNSafeMapUtils.getString(hashMap, "sdkOpenId"));
        rocketPayInfo.setProductId(RNSafeMapUtils.getString(hashMap, "productId"));
        rocketPayInfo.setServerId(RNSafeMapUtils.getString(hashMap, "serverId"));
        rocketPayInfo.setRoleId(RNSafeMapUtils.getString(hashMap, "roleId"));
        rocketPayInfo.setCurrency(RNSafeMapUtils.getString(hashMap, "currency"));
        rocketPayInfo.setRoleName(RNSafeMapUtils.getString(hashMap, "roleName"));
        rocketPayInfo.setRoleLevel(RNSafeMapUtils.getString(hashMap, "roleLevel"));
        rocketPayInfo.setRoleVipLevel((int) RNSafeMapUtils.getDouble(hashMap, "roleVipLevel"));
        rocketPayInfo.setExtraInfo(RNSafeMapUtils.getString(hashMap, "extraInfo"));
        rocketPayInfo.setActivity(RNSafeMapUtils.getString(hashMap, "activity"));
        rocketPayInfo.setGoodType((int) RNSafeMapUtils.getDouble(hashMap, "goodsType"));
        return rocketPayInfo;
    }

    public static HashMap convertResult(RocketPayResult rocketPayResult) {
        HashMap hashMap = new HashMap();
        WritableMap createMap = Arguments.createMap();
        if (rocketPayResult != null) {
            hashMap.put("sdkCode", Integer.valueOf(rocketPayResult.gsdkError.getExtraErrorCode()));
            hashMap.put("sdkMessage", rocketPayResult.gsdkError.getExtraErrorMessage());
            hashMap.put("gameOrderId", rocketPayResult.getGameOrderId());
            hashMap.put("gameProductId", rocketPayResult.getGameProductId());
            if (rocketPayResult.gsdkError != null) {
                createMap.putInt("code", rocketPayResult.gsdkError.getCode());
                createMap.putString("message", rocketPayResult.gsdkError.getMessage());
                createMap.putInt(ErrorConstants.EXTRA_ERROR_CODE, rocketPayResult.gsdkError.getExtraErrorCode());
                createMap.putString(ErrorConstants.EXTRA_ERROR_MESSAGE, rocketPayResult.gsdkError.getExtraErrorMessage());
                createMap.putString(ErrorConstants.ADDITIONAL_INFO, rocketPayResult.gsdkError.getAdditionalInfo());
            } else {
                createMap.putInt("code", -999999);
                createMap.putString("message", "支付返回结果为空");
            }
        } else {
            hashMap.put("sdkCode", -999999);
            createMap.putInt("code", -999999);
            createMap.putString("message", "支付返回结果为空");
        }
        hashMap.put("payResult", createMap);
        return hashMap;
    }
}
