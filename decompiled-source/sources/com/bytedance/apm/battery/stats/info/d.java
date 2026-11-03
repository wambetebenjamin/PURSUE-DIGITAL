package com.bytedance.apm.battery.stats.info;

import org.json.JSONObject;
/* compiled from: WakeLockInfo.java */
/* loaded from: classes.dex */
public class d extends b {
    public int flags;
    public String tag;

    public String toString() {
        return "WakeLockInfo{flags=" + this.flags + ", tag=" + this.tag + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", threadName=" + this.threadName + ", threadStack=" + a() + '}';
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("flags", this.flags);
            jSONObject.put("tag", this.tag);
            jSONObject.put(com.bytedance.apm.constant.c.KEY_START_TIME, this.startTime);
            jSONObject.put("end_time", this.endTime);
            jSONObject.put("thread_name", this.threadName);
            jSONObject.put("thread_stack", a());
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }
}
