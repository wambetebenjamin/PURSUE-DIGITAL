package com.bytedance.applog.isolate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: DataIsolateUtils.java */
/* loaded from: classes.dex */
public class b {
    public static Map<String, List<com.bytedance.applog.store.a>> a(a aVar, List<com.bytedance.applog.store.a> list) {
        HashMap hashMap = new HashMap();
        for (com.bytedance.applog.store.a aVar2 : list) {
            String a2 = a(aVar, aVar2, "");
            if (!hashMap.containsKey(a2)) {
                hashMap.put(a2, new ArrayList());
            }
            ((List) hashMap.get(a2)).add(aVar2);
        }
        return hashMap;
    }

    public static String a(a aVar, JSONObject jSONObject, String str) {
        return (jSONObject == null || aVar == null || aVar != a.USER_ID) ? str : jSONObject.optString("user_id", "0");
    }

    public static String a(a aVar, com.bytedance.applog.store.a aVar2, String str) {
        return (aVar2 == null || aVar == null || aVar != a.USER_ID) ? str : String.valueOf(aVar2.y);
    }
}
