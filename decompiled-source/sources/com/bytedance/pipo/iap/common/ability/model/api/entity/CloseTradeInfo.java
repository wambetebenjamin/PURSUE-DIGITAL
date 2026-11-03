package com.bytedance.pipo.iap.common.ability.model.api.entity;

import com.bytedance.pipo.payment.common.lib.c;
import com.google.gson.annotations.SerializedName;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: CloseTradeRequest.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0012\u0018\u00002\u00020\u0001BM\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\u0002\u0010\nR \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R,\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/model/api/entity/CloseTradeInfo;", "", "merchantId", "", "merchantUserId", "pipoTradeOrderId", "closeReason", "Lcom/bytedance/pipo/iap/common/ability/model/api/entity/CloseReason;", "riskInfo", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/bytedance/pipo/iap/common/ability/model/api/entity/CloseReason;Ljava/util/Map;)V", "getCloseReason", "()Lcom/bytedance/pipo/iap/common/ability/model/api/entity/CloseReason;", "setCloseReason", "(Lcom/bytedance/pipo/iap/common/ability/model/api/entity/CloseReason;)V", "getMerchantId", "()Ljava/lang/String;", "setMerchantId", "(Ljava/lang/String;)V", "getMerchantUserId", "setMerchantUserId", "getPipoTradeOrderId", "setPipoTradeOrderId", "getRiskInfo", "()Ljava/util/Map;", "setRiskInfo", "(Ljava/util/Map;)V", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CloseTradeInfo {
    @SerializedName("close_reason")
    private CloseReason closeReason;
    @SerializedName("merchant_id")
    private String merchantId;
    @SerializedName(c.H)
    private String merchantUserId;
    @SerializedName("pipo_trade_order_id")
    private String pipoTradeOrderId;
    @SerializedName("risk_info")
    private Map<String, String> riskInfo;

    public CloseTradeInfo() {
        this(null, null, null, null, null, 31, null);
    }

    public CloseTradeInfo(String str, String str2, String str3, CloseReason closeReason, Map<String, String> map) {
        this.merchantId = str;
        this.merchantUserId = str2;
        this.pipoTradeOrderId = str3;
        this.closeReason = closeReason;
        this.riskInfo = map;
    }

    public /* synthetic */ CloseTradeInfo(String str, String str2, String str3, CloseReason closeReason, Map map, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : closeReason, (i2 & 16) != 0 ? null : map);
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

    public final String getPipoTradeOrderId() {
        return this.pipoTradeOrderId;
    }

    public final void setPipoTradeOrderId(String str) {
        this.pipoTradeOrderId = str;
    }

    public final CloseReason getCloseReason() {
        return this.closeReason;
    }

    public final void setCloseReason(CloseReason closeReason) {
        this.closeReason = closeReason;
    }

    public final Map<String, String> getRiskInfo() {
        return this.riskInfo;
    }

    public final void setRiskInfo(Map<String, String> map) {
        this.riskInfo = map;
    }
}
