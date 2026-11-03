package com.bytedance.ttgame.module.account.bridge.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GMPayModel.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\bJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\nJ>\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u000e\u0010\nR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u000f\u0010\n¨\u0006\u001c"}, d2 = {"Lcom/bytedance/ttgame/module/account/bridge/model/GMProductAccumulationDetail;", "", "itemId", "", "coinNumber", "", "itemNumber", "levelNumber", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)V", "getCoinNumber", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getItemId", "()Ljava/lang/String;", "getItemNumber", "getLevelNumber", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)Lcom/bytedance/ttgame/module/account/bridge/model/GMProductAccumulationDetail;", "equals", "", "other", "hashCode", "", "toString", "pay_bridge_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GMProductAccumulationDetail {
    private final Long coinNumber;
    private final String itemId;
    private final Long itemNumber;
    private final Long levelNumber;

    public static /* synthetic */ GMProductAccumulationDetail copy$default(GMProductAccumulationDetail gMProductAccumulationDetail, String str, Long l, Long l2, Long l3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = gMProductAccumulationDetail.itemId;
        }
        if ((i2 & 2) != 0) {
            l = gMProductAccumulationDetail.coinNumber;
        }
        if ((i2 & 4) != 0) {
            l2 = gMProductAccumulationDetail.itemNumber;
        }
        if ((i2 & 8) != 0) {
            l3 = gMProductAccumulationDetail.levelNumber;
        }
        return gMProductAccumulationDetail.copy(str, l, l2, l3);
    }

    public final String component1() {
        return this.itemId;
    }

    public final Long component2() {
        return this.coinNumber;
    }

    public final Long component3() {
        return this.itemNumber;
    }

    public final Long component4() {
        return this.levelNumber;
    }

    public final GMProductAccumulationDetail copy(String str, Long l, Long l2, Long l3) {
        return new GMProductAccumulationDetail(str, l, l2, l3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GMProductAccumulationDetail) {
            GMProductAccumulationDetail gMProductAccumulationDetail = (GMProductAccumulationDetail) obj;
            return Intrinsics.areEqual(this.itemId, gMProductAccumulationDetail.itemId) && Intrinsics.areEqual(this.coinNumber, gMProductAccumulationDetail.coinNumber) && Intrinsics.areEqual(this.itemNumber, gMProductAccumulationDetail.itemNumber) && Intrinsics.areEqual(this.levelNumber, gMProductAccumulationDetail.levelNumber);
        }
        return false;
    }

    public int hashCode() {
        String str = this.itemId;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        Long l = this.coinNumber;
        int hashCode2 = (hashCode + (l == null ? 0 : l.hashCode())) * 31;
        Long l2 = this.itemNumber;
        int hashCode3 = (hashCode2 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Long l3 = this.levelNumber;
        return hashCode3 + (l3 != null ? l3.hashCode() : 0);
    }

    public String toString() {
        return "GMProductAccumulationDetail(itemId=" + this.itemId + ", coinNumber=" + this.coinNumber + ", itemNumber=" + this.itemNumber + ", levelNumber=" + this.levelNumber + ')';
    }

    public GMProductAccumulationDetail(String str, Long l, Long l2, Long l3) {
        this.itemId = str;
        this.coinNumber = l;
        this.itemNumber = l2;
        this.levelNumber = l3;
    }

    public final String getItemId() {
        return this.itemId;
    }

    public final Long getCoinNumber() {
        return this.coinNumber;
    }

    public final Long getItemNumber() {
        return this.itemNumber;
    }

    public final Long getLevelNumber() {
        return this.levelNumber;
    }
}
