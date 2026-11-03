package com.bytedance.ttgame.channelapi;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import com.bytedance.ttgame.rocketapi.minors.IMinorsCallback;
import com.bytedance.ttgame.rocketapi.pay.IPayCallback;
import com.bytedance.ttgame.rocketapi.pay.QueryGoodsParams;
import com.bytedance.ttgame.rocketapi.pay.RocketGoods;
import com.bytedance.ttgame.rocketapi.pay.RocketPayInfo;
import com.bytedance.ttgame.rocketapi.pay.RocketPayResult;
import com.bytedance.ttgame.rocketapi.pay.RocketProductInfo;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public interface IChannelPay {
    public static final int ACTION_SAMSUNG_SET_IAP_MODE = 201;
    public static final int PAY_ERR_CODE_CANCEL = 206;
    public static final int PAY_ERR_CODE_CREATE_ORDER_SUCC = 102;
    public static final int PAY_ERR_CODE_SHARK = 101;
    public static final int PAY_ERR_CODE_START_PAY = 103;

    void adaptGoodsForCurrency(RocketGoods rocketGoods, IChannelCallback<RocketGoods> iChannelCallback);

    default void compensate(String str, String str2, String str3) {
    }

    default void generalAction(JSONObject jSONObject) {
    }

    default String getChannelGoodsDetailPath() {
        return "";
    }

    default String getExtraParams() {
        return "";
    }

    default Application.ActivityLifecycleCallbacks getLifecycleCallback() {
        return null;
    }

    default void getProductsList(IChannelCallback<List<RocketProductInfo>> iChannelCallback) {
    }

    default void getV2MinorLimit(String str, IMinorsCallback iMinorsCallback) {
    }

    void init(Context context, String str, IChannelCallback<String> iChannelCallback);

    boolean isBsdkPay();

    default boolean isChannelDetailPathPay() {
        return false;
    }

    default void launchChannelPay(Activity activity, RocketPayInfo rocketPayInfo, String str, IChannelCallback<ChannelPayResult> iChannelCallback) {
    }

    default void onActivityResult(int i2, int i3, Intent intent) {
    }

    default void pay(Activity activity, RocketPayInfo rocketPayInfo, String str, IPayCallback<RocketPayResult> iPayCallback) {
    }

    void payOnSuccess(Activity activity, String str, RocketPayInfo rocketPayInfo, IChannelCallback<ChannelPayResult> iChannelCallback);

    default void queryGooglePlayCountryCodeAsync(Context context, IChannelCallback<IQueryCountryCodeResult> iChannelCallback) {
    }

    void queryProductDetails(List<String> list, IChannelCallback<Map<String, Object>> iChannelCallback);

    default void queryProducts(Context context, QueryGoodsParams queryGoodsParams, IPayCallback<RocketGoods> iPayCallback) {
    }

    default void queryPurchaseByProductId(String str, int i2, IQueryPurchaseOrderCallback iQueryPurchaseOrderCallback) {
    }

    void registerExtraPayCallback(IChannelCallback<Map<String, Object>> iChannelCallback);

    default void registerRewardsOutside(IChannelCallback<List<String>> iChannelCallback) {
    }

    default void setMinorLimit(String str, String str2, IMinorsCallback iMinorsCallback) {
    }

    default void registerGeneralCallback(IChannelCallback<JSONObject> iChannelCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", Integer.MIN_VALUE);
            jSONObject.put("payload", "just test");
            iChannelCallback.onResult(0, jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
