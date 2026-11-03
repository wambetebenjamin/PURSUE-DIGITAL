package com.bytedance.frameworks.baselib.network.http.ok3.impl.metadispatch;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: TTMetaDispatchConfig.java */
/* loaded from: classes2.dex */
public class a extends com.bytedance.frameworks.baselib.network.config.hostpathmatch.b<d> {
    @Override // com.bytedance.frameworks.baselib.network.config.hostpathmatch.b
    public String a() {
        return "ttnet_req_meta_dp";
    }

    @Override // com.bytedance.frameworks.baselib.network.config.hostpathmatch.b
    /* renamed from: b */
    public d a(Object obj) {
        JSONObject jSONObject = obj instanceof JSONObject ? (JSONObject) obj : null;
        if (jSONObject == null) {
            return null;
        }
        d dVar = new d();
        dVar.f8106b = c(jSONObject.optJSONObject("query_add"));
        dVar.f8107c = a(jSONObject.optJSONArray("query_rm"));
        dVar.f8108d = c(jSONObject.optJSONObject("header_add"));
        dVar.f8109e = a(jSONObject.optJSONArray("header_rm"));
        return dVar;
    }

    private Map<String, String> c(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject != null && jSONObject.length() != 0) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String optString = jSONObject.optString(next);
                if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(optString)) {
                    hashMap.put(next, optString);
                }
            }
        }
        return hashMap;
    }
}
