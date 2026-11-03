package com.bytedance.ttgame.module.gameinfo.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Models.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/bytedance/ttgame/module/gameinfo/api/ServerList;", "", "zoneList", "", "Lcom/bytedance/ttgame/module/gameinfo/api/ServerZone;", "(Ljava/util/List;)V", "getZoneList", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ServerList {
    @SerializedName("zone_list")
    private final List<ServerZone> zoneList;

    public ServerList() {
        this(null, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ServerList copy$default(ServerList serverList, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = serverList.zoneList;
        }
        return serverList.copy(list);
    }

    public final List<ServerZone> component1() {
        return this.zoneList;
    }

    public final ServerList copy(List<ServerZone> zoneList) {
        Intrinsics.checkNotNullParameter(zoneList, "zoneList");
        return new ServerList(zoneList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ServerList) && Intrinsics.areEqual(this.zoneList, ((ServerList) obj).zoneList);
    }

    public int hashCode() {
        return this.zoneList.hashCode();
    }

    public String toString() {
        return "ServerList(zoneList=" + this.zoneList + ')';
    }

    public ServerList(List<ServerZone> zoneList) {
        Intrinsics.checkNotNullParameter(zoneList, "zoneList");
        this.zoneList = zoneList;
    }

    public /* synthetic */ ServerList(List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final List<ServerZone> getZoneList() {
        return this.zoneList;
    }
}
