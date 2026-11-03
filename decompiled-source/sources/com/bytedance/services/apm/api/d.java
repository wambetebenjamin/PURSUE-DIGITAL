package com.bytedance.services.apm.api;

import java.util.Map;
/* compiled from: HttpResponse.java */
/* loaded from: classes3.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private int f13280a;

    /* renamed from: b  reason: collision with root package name */
    private Map<String, String> f13281b;

    /* renamed from: c  reason: collision with root package name */
    private byte[] f13282c;

    public d(int i2, byte[] bArr) {
        this.f13280a = i2;
        this.f13282c = bArr;
    }

    public d(int i2, Map<String, String> map, byte[] bArr) {
        this.f13280a = i2;
        this.f13281b = map;
        this.f13282c = bArr;
    }

    public int getStatusCode() {
        return this.f13280a;
    }

    public byte[] getResponseBytes() {
        return this.f13282c;
    }

    public Map<String, String> getHeaders() {
        return this.f13281b;
    }
}
