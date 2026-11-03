package com.bytedance.apm.config;

import org.json.JSONObject;
/* compiled from: EventConfig.java */
/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private String f905a;

    /* renamed from: b  reason: collision with root package name */
    private int f906b;

    /* renamed from: c  reason: collision with root package name */
    private JSONObject f907c;

    /* renamed from: d  reason: collision with root package name */
    private JSONObject f908d;

    /* renamed from: e  reason: collision with root package name */
    private JSONObject f909e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f910f;

    public String getServiceName() {
        return this.f905a;
    }

    public JSONObject getCategory() {
        return this.f907c;
    }

    public JSONObject getMetric() {
        return this.f908d;
    }

    public int getStatus() {
        return this.f906b;
    }

    public JSONObject getExtraLog() {
        return this.f909e;
    }

    public boolean isUploadImmediate() {
        return this.f910f;
    }

    public static a builder() {
        return new a();
    }

    public f(a aVar) {
        this.f905a = aVar.f911a;
        this.f906b = aVar.f912b;
        this.f907c = aVar.f913c;
        this.f908d = aVar.f914d;
        this.f909e = aVar.f915e;
        this.f910f = aVar.f916f;
    }

    /* compiled from: EventConfig.java */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private String f911a;

        /* renamed from: b  reason: collision with root package name */
        private int f912b;

        /* renamed from: c  reason: collision with root package name */
        private JSONObject f913c;

        /* renamed from: d  reason: collision with root package name */
        private JSONObject f914d;

        /* renamed from: e  reason: collision with root package name */
        private JSONObject f915e;

        /* renamed from: f  reason: collision with root package name */
        private boolean f916f;

        private a() {
        }

        public a setServiceName(String str) {
            this.f911a = str;
            return this;
        }

        public a setStatus(int i2) {
            this.f912b = i2;
            return this;
        }

        public a setCategory(JSONObject jSONObject) {
            this.f913c = jSONObject;
            return this;
        }

        public a setMetric(JSONObject jSONObject) {
            this.f914d = jSONObject;
            return this;
        }

        public a setExtraLog(JSONObject jSONObject) {
            this.f915e = jSONObject;
            return this;
        }

        public a isUploadImmediate(boolean z) {
            this.f916f = z;
            return this;
        }

        public f build() {
            return new f(this);
        }
    }
}
