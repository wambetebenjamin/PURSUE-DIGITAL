package com.bytedance.apm6.cpu.collect;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* compiled from: CpuCacheItem.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private EnumC0031a f1951a;

    /* renamed from: b  reason: collision with root package name */
    private double f1952b;

    /* renamed from: c  reason: collision with root package name */
    private double f1953c;

    /* renamed from: d  reason: collision with root package name */
    private double f1954d;

    /* renamed from: e  reason: collision with root package name */
    private double f1955e;

    /* renamed from: f  reason: collision with root package name */
    private String f1956f;

    /* renamed from: g  reason: collision with root package name */
    private long f1957g;

    /* renamed from: h  reason: collision with root package name */
    private int f1958h;

    /* compiled from: CpuCacheItem.java */
    /* renamed from: com.bytedance.apm6.cpu.collect.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public enum EnumC0031a {
        MIX,
        FRONT,
        BACK
    }

    public a(EnumC0031a enumC0031a, long j2) {
        this.f1958h = 0;
        this.f1951a = enumC0031a;
        this.f1957g = j2;
        this.f1958h = 0;
    }

    public a injectScene(String str) {
        this.f1956f = str;
        return this;
    }

    public EnumC0031a getType() {
        return this.f1951a;
    }

    public double getMetricRate() {
        return this.f1952b;
    }

    public double getMetricMaxRate() {
        return this.f1953c;
    }

    public double getMetricCpuSpeed() {
        return this.f1954d;
    }

    public double getMetricMaxCpuSpeed() {
        return this.f1955e;
    }

    public String getSceneString() {
        return this.f1956f;
    }

    public long getFirstTs() {
        return this.f1957g;
    }

    public int getTimes() {
        return this.f1958h;
    }

    public void addTimes() {
        this.f1958h++;
    }

    public void addRate(double d2) {
        if (d2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return;
        }
        this.f1952b += d2;
    }

    public void refreshMaxRate(double d2) {
        if (this.f1953c < d2) {
            this.f1953c = d2;
        }
    }

    public void addSpeed(double d2) {
        if (d2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return;
        }
        this.f1954d += d2;
    }

    public void refreshMaxSpeed(double d2) {
        if (this.f1955e < d2) {
            this.f1955e = d2;
        }
    }

    public String toString() {
        return "CpuCacheItem{type=" + this.f1951a + ", metricRate=" + this.f1952b + ", metricMaxRate=" + this.f1953c + ", metricCpuStats=" + this.f1954d + ", metricMaxCpuStats=" + this.f1955e + ", sceneString='" + this.f1956f + "', firstTs=" + this.f1957g + ", times=" + this.f1958h + '}';
    }
}
