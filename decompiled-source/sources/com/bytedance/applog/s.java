package com.bytedance.applog;

import com.bytedance.bdinstall.ao;
import com.bytedance.bdinstall.v;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
/* compiled from: UriConfig.java */
/* loaded from: classes.dex */
public class s {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3329a = "/service/2/app_log/";

    /* renamed from: b  reason: collision with root package name */
    public static final String f3330b = "/service/2/log_settings/";

    /* renamed from: c  reason: collision with root package name */
    public static final String f3331c = "/service/2/app_log_monitor/";

    /* renamed from: d  reason: collision with root package name */
    public static final String f3332d = "/service/2/abtest_config/";
    private static boolean k = false;
    private static boolean l = false;

    /* renamed from: e  reason: collision with root package name */
    private final String[] f3333e;

    /* renamed from: f  reason: collision with root package name */
    private final String[] f3334f;

    /* renamed from: g  reason: collision with root package name */
    private final String f3335g;

    /* renamed from: h  reason: collision with root package name */
    private final String f3336h;

    /* renamed from: i  reason: collision with root package name */
    private final v f3337i;

    /* renamed from: j  reason: collision with root package name */
    private final Map<String, String> f3338j;

    public static void a(boolean z) {
        k = z;
    }

    public static void b(boolean z) {
        l = z;
    }

    public static String a() {
        return l ? "/service/2/app_log/" : f3331c;
    }

    private s(a aVar) {
        this.f3337i = aVar.f3343e;
        this.f3333e = aVar.f3339a;
        this.f3334f = aVar.f3340b;
        this.f3335g = aVar.f3341c;
        this.f3336h = aVar.f3342d;
        this.f3338j = aVar.f3344f;
    }

    /* compiled from: UriConfig.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private String[] f3339a;

        /* renamed from: b  reason: collision with root package name */
        private String[] f3340b;

        /* renamed from: c  reason: collision with root package name */
        private String f3341c;

        /* renamed from: d  reason: collision with root package name */
        private String f3342d;

        /* renamed from: e  reason: collision with root package name */
        private v f3343e;

        /* renamed from: f  reason: collision with root package name */
        private Map<String, String> f3344f = null;

        public a a(v vVar) {
            this.f3343e = vVar;
            return this;
        }

        public a a(String[] strArr) {
            if (s.k) {
                this.f3339a = (String[]) new HashSet(Arrays.asList(strArr)).toArray(new String[0]);
            } else {
                this.f3339a = strArr;
            }
            return this;
        }

        public a b(String[] strArr) {
            if (s.k) {
                this.f3340b = (String[]) new HashSet(Arrays.asList(strArr)).toArray(new String[0]);
            } else {
                this.f3340b = strArr;
            }
            return this;
        }

        public a a(String str) {
            this.f3341c = str;
            return this;
        }

        public a b(String str) {
            this.f3342d = str;
            return this;
        }

        public a a(Map<String, String> map) {
            this.f3344f = map;
            return this;
        }

        public s a() {
            return new s(this);
        }
    }

    public v b() {
        return this.f3337i;
    }

    public String[] c() {
        return this.f3333e;
    }

    public String[] d() {
        return this.f3334f;
    }

    public String e() {
        return this.f3335g;
    }

    public String f() {
        return this.f3336h;
    }

    public Map<String, String> g() {
        return this.f3338j;
    }

    public static s a(int i2) {
        return com.bytedance.applog.util.p.a(i2);
    }

    public static s a(String str, String[] strArr) {
        return a(str, strArr, true, false);
    }

    public static s a(String str, String[] strArr, boolean z, boolean z2) {
        return a(str, strArr, true, z2, null);
    }

    public static s a(String str, String[] strArr, boolean z, boolean z2, Map<String, String> map) {
        a aVar = new a();
        aVar.a(new v(ao.a(str), z, z2));
        if (strArr == null || strArr.length == 0) {
            aVar.a(new String[]{str + "/service/2/app_log/"});
        } else {
            int length = strArr.length + 1;
            String[] strArr2 = new String[length];
            strArr2[0] = str + "/service/2/app_log/";
            for (int i2 = 1; i2 < length; i2++) {
                strArr2[i2] = strArr[i2 - 1] + "/service/2/app_log/";
            }
            aVar.a(strArr2);
        }
        aVar.b(str + a());
        aVar.a(str + "/service/2/log_settings/");
        aVar.a(map);
        return aVar.a();
    }
}
