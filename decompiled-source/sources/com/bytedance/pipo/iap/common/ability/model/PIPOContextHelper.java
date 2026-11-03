package com.bytedance.pipo.iap.common.ability.model;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class PIPOContextHelper {

    /* loaded from: classes2.dex */
    public static class PIPOContext {
        public static final String TradeType_One_Off = "ONE_OFF";
        public static final String TradeType_Subscription = "AUTO_RENEW";
        public String ChannelSkuId;
        public String CountryOrRegion;
        public String GP_changeType;
        public String GP_obfuscatedAccountId;
        public String GP_obfuscatedProfileId;
        public String GP_oldSubSubscribeToken;
        public String GpSkuTokenInfo = "";
        public String MerchantId;
        public String MerchantUserId;
        public String PipoTradeOrderId;
        public String TradeProduct;
        public String requestHost;
        public String requestSign;
        public String traceId;
    }

    public static PIPOContext parse(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            PIPOContext pIPOContext = new PIPOContext();
            pIPOContext.ChannelSkuId = jSONObject.optString("ChannelSkuId");
            pIPOContext.MerchantId = jSONObject.optString("MerchantId");
            pIPOContext.MerchantUserId = jSONObject.optString("MerchantUserId");
            pIPOContext.PipoTradeOrderId = jSONObject.optString("PipoTradeOrderId");
            pIPOContext.TradeProduct = jSONObject.optString("TradeProduct");
            pIPOContext.requestHost = jSONObject.optString("requestHost");
            pIPOContext.requestSign = jSONObject.optString("requestSign");
            pIPOContext.GP_changeType = jSONObject.optString("GP_changeType", null);
            pIPOContext.GP_oldSubSubscribeToken = jSONObject.optString("GP_oldSubSubscribeToken");
            pIPOContext.GP_obfuscatedAccountId = jSONObject.optString("GP_obfuscatedAccountId");
            pIPOContext.GP_obfuscatedProfileId = jSONObject.optString("GP_obfuscatedProfileId");
            pIPOContext.CountryOrRegion = jSONObject.optString("CountryOrRegion");
            pIPOContext.GpSkuTokenInfo = jSONObject.optString("GP_skuTokenInfo");
            pIPOContext.traceId = jSONObject.optString("trace_id");
            return pIPOContext;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String toJson(PIPOContext pIPOContext) {
        if (pIPOContext == null) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ChannelSkuId", pIPOContext.ChannelSkuId);
            jSONObject.put("MerchantId", pIPOContext.MerchantId);
            jSONObject.put("MerchantUserId", pIPOContext.MerchantUserId);
            jSONObject.put("PipoTradeOrderId", pIPOContext.PipoTradeOrderId);
            jSONObject.put("TradeProduct", pIPOContext.TradeProduct);
            jSONObject.put("requestHost", pIPOContext.requestHost);
            jSONObject.put("requestSign", pIPOContext.requestSign);
            jSONObject.put("GP_changeType", pIPOContext.GP_changeType);
            jSONObject.put("GP_oldSubSubscribeToken", pIPOContext.GP_oldSubSubscribeToken);
            jSONObject.put("GP_obfuscatedAccountId", pIPOContext.GP_obfuscatedAccountId);
            jSONObject.put("GP_obfuscatedProfileId", pIPOContext.GP_obfuscatedProfileId);
            jSONObject.put("CountryOrRegion", pIPOContext.CountryOrRegion);
            jSONObject.put("GP_skuTokenInfo", pIPOContext.GpSkuTokenInfo);
            jSONObject.put("trace_id", pIPOContext.traceId);
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static Map<String, String> toMap(String str) {
        HashMap hashMap = new HashMap();
        if (str == null) {
            return hashMap;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String optString = jSONObject.optString(next);
                if (!TextUtils.isEmpty(optString)) {
                    hashMap.put(next, optString);
                }
            }
            return hashMap;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return hashMap;
        }
    }
}
