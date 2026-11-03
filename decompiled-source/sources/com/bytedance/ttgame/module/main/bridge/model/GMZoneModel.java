package com.bytedance.ttgame.module.main.bridge.model;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GMGameInfoModels.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0002\u0010\rJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0016J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\u0011\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0003Jh\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0005HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001f\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006&"}, d2 = {"Lcom/bytedance/ttgame/module/main/bridge/model/GMZoneModel;", "", "zoneName", "", "zoneId", "", "channelId", "extraInfo", "extraKV", "", "servers", "", "Lcom/bytedance/ttgame/module/main/bridge/model/GMServerModel;", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/List;)V", "getChannelId", "()Ljava/lang/String;", "getExtraInfo", "getExtraKV", "()Ljava/util/Map;", "getServers", "()Ljava/util/List;", "getZoneId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getZoneName", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/List;)Lcom/bytedance/ttgame/module/main/bridge/model/GMZoneModel;", "equals", "", "other", "hashCode", "toString", "main_bridge_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class GMZoneModel {
    private final String channelId;
    private final String extraInfo;
    private final Map<String, String> extraKV;
    private final List<GMServerModel> servers;
    private final Integer zoneId;
    private final String zoneName;

    public static /* synthetic */ GMZoneModel copy$default(GMZoneModel gMZoneModel, String str, Integer num, String str2, String str3, Map map, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = gMZoneModel.zoneName;
        }
        if ((i2 & 2) != 0) {
            num = gMZoneModel.zoneId;
        }
        Integer num2 = num;
        if ((i2 & 4) != 0) {
            str2 = gMZoneModel.channelId;
        }
        String str4 = str2;
        if ((i2 & 8) != 0) {
            str3 = gMZoneModel.extraInfo;
        }
        String str5 = str3;
        Map<String, String> map2 = map;
        if ((i2 & 16) != 0) {
            map2 = gMZoneModel.extraKV;
        }
        Map map3 = map2;
        List<GMServerModel> list2 = list;
        if ((i2 & 32) != 0) {
            list2 = gMZoneModel.servers;
        }
        return gMZoneModel.copy(str, num2, str4, str5, map3, list2);
    }

    public final String component1() {
        return this.zoneName;
    }

    public final Integer component2() {
        return this.zoneId;
    }

    public final String component3() {
        return this.channelId;
    }

    public final String component4() {
        return this.extraInfo;
    }

    public final Map<String, String> component5() {
        return this.extraKV;
    }

    public final List<GMServerModel> component6() {
        return this.servers;
    }

    public final GMZoneModel copy(String str, Integer num, String str2, String str3, Map<String, String> map, List<GMServerModel> list) {
        return new GMZoneModel(str, num, str2, str3, map, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GMZoneModel) {
            GMZoneModel gMZoneModel = (GMZoneModel) obj;
            return Intrinsics.areEqual(this.zoneName, gMZoneModel.zoneName) && Intrinsics.areEqual(this.zoneId, gMZoneModel.zoneId) && Intrinsics.areEqual(this.channelId, gMZoneModel.channelId) && Intrinsics.areEqual(this.extraInfo, gMZoneModel.extraInfo) && Intrinsics.areEqual(this.extraKV, gMZoneModel.extraKV) && Intrinsics.areEqual(this.servers, gMZoneModel.servers);
        }
        return false;
    }

    public int hashCode() {
        String str = this.zoneName;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.zoneId;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.channelId;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.extraInfo;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Map<String, String> map = this.extraKV;
        int hashCode5 = (hashCode4 + (map == null ? 0 : map.hashCode())) * 31;
        List<GMServerModel> list = this.servers;
        return hashCode5 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "GMZoneModel(zoneName=" + this.zoneName + ", zoneId=" + this.zoneId + ", channelId=" + this.channelId + ", extraInfo=" + this.extraInfo + ", extraKV=" + this.extraKV + ", servers=" + this.servers + ')';
    }

    public GMZoneModel(String str, Integer num, String str2, String str3, Map<String, String> map, List<GMServerModel> list) {
        this.zoneName = str;
        this.zoneId = num;
        this.channelId = str2;
        this.extraInfo = str3;
        this.extraKV = map;
        this.servers = list;
    }

    public final String getZoneName() {
        return this.zoneName;
    }

    public final Integer getZoneId() {
        return this.zoneId;
    }

    public final String getChannelId() {
        return this.channelId;
    }

    public final String getExtraInfo() {
        return this.extraInfo;
    }

    public final Map<String, String> getExtraKV() {
        return this.extraKV;
    }

    public final List<GMServerModel> getServers() {
        return this.servers;
    }
}
