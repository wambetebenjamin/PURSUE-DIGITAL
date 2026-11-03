package com.bytedance.crash.config;

import android.text.TextUtils;
import com.bytedance.apm.constant.k;
import com.bytedance.librarian.c;
/* compiled from: NetConfig.java */
/* loaded from: classes2.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    static final String f6361a = "/monitor/collect/c/rapheal_file_collect";

    /* renamed from: b  reason: collision with root package name */
    static final String f6362b = "/monitor/collect/c/core_dump_collect";

    /* renamed from: c  reason: collision with root package name */
    static final String f6363c = "/monitor/collect/c/core_dump_upload_check";

    /* renamed from: d  reason: collision with root package name */
    public static final String f6364d = "/monitor/collect/c/exception";

    /* renamed from: e  reason: collision with root package name */
    static final String f6365e = "/monitor/collect/c/exception/dump_collection";

    /* renamed from: f  reason: collision with root package name */
    static final String f6366f = "/monitor/collect/batch/";

    /* renamed from: g  reason: collision with root package name */
    static final String f6367g = "/monitor/collect/c/exception/dump_collection";

    /* renamed from: h  reason: collision with root package name */
    public static final String f6368h = "/monitor/collect/c/crash";

    /* renamed from: i  reason: collision with root package name */
    public static final String f6369i = "/monitor/collect/c/native_bin_crash";

    /* renamed from: j  reason: collision with root package name */
    static final String f6370j = "/monitor/collect/c/logcollect";
    static final String k = "/monitor/appmonitor/v3/settings";
    static final String l = "/monitor/collect/quota_status";
    static final String m = "/monitor/collect/c/logcollect";
    static final String n = "/monitor/collect/c/biz_exception";
    private static final g o = new g();
    private String A;
    private String B;
    private String C;
    private String D;
    private String p = com.bytedance.crash.runtime.a.a();
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    public static g a() {
        return o;
    }

    private g() {
        q();
    }

    public void a(String str) {
        this.p = str;
        q();
    }

    private void q() {
        this.q = this.p + f6361a;
        this.r = this.p + f6362b;
        this.s = this.p + f6363c;
        this.t = this.p + f6368h;
        this.u = this.p + "/monitor/collect/c/exception/dump_collection";
        this.v = this.p + "/monitor/collect/c/exception";
        this.x = this.p + "/monitor/collect/c/exception/dump_collection";
        this.y = this.p + "/monitor/collect/batch/";
        this.z = this.p + f6369i;
        this.A = this.p + k.FILE_COLLECT_PATH;
        this.B = this.p + "/monitor/appmonitor/v3/settings";
        this.C = this.p + l;
        this.D = this.p + k.FILE_COLLECT_PATH;
        this.w = this.p + n;
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.v = str;
        int indexOf = str.indexOf("//");
        if (indexOf == -1) {
            this.u = str.substring(0, str.indexOf(c.a.SEPARATOR) + 1) + "monitor/collect/c/exception/dump_collection";
            this.x = str.substring(0, str.indexOf(c.a.SEPARATOR) + 1) + "monitor/collect/c/custom_exception/zip";
            return;
        }
        int i2 = indexOf + 2;
        this.u = str.substring(0, str.indexOf(c.a.SEPARATOR, i2) + 1) + "monitor/collect/c/exception/dump_collection";
        this.x = str.substring(0, str.indexOf(c.a.SEPARATOR, i2) + 1) + "monitor/collect/c/custom_exception/zip";
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.z = str;
    }

    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.A = str;
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.t = str;
    }

    public void f(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.B = str;
    }

    public static String b() {
        return o.C;
    }

    public static String c() {
        return o.B;
    }

    public static String d() {
        return o.z;
    }

    public static String e() {
        return o.u;
    }

    public static String f() {
        return o.v;
    }

    public static String g() {
        return o.w;
    }

    public static String h() {
        return o.x;
    }

    public static String i() {
        return o.y;
    }

    public static String j() {
        return o.t;
    }

    public static String k() {
        return o.q;
    }

    public static String l() {
        return o.r;
    }

    public static String m() {
        return o.s;
    }

    public static String n() {
        return d();
    }

    public static String o() {
        return o.A;
    }

    public static String p() {
        return o.D;
    }
}
