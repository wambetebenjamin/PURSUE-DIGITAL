package com.bytedance.frameworks.baselib.network.config;

import android.text.TextUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import okhttp3.Headers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: InsecureRequestControlConfig.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7440a = "insecure_request_control";

    /* renamed from: b  reason: collision with root package name */
    private static final String f7441b = "disable_share_insecure_cookie";

    /* renamed from: c  reason: collision with root package name */
    private static final String f7442c = "secret_cookie_list";

    /* renamed from: d  reason: collision with root package name */
    private static final String f7443d = "secret_header_list";

    /* renamed from: e  reason: collision with root package name */
    private static volatile boolean f7444e = false;

    /* renamed from: f  reason: collision with root package name */
    private static final CopyOnWriteArraySet<String> f7445f = new CopyOnWriteArraySet<>();

    /* renamed from: g  reason: collision with root package name */
    private static final CopyOnWriteArraySet<String> f7446g = new CopyOnWriteArraySet<>();

    public static boolean a() {
        return f7444e;
    }

    public static Set<String> a(List<String> list) {
        CopyOnWriteArraySet<String> copyOnWriteArraySet = f7445f;
        if (copyOnWriteArraySet.isEmpty() || list == null || list.isEmpty()) {
            return null;
        }
        HashSet hashSet = new HashSet();
        Iterator<String> it = copyOnWriteArraySet.iterator();
        while (it.hasNext()) {
            String next = it.next();
            for (String str : list) {
                if (str != null && str.contains(next + "=")) {
                    hashSet.add(next);
                }
            }
        }
        if (hashSet.size() > 0) {
            return hashSet;
        }
        return null;
    }

    public static Set<String> a(Headers headers) {
        CopyOnWriteArraySet<String> copyOnWriteArraySet = f7446g;
        if (copyOnWriteArraySet.isEmpty() || headers == null || headers.size() == 0) {
            return null;
        }
        HashSet hashSet = new HashSet();
        Iterator<String> it = copyOnWriteArraySet.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (headers.get(next) != null) {
                hashSet.add(next);
            }
        }
        if (hashSet.size() > 0) {
            return hashSet;
        }
        return null;
    }

    public static void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        b();
        JSONObject optJSONObject = jSONObject.optJSONObject(f7440a);
        if (optJSONObject == null) {
            return;
        }
        f7444e = optJSONObject.optInt(f7441b, 0) > 0;
        a(optJSONObject.optJSONArray(f7442c), f7445f);
        a(optJSONObject.optJSONArray(f7443d), f7446g);
    }

    private static void b() {
        f7444e = false;
        f7445f.clear();
        f7446g.clear();
    }

    private static void a(JSONArray jSONArray, CopyOnWriteArraySet<String> copyOnWriteArraySet) {
        if (jSONArray == null || copyOnWriteArraySet == null) {
            return;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                String string = jSONArray.getString(i2);
                if (!TextUtils.isEmpty(string)) {
                    copyOnWriteArraySet.add(string);
                }
            } catch (JSONException unused) {
                return;
            }
        }
    }
}
