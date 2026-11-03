package com.bytedance.pipo.iap.common.ability.model.api.entity;

import com.bytedance.pipo.payment.common.lib.c;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: ValidateReceiptData.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001b\u0018\u00002\u00020\u0001Be\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000bR \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR \u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR \u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000fR \u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\r\"\u0004\b\u001d\u0010\u000f¨\u0006\u001e"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/model/api/entity/ValidateReceiptData;", "", "errorCode", "", "errorMessage", "resultCode", "pipoTradeOrderId", "pipoTradeStatus", "channelOrderId", "merchantUserId", "verifyStatus", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChannelOrderId", "()Ljava/lang/String;", "setChannelOrderId", "(Ljava/lang/String;)V", "getErrorCode", "setErrorCode", "getErrorMessage", "setErrorMessage", "getMerchantUserId", "setMerchantUserId", "getPipoTradeOrderId", "setPipoTradeOrderId", "getPipoTradeStatus", "setPipoTradeStatus", "getResultCode", "setResultCode", "getVerifyStatus", "setVerifyStatus", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ValidateReceiptData {
    @SerializedName(c.cG)
    private String channelOrderId;
    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("error_message")
    private String errorMessage;
    @SerializedName(c.H)
    private String merchantUserId;
    @SerializedName("pipo_trade_order_id")
    private String pipoTradeOrderId;
    @SerializedName("pipo_trade_status")
    private String pipoTradeStatus;
    @SerializedName("result_code")
    private String resultCode;
    @SerializedName("verify_status")
    private String verifyStatus;

    public ValidateReceiptData() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    public ValidateReceiptData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.errorCode = str;
        this.errorMessage = str2;
        this.resultCode = str3;
        this.pipoTradeOrderId = str4;
        this.pipoTradeStatus = str5;
        this.channelOrderId = str6;
        this.merchantUserId = str7;
        this.verifyStatus = str8;
    }

    public /* synthetic */ ValidateReceiptData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : str6, (i2 & 64) != 0 ? null : str7, (i2 & 128) == 0 ? str8 : null);
    }

    public final String getErrorCode() {
        return this.errorCode;
    }

    public final void setErrorCode(String str) {
        this.errorCode = str;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public final String getResultCode() {
        return this.resultCode;
    }

    public final void setResultCode(String str) {
        this.resultCode = str;
    }

    public final String getPipoTradeOrderId() {
        return this.pipoTradeOrderId;
    }

    public final void setPipoTradeOrderId(String str) {
        this.pipoTradeOrderId = str;
    }

    public final String getPipoTradeStatus() {
        return this.pipoTradeStatus;
    }

    public final void setPipoTradeStatus(String str) {
        this.pipoTradeStatus = str;
    }

    public final String getChannelOrderId() {
        return this.channelOrderId;
    }

    public final void setChannelOrderId(String str) {
        this.channelOrderId = str;
    }

    public final String getMerchantUserId() {
        return this.merchantUserId;
    }

    public final void setMerchantUserId(String str) {
        this.merchantUserId = str;
    }

    public final String getVerifyStatus() {
        return this.verifyStatus;
    }

    public final void setVerifyStatus(String str) {
        this.verifyStatus = str;
    }
}
