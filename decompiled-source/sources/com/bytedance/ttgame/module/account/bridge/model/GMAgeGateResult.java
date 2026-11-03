package com.bytedance.ttgame.module.account.bridge.model;

import com.bytedance.ttgame.channel.account.ErrorConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GMAgeGateResult.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001c\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\nHÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u000eHÆ\u0003Jc\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0013\u0010&\u001a\u00020\u000e2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0003HÖ\u0001J\t\u0010)\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0018R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013¨\u0006*"}, d2 = {"Lcom/bytedance/ttgame/module/account/bridge/model/GMAgeGateResult;", "", "code", "", "message", "", ErrorConstants.EXTRA_ERROR_CODE, ErrorConstants.EXTRA_ERROR_MESSAGE, ErrorConstants.ADDITIONAL_INFO, "currentTimestamp", "", "windowType", "limitAge", "isInCoolingPeriod", "", "(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;JIIZ)V", "getAdditionalInfo", "()Ljava/lang/String;", "getCode", "()I", "getCurrentTimestamp", "()J", "getExtraErrorCode", "getExtraErrorMessage", "()Z", "getLimitAge", "getMessage", "getWindowType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "account_bridge_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GMAgeGateResult {
    private final String additionalInfo;
    private final int code;
    private final long currentTimestamp;
    private final int extraErrorCode;
    private final String extraErrorMessage;
    private final boolean isInCoolingPeriod;
    private final int limitAge;
    private final String message;
    private final int windowType;

    public GMAgeGateResult() {
        this(0, null, 0, null, null, 0L, 0, 0, false, 511, null);
    }

    public final int component1() {
        return this.code;
    }

    public final String component2() {
        return this.message;
    }

    public final int component3() {
        return this.extraErrorCode;
    }

    public final String component4() {
        return this.extraErrorMessage;
    }

    public final String component5() {
        return this.additionalInfo;
    }

    public final long component6() {
        return this.currentTimestamp;
    }

    public final int component7() {
        return this.windowType;
    }

    public final int component8() {
        return this.limitAge;
    }

    public final boolean component9() {
        return this.isInCoolingPeriod;
    }

    public final GMAgeGateResult copy(int i2, String message, int i3, String extraErrorMessage, String additionalInfo, long j2, int i4, int i5, boolean z) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(extraErrorMessage, "extraErrorMessage");
        Intrinsics.checkNotNullParameter(additionalInfo, "additionalInfo");
        return new GMAgeGateResult(i2, message, i3, extraErrorMessage, additionalInfo, j2, i4, i5, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GMAgeGateResult) {
            GMAgeGateResult gMAgeGateResult = (GMAgeGateResult) obj;
            return this.code == gMAgeGateResult.code && Intrinsics.areEqual(this.message, gMAgeGateResult.message) && this.extraErrorCode == gMAgeGateResult.extraErrorCode && Intrinsics.areEqual(this.extraErrorMessage, gMAgeGateResult.extraErrorMessage) && Intrinsics.areEqual(this.additionalInfo, gMAgeGateResult.additionalInfo) && this.currentTimestamp == gMAgeGateResult.currentTimestamp && this.windowType == gMAgeGateResult.windowType && this.limitAge == gMAgeGateResult.limitAge && this.isInCoolingPeriod == gMAgeGateResult.isInCoolingPeriod;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((((((((((((Integer.hashCode(this.code) * 31) + this.message.hashCode()) * 31) + Integer.hashCode(this.extraErrorCode)) * 31) + this.extraErrorMessage.hashCode()) * 31) + this.additionalInfo.hashCode()) * 31) + Long.hashCode(this.currentTimestamp)) * 31) + Integer.hashCode(this.windowType)) * 31) + Integer.hashCode(this.limitAge)) * 31;
        boolean z = this.isInCoolingPeriod;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return hashCode + i2;
    }

    public String toString() {
        return "GMAgeGateResult(code=" + this.code + ", message=" + this.message + ", extraErrorCode=" + this.extraErrorCode + ", extraErrorMessage=" + this.extraErrorMessage + ", additionalInfo=" + this.additionalInfo + ", currentTimestamp=" + this.currentTimestamp + ", windowType=" + this.windowType + ", limitAge=" + this.limitAge + ", isInCoolingPeriod=" + this.isInCoolingPeriod + ')';
    }

    public GMAgeGateResult(int i2, String message, int i3, String extraErrorMessage, String additionalInfo, long j2, int i4, int i5, boolean z) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(extraErrorMessage, "extraErrorMessage");
        Intrinsics.checkNotNullParameter(additionalInfo, "additionalInfo");
        this.code = i2;
        this.message = message;
        this.extraErrorCode = i3;
        this.extraErrorMessage = extraErrorMessage;
        this.additionalInfo = additionalInfo;
        this.currentTimestamp = j2;
        this.windowType = i4;
        this.limitAge = i5;
        this.isInCoolingPeriod = z;
    }

    public /* synthetic */ GMAgeGateResult(int i2, String str, int i3, String str2, String str3, long j2, int i4, int i5, boolean z, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? 0 : i2, (i6 & 2) != 0 ? "" : str, (i6 & 4) != 0 ? 0 : i3, (i6 & 8) != 0 ? "" : str2, (i6 & 16) == 0 ? str3 : "", (i6 & 32) != 0 ? 0L : j2, (i6 & 64) != 0 ? 0 : i4, (i6 & 128) != 0 ? 0 : i5, (i6 & 256) == 0 ? z : false);
    }

    public final int getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

    public final int getExtraErrorCode() {
        return this.extraErrorCode;
    }

    public final String getExtraErrorMessage() {
        return this.extraErrorMessage;
    }

    public final String getAdditionalInfo() {
        return this.additionalInfo;
    }

    public final long getCurrentTimestamp() {
        return this.currentTimestamp;
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
