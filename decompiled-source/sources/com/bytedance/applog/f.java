package com.bytedance.applog;

import java.util.Map;
/* compiled from: CompressResult.java */
/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f2650a;

    /* renamed from: b  reason: collision with root package name */
    private int f2651b = -1;

    /* renamed from: c  reason: collision with root package name */
    private Map<String, String> f2652c;

    public byte[] a() {
        return this.f2650a;
    }

    public void a(byte[] bArr) {
        this.f2650a = bArr;
    }

    public int b() {
        return this.f2651b;
    }

    public void a(int i2) {
        this.f2651b = i2;
    }

    public Map<String, String> c() {
        return this.f2652c;
    }

    public void a(Map<String, String> map) {
        this.f2652c = map;
    }

    public static f b(byte[] bArr) {
        f fVar = new f();
        fVar.a(bArr);
        fVar.a(-1);
        return fVar;
    }
}
