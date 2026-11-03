package com.bytedance.push.settings;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: LocalRevokeCacheConverter.java */
/* loaded from: classes2.dex */
public class i implements c<List<com.bytedance.push.model.a>>, d<List<com.bytedance.push.model.a>> {
    @Override // com.bytedance.push.settings.c
    public List<com.bytedance.push.model.a> create() {
        return new ArrayList();
    }

    @Override // com.bytedance.push.settings.d
    public List<com.bytedance.push.model.a> to(String str) {
        com.bytedance.push.model.a parse;
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                if (optJSONObject != null && (parse = com.bytedance.push.model.a.parse(optJSONObject)) != null) {
                    arrayList.add(parse);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return arrayList;
    }

    @Override // com.bytedance.push.settings.d
    public String from(List<com.bytedance.push.model.a> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            for (com.bytedance.push.model.a aVar : list) {
                if (aVar != null) {
                    jSONArray.put(aVar.toJson());
                }
            }
        }
        return jSONArray.toString();
    }
}
