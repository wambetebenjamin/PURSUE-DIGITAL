package com.bytedance.ttgame.module.compliance.impl.realname;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
/* compiled from: IdentityInfoResponse.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0005\"\u0004\b\u0006\u0010\u0004¨\u0006\u000f"}, d2 = {"Lcom/bytedance/ttgame/module/compliance/impl/realname/IdentityInfoData;", "", "is_verified", "", "(Z)V", "()Z", "set_verified", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "compliance_impl_realname_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class IdentityInfoData {
    @SerializedName("is_verified")
    private boolean is_verified;

    public static /* synthetic */ IdentityInfoData copy$default(IdentityInfoData identityInfoData, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = identityInfoData.is_verified;
        }
        return identityInfoData.copy(z);
    }

    public final boolean component1() {
        return this.is_verified;
    }

    public final IdentityInfoData copy(boolean z) {
        return new IdentityInfoData(z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof IdentityInfoData) && this.is_verified == ((IdentityInfoData) obj).is_verified;
    }

    public int hashCode() {
        boolean z = this.is_verified;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public String toString() {
        return "IdentityInfoData(is_verified=" + this.is_verified + ')';
    }

    public IdentityInfoData(boolean z) {
        this.is_verified = z;
    }

    public final boolean is_verified() {
        return this.is_verified;
    }

    public final void set_verified(boolean z) {
        this.is_verified = z;
    }
}
