package com.bytedance.sdk.account.param;

import java.util.Map;
/* compiled from: UserLoginParam.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final String f12696a;

    /* renamed from: b  reason: collision with root package name */
    public final String f12697b;

    /* renamed from: c  reason: collision with root package name */
    public final String f12698c;

    /* renamed from: d  reason: collision with root package name */
    public final String f12699d;

    /* renamed from: e  reason: collision with root package name */
    public final String f12700e;

    /* renamed from: f  reason: collision with root package name */
    public final int f12701f;

    /* renamed from: g  reason: collision with root package name */
    public final String f12702g;

    /* renamed from: h  reason: collision with root package name */
    public final Map<String, String> f12703h;

    private b(a aVar) {
        this.f12696a = aVar.f12704a;
        this.f12697b = aVar.f12705b;
        this.f12698c = aVar.f12706c;
        this.f12699d = aVar.f12707d;
        this.f12700e = aVar.f12708e;
        this.f12701f = aVar.f12709f;
        this.f12702g = aVar.f12710g;
        this.f12703h = aVar.f12711h;
    }

    /* compiled from: UserLoginParam.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private String f12704a;

        /* renamed from: b  reason: collision with root package name */
        private String f12705b;

        /* renamed from: c  reason: collision with root package name */
        private String f12706c;

        /* renamed from: d  reason: collision with root package name */
        private String f12707d;

        /* renamed from: e  reason: collision with root package name */
        private String f12708e;

        /* renamed from: f  reason: collision with root package name */
        private int f12709f;

        /* renamed from: g  reason: collision with root package name */
        private String f12710g;

        /* renamed from: h  reason: collision with root package name */
        private Map<String, String> f12711h;

        public a a(String str) {
            this.f12704a = str;
            return this;
        }

        public a b(String str) {
            this.f12705b = str;
            return this;
        }

        public a c(String str) {
            this.f12706c = str;
            return this;
        }

        public a d(String str) {
            this.f12707d = str;
            return this;
        }

        public a e(String str) {
            this.f12708e = str;
            return this;
        }

        public a a(int i2) {
            this.f12709f = i2;
            return this;
        }

        public a f(String str) {
            this.f12710g = str;
            return this;
        }

        public a a(Map<String, String> map) {
            this.f12711h = map;
            return this;
        }

        public b a() {
            return new b(this);
        }
    }
}
