package com.bytedance.framwork.core.sdklib.apm6.downgrade;

import android.text.TextUtils;
import com.bytedance.framwork.core.sdklib.apm6.l;
import com.bytedance.framwork.core.sdklib.apm6.safety.d;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DowngradeInfo.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: c  reason: collision with root package name */
    public static final String f8613c = "duration";

    /* renamed from: d  reason: collision with root package name */
    public static final String f8614d = "expire_time";

    /* renamed from: a  reason: collision with root package name */
    public long f8615a;

    /* renamed from: b  reason: collision with root package name */
    public Map<a, b> f8616b = new HashMap();

    /* compiled from: DowngradeInfo.java */
    /* loaded from: classes2.dex */
    static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final String f8621a = "default";

        /* renamed from: b  reason: collision with root package name */
        public boolean f8622b;

        /* renamed from: c  reason: collision with root package name */
        public Map<String, JSONObject> f8623c = new HashMap();

        b() {
        }

        public static b a(JSONObject jSONObject) {
            JSONObject optJSONObject;
            b bVar = new b();
            bVar.f8622b = jSONObject.optInt("default", 1) == 1;
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!TextUtils.equals(next, "default") && (optJSONObject = jSONObject.optJSONObject(next)) != null) {
                    bVar.f8623c.put(next, optJSONObject);
                }
            }
            return bVar;
        }

        public JSONObject a() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("default", this.f8622b ? 1 : 0);
                for (Map.Entry<String, JSONObject> entry : this.f8623c.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
                return jSONObject;
            } catch (JSONException unused) {
                return null;
            }
        }
    }

    public static c a(JSONObject jSONObject) {
        if (l.e()) {
            d.c(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "DowngradeRule=" + jSONObject.toString());
        }
        c cVar = new c();
        long optLong = jSONObject.optLong("duration", 0L);
        long optLong2 = jSONObject.optLong("expire_time", 0L);
        if (optLong2 > 0) {
            cVar.f8615a = optLong2;
        } else {
            cVar.f8615a = System.currentTimeMillis() + (optLong * 1000);
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(a.OTHER_LOG_TYPE.f8620c);
        if (optJSONObject != null) {
            cVar.f8616b.put(a.OTHER_LOG_TYPE, b.a(optJSONObject));
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject(a.SERVICE_MONITOR.f8620c);
        if (optJSONObject2 != null) {
            cVar.f8616b.put(a.SERVICE_MONITOR, b.a(optJSONObject2));
        }
        return cVar;
    }

    public JSONObject a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("expire_time", this.f8615a);
            for (Map.Entry<a, b> entry : this.f8616b.entrySet()) {
                jSONObject.put(entry.getKey().f8620c, entry.getValue().a());
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* compiled from: DowngradeInfo.java */
    /* loaded from: classes2.dex */
    public enum a {
        OTHER_LOG_TYPE("other"),
        SERVICE_MONITOR("service_monitor");
        

        /* renamed from: c  reason: collision with root package name */
        public final String f8620c;

        a(String str) {
            this.f8620c = str;
        }
    }
}
