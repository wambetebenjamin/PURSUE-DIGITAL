package com.bytedance.ttgame.module.gameinfo.api;

import com.bytedance.sdk.account.save.database.a;
import com.google.gson.annotations.SerializedName;
import gsdk.impl.account.toutiao.f;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Models.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\bHÆ\u0003J\t\u0010 \u001a\u00020\nHÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003Jc\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\bHÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010¨\u0006*"}, d2 = {"Lcom/bytedance/ttgame/module/gameinfo/api/Role;", "", "roleId", "", "name", "level", "serverName", "realServerId", "", f.r, "", "avatarUrl", "job", "extra", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAvatarUrl", "()Ljava/lang/String;", "getExtra", "getJob", "getLevel", "getLoginTime", "()J", "getName", "getRealServerId", "()I", "getRoleId", "getServerName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class Role {
    @SerializedName(a.FIELD_AVATAR_URL)
    private final String avatarUrl;
    @SerializedName("extra")
    private final String extra;
    @SerializedName("job")
    private final String job;
    @SerializedName("level")
    private final String level;
    @SerializedName("login_time")
    private final long loginTime;
    @SerializedName("name")
    private final String name;
    @SerializedName("real_server_id")
    private final int realServerId;
    @SerializedName("role_id")
    private final String roleId;
    @SerializedName("server_name")
    private final String serverName;

    public Role() {
        this(null, null, null, null, 0, 0L, null, null, null, 511, null);
    }

    public final String component1() {
        return this.roleId;
    }

    public final String component2() {
        return this.name;
    }

    public final String component3() {
        return this.level;
    }

    public final String component4() {
        return this.serverName;
    }

    public final int component5() {
        return this.realServerId;
    }

    public final long component6() {
        return this.loginTime;
    }

    public final String component7() {
        return this.avatarUrl;
    }

    public final String component8() {
        return this.job;
    }

    public final String component9() {
        return this.extra;
    }

    public final Role copy(String roleId, String name, String level, String serverName, int i2, long j2, String avatarUrl, String job, String extra) {
        Intrinsics.checkNotNullParameter(roleId, "roleId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(serverName, "serverName");
        Intrinsics.checkNotNullParameter(avatarUrl, "avatarUrl");
        Intrinsics.checkNotNullParameter(job, "job");
        Intrinsics.checkNotNullParameter(extra, "extra");
        return new Role(roleId, name, level, serverName, i2, j2, avatarUrl, job, extra);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Role) {
            Role role = (Role) obj;
            return Intrinsics.areEqual(this.roleId, role.roleId) && Intrinsics.areEqual(this.name, role.name) && Intrinsics.areEqual(this.level, role.level) && Intrinsics.areEqual(this.serverName, role.serverName) && this.realServerId == role.realServerId && this.loginTime == role.loginTime && Intrinsics.areEqual(this.avatarUrl, role.avatarUrl) && Intrinsics.areEqual(this.job, role.job) && Intrinsics.areEqual(this.extra, role.extra);
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((this.roleId.hashCode() * 31) + this.name.hashCode()) * 31) + this.level.hashCode()) * 31) + this.serverName.hashCode()) * 31) + Integer.hashCode(this.realServerId)) * 31) + Long.hashCode(this.loginTime)) * 31) + this.avatarUrl.hashCode()) * 31) + this.job.hashCode()) * 31) + this.extra.hashCode();
    }

    public String toString() {
        return "Role(roleId=" + this.roleId + ", name=" + this.name + ", level=" + this.level + ", serverName=" + this.serverName + ", realServerId=" + this.realServerId + ", loginTime=" + this.loginTime + ", avatarUrl=" + this.avatarUrl + ", job=" + this.job + ", extra=" + this.extra + ')';
    }

    public Role(String roleId, String name, String level, String serverName, int i2, long j2, String avatarUrl, String job, String extra) {
        Intrinsics.checkNotNullParameter(roleId, "roleId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(serverName, "serverName");
        Intrinsics.checkNotNullParameter(avatarUrl, "avatarUrl");
        Intrinsics.checkNotNullParameter(job, "job");
        Intrinsics.checkNotNullParameter(extra, "extra");
        this.roleId = roleId;
        this.name = name;
        this.level = level;
        this.serverName = serverName;
        this.realServerId = i2;
        this.loginTime = j2;
        this.avatarUrl = avatarUrl;
        this.job = job;
        this.extra = extra;
    }

    public /* synthetic */ Role(String str, String str2, String str3, String str4, int i2, long j2, String str5, String str6, String str7, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? "" : str2, (i3 & 4) != 0 ? "" : str3, (i3 & 8) != 0 ? "" : str4, (i3 & 16) != 0 ? 0 : i2, (i3 & 32) != 0 ? 0L : j2, (i3 & 64) != 0 ? "" : str5, (i3 & 128) != 0 ? "" : str6, (i3 & 256) == 0 ? str7 : "");
    }

    public final String getRoleId() {
        return this.roleId;
    }

    public final String getName() {
        return this.name;
    }

    public final String getLevel() {
        return this.level;
    }

    public final String getServerName() {
        return this.serverName;
    }

    public final int getRealServerId() {
        return this.realServerId;
    }

    public final long getLoginTime() {
        return this.loginTime;
    }

    public final String getAvatarUrl() {
        return this.avatarUrl;
    }

    public final String getJob() {
        return this.job;
    }

    public final String getExtra() {
        return this.extra;
    }
}
