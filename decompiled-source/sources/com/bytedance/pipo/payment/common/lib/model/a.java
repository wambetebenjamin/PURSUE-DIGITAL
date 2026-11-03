package com.bytedance.pipo.payment.common.lib.model;
/* compiled from: AppInfo.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private int f10918a;

    /* renamed from: b  reason: collision with root package name */
    private String f10919b;

    /* renamed from: c  reason: collision with root package name */
    private int f10920c;

    /* renamed from: d  reason: collision with root package name */
    private int f10921d;

    /* renamed from: e  reason: collision with root package name */
    private String f10922e;

    /* renamed from: f  reason: collision with root package name */
    private String f10923f;

    /* renamed from: g  reason: collision with root package name */
    private String f10924g;

    /* renamed from: h  reason: collision with root package name */
    private InterfaceC0160a f10925h;

    /* renamed from: i  reason: collision with root package name */
    private InterfaceC0160a f10926i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f10927j;
    private String k;
    private String l;

    /* compiled from: AppInfo.java */
    /* renamed from: com.bytedance.pipo.payment.common.lib.model.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0160a {
        String a();
    }

    public int a() {
        return this.f10918a;
    }

    public void a(int i2) {
        this.f10918a = i2;
    }

    public String b() {
        return this.f10919b;
    }

    public a a(String str) {
        this.f10919b = str;
        return this;
    }

    public int c() {
        return this.f10920c;
    }

    public a b(int i2) {
        this.f10920c = i2;
        return this;
    }

    public int d() {
        return this.f10921d;
    }

    public a c(int i2) {
        this.f10921d = i2;
        return this;
    }

    public String e() {
        return this.f10922e;
    }

    public void b(String str) {
        this.f10922e = str;
    }

    public String f() {
        return this.f10924g;
    }

    public void c(String str) {
        this.f10924g = str;
    }

    public boolean g() {
        return this.f10927j;
    }

    public void a(boolean z) {
        this.f10927j = z;
    }

    public String h() {
        return this.k;
    }

    public void d(String str) {
        this.k = str;
    }

    public String i() {
        return this.l;
    }

    public void e(String str) {
        this.l = str;
    }

    public String toString() {
        return "AppInfo{aid=" + this.f10918a + ", appVersionName='" + this.f10919b + "', versionCode=" + this.f10920c + ", updateVersionCode=" + this.f10921d + ", channel='" + this.f10922e + "', appName='" + this.f10923f + "', appPackageName='" + this.f10924g + "', isI18n=" + this.f10927j + ", netType=" + this.k + ", carrier=" + this.l + '}';
    }

    public String j() {
        return this.f10923f;
    }

    public void f(String str) {
        this.f10923f = str;
    }

    public InterfaceC0160a k() {
        return this.f10926i;
    }

    public void a(InterfaceC0160a interfaceC0160a) {
        this.f10926i = interfaceC0160a;
    }

    public void b(InterfaceC0160a interfaceC0160a) {
        this.f10925h = interfaceC0160a;
    }

    public InterfaceC0160a l() {
        return this.f10925h;
    }
}
