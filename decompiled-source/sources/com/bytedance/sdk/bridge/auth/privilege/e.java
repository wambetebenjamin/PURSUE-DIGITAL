package com.bytedance.sdk.bridge.auth.privilege;

import com.bytedance.sdk.bridge.api.BridgeService;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: JsBridgeAuthManager.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private boolean f13121a;

    /* renamed from: b  reason: collision with root package name */
    private String f13122b;

    /* renamed from: c  reason: collision with root package name */
    private Map<String, List<com.bytedance.sdk.bridge.auth.model.a>> f13123c;

    private e() {
        this.f13123c = new ConcurrentHashMap();
        BridgeService bridgeService = (BridgeService) com.bytedance.news.common.service.manager.e.getService(BridgeService.class);
        if (bridgeService != null) {
            com.bytedance.sdk.bridge.d initBridgeLazyConfig = bridgeService.initBridgeLazyConfig();
            this.f13121a = initBridgeLazyConfig.isNewAuthRequestEnable();
            this.f13122b = initBridgeLazyConfig.getLocalFileUrl();
        }
    }

    public static e getInstance() {
        return a.f13124a;
    }

    /* compiled from: JsBridgeAuthManager.java */
    /* loaded from: classes2.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final e f13124a = new e();

        private a() {
        }
    }

    public boolean isNewAuthRequestEnable() {
        return this.f13121a;
    }

    public Map<String, List<com.bytedance.sdk.bridge.auth.model.a>> getAuthRuleMap() {
        return this.f13123c;
    }

    public String getLocalFileUrl() {
        return this.f13122b;
    }
}
