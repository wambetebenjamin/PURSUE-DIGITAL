package com.bytedance.ttgame.channel.pay;

import com.bytedance.pipo.payment.common.lib.c;
import com.bytedance.ttgame.module.pay.api.I18nPayInfo;
import com.bytedance.ttgame.rocketapi.pay.RocketPayInfo;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;
/* loaded from: classes3.dex */
public class PayInfoMapper {
    private static final String TAG = "{PayService}";

    public static I18nPayInfo transform(String str, RocketPayInfo rocketPayInfo) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            I18nPayInfo i18nPayInfo = new I18nPayInfo();
            i18nPayInfo.setOrderId(jSONObject.optString("order_id"));
            i18nPayInfo.setProductId(jSONObject.optString(c.am));
            i18nPayInfo.setGoodType(jSONObject.optInt("product_type"));
            i18nPayInfo.setOpenId(rocketPayInfo.getSdkOpenId());
            i18nPayInfo.setServerId(rocketPayInfo.getServerId());
            i18nPayInfo.setRoleId(rocketPayInfo.getRoleId());
            i18nPayInfo.setAmount((int) rocketPayInfo.getAmount());
            i18nPayInfo.setCurrency(rocketPayInfo.getCurrency());
            i18nPayInfo.setPurchaseId(rocketPayInfo.getPurchaseId());
            return i18nPayInfo;
        } catch (JSONException e2) {
            Timber.tag("{PayService}").w("convert string to jsonObject has error, error:" + e2.getMessage(), new Object[0]);
            return null;
        }
    }
}
