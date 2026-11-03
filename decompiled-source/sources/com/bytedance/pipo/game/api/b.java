package com.bytedance.pipo.game.api;

import kotlin.text.Typography;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: GooglePayResult.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10046a = 0;

    /* renamed from: b  reason: collision with root package name */
    public static final int f10047b = -1;

    /* renamed from: c  reason: collision with root package name */
    private int f10048c;

    /* renamed from: d  reason: collision with root package name */
    private int f10049d;

    /* renamed from: e  reason: collision with root package name */
    private String f10050e;

    /* renamed from: f  reason: collision with root package name */
    private l f10051f;

    /* renamed from: g  reason: collision with root package name */
    private com.bytedance.pipo.game.api.c f10052g;

    /* renamed from: h  reason: collision with root package name */
    private String f10053h;

    /* renamed from: i  reason: collision with root package name */
    private j f10054i;

    /* compiled from: GooglePayResult.java */
    /* loaded from: classes2.dex */
    public @interface a {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10055a = 0;

        /* renamed from: b  reason: collision with root package name */
        public static final int f10056b = 401;
    }

    /* compiled from: GooglePayResult.java */
    /* renamed from: com.bytedance.pipo.game.api.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public @interface InterfaceC0142b {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10057a = 0;

        /* renamed from: b  reason: collision with root package name */
        public static final int f10058b = 4011;

        /* renamed from: c  reason: collision with root package name */
        public static final int f10059c = 4012;
    }

    /* compiled from: GooglePayResult.java */
    /* loaded from: classes2.dex */
    public @interface c {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10060a = 0;

        /* renamed from: b  reason: collision with root package name */
        public static final int f10061b = 201;

        /* renamed from: c  reason: collision with root package name */
        public static final int f10062c = 202;

        /* renamed from: d  reason: collision with root package name */
        public static final int f10063d = 203;

        /* renamed from: e  reason: collision with root package name */
        public static final int f10064e = 205;

        /* renamed from: f  reason: collision with root package name */
        public static final int f10065f = 206;

        /* renamed from: g  reason: collision with root package name */
        public static final int f10066g = 207;

        /* renamed from: h  reason: collision with root package name */
        public static final int f10067h = 208;

        /* renamed from: i  reason: collision with root package name */
        public static final int f10068i = 209;

        /* renamed from: j  reason: collision with root package name */
        public static final int f10069j = 210;
    }

    /* compiled from: GooglePayResult.java */
    /* loaded from: classes2.dex */
    public @interface d {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10070a = 0;

        /* renamed from: b  reason: collision with root package name */
        public static final int f10071b = 2011;

        /* renamed from: c  reason: collision with root package name */
        public static final int f10072c = 2012;

        /* renamed from: d  reason: collision with root package name */
        public static final int f10073d = 2021;

        /* renamed from: e  reason: collision with root package name */
        public static final int f10074e = 2022;

        /* renamed from: f  reason: collision with root package name */
        public static final int f10075f = 2023;

        /* renamed from: g  reason: collision with root package name */
        public static final int f10076g = 3001;

        /* renamed from: h  reason: collision with root package name */
        public static final int f10077h = 2031;

        /* renamed from: i  reason: collision with root package name */
        public static final int f10078i = 2041;

        /* renamed from: j  reason: collision with root package name */
        public static final int f10079j = 2051;
        public static final int k = 2091;
        public static final int l = 2001;
    }

    /* compiled from: GooglePayResult.java */
    /* loaded from: classes2.dex */
    public @interface e {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10080a = 0;

        /* renamed from: b  reason: collision with root package name */
        public static final int f10081b = 301;

        /* renamed from: c  reason: collision with root package name */
        public static final int f10082c = 302;
    }

    /* compiled from: GooglePayResult.java */
    /* loaded from: classes2.dex */
    public @interface f {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10083a = -42000;

        /* renamed from: b  reason: collision with root package name */
        public static final int f10084b = -42100;
    }

    public b(int i2, int i3, String str) {
        this.f10051f = l.UNKNOWN;
        this.f10048c = i2;
        this.f10049d = i3;
        this.f10050e = str;
    }

    public b() {
        this(-1, -1, "");
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", this.f10048c);
            jSONObject.put("detailCode", this.f10049d);
            jSONObject.put("message", this.f10050e);
            jSONObject.put("payload", this.f10053h);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void a(int i2) {
        this.f10048c = i2;
    }

    public b b(int i2) {
        this.f10048c = i2;
        return this;
    }

    public b c(int i2) {
        this.f10049d = i2;
        return this;
    }

    public b a(String str) {
        this.f10050e = str;
        return this;
    }

    public b a(com.bytedance.pipo.game.api.c cVar) {
        this.f10052g = cVar;
        return this;
    }

    public b a(l lVar) {
        this.f10051f = lVar;
        return this;
    }

    public b a(j jVar) {
        this.f10054i = jVar;
        return this;
    }

    public l b() {
        return this.f10051f;
    }

    public com.bytedance.pipo.game.api.c c() {
        return this.f10052g;
    }

    public int d() {
        return this.f10049d;
    }

    public int e() {
        return this.f10048c;
    }

    public String f() {
        return this.f10050e;
    }

    public boolean g() {
        return this.f10048c == 0;
    }

    public String h() {
        return this.f10053h;
    }

    public j i() {
        return this.f10054i;
    }

    public b b(String str) {
        this.f10053h = str;
        return this;
    }

    public String j() {
        return "{ \"code\":" + this.f10048c + ",  \"detailCode\":" + this.f10049d + ",  \"message\":\"" + this.f10050e + Typography.quote + ",  \"payType\":\"" + this.f10051f;
    }

    public String toString() {
        StringBuilder append = new StringBuilder().append("{\n\t\"code\":").append(this.f10048c).append(", \n\t\"detailCode\":").append(this.f10049d).append(", \n\t\"message\":\"").append(this.f10050e).append(Typography.quote).append(", \n\t\"payType\":\"").append(this.f10051f).append(Typography.quote).append(", \n\t\"mPipoRequest\":");
        com.bytedance.pipo.game.api.c cVar = this.f10052g;
        return append.append(cVar == null ? "\"null\"" : cVar.toString()).append("\n}").toString();
    }
}
