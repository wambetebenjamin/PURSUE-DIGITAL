package com.bytedance.sdk.account.param;

import java.util.HashMap;
import java.util.Map;
/* compiled from: SendCodeParam.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f12685a;
    public final String authToken;
    public final boolean autoRead;
    public final String captcha;
    public final int checkRegister;
    public final Map<String, String> extraInfo;
    public final String mobile;
    public final String oldMobile;
    public final String sharkTicket;
    public final String ticket;
    public final int type;
    public final boolean unbindExist;
    public final String unusableMobileTicket;

    public static void a(boolean z) {
        f12685a = z;
    }

    private a(C0194a c0194a) {
        this.mobile = c0194a.f12686a;
        this.oldMobile = c0194a.f12687b;
        this.type = c0194a.f12688c;
        this.captcha = c0194a.f12689d;
        this.unbindExist = c0194a.f12690e;
        this.checkRegister = c0194a.f12691f;
        this.ticket = c0194a.f12692g;
        this.autoRead = c0194a.f12693h;
        this.sharkTicket = c0194a.f12694i;
        this.authToken = c0194a.f12695j;
        this.unusableMobileTicket = c0194a.k;
        if (f12685a) {
            if (c0194a.l == null) {
                this.extraInfo = new HashMap();
            } else {
                this.extraInfo = c0194a.l;
            }
            this.extraInfo.put("is6Digits", "1");
            return;
        }
        this.extraInfo = c0194a.l;
    }

    public static a getLoginParam(String str) {
        return new C0194a(str, 24).build();
    }

    public static a getBindParam(String str) {
        return new C0194a(str, 8).build();
    }

    /* compiled from: SendCodeParam.java */
    /* renamed from: com.bytedance.sdk.account.param.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0194a {

        /* renamed from: a  reason: collision with root package name */
        private final String f12686a;

        /* renamed from: b  reason: collision with root package name */
        private String f12687b;

        /* renamed from: c  reason: collision with root package name */
        private final int f12688c;

        /* renamed from: d  reason: collision with root package name */
        private String f12689d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f12690e;

        /* renamed from: f  reason: collision with root package name */
        private int f12691f = -1;

        /* renamed from: g  reason: collision with root package name */
        private String f12692g;

        /* renamed from: h  reason: collision with root package name */
        private boolean f12693h;

        /* renamed from: i  reason: collision with root package name */
        private String f12694i;

        /* renamed from: j  reason: collision with root package name */
        private String f12695j;
        private String k;
        private Map<String, String> l;

        public C0194a(String str, int i2) {
            this.f12686a = str;
            this.f12688c = i2;
        }

        public C0194a setOldMobile(String str) {
            this.f12687b = str;
            return this;
        }

        public C0194a setCaptcha(String str) {
            this.f12689d = str;
            return this;
        }

        public C0194a setUnbindExist(boolean z) {
            this.f12690e = z;
            return this;
        }

        public C0194a setCheckRegister(int i2) {
            this.f12691f = i2;
            return this;
        }

        public C0194a setTicket(String str) {
            this.f12692g = str;
            return this;
        }

        public C0194a setAutoRead(boolean z) {
            this.f12693h = z;
            return this;
        }

        public C0194a setSharkTicket(String str) {
            this.f12694i = str;
            return this;
        }

        public C0194a setAuthToken(String str) {
            this.f12695j = str;
            return this;
        }

        public C0194a setUnusableMobileTicket(String str) {
            this.k = str;
            return this;
        }

        public C0194a setExtraInfo(Map<String, String> map) {
            this.l = map;
            return this;
        }

        public a build() {
            return new a(this);
        }
    }
}
