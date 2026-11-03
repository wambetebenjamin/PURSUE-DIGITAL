package com.bytedance.services.apm.api;

import org.json.JSONObject;
/* compiled from: ApmAgentService.java */
/* loaded from: classes3.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    protected static IApmAgent f13264a = null;

    /* renamed from: b  reason: collision with root package name */
    protected static volatile boolean f13265b = false;

    protected a() {
    }

    public static void a() {
        try {
            final IApmAgent iApmAgent = (IApmAgent) Class.forName("com.bytedance.apm.impl.ApmAgentServiceImpl").newInstance();
            com.bytedance.news.common.service.manager.e.registerService(IApmAgent.class, (com.bytedance.news.common.service.manager.b) new com.bytedance.news.common.service.manager.b<IApmAgent>() { // from class: com.bytedance.services.apm.api.a.1
                @Override // com.bytedance.news.common.service.manager.b
                /* renamed from: a */
                public IApmAgent create() {
                    return IApmAgent.this;
                }
            });
        } catch (Throwable unused) {
        }
    }

    public static void setApmAgentService(IApmAgent iApmAgent) {
        f13264a = iApmAgent;
    }

    public static void monitorEventSafely(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        IApmAgent iApmAgent = f13264a;
        if (iApmAgent == null) {
            if (f13265b) {
                throw new RuntimeException("implement IApmAgent and set sInstance first");
            }
            return;
        }
        iApmAgent.monitorEvent(str, jSONObject, jSONObject2, jSONObject3);
    }

    public static void monitorLogSafely(String str, JSONObject jSONObject) {
        IApmAgent iApmAgent = f13264a;
        if (iApmAgent == null) {
            if (f13265b) {
                throw new RuntimeException("implement IApmAgent and set sInstance first");
            }
            return;
        }
        iApmAgent.monitorLog(str, jSONObject);
    }

    @Deprecated
    public static void monitorStatusRateSafely(String str, int i2, JSONObject jSONObject) {
        IApmAgent iApmAgent = f13264a;
        if (iApmAgent == null) {
            if (f13265b) {
                throw new RuntimeException("implement IApmAgent and set sInstance first");
            }
            return;
        }
        iApmAgent.monitorStatusRate(str, i2, jSONObject);
    }

    @Deprecated
    public static void monitorDurationSafely(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        IApmAgent iApmAgent = f13264a;
        if (iApmAgent == null) {
            if (f13265b) {
                throw new RuntimeException("implement IApmAgent and set sInstance first");
            }
            return;
        }
        iApmAgent.monitorDuration(str, jSONObject, jSONObject2);
    }

    @Deprecated
    public static void monitorStatusAndDurationSafely(String str, int i2, JSONObject jSONObject, JSONObject jSONObject2) {
        IApmAgent iApmAgent = f13264a;
        if (iApmAgent == null) {
            if (f13265b) {
                throw new RuntimeException("implement IApmAgent and set sInstance first");
            }
            return;
        }
        iApmAgent.monitorStatusAndDuration(str, i2, jSONObject, jSONObject2);
    }

    public static boolean isImplInjected() {
        return f13264a != null;
    }

    public static boolean isIsStrictMode() {
        return f13265b;
    }

    public static void setIsStrictMode(boolean z) {
        f13265b = z;
    }
}
