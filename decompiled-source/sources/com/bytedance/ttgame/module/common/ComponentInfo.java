package com.bytedance.ttgame.module.common;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ComponentInfo.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u001e\u0010\u000b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/bytedance/ttgame/module/common/ComponentInfo;", "", "name", "", "nativeName", "version", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getNativeName", "unityName", "getUnityName", "setUnityName", "(Ljava/lang/String;)V", "getVersion", "setVersion", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ComponentInfo {
    private final String name;
    @SerializedName("android_name")
    private final String nativeName;
    @SerializedName("unity_name")
    private String unityName;
    private String version;

    public ComponentInfo() {
        this(null, null, 3, null);
    }

    public static /* synthetic */ ComponentInfo copy$default(ComponentInfo componentInfo, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = componentInfo.name;
        }
        if ((i2 & 2) != 0) {
            str2 = componentInfo.nativeName;
        }
        return componentInfo.copy(str, str2);
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.nativeName;
    }

    public final ComponentInfo copy(String name, String nativeName) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(nativeName, "nativeName");
        return new ComponentInfo(name, nativeName);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ComponentInfo) {
            ComponentInfo componentInfo = (ComponentInfo) obj;
            return Intrinsics.areEqual(this.name, componentInfo.name) && Intrinsics.areEqual(this.nativeName, componentInfo.nativeName);
        }
        return false;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.nativeName.hashCode();
    }

    public String toString() {
        return "ComponentInfo(name=" + this.name + ", nativeName=" + this.nativeName + ')';
    }

    public ComponentInfo(String name, String nativeName) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(nativeName, "nativeName");
        this.name = name;
        this.nativeName = nativeName;
        this.unityName = "";
        this.version = "";
    }

    public /* synthetic */ ComponentInfo(String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2);
    }

    public final String getName() {
        return this.name;
    }

    public final String getNativeName() {
        return this.nativeName;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ComponentInfo(String name, String nativeName, String version) {
        this(name, nativeName);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(nativeName, "nativeName");
        Intrinsics.checkNotNullParameter(version, "version");
        this.version = version;
    }

    public final String getUnityName() {
        return this.unityName;
    }

    public final void setUnityName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.unityName = str;
    }

    public final String getVersion() {
        return this.version;
    }

    public final void setVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.version = str;
    }
}
