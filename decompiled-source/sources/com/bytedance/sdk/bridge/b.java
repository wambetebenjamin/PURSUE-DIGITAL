package com.bytedance.sdk.bridge;
/* compiled from: BridgeConfig.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private Boolean f13130a;

    /* renamed from: b  reason: collision with root package name */
    private String f13131b;

    /* renamed from: c  reason: collision with root package name */
    private Boolean f13132c;

    /* renamed from: d  reason: collision with root package name */
    private Boolean f13133d;

    /* renamed from: e  reason: collision with root package name */
    private Boolean f13134e;

    private b(Boolean bool, String str, Boolean bool2, Boolean bool3, Boolean bool4) {
        this.f13130a = bool;
        this.f13131b = str;
        this.f13132c = bool2;
        this.f13133d = bool3;
        this.f13134e = bool4;
    }

    public Boolean isDebug() {
        Boolean bool = this.f13130a;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public String getSchema() {
        return this.f13131b;
    }

    public Boolean isIgnoreNameSpace() {
        Boolean bool = this.f13132c;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Boolean jsCallSuccessCostEnable() {
        Boolean bool = this.f13133d;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Boolean isCompatiblePreLoadWebview() {
        Boolean bool = this.f13134e;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    /* compiled from: BridgeConfig.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private String f13135a;

        /* renamed from: b  reason: collision with root package name */
        private Boolean f13136b;

        /* renamed from: c  reason: collision with root package name */
        private Boolean f13137c = true;

        /* renamed from: d  reason: collision with root package name */
        private Boolean f13138d;

        /* renamed from: e  reason: collision with root package name */
        private Boolean f13139e;

        @Deprecated
        public a setSchema(String str) {
            this.f13135a = str;
            return this;
        }

        public a isDebug(Boolean bool) {
            this.f13136b = bool;
            return this;
        }

        public a setIgnoreNameSpace(Boolean bool) {
            this.f13137c = bool;
            return this;
        }

        public a jsCallSuccessCostEnable(Boolean bool) {
            this.f13138d = bool;
            return this;
        }

        public a isCompatiblePreLoadWebview(Boolean bool) {
            this.f13139e = bool;
            return this;
        }

        public b build() {
            return new b(this.f13136b, this.f13135a, this.f13137c, this.f13138d, this.f13139e);
        }
    }
}
