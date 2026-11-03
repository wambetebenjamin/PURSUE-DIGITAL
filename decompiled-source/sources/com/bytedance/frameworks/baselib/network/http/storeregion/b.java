package com.bytedance.frameworks.baselib.network.http.storeregion;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.apm.constant.r;
import com.bytedance.common.utility.Logger;
import com.bytedance.frameworks.baselib.network.http.util.o;
import com.bytedance.frameworks.baselib.network.http.util.p;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.client.Request;
import com.meituan.robust.Constants;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: StoreRegionManager.java */
/* loaded from: classes2.dex */
public class b {
    private static final String G = "update_region_info";
    private static final String I = "received_region_config";

    /* renamed from: J  reason: collision with root package name */
    private static final String f8289J = "local";
    private static boolean P = false;

    /* renamed from: a  reason: collision with root package name */
    public static final String f8290a = "x-tt-store-region";

    /* renamed from: b  reason: collision with root package name */
    public static final String f8291b = "x-tt-store-region-src";

    /* renamed from: c  reason: collision with root package name */
    public static final String f8292c = "Set-Cookie";

    /* renamed from: d  reason: collision with root package name */
    public static final String f8293d = "store-country-code=";

    /* renamed from: e  reason: collision with root package name */
    public static final String f8294e = "store-country-code-src=";

    /* renamed from: f  reason: collision with root package name */
    public static final String f8295f = "store-region=";

    /* renamed from: g  reason: collision with root package name */
    public static final String f8296g = "x-tt-tnc-attr";

    /* renamed from: h  reason: collision with root package name */
    public static final String f8297h = "x-tt-with-tnc";

    /* renamed from: i  reason: collision with root package name */
    public static final String f8298i = "x-ss-etag";

    /* renamed from: j  reason: collision with root package name */
    public static final String f8299j = "x-tt-tnc-config";
    static final /* synthetic */ boolean k = true;
    private static volatile b l = null;
    private static final String m = "StoreRegionManager";
    private static final String n = "update_store_idc_path_list";
    private static final String o = "add_store_idc_host_list";
    private static final String p = "ttnet_store_region";
    public static final String q = "store-region-src=";
    private static final String r = "store_region";
    public static final String s = "region_legacy_user=";
    public static final String t = "x-tt-store-sec-uid";
    private static final String u = "store_region_src";
    public static final String v = "x-tt-logid";
    public static final String x = "x-tt-app-init-region";
    private static final String y = "store_sec_uid";
    private static final String z = "region_legacy_user";
    private Context E;
    private boolean F;
    private com.bytedance.frameworks.baselib.network.http.storeregion.a H;
    private boolean O;
    private String w = "";
    private String A = "";
    private String K = "";
    private String L = "";
    private String B = "";
    private String M = "";
    private String N = "";
    private List<String> C = new ArrayList();
    private List<String> D = new ArrayList();

    /* compiled from: StoreRegionManager.java */
    /* loaded from: classes2.dex */
    public interface a {
        void notifyStoreRegionUpdatedForCronet(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11);
    }

    public static String j() {
        return p;
    }

    public static b a() {
        if (l == null) {
            synchronized (b.class) {
                if (l == null) {
                    l = new b();
                }
            }
        }
        return l;
    }

    private b() {
    }

    public void f() {
        if (this.O) {
            return;
        }
        this.O = true;
        SharedPreferences.Editor edit = this.E.getSharedPreferences(p, 0).edit();
        edit.putBoolean(I, true);
        edit.apply();
    }

    public boolean b() {
        return this.F;
    }

    public String c() {
        return this.w;
    }

    public String d() {
        return this.A;
    }

    public String h() {
        return this.K;
    }

