package com.bytedance.apm.data.type;

import android.text.TextUtils;
import com.bytedance.apm.constant.p;
import com.bytedance.apm.constant.r;
import com.bytedance.apm.util.l;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: PerfData.java */
/* loaded from: classes.dex */
public class f implements com.bytedance.apm.data.b {
    public JSONObject extraStatus;
    public JSONObject extraValues;
    public JSONObject filters;
    public boolean forceReport;
    public boolean isSaveImmediately;
    public JSONObject logExtr;
    public String metricName;
    public String serviceName;

    @Override // com.bytedance.apm.data.b
    public String getTypeLabel() {
        return "performance_monitor";
    }

    @Override // com.bytedance.apm.data.b
    public boolean isUploadImmediately() {
        return false;
    }

    @Override // com.bytedance.apm.data.b
    public boolean supportFetch() {
        return true;
    }

    public f() {
    }

    public f serviceName(String str) {
        this.serviceName = str;
        return this;
    }

    public f metricName(String str) {
        this.metricName = str;
        return this;
    }

    public f forceReport(boolean z) {
        this.forceReport = z;
        return this;
    }

    public f extraValues(JSONObject jSONObject) {
        this.extraValues = jSONObject;
        return this;
    }

    public f extraStatus(JSONObject jSONObject) {
        this.extraStatus = jSONObject;
        return this;
    }

    public f appendFilters(JSONObject jSONObject) {
        this.filters = jSONObject;
        return this;
    }

    public f extraLog(JSONObject jSONObject) {
        this.logExtr = jSONObject;
        return this;
    }

    public f(String str, String str2, boolean z, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        this.serviceName = str;
        this.metricName = str2;
        this.forceReport = z;
        this.extraValues = jSONObject;
        this.extraStatus = jSONObject2;
        this.logExtr = jSONObject3;
    }

    public f(String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        this(str, str2, false, jSONObject, jSONObject2, jSONObject3);
    }

    public f(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        this(str, "", false, jSONObject, jSONObject2, jSONObject3);
    }

    @Override // com.bytedance.apm.data.b
    public JSONObject packLog() {
        try {
            if (this.logExtr == null) {
                this.logExtr = new JSONObject();
            }
            this.logExtr.put("log_type", "performance_monitor");
            this.logExtr.put("service", this.serviceName);
            if (!l.isEmpty(this.extraValues)) {
                this.logExtr.put(com.bytedance.apm.constant.c.KEY_EXTRA_VALUES, this.extraValues);
            }
            if (TextUtils.equals("start", this.serviceName) && TextUtils.equals("from", this.logExtr.optString("monitor-plugin"))) {
                if (this.extraStatus == null) {
                    this.extraStatus = new JSONObject();
                }
                this.extraStatus.put("start_mode", com.bytedance.apm.c.getLaunchMode());
            }
            if (!l.isEmpty(this.extraStatus)) {
                this.logExtr.put(com.bytedance.apm.constant.c.KEY_EXTRA_STATUS, this.extraStatus);
            }
            if (!l.isEmpty(this.filters)) {
                this.logExtr.put("filters", this.filters);
            }
            return this.logExtr;
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // com.bytedance.apm.data.b
    public boolean isSampled(JSONObject jSONObject) {
        boolean perfFpsAllowSwitch;
        JSONObject optJSONObject;
        if ("fps".equals(this.serviceName) || "fps_drop".equals(this.serviceName)) {
            perfFpsAllowSwitch = com.bytedance.apm.samplers.c.getPerfFpsAllowSwitch(this.serviceName, this.metricName);
        } else if (com.bytedance.apm.constant.d.MONITOR_TYPE_TEMPERATURE.equals(this.serviceName)) {
            perfFpsAllowSwitch = com.bytedance.apm.samplers.c.getPerfSecondStageSwitch(this.serviceName);
        } else {
            if (!"battery".equals(this.serviceName)) {
                if ("start".equals(this.serviceName)) {
                    if (!com.bytedance.apm.samplers.c.getPerfAllowSwitch(this.serviceName) && !com.bytedance.apm.samplers.c.getMetricSwitch(this.metricName)) {
                        perfFpsAllowSwitch = false;
                    }
                } else if ("start_trace".equals(this.serviceName)) {
                    if (jSONObject != null) {
                        if (!com.bytedance.apm.samplers.c.getPerfSecondStageSwitch(p.PERF_KEY_PERF_DATA_COLLECT)) {
                            jSONObject.remove(com.bytedance.apm.launch.c.LAUNCH_DATA_PERF_DATA);
                        }
                        if (!com.bytedance.apm.samplers.c.getPerfAllowSwitch(this.serviceName) && (optJSONObject = jSONObject.optJSONObject(r.STATS_KEY_TRACE)) != null) {
                            optJSONObject.remove(r.STATS_KEY_SPANS);
                        }
                    }
                    perfFpsAllowSwitch = com.bytedance.apm.samplers.c.getPerfAllowSwitch(this.serviceName);
                } else {
                    perfFpsAllowSwitch = com.bytedance.apm.samplers.c.getPerfAllowSwitch(this.serviceName);
                }
            }
            perfFpsAllowSwitch = true;
        }
        return this.forceReport || perfFpsAllowSwitch;
    }

    @Override // com.bytedance.apm.data.b
    public String getSubTypeLabel() {
        return this.serviceName;
    }

    @Override // com.bytedance.apm.data.b
    public boolean isSaveImmediately() {
        return this.isSaveImmediately;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public String getMetricName() {
        return this.metricName;
    }

    public boolean isAddDeviceInfo() {
        return TextUtils.equals(this.serviceName, "memory");
    }
}
