package com.bytedance.frameworks.baselib.network.http;

import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: NativeRequestInterceptor.java */
/* loaded from: classes2.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7923a = "NativeRequestInterceptor";

    /* renamed from: b  reason: collision with root package name */
    private static a f7924b;

    /* compiled from: NativeRequestInterceptor.java */
    /* loaded from: classes2.dex */
    public interface a {
        c a(String str, Map<String, String> map, List<String> list);

        d a(String str, Map<String, List<String>> map, int i2);
    }

    /* compiled from: NativeRequestInterceptor.java */
    /* loaded from: classes2.dex */
    public enum b {
        NOT_MODIFIED(0),
        URL_MODIFIED(1),
        HEADER_MODIFIED(2),
        BOTH_MODIFIED(3);
        

        /* renamed from: e  reason: collision with root package name */
        public final int f7931e;

        b(int i2) {
            this.f7931e = i2;
        }

        public int a() {
            return this.f7931e;
        }
    }

    /* compiled from: NativeRequestInterceptor.java */
    /* loaded from: classes2.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        private b f7932a;

        /* renamed from: b  reason: collision with root package name */
        private String f7933b;

        /* renamed from: c  reason: collision with root package name */
        private Map<String, String> f7934c;

        /* renamed from: d  reason: collision with root package name */
        private Set<String> f7935d;

        public c(b bVar) {
            this.f7932a = bVar;
        }

        public void a(String str) {
            this.f7933b = str;
        }

        public void a(Map<String, String> map) {
            this.f7934c = map;
        }

        public void a(Set<String> set) {
            this.f7935d = set;
        }

        public String a() {
            JSONObject jSONObject = new JSONObject();
            try {
                int i2 = AnonymousClass1.f7925a[this.f7932a.ordinal()];
                if (i2 == 1) {
                    jSONObject.put("modified", b.URL_MODIFIED.a());
                    a(jSONObject, this.f7933b);
                } else if (i2 == 2) {
                    jSONObject.put("modified", b.HEADER_MODIFIED.a());
                    a(jSONObject, this.f7934c, this.f7935d);
                } else if (i2 == 3) {
                    jSONObject.put("modified", b.BOTH_MODIFIED.a());
                    a(jSONObject, this.f7933b);
                    a(jSONObject, this.f7934c, this.f7935d);
                }
            } catch (JSONException unused) {
            }
            return jSONObject.toString();
        }

        private static void a(JSONObject jSONObject, String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                new URI(str);
                jSONObject.put("new_url", str);
            } catch (Exception unused) {
            }
        }

        private static void a(JSONObject jSONObject, Map<String, String> map, Set<String> set) {
            if (set != null) {
                JSONArray jSONArray = new JSONArray();
                for (String str : set) {
                    jSONArray.put(str);
                }
                jSONObject.put("removed_headers", jSONArray);
            }
            if (map == null) {
                return;
            }
            JSONObject jSONObject2 = new JSONObject();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!entry.getValue().isEmpty()) {
                    jSONObject2.put(entry.getKey(), entry.getValue());
                }
            }
            jSONObject.put("new_headers", jSONObject2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: NativeRequestInterceptor.java */
    /* renamed from: com.bytedance.frameworks.baselib.network.http.j$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f7925a;

        static {
            int[] iArr = new int[b.values().length];
            f7925a = iArr;
            try {
                iArr[b.URL_MODIFIED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7925a[b.HEADER_MODIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7925a[b.BOTH_MODIFIED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* compiled from: NativeRequestInterceptor.java */
    /* loaded from: classes2.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        private b f7936a;

        /* renamed from: b  reason: collision with root package name */
        private Set<String> f7937b;

        /* renamed from: c  reason: collision with root package name */
        private Map<String, List<String>> f7938c;

        public d(b bVar) {
            this.f7936a = bVar;
        }

        public void a(Set<String> set) {
            this.f7937b = set;
        }

        public void a(Map<String, List<String>> map) {
            this.f7938c = map;
        }

        public String a() {
            if (this.f7936a == b.HEADER_MODIFIED || this.f7936a == b.BOTH_MODIFIED) {
                if (this.f7938c == null && this.f7937b == null) {
                    return null;
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("modified", this.f7936a.a());
                    Map<String, List<String>> map = this.f7938c;
                    if (map != null) {
                        jSONObject.put("new_headers", b(map));
                    }
                    if (this.f7937b != null) {
                        JSONArray jSONArray = new JSONArray();
                        for (String str : this.f7937b) {
                            jSONArray.put(str);
                        }
                        jSONObject.put("removed_headers", jSONArray);
                    }
                } catch (JSONException unused) {
                }
                return jSONObject.toString();
            }
            return null;
        }

        private static JSONObject b(Map<String, List<String>> map) {
            if (map == null || map.isEmpty()) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                JSONArray jSONArray = new JSONArray();
                for (String str : entry.getValue()) {
                    if (!TextUtils.isEmpty(str)) {
                        jSONArray.put(str);
                    }
                }
                jSONObject.put(entry.getKey(), jSONArray);
            }
            return jSONObject;
        }
    }

    public static void a(a aVar) {
        f7924b = aVar;
    }

    public static String a(String str, Map<String, String> map, List<String> list) {
        if (f7924b == null) {
            return null;
        }
        if (Logger.debug()) {
            Logger.d(f7923a, "request url:" + str + " headers:" + map.toString() + " domains:" + list);
        }
        c a2 = f7924b.a(str, map, list);
        if (a2 == null) {
            return null;
        }
        String a3 = a2.a();
        if (Logger.debug()) {
            Logger.d(f7923a, "request json:" + a3);
        }
        return a3;
    }

    public static String a(String str, Map<String, List<String>> map, int i2) {
        if (f7924b == null) {
            return null;
        }
        if (Logger.debug()) {
            Logger.d(f7923a, "response url:" + str + " headers:" + map.toString() + " code:" + i2);
        }
        d a2 = f7924b.a(str, map, i2);
        if (a2 == null) {
            return null;
        }
        String a3 = a2.a();
        if (Logger.debug()) {
            Logger.d(f7923a, "response json:" + a3);
        }
        return a3;
    }
}
