package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class AFe1rSDK implements AFe1kSDK<Map<String, Object>> {
    @Override // com.appsflyer.internal.AFe1kSDK
    public final /* synthetic */ Map<String, Object> valueOf(String str) throws JSONException {
        return values(str);
    }

    public static Map<String, Object> values(String str) throws JSONException {
        try {
            HashMap hashMap = new HashMap();
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.isNull(next) ? null : jSONObject.get(next));
            }
            hashMap.remove("is_cache");
            return hashMap;
        } catch (JSONException e2) {
            AFLogger.afErrorLogForExcManagerOnly("error while paring GCD response", e2);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("string_response", str);
            return hashMap2;
        }
    }
}
