package com.bytedance.crash.dumper.tools;
/* compiled from: StringDumper.java */
/* loaded from: classes2.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f6620a = {'t', 'r', 'u', 'e'};

    /* renamed from: b  reason: collision with root package name */
    private static final char[] f6621b = {'f', 'a', 'l', 's', 'e'};

    /* renamed from: c  reason: collision with root package name */
    private final a f6622c;

    public i(String str) {
        this.f6622c = a(str);
    }

    private a a(String str) {
        try {
            return new b(str);
        } catch (Throwable unused) {
            return new c(str);
        }
    }

    public i b(boolean z) {
        if (z) {
            this.f6622c.a(f6620a);
        } else {
            this.f6622c.a(f6621b);
        }
        return this;
    }

    public i b(char c2) {
        this.f6622c.a(c2);
        return this;
    }

    public i b(int i2) {
        this.f6622c.a(i2);
        return this;
    }

    public i b(long j2) {
        this.f6622c.a(j2);
        return this;
    }

    public i a(char[] cArr) {
        this.f6622c.a(cArr);
        return this;
    }

    public i a(byte[] bArr) {
        this.f6622c.a(bArr);
        return this;
    }

    public i c(String str) {
        if (str != null) {
            this.f6622c.a(str);
        }
        return this;
    }

    public i a(Throwable th) {
        c(th.getClass().getName());
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage != null) {
            b(':').b(' ').c(localizedMessage);
        }
        return this;
    }

    public i f() {
        this.f6622c.a();
        return this;
    }

    public i g() {
        this.f6622c.b();
        return this;
    }
}
