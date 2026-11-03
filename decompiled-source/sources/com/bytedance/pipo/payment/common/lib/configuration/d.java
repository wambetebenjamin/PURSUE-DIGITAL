package com.bytedance.pipo.payment.common.lib.configuration;

import android.text.TextUtils;
/* compiled from: IapConfiguration.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public String f10894a;

    /* renamed from: b  reason: collision with root package name */
    public String f10895b;

    /* renamed from: c  reason: collision with root package name */
    public com.bytedance.pipo.payment.common.lib.interfaze.a f10896c;

    /* renamed from: e  reason: collision with root package name */
    public boolean f10897e;

    /* renamed from: f  reason: collision with root package name */
    public boolean f10898f;

    /* renamed from: g  reason: collision with root package name */
    public boolean f10899g;

    /* renamed from: h  reason: collision with root package name */
    public com.bytedance.pipo.payment.common.lib.callback.e f10900h;

    public void a(d dVar) {
        com.bytedance.pipo.payment.common.lib.interfaze.a aVar;
        if (TextUtils.isEmpty(this.f10894a) && !TextUtils.isEmpty(dVar.f10894a)) {
            this.f10894a = dVar.f10894a;
        }
        if (TextUtils.isEmpty(this.f10895b) && !TextUtils.isEmpty(dVar.f10895b)) {
            this.f10895b = dVar.f10895b;
        }
        if (this.f10896c == null && (aVar = dVar.f10896c) != null) {
            this.f10896c = aVar;
        }
        this.f10897e = dVar.f10897e;
        if (!this.f10898f && dVar.f10898f) {
            this.f10898f = true;
        }
        if (this.f10899g || !dVar.f10899g) {
            return;
        }
        this.f10899g = true;
    }

    public d(a aVar) {
        this.f10894a = aVar.f10901a;
        this.f10895b = aVar.f10902b;
        this.f10896c = aVar.f10903c;
        this.f10897e = aVar.f10904d;
        this.f10898f = aVar.f10905e;
        this.f10899g = aVar.f10906f;
        this.f10900h = aVar.f10907g;
    }

    /* compiled from: IapConfiguration.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private String f10901a;

        /* renamed from: b  reason: collision with root package name */
        private String f10902b;

        /* renamed from: c  reason: collision with root package name */
        private com.bytedance.pipo.payment.common.lib.interfaze.a f10903c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f10904d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f10905e = true;

        /* renamed from: f  reason: collision with root package name */
        private boolean f10906f;

        /* renamed from: g  reason: collision with root package name */
        private com.bytedance.pipo.payment.common.lib.callback.e f10907g;

        public d a() {
            return new d(this);
        }

        public a a(String str) {
            this.f10901a = str;
            return this;
        }

        public a b(String str) {
            this.f10902b = str;
            return this;
        }

        public a a(com.bytedance.pipo.payment.common.lib.interfaze.a aVar) {
            this.f10903c = aVar;
            return this;
        }

        public a a(boolean z) {
            this.f10904d = z;
            return this;
        }

        public a b(boolean z) {
            this.f10905e = z;
            return this;
        }

        public a c(boolean z) {
            this.f10906f = z;
            return this;
        }

        public a a(com.bytedance.pipo.payment.common.lib.callback.e eVar) {
            this.f10907g = eVar;
            return this;
        }
    }
}
