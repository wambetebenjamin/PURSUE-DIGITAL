package com.bytedance.ttgame.library.module_manager;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: MavenNotion.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\u0003H\u0016R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\f"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/MavenNotion;", "", "group", "", "artifact", "version", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getArtifact", "()Ljava/lang/String;", "getGroup", "getVersion", "toString", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public class MavenNotion {
    private final String artifact;
    private final String group;
    private final String version;

    public MavenNotion(String group, String artifact, String version) {
        Intrinsics.checkNotNullParameter(group, "group");
        Intrinsics.checkNotNullParameter(artifact, "artifact");
        Intrinsics.checkNotNullParameter(version, "version");
        this.group = group;
        this.artifact = artifact;
        this.version = version;
    }

    public String getGroup() {
        return this.group;
    }

    public String getArtifact() {
        return this.artifact;
    }

    public String getVersion() {
        return this.version;
    }

    public String toString() {
        return getGroup() + ':' + getArtifact() + ':' + getVersion();
    }
}
