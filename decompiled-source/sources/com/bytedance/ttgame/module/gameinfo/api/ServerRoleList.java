package com.bytedance.ttgame.module.gameinfo.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Models.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J)\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/module/gameinfo/api/ServerRoleList;", "", "serverList", "", "Lcom/bytedance/ttgame/module/gameinfo/api/ServerZone;", "roleList", "Lcom/bytedance/ttgame/module/gameinfo/api/Role;", "(Ljava/util/List;Ljava/util/List;)V", "getRoleList", "()Ljava/util/List;", "getServerList", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ServerRoleList {
    @SerializedName("role_list")
    private final List<Role> roleList;
    @SerializedName("zone_list")
    private final List<ServerZone> serverList;

    public ServerRoleList() {
        this(null, null, 3, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ServerRoleList copy$default(ServerRoleList serverRoleList, List list, List list2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = serverRoleList.serverList;
        }
        if ((i2 & 2) != 0) {
            list2 = serverRoleList.roleList;
        }
        return serverRoleList.copy(list, list2);
    }

    public final List<ServerZone> component1() {
        return this.serverList;
    }

    public final List<Role> component2() {
        return this.roleList;
    }

    public final ServerRoleList copy(List<ServerZone> serverList, List<Role> roleList) {
        Intrinsics.checkNotNullParameter(serverList, "serverList");
        Intrinsics.checkNotNullParameter(roleList, "roleList");
        return new ServerRoleList(serverList, roleList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ServerRoleList) {
            ServerRoleList serverRoleList = (ServerRoleList) obj;
            return Intrinsics.areEqual(this.serverList, serverRoleList.serverList) && Intrinsics.areEqual(this.roleList, serverRoleList.roleList);
        }
        return false;
    }

    public int hashCode() {
        return (this.serverList.hashCode() * 31) + this.roleList.hashCode();
    }

    public String toString() {
        return "ServerRoleList(serverList=" + this.serverList + ", roleList=" + this.roleList + ')';
    }

    public ServerRoleList(List<ServerZone> serverList, List<Role> roleList) {
        Intrinsics.checkNotNullParameter(serverList, "serverList");
        Intrinsics.checkNotNullParameter(roleList, "roleList");
        this.serverList = serverList;
        this.roleList = roleList;
    }

    public /* synthetic */ ServerRoleList(List list, List list2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? CollectionsKt.emptyList() : list, (i2 & 2) != 0 ? CollectionsKt.emptyList() : list2);
    }

    public final List<ServerZone> getServerList() {
        return this.serverList;
    }

    public final List<Role> getRoleList() {
        return this.roleList;
    }
}
