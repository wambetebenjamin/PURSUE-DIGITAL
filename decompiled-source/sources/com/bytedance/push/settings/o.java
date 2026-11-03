package com.bytedance.push.settings;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
/* compiled from: TokenCacheConverter.java */
/* loaded from: classes2.dex */
public class o implements c<List<com.bytedance.push.model.d>>, d<List<com.bytedance.push.model.d>> {
    @Override // com.bytedance.push.settings.d
    public List<com.bytedance.push.model.d> to(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                com.bytedance.push.model.d parse = com.bytedance.push.model.d.parse(jSONArray.optJSONObject(i2));
                if (parse != null) {
                    arrayList.add(parse);
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    @Override // com.bytedance.push.settings.d
    public String from(List<com.bytedance.push.model.d> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            for (com.bytedance.push.model.d dVar : list) {
                if (dVar != null) {
                    jSONArray.put(dVar.toJson());
                }
            }
        }
        return jSONArray.toString();
    }

    @Override // com.bytedance.push.settings.c
    public List<com.bytedance.push.model.d> create() {
        return new ArrayList();
    }
}
