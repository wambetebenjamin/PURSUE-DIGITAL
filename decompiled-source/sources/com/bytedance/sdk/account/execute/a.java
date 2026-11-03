package com.bytedance.sdk.account.execute;

import android.text.TextUtils;
import com.ss.android.account.f;
import com.ss.android.h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* compiled from: ApiRequest.java */
/* loaded from: classes2.dex */
public class a {
    public static final String METHOD_GET = "get";
    public static final String METHOD_POST = "post";
    public static final String METHOD_POST_FILE = "post_file";
    public final String filePath;
    public final List<h> headers;
    public long keepTime;
    public final String method;
    public final String paramName;
    public final Map<String, String> parms;
    public final String url;
    public boolean valueNullable;

    public a(String str, String str2, Map<String, String> map, List<h> list) {
        this.keepTime = 0L;
        this.url = str;
        this.method = str2;
        this.parms = map;
        this.headers = list;
        this.paramName = null;
        this.filePath = null;
    }

    public a(String str, String str2, Map<String, String> map, List<h> list, String str3, String str4) {
        this.keepTime = 0L;
        this.url = str;
        this.method = str2;
        this.parms = map;
        this.paramName = str3;
        this.filePath = str4;
        this.headers = list;
    }

    public String parameter(String str) {
        Map<String, String> map = this.parms;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public C0188a a() {
        return new C0188a(this);
    }

    /* compiled from: ApiRequest.java */
    /* renamed from: com.bytedance.sdk.account.execute.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0188a {

        /* renamed from: a  reason: collision with root package name */
        private String f12354a;

        /* renamed from: b  reason: collision with root package name */
        private String f12355b;

        /* renamed from: c  reason: collision with root package name */
        private Map<String, String> f12356c;

        /* renamed from: d  reason: collision with root package name */
        private List<h> f12357d;

        /* renamed from: e  reason: collision with root package name */
        private String f12358e;

        /* renamed from: f  reason: collision with root package name */
        private String f12359f;

        /* renamed from: g  reason: collision with root package name */
        private boolean f12360g;

        public C0188a() {
        }

        public C0188a(a aVar) {
            this.f12354a = aVar.url;
            this.f12355b = aVar.method;
            this.f12356c = aVar.parms;
            this.f12357d = aVar.headers;
            this.f12358e = aVar.paramName;
            this.f12359f = aVar.filePath;
            this.f12360g = aVar.valueNullable;
        }

        public C0188a url(String str) {
            this.f12354a = str;
            return this;
        }

        public C0188a file(String str, String str2) {
            if (str != null && str2 != null) {
                this.f12358e = str;
                this.f12359f = str2;
            }
            return this;
        }

        public C0188a addHeader(h hVar) {
            if (hVar == null) {
                return this;
            }
            if (this.f12357d == null) {
                this.f12357d = new ArrayList();
            }
            this.f12357d.add(hVar);
            return this;
        }

        public C0188a addHeader(String str, String str2) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                if (this.f12357d == null) {
                    this.f12357d = new ArrayList();
                }
                this.f12357d.add(new h(str, str2));
            }
            return this;
        }

        public C0188a addHeaders(List<h> list) {
            if (list != null && !list.isEmpty()) {
                if (this.f12357d == null) {
                    this.f12357d = new ArrayList();
                }
                this.f12357d.addAll(list);
            }
            return this;
        }

        public C0188a a(Map<String, String> map) {
            if (map != null && !map.isEmpty()) {
                if (this.f12357d == null) {
                    this.f12357d = new ArrayList();
                }
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    this.f12357d.add(new h(entry.getKey(), entry.getValue()));
                }
            }
            return this;
        }

        public C0188a parameter(String str, String str2) {
            if (this.f12356c == null) {
                this.f12356c = new HashMap();
            }
            this.f12356c.put(str, str2);
            return this;
        }

        public C0188a parameters(Map<String, String> map) {
            if (this.f12356c == null) {
                this.f12356c = map;
            } else if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    this.f12356c.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public C0188a parameters(Map<String, String> map, Map<String, String> map2) {
            if (this.f12356c == null) {
                this.f12356c = new HashMap();
            }
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    this.f12356c.put(entry.getKey(), entry.getValue());
                }
            }
            if (map2 != null && !map2.isEmpty()) {
                for (String str : map2.keySet()) {
                    if (!TextUtils.isEmpty(str) && map2.get(str) != null) {
                        this.f12356c.put(str, map2.get(str));
                    }
                }
            }
            return this;
        }

        public C0188a valueNullable(boolean z) {
            this.f12360g = z;
            return this;
        }

        public C0188a checkSupportMultiLogin() {
            if (this.f12356c == null) {
                this.f12356c = new HashMap();
            }
            if (f.getConfig().isSupportMultiLogin()) {
                this.f12356c.put("multi_login", "1");
            }
            return this;
        }

        public a get() {
            this.f12355b = "get";
            a aVar = new a(this.f12354a, this.f12355b, this.f12356c, this.f12357d);
            aVar.valueNullable = this.f12360g;
            return aVar;
        }

        public a post() {
            this.f12355b = a.METHOD_POST;
            a aVar = new a(this.f12354a, this.f12355b, this.f12356c, this.f12357d);
            aVar.valueNullable = this.f12360g;
            return aVar;
        }

        public a postFile() {
            this.f12355b = a.METHOD_POST_FILE;
            a aVar = new a(this.f12354a, this.f12355b, this.f12356c, this.f12357d, this.f12358e, this.f12359f);
            aVar.valueNullable = this.f12360g;
            return aVar;
        }

        public a a() {
            if (TextUtils.isEmpty(this.f12355b)) {
                this.f12355b = "get";
            }
            a aVar = new a(this.f12354a, this.f12355b, this.f12356c, this.f12357d, this.f12358e, this.f12359f);
            aVar.valueNullable = this.f12360g;
            return aVar;
        }
    }
}
