package com.bytedance.ttgame.library.module_manager;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Module.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001B?\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\b\u0010\r\u001a\u00020\u0003H\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/Module;", "Lcom/bytedance/ttgame/library/module_manager/GSDKArtifact;", "name", "", "component", "group", "artifact", "version", "region", "pluginVersion", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getComponent", "()Ljava/lang/String;", "toString", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class Module extends GSDKArtifact {
    private final String component;

    public final String getComponent() {
        return this.component;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Module(String name, String component, String group, String artifact, String version, String region, String pluginVersion) {
        super(name, group, artifact, version, region, pluginVersion);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(component, "component");
        Intrinsics.checkNotNullParameter(group, "group");
        Intrinsics.checkNotNullParameter(artifact, "artifact");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(pluginVersion, "pluginVersion");
        this.component = component;
    }

    @Override // com.bytedance.ttgame.library.module_manager.GSDKArtifact
    public String toString() {
        return "\n\tcomponent=" + this.component + super.toString();
    }
}
