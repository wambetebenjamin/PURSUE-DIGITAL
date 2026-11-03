package com.bytedance.apm.agent.instrumentation.transaction;

import org.json.JSONObject;
/* compiled from: TransactionData.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: b  reason: collision with root package name */
    private long f282b;

    /* renamed from: c  reason: collision with root package name */
    private String f283c;

    /* renamed from: d  reason: collision with root package name */
    private String f284d;

    /* renamed from: e  reason: collision with root package name */
    private long f285e;

    /* renamed from: f  reason: collision with root package name */
    private int f286f;

    /* renamed from: g  reason: collision with root package name */
    private int f287g;

    /* renamed from: h  reason: collision with root package name */
    private long f288h;

    /* renamed from: i  reason: collision with root package name */
    private long f289i;

    /* renamed from: j  reason: collision with root package name */
    private String f290j;
    private String k;
    private JSONObject l;

    /* renamed from: a  reason: collision with root package name */
    private long f281a = System.currentTimeMillis();
    private Object m = new Object();

    public a(String str, String str2, long j2, int i2, int i3, long j3, long j4, String str3, String str4, JSONObject jSONObject) {
        this.f283c = str;
        this.f284d = str2;
        this.f285e = j2;
        this.f286f = i2;
        this.f287g = i3;
        this.f288h = j3;
        this.f289i = j4;
        this.f290j = str3;
        this.k = str4;
        this.l = jSONObject;
    }

    public String getUrl() {
        return this.f283c;
    }

    public String getHttpMethod() {
        return this.k;
    }

    public String getCarrier() {
        return this.f284d;
    }

    public int getStatusCode() {
        return this.f286f;
    }

    public int getErrorCode() {
        return this.f287g;
    }

    public void setErrorCode(int i2) {
        synchronized (this.m) {
            this.f287g = i2;
        }
    }

    public long getBytesSent() {
        return this.f288h;
    }

    public long getBytesReceived() {
        return this.f289i;
    }

    public String getWanType() {
        return this.f290j;
    }

    public long getRequestStart() {
        return this.f281a;
    }

    public long getTotalTime() {
        return this.f285e;
    }

    public JSONObject getExtraData() {
        return this.l;
    }

    public long getRequestEnd() {
        return this.f282b;
    }

    public void setRequestEnd(long j2) {
        this.f282b = j2;
    }

    public String toString() {
        return "TransactionData{requestStart=" + this.f281a + ", url='" + this.f283c + "', carrier='" + this.f284d + "', totalTime=" + this.f285e + ", statusCode=" + this.f286f + ", errorCode=" + this.f287g + ", bytesSent=" + this.f288h + ", bytesReceived=" + this.f289i + ", wanType='" + this.f290j + "', httpMethod='" + this.k + "', extraData=" + this.l + '}';
    }
}
