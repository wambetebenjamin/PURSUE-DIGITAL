package com.bytedance.apm.entity;

import org.json.JSONObject;
/* compiled from: BatteryLogEntity.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private long f1025a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f1026b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f1027c;

    /* renamed from: d  reason: collision with root package name */
    private String f1028d;

    /* renamed from: e  reason: collision with root package name */
    private long f1029e;

    /* renamed from: f  reason: collision with root package name */
    private String f1030f;

    /* renamed from: g  reason: collision with root package name */
    private long f1031g;

    /* renamed from: h  reason: collision with root package name */
    private String f1032h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f1033i;

    /* renamed from: j  reason: collision with root package name */
    private String f1034j;
    private boolean k;
    public long time;
    public String type;

    public b(boolean z, long j2, String str, boolean z2, long j3, String str2) {
        this.f1026b = z;
        this.time = j2;
        this.type = str;
        this.f1027c = z2;
        this.f1029e = j3;
        this.f1028d = str2;
    }

    public b(boolean z, long j2, String str, boolean z2, long j3, String str2, String str3) {
        this.f1026b = z;
        this.time = j2;
        this.type = str;
        this.f1027c = z2;
        this.f1029e = j3;
        this.f1028d = str2;
        this.f1030f = str3;
    }

    public void setScene(String str) {
        this.f1028d = str;
    }

    public boolean isFront() {
        return this.f1026b;
    }

    public boolean isBack() {
        return !this.f1026b;
    }

    public boolean isOn() {
        return this.f1027c;
    }

    public boolean isOff() {
        return !this.f1027c;
    }

    public long getAccumulation() {
        return this.f1029e;
    }

    public long getTime() {
        return this.time;
    }

    public String getType() {
        return this.type;
    }

    public boolean isStatus() {
        return this.f1027c;
    }

    public String getScene() {
        return this.f1028d;
    }

    public String getSource() {
        return this.f1030f;
    }

    public void setVersionId(long j2) {
        this.f1031g = j2;
    }

    public long getVersionId() {
        return this.f1031g;
    }

    public String getProcessName() {
        return this.f1032h;
    }

    public void setProcessName(String str) {
        this.f1032h = str;
    }

    public boolean isMainProcess() {
        return this.f1033i;
    }

    public void setMainProcess(boolean z) {
        this.f1033i = z;
    }

    public String getStartUuid() {
        return this.f1034j;
    }

    public void setStartUuid(String str) {
        this.f1034j = str;
    }

    public boolean isDeleteFlag() {
        return this.k;
    }

    public void setDeleteFlag(boolean z) {
        this.k = z;
    }

    public long getId() {
        return this.f1025a;
    }

    public void setId(long j2) {
        this.f1025a = j2;
    }

    public void setAccumulation(long j2) {
        this.f1029e = j2;
    }

    public String toString() {
        return "BatteryLogEntity{id=" + this.f1025a + ", front=" + this.f1026b + ", time=" + this.time + ", type='" + this.type + "', status=" + this.f1027c + ", scene='" + this.f1028d + "', accumulation=" + this.f1029e + ", source='" + this.f1030f + "', versionId=" + this.f1031g + ", processName='" + this.f1032h + "', mainProcess=" + this.f1033i + ", startUuid='" + this.f1034j + "', deleteFlag=" + this.k + '}';
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("front", this.f1026b);
        jSONObject.put(com.bytedance.sdk.account.save.database.a.FIELD_TIME, this.time);
        jSONObject.put("type", this.type);
        jSONObject.put("scene", this.f1028d);
        jSONObject.put("processName", this.f1032h);
        return jSONObject;
    }
}
