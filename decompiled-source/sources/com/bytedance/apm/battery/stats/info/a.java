package com.bytedance.apm.battery.stats.info;

import android.os.SystemClock;
import org.json.JSONObject;
/* compiled from: AlarmInfo.java */
/* loaded from: classes.dex */
public class a extends b {
    public String intentInfo;
    public long interval;
    public int type;

    public long getUTCTriggerAtMillis() {
        int i2 = this.type;
        if (i2 == 1 || i2 == 0) {
            return this.startTime;
        }
        return (this.startTime + System.currentTimeMillis()) - SystemClock.elapsedRealtime();
    }

    public boolean isWakeUpAlarm() {
        int i2 = this.type;
        return i2 == 2 || i2 == 0;
    }

    public String toString() {
        return "AlarmInfo{type=" + this.type + ", interval=" + this.interval + ", intentInfo=" + this.intentInfo + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", threadName=" + this.threadName + ", threadStack=" + a() + '}';
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.bytedance.apm.constant.c.KEY_START_TIME, this.startTime);
            jSONObject.put("end_time", this.endTime);
            jSONObject.put("thread_name", this.threadName);
            jSONObject.put("thread_stack", a());
            jSONObject.put(com.bytedance.applog.forward.b.f2686c, this.interval);
            jSONObject.put("type", this.type);
            jSONObject.put("intent_info", this.intentInfo);
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }
}
