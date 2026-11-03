package com.bytedance.ttgame.channel.entity;

import com.bytedance.apm.constant.l;
import com.bytedance.ttgame.sdk.module.account.pojo.BaseResponse;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AccountWithSamePuidInfoResponse.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/channel/entity/OSAccountInfoResponse;", "Lcom/bytedance/ttgame/sdk/module/account/pojo/BaseResponse;", "logId", "", "osAccountInfoList", "Lcom/bytedance/ttgame/channel/entity/OSAccountInfoList;", "(Ljava/lang/String;Lcom/bytedance/ttgame/channel/entity/OSAccountInfoList;)V", "getLogId", "()Ljava/lang/String;", "getOsAccountInfoList", "()Lcom/bytedance/ttgame/channel/entity/OSAccountInfoList;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "account_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OSAccountInfoResponse extends BaseResponse {
    @SerializedName(l.KEY_LOG_ID)
    private final String logId;
    @SerializedName("data")
    private final OSAccountInfoList osAccountInfoList;

    public static /* synthetic */ OSAccountInfoResponse copy$default(OSAccountInfoResponse oSAccountInfoResponse, String str, OSAccountInfoList oSAccountInfoList, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = oSAccountInfoResponse.logId;
        }
        if ((i2 & 2) != 0) {
            oSAccountInfoList = oSAccountInfoResponse.osAccountInfoList;
        }
        return oSAccountInfoResponse.copy(str, oSAccountInfoList);
    }

    public final String component1() {
        return this.logId;
    }

    public final OSAccountInfoList component2() {
        return this.osAccountInfoList;
    }

    public final OSAccountInfoResponse copy(String str, OSAccountInfoList osAccountInfoList) {
        Intrinsics.checkNotNullParameter(osAccountInfoList, "osAccountInfoList");
        return new OSAccountInfoResponse(str, osAccountInfoList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OSAccountInfoResponse) {
            OSAccountInfoResponse oSAccountInfoResponse = (OSAccountInfoResponse) obj;
            return Intrinsics.areEqual(this.logId, oSAccountInfoResponse.logId) && Intrinsics.areEqual(this.osAccountInfoList, oSAccountInfoResponse.osAccountInfoList);
        }
        return false;
    }

    public int hashCode() {
        String str = this.logId;
        return ((str == null ? 0 : str.hashCode()) * 31) + this.osAccountInfoList.hashCode();
    }

    public String toString() {
        return "OSAccountInfoResponse(logId=" + this.logId + ", osAccountInfoList=" + this.osAccountInfoList + ')';
    }

    public final String getLogId() {
        return this.logId;
    }

    public OSAccountInfoResponse(String str, OSAccountInfoList osAccountInfoList) {
        Intrinsics.checkNotNullParameter(osAccountInfoList, "osAccountInfoList");
        this.logId = str;
        this.osAccountInfoList = osAccountInfoList;
    }

    public final OSAccountInfoList getOsAccountInfoList() {
        return this.osAccountInfoList;
    }
}
