package com.bytedance.apm6.cpu.config;

import java.util.Map;
/* compiled from: CpuExceptionConfig.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final double f2032a = 3.0d;

    /* renamed from: b  reason: collision with root package name */
    private static final double f2033b = 6.0d;

    /* renamed from: c  reason: collision with root package name */
    private static final double f2034c = 0.05d;

    /* renamed from: j  reason: collision with root package name */
    private Map<String, Double> f2041j;
    private Map<String, Double> k;
    private Map<String, Double> l;
    private int n;
    private int o;

    /* renamed from: d  reason: collision with root package name */
    private boolean f2035d = false;

    /* renamed from: e  reason: collision with root package name */
    private boolean f2036e = false;

    /* renamed from: f  reason: collision with root package name */
    private double f2037f = f2032a;

    /* renamed from: g  reason: collision with root package name */
    private double f2038g = f2033b;

    /* renamed from: h  reason: collision with root package name */
    private double f2039h = f2034c;

    /* renamed from: i  reason: collision with root package name */
    private boolean f2040i = false;
    private boolean m = true;
    private boolean p = false;

    public boolean isOpen() {
        return this.f2035d;
    }

    public void setOpen(boolean z) {
        this.f2035d = z;
    }

    public Map<String, Double> getBackSceneMaxSpeedMap() {
        return this.f2041j;
    }

    public void setBackSceneMaxSpeedMap(Map<String, Double> map) {
        this.f2041j = map;
    }

    public Map<String, Double> getForeSceneMaxSpeedMap() {
        return this.k;
    }

    public void setForeSceneMaxSpeedMap(Map<String, Double> map) {
        this.k = map;
    }

    public boolean isCollectMainThread() {
        return this.f2036e;
    }

    public void setCollectMainThread(boolean z) {
        this.f2036e = z;
    }

    public double getMaxProcessBackCpuSpeed() {
        return this.f2037f;
    }

    public void setMaxProcessBackCpuSpeed(double d2) {
        this.f2037f = d2;
    }

    public double getMaxThreadCpuRate() {
        return this.f2039h;
    }

    public void setMaxThreadCpuRate(double d2) {
        this.f2039h = d2;
    }

    public Map<String, Double> a() {
        return this.l;
    }

    public void a(Map<String, Double> map) {
        this.l = map;
    }

    public boolean isCollectAllProcess() {
        return this.f2040i;
    }

    public void setCollectAllProcess(boolean z) {
        this.f2040i = z;
    }

    public double getMaxProcessForeCpuSpeed() {
        return this.f2038g;
    }

    public void setMaxProcessForeCpuSpeed(double d2) {
        this.f2038g = d2;
    }

    public String toString() {
        return "CpuExceptionConfig{isOpen=" + this.f2035d + ", isCollectMainThread=" + this.f2036e + ", maxProcessBackCpuSpeed=" + this.f2037f + ", maxProcessForeCpuSpeed=" + this.f2038g + ", maxThreadCpuRate=" + this.f2039h + ", isCollectAllProcess=" + this.f2040i + ", backSceneMaxSpeedMap=" + this.f2041j + ", foreSceneMaxSpeedMap=" + this.k + '}';
    }

    public boolean b() {
        return this.m;
    }

    public void a(boolean z) {
        this.m = z;
    }

    public int c() {
        return this.n;
    }

    public void a(int i2) {
        this.n = i2;
    }

    public int d() {
        return this.o;
    }

    public void b(int i2) {
        this.o = i2;
    }

    public boolean e() {
        return this.p;
    }

    public void b(boolean z) {
        this.p = z;
    }
}
