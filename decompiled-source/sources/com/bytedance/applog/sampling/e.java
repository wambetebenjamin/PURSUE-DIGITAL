package com.bytedance.applog.sampling;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.applog.log.o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: EventSamplingLoader.java */
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3368a = "sampling_list";

    /* renamed from: b  reason: collision with root package name */
    public static final String f3369b = "sampling_list";

    /* renamed from: c  reason: collision with root package name */
    public static final String f3370c = "event_sampling_version";

    /* renamed from: d  reason: collision with root package name */
    public static final String f3371d = "samplings";

    /* renamed from: e  reason: collision with root package name */
    public static final String f3372e = "sampling_rate";

    /* renamed from: f  reason: collision with root package name */
    private static final String f3373f = "EventSamplingLoader";

    /* renamed from: g  reason: collision with root package name */
    private static final String f3374g = "sampling_method";

    /* renamed from: h  reason: collision with root package name */
    private static final String f3375h = "events";

    /* renamed from: i  reason: collision with root package name */
    private static final String f3376i = "param_keys";

    /* renamed from: j  reason: collision with root package name */
    private static final String f3377j = "param_key_values";

    public static d a(Context context, String str, JSONObject jSONObject, d dVar) {
        if (jSONObject == null) {
            return dVar;
        }
        try {
            d dVar2 = new d();
            dVar2.a(jSONObject.optInt(f3370c, 0));
            dVar2.b(jSONObject.optInt(d.f3359b, 0));
            dVar2.a(jSONObject.optJSONArray(d.f3361d));
            JSONArray optJSONArray = jSONObject.optJSONArray(f3371d);
            a(dVar2, optJSONArray);
            if (TextUtils.isEmpty(str)) {
                str = "sampling_list";
            }
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putInt(d.f3359b, dVar2.b());
            edit.putInt("version", dVar2.a());
            if (optJSONArray != null) {
                edit.putString(f3371d, optJSONArray.toString());
            }
            if (dVar2.c() != null) {
                edit.putString(d.f3361d, dVar2.c().toString());
            }
            edit.apply();
            return dVar2;
        } catch (Throwable th) {
            o.a().a(12, "parseEventSamplingFromServer", th, new Object[0]);
            return dVar;
        }
    }

    public static d a(Context context, String str) {
        d dVar = new d();
        if (TextUtils.isEmpty(str)) {
            str = "sampling_list";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        dVar.a(sharedPreferences.getInt("version", 0));
        dVar.b(sharedPreferences.getInt(d.f3359b, 0));
        String string = sharedPreferences.getString(d.f3361d, null);
        if (!TextUtils.isEmpty(string)) {
            try {
                dVar.a(new JSONArray(string));
            } catch (Throwable th) {
                o.a().a(12, "parseEventSamplingFromLocal config ids error", th, new Object[0]);
            }
        }
        String string2 = sharedPreferences.getString(f3371d, null);
        if (!TextUtils.isEmpty(string2)) {
            try {
                a(dVar, new JSONArray(string2));
            } catch (Throwable th2) {
                o.a().a(12, "parseEventSamplingFromLocal sampling config", th2, new Object[0]);
            }
        }
        return dVar;
    }

    private static void a(d dVar, JSONArray jSONArray) {
        int optInt;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        JSONArray jSONArray2 = jSONArray;
        if (jSONArray2 == null || jSONArray.length() <= 0) {
            return;
        }
        dVar.b(jSONArray);
        int i2 = 0;
        while (i2 < jSONArray.length()) {
            try {
                JSONObject optJSONObject = jSONArray2.optJSONObject(i2);
                if (optJSONObject != null && (optInt = optJSONObject.optInt(f3372e)) < 10000) {
                    int optInt2 = optJSONObject.optInt(f3374g);
                    HashSet hashSet = new HashSet();
                    JSONArray optJSONArray = optJSONObject.optJSONArray("events");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                            String optString = optJSONArray.optString(i3);
                            if (!TextUtils.isEmpty(optString)) {
                                hashSet.add(optString);
                            }
                        }
                    }
                    HashMap hashMap = new HashMap();
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject(f3376i);
                    if (optJSONObject2 != null) {
                        Iterator<String> keys = optJSONObject2.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            if (!TextUtils.isEmpty(next)) {
                                JSONArray optJSONArray2 = optJSONObject2.optJSONArray(next);
                                if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                    ArrayList arrayList = new ArrayList();
                                    for (int i4 = 0; i4 < optJSONArray2.length(); i4++) {
                                        JSONArray optJSONArray3 = optJSONArray2.optJSONArray(i4);
                                        if (optJSONArray3 != null && optJSONArray3.length() > 0) {
                                            HashSet hashSet2 = new HashSet();
                                            for (int i5 = 0; i5 < optJSONArray3.length(); i5++) {
                                                String optString2 = optJSONArray3.optString(i5);
                                                if (!TextUtils.isEmpty(optString2)) {
                                                    hashSet2.add(optString2);
                                                }
                                            }
                                            arrayList.add(hashSet2);
                                        }
                                    }
                                    hashMap.put(next, arrayList);
                                }
                            }
                        }
                    }
                    HashMap hashMap2 = new HashMap();
                    JSONObject optJSONObject3 = optJSONObject.optJSONObject(f3377j);
                    if (optJSONObject3 != null) {
                        Iterator<String> keys2 = optJSONObject3.keys();
                        while (keys2.hasNext()) {
                            String next2 = keys2.next();
                            if (!TextUtils.isEmpty(next2)) {
                                JSONArray optJSONArray4 = optJSONObject3.optJSONArray(next2);
                                if (optJSONArray4 == null || optJSONArray4.length() <= 0) {
                                    jSONObject = optJSONObject3;
                                } else {
                                    ArrayList arrayList2 = new ArrayList();
                                    int i6 = 0;
                                    while (i6 < optJSONArray4.length()) {
                                        JSONObject optJSONObject4 = optJSONArray4.optJSONObject(i6);
                                        if (optJSONObject4 != null) {
                                            HashMap hashMap3 = new HashMap();
                                            Iterator<String> keys3 = optJSONObject4.keys();
                                            while (keys3.hasNext()) {
                                                String next3 = keys3.next();
                                                if (TextUtils.isEmpty(next3)) {
                                                    jSONObject3 = optJSONObject3;
                                                } else {
                                                    jSONObject3 = optJSONObject3;
                                                    Object opt = optJSONObject4.opt(next3);
                                                    if (opt != null) {
                                                        hashMap3.put(next3, opt);
                                                    }
                                                }
                                                optJSONObject3 = jSONObject3;
                                            }
                                            jSONObject2 = optJSONObject3;
                                            arrayList2.add(hashMap3);
                                        } else {
                                            jSONObject2 = optJSONObject3;
                                        }
                                        i6++;
                                        optJSONObject3 = jSONObject2;
                                    }
                                    jSONObject = optJSONObject3;
                                    hashMap2.put(next2, arrayList2);
                                }
                                optJSONObject3 = jSONObject;
                            }
                        }
                    }
                    dVar.a(optInt, optInt2, hashSet, hashMap, hashMap2);
                }
                i2++;
                jSONArray2 = jSONArray;
            } catch (Throwable th) {
                o.a().a(12, "loadSamplingMapFromJsonArray error", th, new Object[0]);
                return;
            }
        }
    }
}
