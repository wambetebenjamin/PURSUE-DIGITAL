package com.bytedance.apm.entity;

import org.json.JSONObject;
/* compiled from: LocalLog.java */
/* loaded from: classes.dex */
public class f {
    public long createTime;
    public JSONObject data;
    public long id;
    public boolean isSampled;
    public String type;
    public String type2;
    public long versionId;

    public f() {
    }

    public f(long j2, String str, long j3, JSONObject jSONObject) {
        this.id = j2;
        this.type = str;
        this.data = jSONObject;
        this.versionId = j3;
    }

    public f(long j2, String str, long j3, String str2) {
        this.id = j2;
        this.type = str;
        try {
            this.data = new JSONObject(str2);
        } catch (Exception unused) {
        }
        this.versionId = j3;
    }

    public f setId(long j2) {
        this.id = j2;
        return this;
    }

    public f setType(String str) {
        this.type = str;
        return this;
    }

    public f setType2(String str) {
        this.type2 = str;
        return this;
    }

    public f setData(JSONObject jSONObject) {
        this.data = jSONObject;
        return this;
    }

    public f setIsSampled(boolean z) {
        this.isSampled = z;
        return this;
    }

    public f setVersionId(long j2) {
        this.versionId = j2;
        return this;
    }

    public f setTimestamp(long j2) {
        this.createTime = j2;
        return this;
    }

    public static f newLocalLog(String str) {
        str.hashCode();
        if (str.equals("api_all")) {
            return new a().setType(str);
        }
        return new f().setType(str);
    }

    public String toString() {
        return "LocalLog{id=" + this.id + ", type='" + this.type + "', type2='" + this.type2 + "', data='" + this.data + "', versionId=" + this.versionId + ", createTime=" + this.createTime + ", isSampled=" + this.isSampled + '}';
    }
}
