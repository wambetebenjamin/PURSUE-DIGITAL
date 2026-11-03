package com.bytedance.common.jato.threads;
/* compiled from: ThreadStats.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private int f5020a;

    /* renamed from: b  reason: collision with root package name */
    private int f5021b;

    /* renamed from: c  reason: collision with root package name */
    private int f5022c;

    /* renamed from: d  reason: collision with root package name */
    private int f5023d;

    /* renamed from: e  reason: collision with root package name */
    private int f5024e;

    /* renamed from: f  reason: collision with root package name */
    private int f5025f;

    public b(int i2, int i3, int i4, int i5, int i6, int i7) {
        this.f5020a = i2;
        this.f5021b = i3;
        this.f5022c = i4;
        this.f5023d = i5;
        this.f5024e = i6;
        this.f5025f = i7;
    }

    public int a() {
        return this.f5020a;
    }

    public int b() {
        return this.f5021b;
    }

    public int c() {
        return this.f5022c;
    }

    public int d() {
        return this.f5023d;
    }

    public int e() {
        return this.f5024e;
    }

    public int f() {
        return this.f5025f;
    }

    public String toString() {
        return "ThreadStats{threadId=" + this.f5020a + ", threadStatus=" + this.f5021b + ", tid=" + this.f5022c + ", utime=" + this.f5023d + ", stime=" + this.f5024e + ", isDaemon=" + this.f5025f + '}';
    }
}
