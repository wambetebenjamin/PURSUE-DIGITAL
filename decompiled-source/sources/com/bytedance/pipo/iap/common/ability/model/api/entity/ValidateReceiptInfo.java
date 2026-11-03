package com.bytedance.pipo.iap.common.ability.model.api.entity;

import com.bytedance.pipo.payment.common.lib.c;
import com.google.gson.annotations.SerializedName;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: ValidateReceiptRequest.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b*\u0018\u00002\u00020\u0001B»\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0011R \u0010\f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R \u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015R \u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R \u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015R \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0013\"\u0004\b#\u0010\u0015R.\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R \u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0013\"\u0004\b)\u0010\u0015R,\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010%\"\u0004\b+\u0010'R \u0010\r\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0013\"\u0004\b-\u0010\u0015R \u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0013\"\u0004\b/\u0010\u0015¨\u00060"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/model/api/entity/ValidateReceiptInfo;", "", "merchantId", "", "merchantUserId", "pipoContext", "", "pipoTradeOrderId", "originalChannelOrderId", "channelOrderId", "channelSkuId", "channelToken", "channelAppId", "tradePlatform", "riskInfo", "deviceId", "verifyScene", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)V", "getChannelAppId", "()Ljava/lang/String;", "setChannelAppId", "(Ljava/lang/String;)V", "getChannelOrderId", "setChannelOrderId", "getChannelSkuId", "setChannelSkuId", "getChannelToken", "setChannelToken", "getDeviceId", "setDeviceId", "getMerchantId", "setMerchantId", "getMerchantUserId", "setMerchantUserId", "getOriginalChannelOrderId", "setOriginalChannelOrderId", "getPipoContext", "()Ljava/util/Map;", "setPipoContext", "(Ljava/util/Map;)V", "getPipoTradeOrderId", "setPipoTradeOrderId", "getRiskInfo", "setRiskInfo", "getTradePlatform", "setTradePlatform", "getVerifyScene", "setVerifyScene", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ValidateReceiptInfo {
    @SerializedName("channel_app_id")
    private String channelAppId;
    @SerializedName(c.cG)
    private String channelOrderId;
    @SerializedName(c.M)
    private String channelSkuId;
    @SerializedName("channel_token")
    private String channelToken;
    @SerializedName("device_id")
    private String deviceId;
    @SerializedName("merchant_id")
    private String merchantId;
    @SerializedName(c.H)
    private String merchantUserId;
    @SerializedName("original_channel_order_id")
    private String originalChannelOrderId;
    @SerializedName("pipo_context")
    private Map<String, String> pipoContext;
    @SerializedName("pipo_trade_order_id")
    private String pipoTradeOrderId;
    @SerializedName("risk_info")
    private Map<String, String> riskInfo;
    @SerializedName(c.I)
    private String tradePlatform;
    @SerializedName("verify_scene")
    private String verifyScene;

    public ValidateReceiptInfo() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
    }

    public ValidateReceiptInfo(String str, String str2, Map<String, String> map, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Map<String, String> map2, String str10, String str11) {
        this.merchantId = str;
        this.merchantUserId = str2;
        this.pipoContext = map;
        this.pipoTradeOrderId = str3;
        this.originalChannelOrderId = str4;
        this.channelOrderId = str5;
        this.channelSkuId = str6;
        this.channelToken = str7;
        this.channelAppId = str8;
        this.tradePlatform = str9;
        this.riskInfo = map2;
        this.deviceId = str10;
        this.verifyScene = str11;
    }

    public /* synthetic */ ValidateReceiptInfo(String str, String str2, Map map, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Map map2, String str10, String str11, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : map, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : str5, (i2 & 64) != 0 ? null : str6, (i2 & 128) != 0 ? null : str7, (i2 & 256) != 0 ? null : str8, (i2 & 512) != 0 ? null : str9, (i2 & 1024) != 0 ? null : map2, (i2 & 2048) != 0 ? null : str10, (i2 & 4096) == 0 ? str11 : null);
    }

    public final String getMerchantId() {
        return this.merchantId;
    }

    public final void setMerchantId(String str) {
        this.merchantId = str;
    }

    public final String getMerchantUserId() {
        return this.merchantUserId;
    }

    public final void setMerchantUserId(String str) {
        this.merchantUserId = str;
    }

    public final Map<String, String> getPipoContext() {
        return this.pipoContext;
    }

    public final void setPipoContext(Map<String, String> map) {
        this.pipoContext = map;
    }

    public final String getPipoTradeOrderId() {
        return this.pipoTradeOrderId;
    }

    public final void setPipoTradeOrderId(String str) {
        this.pipoTradeOrderId = str;
    }

    public final String getOriginalChannelOrderId() {
        return this.originalChannelOrderId;
    }

    public final void setOriginalChannelOrderId(String str) {
        this.originalChannelOrderId = str;
    }

    public final String getChannelOrderId() {
        return this.channelOrderId;
    }

    public final void setChannelOrderId(String str) {
        this.channelOrderId = str;
    }

    public final String getChannelSkuId() {
        return this.channelSkuId;
    }

    public final void setChannelSkuId(String str) {
        this.channelSkuId = str;
    }

    public final String getChannelToken() {
        return this.channelToken;
    }

    public final void setChannelToken(String str) {
        this.channelToken = str;
    }

    public final String getChannelAppId() {
        return this.channelAppId;
    }

    public final void setChannelAppId(String str) {
        this.channelAppId = str;
    }

    public final String getTradePlatform() {
        return this.tradePlatform;
    }

    public final void setTradePlatform(String str) {
        this.tradePlatform = str;
    }

    public final Map<String, String> getRiskInfo() {
        return this.riskInfo;
    }

    public final void setRiskInfo(Map<String, String> map) {
        this.riskInfo = map;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final void setDeviceId(String str) {
        this.deviceId = str;
    }

    public final String getVerifyScene() {
        return this.verifyScene;
    }

    public final void setVerifyScene(String str) {
        this.verifyScene = str;
    }
}
