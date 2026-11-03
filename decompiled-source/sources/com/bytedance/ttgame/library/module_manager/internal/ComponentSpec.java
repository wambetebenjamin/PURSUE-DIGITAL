package com.bytedance.ttgame.library.module_manager.internal;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GSDKSuiteMetaInfo.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/internal/ComponentSpec;", "", "name", "", "version", "abilities", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAbilities", "()Ljava/lang/String;", "getName", "getVersion", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ComponentSpec {
    @SerializedName("abilities")
    private final String abilities;
    @SerializedName("name")
    private final String name;
    @SerializedName("version")
    private final String version;

    public static /* synthetic */ ComponentSpec copy$default(ComponentSpec componentSpec, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = componentSpec.name;
        }
        if ((i2 & 2) != 0) {
            str2 = componentSpec.version;
        }
        if ((i2 & 4) != 0) {
            str3 = componentSpec.abilities;
        }
        return componentSpec.copy(str, str2, str3);
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.version;
    }

    public final String component3() {
        return this.abilities;
    }

    public final ComponentSpec copy(String name, String version, String abilities) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(abilities, "abilities");
        return new ComponentSpec(name, version, abilities);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ComponentSpec) {
            ComponentSpec componentSpec = (ComponentSpec) obj;
            return Intrinsics.areEqual(this.name, componentSpec.name) && Intrinsics.areEqual(this.version, componentSpec.version) && Intrinsics.areEqual(this.abilities, componentSpec.abilities);
        }
        return false;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.version.hashCode()) * 31) + this.abilities.hashCode();
    }

    public String toString() {
        return "ComponentSpec(name=" + this.name + ", version=" + this.version + ", abilities=" + this.abilities + ')';
    }

    public ComponentSpec(String name, String version, String abilities) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(abilities, "abilities");
        this.name = name;
        this.version = version;
        this.abilities = abilities;
    }

    public final String getName() {
        return this.name;
    }

    public final String getVersion() {
        return this.version;
    }

    public final String getAbilities() {
        return this.abilities;
    }
}
