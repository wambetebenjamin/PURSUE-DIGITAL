package com.bytedance.ttgame.module.main.bridge.model;

import gsdk.impl.account.toutiao.f;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GMGameInfoModels.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0017J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0014J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jz\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010'J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020\u0007HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010¨\u0006-"}, d2 = {"Lcom/bytedance/ttgame/module/main/bridge/model/GMRoleModel;", "", "roleId", "", "roleName", "roleLevel", "realServerId", "", "serverName", f.r, "", "avatarUrl", "job", "extra", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAvatarUrl", "()Ljava/lang/String;", "getExtra", "getJob", "getLoginTime", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRealServerId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getRoleId", "getRoleLevel", "getRoleName", "getServerName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/bytedance/ttgame/module/main/bridge/model/GMRoleModel;", "equals", "", "other", "hashCode", "toString", "main_bridge_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class GMRoleModel {
    private final String avatarUrl;
    private final String extra;
    private final String job;
    private final Long loginTime;
    private final Integer realServerId;
    private final String roleId;
    private final String roleLevel;
    private final String roleName;
    private final String serverName;

    public final String component1() {
        return this.roleId;
    }

    public final String component2() {
        return this.roleName;
    }

    public final String component3() {
        return this.roleLevel;
    }

    public final Integer component4() {
        return this.realServerId;
    }

    public final String component5() {
        return this.serverName;
    }

    public final Long component6() {
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

    public final GMRoleModel copy(String str, String str2, String str3, Integer num, String str4, Long l, String str5, String str6, String str7) {
        return new GMRoleModel(str, str2, str3, num, str4, l, str5, str6, str7);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GMRoleModel) {
            GMRoleModel gMRoleModel = (GMRoleModel) obj;
            return Intrinsics.areEqual(this.roleId, gMRoleModel.roleId) && Intrinsics.areEqual(this.roleName, gMRoleModel.roleName) && Intrinsics.areEqual(this.roleLevel, gMRoleModel.roleLevel) && Intrinsics.areEqual(this.realServerId, gMRoleModel.realServerId) && Intrinsics.areEqual(this.serverName, gMRoleModel.serverName) && Intrinsics.areEqual(this.loginTime, gMRoleModel.loginTime) && Intrinsics.areEqual(this.avatarUrl, gMRoleModel.avatarUrl) && Intrinsics.areEqual(this.job, gMRoleModel.job) && Intrinsics.areEqual(this.extra, gMRoleModel.extra);
        }
        return false;
    }

    public int hashCode() {
        String str = this.roleId;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.roleName;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.roleLevel;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num = this.realServerId;
        int hashCode4 = (hashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        String str4 = this.serverName;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Long l = this.loginTime;
        int hashCode6 = (hashCode5 + (l == null ? 0 : l.hashCode())) * 31;
        String str5 = this.avatarUrl;
        int hashCode7 = (hashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.job;
        int hashCode8 = (hashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.extra;
        return hashCode8 + (str7 != null ? str7.hashCode() : 0);
    }

    public String toString() {
        return "GMRoleModel(roleId=" + this.roleId + ", roleName=" + this.roleName + ", roleLevel=" + this.roleLevel + ", realServerId=" + this.realServerId + ", serverName=" + this.serverName + ", loginTime=" + this.loginTime + ", avatarUrl=" + this.avatarUrl + ", job=" + this.job + ", extra=" + this.extra + ')';
    }

    public GMRoleModel(String str, String str2, String str3, Integer num, String str4, Long l, String str5, String str6, String str7) {
        this.roleId = str;
        this.roleName = str2;
        this.roleLevel = str3;
        this.realServerId = num;
        this.serverName = str4;
        this.loginTime = l;
        this.avatarUrl = str5;
        this.job = str6;
        this.extra = str7;
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

    public final Integer getRealServerId() {
        return this.realServerId;
    }

    public final String getServerName() {
        return this.serverName;
    }

    public final Long getLoginTime() {
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
