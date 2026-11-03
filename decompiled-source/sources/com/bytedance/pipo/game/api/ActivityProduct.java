package com.bytedance.pipo.game.api;

import com.bytedance.ttgame.module.cdkey.api.ExchangeConstantKt;
import com.bytedance.ttgame.module.share.api.utils.ConstantKt;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ActivityProduct.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b/\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001Bs\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0010J\u0010\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0017J\u0010\u00100\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u00101\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0017J\u000b\u00105\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0017J\u0010\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0017J\u0010\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0017J\u0010\u00109\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010\u0012J\u0092\u0001\u0010:\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010;J\u0013\u0010<\u001a\u00020=2\b\u0010>\u001a\u0004\u0018\u00010?HÖ\u0003J\t\u0010@\u001a\u00020\rHÖ\u0001J\t\u0010A\u001a\u00020\u0005HÖ\u0001R\"\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\"\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u001f\u0010\u0017\"\u0004\b \u0010\u0019R \u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001c\"\u0004\b\"\u0010\u001eR\"\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b#\u0010\u0017\"\u0004\b$\u0010\u0019R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001c\"\u0004\b&\u0010\u001eR \u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001c\"\u0004\b(\u0010\u001eR\"\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b)\u0010\u0017\"\u0004\b*\u0010\u0019R\"\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b+\u0010\u0017\"\u0004\b,\u0010\u0019R\"\u0010\f\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b-\u0010\u0012\"\u0004\b.\u0010\u0014¨\u0006B"}, d2 = {"Lcom/bytedance/pipo/game/api/ActivityProduct;", "Ljava/io/Serializable;", "activityId", "", "activityType", "", "gift", "coinNumber", "itemId", "itemNumber", "startTime", "endTime", "version", "", "active", ConstantKt.DESC, "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "getActive", "()Ljava/lang/Integer;", "setActive", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getActivityId", "()Ljava/lang/Long;", "setActivityId", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getActivityType", "()Ljava/lang/String;", "setActivityType", "(Ljava/lang/String;)V", "getCoinNumber", "setCoinNumber", "getDesc", "setDesc", "getEndTime", "setEndTime", "getGift", "setGift", "getItemId", "setItemId", "getItemNumber", "setItemNumber", "getStartTime", "setStartTime", "getVersion", "setVersion", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/bytedance/pipo/game/api/ActivityProduct;", "equals", "", "other", "", "hashCode", "toString", "library_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ActivityProduct implements Serializable {
    @SerializedName("active")
    private Integer active;
    @SerializedName(ExchangeConstantKt.ACTIVITY_ID)
    private Long activityId;
    @SerializedName("activity_type")
    private String activityType;
    @SerializedName("coin_number")
    private Long coinNumber;
    @SerializedName(ConstantKt.DESC)
    private String desc;
    @SerializedName("end_time")
    private Long endTime;
    @SerializedName("gift")
    private String gift;
    @SerializedName(FirebaseAnalytics.Param.ITEM_ID)
    private String itemId;
    @SerializedName("item_number")
    private Long itemNumber;
    @SerializedName(com.bytedance.apm.constant.c.KEY_START_TIME)
    private Long startTime;
    @SerializedName("version")
    private Integer version;

    public final Long component1() {
        return this.activityId;
    }

    public final Integer component10() {
        return this.active;
    }

    public final String component11() {
        return this.desc;
    }

    public final String component2() {
        return this.activityType;
    }

    public final String component3() {
        return this.gift;
    }

    public final Long component4() {
        return this.coinNumber;
    }

    public final String component5() {
        return this.itemId;
    }

    public final Long component6() {
        return this.itemNumber;
    }

    public final Long component7() {
        return this.startTime;
    }

    public final Long component8() {
        return this.endTime;
    }

    public final Integer component9() {
        return this.version;
    }

    public final ActivityProduct copy(Long l, String str, String str2, Long l2, String str3, Long l3, Long l4, Long l5, Integer num, Integer num2, String str4) {
        return new ActivityProduct(l, str, str2, l2, str3, l3, l4, l5, num, num2, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ActivityProduct) {
            ActivityProduct activityProduct = (ActivityProduct) obj;
            return Intrinsics.areEqual(this.activityId, activityProduct.activityId) && Intrinsics.areEqual(this.activityType, activityProduct.activityType) && Intrinsics.areEqual(this.gift, activityProduct.gift) && Intrinsics.areEqual(this.coinNumber, activityProduct.coinNumber) && Intrinsics.areEqual(this.itemId, activityProduct.itemId) && Intrinsics.areEqual(this.itemNumber, activityProduct.itemNumber) && Intrinsics.areEqual(this.startTime, activityProduct.startTime) && Intrinsics.areEqual(this.endTime, activityProduct.endTime) && Intrinsics.areEqual(this.version, activityProduct.version) && Intrinsics.areEqual(this.active, activityProduct.active) && Intrinsics.areEqual(this.desc, activityProduct.desc);
        }
        return false;
    }

    public int hashCode() {
        Long l = this.activityId;
        int hashCode = (l == null ? 0 : l.hashCode()) * 31;
        String str = this.activityType;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.gift;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l2 = this.coinNumber;
        int hashCode4 = (hashCode3 + (l2 == null ? 0 : l2.hashCode())) * 31;
        String str3 = this.itemId;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Long l3 = this.itemNumber;
        int hashCode6 = (hashCode5 + (l3 == null ? 0 : l3.hashCode())) * 31;
        Long l4 = this.startTime;
        int hashCode7 = (hashCode6 + (l4 == null ? 0 : l4.hashCode())) * 31;
        Long l5 = this.endTime;
        int hashCode8 = (hashCode7 + (l5 == null ? 0 : l5.hashCode())) * 31;
        Integer num = this.version;
        int hashCode9 = (hashCode8 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.active;
        int hashCode10 = (hashCode9 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str4 = this.desc;
        return hashCode10 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ActivityProduct(activityId=").append(this.activityId).append(", activityType=").append(this.activityType).append(", gift=").append(this.gift).append(", coinNumber=").append(this.coinNumber).append(", itemId=").append(this.itemId).append(", itemNumber=").append(this.itemNumber).append(", startTime=").append(this.startTime).append(", endTime=").append(this.endTime).append(", version=").append(this.version).append(", active=").append(this.active).append(", desc=").append(this.desc).append(')');
        return sb.toString();
    }

    public ActivityProduct(Long l, String str, String str2, Long l2, String str3, Long l3, Long l4, Long l5, Integer num, Integer num2, String str4) {
        this.activityId = l;
        this.activityType = str;
        this.gift = str2;
        this.coinNumber = l2;
        this.itemId = str3;
        this.itemNumber = l3;
        this.startTime = l4;
        this.endTime = l5;
        this.version = num;
        this.active = num2;
        this.desc = str4;
    }

    public final Long getActivityId() {
        return this.activityId;
    }

    public final void setActivityId(Long l) {
        this.activityId = l;
    }

    public final String getActivityType() {
        return this.activityType;
    }

    public final void setActivityType(String str) {
        this.activityType = str;
    }

    public final String getGift() {
        return this.gift;
    }

    public final void setGift(String str) {
        this.gift = str;
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

    public final Long getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(Long l) {
        this.startTime = l;
    }

    public final Long getEndTime() {
        return this.endTime;
    }

    public final void setEndTime(Long l) {
        this.endTime = l;
    }

    public final Integer getVersion() {
        return this.version;
    }

    public final void setVersion(Integer num) {
        this.version = num;
    }

    public final Integer getActive() {
        return this.active;
    }

    public final void setActive(Integer num) {
        this.active = num;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final void setDesc(String str) {
        this.desc = str;
    }
}
