package com.bytedance.apm.entity;

import org.json.JSONObject;
/* compiled from: ImageSample.java */
/* loaded from: classes.dex */
public class d {
    public int count = 1;
    public long last_duration;
    public JSONObject last_extra;
    public String last_ip;
    public long last_timestamp;
    public String last_uri;
    public int status;

    public d(String str, int i2, String str2, long j2, long j3, JSONObject jSONObject) {
        this.last_ip = str2;
        this.last_uri = str;
        this.status = i2;
        this.last_timestamp = j2;
        this.last_duration = j3;
        this.last_extra = jSONObject;
    }
}
