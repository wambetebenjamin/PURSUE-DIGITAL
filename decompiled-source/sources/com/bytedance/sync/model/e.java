package com.bytedance.sync.model;

import com.google.gson.annotations.SerializedName;
/* compiled from: Topic.java */
/* loaded from: classes3.dex */
public class e {
    @SerializedName("topic")

    /* renamed from: a  reason: collision with root package name */
    private final String f13476a;
    @SerializedName("business")

    /* renamed from: b  reason: collision with root package name */
    private final long f13477b;
    @SerializedName("deviceid")

    /* renamed from: c  reason: collision with root package name */
    private String f13478c;
    @SerializedName("secuid")

    /* renamed from: d  reason: collision with root package name */
    private String f13479d;

    public e(String str, long j2) {
        this.f13476a = str;
        this.f13477b = j2;
    }

    public String getTopic() {
        return this.f13476a;
    }

    public long getBusiness() {
        return this.f13477b;
    }

    public String getDid() {
        return this.f13478c;
    }

    public void setDid(String str) {
        this.f13478c = str;
    }

    public String getUid() {
        return this.f13479d;
    }

    public void setUid(String str) {
        this.f13479d = str;
    }

    public String toString() {
        return "Topic{topic='" + this.f13476a + "', business=" + this.f13477b + ", did='" + this.f13478c + "', uid='" + this.f13479d + "'}";
    }
}
