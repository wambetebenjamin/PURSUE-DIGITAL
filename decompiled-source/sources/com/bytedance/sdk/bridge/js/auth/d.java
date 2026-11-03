package com.bytedance.sdk.bridge.js.auth;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: JSBridgeAuthManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/bytedance/sdk/bridge/js/auth/JSBridgeAuthManager;", "", "()V", "privilegeService", "Lcom/bytedance/sdk/bridge/js/auth/JSBridgePrivilegeService;", "getPrivilegeService", "()Lcom/bytedance/sdk/bridge/js/auth/JSBridgePrivilegeService;", "setPrivilegeService", "(Lcom/bytedance/sdk/bridge/js/auth/JSBridgePrivilegeService;)V", "clearConfig", "", "url", "", "authjs-bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class d {
    public static final d INSTANCE = new d();

    /* renamed from: a  reason: collision with root package name */
    private static h f13180a = new h();

    private d() {
    }

    public final h getPrivilegeService() {
        return f13180a;
    }

    public final void setPrivilegeService(h hVar) {
        f13180a = hVar;
    }

    public final void clearConfig(String url) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        g.configItems.remove(url);
    }
}
