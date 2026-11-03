package com.bytedance.applog.log;

import java.util.List;
/* compiled from: LogInfoBuilder.java */
/* loaded from: classes.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private final j f2770a = new j();

    public k() {
        a(System.currentTimeMillis());
    }

    public k a(long j2) {
        this.f2770a.a(j2);
        return this;
    }

    public k a(String str) {
        this.f2770a.a(str);
        return this;
    }

    public k b(String str) {
        this.f2770a.b(str);
        return this;
    }

    public k a(int i2) {
        this.f2770a.a(i2);
        return this;
    }

    public k b(int i2) {
        this.f2770a.b(i2);
        return this;
    }

    public k a(List<String> list) {
        this.f2770a.a(list);
        return this;
    }

    public k c(String str) {
        this.f2770a.c(str);
        return this;
    }

    public k a(Throwable th) {
        this.f2770a.a(th);
        return this;
    }

    public j a() {
        return this.f2770a;
    }
}
