package com.bytedance.ttgame.module.main.bridge.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GMGameInfoModels.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u0011\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J-\u0010\r\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/module/main/bridge/model/GMServerAndRoleModel;", "", "zones", "", "Lcom/bytedance/ttgame/module/main/bridge/model/GMZoneModel;", "roles", "Lcom/bytedance/ttgame/module/main/bridge/model/GMRoleModel;", "(Ljava/util/List;Ljava/util/List;)V", "getRoles", "()Ljava/util/List;", "getZones", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "main_bridge_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class GMServerAndRoleModel {
    private final List<GMRoleModel> roles;
    private final List<GMZoneModel> zones;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GMServerAndRoleModel copy$default(GMServerAndRoleModel gMServerAndRoleModel, List list, List list2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = gMServerAndRoleModel.zones;
        }
        if ((i2 & 2) != 0) {
            list2 = gMServerAndRoleModel.roles;
        }
        return gMServerAndRoleModel.copy(list, list2);
    }

    public final List<GMZoneModel> component1() {
        return this.zones;
    }

    public final List<GMRoleModel> component2() {
        return this.roles;
    }

    public final GMServerAndRoleModel copy(List<GMZoneModel> list, List<GMRoleModel> list2) {
        return new GMServerAndRoleModel(list, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GMServerAndRoleModel) {
            GMServerAndRoleModel gMServerAndRoleModel = (GMServerAndRoleModel) obj;
            return Intrinsics.areEqual(this.zones, gMServerAndRoleModel.zones) && Intrinsics.areEqual(this.roles, gMServerAndRoleModel.roles);
        }
        return false;
    }

    public int hashCode() {
        List<GMZoneModel> list = this.zones;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<GMRoleModel> list2 = this.roles;
        return hashCode + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "GMServerAndRoleModel(zones=" + this.zones + ", roles=" + this.roles + ')';
    }

    public GMServerAndRoleModel(List<GMZoneModel> list, List<GMRoleModel> list2) {
        this.zones = list;
        this.roles = list2;
    }

    public final List<GMZoneModel> getZones() {
        return this.zones;
    }

    public final List<GMRoleModel> getRoles() {
        return this.roles;
    }
}
