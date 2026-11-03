package com.ss.android.common.applog.filter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: AbstractEventFilter.java */
/* loaded from: classes9.dex */
public abstract class a {

    /* renamed from: c  reason: collision with root package name */
    private static final String f18034c = "event_list";

    /* renamed from: d  reason: collision with root package name */
    private static final String f18035d = "is_block";

    /* renamed from: e  reason: collision with root package name */
    private static final String f18036e = "events";

    /* renamed from: f  reason: collision with root package name */
    private static final String f18037f = "params";

    /* renamed from: g  reason: collision with root package name */
    private static final String f18038g = "sp_filter_name";

    /* renamed from: a  reason: collision with root package name */
    protected HashSet<String> f18039a;

    /* renamed from: b  reason: collision with root package name */
    protected HashMap<String, HashSet<String>> f18040b;

    protected abstract boolean a(String str);

    protected abstract boolean a(HashSet<String> hashSet, String str);

    /* JADX INFO: Access modifiers changed from: protected */
    public a(HashSet<String> hashSet, HashMap<String, HashSet<String>> hashMap) {
        this.f18039a = hashSet;
        this.f18040b = hashMap;
    }

    public final boolean filter(String str, JSONObject jSONObject) {
        HashMap<String, HashSet<String>> hashMap;
        HashSet<String> hashSet;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        HashSet<String> hashSet2 = this.f18039a;
        if (hashSet2 != null && hashSet2.size() > 0) {
            if (a(str)) {
                return false;
            }
            if (jSONObject != null && (hashMap = this.f18040b) != null && hashMap.size() > 0 && this.f18040b.containsKey(str) && (hashSet = this.f18040b.get(str)) != null && hashSet.size() > 0) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    if (a(hashSet, keys.next())) {
                        try {
                            keys.remove();
                        } catch (Throwable unused) {
                        }
                    }
                }
            }
        }
        return true;
    }

    public static final a parseFilterFromServer(Context context, JSONObject jSONObject) {
        JSONObject optJSONObject;
        a cVar;
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("sp_filter_name", 0).edit();
            edit.clear().commit();
            if (jSONObject == null || !jSONObject.has(f18034c) || (optJSONObject = jSONObject.optJSONObject(f18034c)) == null) {
                return null;
            }
            int optInt = optJSONObject.optInt(f18035d, 0);
            edit.putInt(f18035d, optInt);
            HashSet hashSet = new HashSet();
            JSONArray optJSONArray = optJSONObject.optJSONArray("events");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    String optString = optJSONArray.optString(i2);
                    if (!TextUtils.isEmpty(optString)) {
                        hashSet.add(optString);
                    }
                }
            }
            if (hashSet.size() > 0) {
                edit.putStringSet("events", hashSet);
            }
            HashMap hashMap = new HashMap();
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("params");
            if (optJSONObject2 != null) {
                Iterator<String> keys = optJSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!TextUtils.isEmpty(next)) {
                        HashSet hashSet2 = new HashSet();
                        JSONArray optJSONArray2 = optJSONObject2.optJSONArray(next);
                        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                            for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                                String optString2 = optJSONArray2.optString(i3);
                                if (!TextUtils.isEmpty(optString2)) {
                                    hashSet2.add(optString2);
                                }
                            }
                        }
                        if (hashSet2.size() > 0) {
                            hashMap.put(next, hashSet2);
                        }
                    }
                }
            }
            if (hashMap.size() > 0) {
                for (Map.Entry entry : hashMap.entrySet()) {
                    edit.putStringSet((String) entry.getKey(), (Set) entry.getValue());
                }
            }
            edit.commit();
            if (optInt > 0) {
                cVar = new b(hashSet, hashMap);
            } else {
                cVar = new c(hashSet, hashMap);
            }
            return cVar;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static final a parseFilterFromLocal(Context context) {
        Map<String, ?> map;
        a cVar;
        Set set;
        Set set2;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("sp_filter_name", 0);
            HashSet hashSet = new HashSet();
            HashMap hashMap = new HashMap();
            try {
                map = sharedPreferences.getAll();
            } catch (Throwable unused) {
                map = null;
            }
            if (map != null && map.size() > 0) {
                int i2 = 0;
                for (Map.Entry<String, ?> entry : map.entrySet()) {
                    if (entry != null) {
                        String key = entry.getKey();
                        if (f18035d.equals(key)) {
                            i2 = sharedPreferences.getInt(f18035d, 0);
                        } else if ("events".equals(key)) {
                            try {
                                set2 = (Set) entry.getValue();
                            } catch (Throwable unused2) {
                                set2 = null;
                            }
                            if (set2 != null && set2.size() > 0) {
                                hashSet.addAll(set2);
                            }
                        } else if (!TextUtils.isEmpty(key)) {
                            HashSet hashSet2 = new HashSet();
                            try {
                                set = (Set) entry.getValue();
                            } catch (Throwable unused3) {
                                set = null;
                            }
                            if (set != null && set.size() > 0) {
                                hashSet2.addAll(set);
                            }
                            if (hashSet2.size() > 0) {
                                hashMap.put(key, hashSet2);
                            }
                        }
                    }
                }
                if (i2 > 0) {
                    cVar = new b(hashSet, hashMap);
                } else {
                    cVar = new c(hashSet, hashMap);
                }
                return cVar;
            }
            return null;
        } catch (Throwable unused4) {
            return null;
        }
    }

    public static final a parseFilterFromClient(List<String> list, boolean z) {
        if (list != null && !list.isEmpty()) {
            HashSet hashSet = new HashSet();
            for (String str : list) {
                if (!TextUtils.isEmpty(str)) {
                    hashSet.add(str);
                }
            }
            if (!hashSet.isEmpty()) {
                if (z) {
                    return new b(hashSet, null);
                }
                return new c(hashSet, null);
            }
        }
        return null;
    }
}
