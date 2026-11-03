package com.bytedance.push.settings.assosiation.start;

import android.text.TextUtils;
import com.bytedance.push.settings.c;
import com.bytedance.push.settings.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: AssociationStartSettingsConverter.java */
/* loaded from: classes2.dex */
public class a implements c<b>, d<b> {

    /* renamed from: a  reason: collision with root package name */
    private final String f11721a = "enable_association_hook";

    /* renamed from: b  reason: collision with root package name */
    private final String f11722b = "enable_association_start_monitor";

    /* renamed from: c  reason: collision with root package name */
    private final String f11723c = "enable_association_start_intercept";

    /* renamed from: d  reason: collision with root package name */
    private final String f11724d = "intercept_component_white_list";

    /* renamed from: e  reason: collision with root package name */
    private final String f11725e = "intercept_component_black_list";

    /* renamed from: f  reason: collision with root package name */
    private final String f11726f = "isolation_intercept_component_black_list";

    /* renamed from: g  reason: collision with root package name */
    private final String f11727g = "monitor_component_black_list";

    /* renamed from: h  reason: collision with root package name */
    private final String f11728h = "isolation_redirect_component_map";

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public b to(String str) {
        b create = create();
        try {
            JSONObject jSONObject = new JSONObject(str);
            create.f11729a = jSONObject.optBoolean("enable_association_hook");
            create.f11730b = jSONObject.optBoolean("enable_association_start_monitor");
            create.f11732d = jSONObject.optBoolean("enable_association_start_intercept");
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("monitor_component_black_list");
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    arrayList.add((String) optJSONArray.get(i2));
                }
            }
            create.f11731c = arrayList;
            ArrayList arrayList2 = new ArrayList();
            JSONArray optJSONArray2 = jSONObject.optJSONArray("intercept_component_white_list");
            if (optJSONArray2 != null) {
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    arrayList2.add((String) optJSONArray2.get(i3));
                }
            }
            create.f11735g = arrayList2;
            ArrayList arrayList3 = new ArrayList();
            JSONArray optJSONArray3 = jSONObject.optJSONArray("intercept_component_black_list");
            if (optJSONArray3 != null) {
                for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                    arrayList3.add((String) optJSONArray3.get(i4));
                }
            }
            create.f11734f = arrayList3;
            ArrayList arrayList4 = new ArrayList();
            JSONArray optJSONArray4 = jSONObject.optJSONArray("isolation_intercept_component_black_list");
            if (optJSONArray4 != null) {
                for (int i5 = 0; i5 < optJSONArray4.length(); i5++) {
                    arrayList4.add((String) optJSONArray4.get(i5));
                }
            }
            create.f11733e = arrayList4;
            HashMap hashMap = new HashMap();
            JSONObject optJSONObject = jSONObject.optJSONObject("isolation_redirect_component_map");
            if (optJSONObject != null) {
                Iterator<String> keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    String optString = optJSONObject.optString(next);
                    if (!TextUtils.isEmpty(optString)) {
                        hashMap.put(next, optString);
                    }
                }
            }
            create.f11736h = hashMap;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return create;
    }

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public String from(b bVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("enable_association_hook", bVar.f11729a);
            jSONObject.put("enable_association_start_monitor", bVar.f11730b);
            jSONObject.put("enable_association_start_intercept", bVar.f11732d);
            JSONArray jSONArray = new JSONArray();
            if (bVar.f11731c != null) {
                for (String str : bVar.f11731c) {
                    jSONArray.put(str);
                }
            }
            jSONObject.put("monitor_component_black_list", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            if (bVar.f11735g != null) {
                for (String str2 : bVar.f11735g) {
                    jSONArray2.put(str2);
                }
            }
            jSONObject.put("intercept_component_white_list", jSONArray2);
            JSONArray jSONArray3 = new JSONArray();
            if (bVar.f11734f != null) {
                for (String str3 : bVar.f11734f) {
                    jSONArray3.put(str3);
                }
            }
            jSONObject.put("intercept_component_black_list", jSONArray3);
            JSONArray jSONArray4 = new JSONArray();
            if (bVar.f11733e != null) {
                for (String str4 : bVar.f11733e) {
                    jSONArray4.put(str4);
                }
            }
            jSONObject.put("isolation_intercept_component_black_list", jSONArray4);
            JSONObject jSONObject2 = new JSONObject();
            for (Map.Entry<String, String> entry : bVar.f11736h.entrySet()) {
                jSONObject2.put(entry.getKey(), entry.getValue());
            }
            jSONObject.put("isolation_redirect_component_map", jSONObject2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject.toString();
    }

    @Override // com.bytedance.push.settings.c
    /* renamed from: a */
    public b create() {
        return new b();
    }
}
