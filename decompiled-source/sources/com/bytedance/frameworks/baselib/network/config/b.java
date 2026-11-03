package com.bytedance.frameworks.baselib.network.config;

import android.text.TextUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: TTCookieConfig.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7447a = "cookie_save_v2_enabled";

    /* renamed from: b  reason: collision with root package name */
    private static final String f7448b = "cookie_backup_async_save_enabled";

    /* renamed from: c  reason: collision with root package name */
    private static final String f7449c = "cookie_backup_disabled";

    /* renamed from: d  reason: collision with root package name */
    private static final String f7450d = "cookie_batch";

    /* renamed from: e  reason: collision with root package name */
    private static final String f7451e = "disable_cookie_share_host_match_fix";

    /* renamed from: f  reason: collision with root package name */
    private static final String f7452f = "enable_flush_cookie_for_region";

    /* renamed from: g  reason: collision with root package name */
    private static final String f7453g = "disable_share_cookie_keys";

    /* renamed from: h  reason: collision with root package name */
    private static volatile boolean f7454h = false;

    /* renamed from: i  reason: collision with root package name */
    private static volatile boolean f7455i = false;

    /* renamed from: j  reason: collision with root package name */
    private static volatile boolean f7456j = false;
    private static volatile boolean k = false;
    private static volatile boolean l = false;
    private static volatile boolean m = false;
    private static final Set<String> n = new CopyOnWriteArraySet();

    public static boolean a() {
        return f7454h;
    }

    public static boolean b() {
        return f7455i;
    }

    public static boolean c() {
        return f7456j;
    }

    public static boolean d() {
        return k;
    }

    public static boolean e() {
        return l;
    }

    public static boolean f() {
        return m;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String trim = str.trim();
        int indexOf = trim.indexOf(59);
        if (indexOf != -1) {
            trim = trim.substring(0, indexOf);
        }
        Iterator<String> it = n.iterator();
        while (it.hasNext()) {
            if (trim.startsWith(it.next() + "=")) {
                return true;
            }
        }
        return false;
    }

    public static void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        g();
        f7454h = jSONObject.optInt(f7447a, 0) > 0;
        f7455i = jSONObject.optInt(f7448b, 0) > 0;
        f7456j = jSONObject.optInt(f7449c, 0) > 0;
        k = jSONObject.optInt(f7450d, 0) > 0;
        l = jSONObject.optInt(f7451e, 0) > 0;
        m = jSONObject.optInt(f7452f, 0) > 0;
        JSONArray optJSONArray = jSONObject.optJSONArray(f7453g);
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return;
        }
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            String optString = optJSONArray.optString(i2);
            if (!TextUtils.isEmpty(optString)) {
                hashSet.add(optString);
            }
        }
        n.addAll(hashSet);
    }

    private static void g() {
        f7454h = false;
        f7455i = false;
        f7456j = false;
        k = false;
        l = false;
        m = false;
        n.clear();
    }
}
