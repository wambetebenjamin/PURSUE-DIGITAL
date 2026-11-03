package com.bytedance.pipo.game.impl.model;

import com.bytedance.pipo.game.api.j;
/* compiled from: PipoPayResult.java */
/* loaded from: classes2.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    public j f10382a;
    @Deprecated

    /* renamed from: b  reason: collision with root package name */
    private String f10383b;

    /* renamed from: c  reason: collision with root package name */
    private String f10384c = "";

    /* renamed from: d  reason: collision with root package name */
    private String f10385d;
    @Deprecated

    /* renamed from: e  reason: collision with root package name */
    private String f10386e;
    @Deprecated

    /* renamed from: f  reason: collision with root package name */
    private String f10387f;

    /* renamed from: g  reason: collision with root package name */
    private String f10388g;

    public g() {
    }

    private g(int i2, String str) {
        this.f10382a = new j(i2, str);
    }

    public String a() {
        return this.f10384c;
    }

    @Deprecated
    public String b() {
        return this.f10386e;
    }

    @Deprecated
    public String c() {
        return this.f10387f;
    }

    public void a(String str) {
        this.f10384c = str;
    }

    public String d() {
        return this.f10385d;
    }

    public void b(String str) {
        this.f10385d = str;
    }

    @Deprecated
    public void c(String str) {
        this.f10386e = str;
    }

    @Deprecated
    public void d(String str) {
        this.f10387f = str;
    }

    public String e() {
        return this.f10388g;
    }

    public void e(String str) {
        this.f10388g = str;
    }

    public static g f() {
        return new g(0, "pay success.");
    }

    public static g a(int i2, String str, int i3, String str2) {
        g gVar = new g();
        if (str.isEmpty()) {
            gVar.f10382a = new j(i2, str);
        } else {
            gVar.f10382a = new j(i2, str, i3, str2);
        }
        return gVar;
    }

    public static g a(int i2, String str) {
        return a(i2, str, i2, null);
    }

    public static g b(int i2, String str) {
        g gVar = new g();
        gVar.f10383b = String.valueOf(i2);
        if (i2 >= 0) {
            i2 = -i2;
        }
        gVar.f10382a = new j(i2, str);
        return gVar;
    }

    public static g c(int i2, String str) {
        g gVar = new g();
        gVar.f10382a = new j(i2, str);
        gVar.f10383b = String.valueOf(i2);
        return gVar;
    }

    public static g b(int i2, String str, int i3, String str2) {
        g gVar = new g();
        gVar.f10382a = new j(i2, str, i3, str2);
        gVar.f10383b = String.valueOf(i2);
        return gVar;
    }

    public String toString() {
        return "RocketPayResult{errorInfo= " + this.f10382a + ", gameOrderId='" + this.f10384c + "', gameProductId='" + this.f10385d + "'}";
    }
}
