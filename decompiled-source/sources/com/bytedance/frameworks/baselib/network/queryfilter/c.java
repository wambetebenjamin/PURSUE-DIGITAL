package com.bytedance.frameworks.baselib.network.queryfilter;

import android.os.SystemClock;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.frameworks.baselib.network.http.util.p;
import com.bytedance.librarian.c;
import com.bytedance.retrofit2.client.Request;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: QueryFilterAction.java */
/* loaded from: classes2.dex */
public abstract class c {

    /* renamed from: d  reason: collision with root package name */
    private static final String f8398d = "rm";

    /* renamed from: e  reason: collision with root package name */
    private static final String f8399e = "encrypt";

    /* renamed from: f  reason: collision with root package name */
    private static final String f8400f = "host_group";

    /* renamed from: g  reason: collision with root package name */
    private static final String f8401g = "equal_group";

    /* renamed from: h  reason: collision with root package name */
    private static final String f8402h = "prefix_group";

    /* renamed from: i  reason: collision with root package name */
    private static final String f8403i = "pattern_group";

    /* renamed from: j  reason: collision with root package name */
    private static final String f8404j = "c";

    /* renamed from: a  reason: collision with root package name */
    private int f8405a;

    /* renamed from: c  reason: collision with root package name */
    private int f8407c;

    /* renamed from: b  reason: collision with root package name */
    protected boolean f8406b = false;
    private Set<String> k = new HashSet();
    private Set<String> l = new HashSet();
    private Set<String> m = new HashSet();
    private Set<Pattern> n = new HashSet();

    protected abstract void a(JSONObject jSONObject);

    protected abstract boolean a(Request request, Map<String, List<String>> map);

    public c(int i2) {
        this.f8405a = i2;
        this.f8407c = i2;
    }

    public int a() {
        return this.f8405a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(Request request, Map<String, List<String>> map, List<a> list) {
        a aVar = new a();
        aVar.a(SystemClock.uptimeMillis());
        boolean b2 = b(request, map);
        if (b2) {
            request.setQueryFilterPriority(this.f8407c);
        }
        aVar.a(b2);
        aVar.a(this.f8405a);
        aVar.b(SystemClock.uptimeMillis());
        list.add(aVar);
        return b2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static c a(String str, int i2, int i3, JSONObject jSONObject) {
        c bVar;
        if (str.equals(f8398d)) {
            bVar = new f(i2);
        } else if (!str.equals("encrypt")) {
            return null;
        } else {
            bVar = new b(i2);
        }
        bVar.a(jSONObject);
        bVar.a(i3);
        return bVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(JSONObject jSONObject) {
        b(jSONObject.optJSONArray(f8400f), this.k);
        b(jSONObject.optJSONArray(f8401g), this.l);
        b(jSONObject.optJSONArray(f8402h), this.m);
        a(jSONObject.optJSONArray(f8403i), this.n);
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str2 : this.k) {
            if (p.a(str, str2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(Request request) {
        if (!this.f8406b || e.e().f8421a.get()) {
            if (a(this.k) || a(request.getHost())) {
                String path = request.getPath();
                if (TextUtils.isEmpty(path)) {
                    if (Logger.debug()) {
                        Logger.d(f8404j, "Path is empty: " + path);
                    }
                    return false;
                }
                int length = path.length();
                if (length > 1) {
                    int i2 = length - 1;
                    if (path.substring(i2).equals(c.a.SEPARATOR)) {
                        path = path.substring(0, i2);
                    }
                }
                if (a(this.l) || !this.l.contains(path)) {
                    if (!a(this.m)) {
                        for (String str : this.m) {
                            if (path.startsWith(str)) {
                                return true;
                            }
                        }
                    }
                    if (!a(this.n)) {
                        for (Pattern pattern : this.n) {
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
                return true;
            }
            return false;
        }
        return false;
    }

    private static void a(JSONArray jSONArray, Set<Pattern> set) {
        if (jSONArray == null || set == null) {
            return;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                String string = jSONArray.getString(i2);
                if (!TextUtils.isEmpty(string)) {
                    try {
                        set.add(Pattern.compile(string, 2));
                    } catch (Throwable unused) {
                    }
                }
            } catch (JSONException unused2) {
                return;
            }
        }
    }

    private void b(JSONArray jSONArray, Set<String> set) {
        if (jSONArray == null || set == null) {
            return;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                String string = jSONArray.getString(i2);
                if (!TextUtils.isEmpty(string)) {
                    set.add(string);
                }
            } catch (JSONException unused) {
                return;
            }
        }
    }

    private void a(int i2) {
        this.f8407c = i2;
    }

    private boolean b(Request request) {
        return request.getQueryFilterPriority() <= this.f8405a;
    }

    private boolean b(Request request, Map<String, List<String>> map) {
        if (b(request)) {
            return a(request, map);
        }
        return false;
    }

    private static boolean a(Set<?> set) {
        return set == null || set.isEmpty();
    }
}
