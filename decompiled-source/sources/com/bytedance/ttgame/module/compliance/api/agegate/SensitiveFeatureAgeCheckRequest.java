package com.bytedance.ttgame.module.compliance.api.agegate;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SensitiveFeatureAgeCheckRequest.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/bytedance/ttgame/module/compliance/api/agegate/SensitiveFeatureAgeCheckRequest;", "", "sceneKey", "", "birthDate", "(Ljava/lang/String;Ljava/lang/String;)V", "getBirthDate", "()Ljava/lang/String;", "getSceneKey", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "compliance_api_agegate_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SensitiveFeatureAgeCheckRequest {
    private final String birthDate;
    private final String sceneKey;

    public SensitiveFeatureAgeCheckRequest() {
        this(null, null, 3, null);
    }

    public static /* synthetic */ SensitiveFeatureAgeCheckRequest copy$default(SensitiveFeatureAgeCheckRequest sensitiveFeatureAgeCheckRequest, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = sensitiveFeatureAgeCheckRequest.sceneKey;
        }
        if ((i2 & 2) != 0) {
            str2 = sensitiveFeatureAgeCheckRequest.birthDate;
        }
        return sensitiveFeatureAgeCheckRequest.copy(str, str2);
    }

    public final String component1() {
        return this.sceneKey;
    }

    public final String component2() {
        return this.birthDate;
    }

    public final SensitiveFeatureAgeCheckRequest copy(String str, String str2) {
        return new SensitiveFeatureAgeCheckRequest(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SensitiveFeatureAgeCheckRequest) {
            SensitiveFeatureAgeCheckRequest sensitiveFeatureAgeCheckRequest = (SensitiveFeatureAgeCheckRequest) obj;
            return Intrinsics.areEqual(this.sceneKey, sensitiveFeatureAgeCheckRequest.sceneKey) && Intrinsics.areEqual(this.birthDate, sensitiveFeatureAgeCheckRequest.birthDate);
        }
        return false;
    }

    public int hashCode() {
        String str = this.sceneKey;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.birthDate;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "SensitiveFeatureAgeCheckRequest(sceneKey=" + this.sceneKey + ", birthDate=" + this.birthDate + ')';
    }

    public SensitiveFeatureAgeCheckRequest(String str, String str2) {
        this.sceneKey = str;
        this.birthDate = str2;
    }

    public /* synthetic */ SensitiveFeatureAgeCheckRequest(String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2);
    }

    public final String getSceneKey() {
        return this.sceneKey;
    }

    public final String getBirthDate() {
        return this.birthDate;
    }
}
