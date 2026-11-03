package com.bytedance.ttgame.module.cdkey.api;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Models.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000bJ2\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\tR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u0010R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u0010¨\u0006\""}, d2 = {"Lcom/bytedance/ttgame/module/cdkey/api/GMUserInfo;", "", "activityId", "", "processId", "fromH5", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getActivityId", "()Ljava/lang/String;", "getFromH5", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "fromSource", "getFromSource", "setFromSource", "(Ljava/lang/String;)V", "getProcessId", "roleID", "getRoleID", "setRoleID", "serverId", "getServerId", "setServerId", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/bytedance/ttgame/module/cdkey/api/GMUserInfo;", "equals", "other", "hashCode", "", "toString", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GMUserInfo {
    private final String activityId;
    private final Boolean fromH5;
    private String fromSource;
    private final String processId;
    private String roleID;
    private String serverId;

    public static /* synthetic */ GMUserInfo copy$default(GMUserInfo gMUserInfo, String str, String str2, Boolean bool, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = gMUserInfo.activityId;
        }
        if ((i2 & 2) != 0) {
            str2 = gMUserInfo.processId;
        }
        if ((i2 & 4) != 0) {
            bool = gMUserInfo.fromH5;
        }
        return gMUserInfo.copy(str, str2, bool);
    }

    public final String component1() {
        return this.activityId;
    }

    public final String component2() {
        return this.processId;
    }

    public final Boolean component3() {
        return this.fromH5;
    }

    public final GMUserInfo copy(String str, String str2, Boolean bool) {
        return new GMUserInfo(str, str2, bool);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GMUserInfo) {
            GMUserInfo gMUserInfo = (GMUserInfo) obj;
            return Intrinsics.areEqual(this.activityId, gMUserInfo.activityId) && Intrinsics.areEqual(this.processId, gMUserInfo.processId) && Intrinsics.areEqual(this.fromH5, gMUserInfo.fromH5);
        }
        return false;
    }

    public int hashCode() {
        String str = this.activityId;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.processId;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.fromH5;
        return hashCode2 + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "GMUserInfo(activityId=" + this.activityId + ", processId=" + this.processId + ", fromH5=" + this.fromH5 + ')';
    }

    public GMUserInfo(String str, String str2, Boolean bool) {
        this.activityId = str;
        this.processId = str2;
        this.fromH5 = bool;
        this.serverId = "";
        this.roleID = "";
        this.fromSource = "";
    }

    public final String getActivityId() {
        return this.activityId;
    }

    public final String getProcessId() {
        return this.processId;
    }

    public /* synthetic */ GMUserInfo(String str, String str2, Boolean bool, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i2 & 4) != 0 ? false : bool);
    }

    public final Boolean getFromH5() {
        return this.fromH5;
    }

    public final String getServerId() {
        return this.serverId;
    }

    public final void setServerId(String str) {
        this.serverId = str;
    }

    public final String getRoleID() {
        return this.roleID;
    }

    public final void setRoleID(String str) {
        this.roleID = str;
    }

    public final String getFromSource() {
        return this.fromSource;
    }

    public final void setFromSource(String str) {
        this.fromSource = str;
    }
}
