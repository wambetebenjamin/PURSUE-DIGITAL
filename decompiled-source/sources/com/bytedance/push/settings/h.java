package com.bytedance.push.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
/* compiled from: ListStrConverter.java */
/* loaded from: classes2.dex */
public class h implements c<List<String>>, d<List<String>> {
    @Override // com.bytedance.push.settings.c
    public List<String> create() {
        return new ArrayList();
    }

    @Override // com.bytedance.push.settings.d
    public List<String> to(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                arrayList.add(jSONArray.optString(i2));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    @Override // com.bytedance.push.settings.d
    public String from(List<String> list) {
        JSONArray jSONArray;
        if (list != null) {
            jSONArray = new JSONArray((Collection) list);
        } else {
            jSONArray = new JSONArray();
        }
        return jSONArray.toString();
    }
}
