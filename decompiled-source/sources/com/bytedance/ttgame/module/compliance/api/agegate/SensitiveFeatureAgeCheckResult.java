package com.bytedance.ttgame.module.compliance.api.agegate;

import com.bytedance.ttgame.base.GSDKError;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SensitiveFeatureAgeCheckResult.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/bytedance/ttgame/module/compliance/api/agegate/SensitiveFeatureAgeCheckResult;", "", "allowed", "", "gsdkError", "Lcom/bytedance/ttgame/base/GSDKError;", "(ZLcom/bytedance/ttgame/base/GSDKError;)V", "getAllowed", "()Z", "getGsdkError", "()Lcom/bytedance/ttgame/base/GSDKError;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "compliance_api_agegate_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SensitiveFeatureAgeCheckResult {
    private final boolean allowed;
    private final GSDKError gsdkError;

    public SensitiveFeatureAgeCheckResult() {
        this(false, null, 3, null);
    }

    public static /* synthetic */ SensitiveFeatureAgeCheckResult copy$default(SensitiveFeatureAgeCheckResult sensitiveFeatureAgeCheckResult, boolean z, GSDKError gSDKError, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = sensitiveFeatureAgeCheckResult.allowed;
        }
        if ((i2 & 2) != 0) {
            gSDKError = sensitiveFeatureAgeCheckResult.gsdkError;
        }
        return sensitiveFeatureAgeCheckResult.copy(z, gSDKError);
    }

    public final boolean component1() {
        return this.allowed;
    }

    public final GSDKError component2() {
        return this.gsdkError;
    }

    public final SensitiveFeatureAgeCheckResult copy(boolean z, GSDKError gSDKError) {
        return new SensitiveFeatureAgeCheckResult(z, gSDKError);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SensitiveFeatureAgeCheckResult) {
            SensitiveFeatureAgeCheckResult sensitiveFeatureAgeCheckResult = (SensitiveFeatureAgeCheckResult) obj;
            return this.allowed == sensitiveFeatureAgeCheckResult.allowed && Intrinsics.areEqual(this.gsdkError, sensitiveFeatureAgeCheckResult.gsdkError);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public int hashCode() {
        boolean z = this.allowed;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i2 = r0 * 31;
        GSDKError gSDKError = this.gsdkError;
        return i2 + (gSDKError == null ? 0 : gSDKError.hashCode());
    }

    public String toString() {
        return "SensitiveFeatureAgeCheckResult(allowed=" + this.allowed + ", gsdkError=" + this.gsdkError + ')';
    }

    public SensitiveFeatureAgeCheckResult(boolean z, GSDKError gSDKError) {
        this.allowed = z;
        this.gsdkError = gSDKError;
    }

    public /* synthetic */ SensitiveFeatureAgeCheckResult(boolean z, GSDKError gSDKError, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? null : gSDKError);
    }

    public final boolean getAllowed() {
        return this.allowed;
    }

    public final GSDKError getGsdkError() {
        return this.gsdkError;
    }
}
