package com.bytedance.tracing.internal;

import com.bytedance.apm.util.m;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: TraceData.java */
@Deprecated
/* loaded from: classes3.dex */
public class c implements com.bytedance.apm.data.b {

    /* renamed from: a  reason: collision with root package name */
    private final String f14056a;

    /* renamed from: b  reason: collision with root package name */
    private final com.bytedance.tracing.core.a f14057b;

    @Override // com.bytedance.apm.data.b
    public String getSubTypeLabel() {
        return "tracing";
    }

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

    public c(com.bytedance.tracing.core.a aVar) {
        this.f14056a = aVar.getServiceName();
        this.f14057b = aVar;
    }

    @Override // com.bytedance.apm.data.b
    public JSONObject packLog() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("log_type", e.VALUE_TRACING);
            jSONObject.put("service", this.f14056a);
            jSONObject.put("trace_id", this.f14057b.getTraceId() + "");
            jSONObject.put(e.KEY_SPAN_ID, this.f14057b.getSpanId() + "");
            jSONObject.put(e.KEY_OP_NAME, this.f14057b.getOperationName());
            jSONObject.put(e.KEY_PARENT_ID, this.f14057b.getParentId() + "");
            jSONObject.put(e.KEY_REF_ID, this.f14057b.getRefId() + "");
            jSONObject.put(e.KEY_START_TIMESTAMP, this.f14057b.getStartTs());
            jSONObject.put(e.KEY_END_TIMESTAMP, this.f14057b.getFinishTs());
            Map<String, String> tags = this.f14057b.getTags();
            if (tags != null && !tags.isEmpty()) {
                jSONObject.put(e.KEY_TAGS, new JSONObject(this.f14057b.getTags()));
            }
            List<b> logs = this.f14057b.getLogs();
            if (!m.isEmpty(logs)) {
                JSONArray jSONArray = new JSONArray();
                for (b bVar : logs) {
                    jSONArray.put(bVar.toJson());
                }
                jSONObject.put(e.KEY_LOGS, jSONArray);
            }
            jSONObject.put(e.KEY_THREAD_NAME, this.f14057b.getThreadName());
            jSONObject.put("hit_rules", d.getInstance().isErrorOrSampleHit(this.f14057b.isErrorTag(), this.f14056a));
            jSONObject.put(e.KEY_SAMPLE_RATE, d.getInstance().getSampleRate(this.f14056a));
            jSONObject.put(e.KEY_FINISH_FLAG, 1);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // com.bytedance.apm.data.b
    public boolean isSampled(JSONObject jSONObject) {
        return d.getInstance().isErrorOrSampleHit(this.f14057b.isErrorTag(), this.f14056a) != 0;
    }
}
