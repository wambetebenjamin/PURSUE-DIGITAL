package com.bytedance.applog.forward;

import android.text.TextUtils;
import com.bytedance.applog.log.o;
import com.bytedance.librarian.c;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: EventForward.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: d  reason: collision with root package name */
    private String f2680d;

    /* renamed from: e  reason: collision with root package name */
    private String f2681e;

    /* renamed from: g  reason: collision with root package name */
    private String f2683g;

    /* renamed from: a  reason: collision with root package name */
    private int f2677a = 0;

    /* renamed from: b  reason: collision with root package name */
    private long f2678b = 60000;

    /* renamed from: c  reason: collision with root package name */
    private final Set<String> f2679c = new HashSet();

    /* renamed from: f  reason: collision with root package name */
    private int f2682f = 0;

    public static a a(JSONObject jSONObject) {
        a aVar = new a();
        aVar.a(jSONObject.optInt("version", 0));
        int optInt = jSONObject.optInt(b.f2686c, -1);
        aVar.a(optInt >= 0 ? optInt * 1000 : 60000L);
        aVar.c(jSONObject.optString(b.f2689f));
        aVar.b(jSONObject.optString(b.f2688e));
        aVar.b(jSONObject.optInt(b.f2690g, 0));
        JSONArray optJSONArray = jSONObject.optJSONArray(b.f2687d);
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                aVar.a(optJSONArray.optString(i2));
            }
        }
        return aVar;
    }

    public int a() {
        return this.f2677a;
    }

    public void a(int i2) {
        this.f2677a = i2;
    }

    public long b() {
        return this.f2678b;
    }

    public void a(long j2) {
        this.f2678b = j2;
    }

    public Set<String> c() {
        return this.f2679c;
    }

    public void a(Set<String> set) {
        this.f2679c.addAll(set);
    }

    public void a(String str) {
        this.f2679c.add(str);
    }

    public String d() {
        return this.f2680d;
    }

    public void b(String str) {
        this.f2680d = str;
    }

    public String e() {
        return this.f2681e;
    }

    public void c(String str) {
        this.f2681e = str;
    }

    public int f() {
        return this.f2682f;
    }

    public void b(int i2) {
        this.f2682f = i2;
    }

    public boolean g() {
        return (this.f2682f != 1 || TextUtils.isEmpty(this.f2680d) || TextUtils.isEmpty(this.f2681e) || this.f2679c.isEmpty() || this.f2678b <= 0 || TextUtils.isEmpty(i())) ? false : true;
    }

    public JSONObject h() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", a());
            jSONObject.put(b.f2686c, b());
            jSONObject.put(b.f2689f, e());
            jSONObject.put(b.f2688e, d());
            jSONObject.put(b.f2690g, f());
            JSONArray jSONArray = new JSONArray();
            for (String str : c()) {
                jSONArray.put(str);
            }
            jSONObject.put(b.f2687d, jSONArray);
        } catch (Throwable th) {
            o.a().a("put json object for event forward failed", th, new Object[0]);
        }
        return jSONObject;
    }

    public boolean d(String str) {
        return this.f2679c.contains(str);
    }

    public String i() {
        if (f() == 0 || TextUtils.isEmpty(d())) {
            return null;
        }
        if (TextUtils.isEmpty(this.f2683g)) {
            String d2 = (!d().startsWith("https://") || d().endsWith(c.a.SEPARATOR)) ? null : d();
            if (TextUtils.isEmpty(d2)) {
                this.f2683g = null;
                return null;
            }
            this.f2683g = d2 + ((!TextUtils.isEmpty(e()) && e().startsWith(c.a.SEPARATOR) && e().endsWith(c.a.SEPARATOR)) ? e() : "/service/2/app_log/");
        }
        return this.f2683g;
    }

    public boolean equals(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            return aVar.a() == a() && aVar.b() == b() && TextUtils.equals(aVar.d(), d()) && TextUtils.equals(aVar.e(), e()) && aVar.f() == f() && aVar.c().containsAll(c()) && c().containsAll(aVar.c());
        }
        return super.equals(obj);
    }
}
