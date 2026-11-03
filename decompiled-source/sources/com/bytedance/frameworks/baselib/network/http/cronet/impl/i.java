package com.bytedance.frameworks.baselib.network.http.cronet.impl;

import android.text.TextUtils;
/* compiled from: TTDispatchResult.java */
/* loaded from: classes2.dex */
public class i {

    /* renamed from: h  reason: collision with root package name */
    static final /* synthetic */ boolean f7748h = true;

    /* renamed from: a  reason: collision with root package name */
    public final String f7749a;

    /* renamed from: b  reason: collision with root package name */
    public final String f7750b;

    /* renamed from: c  reason: collision with root package name */
    public final String f7751c;

    /* renamed from: d  reason: collision with root package name */
    public final String f7752d;

    /* renamed from: e  reason: collision with root package name */
    public final a f7753e;

    /* renamed from: f  reason: collision with root package name */
    public final Exception f7754f;

    /* renamed from: g  reason: collision with root package name */
    public final String f7755g;

    /* compiled from: TTDispatchResult.java */
    /* loaded from: classes2.dex */
    public enum a {
        SUCCESS,
        INVALID_ORIGIN_URL,
        CRONET_NOT_INIT,
        TIMEOUT,
        INVALID_FINAL_URL,
        WRONG_FORMAT,
        OKHTTP_DISPATCH_FAILED,
        NOT_REACHED
    }

    public i(String str, a aVar, Exception exc) {
        if (!f7748h && aVar == a.SUCCESS) {
            throw new AssertionError();
        }
        this.f7749a = str;
        this.f7750b = str;
        this.f7751c = "0";
        this.f7752d = "0";
        this.f7753e = aVar;
        this.f7754f = exc;
        this.f7755g = "";
    }

    public i(String str, String str2, String str3, String str4, a aVar, String str5) {
        this.f7749a = str;
        this.f7750b = str2;
        if (!TextUtils.isEmpty(str3)) {
            this.f7751c = str3;
        } else {
            this.f7751c = "0";
        }
        if (!TextUtils.isEmpty(str4)) {
            this.f7752d = str4;
        } else {
            this.f7752d = "0";
        }
        if (!TextUtils.isEmpty(str5)) {
            this.f7755g = str5;
        } else {
            this.f7755g = "";
        }
        this.f7753e = aVar;
        this.f7754f = null;
    }

    public String a() {
        return this.f7749a;
    }

    public String b() {
        return this.f7750b;
    }

    public String c() {
        return this.f7751c;
    }

    public String d() {
        return this.f7752d;
    }

    public String e() {
        return this.f7755g;
    }

    public a f() {
        return this.f7753e;
    }

    public Exception g() {
        return this.f7754f;
    }
}
