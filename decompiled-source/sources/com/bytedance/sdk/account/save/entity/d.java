package com.bytedance.sdk.account.save.entity;
/* compiled from: LoginInfo.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private long f13005a;

    /* renamed from: b  reason: collision with root package name */
    private int f13006b;

    /* renamed from: c  reason: collision with root package name */
    private String f13007c;

    /* renamed from: d  reason: collision with root package name */
    private long f13008d;

    /* renamed from: e  reason: collision with root package name */
    private String f13009e;

    /* renamed from: f  reason: collision with root package name */
    private String f13010f;

    /* renamed from: g  reason: collision with root package name */
    private String f13011g;

    /* renamed from: h  reason: collision with root package name */
    private String f13012h;

    /* renamed from: i  reason: collision with root package name */
    private String f13013i;

    /* renamed from: j  reason: collision with root package name */
    private com.bytedance.sdk.account.save.entity.a f13014j;

    public d(long j2, int i2, String str, long j3, String str2, String str3, String str4, String str5, String str6, com.bytedance.sdk.account.save.entity.a aVar) {
        this.f13005a = j2;
        this.f13006b = i2;
        this.f13007c = str;
        this.f13008d = j3;
        this.f13009e = str2;
        this.f13010f = str3;
        this.f13011g = str4;
        this.f13012h = str5;
        this.f13013i = str6;
        this.f13014j = aVar;
    }

    public long getTime() {
        return this.f13005a;
    }

    public int getType() {
        return this.f13006b;
    }

    public String getInfo() {
        return this.f13007c;
    }

    public void setInfo(String str) {
        this.f13007c = str;
    }

    public void setTime(long j2) {
        this.f13005a = j2;
    }

    public void setType(int i2) {
        this.f13006b = i2;
    }

    public long getUid() {
        return this.f13008d;
    }

    public void setUid(long j2) {
        this.f13008d = j2;
    }

    public String getAvatarUrl() {
        return this.f13010f;
    }

    public void setAvatarUrl(String str) {
        this.f13010f = str;
    }

    public String getScreenName() {
        return this.f13011g;
    }

    public void setScreenName(String str) {
        this.f13011g = str;
    }

    public String getPlatformAvatarUrl() {
        return this.f13012h;
    }

    public void setPlatformAvatarUrl(String str) {
        this.f13012h = str;
    }

    public String getPlatformScreenName() {
        return this.f13013i;
    }

    public void setPlatformScreenName(String str) {
        this.f13013i = str;
    }

    public String getSecUid() {
        return this.f13009e;
    }

    public void setSecUid(String str) {
        this.f13009e = str;
    }

    public com.bytedance.sdk.account.save.entity.a getExt() {
        return this.f13014j;
    }

    public void setExt(com.bytedance.sdk.account.save.entity.a aVar) {
        this.f13014j = aVar;
    }

    public Integer getCountryCode() {
        com.bytedance.sdk.account.save.entity.a aVar = this.f13014j;
        if (aVar != null) {
            return aVar.getCountryCode();
        }
        return null;
    }

    public void setCountryCode(Integer num) {
        if (this.f13014j == null) {
            this.f13014j = new com.bytedance.sdk.account.save.entity.a();
        }
        this.f13014j.setCountryCode(num);
    }

    /* compiled from: LoginInfo.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private long f13015a;

        /* renamed from: b  reason: collision with root package name */
        private int f13016b;

        /* renamed from: c  reason: collision with root package name */
        private String f13017c;

        /* renamed from: d  reason: collision with root package name */
        private long f13018d;

        /* renamed from: e  reason: collision with root package name */
        private String f13019e;

        /* renamed from: f  reason: collision with root package name */
        private String f13020f;

        /* renamed from: g  reason: collision with root package name */
        private String f13021g;

        /* renamed from: h  reason: collision with root package name */
        private String f13022h;

        /* renamed from: i  reason: collision with root package name */
        private String f13023i;

        /* renamed from: j  reason: collision with root package name */
        private com.bytedance.sdk.account.save.entity.a f13024j;

        public a withTime(long j2) {
            this.f13015a = j2;
            return this;
        }

        public a withType(int i2) {
            this.f13016b = i2;
            return this;
        }

        public a withInfo(String str) {
            this.f13017c = str;
            return this;
        }

        public a withUid(long j2) {
            this.f13018d = j2;
            return this;
        }

        public a withAvatarUrl(String str) {
            this.f13020f = str;
            return this;
        }

        public a withSecUid(String str) {
            this.f13019e = str;
            return this;
        }

        public a withScreenName(String str) {
            this.f13021g = str;
            return this;
        }

        public a withPlatformAvatarUrl(String str) {
            this.f13022h = str;
            return this;
        }

        public a withPlatformScreenName(String str) {
            this.f13023i = str;
            return this;
        }

        public a withExt(com.bytedance.sdk.account.save.entity.a aVar) {
            this.f13024j = aVar;
            return this;
        }

        public a withCountryCode(Integer num) {
            if (this.f13024j == null) {
                this.f13024j = new com.bytedance.sdk.account.save.entity.a();
            }
            this.f13024j.setCountryCode(num);
            return this;
        }

        public d build() {
            return new d(this.f13015a, this.f13016b, this.f13017c, this.f13018d, this.f13019e, this.f13020f, this.f13021g, this.f13022h, this.f13023i, this.f13024j);
        }
    }
}
