package com.bytedance.framwork.core.sdklib.apm6;

import org.json.JSONObject;
/* compiled from: LogItem.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private long f8601a;

    /* renamed from: b  reason: collision with root package name */
    private long f8602b;

    /* renamed from: c  reason: collision with root package name */
    private byte[] f8603c;

    public d(byte[] bArr, long j2, long j3) {
        this.f8603c = bArr;
        this.f8601a = j3;
        this.f8602b = j2;
    }

    public byte[] a() {
        return this.f8603c;
    }

    public long b() {
        return this.f8601a;
    }

    public long c() {
        return this.f8602b;
    }

    public String toString() {
        try {
            return new JSONObject(new String(this.f8603c)).toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
