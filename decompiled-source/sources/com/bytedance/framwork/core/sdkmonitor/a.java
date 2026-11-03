package com.bytedance.framwork.core.sdkmonitor;

import org.json.JSONObject;
/* compiled from: ApiData.java */
/* loaded from: classes2.dex */
public class a {
    public String apiType;
    public long duration;
    public JSONObject extJson;
    public String sendIp;
    public long sendTime;
    public String sendUrl;
    public int status;
    public String traceCode;

    public a(String str, long j2, long j3, String str2, String str3, String str4, int i2, JSONObject jSONObject) {
        this.apiType = str;
        this.duration = j2;
        this.sendTime = j3;
        this.sendUrl = str2;
        this.sendIp = str3;
        this.traceCode = str4;
        this.status = i2;
        this.extJson = jSONObject;
    }
}
