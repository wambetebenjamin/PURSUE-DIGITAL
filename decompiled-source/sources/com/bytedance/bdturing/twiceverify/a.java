package com.bytedance.bdturing.twiceverify;
/* compiled from: TwiceVerifyConfig.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private int f4347a;

    /* renamed from: b  reason: collision with root package name */
    private int f4348b;

    /* renamed from: c  reason: collision with root package name */
    private String f4349c;

    /* renamed from: d  reason: collision with root package name */
    private String f4350d;

    /* renamed from: e  reason: collision with root package name */
    private String f4351e;

    /* renamed from: f  reason: collision with root package name */
    private String f4352f;

    private a(C0054a c0054a) {
        this.f4347a = 0;
        this.f4348b = -1;
        this.f4347a = c0054a.f4353a;
        this.f4348b = c0054a.f4354b;
        this.f4349c = c0054a.f4355c;
        this.f4350d = c0054a.f4356d;
        this.f4351e = c0054a.f4357e;
        this.f4352f = c0054a.f4358f;
    }

    public int a() {
        return this.f4347a;
    }

    public int b() {
        return this.f4348b;
    }

    public String c() {
        return this.f4349c;
    }

    public String d() {
        return this.f4350d;
    }

    public String e() {
        return this.f4351e;
    }

    public String f() {
        return this.f4352f;
    }

    /* compiled from: TwiceVerifyConfig.java */
    /* renamed from: com.bytedance.bdturing.twiceverify.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0054a {

        /* renamed from: a  reason: collision with root package name */
        private int f4353a = 0;

        /* renamed from: b  reason: collision with root package name */
        private int f4354b = -1;

        /* renamed from: c  reason: collision with root package name */
        private String f4355c;

        /* renamed from: d  reason: collision with root package name */
        private String f4356d;

        /* renamed from: e  reason: collision with root package name */
        private String f4357e;

        /* renamed from: f  reason: collision with root package name */
        private String f4358f;

        public C0054a a(int i2) {
            this.f4353a = i2;
            return this;
        }

        public C0054a b(int i2) {
            this.f4354b = i2;
            return this;
        }

        public C0054a a(String str) {
            this.f4355c = str;
            return this;
        }

        public C0054a b(String str) {
            this.f4356d = str;
            return this;
        }

        public C0054a c(String str) {
            this.f4357e = str;
            return this;
        }

        public C0054a d(String str) {
            this.f4358f = str;
            return this;
        }

        public a a() {
            return new a(this);
        }
    }
}
