package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.ttgame.base.GSDKError;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AgeGateResult.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\nHÆ\u0003J=\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/bytedance/ttgame/rocketapi/account/AgeGateResult;", "", "gsdkError", "Lcom/bytedance/ttgame/base/GSDKError;", "currentTimeStamp", "", "windowType", "", "limitAge", "isInCoolingPeriod", "", "(Lcom/bytedance/ttgame/base/GSDKError;JIIZ)V", "getCurrentTimeStamp", "()J", "getGsdkError", "()Lcom/bytedance/ttgame/base/GSDKError;", "()Z", "getLimitAge", "()I", "getWindowType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "", "account_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class AgeGateResult {
    private final long currentTimeStamp;
    private final GSDKError gsdkError;
    private final boolean isInCoolingPeriod;
    private final int limitAge;
    private final int windowType;

    public static /* synthetic */ AgeGateResult copy$default(AgeGateResult ageGateResult, GSDKError gSDKError, long j2, int i2, int i3, boolean z, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            gSDKError = ageGateResult.gsdkError;
        }
        if ((i4 & 2) != 0) {
            j2 = ageGateResult.currentTimeStamp;
        }
        long j3 = j2;
        if ((i4 & 4) != 0) {
            i2 = ageGateResult.windowType;
        }
        int i5 = i2;
        if ((i4 & 8) != 0) {
            i3 = ageGateResult.limitAge;
        }
        int i6 = i3;
        if ((i4 & 16) != 0) {
            z = ageGateResult.isInCoolingPeriod;
        }
        return ageGateResult.copy(gSDKError, j3, i5, i6, z);
    }

    public final GSDKError component1() {
        return this.gsdkError;
    }

    public final long component2() {
        return this.currentTimeStamp;
    }

    public final int component3() {
        return this.windowType;
    }

    public final int component4() {
        return this.limitAge;
    }

    public final boolean component5() {
        return this.isInCoolingPeriod;
    }

    public final AgeGateResult copy(GSDKError gSDKError, long j2, int i2, int i3, boolean z) {
        return new AgeGateResult(gSDKError, j2, i2, i3, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AgeGateResult) {
            AgeGateResult ageGateResult = (AgeGateResult) obj;
            return Intrinsics.areEqual(this.gsdkError, ageGateResult.gsdkError) && this.currentTimeStamp == ageGateResult.currentTimeStamp && this.windowType == ageGateResult.windowType && this.limitAge == ageGateResult.limitAge && this.isInCoolingPeriod == ageGateResult.isInCoolingPeriod;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        GSDKError gSDKError = this.gsdkError;
        int hashCode = (((((((gSDKError == null ? 0 : gSDKError.hashCode()) * 31) + Long.hashCode(this.currentTimeStamp)) * 31) + Integer.hashCode(this.windowType)) * 31) + Integer.hashCode(this.limitAge)) * 31;
        boolean z = this.isInCoolingPeriod;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return hashCode + i2;
    }

    public String toString() {
        return "AgeGateResult(gsdkError=" + this.gsdkError + ", currentTimeStamp=" + this.currentTimeStamp + ", windowType=" + this.windowType + ", limitAge=" + this.limitAge + ", isInCoolingPeriod=" + this.isInCoolingPeriod + ')';
    }

    public AgeGateResult(GSDKError gSDKError, long j2, int i2, int i3, boolean z) {
        this.gsdkError = gSDKError;
        this.currentTimeStamp = j2;
        this.windowType = i2;
        this.limitAge = i3;
        this.isInCoolingPeriod = z;
    }

    public /* synthetic */ AgeGateResult(GSDKError gSDKError, long j2, int i2, int i3, boolean z, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(gSDKError, (i4 & 2) != 0 ? 0L : j2, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3, (i4 & 16) != 0 ? false : z);
    }

    public final GSDKError getGsdkError() {
        return this.gsdkError;
    }

    public final long getCurrentTimeStamp() {
        return this.currentTimeStamp;
    }

    public final int getWindowType() {
        return this.windowType;
    }

    public final int getLimitAge() {
        return this.limitAge;
    }

    public final boolean isInCoolingPeriod() {
        return this.isInCoolingPeriod;
    }
}
