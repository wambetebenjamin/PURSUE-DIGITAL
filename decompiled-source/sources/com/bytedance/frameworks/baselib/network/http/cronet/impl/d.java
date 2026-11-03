package com.bytedance.frameworks.baselib.network.http.cronet.impl;

import android.os.SystemClock;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.frameworks.baselib.network.http.util.p;
import com.bytedance.retrofit2.client.Request;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: CronetSsCallConfig.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static volatile d f7704a = null;

    /* renamed from: b  reason: collision with root package name */
    private static final String f7705b = "d";

    /* renamed from: d  reason: collision with root package name */
    private static final int f7706d = 4000;

    /* renamed from: f  reason: collision with root package name */
    private static final String f7707f = "enabled_v2";

    /* renamed from: g  reason: collision with root package name */
    private static final String f7708g = "connect_interval_millis";

    /* renamed from: h  reason: collision with root package name */
    private static final String f7709h = "retry_for_not_2xx_code";

    /* renamed from: i  reason: collision with root package name */
    private static final String f7710i = "host_group";

    /* renamed from: j  reason: collision with root package name */
    private static final String f7711j = "concurrent_hosts";
    private static final String k = "equal_group";
    private static final String l = "prefix_group";
    private static final String m = "pattern_group";
    private static final String n = "match_rules";
    private static final String o = "fail_count";
    private static final String p = "forbid_seconds";
    private static final String q = "block_code_list";
    private static final String r = "bypass_rs_enabled";
    private static final String s = "rs_name";
    private static final String t = "concurrent_route";
    private static final String u = "fin_fix_enabled";
    private static final String v = "max_wait_seconds";

    /* renamed from: c  reason: collision with root package name */
    private volatile int f7712c = 0;

    /* renamed from: e  reason: collision with root package name */
    private List<a> f7713e = new CopyOnWriteArrayList();

    public static d a() {
        if (f7704a == null) {
            synchronized (d.class) {
                if (f7704a == null) {
                    f7704a = new d();
                }
            }
        }
        return f7704a;
    }

    private d() {
    }

    public boolean b() {
        return this.f7712c > 0;
    }

    public a a(Request request) {
        c();
        for (a aVar : this.f7713e) {
            if (a(request, aVar)) {
                return aVar;
            }
        }
        return null;
    }

    public boolean a(int i2, String str) {
        a b2;
        if (i2 == 0 || TextUtils.isEmpty(str) || (b2 = b(str)) == null || b2.f7719f == null) {
            return false;
        }
        return b2.f7719f.contains(Integer.valueOf(i2));
    }

    private a b(String str) {
        for (a aVar : this.f7713e) {
            if (aVar.b().equals(str)) {
                return aVar;
            }
        }
        return null;
    }

    private void c() {
        for (a aVar : this.f7713e) {
            if (aVar.l) {
                long uptimeMillis = SystemClock.uptimeMillis() - aVar.k;
                if (uptimeMillis > aVar.f7720g * 1000) {
                    aVar.l = false;
                }
                if (Logger.debug()) {
                    Logger.d(f7705b, "Rule id: " + aVar.f7722i + " has forbidden duration: " + uptimeMillis + " fail count: " + aVar.f7723j + " forbidden duration: " + (aVar.f7720g * 1000));
                }
            }
        }
    }

    private boolean a(Request request, a aVar) {
        boolean z;
        if (aVar.f7720g <= 0 || !aVar.l) {
            String url = request.getUrl();
            if (TextUtils.isEmpty(url)) {
                return false;
            }
            Iterator<String> it = aVar.f7714a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if (p.a(request.getHost(), it.next())) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                if (Logger.debug()) {
                    Logger.d(f7705b, "host not match: " + url);
                }
                return false;
            }
            String path = request.getPath();
            if (TextUtils.isEmpty(path)) {
                return false;
            }
            if (!a(aVar.f7716c)) {
                for (String str : aVar.f7716c) {
                    if (path.equals(str)) {
                        return true;
                    }
                }
            }
            if (!a(aVar.f7717d)) {
                for (String str2 : aVar.f7717d) {
                    if (path.startsWith(str2)) {
                        return true;
                    }
                }
            }
            if (!a(aVar.f7718e)) {
                for (Pattern pattern : aVar.f7718e) {
                    if (pattern != null) {
                        try {
                            if (pattern.matcher(path).matches()) {
                                return true;
                            }
                        } catch (IllegalArgumentException unused) {
                            continue;
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CronetSsCallConfig.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        final List<String> f7714a;

        /* renamed from: b  reason: collision with root package name */
        final List<String> f7715b;

        /* renamed from: c  reason: collision with root package name */
        List<String> f7716c;

        /* renamed from: d  reason: collision with root package name */
        List<String> f7717d;

        /* renamed from: e  reason: collision with root package name */
        List<Pattern> f7718e;

        /* renamed from: f  reason: collision with root package name */
        Set<Integer> f7719f;
        boolean q = false;
        int r = 0;
        volatile long k = SystemClock.uptimeMillis();

        /* renamed from: j  reason: collision with root package name */
        volatile int f7723j = 0;

        /* renamed from: h  reason: collision with root package name */
        int f7721h = Integer.MAX_VALUE;

        /* renamed from: g  reason: collision with root package name */
        int f7720g = 0;
        volatile boolean l = false;

        /* renamed from: i  reason: collision with root package name */
        String f7722i = UUID.randomUUID().toString();
        boolean n = false;
        int m = 4000;
        boolean o = true;
        String p = null;

        public a(List<String> list, List<String> list2) {
            this.f7714a = list;
            this.f7715b = list2;
        }

        public List<String> a() {
            return this.f7715b;
        }

        public String b() {
            return this.f7722i;
        }
    }

    public void a(String str) {
        if (Logger.debug()) {
            Logger.d(f7705b, "onNetConfigChanged config: " + str);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f7712c = jSONObject.optInt(f7707f, 0);
            this.f7713e.clear();
            JSONArray jSONArray = jSONObject.getJSONArray(n);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                a((JSONObject) jSONArray.get(i2));
            }
        } catch (Throwable unused) {
            if (Logger.debug()) {
                Logger.d(f7705b, "parse json config error");
            }
        }
    }

    private static boolean a(JSONObject jSONObject, String str, List<String> list) {
        JSONArray optJSONArray;
        if (jSONObject == null || TextUtils.isEmpty(str) || list == null || (optJSONArray = jSONObject.optJSONArray(str)) == null) {
            return false;
        }
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            String optString = optJSONArray.optString(i2);
            if (!TextUtils.isEmpty(optString)) {
                list.add(optString);
            }
        }
        return true;
    }

    private void a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        a(jSONObject, f7710i, arrayList);
        ArrayList arrayList2 = new ArrayList();
        a(jSONObject, f7711j, arrayList2);
        if (arrayList.isEmpty() || arrayList2.size() < 2) {
            return;
        }
        a aVar = new a(arrayList, arrayList2);
        ArrayList arrayList3 = new ArrayList();
        if (a(jSONObject, k, arrayList3)) {
            aVar.f7716c = arrayList3;
        }
        ArrayList arrayList4 = new ArrayList();
        if (a(jSONObject, l, arrayList4)) {
            aVar.f7717d = arrayList4;
        }
        ArrayList arrayList5 = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray(m);
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                String optString = optJSONArray.optString(i2);
                if (!TextUtils.isEmpty(optString)) {
                    try {
                        arrayList5.add(Pattern.compile(optString, 2));
                    } catch (Throwable unused) {
                    }
                }
            }
            aVar.f7718e = arrayList5;
        }
        aVar.f7721h = jSONObject.optInt(o, Integer.MAX_VALUE);
        aVar.f7720g = jSONObject.optInt(p, 0);
        aVar.m = jSONObject.optInt(f7708g, 4000);
        aVar.o = jSONObject.optInt(r, 1) > 0;
        aVar.p = jSONObject.optString(t);
        if (TextUtils.isEmpty(aVar.p)) {
            aVar.p = jSONObject.optString(s, null);
        }
        aVar.q = jSONObject.optInt(u, 0) > 0;
        aVar.r = jSONObject.optInt(v, 0);
        if (aVar.m <= 0) {
            return;
        }
        aVar.n = jSONObject.optInt(f7709h, 0) > 0;
        HashSet hashSet = new HashSet();
        JSONArray optJSONArray2 = jSONObject.optJSONArray(q);
        if (optJSONArray2 != null) {
            for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                hashSet.add(Integer.valueOf(optJSONArray2.optInt(i3)));
            }
            aVar.f7719f = hashSet;
        }
        this.f7713e.add(aVar);
    }

    private static boolean a(List<?> list) {
        return list == null || list.isEmpty();
    }

    private void a(a aVar, boolean z) {
        if (Logger.debug()) {
            Logger.d(f7705b, "Forbidden: " + aVar.l + " failed count: " + aVar.f7723j + " max count: " + aVar.f7721h);
        }
        if (z) {
            if (aVar.f7723j != 0 || aVar.l) {
                aVar.f7723j = 0;
                aVar.l = false;
                return;
            }
            return;
        }
        int i2 = aVar.f7723j + 1;
        aVar.f7723j = i2;
        if (i2 < aVar.f7721h) {
            return;
        }
        if (!aVar.l) {
            aVar.l = true;
            aVar.k = SystemClock.uptimeMillis();
        }
        if (Logger.debug()) {
            Logger.d(f7705b, "Forbidden: " + aVar.l + " failed count: " + aVar.f7723j);
        }
    }

    public void a(String str, boolean z) {
        a b2;
        if (TextUtils.isEmpty(str) || (b2 = b(str)) == null) {
            return;
        }
        a(b2, z);
    }
}
