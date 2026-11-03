package com.bytedance.frameworks.baselib.network.http.parser;

import android.text.TextUtils;
import com.bytedance.frameworks.baselib.network.http.util.p;
import java.net.URL;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: RequestTimeoutParser.java */
/* loaded from: classes2.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8265a = "to_config_v1";

    /* renamed from: b  reason: collision with root package name */
    private static final String f8266b = "enabled";

    /* renamed from: c  reason: collision with root package name */
    private static final String f8267c = "rule_list";

    /* renamed from: d  reason: collision with root package name */
    private static final String f8268d = "host_eq";

    /* renamed from: e  reason: collision with root package name */
    private static final String f8269e = "host_wc";

    /* renamed from: f  reason: collision with root package name */
    private static final String f8270f = "path_eq";

    /* renamed from: g  reason: collision with root package name */
    private static final String f8271g = "path_prefix";

    /* renamed from: h  reason: collision with root package name */
    private static final String f8272h = "path_contain";

    /* renamed from: i  reason: collision with root package name */
    private static final String f8273i = "path_pattern";

    /* renamed from: j  reason: collision with root package name */
    private static final String f8274j = "pro_to";
    private static final String k = "conn_to";
    private static final String l = "read_to";
    private static final String m = "write_to";
    private static final String n = "value";
    private static final int o = 0;
    private static final int p = 1;
    private static final int q = 2;
    private static final int r = 3;
    private static final int s = 4;
    private static final int t = 5;
    private static final int u = 6;
    private static JSONObject v;
    private static ReentrantReadWriteLock w;
    private static Lock x;
    private static Lock y;

    /* compiled from: RequestTimeoutParser.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public int f8275a;

        /* renamed from: b  reason: collision with root package name */
        public int f8276b;

        /* renamed from: c  reason: collision with root package name */
        public int f8277c;
    }

    static {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        w = reentrantReadWriteLock;
        x = reentrantReadWriteLock.readLock();
        y = w.writeLock();
    }

    public static void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            y.lock();
            v = jSONObject.optJSONObject(f8265a);
        } finally {
            y.unlock();
        }
    }

    public static int a(URL url, a aVar) {
        JSONObject jSONObject;
        JSONArray optJSONArray;
        int i2;
        JSONArray jSONArray;
        int i3;
        JSONObject optJSONObject;
        if (url == null) {
            return -1;
        }
        try {
            x.lock();
            jSONObject = v;
        } catch (Throwable unused) {
        }
        if (jSONObject != null) {
            if ((jSONObject.optInt("enabled", 0) > 0) && (optJSONArray = v.optJSONArray(f8267c)) != null && optJSONArray.length() != 0) {
                for (int i4 = 0; i4 < optJSONArray.length(); i4++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i4);
                    if (optJSONObject2 != null) {
                        JSONArray jSONArray2 = null;
                        if (optJSONObject2.has(f8268d)) {
                            jSONArray = optJSONObject2.optJSONArray(f8268d);
                            i2 = 1;
                        } else if (optJSONObject2.has(f8269e)) {
                            i2 = 2;
                            jSONArray = optJSONObject2.optJSONArray(f8269e);
                        } else {
                            i2 = 0;
                            jSONArray = null;
                        }
                        if (jSONArray != null && a(url.getHost(), jSONArray, i2)) {
                            if (optJSONObject2.has(f8270f)) {
                                i3 = 3;
                                jSONArray2 = optJSONObject2.optJSONArray(f8270f);
                            } else if (optJSONObject2.has(f8271g)) {
                                i3 = 4;
                                jSONArray2 = optJSONObject2.optJSONArray(f8271g);
                            } else if (optJSONObject2.has(f8272h)) {
                                i3 = 5;
                                jSONArray2 = optJSONObject2.optJSONArray(f8272h);
                            } else if (optJSONObject2.has(f8273i)) {
                                i3 = 6;
                                jSONArray2 = optJSONObject2.optJSONArray(f8273i);
                            } else {
                                i3 = 0;
                            }
                            if (jSONArray2 != null && a(url.getPath(), jSONArray2, i3) && (optJSONObject = optJSONObject2.optJSONObject("value")) != null) {
                                aVar.f8275a = optJSONObject.optInt(k, -1);
                                aVar.f8276b = optJSONObject.optInt(l, -1);
                                aVar.f8277c = optJSONObject.optInt(m, -1);
                                int optInt = optJSONObject.optInt(f8274j, -1);
                                x.unlock();
                                return optInt;
                            }
                        }
                    }
                }
                x.unlock();
                return -1;
            }
        }
        x.unlock();
        return -1;
    }

    private static boolean a(String str, JSONArray jSONArray, int i2) {
        if (str != null && !str.isEmpty() && jSONArray != null && jSONArray.length() != 0) {
            try {
                switch (i2) {
                    case 1:
                    case 3:
                        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                            String optString = jSONArray.optString(i3);
                            if (!TextUtils.isEmpty(optString) && str.equals(optString)) {
                                return true;
                            }
                        }
                        break;
                    case 2:
                        for (int i4 = 0; i4 < jSONArray.length(); i4++) {
                            String optString2 = jSONArray.optString(i4);
                            if (!TextUtils.isEmpty(optString2) && p.a(str, optString2)) {
                                return true;
                            }
                        }
                        break;
                    case 4:
                        for (int i5 = 0; i5 < jSONArray.length(); i5++) {
                            String optString3 = jSONArray.optString(i5);
                            if (!TextUtils.isEmpty(optString3) && str.startsWith(optString3)) {
                                return true;
                            }
                        }
                        break;
                    case 5:
                        for (int i6 = 0; i6 < jSONArray.length(); i6++) {
                            String optString4 = jSONArray.optString(i6);
                            if (!TextUtils.isEmpty(optString4) && str.contains(optString4)) {
                                return true;
                            }
                        }
                        break;
                    case 6:
                        for (int i7 = 0; i7 < jSONArray.length(); i7++) {
                            String optString5 = jSONArray.optString(i7);
                            if (!TextUtils.isEmpty(optString5) && str.matches(optString5)) {
                                return true;
                            }
                        }
                        break;
                    default:
                        return false;
                }
                return false;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }
}
