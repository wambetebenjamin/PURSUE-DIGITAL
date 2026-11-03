package com.bytedance.ttgame.module.pay.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import com.bytedance.ttgame.main.internal.net.IRetrofit;
import com.bytedance.ttgame.rocketapi.minors.IMinorsCallback;
import com.bytedance.ttgame.rocketapi.pay.IPayCallback;
import com.bytedance.ttgame.rocketapi.pay.IQueryCountryCodeCallback;
import com.bytedance.ttgame.rocketapi.pay.IQueryProductsCallback;
import com.bytedance.ttgame.rocketapi.pay.IQueryPurchaseCallback;
import com.bytedance.ttgame.rocketapi.pay.IQueryRewardsCallback;
import com.bytedance.ttgame.rocketapi.pay.QueryGoodsParams;
import com.bytedance.ttgame.rocketapi.pay.QueryPurchaseReason;
import com.bytedance.ttgame.rocketapi.pay.RocketGoods;
import com.bytedance.ttgame.rocketapi.pay.RocketPayInfo;
import com.bytedance.ttgame.rocketapi.pay.RocketPayResult;
import com.bytedance.ttgame.rocketapi.pay.RocketPurchaseRecord;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public interface IPayService extends IModuleApi {
    public static final String TAG = "{PayService}";

    void compensate(String str, String str2, String str3);

    @InternalApi
    byte[] decodeAndDecrypt(String str);

    @InternalApi
    String encodeAndEncryptParameters(JSONObject jSONObject);

    void generalAction(JSONObject jSONObject);

    @InternalApi
    GSDKError getNetError(Context context, String str, Throwable th);

    HashMap<String, RocketPurchaseRecord> getNoConsumableMap();

    @InternalApi
    JSONObject getPayResult();

    @InternalApi
    IRetrofit getPayRetrofit();

    void getProductsList(IQueryProductsCallback iQueryProductsCallback);

    void getV2MinorLimit(String str, IMinorsCallback iMinorsCallback);

    @InternalApi
    void init(Context context, String str);

    @InternalApi
    void initOnHomeActivity(Context context);

    @InternalApi
    boolean isBlockedCloseWeb();

    boolean isBsdkPay();

    @InternalApi
    boolean isPaySuccessful();

    @InternalApi
    void onActivityResult(int i2, int i3, Intent intent);

    void pay(Activity activity, RocketPayInfo rocketPayInfo, IPayCallback<RocketPayResult> iPayCallback);

    void queryGoods(Context context, QueryGoodsParams queryGoodsParams, IPayCallback<RocketGoods> iPayCallback);

    void queryGooglePlayCountryCodeAsync(Context context, IQueryCountryCodeCallback iQueryCountryCodeCallback);

    void queryProducts(List<String> list, IQueryProductsCallback iQueryProductsCallback);

    void queryPurchaseByProductId(String str, QueryPurchaseReason queryPurchaseReason, IQueryPurchaseCallback iQueryPurchaseCallback);

    void receiveRewards(Context context, RocketPayInfo rocketPayInfo, IPayCallback<RocketPayResult> iPayCallback);

    void registerExtraPayCallback(IPayCallback<RocketPayResult> iPayCallback);

    void registerGeneralCallback(ICallback<JSONObject> iCallback);

    void registerRewardsOutside(IQueryRewardsCallback iQueryRewardsCallback);

    void restoreSubscription(String str, String str2, String str3, String str4);

    void setV2MinorLimit(String str, String str2, IMinorsCallback iMinorsCallback);
}
