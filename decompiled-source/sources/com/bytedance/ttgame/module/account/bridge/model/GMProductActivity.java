package com.bytedance.ttgame.module.account.bridge.model;

import com.bytedance.ttgame.module.share.api.utils.ConstantKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GMPayModel.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b)\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0011J\u0010\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0016J\u000b\u0010&\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010+\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010,\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0016J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0092\u0001\u0010/\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u00100J\u0013\u00101\u001a\u00020\f2\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u00020\nHÖ\u0001J\t\u00104\u001a\u00020\u0005HÖ\u0001R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001a\u0010\u0016R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001c\u0010\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001f\u0010\u0016R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b \u0010\u0016R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010#\u001a\u0004\b!\u0010\"¨\u00065"}, d2 = {"Lcom/bytedance/ttgame/module/account/bridge/model/GMProductActivity;", "", "activityId", "", "activityType", "", "giftType", "startTime", "endTime", "version", "", "active", "", "coinNumber", "itemId", "itemNumber", ConstantKt.DESC, "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)V", "getActive", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getActivityId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getActivityType", "()Ljava/lang/String;", "getCoinNumber", "getDesc", "getEndTime", "getGiftType", "getItemId", "getItemNumber", "getStartTime", "getVersion", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)Lcom/bytedance/ttgame/module/account/bridge/model/GMProductActivity;", "equals", "other", "hashCode", "toString", "pay_bridge_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GMProductActivity {
    private final Boolean active;
    private final Long activityId;
    private final String activityType;
    private final Long coinNumber;
    private final String desc;
    private final Long endTime;
    private final String giftType;
    private final String itemId;
    private final Long itemNumber;
    private final Long startTime;
    private final Integer version;

    public final Long component1() {
        return this.activityId;
    }

    public final Long component10() {
        return this.itemNumber;
    }

    public final String component11() {
        return this.desc;
    }

    public final String component2() {
        return this.activityType;
    }

    public final String component3() {
        return this.giftType;
    }

    public final Long component4() {
        return this.startTime;
    }

    public final Long component5() {
        return this.endTime;
    }

    public final Integer component6() {
        return this.version;
    }

    public final Boolean component7() {
        return this.active;
    }

    public final Long component8() {
        return this.coinNumber;
    }

    public final String component9() {
        return this.itemId;
    }

    public final GMProductActivity copy(Long l, String str, String str2, Long l2, Long l3, Integer num, Boolean bool, Long l4, String str3, Long l5, String str4) {
        return new GMProductActivity(l, str, str2, l2, l3, num, bool, l4, str3, l5, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GMProductActivity) {
            GMProductActivity gMProductActivity = (GMProductActivity) obj;
            return Intrinsics.areEqual(this.activityId, gMProductActivity.activityId) && Intrinsics.areEqual(this.activityType, gMProductActivity.activityType) && Intrinsics.areEqual(this.giftType, gMProductActivity.giftType) && Intrinsics.areEqual(this.startTime, gMProductActivity.startTime) && Intrinsics.areEqual(this.endTime, gMProductActivity.endTime) && Intrinsics.areEqual(this.version, gMProductActivity.version) && Intrinsics.areEqual(this.active, gMProductActivity.active) && Intrinsics.areEqual(this.coinNumber, gMProductActivity.coinNumber) && Intrinsics.areEqual(this.itemId, gMProductActivity.itemId) && Intrinsics.areEqual(this.itemNumber, gMProductActivity.itemNumber) && Intrinsics.areEqual(this.desc, gMProductActivity.desc);
        }
        return false;
    }

    public int hashCode() {
        Long l = this.activityId;
        int hashCode = (l == null ? 0 : l.hashCode()) * 31;
        String str = this.activityType;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.giftType;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l2 = this.startTime;
        int hashCode4 = (hashCode3 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Long l3 = this.endTime;
        int hashCode5 = (hashCode4 + (l3 == null ? 0 : l3.hashCode())) * 31;
        Integer num = this.version;
        int hashCode6 = (hashCode5 + (num == null ? 0 : num.hashCode())) * 31;
        Boolean bool = this.active;
        int hashCode7 = (hashCode6 + (bool == null ? 0 : bool.hashCode())) * 31;
        Long l4 = this.coinNumber;
        int hashCode8 = (hashCode7 + (l4 == null ? 0 : l4.hashCode())) * 31;
        String str3 = this.itemId;
        int hashCode9 = (hashCode8 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Long l5 = this.itemNumber;
        int hashCode10 = (hashCode9 + (l5 == null ? 0 : l5.hashCode())) * 31;
        String str4 = this.desc;
        return hashCode10 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GMProductActivity(activityId=").append(this.activityId).append(", activityType=").append(this.activityType).append(", giftType=").append(this.giftType).append(", startTime=").append(this.startTime).append(", endTime=").append(this.endTime).append(", version=").append(this.version).append(", active=").append(this.active).append(", coinNumber=").append(this.coinNumber).append(", itemId=").append(this.itemId).append(", itemNumber=").append(this.itemNumber).append(", desc=").append(this.desc).append(')');
        return sb.toString();
    }

    public GMProductActivity(Long l, String str, String str2, Long l2, Long l3, Integer num, Boolean bool, Long l4, String str3, Long l5, String str4) {
        this.activityId = l;
        this.activityType = str;
        this.giftType = str2;
        this.startTime = l2;
        this.endTime = l3;
        this.version = num;
        this.active = bool;
        this.coinNumber = l4;
        this.itemId = str3;
        this.itemNumber = l5;
        this.desc = str4;
    }

    public final Long getActivityId() {
        return this.activityId;
    }

    public final String getActivityType() {
        return this.activityType;
    }

    public final String getGiftType() {
        return this.giftType;
    }

    public final Long getStartTime() {
        return this.startTime;
    }

    public final Long getEndTime() {
        return this.endTime;
    }

    public final Integer getVersion() {
        return this.version;
    }

    public final Boolean getActive() {
        return this.active;
    }

    public final Long getCoinNumber() {
        return this.coinNumber;
    }

    public final String getItemId() {
        return this.itemId;
    }

    public final Long getItemNumber() {
        return this.itemNumber;
    }

    public final String getDesc() {
        return this.desc;
    }
}
