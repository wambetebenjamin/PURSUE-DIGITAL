package com.bytedance.ttgame.library.module_manager;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GSDKWrapper.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\b\u0010\u0015\u001a\u00020\u0003H\u0016R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/GSDKWrapper;", "Lcom/bytedance/ttgame/library/module_manager/MavenNotion;", "group", "", "artifact", "version", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getArtifact", "()Ljava/lang/String;", "getGroup", "getVersion", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GSDKWrapper extends MavenNotion {
    private final String artifact;
    private final String group;
    private final String version;

    public static /* synthetic */ GSDKWrapper copy$default(GSDKWrapper gSDKWrapper, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = gSDKWrapper.getGroup();
        }
        if ((i2 & 2) != 0) {
            str2 = gSDKWrapper.getArtifact();
        }
        if ((i2 & 4) != 0) {
            str3 = gSDKWrapper.getVersion();
        }
        return gSDKWrapper.copy(str, str2, str3);
    }

    public final String component1() {
        return getGroup();
    }

    public final String component2() {
        return getArtifact();
    }

    public final String component3() {
        return getVersion();
    }

    public final GSDKWrapper copy(String group, String artifact, String version) {
        Intrinsics.checkNotNullParameter(group, "group");
        Intrinsics.checkNotNullParameter(artifact, "artifact");
        Intrinsics.checkNotNullParameter(version, "version");
        return new GSDKWrapper(group, artifact, version);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GSDKWrapper) {
            GSDKWrapper gSDKWrapper = (GSDKWrapper) obj;
            return Intrinsics.areEqual(getGroup(), gSDKWrapper.getGroup()) && Intrinsics.areEqual(getArtifact(), gSDKWrapper.getArtifact()) && Intrinsics.areEqual(getVersion(), gSDKWrapper.getVersion());
        }
        return false;
    }

    public int hashCode() {
        return (((getGroup().hashCode() * 31) + getArtifact().hashCode()) * 31) + getVersion().hashCode();
    }

    @Override // com.bytedance.ttgame.library.module_manager.MavenNotion
    public String getGroup() {
        return this.group;
    }

    @Override // com.bytedance.ttgame.library.module_manager.MavenNotion
    public String getArtifact() {
        return this.artifact;
    }

    @Override // com.bytedance.ttgame.library.module_manager.MavenNotion
    public String getVersion() {
        return this.version;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GSDKWrapper(String group, String artifact, String version) {
        super(group, artifact, version);
        Intrinsics.checkNotNullParameter(group, "group");
        Intrinsics.checkNotNullParameter(artifact, "artifact");
        Intrinsics.checkNotNullParameter(version, "version");
        this.group = group;
        this.artifact = artifact;
        this.version = version;
    }

    @Override // com.bytedance.ttgame.library.module_manager.MavenNotion
    public String toString() {
        return super.toString();
    }
}
