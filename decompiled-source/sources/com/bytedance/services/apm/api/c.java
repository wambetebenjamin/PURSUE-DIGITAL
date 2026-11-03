package com.bytedance.services.apm.api;

import org.json.JSONObject;
/* compiled from: EventConfig.java */
/* loaded from: classes3.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private String f13268a;

    /* renamed from: b  reason: collision with root package name */
    private int f13269b;

    /* renamed from: c  reason: collision with root package name */
    private JSONObject f13270c;

    /* renamed from: d  reason: collision with root package name */
    private JSONObject f13271d;

    /* renamed from: e  reason: collision with root package name */
    private JSONObject f13272e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f13273f;

    public String getServiceName() {
        return this.f13268a;
    }

    public JSONObject getCategory() {
        return this.f13270c;
    }

    public JSONObject getMetric() {
        return this.f13271d;
    }

    public int getStatus() {
        return this.f13269b;
    }

    public JSONObject getExtraLog() {
        return this.f13272e;
    }

    public boolean isUploadImmediate() {
        return this.f13273f;
    }

    public static a builder() {
        return new a();
    }

    public c(a aVar) {
        this.f13268a = aVar.f13274a;
        this.f13269b = aVar.f13275b;
        this.f13270c = aVar.f13276c;
        this.f13271d = aVar.f13277d;
        this.f13272e = aVar.f13278e;
        this.f13273f = aVar.f13279f;
    }

    /* compiled from: EventConfig.java */
    /* loaded from: classes3.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private String f13274a;

        /* renamed from: b  reason: collision with root package name */
        private int f13275b;

        /* renamed from: c  reason: collision with root package name */
        private JSONObject f13276c;

        /* renamed from: d  reason: collision with root package name */
        private JSONObject f13277d;

        /* renamed from: e  reason: collision with root package name */
        private JSONObject f13278e;

        /* renamed from: f  reason: collision with root package name */
        private boolean f13279f;

        private a() {
        }

        public a setServiceName(String str) {
            this.f13274a = str;
            return this;
        }

        public a setStatus(int i2) {
            this.f13275b = i2;
            return this;
        }

        public a setCategory(JSONObject jSONObject) {
            this.f13276c = jSONObject;
            return this;
        }

        public a setMetric(JSONObject jSONObject) {
            this.f13277d = jSONObject;
            return this;
        }

        public a setExtraLog(JSONObject jSONObject) {
            this.f13278e = jSONObject;
            return this;
        }

        public a isUploadImmediate(boolean z) {
            this.f13279f = z;
            return this;
        }

        public c build() {
            return new c(this);
        }
    }
}
