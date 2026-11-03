package com.bytedance.common.model;
/* compiled from: Result.java */
/* loaded from: classes2.dex */
public class d {
    public static final int CODE_FAILED = -1;
    public static final int CODE_UNKNOWN = 0;

    /* renamed from: e  reason: collision with root package name */
    public static final int f5074e = 1;

    /* renamed from: f  reason: collision with root package name */
    public static final int f5075f = 16;

    /* renamed from: a  reason: collision with root package name */
    private int f5076a = 0;

    /* renamed from: b  reason: collision with root package name */
    private int f5077b = 0;

    /* renamed from: c  reason: collision with root package name */
    private String f5078c = "";

    /* renamed from: d  reason: collision with root package name */
    private long f5079d;

    public d withCode(int i2) {
        this.f5076a = i2;
        return this;
    }

    public d withDetailCode(int i2) {
        this.f5077b = i2;
        return this;
    }

    public d withMessage(String str) {
        this.f5078c = str;
        return this;
    }

    public d withRequestId(long j2) {
        this.f5079d = j2;
        return this;
    }

    public long getRequestId() {
        return this.f5079d;
    }

    public int getCode() {
        return this.f5076a;
    }

    public int getDetailCode() {
        return this.f5077b;
    }

    public String getMessage() {
        return this.f5078c;
    }

    public boolean a() {
        return c() || b();
    }

    public boolean b() {
        int i2 = this.f5076a;
        return (i2 == 0 || i2 == -1 || (i2 & 16) == 0) ? false : true;
    }

    public boolean c() {
        int i2 = this.f5076a;
        return (i2 == 0 || i2 == -1 || (i2 & 1) == 0) ? false : true;
    }

    public String toString() {
        return "Result{mCode=" + this.f5076a + ", mDetailCode=" + this.f5077b + ", mMessage='" + this.f5078c + "'}";
    }
}
