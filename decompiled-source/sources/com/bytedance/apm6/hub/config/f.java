package com.bytedance.apm6.hub.config;

import org.json.JSONObject;
/* compiled from: MemoryConfigManager.java */
/* loaded from: classes.dex */
public class f implements com.bytedance.apm6.memory.config.b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2272a = "collect_interval";

    /* renamed from: b  reason: collision with root package name */
    private static final String f2273b = "reach_top_memory_rate";

    /* renamed from: c  reason: collision with root package name */
    private com.bytedance.apm6.memory.config.a f2274c;

    public f() {
        com.bytedance.apm6.hub.config.internal.b.getInstance().init();
        com.bytedance.apm6.hub.config.internal.b.getInstance().registerConfigListener(new com.bytedance.apm6.hub.config.internal.c() { // from class: com.bytedance.apm6.hub.config.f.1
            @Override // com.bytedance.apm6.hub.config.internal.c
            public void onConfigChanged(JSONObject jSONObject, boolean z) {
                f.this.a(jSONObject, z);
            }
        });
    }

    @Override // com.bytedance.apm6.memory.config.b
    public com.bytedance.apm6.memory.config.a getConfig() {
        return this.f2274c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject, boolean z) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2 = jSONObject.optJSONObject("performance_modules");
        if (optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject("memory")) == null) {
            return;
        }
        this.f2274c = new com.bytedance.apm6.memory.config.a(optJSONObject.optLong("collect_interval", 120L), optJSONObject.optDouble("reach_top_memory_rate", 0.8d), false, false, optJSONObject.optInt("enable_upload", 0) == 1, optJSONObject.optInt(com.bytedance.apm6.hub.config.internal.d.PERF_MEMORY_CLOSE_COLLECT) != 1);
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.hub.config.internal.a.LOG_TAG, "parsed MemoryConfig=" + this.f2274c);
        }
        com.bytedance.apm6.memory.c.getInstance().updateConfig(getConfig());
    }
}
