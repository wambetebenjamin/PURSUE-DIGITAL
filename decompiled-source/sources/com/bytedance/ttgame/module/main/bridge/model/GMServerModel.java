package com.bytedance.ttgame.module.main.bridge.model;

import com.bytedance.sdk.account.save.database.a;
import com.bytedance.tracing.internal.e;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GMGameInfoModels.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b,\b\u0086\b\u0018\u00002\u00020\u0001B£\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0011\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fHÆ\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0017\u0010.\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0013HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000b\u00101\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000b\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u00106\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u001bJ\u0010\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000b\u00108\u001a\u0004\u0018\u00010\u0005HÆ\u0003JÈ\u0001\u00109\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010:J\u0013\u0010;\u001a\u00020\u000b2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010=\u001a\u00020\u0003HÖ\u0001J\t\u0010>\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001f\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\n\u0010\u001bR\u0015\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b \u0010\u001eR\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b!\u0010\u001eR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b#\u0010\u001eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b%\u0010\u001eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0018R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b'\u0010\u001eR\u0019\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b*\u0010\u001e¨\u0006?"}, d2 = {"Lcom/bytedance/ttgame/module/main/bridge/model/GMServerModel;", "", "serverId", "", "serverName", "", "serverType", "serverEntry", "opStatus", "realServerId", "isMerged", "", "onlineLoad", "extraInfo", e.KEY_TAGS, "", "Lcom/bytedance/ttgame/module/main/bridge/model/GMServerTagModel;", "openTimestamp", "extraKV", "", "pingAddr", a.FIELD_TIME, "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Integer;)V", "getExtraInfo", "()Ljava/lang/String;", "getExtraKV", "()Ljava/util/Map;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getOnlineLoad", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getOpStatus", "getOpenTimestamp", "getPingAddr", "getRealServerId", "getServerEntry", "getServerId", "getServerName", "getServerType", "getTags", "()Ljava/util/List;", "getTime", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Integer;)Lcom/bytedance/ttgame/module/main/bridge/model/GMServerModel;", "equals", "other", "hashCode", "toString", "main_bridge_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class GMServerModel {
    private final String extraInfo;
    private final Map<String, String> extraKV;
    private final Boolean isMerged;
    private final Integer onlineLoad;
    private final Integer opStatus;
    private final Integer openTimestamp;
    private final String pingAddr;
    private final Integer realServerId;
    private final String serverEntry;
    private final Integer serverId;
    private final String serverName;
    private final Integer serverType;
    private final List<GMServerTagModel> tags;
    private final Integer time;

    public final Integer component1() {
        return this.serverId;
    }

    public final List<GMServerTagModel> component10() {
        return this.tags;
    }

    public final Integer component11() {
        return this.openTimestamp;
    }

    public final Map<String, String> component12() {
        return this.extraKV;
    }

    public final String component13() {
        return this.pingAddr;
    }

    public final Integer component14() {
        return this.time;
    }

    public final String component2() {
        return this.serverName;
    }

    public final Integer component3() {
        return this.serverType;
    }

    public final String component4() {
        return this.serverEntry;
    }

    public final Integer component5() {
        return this.opStatus;
    }

    public final Integer component6() {
        return this.realServerId;
    }

    public final Boolean component7() {
        return this.isMerged;
    }

    public final Integer component8() {
        return this.onlineLoad;
    }

    public final String component9() {
        return this.extraInfo;
    }

    public final GMServerModel copy(Integer num, String str, Integer num2, String str2, Integer num3, Integer num4, Boolean bool, Integer num5, String str3, List<GMServerTagModel> list, Integer num6, Map<String, String> map, String str4, Integer num7) {
        return new GMServerModel(num, str, num2, str2, num3, num4, bool, num5, str3, list, num6, map, str4, num7);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GMServerModel) {
            GMServerModel gMServerModel = (GMServerModel) obj;
            return Intrinsics.areEqual(this.serverId, gMServerModel.serverId) && Intrinsics.areEqual(this.serverName, gMServerModel.serverName) && Intrinsics.areEqual(this.serverType, gMServerModel.serverType) && Intrinsics.areEqual(this.serverEntry, gMServerModel.serverEntry) && Intrinsics.areEqual(this.opStatus, gMServerModel.opStatus) && Intrinsics.areEqual(this.realServerId, gMServerModel.realServerId) && Intrinsics.areEqual(this.isMerged, gMServerModel.isMerged) && Intrinsics.areEqual(this.onlineLoad, gMServerModel.onlineLoad) && Intrinsics.areEqual(this.extraInfo, gMServerModel.extraInfo) && Intrinsics.areEqual(this.tags, gMServerModel.tags) && Intrinsics.areEqual(this.openTimestamp, gMServerModel.openTimestamp) && Intrinsics.areEqual(this.extraKV, gMServerModel.extraKV) && Intrinsics.areEqual(this.pingAddr, gMServerModel.pingAddr) && Intrinsics.areEqual(this.time, gMServerModel.time);
        }
        return false;
    }

    public int hashCode() {
        Integer num = this.serverId;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.serverName;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num2 = this.serverType;
        int hashCode3 = (hashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str2 = this.serverEntry;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num3 = this.opStatus;
        int hashCode5 = (hashCode4 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.realServerId;
        int hashCode6 = (hashCode5 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Boolean bool = this.isMerged;
        int hashCode7 = (hashCode6 + (bool == null ? 0 : bool.hashCode())) * 31;
        Integer num5 = this.onlineLoad;
        int hashCode8 = (hashCode7 + (num5 == null ? 0 : num5.hashCode())) * 31;
        String str3 = this.extraInfo;
        int hashCode9 = (hashCode8 + (str3 == null ? 0 : str3.hashCode())) * 31;
        List<GMServerTagModel> list = this.tags;
        int hashCode10 = (hashCode9 + (list == null ? 0 : list.hashCode())) * 31;
        Integer num6 = this.openTimestamp;
        int hashCode11 = (hashCode10 + (num6 == null ? 0 : num6.hashCode())) * 31;
        Map<String, String> map = this.extraKV;
        int hashCode12 = (hashCode11 + (map == null ? 0 : map.hashCode())) * 31;
        String str4 = this.pingAddr;
        int hashCode13 = (hashCode12 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Integer num7 = this.time;
        return hashCode13 + (num7 != null ? num7.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GMServerModel(serverId=").append(this.serverId).append(", serverName=").append(this.serverName).append(", serverType=").append(this.serverType).append(", serverEntry=").append(this.serverEntry).append(", opStatus=").append(this.opStatus).append(", realServerId=").append(this.realServerId).append(", isMerged=").append(this.isMerged).append(", onlineLoad=").append(this.onlineLoad).append(", extraInfo=").append(this.extraInfo).append(", tags=").append(this.tags).append(", openTimestamp=").append(this.openTimestamp).append(", extraKV=");
        sb.append(this.extraKV).append(", pingAddr=").append(this.pingAddr).append(", time=").append(this.time).append(')');
        return sb.toString();
    }

    public GMServerModel(Integer num, String str, Integer num2, String str2, Integer num3, Integer num4, Boolean bool, Integer num5, String str3, List<GMServerTagModel> list, Integer num6, Map<String, String> map, String str4, Integer num7) {
        this.serverId = num;
        this.serverName = str;
        this.serverType = num2;
        this.serverEntry = str2;
        this.opStatus = num3;
        this.realServerId = num4;
        this.isMerged = bool;
        this.onlineLoad = num5;
        this.extraInfo = str3;
        this.tags = list;
        this.openTimestamp = num6;
        this.extraKV = map;
        this.pingAddr = str4;
        this.time = num7;
    }

    public final Integer getServerId() {
        return this.serverId;
    }

    public final String getServerName() {
        return this.serverName;
    }

    public final Integer getServerType() {
        return this.serverType;
    }

    public final String getServerEntry() {
        return this.serverEntry;
    }

    public final Integer getOpStatus() {
        return this.opStatus;
    }

    public final Integer getRealServerId() {
        return this.realServerId;
    }

    public final Boolean isMerged() {
        return this.isMerged;
    }

    public final Integer getOnlineLoad() {
        return this.onlineLoad;
    }

    public final String getExtraInfo() {
        return this.extraInfo;
    }

    public final List<GMServerTagModel> getTags() {
        return this.tags;
    }

    public final Integer getOpenTimestamp() {
        return this.openTimestamp;
    }

    public final Map<String, String> getExtraKV() {
        return this.extraKV;
    }

    public final String getPingAddr() {
        return this.pingAddr;
    }

    public final Integer getTime() {
        return this.time;
    }
}
