package com.bytedance.ttgame.module.gameinfo.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Models.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\t\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/bytedance/ttgame/module/gameinfo/api/RoleList;", "", "roleList", "", "Lcom/bytedance/ttgame/module/gameinfo/api/Role;", "(Ljava/util/List;)V", "getRoleList", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class RoleList {
    @SerializedName("role_list")
    private final List<Role> roleList;

    public RoleList() {
        this(null, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RoleList copy$default(RoleList roleList, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = roleList.roleList;
        }
        return roleList.copy(list);
    }

    public final List<Role> component1() {
        return this.roleList;
    }

    public final RoleList copy(List<Role> list) {
        return new RoleList(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RoleList) && Intrinsics.areEqual(this.roleList, ((RoleList) obj).roleList);
    }

    public int hashCode() {
        List<Role> list = this.roleList;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    public String toString() {
        return "RoleList(roleList=" + this.roleList + ')';
    }

    public RoleList(List<Role> list) {
        this.roleList = list;
    }

    public /* synthetic */ RoleList(List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final List<Role> getRoleList() {
        return this.roleList;
    }
}
