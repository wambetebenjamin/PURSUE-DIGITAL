package com.bytedance.frameworks.baselib.network.http.commonparam;

import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: CommonParamConfig.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7621a = "equal_match";

    /* renamed from: b  reason: collision with root package name */
    public static final String f7622b = "prefix_match";

    /* renamed from: c  reason: collision with root package name */
    public static final String f7623c = "pattern_match";
    private static final String o = "CommonParamConfig";
    private static final String p = "host_group";
    private static final String q = "min_params_exclude";
    private static final String r = "L0_path";
    private static final String s = "L1_path";
    private static final String t = "equal_group";
    private static final String u = "prefix_group";
    private static final String v = "pattern_group";

    /* renamed from: d  reason: collision with root package name */
    private volatile boolean f7624d;

    /* renamed from: e  reason: collision with root package name */
    private volatile boolean f7625e;

    /* renamed from: f  reason: collision with root package name */
    private volatile String f7626f;

    /* renamed from: g  reason: collision with root package name */
    private CopyOnWriteArraySet<String> f7627g;

    /* renamed from: h  reason: collision with root package name */
    private CopyOnWriteArraySet<String> f7628h;

    /* renamed from: i  reason: collision with root package name */
    private CopyOnWriteArraySet<String> f7629i;

    /* renamed from: j  reason: collision with root package name */
    private CopyOnWriteArraySet<Pattern> f7630j;
    private CopyOnWriteArraySet<String> k;
    private CopyOnWriteArraySet<String> l;
    private CopyOnWriteArraySet<Pattern> m;
    private CopyOnWriteArraySet<String> n;
    private volatile boolean w;

    public a(C0108a c0108a) {
        this.f7624d = c0108a.f7631a;
        this.w = c0108a.k;
        this.f7625e = c0108a.f7632b;
        this.f7627g = c0108a.f7633c;
        this.f7628h = c0108a.f7634d;
        this.f7629i = c0108a.f7635e;
        this.f7630j = c0108a.f7636f;
        this.k = c0108a.f7637g;
        this.l = c0108a.f7638h;
        this.m = c0108a.f7639i;
        this.n = c0108a.f7640j;
    }

    public static C0108a a() {
        return new C0108a();
    }

    public boolean b() {
        return this.f7624d;
    }

    public boolean l() {
        return this.w;
    }

    public boolean c() {
        return this.f7625e;
    }

    public Set<String> d() {
        return this.f7627g;
    }

    public Set<String> e() {
        return this.f7628h;
    }

    public Set<String> f() {
        return this.f7629i;
    }

    public Set<Pattern> g() {
        return this.f7630j;
    }

    public Set<String> h() {
        return this.k;
    }

    public Set<String> i() {
        return this.l;
    }

    public Set<Pattern> j() {
        return this.m;
    }

    public Set<String> k() {
        return this.n;
    }

    /* compiled from: CommonParamConfig.java */
    /* renamed from: com.bytedance.frameworks.baselib.network.http.commonparam.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static final class C0108a {

        /* renamed from: a  reason: collision with root package name */
        private boolean f7631a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f7632b;

        /* renamed from: c  reason: collision with root package name */
        private CopyOnWriteArraySet<String> f7633c = new CopyOnWriteArraySet<>();

        /* renamed from: d  reason: collision with root package name */
        private CopyOnWriteArraySet<String> f7634d = new CopyOnWriteArraySet<>();

        /* renamed from: e  reason: collision with root package name */
        private CopyOnWriteArraySet<String> f7635e = new CopyOnWriteArraySet<>();

        /* renamed from: f  reason: collision with root package name */
        private CopyOnWriteArraySet<Pattern> f7636f = new CopyOnWriteArraySet<>();

        /* renamed from: g  reason: collision with root package name */
        private CopyOnWriteArraySet<String> f7637g = new CopyOnWriteArraySet<>();

        /* renamed from: h  reason: collision with root package name */
        private CopyOnWriteArraySet<String> f7638h = new CopyOnWriteArraySet<>();

        /* renamed from: i  reason: collision with root package name */
        private CopyOnWriteArraySet<Pattern> f7639i = new CopyOnWriteArraySet<>();

        /* renamed from: j  reason: collision with root package name */
        private CopyOnWriteArraySet<String> f7640j = new CopyOnWriteArraySet<>();
        private boolean k;

        public C0108a a(boolean z) {
            this.f7631a = z;
            return this;
        }

        public C0108a c(boolean z) {
            this.k = z;
            return this;
        }

        public C0108a b(boolean z) {
            this.f7632b = z;
            return this;
        }

        public C0108a a(List<String> list) {
            if (a.b(list)) {
                throw new IllegalArgumentException("domainFilterList can not be null or empty.");
            }
            for (String str : list) {
                this.f7633c.add(str);
            }
            return this;
        }

        public C0108a b(List<String> list) {
            if (a.b(list)) {
                throw new IllegalArgumentException("minParamsExcludeList can not be null or empty.");
            }
            for (String str : list) {
                this.f7640j.add(str);
            }
            return this;
        }

        public C0108a a(Map<String, List<String>> map) {
            if (map == null || map.isEmpty()) {
                throw new IllegalArgumentException("addMaxParamsPathFilterMap can not be null or empty.");
            }
            List<String> list = map.get(a.f7621a);
            if (!a.b(list)) {
                for (String str : list) {
                    this.f7634d.add(str);
                }
            }
            List<String> list2 = map.get(a.f7622b);
            if (!a.b(list2)) {
                for (String str2 : list2) {
                    this.f7635e.add(str2);
                }
            }
            List<String> list3 = map.get(a.f7623c);
            if (list3 != null && !list3.isEmpty()) {
                for (String str3 : list3) {
                    if (!TextUtils.isEmpty(str3)) {
                        try {
                            this.f7636f.add(Pattern.compile(str3, 2));
                        } catch (Throwable unused) {
                        }
                    }
                }
            }
            return this;
        }

        public C0108a b(Map<String, List<String>> map) {
            if (map == null || map.isEmpty()) {
                throw new IllegalArgumentException("addMinParamsPathFilterMap can not be null or empty.");
            }
            List<String> list = map.get(a.f7621a);
            if (!a.b(list)) {
                for (String str : list) {
                    this.f7637g.add(str);
                }
            }
            List<String> list2 = map.get(a.f7622b);
            if (!a.b(list2)) {
                for (String str2 : list2) {
                    this.f7638h.add(str2);
                }
            }
            List<String> list3 = map.get(a.f7623c);
            if (!a.b(list3)) {
                for (String str3 : list3) {
                    if (!TextUtils.isEmpty(str3)) {
                        try {
                            this.f7639i.add(Pattern.compile(str3, 2));
                        } catch (Throwable unused) {
                        }
                    }
                }
            }
            return this;
        }

        public a a() {
            return new a(this);
        }
    }

    public void a(String str) {
        if (Logger.debug()) {
            Logger.d(o, "onNetConfigChanged config: " + str);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!TextUtils.isEmpty(this.f7626f) && this.f7626f.equals(str)) {
            if (Logger.debug()) {
                Logger.d(o, "Config is same with local config, do not parse.");
                return;
            }
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f7626f = str;
            b(jSONObject.optJSONArray(p), this.f7627g);
            b(jSONObject.optJSONArray(q), this.n);
            JSONObject optJSONObject = jSONObject.optJSONObject(r);
            if (optJSONObject != null) {
                b(optJSONObject.optJSONArray(t), this.f7628h);
                b(optJSONObject.optJSONArray(u), this.f7629i);
                a(optJSONObject.optJSONArray(v), this.f7630j);
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject(s);
            if (optJSONObject2 != null) {
                b(optJSONObject2.optJSONArray(t), this.k);
                b(optJSONObject2.optJSONArray(u), this.l);
                a(optJSONObject2.optJSONArray(v), this.m);
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(JSONArray jSONArray, Set<Pattern> set) {
        if (jSONArray == null || set == null) {
            return;
        }
        HashSet hashSet = new HashSet();
        for (Pattern pattern : set) {
            hashSet.add(pattern.toString());
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                String string = jSONArray.getString(i2);
                if (!TextUtils.isEmpty(string) && !hashSet.contains(string)) {
                    set.add(Pattern.compile(string, 2));
                }
            } catch (JSONException unused) {
                return;
            }
        }
    }

    private static void b(JSONArray jSONArray, Set<String> set) {
        if (jSONArray == null || set == null) {
            return;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                set.add(jSONArray.getString(i2));
            } catch (JSONException unused) {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(List<?> list) {
        return list == null || list.isEmpty();
    }
}
