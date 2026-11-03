package com.bytedance.pipo.payment.common.lib.configuration;

import android.app.Application;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: Configuration.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public Application f10870a;

    /* renamed from: b  reason: collision with root package name */
    public com.bytedance.pipo.payment.common.lib.model.a f10871b;

    /* renamed from: c  reason: collision with root package name */
    public String f10872c;

    /* renamed from: d  reason: collision with root package name */
    public c f10873d;

    /* renamed from: e  reason: collision with root package name */
    public boolean f10874e;

    /* renamed from: f  reason: collision with root package name */
    public String f10875f;

    /* renamed from: g  reason: collision with root package name */
    public String f10876g;

    /* renamed from: h  reason: collision with root package name */
    public JSONObject f10877h;

    /* renamed from: i  reason: collision with root package name */
    public Map<String, String> f10878i;

    /* renamed from: j  reason: collision with root package name */
    public d f10879j;
    public com.bytedance.pipo.payment.common.lib.interfaze.b k;
    public com.bytedance.pipo.payment.common.lib.interfaze.d l;
    public b m;
    public com.bytedance.pipo.payment.common.lib.interfaze.c n;
    public com.bytedance.pipo.payment.common.lib.callback.c o;
    public e p;

    public a(C0159a c0159a) {
        this.f10870a = c0159a.f10880a;
        this.f10871b = c0159a.f10881b;
        this.f10872c = c0159a.f10884e;
        this.f10873d = c0159a.f10885f;
        this.f10874e = c0159a.f10886g;
        this.f10875f = c0159a.f10882c;
        this.f10876g = c0159a.f10883d;
        this.p = c0159a.f10887h;
        this.f10879j = c0159a.f10889j;
        this.f10878i = c0159a.f10888i;
        this.k = c0159a.k;
        this.l = c0159a.l;
        this.m = c0159a.m;
        this.n = c0159a.n;
        this.o = c0159a.o;
        this.f10877h = c0159a.p;
    }

    /* compiled from: Configuration.java */
    /* renamed from: com.bytedance.pipo.payment.common.lib.configuration.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0159a {

        /* renamed from: a  reason: collision with root package name */
        private Application f10880a;

        /* renamed from: b  reason: collision with root package name */
        private com.bytedance.pipo.payment.common.lib.model.a f10881b;

        /* renamed from: c  reason: collision with root package name */
        private String f10882c;

        /* renamed from: d  reason: collision with root package name */
        private String f10883d;

        /* renamed from: e  reason: collision with root package name */
        private String f10884e;

        /* renamed from: f  reason: collision with root package name */
        private c f10885f;

        /* renamed from: g  reason: collision with root package name */
        private boolean f10886g;

        /* renamed from: h  reason: collision with root package name */
        private e f10887h;

        /* renamed from: i  reason: collision with root package name */
        private Map<String, String> f10888i;

        /* renamed from: j  reason: collision with root package name */
        private d f10889j;
        private com.bytedance.pipo.payment.common.lib.interfaze.b k;
        private com.bytedance.pipo.payment.common.lib.interfaze.d l;
        private b m;
        private com.bytedance.pipo.payment.common.lib.interfaze.c n;
        private com.bytedance.pipo.payment.common.lib.callback.c o;
        private JSONObject p;

        public C0159a(Application application, com.bytedance.pipo.payment.common.lib.model.a aVar) {
            this.f10880a = application;
            this.f10881b = aVar;
        }

        public C0159a a(Application application) {
            this.f10880a = application;
            return this;
        }

        public C0159a a(com.bytedance.pipo.payment.common.lib.model.a aVar) {
            this.f10881b = aVar;
            return this;
        }

        public C0159a a(String str) {
            this.f10882c = str;
            return this;
        }

        public C0159a b(String str) {
            this.f10883d = str;
            return this;
        }

        public C0159a a(e eVar) {
            this.f10887h = eVar;
            return this;
        }

        public C0159a c(String str) {
            this.f10884e = str;
            return this;
        }

        public C0159a a(c cVar) {
            this.f10885f = cVar;
            return this;
        }

        public C0159a a(boolean z) {
            this.f10886g = z;
            return this;
        }

        public C0159a a(Map<String, String> map) {
            this.f10888i = map;
            return this;
        }

        public C0159a a(d dVar) {
            this.f10889j = dVar;
            return this;
        }

        public C0159a a(com.bytedance.pipo.payment.common.lib.interfaze.b bVar) {
            this.k = bVar;
            return this;
        }

        public C0159a a(com.bytedance.pipo.payment.common.lib.interfaze.d dVar) {
            this.l = dVar;
            return this;
        }

        public C0159a a(b bVar) {
            this.m = bVar;
            return this;
        }

        public C0159a a(com.bytedance.pipo.payment.common.lib.interfaze.c cVar) {
            this.n = cVar;
            return this;
        }

        public C0159a a(com.bytedance.pipo.payment.common.lib.callback.c cVar) {
            this.o = cVar;
            return this;
        }

        public C0159a a(JSONObject jSONObject) {
            this.p = jSONObject;
            return this;
        }

        public a a() {
            return new a(this);
        }
    }
}
