package com.bytedance.bdinstall;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AppVersionCompat.java */
/* loaded from: classes.dex */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    private final String f3832a;

    /* renamed from: b  reason: collision with root package name */
    private final String f3833b;

    /* renamed from: c  reason: collision with root package name */
    private final long f3834c;

    /* renamed from: d  reason: collision with root package name */
    private final long f3835d;

    /* renamed from: e  reason: collision with root package name */
    private final long f3836e;

    /* renamed from: f  reason: collision with root package name */
    private final Context f3837f;

    /* renamed from: g  reason: collision with root package name */
    private final com.bytedance.bdinstall.util.r<PackageInfo> f3838g = new com.bytedance.bdinstall.util.r<PackageInfo>() { // from class: com.bytedance.bdinstall.i.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.bdinstall.util.r
        /* renamed from: a */
        public PackageInfo b(Object... objArr) {
            try {
                return i.this.f3837f.getPackageManager().getPackageInfo(i.this.f3837f.getPackageName(), 0);
            } catch (Throwable th) {
                u.b("AppVersionCompat#getPackageInfo error", th);
                return null;
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(o oVar) {
        this.f3837f = oVar.n;
        this.f3832a = oVar.q;
        this.f3833b = TextUtils.isEmpty(oVar.r) ? "" : oVar.r;
        this.f3834c = oVar.s;
        this.f3835d = oVar.t;
        this.f3836e = oVar.u;
    }

    public String a() {
        PackageInfo c2;
        if (TextUtils.isEmpty(this.f3832a) && (c2 = this.f3838g.c(new Object[0])) != null) {
            return c2.versionName;
        }
        return this.f3832a;
    }

    public String b() {
        return this.f3833b;
    }

    public long c() {
        PackageInfo c2;
        if (this.f3834c == 0 && (c2 = this.f3838g.c(new Object[0])) != null) {
            return c2.versionCode;
        }
        return this.f3834c;
    }

    public long d() {
        PackageInfo c2;
        if (this.f3835d == 0 && (c2 = this.f3838g.c(new Object[0])) != null) {
            return c2.versionCode;
        }
        return this.f3835d;
    }

    public long e() {
        PackageInfo c2;
        if (this.f3836e == 0 && (c2 = this.f3838g.c(new Object[0])) != null) {
            return c2.versionCode;
        }
        return this.f3836e;
    }
}
