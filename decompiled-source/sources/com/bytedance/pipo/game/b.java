package com.bytedance.pipo.game;

import android.app.Application;
/* compiled from: Configuration.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final Application f10197a;

    /* renamed from: b  reason: collision with root package name */
    public final String f10198b;

    /* renamed from: c  reason: collision with root package name */
    public final e f10199c;

    /* renamed from: d  reason: collision with root package name */
    public final com.bytedance.pipo.game.impl.c f10200d;

    /* renamed from: e  reason: collision with root package name */
    public final long f10201e;

    /* renamed from: f  reason: collision with root package name */
    public final String f10202f;

    /* renamed from: g  reason: collision with root package name */
    public final com.bytedance.pipo.game.api.g f10203g;

    /* renamed from: h  reason: collision with root package name */
    public final com.bytedance.pipo.game.api.f f10204h;

    private b(Application application, String str, com.bytedance.pipo.game.api.f fVar, e eVar, com.bytedance.pipo.game.api.g gVar, com.bytedance.pipo.game.api.a aVar, long j2, String str2) {
        this.f10197a = application;
        this.f10198b = str;
        this.f10204h = fVar;
        this.f10199c = eVar;
        this.f10203g = gVar;
        this.f10200d = new com.bytedance.pipo.game.impl.c(new com.bytedance.pipo.game.api.a() { // from class: com.bytedance.pipo.game.b.1
        });
        this.f10201e = j2;
        this.f10202f = str2;
        com.bytedance.pipo.game.impl.net.b.a(com.bytedance.pipo.game.impl.f.f10331a, "pipo start initTpsClient " + Thread.currentThread().getName());
        com.bytedance.pipo.game.impl.net.b.a(com.bytedance.pipo.game.impl.f.f10331a, "pipo end initTpsClient result=> " + com.bytedance.pipo.game.impl.util.b.a());
    }

    /* compiled from: Configuration.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public Application f10206a;

        /* renamed from: b  reason: collision with root package name */
        public String f10207b;

        /* renamed from: c  reason: collision with root package name */
        public String f10208c;

        /* renamed from: d  reason: collision with root package name */
        public e f10209d;

        /* renamed from: e  reason: collision with root package name */
        public com.bytedance.pipo.game.api.a f10210e;

        /* renamed from: f  reason: collision with root package name */
        public long f10211f = 0;

        /* renamed from: g  reason: collision with root package name */
        public String f10212g;

        /* renamed from: h  reason: collision with root package name */
        public com.bytedance.pipo.game.api.g f10213h;

        /* renamed from: i  reason: collision with root package name */
        public com.bytedance.pipo.game.api.f f10214i;

        public a(Application application) {
            this.f10206a = application;
        }

        public b a() {
            return new b(this.f10206a, this.f10208c, this.f10214i, this.f10209d, this.f10213h, this.f10210e, this.f10211f, this.f10212g);
        }

        public a a(com.bytedance.pipo.game.api.f fVar) {
            this.f10214i = fVar;
            return this;
        }

        public a a(String str) {
            this.f10208c = str;
            return this;
        }

        public a b(String str) {
            com.bytedance.pipo.game.impl.net.a.f10443a = str;
            return this;
        }

        public a a(e eVar) {
            this.f10209d = eVar;
            return this;
        }

        public a a(com.bytedance.pipo.game.api.a aVar) {
            this.f10210e = aVar;
            return this;
        }

        public a a(long j2) {
            this.f10211f = j2;
            return this;
        }
    }
}
