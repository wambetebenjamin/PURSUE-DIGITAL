package com.bytedance.apm.util;

import java.util.Map;
import org.json.JSONObject;
/* compiled from: MapUtils.java */
/* loaded from: classes.dex */
public class o {
    public static JSONObject mapToJson(Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject(map);
        jSONObject.put("", "");
        return jSONObject;
    }
}
