package com.bytedance.pipo.iap.common.ability.model.api.entity;

import com.bytedance.pipo.payment.common.lib.c;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CloseTradeData.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001BY\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\u0006\u0010\u001b\u001a\u00020\u001cR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR \u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR \u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000e¨\u0006\u001d"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/model/api/entity/CloseTradeData;", "", "errorCode", "", "errorMessage", "resultCode", "merchantId", "merchantUserId", "merchantOrderId", "pipoTradeOrderId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getErrorCode", "()Ljava/lang/String;", "setErrorCode", "(Ljava/lang/String;)V", "getErrorMessage", "setErrorMessage", "getMerchantId", "setMerchantId", "getMerchantOrderId", "setMerchantOrderId", "getMerchantUserId", "setMerchantUserId", "getPipoTradeOrderId", "setPipoTradeOrderId", "getResultCode", "setResultCode", "isSuccess", "", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CloseTradeData {
    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("error_message")
    private String errorMessage;
    @SerializedName("merchant_id")
    private String merchantId;
    @SerializedName("merchant_order_id")
    private String merchantOrderId;
    @SerializedName(c.H)
    private String merchantUserId;
    @SerializedName("pipo_trade_order_id")
    private String pipoTradeOrderId;
    @SerializedName("result_code")
    private String resultCode;

    public CloseTradeData() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public CloseTradeData(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.errorCode = str;
        this.errorMessage = str2;
        this.resultCode = str3;
        this.merchantId = str4;
        this.merchantUserId = str5;
        this.merchantOrderId = str6;
        this.pipoTradeOrderId = str7;
    }

    public /* synthetic */ CloseTradeData(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : str6, (i2 & 64) != 0 ? null : str7);
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

    public final String getMerchantOrderId() {
        return this.merchantOrderId;
    }

    public final void setMerchantOrderId(String str) {
        this.merchantOrderId = str;
    }

    public final String getPipoTradeOrderId() {
        return this.pipoTradeOrderId;
    }

    public final void setPipoTradeOrderId(String str) {
        this.pipoTradeOrderId = str;
    }

    public final boolean isSuccess() {
        return Intrinsics.areEqual(this.errorCode, "000000");
    }
}
