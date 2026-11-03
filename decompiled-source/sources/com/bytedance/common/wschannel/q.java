package com.bytedance.common.wschannel;

import android.app.Application;
/* compiled from: WschannelSdkInitParam.java */
/* loaded from: classes2.dex */
public class q {

    /* renamed from: a  reason: collision with root package name */
    public final com.bytedance.common.wschannel.app.c f5877a;

    /* renamed from: b  reason: collision with root package name */
    private Application f5878b;

    /* renamed from: c  reason: collision with root package name */
    private com.bytedance.common.wschannel.app.g f5879c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f5880d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f5881e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f5882f;

    /* renamed from: g  reason: collision with root package name */
    private c<Boolean> f5883g;

    public q(Application application, com.bytedance.common.wschannel.app.g gVar, boolean z, boolean z2, boolean z3, com.bytedance.common.wschannel.app.c cVar) {
        this.f5878b = application;
        this.f5879c = gVar;
        this.f5880d = z;
        this.f5881e = z2;
        this.f5882f = z3;
        this.f5877a = cVar;
    }

    public Application a() {
        return this.f5878b;
    }

    public com.bytedance.common.wschannel.app.g b() {
        return this.f5879c;
    }

    public boolean c() {
        return this.f5882f;
    }

    public boolean d() {
        return this.f5880d;
    }

    public boolean e() {
        return this.f5881e;
    }

    public c<Boolean> f() {
        return this.f5883g;
    }

    public void a(c<Boolean> cVar) {
        this.f5883g = cVar;
    }

    public void a(com.bytedance.common.wschannel.app.g gVar) {
        this.f5879c = gVar;
    }

    /* compiled from: WschannelSdkInitParam.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private Application f5884a;

        /* renamed from: b  reason: collision with root package name */
        private com.bytedance.common.wschannel.app.g f5885b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f5886c = false;

        /* renamed from: d  reason: collision with root package name */
        private boolean f5887d = true;

        /* renamed from: e  reason: collision with root package name */
        private boolean f5888e = false;

        /* renamed from: f  reason: collision with root package name */
        private com.bytedance.common.wschannel.app.c f5889f;

        public a a(Application application) {
            this.f5884a = application;
            return this;
        }

        public a a(com.bytedance.common.wschannel.app.g gVar) {
            this.f5885b = gVar;
            return this;
        }

        public a a(boolean z) {
            this.f5886c = z;
            return this;
        }

        public a b(boolean z) {
            this.f5887d = z;
            return this;
        }

        public a c(boolean z) {
            this.f5888e = z;
            return this;
        }

        public a a(com.bytedance.common.wschannel.app.c cVar) {
            this.f5889f = cVar;
            return this;
        }

        public q a() {
            return new q(this.f5884a, this.f5885b, this.f5886c, this.f5887d, this.f5888e, this.f5889f);
        }
    }
}
