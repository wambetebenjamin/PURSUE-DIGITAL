package com.bytedance.bdinstall;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: InstallInfo.java */
/* loaded from: classes.dex */
public class al implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    private String f3677a = "";

    /* renamed from: b  reason: collision with root package name */
    private String f3678b = "";

    /* renamed from: c  reason: collision with root package name */
    private String f3679c;

    /* renamed from: d  reason: collision with root package name */
    private String f3680d;

    /* renamed from: e  reason: collision with root package name */
    private String f3681e;

    public String a() {
        return this.f3678b;
    }

    public String b() {
        return this.f3679c;
    }

    public String c() {
        return this.f3680d;
    }

    public String d() {
        return this.f3677a;
    }

    public void a(String str) {
        this.f3677a = str;
    }

    public void b(String str) {
        this.f3678b = str;
    }

    public void c(String str) {
        this.f3679c = str;
    }

    public void d(String str) {
        this.f3680d = str;
    }

    public String e() {
        return this.f3681e;
    }

    public void e(String str) {
        this.f3681e = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JSONObject f() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.f3677a);
            jSONObject.put("iid", this.f3678b);
            jSONObject.put("openudid", this.f3679c);
            jSONObject.put("cliend_udid", this.f3680d);
            jSONObject.put("ssid", this.f3681e);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static al f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            al alVar = new al();
            alVar.a(jSONObject.optString("did", ""));
            alVar.b(jSONObject.optString("iid", ""));
            alVar.c(jSONObject.optString("openudid", ""));
            alVar.d(jSONObject.optString("cliend_udid", ""));
            alVar.e(jSONObject.optString("ssid", ""));
            return alVar;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return "{d='" + this.f3677a + "', i='" + this.f3678b + "', o='" + this.f3679c + "', c='" + this.f3680d + "', s='" + this.f3681e + "'}";
    }

    /* renamed from: g */
    public al clone() {
        try {
            return (al) super.clone();
        } catch (CloneNotSupportedException e2) {
            u.b("clone error", e2);
            return null;
        }
    }
}
