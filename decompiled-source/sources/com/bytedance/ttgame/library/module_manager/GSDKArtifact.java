package com.bytedance.ttgame.library.module_manager;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GSDKArtifact.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\b&\u0018\u00002\u00020\u0001B7\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\b\u0010\u0017\u001a\u00020\u0003H\u0016R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000bR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000b\"\u0004\b\u0012\u0010\rR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000b\"\u0004\b\u0014\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/GSDKArtifact;", "", "name", "", "group", "artifact", "version", "region", "pluginVersion", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getArtifact", "()Ljava/lang/String;", "setArtifact", "(Ljava/lang/String;)V", "getGroup", "setGroup", "getName", "getPluginVersion", "setPluginVersion", "getRegion", "setRegion", "getVersion", "setVersion", "toString", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class GSDKArtifact {
    private String artifact;
    private String group;
    private final String name;
    private String pluginVersion;
    private String region;
    private String version;

    public GSDKArtifact(String name, String group, String artifact, String version, String region, String pluginVersion) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(group, "group");
        Intrinsics.checkNotNullParameter(artifact, "artifact");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(pluginVersion, "pluginVersion");
        this.name = name;
        this.group = group;
        this.artifact = artifact;
        this.version = version;
        this.region = region;
        this.pluginVersion = pluginVersion;
    }

    public final String getName() {
        return this.name;
    }

    public final String getGroup() {
        return this.group;
    }

    public final void setGroup(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.group = str;
    }

    public final String getArtifact() {
        return this.artifact;
    }

    public final void setArtifact(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.artifact = str;
    }

    public final String getVersion() {
        return this.version;
    }

    public final void setVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.version = str;
    }

    public final String getRegion() {
        return this.region;
    }

    public final void setRegion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.region = str;
    }

    public final String getPluginVersion() {
        return this.pluginVersion;
    }

    public final void setPluginVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.pluginVersion = str;
    }

    public String toString() {
        return "\n\tname=" + this.name + "\n\tgroup=" + this.group + "\n\tartifact=" + this.artifact + "\n\tversion=" + this.version + "\n\tregion=" + this.region + "\n\tpluginVersion=" + this.pluginVersion;
    }
}
