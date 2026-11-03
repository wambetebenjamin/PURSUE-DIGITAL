package com.bytedance.crash.event;

import java.util.Map;
/* compiled from: BizException.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private Throwable f6667a;

    /* renamed from: b  reason: collision with root package name */
    private String f6668b;

    /* renamed from: c  reason: collision with root package name */
    private Map<String, String> f6669c;

    /* renamed from: d  reason: collision with root package name */
    private Map<String, String> f6670d;

    public a(Throwable th, String str, Map<String, String> map, Map<String, String> map2) {
        this.f6667a = th;
        this.f6668b = str;
        this.f6669c = map;
        this.f6670d = map2;
    }

    public Throwable a() {
        return this.f6667a;
    }

    public String b() {
        return this.f6668b;
    }

    public Map<String, String> c() {
        return this.f6669c;
    }

    public Map<String, String> d() {
        return this.f6670d;
    }
}
