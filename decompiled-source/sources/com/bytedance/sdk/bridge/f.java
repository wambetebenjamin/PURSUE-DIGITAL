package com.bytedance.sdk.bridge;

import androidx.lifecycle.Lifecycle;
import com.bytedance.sdk.bridge.annotation.BridgePrivilege;
import com.bytedance.sdk.bridge.api.BridgeService;
import com.bytedance.sdk.bridge.b;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BridgeManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\tH\u0007J\u0016\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00072\b\b\u0001\u0010\u0018\u001a\u00020\u0007J\u000e\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/bytedance/sdk/bridge/BridgeManager;", "", "()V", "DEFAULT_DEBUG", "", "DEFAULT_IGNORE_NAMESPACE", "DEFAULT_SCHEMA", "", "bridgeConfig", "Lcom/bytedance/sdk/bridge/BridgeConfig;", "getBridgeConfig", "()Lcom/bytedance/sdk/bridge/BridgeConfig;", "setBridgeConfig", "(Lcom/bytedance/sdk/bridge/BridgeConfig;)V", "bridgeService", "Lcom/bytedance/sdk/bridge/api/BridgeService;", "config", "", "registerBridgeWithLifeCycle", "bridgeModule", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "registerEvent", "event", "privilege", "registerGlobalBridge", "bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f13151a = false;

    /* renamed from: d  reason: collision with root package name */
    private static BridgeService f13154d;

    /* renamed from: e  reason: collision with root package name */
    private static b f13155e;
    public static final f INSTANCE = new f();

    /* renamed from: b  reason: collision with root package name */
    private static final String f13152b = f13152b;

    /* renamed from: b  reason: collision with root package name */
    private static final String f13152b = f13152b;

    /* renamed from: c  reason: collision with root package name */
    private static final boolean f13153c = true;

    static {
        b build;
        BridgeService bridgeService = (BridgeService) com.bytedance.news.common.service.manager.e.getService(BridgeService.class);
        f13154d = bridgeService;
        if (bridgeService == null || (build = bridgeService.initBridgeConfig()) == null) {
            build = new b.a().isDebug(Boolean.valueOf(f13151a)).setSchema(f13152b).setIgnoreNameSpace(true).jsCallSuccessCostEnable(false).isCompatiblePreLoadWebview(false).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "BridgeConfig.Builder()\n …                 .build()");
        }
        f13155e = build;
    }

    private f() {
    }

    public final b getBridgeConfig() {
        return f13155e;
    }

    public final void setBridgeConfig(b bVar) {
        Intrinsics.checkParameterIsNotNull(bVar, "<set-?>");
        f13155e = bVar;
    }

    @Deprecated(message = "请使用 implements BridgeService 形式进行初始化,这样有利于懒加载,可以不用再 application 阶段初始化.")
    public final void config(b bridgeConfig) {
        Intrinsics.checkParameterIsNotNull(bridgeConfig, "bridgeConfig");
        BridgeService bridgeService = f13154d;
        if (bridgeService != null) {
            if ((bridgeService != null ? bridgeService.initBridgeConfig() : null) != null) {
                return;
            }
        }
        f13155e = bridgeConfig;
    }

    public final void registerGlobalBridge(Object bridgeModule) {
        Intrinsics.checkParameterIsNotNull(bridgeModule, "bridgeModule");
        j.registerBridge$default(j.INSTANCE, bridgeModule, null, 2, null);
    }

    public final void registerBridgeWithLifeCycle(Object bridgeModule, Lifecycle lifecycle) {
        Intrinsics.checkParameterIsNotNull(bridgeModule, "bridgeModule");
        Intrinsics.checkParameterIsNotNull(lifecycle, "lifecycle");
        j.INSTANCE.registerBridgeWithLifeCycle(bridgeModule, lifecycle);
    }

    public final void registerEvent(String event, @BridgePrivilege String privilege) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(privilege, "privilege");
        j.INSTANCE.registerEvent(event, privilege);
    }
}
