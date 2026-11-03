package com.bytedance.apm.battery.stats.info;

import org.json.JSONObject;
/* compiled from: LocInfo.java */
/* loaded from: classes.dex */
public class c extends b {
    public String request;

    public String toString() {
        return "LocInfo{request=" + this.request + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", threadName=" + this.threadName + ", threadStack=" + a() + '}';
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.bytedance.apm.constant.c.KEY_START_TIME, this.startTime);
            jSONObject.put("end_time", this.endTime);
            jSONObject.put("thread_name", this.threadName);
            jSONObject.put("thread_stack", a());
            jSONObject.put("request", this.request);
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }
}