    public void a(String str, String str2, String str3, Context context, com.bytedance.frameworks.baselib.network.http.storeregion.a aVar) {
        if (Logger.debug()) {
            Logger.d(m, "rule json: " + str2);
        }
        if (TextUtils.isEmpty(str2) || context == null || aVar == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            JSONArray optJSONArray = jSONObject.optJSONArray(n);
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                String string = optJSONArray.getString(i2);
                if (!TextUtils.isEmpty(string)) {
                    this.C.add(string);
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray(o);
            for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                String string2 = optJSONArray2.getString(i3);
                if (!TextUtils.isEmpty(string2)) {
                    this.D.add(string2);
                }
            }
            this.E = context;
            this.H = aVar;
            if (!TextUtils.isEmpty(str)) {
                this.B = str.toLowerCase();
            }
            if (this.D.isEmpty() || this.C.isEmpty()) {
                return;
            }
            this.F = true;
            this.N = str3;
            e();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void e() {
        SharedPreferences sharedPreferences = this.E.getSharedPreferences(p, 0);
        this.w = sharedPreferences.getString("store_region", "");
        this.A = sharedPreferences.getString(u, "");
        this.K = sharedPreferences.getString("region_legacy_user", "");
        this.L = sharedPreferences.getString(y, "");
        this.M = sharedPreferences.getString(G, "");
        this.O = sharedPreferences.getBoolean(I, false);
        if (TextUtils.isEmpty(this.w) && !TextUtils.isEmpty(this.B)) {
            this.w = this.B;
            this.A = "local";
        }
        this.H.a("", this.w, this.A, this.K, this.L, "");
        if (Logger.debug()) {
            Logger.d(m, "Init region:" + this.w + " sec_uid:" + this.L + " source:" + this.A + " local:" + this.B + " init region:" + this.N + " region config:" + this.O);
        }
    }

    private boolean a(Request request, Request.Builder builder) {
        boolean z2;
        if (TextUtils.isEmpty(request.getPath())) {
            return false;
        }
        if ("/get_domains/v5/".equals(request.getPath())) {
            return true;
        }
        Iterator<String> it = this.C.iterator();
        while (true) {
            if (!it.hasNext()) {
                z2 = false;
                break;
            }
            if (p.a(request.getPath(), it.next())) {
                z2 = true;
                break;
            }
        }
        if (z2) {
            o oVar = new o(request.getUrl());
            oVar.a("okhttp_version", "4.2.243.51-gsdk");
            oVar.a("use_store_region_cookie", "1");
            builder.url(oVar.d());
            return true;
        }
        return false;
    }

    public Request a(Request request) {
        boolean z2;
        String str;
        if (!this.F || TextUtils.isEmpty(request.getHost()) || request.isPureRequest()) {
            return null;
        }
        boolean z3 = false;
        Iterator<String> it = this.D.iterator();
        while (true) {
            z2 = true;
            if (!it.hasNext()) {
                break;
            }
            if (p.a(request.getHost(), it.next())) {
                z3 = true;
                break;
            }
        }
        ArrayList arrayList = new ArrayList();
        if (this.O || TextUtils.isEmpty(this.N)) {
            z2 = z3;
        } else {
            arrayList.add(new Header(x, this.N));
        }
        if (z2) {
            if (request.getHeaders() != null) {
                arrayList.addAll(request.getHeaders());
            }
            Request.Builder newBuilder = request.newBuilder();
            if (a(request, newBuilder)) {
                newBuilder.headers(arrayList);
                return newBuilder.build();
            }
            if (!this.w.isEmpty()) {
                str = this.w;
            } else {
                str = !this.B.isEmpty() ? this.B : "";
            }
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(new Header(f8290a, str));
            }
            if (!TextUtils.isEmpty(this.A)) {
                arrayList.add(new Header(f8291b, this.A));
            }
            newBuilder.headers(arrayList);
            return newBuilder.build();
        }
        return null;
    }

    public void a(Map<String, String> map) {
        if (!this.F || map == null || this.O || TextUtils.isEmpty(this.N)) {
            return;
        }
        map.put(x, this.N);
    }

    public void a(Response response, String str, byte[] bArr) {
        boolean z2;
        if (this.F) {
            try {
                URI a2 = p.a(str);
                String str2 = a2.getHost() + a2.getPath();
                if (a2 == null || TextUtils.isEmpty(a2.getPath()) || !b(a2.getPath())) {
                    return;
                }
                C0117b a3 = a(response.headers("Set-Cookie"));
                String header = response.header(f8298i);
                String header2 = response.header(f8296g);
                String header3 = response.header(f8297h);
                JSONObject jSONObject = null;
                if (!TextUtils.isEmpty(header3) && header3.equals("1")) {
                    try {
                        jSONObject = new JSONObject(new String(bArr)).getJSONObject("tnc_data");
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                if (a3 == null && jSONObject != null) {
                    this.H.a(jSONObject, header, header2, false);
                } else if (a3 == null) {
                } else {
                    if (Logger.debug()) {
                        Logger.d(m, "origin region:" + this.w + " sec_uid:" + this.L + " source:" + this.A + " local:" + this.B);
                    }
                    boolean z3 = !this.w.equals(a3.f8300a);
                    String header4 = response.header(t);
                    if (header4 == null) {
                        header4 = "";
                    }
                    SharedPreferences.Editor edit = this.E.getSharedPreferences(p, 0).edit();
                    if (this.w.equals(a3.f8300a) && this.A.equals(a3.f8301b) && header4.equals(this.L)) {
                        z2 = false;
                    } else {
                        this.w = a3.f8300a;
                        this.A = a3.f8301b;
                        this.L = header4;
                        edit.putString("store_region", this.w);
                        edit.putString(u, this.A);
                        edit.putString(y, this.L);
                        z2 = true;
                    }
                    if (!this.K.equals(a3.f8302c)) {
                        String str3 = a3.f8302c;
                        this.K = str3;
                        edit.putString("region_legacy_user", str3);
                        z2 = true;
                    }
                    if (Logger.debug()) {
                        Logger.d(m, "update region:" + this.w + " sec_uid:" + this.L + " source:" + this.A + " regionLegacyUser:" + this.K + "local:" + this.B);
                        Logger.d(m, "save config:" + z2 + " update tnc:" + z3);
                    }
                    boolean a4 = this.H.a(jSONObject, header, header2, z3);
                    if (z2) {
                        String header5 = response.header("x-tt-logid");
                        String str4 = a2.getPath() + "," + header5 + "," + a3.f8300a + "," + a3.f8301b;
                        this.M = str4;
                        edit.putString(G, str4);
                        edit.apply();
                        this.H.a(a(header5, str2, a4), com.bytedance.ttnet.cronet.b.f15233a);
                        this.H.a("", this.w, this.A, this.K, this.L, header5);
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6) {
        if (this.E == null || !this.F || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        if (!k && (this.w == null || this.A == null)) {
            throw new AssertionError();
        }
        if (this.w.equalsIgnoreCase(str2) && this.A.equalsIgnoreCase(str3) && this.L.equalsIgnoreCase(str5)) {
            return;
        }
        this.L = str5;
        this.w = str2;
        this.A = str3;
        this.K = str4;
        if (Logger.debug()) {
            Logger.d(m, "saveStoreRegionForCronet region:" + this.w + " source:" + this.A + " sec uid:" + this.L);
        }
        SharedPreferences.Editor edit = this.E.getSharedPreferences(p, 0).edit();
        edit.putString("store_region", this.w);
        edit.putString(u, this.A);
        edit.putString(y, this.L);
        edit.apply();
    }

    private String a(String str, String str2, boolean z2) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("url", str2);
            jSONObject2.put("logId", str);
            jSONObject.put("tnc", z2);
            jSONObject.put(r.STATS_BASE_MODULE, jSONObject2);
            jSONObject.put("report_time", System.currentTimeMillis());
            jSONObject.put("store_region", this.w);
            jSONObject.put("source", this.A);
            jSONObject.put("region_legacy_user", this.K);
            jSONObject.put("local", this.B);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str2 : this.C) {
            if (p.a(str, str2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: StoreRegionManager.java */
    /* renamed from: com.bytedance.frameworks.baselib.network.http.storeregion.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public class C0117b {

        /* renamed from: a  reason: collision with root package name */
        String f8300a;

        /* renamed from: b  reason: collision with root package name */
        String f8301b;

        /* renamed from: c  reason: collision with root package name */
        String f8302c;

        C0117b(String str, String str2, String str3) {
            this.f8300a = str;
            this.f8301b = str2;
            this.f8302c = str3;
        }
    }

    private String a(String str) {
        String[] split;
        String[] split2;
        return (TextUtils.isEmpty(str) || (split = str.split(Constants.PACKNAME_END)) == null || split.length <= 0 || (split2 = split[0].trim().split("=")) == null || split2.length != 2) ? "" : split2[1];
    }

    private C0117b a(List<String> list) {
        String str;
        String str2;
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (P) {
            str = f8295f;
            str2 = q;
        } else {
            str = f8293d;
            str2 = f8294e;
        }
        String str3 = "";
        String str4 = "";
        String str5 = str4;
        for (String str6 : list) {
            if (str6.toLowerCase().startsWith(str)) {
                str3 = str6;
            } else if (str6.toLowerCase().startsWith(str2)) {
                str4 = str6;
            } else if (str6.toLowerCase().startsWith(s)) {
                str5 = str6;
            }
        }
        if (TextUtils.isEmpty(str3)) {
            return null;
        }
        String a2 = a(str3);
        if (a2.isEmpty()) {
            return null;
        }
        return new C0117b(a2, a(str4), a(str5));
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:9|(1:38)|13|(4:17|18|19|(6:(1:25)|26|27|28|29|30)(1:23))|37|(1:21)|(0)|26|27|28|29|30) */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0112, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0113, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.net.HttpURLConnection r19, byte[] r20, com.bytedance.frameworks.baselib.network.http.storeregion.b.a r21) {
        /*
            Method dump skipped, instructions count: 297
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.baselib.network.http.storeregion.b.a(java.net.HttpURLConnection, byte[], com.bytedance.frameworks.baselib.network.http.storeregion.b$a):void");
    }

    private static String a(Map<String, List<String>> map, String str) {
        if (map != null && !TextUtils.isEmpty(str)) {
            List<String> list = null;
            Iterator<String> it = map.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (str.equalsIgnoreCase(next)) {
                    list = map.get(next);
                    break;
                }
            }
            if (list != null && list.size() > 0) {
                return list.get(list.size() - 1);
            }
        }
        return "";
    }

    public JSONObject g() {
        if (this.F) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("region", this.w);
                jSONObject.put("source", this.A);
                jSONObject.put("local_region", this.B);
                jSONObject.put("update_region", this.M);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return jSONObject;
        }
        return null;
    }

    public static void a(boolean z2) {
        P = z2;
    }

    public static boolean i() {
        return P;
    }
}
