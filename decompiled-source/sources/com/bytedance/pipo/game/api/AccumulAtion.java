package com.bytedance.pipo.game.api;

import com.bytedance.ttgame.module.cdkey.api.ExchangeConstantKt;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AccumulAtion.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001BQ\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f¢\u0006\u0002\u0010\u000eJ\u0010\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010+\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010#J\u000b\u0010,\u001a\u0004\u0018\u00010\nHÆ\u0003J\u0011\u0010-\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fHÆ\u0003Jh\u0010.\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010/J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u000103HÖ\u0003J\t\u00104\u001a\u00020\bHÖ\u0001J\b\u00105\u001a\u00020\nH\u0016R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R&\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012R \u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\"\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b \u0010\u0010\"\u0004\b!\u0010\u0012R\"\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u00066"}, d2 = {"Lcom/bytedance/pipo/game/api/AccumulAtion;", "Ljava/io/Serializable;", "activityId", "", "currentSum", "startTime", "endTime", "version", "", "gift", "", "details", "", "Lcom/bytedance/pipo/game/api/Detail;", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;)V", "getActivityId", "()Ljava/lang/Long;", "setActivityId", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getCurrentSum", "setCurrentSum", "getDetails", "()Ljava/util/List;", "setDetails", "(Ljava/util/List;)V", "getEndTime", "setEndTime", "getGift", "()Ljava/lang/String;", "setGift", "(Ljava/lang/String;)V", "getStartTime", "setStartTime", "getVersion", "()Ljava/lang/Integer;", "setVersion", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;)Lcom/bytedance/pipo/game/api/AccumulAtion;", "equals", "", "other", "", "hashCode", "toString", "library_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AccumulAtion implements Serializable {
    @SerializedName(ExchangeConstantKt.ACTIVITY_ID)
    private Long activityId;
    @SerializedName("current_sum")
    private Long currentSum;
    @SerializedName("details")
    private List<Detail> details;
    @SerializedName("end_time")
    private Long endTime;
    @SerializedName("gift")
    private String gift;
    @SerializedName(com.bytedance.apm.constant.c.KEY_START_TIME)
    private Long startTime;
    @SerializedName("version")
    private Integer version;

    public static /* synthetic */ AccumulAtion copy$default(AccumulAtion accumulAtion, Long l, Long l2, Long l3, Long l4, Integer num, String str, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            l = accumulAtion.activityId;
        }
        if ((i2 & 2) != 0) {
            l2 = accumulAtion.currentSum;
        }
        Long l5 = l2;
        if ((i2 & 4) != 0) {
            l3 = accumulAtion.startTime;
        }
        Long l6 = l3;
        if ((i2 & 8) != 0) {
            l4 = accumulAtion.endTime;
        }
        Long l7 = l4;
        if ((i2 & 16) != 0) {
            num = accumulAtion.version;
        }
        Integer num2 = num;
        if ((i2 & 32) != 0) {
            str = accumulAtion.gift;
        }
        String str2 = str;
        List<Detail> list2 = list;
        if ((i2 & 64) != 0) {
            list2 = accumulAtion.details;
        }
        return accumulAtion.copy(l, l5, l6, l7, num2, str2, list2);
    }

    public final Long component1() {
        return this.activityId;
    }

    public final Long component2() {
        return this.currentSum;
    }

    public final Long component3() {
        return this.startTime;
    }

    public final Long component4() {
        return this.endTime;
    }

    public final Integer component5() {
        return this.version;
    }

    public final String component6() {
        return this.gift;
    }

    public final List<Detail> component7() {
        return this.details;
    }

    public final AccumulAtion copy(Long l, Long l2, Long l3, Long l4, Integer num, String str, List<Detail> list) {
        return new AccumulAtion(l, l2, l3, l4, num, str, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AccumulAtion) {
            AccumulAtion accumulAtion = (AccumulAtion) obj;
            return Intrinsics.areEqual(this.activityId, accumulAtion.activityId) && Intrinsics.areEqual(this.currentSum, accumulAtion.currentSum) && Intrinsics.areEqual(this.startTime, accumulAtion.startTime) && Intrinsics.areEqual(this.endTime, accumulAtion.endTime) && Intrinsics.areEqual(this.version, accumulAtion.version) && Intrinsics.areEqual(this.gift, accumulAtion.gift) && Intrinsics.areEqual(this.details, accumulAtion.details);
        }
        return false;
    }

    public int hashCode() {
        Long l = this.activityId;
        int hashCode = (l == null ? 0 : l.hashCode()) * 31;
        Long l2 = this.currentSum;
        int hashCode2 = (hashCode + (l2 == null ? 0 : l2.hashCode())) * 31;
        Long l3 = this.startTime;
        int hashCode3 = (hashCode2 + (l3 == null ? 0 : l3.hashCode())) * 31;
        Long l4 = this.endTime;
        int hashCode4 = (hashCode3 + (l4 == null ? 0 : l4.hashCode())) * 31;
        Integer num = this.version;
        int hashCode5 = (hashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.gift;
        int hashCode6 = (hashCode5 + (str == null ? 0 : str.hashCode())) * 31;
        List<Detail> list = this.details;
        return hashCode6 + (list != null ? list.hashCode() : 0);
    }

    public AccumulAtion(Long l, Long l2, Long l3, Long l4, Integer num, String str, List<Detail> list) {
        this.activityId = l;
        this.currentSum = l2;
        this.startTime = l3;
        this.endTime = l4;
        this.version = num;
        this.gift = str;
        this.details = list;
    }

    public final Long getActivityId() {
        return this.activityId;
    }

    public final void setActivityId(Long l) {
        this.activityId = l;
    }

    public final Long getCurrentSum() {
        return this.currentSum;
    }

    public final void setCurrentSum(Long l) {
        this.currentSum = l;
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

    public final String getGift() {
        return this.gift;
    }

    public final void setGift(String str) {
        this.gift = str;
    }

    public final List<Detail> getDetails() {
        return this.details;
    }

    public final void setDetails(List<Detail> list) {
        this.details = list;
    }

    public String toString() {
        return "AccumulAtion(activityId=" + this.activityId + ", currentSum=" + this.currentSum + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", version=" + this.version + ", gift=" + this.gift + ", details=" + this.details + ')';
    }
}
