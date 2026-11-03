package com.bytedance.sdk.account.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: CommonRequestCacheHelper.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13082a = "com_bytedance_sdk_account_utils_common_request_cache_helper";

    /* renamed from: b  reason: collision with root package name */
    private static final String f13083b = "expire_timing";

    /* renamed from: c  reason: collision with root package name */
    private static final String f13084c = "cache_value";

    /* renamed from: d  reason: collision with root package name */
    private static c f13085d;

    /* renamed from: e  reason: collision with root package name */
    private SharedPreferences f13086e;

    private c() {
    }

    public static c getInstance() {
        if (f13085d == null) {
            synchronized (c.class) {
                if (f13085d == null) {
                    f13085d = new c();
                }
            }
        }
        return f13085d;
    }

    private SharedPreferences a() {
        Context b2;
        if (this.f13086e == null && (b2 = com.ss.android.token.d.b()) != null) {
            this.f13086e = b2.getSharedPreferences(f13082a, 0);
        }
        return this.f13086e;
    }

    private String a(String str, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (map != null && !map.isEmpty()) {
            ArrayList arrayList = new ArrayList(map.keySet());
            Collections.sort(arrayList);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str2 = map.get((String) it.next());
                if (str2 != null) {
                    sb.append(str2);
                }
            }
        }
        return sb.toString();
    }

    public void putCache(String str, Map<String, String> map, String str2, long j2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            String a2 = a(str, map);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(f13083b, j2);
            jSONObject.put(f13084c, str2);
            SharedPreferences a3 = a();
            if (a3 == null || System.currentTimeMillis() >= j2) {
                return;
            }
            a3.edit().putString(a2, jSONObject.toString()).apply();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String getCache(String str, Map<String, String> map) {
        SharedPreferences a2;
        JSONObject jSONObject;
        if (TextUtils.isEmpty(str) || (a2 = a()) == null) {
            return null;
        }
        String a3 = a(str, map);
        String string = a2.getString(a3, "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            jSONObject = new JSONObject(string);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (System.currentTimeMillis() < jSONObject.optLong(f13083b)) {
            return jSONObject.optString(f13084c, "");
        }
        a2.edit().putString(a3, "").apply();
        return null;
    }

    public void clearCache() {
        SharedPreferences a2 = a();
        if (a2 != null) {
            a2.edit().clear().apply();
        }
    }

    public static String getPathFromUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return new URL(str).getPath();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
