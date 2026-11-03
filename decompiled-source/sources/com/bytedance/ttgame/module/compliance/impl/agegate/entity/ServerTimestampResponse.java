package com.bytedance.ttgame.module.compliance.impl.agegate.entity;

import com.bytedance.ttgame.base.BaseResponse;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ServerTimestampResponse.kt */
/* loaded from: classes5.dex */
public final class ServerTimestampResponse extends BaseResponse {
    @SerializedName("data")
    private final ServerData data;

    public static /* synthetic */ ServerTimestampResponse copy$default(ServerTimestampResponse serverTimestampResponse, ServerData serverData, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            serverData = serverTimestampResponse.data;
        }
        return serverTimestampResponse.copy(serverData);
    }

    public final ServerData component1() {
        return this.data;
    }

    public final ServerTimestampResponse copy(ServerData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new ServerTimestampResponse(data);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ServerTimestampResponse) && Intrinsics.areEqual(this.data, ((ServerTimestampResponse) obj).data);
    }

    public int hashCode() {
        return this.data.hashCode();
    }

    public String toString() {
        return "ServerTimestampResponse(data=" + this.data + ')';
    }

    public ServerTimestampResponse(ServerData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }

    public final ServerData getData() {
        return this.data;
    }
}
