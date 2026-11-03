package com.bytedance.frameworks.baselib.network.http.util;

import java.util.Locale;
/* compiled from: HttpHost.java */
/* loaded from: classes2.dex */
public final class e implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8321a = "http";

    /* renamed from: b  reason: collision with root package name */
    protected final String f8322b;

    /* renamed from: c  reason: collision with root package name */
    protected final String f8323c;

    /* renamed from: d  reason: collision with root package name */
    protected final int f8324d;

    /* renamed from: e  reason: collision with root package name */
    protected final String f8325e;

    public e(String str, int i2, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Host name may not be null");
        }
        this.f8322b = str;
        this.f8323c = str.toLowerCase(Locale.ENGLISH);
        if (str2 != null) {
            this.f8325e = str2.toLowerCase(Locale.ENGLISH);
        } else {
            this.f8325e = "http";
        }
        this.f8324d = i2;
    }

    public e(String str, int i2) {
        this(str, i2, null);
    }

    public e(String str) {
        this(str, -1, null);
    }

    public e(e eVar) {
        this(eVar.f8322b, eVar.f8324d, eVar.f8325e);
    }

    public String a() {
        return this.f8322b;
    }

    public int b() {
        return this.f8324d;
    }

    public String c() {
        return this.f8325e;
    }

    public String d() {
        c cVar = new c(32);
        cVar.a(this.f8325e);
        cVar.a("://");
        cVar.a(this.f8322b);
        if (this.f8324d != -1) {
            cVar.a(':');
            cVar.a(Integer.toString(this.f8324d));
        }
        return cVar.toString();
    }

    public String e() {
        c cVar = new c(32);
        cVar.a(this.f8322b);
        if (this.f8324d != -1) {
            cVar.a(':');
            cVar.a(Integer.toString(this.f8324d));
        }
        return cVar.toString();
    }

    public String toString() {
        return d();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            return this.f8323c.equals(eVar.f8323c) && this.f8324d == eVar.f8324d && this.f8325e.equals(eVar.f8325e);
        }
        return false;
    }

    public int hashCode() {
        return j.a(j.a(j.a(17, this.f8323c), this.f8324d), this.f8325e);
    }

    public Object clone() {
        return super.clone();
    }
}
