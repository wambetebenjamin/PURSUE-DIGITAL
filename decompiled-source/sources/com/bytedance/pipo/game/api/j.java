package com.bytedance.pipo.game.api;
/* compiled from: PayError.java */
/* loaded from: classes2.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private int f10103a;

    /* renamed from: b  reason: collision with root package name */
    private String f10104b;

    /* renamed from: c  reason: collision with root package name */
    private int f10105c;

    /* renamed from: d  reason: collision with root package name */
    private String f10106d;

    /* renamed from: e  reason: collision with root package name */
    private String f10107e;

    public boolean a() {
        return this.f10103a == 0;
    }

    public int b() {
        return this.f10103a;
    }

    public String c() {
        return this.f10104b;
    }

    public int d() {
        return this.f10105c;
    }

    public String e() {
        return this.f10106d;
    }

    public String f() {
        return this.f10107e;
    }

    public j(int i2, String str) {
        this.f10103a = i2;
        this.f10104b = str;
        this.f10106d = "";
        this.f10107e = "";
    }

    public j(int i2, String str, int i3, String str2) {
        this(i2, str);
        this.f10105c = i3;
        this.f10106d = str2;
    }

    public j(int i2, String str, int i3, String str2, String str3) {
        this.f10103a = i2;
        this.f10104b = str;
        this.f10105c = i3;
        this.f10106d = str2;
        this.f10107e = str3;
    }

    public String toString() {
        return "Error{errorCode=" + this.f10103a + ", errorMessage='" + this.f10104b + "', extraError=" + this.f10105c + ", extraMessage='" + this.f10106d + "', additionalInfo='" + this.f10107e + "'}";
    }
}
