package com.bytedance.apm6.consumer.slardar.send.downgrade;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DowngradeInfo.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: c  reason: collision with root package name */
    public static final String f1897c = "duration";

    /* renamed from: d  reason: collision with root package name */
    public static final String f1898d = "expire_time";

    /* renamed from: a  reason: collision with root package name */
    public long f1899a;

    /* renamed from: b  reason: collision with root package name */
    public Map<a, b> f1900b = new HashMap();

    /* compiled from: DowngradeInfo.java */
    /* loaded from: classes.dex */
    static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final String f1905a = "default";

        /* renamed from: b  reason: collision with root package name */
        public boolean f1906b;

        /* renamed from: c  reason: collision with root package name */
        public Map<String, JSONObject> f1907c = new HashMap();

        b() {
        }

        public static b a(JSONObject jSONObject) {
            JSONObject optJSONObject;
            b bVar = new b();
            bVar.f1906b = jSONObject.optInt("default", 1) == 1;
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!TextUtils.equals(next, "default") && (optJSONObject = jSONObject.optJSONObject(next)) != null) {
                    bVar.f1907c.put(next, optJSONObject);
                }
            }
            return bVar;
        }

        public JSONObject a() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("default", this.f1906b ? 1 : 0);
                for (Map.Entry<String, JSONObject> entry : this.f1907c.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
                return jSONObject;
            } catch (JSONException unused) {
                return null;
            }
        }
    }

    public static c a(JSONObject jSONObject) {
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.i(com.bytedance.apm6.consumer.slardar.b.TAG, "DowngradeRule=" + jSONObject.toString());
        }
        c cVar = new c();
        long optLong = jSONObject.optLong("duration", 0L);
        long optLong2 = jSONObject.optLong("expire_time", 0L);
        if (optLong2 > 0) {
            cVar.f1899a = optLong2;
        } else {
            cVar.f1899a = System.currentTimeMillis() + (optLong * 1000);
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(a.OTHER_LOG_TYPE.f1904c);
        if (optJSONObject != null) {
            cVar.f1900b.put(a.OTHER_LOG_TYPE, b.a(optJSONObject));
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject(a.SERVICE_MONITOR.f1904c);
        if (optJSONObject2 != null) {
            cVar.f1900b.put(a.SERVICE_MONITOR, b.a(optJSONObject2));
        }
        return cVar;
    }

    public JSONObject a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("expire_time", this.f1899a);
            for (Map.Entry<a, b> entry : this.f1900b.entrySet()) {
                jSONObject.put(entry.getKey().f1904c, entry.getValue().a());
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* compiled from: DowngradeInfo.java */
    /* loaded from: classes.dex */
    public enum a {
        OTHER_LOG_TYPE("other"),
        SERVICE_MONITOR("service_monitor");
        

        /* renamed from: c  reason: collision with root package name */
        public final String f1904c;

        a(String str) {
            this.f1904c = str;
        }
    }
}
