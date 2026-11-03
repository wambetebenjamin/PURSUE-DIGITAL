package com.bytedance.ttgame.rocketapi.pay;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AccumulAtion.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ>\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\b\u0010\"\u001a\u00020\u0006H\u0016R\"\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\u0012\u0010\n\"\u0004\b\u0013\u0010\fR\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\u0014\u0010\n\"\u0004\b\u0015\u0010\f¨\u0006#"}, d2 = {"Lcom/bytedance/ttgame/rocketapi/pay/Detail;", "Ljava/io/Serializable;", "levelConfig", "", "coinNumber", "itemId", "", "itemNumber", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;)V", "getCoinNumber", "()Ljava/lang/Long;", "setCoinNumber", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getItemId", "()Ljava/lang/String;", "setItemId", "(Ljava/lang/String;)V", "getItemNumber", "setItemNumber", "getLevelConfig", "setLevelConfig", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;)Lcom/bytedance/ttgame/rocketapi/pay/Detail;", "equals", "", "other", "", "hashCode", "", "toString", "pay_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class Detail implements Serializable {
    @SerializedName("coin_number")
    private Long coinNumber;
    @SerializedName(FirebaseAnalytics.Param.ITEM_ID)
    private String itemId;
    @SerializedName("item_number")
    private Long itemNumber;
    @SerializedName("level_config")
    private Long levelConfig;

    public static /* synthetic */ Detail copy$default(Detail detail, Long l, Long l2, String str, Long l3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            l = detail.levelConfig;
        }
        if ((i2 & 2) != 0) {
            l2 = detail.coinNumber;
        }
        if ((i2 & 4) != 0) {
            str = detail.itemId;
        }
        if ((i2 & 8) != 0) {
            l3 = detail.itemNumber;
        }
        return detail.copy(l, l2, str, l3);
    }

    public final Long component1() {
        return this.levelConfig;
    }

    public final Long component2() {
        return this.coinNumber;
    }

    public final String component3() {
        return this.itemId;
    }

    public final Long component4() {
        return this.itemNumber;
    }

    public final Detail copy(Long l, Long l2, String str, Long l3) {
        return new Detail(l, l2, str, l3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Detail) {
            Detail detail = (Detail) obj;
            return Intrinsics.areEqual(this.levelConfig, detail.levelConfig) && Intrinsics.areEqual(this.coinNumber, detail.coinNumber) && Intrinsics.areEqual(this.itemId, detail.itemId) && Intrinsics.areEqual(this.itemNumber, detail.itemNumber);
        }
        return false;
    }

    public int hashCode() {
        Long l = this.levelConfig;
        int hashCode = (l == null ? 0 : l.hashCode()) * 31;
        Long l2 = this.coinNumber;
        int hashCode2 = (hashCode + (l2 == null ? 0 : l2.hashCode())) * 31;
        String str = this.itemId;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Long l3 = this.itemNumber;
        return hashCode3 + (l3 != null ? l3.hashCode() : 0);
    }

    public Detail(Long l, Long l2, String str, Long l3) {
        this.levelConfig = l;
        this.coinNumber = l2;
        this.itemId = str;
        this.itemNumber = l3;
    }

    public final Long getLevelConfig() {
        return this.levelConfig;
    }

    public final void setLevelConfig(Long l) {
        this.levelConfig = l;
    }

    public final Long getCoinNumber() {
        return this.coinNumber;
    }

    public final void setCoinNumber(Long l) {
        this.coinNumber = l;
    }

    public final String getItemId() {
        return this.itemId;
    }

    public final void setItemId(String str) {
        this.itemId = str;
    }

    public final Long getItemNumber() {
        return this.itemNumber;
    }

    public final void setItemNumber(Long l) {
        this.itemNumber = l;
    }

    public String toString() {
        return "Detail(levelConfig=" + this.levelConfig + ", coinNumber=" + this.coinNumber + ", itemId=" + this.itemId + ", itemNumber=" + this.itemNumber + ')';
    }
}
