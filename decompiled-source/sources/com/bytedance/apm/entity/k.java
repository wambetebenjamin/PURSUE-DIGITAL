package com.bytedance.apm.entity;
/* compiled from: TrafficLogEntity.java */
/* loaded from: classes.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private long f1044a;

    /* renamed from: b  reason: collision with root package name */
    private int f1045b;

    /* renamed from: c  reason: collision with root package name */
    private int f1046c;

    /* renamed from: d  reason: collision with root package name */
    private int f1047d;

    /* renamed from: e  reason: collision with root package name */
    private long f1048e;

    /* renamed from: f  reason: collision with root package name */
    private long f1049f;

    public k(long j2, int i2, int i3, int i4, long j3) {
        this.f1044a = j2;
        this.f1045b = i3;
        this.f1046c = i4;
        this.f1047d = i2;
        this.f1048e = j3;
    }

    public k(long j2, int i2, int i3, int i4, long j3, long j4) {
        this.f1044a = j2;
        this.f1045b = i3;
        this.f1046c = i4;
        this.f1047d = i2;
        this.f1048e = j3;
        this.f1049f = j4;
    }

    public long getValue() {
        return this.f1044a;
    }

    public int getNetType() {
        return this.f1045b;
    }

    public int getSend() {
        return this.f1046c;
    }

    public int getFront() {
        return this.f1047d;
    }

    public long getTime() {
        return this.f1048e;
    }

    public long getSid() {
        return this.f1049f;
    }

    public String toString() {
        return "TrafficLogEntity{value=" + this.f1044a + ", netType=" + this.f1045b + ", send=" + this.f1046c + ", front=" + this.f1047d + ", time=" + this.f1048e + ", sid=" + this.f1049f + '}';
    }
}
