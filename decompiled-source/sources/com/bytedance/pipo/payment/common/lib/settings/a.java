package com.bytedance.pipo.payment.common.lib.settings;

import com.bytedance.push.settings.d;
import org.json.JSONObject;
/* compiled from: JsonObjectConverter.java */
/* loaded from: classes2.dex */
public class a implements d<JSONObject> {
    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public JSONObject to(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public String from(JSONObject jSONObject) {
        try {
            return jSONObject.toString();
        } catch (Throwable unused) {
            return "";
        }
    }
}
