package com.bytedance.push.configuration;
/* compiled from: BDPushBaseConfiguration.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private com.bytedance.push.a f11297a;

    /* renamed from: b  reason: collision with root package name */
    private String f11298b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f11299c;

    public b(com.bytedance.push.a aVar, String str, boolean z) {
        this.f11297a = aVar;
        this.f11298b = str;
        this.f11299c = z;
    }

    public boolean isI18n() {
        return this.f11299c;
    }

    public b setI18n(boolean z) {
        this.f11299c = z;
        return this;
    }

    public com.bytedance.push.a getAppInfo() {
        return this.f11297a;
    }

    public b setAppInfo(com.bytedance.push.a aVar) {
        this.f11297a = aVar;
        return this;
    }

    public String getHost() {
        return this.f11298b;
    }

    public b setHost(String str) {
        this.f11298b = str;
        return this;
    }
}
