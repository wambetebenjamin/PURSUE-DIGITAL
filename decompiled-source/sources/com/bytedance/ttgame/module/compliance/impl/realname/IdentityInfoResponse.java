package com.bytedance.ttgame.module.compliance.impl.realname;

import com.bytedance.ttgame.base.BaseResponse;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: IdentityInfoResponse.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/bytedance/ttgame/module/compliance/impl/realname/IdentityInfoResponse;", "Lcom/bytedance/ttgame/base/BaseResponse;", "logid", "", "data", "Lcom/bytedance/ttgame/module/compliance/impl/realname/IdentityInfoData;", "(Ljava/lang/String;Lcom/bytedance/ttgame/module/compliance/impl/realname/IdentityInfoData;)V", "getData", "()Lcom/bytedance/ttgame/module/compliance/impl/realname/IdentityInfoData;", "setData", "(Lcom/bytedance/ttgame/module/compliance/impl/realname/IdentityInfoData;)V", "getLogid", "()Ljava/lang/String;", "setLogid", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "compliance_impl_realname_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class IdentityInfoResponse extends BaseResponse {
    @SerializedName("data")
    private IdentityInfoData data;
    @SerializedName("logid")
    private String logid;

    public static /* synthetic */ IdentityInfoResponse copy$default(IdentityInfoResponse identityInfoResponse, String str, IdentityInfoData identityInfoData, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = identityInfoResponse.logid;
        }
        if ((i2 & 2) != 0) {
            identityInfoData = identityInfoResponse.data;
        }
        return identityInfoResponse.copy(str, identityInfoData);
    }

    public final String component1() {
        return this.logid;
    }

    public final IdentityInfoData component2() {
        return this.data;
    }

    public final IdentityInfoResponse copy(String str, IdentityInfoData identityInfoData) {
        return new IdentityInfoResponse(str, identityInfoData);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IdentityInfoResponse) {
            IdentityInfoResponse identityInfoResponse = (IdentityInfoResponse) obj;
            return Intrinsics.areEqual(this.logid, identityInfoResponse.logid) && Intrinsics.areEqual(this.data, identityInfoResponse.data);
        }
        return false;
    }

    public int hashCode() {
        String str = this.logid;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        IdentityInfoData identityInfoData = this.data;
        return hashCode + (identityInfoData != null ? identityInfoData.hashCode() : 0);
    }

    public String toString() {
        return "IdentityInfoResponse(logid=" + this.logid + ", data=" + this.data + ')';
    }

    public final String getLogid() {
        return this.logid;
    }

    public final void setLogid(String str) {
        this.logid = str;
    }

    public final IdentityInfoData getData() {
        return this.data;
    }

    public final void setData(IdentityInfoData identityInfoData) {
        this.data = identityInfoData;
    }

    public IdentityInfoResponse(String str, IdentityInfoData identityInfoData) {
        this.logid = str;
        this.data = identityInfoData;
    }
}
