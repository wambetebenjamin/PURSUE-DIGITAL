package com.bytedance.ttgame.channel.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AccountWithSamePuidInfoResponse.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u001c\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010!\u001a\u00020\fHÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jq\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010$\u001a\u00020\u00062\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\fHÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000fR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011¨\u0006("}, d2 = {"Lcom/bytedance/ttgame/channel/entity/OSAccountItem;", "", "os", "", "sdkOpenId", "isCurrentLogin", "", "roleId", "roleName", "roleLevel", "roleAvatar", "serverId", "", "serverName", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "()Z", "getOs", "()Ljava/lang/String;", "getRoleAvatar", "getRoleId", "getRoleLevel", "getRoleName", "getSdkOpenId", "getServerId", "()I", "getServerName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "account_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OSAccountItem {
    @SerializedName("is_current_login")
    private final boolean isCurrentLogin;
    @SerializedName("os")
    private final String os;
    @SerializedName("role_avatar")
    private final String roleAvatar;
    @SerializedName("role_id")
    private final String roleId;
    @SerializedName("role_level")
    private final String roleLevel;
    @SerializedName("role_name")
    private final String roleName;
    @SerializedName("sdk_open_id")
    private final String sdkOpenId;
    @SerializedName("server_id")
    private final int serverId;
    @SerializedName("server_name")
    private final String serverName;

    public final String component1() {
        return this.os;
    }

    public final String component2() {
        return this.sdkOpenId;
    }

    public final boolean component3() {
        return this.isCurrentLogin;
    }

    public final String component4() {
        return this.roleId;
    }

    public final String component5() {
        return this.roleName;
    }

    public final String component6() {
        return this.roleLevel;
    }

    public final String component7() {
        return this.roleAvatar;
    }

    public final int component8() {
        return this.serverId;
    }

    public final String component9() {
        return this.serverName;
    }

    public final OSAccountItem copy(String str, String str2, boolean z, String str3, String str4, String str5, String str6, int i2, String str7) {
        return new OSAccountItem(str, str2, z, str3, str4, str5, str6, i2, str7);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OSAccountItem) {
            OSAccountItem oSAccountItem = (OSAccountItem) obj;
            return Intrinsics.areEqual(this.os, oSAccountItem.os) && Intrinsics.areEqual(this.sdkOpenId, oSAccountItem.sdkOpenId) && this.isCurrentLogin == oSAccountItem.isCurrentLogin && Intrinsics.areEqual(this.roleId, oSAccountItem.roleId) && Intrinsics.areEqual(this.roleName, oSAccountItem.roleName) && Intrinsics.areEqual(this.roleLevel, oSAccountItem.roleLevel) && Intrinsics.areEqual(this.roleAvatar, oSAccountItem.roleAvatar) && this.serverId == oSAccountItem.serverId && Intrinsics.areEqual(this.serverName, oSAccountItem.serverName);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.os;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.sdkOpenId;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        boolean z = this.isCurrentLogin;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int i3 = (hashCode2 + i2) * 31;
        String str3 = this.roleId;
        int hashCode3 = (i3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.roleName;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.roleLevel;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.roleAvatar;
        int hashCode6 = (((hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31) + Integer.hashCode(this.serverId)) * 31;
        String str7 = this.serverName;
        return hashCode6 + (str7 != null ? str7.hashCode() : 0);
    }

    public String toString() {
        return "OSAccountItem(os=" + this.os + ", sdkOpenId=" + this.sdkOpenId + ", isCurrentLogin=" + this.isCurrentLogin + ", roleId=" + this.roleId + ", roleName=" + this.roleName + ", roleLevel=" + this.roleLevel + ", roleAvatar=" + this.roleAvatar + ", serverId=" + this.serverId + ", serverName=" + this.serverName + ')';
    }

    public OSAccountItem(String str, String str2, boolean z, String str3, String str4, String str5, String str6, int i2, String str7) {
        this.os = str;
        this.sdkOpenId = str2;
        this.isCurrentLogin = z;
        this.roleId = str3;
        this.roleName = str4;
        this.roleLevel = str5;
        this.roleAvatar = str6;
        this.serverId = i2;
        this.serverName = str7;
    }

    public final String getOs() {
        return this.os;
    }

    public final String getSdkOpenId() {
        return this.sdkOpenId;
    }

    public final boolean isCurrentLogin() {
        return this.isCurrentLogin;
    }

    public final String getRoleId() {
        return this.roleId;
    }

    public final String getRoleName() {
        return this.roleName;
    }

    public final String getRoleLevel() {
        return this.roleLevel;
    }

    public final String getRoleAvatar() {
        return this.roleAvatar;
    }

    public final int getServerId() {
        return this.serverId;
    }

    public final String getServerName() {
        return this.serverName;
    }
}
