package com.bytedance.ttnet.config;

import android.text.TextUtils;
import org.json.JSONObject;
/* compiled from: JsonOptConfig.java */
/* loaded from: classes8.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static volatile boolean f15107a = false;

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f15108b = false;

    /* renamed from: c  reason: collision with root package name */
    private static volatile a f15109c;

    /* compiled from: JsonOptConfig.java */
    /* loaded from: classes8.dex */
    public interface a {
        String a(String str, String[] strArr);
    }

    public static void a(boolean z) {
        f15107a = z;
    }

    public static boolean a() {
        return f15107a;
    }

    public static void b(boolean z) {
        f15108b = z;
    }

    public static boolean b() {
        return f15108b;
    }

    public static boolean a(Object obj) {
        if (obj instanceof String) {
            return ((String) obj).length() > 0;
        } else if (obj instanceof JSONObject) {
            return ((JSONObject) obj).length() > 0;
        } else if (obj instanceof b) {
            return ((b) obj).c();
        } else {
            return false;
        }
    }

    public static JSONObject b(Object obj) {
        if (obj instanceof String) {
            return new JSONObject((String) obj);
        }
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        if (obj instanceof b) {
            return ((b) obj).b();
        }
        return null;
    }

    public static JSONObject c(Object obj) {
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        if (obj instanceof b) {
            try {
                return ((b) obj).b().optJSONObject("data");
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return new JSONObject();
    }

    public static void a(a aVar) {
        f15109c = aVar;
    }

    public static String a(Object obj, JSONObject jSONObject, String[] strArr) {
        String str = (strArr == null || strArr.length <= 0) ? null : strArr[strArr.length - 1];
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (f15109c != null && (obj instanceof b)) {
            try {
                return f15109c.a(((b) obj).a(), strArr);
            } catch (Throwable unused) {
            }
        }
        return jSONObject.optString(str);
    }

    /* compiled from: JsonOptConfig.java */
    /* loaded from: classes8.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private String f15110a;

        /* renamed from: b  reason: collision with root package name */
        private JSONObject f15111b;

        public b(String str, JSONObject jSONObject) {
            this.f15110a = str;
            this.f15111b = jSONObject;
        }

        public String a() {
            if (TextUtils.isEmpty(this.f15110a)) {
                this.f15110a = this.f15111b.toString();
            }
            return this.f15110a;
        }

        public JSONObject b() {
            if (this.f15111b == null) {
                this.f15111b = new JSONObject(this.f15110a);
            }
            return this.f15111b;
        }

        public boolean c() {
            JSONObject jSONObject;
            return !TextUtils.isEmpty(this.f15110a) || ((jSONObject = this.f15111b) != null && jSONObject.length() > 0);
        }
    }
}
