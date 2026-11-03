package com.bytedance.ttgame.module.pay.api;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes6.dex */
public class PayConfig {
    @SerializedName("https_base_url")
    private String HttpsBaseUrl;
    @SerializedName("https_sandbox_url")
    private String HttpsSandBoxUrl;
    @SerializedName("merchant_id")
    private String MerchantId;
    @SerializedName("iap_key")
    private String iapKey;

    public String getIapKey() {
        return this.iapKey;
    }

    public String getMerchantId() {
        return this.MerchantId;
    }

    public void setMerchantId(String str) {
        this.MerchantId = str;
    }

    public void setIapKey(String str) {
        this.iapKey = str;
    }

    public String getHttpsSandBoxUrl() {
        return this.HttpsSandBoxUrl;
    }

    public void setHttpsSandBoxUrl(String str) {
        this.HttpsSandBoxUrl = str;
    }

    public String getHttpsBaseUrl() {
        return this.HttpsBaseUrl;
    }

    public void setHttpsBaseUrl(String str) {
        this.HttpsBaseUrl = str;
    }
}
