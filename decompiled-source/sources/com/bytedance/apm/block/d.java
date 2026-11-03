package com.bytedance.apm.block;

import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BlockRecord.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: b  reason: collision with root package name */
    String f699b;

    /* renamed from: g  reason: collision with root package name */
    long f704g;

    /* renamed from: i  reason: collision with root package name */
    long f706i;
    boolean m;
    long n;
    long o;
    StackTraceElement[] p;
    StackTraceElement[] q;
    String r;
    String s;
    String t;
    JSONObject u;
    JSONObject v;
    JSONObject w;
    JSONObject x;

    /* renamed from: a  reason: collision with root package name */
    boolean f698a = true;

    /* renamed from: c  reason: collision with root package name */
    boolean f700c = false;

    /* renamed from: d  reason: collision with root package name */
    boolean f701d = false;

    /* renamed from: e  reason: collision with root package name */
    boolean f702e = false;

    /* renamed from: f  reason: collision with root package name */
    boolean f703f = false;

    /* renamed from: h  reason: collision with root package name */
    long f705h = -1;

    /* renamed from: j  reason: collision with root package name */
    boolean f707j = false;
    boolean k = false;
    volatile boolean l = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(long j2, String str) {
        this.f704g = -1L;
        this.f704g = j2;
        this.f699b = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d a() {
        d dVar = new d(this.f704g, this.f699b);
        dVar.f705h = this.f705h;
        dVar.f698a = this.f698a;
        dVar.f700c = this.f700c;
        dVar.f701d = this.f701d;
        dVar.f702e = this.f702e;
        dVar.f703f = this.f703f;
        dVar.f706i = this.f706i;
        dVar.f707j = this.f707j;
        dVar.k = this.k;
        dVar.n = this.n;
        dVar.o = this.o;
        dVar.p = this.p;
        dVar.q = this.q;
        dVar.r = this.r;
        dVar.s = this.s;
        dVar.t = this.t;
        dVar.u = this.u;
        dVar.v = this.v;
        dVar.w = this.w;
        dVar.x = this.x;
        dVar.m = this.m;
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2, String str) {
        this.f704g = j2;
        this.f699b = str;
        this.f705h = -1L;
        this.f698a = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        this.f698a = true;
        this.f700c = false;
        this.f701d = false;
        this.f702e = false;
        this.f703f = false;
        this.f706i = 0L;
        this.f707j = false;
        this.l = false;
        this.k = false;
        this.m = false;
        this.n = 0L;
        this.o = 0L;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
    }
}
