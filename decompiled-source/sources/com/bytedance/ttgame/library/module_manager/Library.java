package com.bytedance.ttgame.library.module_manager;

import com.bytedance.ttgame.library.module_manager.internal.Logger;
import java.io.StringReader;
import java.util.Properties;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Library.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB7\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u0003H\u0016¨\u0006\f"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/Library;", "Lcom/bytedance/ttgame/library/module_manager/GSDKArtifact;", "name", "", "group", "artifact", "version", "region", "pluginVersion", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "toString", "Companion", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Library extends GSDKArtifact {
    public static final Companion Companion = new Companion(null);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Library(String name, String group, String artifact, String version, String region, String pluginVersion) {
        super(name, group, artifact, version, region, pluginVersion);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(group, "group");
        Intrinsics.checkNotNullParameter(artifact, "artifact");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(pluginVersion, "pluginVersion");
    }

    @Override // com.bytedance.ttgame.library.module_manager.GSDKArtifact
    public String toString() {
        return "Library: " + super.toString();
    }

    /* compiled from: Library.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/Library$Companion;", "", "()V", "parse", "Lcom/bytedance/ttgame/library/module_manager/Library;", "config", "", "parse$module_manager_release", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Library parse$module_manager_release(String config) {
            Intrinsics.checkNotNullParameter(config, "config");
            Properties properties = new Properties();
            properties.load(new StringReader(config));
            String property = properties.getProperty("name");
            Intrinsics.checkNotNullExpressionValue(property, "properties.getProperty(\"name\")");
            String property2 = properties.getProperty("group");
            Intrinsics.checkNotNullExpressionValue(property2, "properties.getProperty(\"group\")");
            String property3 = properties.getProperty("artifact");
            Intrinsics.checkNotNullExpressionValue(property3, "properties.getProperty(\"artifact\")");
            String property4 = properties.getProperty("version");
            Intrinsics.checkNotNullExpressionValue(property4, "properties.getProperty(\"version\")");
            String property5 = properties.getProperty("region");
            Intrinsics.checkNotNullExpressionValue(property5, "properties.getProperty(\"region\")");
            String property6 = properties.getProperty("pluginVersion");
            Intrinsics.checkNotNullExpressionValue(property6, "properties.getProperty(\"pluginVersion\")");
            Library library = new Library(property, property2, property3, property4, property5, property6);
            Logger.INSTANCE.logVerbose("装载到 " + library);
            return library;
        }
    }
}
