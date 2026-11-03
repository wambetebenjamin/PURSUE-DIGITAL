package com.bytedance.ttgame.module.compliance.impl.agegate.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ServerTimestampResponse.kt */
/* loaded from: classes5.dex */
public final class ServerData {
    @SerializedName("ipv4")
    private final String ipv4;
    @SerializedName("ipv6")
    private final String ipv6;
    @SerializedName("timestamp")
    private final long timestamp;

    public static /* synthetic */ ServerData copy$default(ServerData serverData, String str, String str2, long j2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = serverData.ipv4;
        }
        if ((i2 & 2) != 0) {
            str2 = serverData.ipv6;
        }
        if ((i2 & 4) != 0) {
            j2 = serverData.timestamp;
        }
        return serverData.copy(str, str2, j2);
    }

    public final String component1() {
        return this.ipv4;
    }

    public final String component2() {
        return this.ipv6;
    }

    public final long component3() {
        return this.timestamp;
    }

    public final ServerData copy(String ipv4, String ipv6, long j2) {
        Intrinsics.checkNotNullParameter(ipv4, "ipv4");
        Intrinsics.checkNotNullParameter(ipv6, "ipv6");
        return new ServerData(ipv4, ipv6, j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ServerData) {
            ServerData serverData = (ServerData) obj;
            return Intrinsics.areEqual(this.ipv4, serverData.ipv4) && Intrinsics.areEqual(this.ipv6, serverData.ipv6) && this.timestamp == serverData.timestamp;
        }
        return false;
    }

    public int hashCode() {
        return (((this.ipv4.hashCode() * 31) + this.ipv6.hashCode()) * 31) + Long.hashCode(this.timestamp);
    }

    public String toString() {
        return "ServerData(ipv4=" + this.ipv4 + ", ipv6=" + this.ipv6 + ", timestamp=" + this.timestamp + ')';
    }

    public ServerData(String ipv4, String ipv6, long j2) {
        Intrinsics.checkNotNullParameter(ipv4, "ipv4");
        Intrinsics.checkNotNullParameter(ipv6, "ipv6");
        this.ipv4 = ipv4;
        this.ipv6 = ipv6;
        this.timestamp = j2;
    }

    public final String getIpv4() {
        return this.ipv4;
    }

    public final String getIpv6() {
        return this.ipv6;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}
