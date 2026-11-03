package com.vk.api.sdk.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.bytedance.sdk.account.platform.base.i;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: BanInfo.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \"2\u00020\u0001:\u0001\"BA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003JE\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u0016HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0016H\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006#"}, d2 = {"Lcom/vk/api/sdk/auth/BanInfo;", "Landroid/os/Parcelable;", "memberName", "", "message", i.CT_KEY_ACCESS_TOKEN, "secret", "restoreUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccessToken", "()Ljava/lang/String;", "getMemberName", "getMessage", "getRestoreUrl", "getSecret", "component1", "component2", "component3", "component4", "component5", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "s", "Landroid/os/Parcel;", "flags", "Companion", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class BanInfo implements Parcelable {
    private final String accessToken;
    private final String memberName;
    private final String message;
    private final String restoreUrl;
    private final String secret;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<BanInfo> CREATOR = new Parcelable.Creator<BanInfo>() { // from class: com.vk.api.sdk.auth.BanInfo$Companion$CREATOR$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BanInfo createFromParcel(Parcel source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            return new BanInfo(source.readString(), source.readString(), source.readString(), source.readString(), source.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BanInfo[] newArray(int i2) {
            return new BanInfo[i2];
        }
    };

    public BanInfo() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ BanInfo copy$default(BanInfo banInfo, String str, String str2, String str3, String str4, String str5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = banInfo.memberName;
        }
        if ((i2 & 2) != 0) {
            str2 = banInfo.message;
        }
        String str6 = str2;
        if ((i2 & 4) != 0) {
            str3 = banInfo.accessToken;
        }
        String str7 = str3;
        if ((i2 & 8) != 0) {
            str4 = banInfo.secret;
        }
        String str8 = str4;
        if ((i2 & 16) != 0) {
            str5 = banInfo.restoreUrl;
        }
        return banInfo.copy(str, str6, str7, str8, str5);
    }

    @JvmStatic
    public static final BanInfo parse(JSONObject jSONObject) {
        return Companion.parse(jSONObject);
    }

    public final String component1() {
        return this.memberName;
    }

    public final String component2() {
        return this.message;
    }

    public final String component3() {
        return this.accessToken;
    }

    public final String component4() {
        return this.secret;
    }

    public final String component5() {
        return this.restoreUrl;
    }

    public final BanInfo copy(String str, String str2, String str3, String str4, String str5) {
        return new BanInfo(str, str2, str3, str4, str5);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BanInfo) {
                BanInfo banInfo = (BanInfo) obj;
                return Intrinsics.areEqual(this.memberName, banInfo.memberName) && Intrinsics.areEqual(this.message, banInfo.message) && Intrinsics.areEqual(this.accessToken, banInfo.accessToken) && Intrinsics.areEqual(this.secret, banInfo.secret) && Intrinsics.areEqual(this.restoreUrl, banInfo.restoreUrl);
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.memberName;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.message;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.accessToken;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.secret;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.restoreUrl;
        return hashCode4 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "BanInfo(memberName=" + this.memberName + ", message=" + this.message + ", accessToken=" + this.accessToken + ", secret=" + this.secret + ", restoreUrl=" + this.restoreUrl + ")";
    }

    public BanInfo(String str, String str2, String str3, String str4, String str5) {
        this.memberName = str;
        this.message = str2;
        this.accessToken = str3;
        this.secret = str4;
        this.restoreUrl = str5;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ BanInfo(java.lang.String r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, int r9, kotlin.jvm.internal.DefaultConstructorMarker r10) {
        /*
            r3 = this;
            r10 = r9 & 1
            r0 = 0
            if (r10 == 0) goto L9
            r4 = r0
            java.lang.String r4 = (java.lang.String) r4
            r4 = r0
        L9:
            r10 = r9 & 2
            if (r10 == 0) goto L12
            r5 = r0
            java.lang.String r5 = (java.lang.String) r5
            r10 = r0
            goto L13
        L12:
            r10 = r5
        L13:
            r5 = r9 & 4
            if (r5 == 0) goto L1c
            r5 = r0
            java.lang.String r5 = (java.lang.String) r5
            r1 = r0
            goto L1d
        L1c:
            r1 = r6
        L1d:
            r5 = r9 & 8
            if (r5 == 0) goto L26
            r5 = r0
            java.lang.String r5 = (java.lang.String) r5
            r2 = r0
            goto L27
        L26:
            r2 = r7
        L27:
            r5 = r9 & 16
            if (r5 == 0) goto L2f
            r5 = r0
            java.lang.String r5 = (java.lang.String) r5
            goto L30
        L2f:
            r0 = r8
        L30:
            r5 = r3
            r6 = r4
            r7 = r10
            r8 = r1
            r9 = r2
            r10 = r0
            r5.<init>(r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vk.api.sdk.auth.BanInfo.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getMemberName() {
        return this.memberName;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final String getSecret() {
        return this.secret;
    }

    public final String getRestoreUrl() {
        return this.restoreUrl;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel s, int i2) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        s.writeString(this.memberName);
        s.writeString(this.message);
        s.writeString(this.accessToken);
        s.writeString(this.secret);
        s.writeString(this.restoreUrl);
    }

    /* compiled from: BanInfo.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/vk/api/sdk/auth/BanInfo$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/vk/api/sdk/auth/BanInfo;", "parse", "json", "Lorg/json/JSONObject;", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final BanInfo parse(JSONObject json) {
            Intrinsics.checkParameterIsNotNull(json, "json");
            return new BanInfo(json.optString("member_name"), json.optString("message"), json.optString("access_token"), json.optString("secret"), json.optString("restore_url"));
        }
    }
}
