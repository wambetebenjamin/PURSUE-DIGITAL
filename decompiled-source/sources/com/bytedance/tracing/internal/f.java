package com.bytedance.tracing.internal;

import org.json.JSONObject;
/* compiled from: TracingData.java */
/* loaded from: classes3.dex */
public class f implements com.bytedance.apm.data.b {

    /* renamed from: a  reason: collision with root package name */
    private final JSONObject f14063a;

    /* renamed from: b  reason: collision with root package name */
    private final String f14064b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f14065c;

    /* renamed from: d  reason: collision with root package name */
    private final String f14066d;

    @Override // com.bytedance.apm.data.b
    public String getTypeLabel() {
        return "tracing";
    }

    @Override // com.bytedance.apm.data.b
    public boolean isSaveImmediately() {
        return false;
    }

    @Override // com.bytedance.apm.data.b
    public boolean isUploadImmediately() {
        return false;
    }

    @Override // com.bytedance.apm.data.b
    public boolean supportFetch() {
        return false;
    }

    public f(JSONObject jSONObject, String str, boolean z, String str2) {
        this.f14063a = jSONObject;
        this.f14064b = str;
        this.f14065c = z;
        this.f14066d = str2;
    }

    @Override // com.bytedance.apm.data.b
    public JSONObject packLog() {
        return this.f14063a;
    }

    @Override // com.bytedance.apm.data.b
    public boolean isSampled(JSONObject jSONObject) {
        if (com.bytedance.apm.launch.c.LAUNCH_TASK_TRACING_SERVICE_NAME.equals(this.f14064b)) {
            return com.bytedance.apm.samplers.c.getPerfAllowSwitch("start_trace");
        }
        return d.getInstance().isErrorOrSampleHit(this.f14065c, this.f14064b) != 0;
    }

    @Override // com.bytedance.apm.data.b
    public String getSubTypeLabel() {
        return this.f14066d;
    }
}
