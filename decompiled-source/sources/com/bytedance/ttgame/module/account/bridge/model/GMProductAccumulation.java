package com.bytedance.ttgame.module.account.bridge.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GMPayModel.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BQ\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010 \u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000b\u0010!\u001a\u0004\u0018\u00010\nHÆ\u0003J\u0011\u0010\"\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fHÆ\u0003Jh\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\bHÖ\u0001J\t\u0010)\u001a\u00020\nHÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010R\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0015\u0010\u0010R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0018\u0010\u0010R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001a¨\u0006*"}, d2 = {"Lcom/bytedance/ttgame/module/account/bridge/model/GMProductAccumulation;", "", "accId", "", "currentSum", "startTime", "endTime", "version", "", "giftType", "", "details", "", "Lcom/bytedance/ttgame/module/account/bridge/model/GMProductAccumulationDetail;", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;)V", "getAccId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCurrentSum", "getDetails", "()Ljava/util/List;", "getEndTime", "getGiftType", "()Ljava/lang/String;", "getStartTime", "getVersion", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;)Lcom/bytedance/ttgame/module/account/bridge/model/GMProductAccumulation;", "equals", "", "other", "hashCode", "toString", "pay_bridge_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GMProductAccumulation {
    private final Long accId;
    private final Long currentSum;
    private final List<GMProductAccumulationDetail> details;
    private final Long endTime;
    private final String giftType;
    private final Long startTime;
    private final Integer version;

    public static /* synthetic */ GMProductAccumulation copy$default(GMProductAccumulation gMProductAccumulation, Long l, Long l2, Long l3, Long l4, Integer num, String str, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            l = gMProductAccumulation.accId;
        }
        if ((i2 & 2) != 0) {
            l2 = gMProductAccumulation.currentSum;
        }
        Long l5 = l2;
        if ((i2 & 4) != 0) {
            l3 = gMProductAccumulation.startTime;
        }
        Long l6 = l3;
        if ((i2 & 8) != 0) {
            l4 = gMProductAccumulation.endTime;
        }
        Long l7 = l4;
        if ((i2 & 16) != 0) {
            num = gMProductAccumulation.version;
        }
        Integer num2 = num;
        if ((i2 & 32) != 0) {
            str = gMProductAccumulation.giftType;
        }
        String str2 = str;
        List<GMProductAccumulationDetail> list2 = list;
        if ((i2 & 64) != 0) {
            list2 = gMProductAccumulation.details;
        }
        return gMProductAccumulation.copy(l, l5, l6, l7, num2, str2, list2);
    }

    public final Long component1() {
        return this.accId;
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
        return this.giftType;
    }

    public final List<GMProductAccumulationDetail> component7() {
        return this.details;
    }

    public final GMProductAccumulation copy(Long l, Long l2, Long l3, Long l4, Integer num, String str, List<GMProductAccumulationDetail> list) {
        return new GMProductAccumulation(l, l2, l3, l4, num, str, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GMProductAccumulation) {
            GMProductAccumulation gMProductAccumulation = (GMProductAccumulation) obj;
            return Intrinsics.areEqual(this.accId, gMProductAccumulation.accId) && Intrinsics.areEqual(this.currentSum, gMProductAccumulation.currentSum) && Intrinsics.areEqual(this.startTime, gMProductAccumulation.startTime) && Intrinsics.areEqual(this.endTime, gMProductAccumulation.endTime) && Intrinsics.areEqual(this.version, gMProductAccumulation.version) && Intrinsics.areEqual(this.giftType, gMProductAccumulation.giftType) && Intrinsics.areEqual(this.details, gMProductAccumulation.details);
        }
        return false;
    }

    public int hashCode() {
        Long l = this.accId;
        int hashCode = (l == null ? 0 : l.hashCode()) * 31;
        Long l2 = this.currentSum;
        int hashCode2 = (hashCode + (l2 == null ? 0 : l2.hashCode())) * 31;
        Long l3 = this.startTime;
        int hashCode3 = (hashCode2 + (l3 == null ? 0 : l3.hashCode())) * 31;
        Long l4 = this.endTime;
        int hashCode4 = (hashCode3 + (l4 == null ? 0 : l4.hashCode())) * 31;
        Integer num = this.version;
        int hashCode5 = (hashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.giftType;
        int hashCode6 = (hashCode5 + (str == null ? 0 : str.hashCode())) * 31;
        List<GMProductAccumulationDetail> list = this.details;
        return hashCode6 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "GMProductAccumulation(accId=" + this.accId + ", currentSum=" + this.currentSum + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", version=" + this.version + ", giftType=" + this.giftType + ", details=" + this.details + ')';
    }

    public GMProductAccumulation(Long l, Long l2, Long l3, Long l4, Integer num, String str, List<GMProductAccumulationDetail> list) {
        this.accId = l;
        this.currentSum = l2;
        this.startTime = l3;
        this.endTime = l4;
        this.version = num;
        this.giftType = str;
        this.details = list;
    }

    public final Long getAccId() {
        return this.accId;
    }

    public final Long getCurrentSum() {
        return this.currentSum;
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

    public final String getGiftType() {
        return this.giftType;
    }

    public final List<GMProductAccumulationDetail> getDetails() {
        return this.details;
    }
}
