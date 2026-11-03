package com.bytedance.sdk.bridge;
/* compiled from: BridgeLazyConfig.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private b f13142a;

    private d(b bVar) {
        this.f13142a = bVar;
    }

    /* compiled from: BridgeLazyConfig.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private int f13143a;

        /* renamed from: b  reason: collision with root package name */
        private String f13144b;

        /* renamed from: c  reason: collision with root package name */
        private String f13145c;

        /* renamed from: d  reason: collision with root package name */
        private String f13146d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f13147e;

        /* renamed from: f  reason: collision with root package name */
        private String f13148f;

        public a aid(int i2) {
            this.f13143a = i2;
            return this;
        }

        public a appVersion(String str) {
            this.f13144b = str;
            return this;
        }

        public a deviceId(String str) {
            this.f13145c = str;
            return this;
        }

        public a accessKey(String str) {
            this.f13146d = str;
            return this;
        }

        public a newAuthRequestEnable(boolean z) {
            this.f13147e = z;
            return this;
        }

        public a localFileUrl(String str) {
            this.f13148f = str;
            return this;
        }

        public d build() {
            b bVar = new b();
            bVar.accessKey = this.f13146d;
            bVar.aid = this.f13143a;
            bVar.appVersion = this.f13144b;
            bVar.deviceId = this.f13145c;
            bVar.newAuthRequestEnable = this.f13147e;
            bVar.localFileUrl = this.f13148f;
            return new d(bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BridgeLazyConfig.java */
    /* loaded from: classes2.dex */
    public static class b {
        public String accessKey;
        public int aid;
        public String appVersion;
        public String deviceId;
        public String localFileUrl;
        public boolean newAuthRequestEnable;

        private b() {
        }
    }

    public int getAid() {
        return this.f13142a.aid;
    }

    public String getAppVersion() {
        return this.f13142a.appVersion;
    }

    public String getDeviceId() {
        return this.f13142a.deviceId;
    }

    public String getAccessKey() {
        return this.f13142a.accessKey;
    }

    public boolean isNewAuthRequestEnable() {
        return this.f13142a.newAuthRequestEnable;
    }

    public String getLocalFileUrl() {
        return this.f13142a.localFileUrl;
    }
}
