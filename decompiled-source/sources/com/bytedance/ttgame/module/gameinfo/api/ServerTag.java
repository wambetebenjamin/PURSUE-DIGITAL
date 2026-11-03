package com.bytedance.ttgame.module.gameinfo.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Models.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/bytedance/ttgame/module/gameinfo/api/ServerTag;", "", "value", "", "name", "", "(ILjava/lang/String;)V", "getName", "()Ljava/lang/String;", "getValue", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ServerTag {
    @SerializedName("name")
    private final String name;
    @SerializedName("value")
    private final int value;

    public ServerTag() {
        this(0, null, 3, null);
    }

    public static /* synthetic */ ServerTag copy$default(ServerTag serverTag, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = serverTag.value;
        }
        if ((i3 & 2) != 0) {
            str = serverTag.name;
        }
        return serverTag.copy(i2, str);
    }

    public final int component1() {
        return this.value;
    }

    public final String component2() {
        return this.name;
    }

    public final ServerTag copy(int i2, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new ServerTag(i2, name);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ServerTag) {
            ServerTag serverTag = (ServerTag) obj;
            return this.value == serverTag.value && Intrinsics.areEqual(this.name, serverTag.name);
        }
        return false;
    }

    public int hashCode() {
        return (Integer.hashCode(this.value) * 31) + this.name.hashCode();
    }

    public String toString() {
        return "ServerTag(value=" + this.value + ", name=" + this.name + ')';
    }

    public ServerTag(int i2, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.value = i2;
        this.name = name;
    }

    public /* synthetic */ ServerTag(int i2, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2, (i3 & 2) != 0 ? "" : str);
    }

    public final int getValue() {
        return this.value;
    }

    public final String getName() {
        return this.name;
    }
}
