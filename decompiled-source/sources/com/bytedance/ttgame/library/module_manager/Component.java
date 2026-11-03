package com.bytedance.ttgame.library.module_manager;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Component.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\f\u0018\u00002\u00020\u0001Bu\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\u0006\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b0\u0006\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\u0006\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f¢\u0006\u0002\u0010\u0010R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019¨\u0006\u001b"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/Component;", "", "name", "", "version", "apis", "", "Lcom/bytedance/ttgame/library/module_manager/ApiModule;", "impls", "Lcom/bytedance/ttgame/library/module_manager/ImplModule;", "bridges", "Lcom/bytedance/ttgame/library/module_manager/BridgeModule;", "demos", "Lcom/bytedance/ttgame/library/module_manager/DemoModule;", "abilities", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;)V", "getAbilities", "()Ljava/util/List;", "getApis", "()Ljava/util/Map;", "getBridges", "getDemos", "getImpls", "getName", "()Ljava/lang/String;", "getVersion", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Component {
    private final List<String> abilities;
    private final Map<String, ApiModule> apis;
    private final Map<String, BridgeModule> bridges;
    private final Map<String, DemoModule> demos;
    private final Map<String, ImplModule> impls;
    private final String name;
    private final String version;

    public Component(String name, String version, Map<String, ApiModule> apis, Map<String, ImplModule> impls, Map<String, BridgeModule> bridges, Map<String, DemoModule> demos, List<String> abilities) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(apis, "apis");
        Intrinsics.checkNotNullParameter(impls, "impls");
        Intrinsics.checkNotNullParameter(bridges, "bridges");
        Intrinsics.checkNotNullParameter(demos, "demos");
        Intrinsics.checkNotNullParameter(abilities, "abilities");
        this.name = name;
        this.version = version;
        this.apis = apis;
        this.impls = impls;
        this.bridges = bridges;
        this.demos = demos;
        this.abilities = abilities;
    }

    public final String getName() {
        return this.name;
    }

    public final String getVersion() {
        return this.version;
    }

    public final Map<String, ApiModule> getApis() {
        return this.apis;
    }

    public final Map<String, ImplModule> getImpls() {
        return this.impls;
    }

    public final Map<String, BridgeModule> getBridges() {
        return this.bridges;
    }

    public final Map<String, DemoModule> getDemos() {
        return this.demos;
    }

    public final List<String> getAbilities() {
        return this.abilities;
    }
}
