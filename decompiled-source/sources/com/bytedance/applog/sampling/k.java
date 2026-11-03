package com.bytedance.applog.sampling;
/* compiled from: SamplingUser.java */
/* loaded from: classes.dex */
public class k implements b {

    /* renamed from: a  reason: collision with root package name */
    private String f3378a;

    /* renamed from: b  reason: collision with root package name */
    private String f3379b;

    /* renamed from: c  reason: collision with root package name */
    private String f3380c;

    /* renamed from: d  reason: collision with root package name */
    private String f3381d;

    /* renamed from: e  reason: collision with root package name */
    private String f3382e;

    public k(String str, String str2) {
        this.f3378a = str;
        this.f3379b = str2;
    }

    public k(String str, String str2, String str3) {
        this(str, str2);
        this.f3382e = str3;
    }

    public k(String str, String str2, String str3, String str4, String str5) {
        this(str, str2, str3);
        this.f3380c = str4;
        this.f3381d = str5;
    }

    public String f() {
        return this.f3379b;
    }

    public void a(String str) {
        this.f3379b = str;
    }

    public String g() {
        return this.f3380c;
    }

    public void b(String str) {
        this.f3380c = str;
    }

    public String h() {
        return this.f3381d;
    }

    public void c(String str) {
        this.f3381d = str;
    }

    public String i() {
        return this.f3382e;
    }

    public void d(String str) {
        this.f3382e = str;
    }

    public String j() {
        return this.f3378a;
    }

    public void e(String str) {
        this.f3378a = str;
    }

    @Override // com.bytedance.applog.sampling.b
    public String a() {
        return this.f3378a;
    }

    @Override // com.bytedance.applog.sampling.b
    public String b() {
        return this.f3379b;
    }

    @Override // com.bytedance.applog.sampling.b
    public String c() {
        return this.f3382e;
    }

    @Override // com.bytedance.applog.sampling.b
    public String d() {
        return this.f3380c;
    }

    @Override // com.bytedance.applog.sampling.b
    public String e() {
        return this.f3381d;
    }
}
