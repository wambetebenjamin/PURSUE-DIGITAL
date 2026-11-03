package com.bytedance.ttgame.module.cdkey;

import com.bytedance.ttgame.module.cdkey.api.ExchangeConstantKt;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CDKeyService.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003JU\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006!"}, d2 = {"Lcom/bytedance/ttgame/module/cdkey/GiftExchangeRequest;", "", ExchangeConstantKt.ACTIVITY_ID, "", ExchangeConstantKt.PROCESS_ID, "access_token", "did", "server_id", "role_id", "from_source", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccess_token", "()Ljava/lang/String;", "getActivity_id", "getDid", "getFrom_source", "getProcess_id", "getRole_id", "getServer_id", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GiftExchangeRequest {
    @SerializedName("access_token")
    private final String access_token;
    @SerializedName(ExchangeConstantKt.ACTIVITY_ID)
    private final String activity_id;
    @SerializedName("did")
    private final String did;
    @SerializedName("from_source")
    private final String from_source;
    @SerializedName(ExchangeConstantKt.PROCESS_ID)
    private final String process_id;
    @SerializedName("role_id")
    private final String role_id;
    @SerializedName("server_id")
    private final String server_id;

    public static /* synthetic */ GiftExchangeRequest copy$default(GiftExchangeRequest giftExchangeRequest, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = giftExchangeRequest.activity_id;
        }
        if ((i2 & 2) != 0) {
            str2 = giftExchangeRequest.process_id;
        }
        String str8 = str2;
        if ((i2 & 4) != 0) {
            str3 = giftExchangeRequest.access_token;
        }
        String str9 = str3;
        if ((i2 & 8) != 0) {
            str4 = giftExchangeRequest.did;
        }
        String str10 = str4;
        if ((i2 & 16) != 0) {
            str5 = giftExchangeRequest.server_id;
        }
        String str11 = str5;
        if ((i2 & 32) != 0) {
            str6 = giftExchangeRequest.role_id;
        }
        String str12 = str6;
        if ((i2 & 64) != 0) {
            str7 = giftExchangeRequest.from_source;
        }
        return giftExchangeRequest.copy(str, str8, str9, str10, str11, str12, str7);
    }

    public final String component1() {
        return this.activity_id;
    }

    public final String component2() {
        return this.process_id;
    }

    public final String component3() {
        return this.access_token;
    }

    public final String component4() {
        return this.did;
    }

    public final String component5() {
        return this.server_id;
    }

    public final String component6() {
        return this.role_id;
    }

    public final String component7() {
        return this.from_source;
    }

    public final GiftExchangeRequest copy(String activity_id, String process_id, String access_token, String did, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(activity_id, "activity_id");
        Intrinsics.checkNotNullParameter(process_id, "process_id");
        Intrinsics.checkNotNullParameter(access_token, "access_token");
        Intrinsics.checkNotNullParameter(did, "did");
        return new GiftExchangeRequest(activity_id, process_id, access_token, did, str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GiftExchangeRequest) {
            GiftExchangeRequest giftExchangeRequest = (GiftExchangeRequest) obj;
            return Intrinsics.areEqual(this.activity_id, giftExchangeRequest.activity_id) && Intrinsics.areEqual(this.process_id, giftExchangeRequest.process_id) && Intrinsics.areEqual(this.access_token, giftExchangeRequest.access_token) && Intrinsics.areEqual(this.did, giftExchangeRequest.did) && Intrinsics.areEqual(this.server_id, giftExchangeRequest.server_id) && Intrinsics.areEqual(this.role_id, giftExchangeRequest.role_id) && Intrinsics.areEqual(this.from_source, giftExchangeRequest.from_source);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((((((this.activity_id.hashCode() * 31) + this.process_id.hashCode()) * 31) + this.access_token.hashCode()) * 31) + this.did.hashCode()) * 31;
        String str = this.server_id;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.role_id;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.from_source;
        return hashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "GiftExchangeRequest(activity_id=" + this.activity_id + ", process_id=" + this.process_id + ", access_token=" + this.access_token + ", did=" + this.did + ", server_id=" + this.server_id + ", role_id=" + this.role_id + ", from_source=" + this.from_source + ')';
    }

    public GiftExchangeRequest(String activity_id, String process_id, String access_token, String did, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(activity_id, "activity_id");
        Intrinsics.checkNotNullParameter(process_id, "process_id");
        Intrinsics.checkNotNullParameter(access_token, "access_token");
        Intrinsics.checkNotNullParameter(did, "did");
        this.activity_id = activity_id;
        this.process_id = process_id;
        this.access_token = access_token;
        this.did = did;
        this.server_id = str;
        this.role_id = str2;
        this.from_source = str3;
    }

    public final String getActivity_id() {
        return this.activity_id;
    }

    public final String getProcess_id() {
        return this.process_id;
    }

    public final String getAccess_token() {
        return this.access_token;
    }

    public final String getDid() {
        return this.did;
    }

    public final String getServer_id() {
        return this.server_id;
    }

    public final String getRole_id() {
        return this.role_id;
    }

    public final String getFrom_source() {
        return this.from_source;
    }
}
