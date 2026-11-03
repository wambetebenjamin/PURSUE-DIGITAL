package com.bytedance.news.common.settings;
/* compiled from: SettingsLazyConfig.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private b f9781a;

    private d(b bVar) {
        this.f9781a = bVar;
    }

    public String getUpdateVersionCode() {
        return this.f9781a.updateVersionCode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SettingsLazyConfig.java */
    /* loaded from: classes2.dex */
    public static class b {
        public String updateVersionCode;

        private b() {
        }
    }

    /* compiled from: SettingsLazyConfig.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private String f9782a;

        public a updateVersionCode(String str) {
            this.f9782a = str;
            return this;
        }

        public d build() {
            b bVar = new b();
            bVar.updateVersionCode = this.f9782a;
            return new d(bVar);
        }
    }
}
