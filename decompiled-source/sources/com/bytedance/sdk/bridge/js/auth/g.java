package com.bytedance.sdk.bridge.js.auth;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: JSBridgeConfigTask.java */
/* loaded from: classes3.dex */
public abstract class g extends com.bytedance.sdk.bridge.auth.privilege.c<String, i> {
    public static final Map<String, i> configItems = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.bridge.auth.privilege.c
    public void a(String str, i iVar) {
        configItems.put(str, iVar);
    }
}
