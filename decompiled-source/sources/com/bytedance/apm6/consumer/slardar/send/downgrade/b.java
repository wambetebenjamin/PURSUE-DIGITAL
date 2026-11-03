package com.bytedance.apm6.consumer.slardar.send.downgrade;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DowngradeDataStorage.java */
/* loaded from: classes.dex */
class b {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1893a = "APM-Downgrade";

    /* renamed from: c  reason: collision with root package name */
    private static final String f1894c = "monitor_downgrade";

    /* renamed from: d  reason: collision with root package name */
    private static final String f1895d = "rule";

    /* renamed from: b  reason: collision with root package name */
    private SharedPreferences f1896b;

    public b(Context context) {
        if (this.f1896b == null) {
            synchronized (this) {
                if (this.f1896b == null) {
                    this.f1896b = context.getSharedPreferences(f1894c, 0);
                }
            }
        }
    }

    public void a(c cVar) {
        JSONObject a2;
        if (cVar == null || this.f1896b == null || (a2 = cVar.a()) == null) {
            return;
        }
        String jSONObject = a2.toString();
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.i(f1893a, "DowngradeData-save-" + jSONObject);
        }
        this.f1896b.edit().putString(f1895d, a2.toString()).apply();
    }

    public c a() {
        String string = this.f1896b.getString(f1895d, null);
        if (string != null) {
            try {
                if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                    com.bytedance.apm6.util.log.b.i(f1893a, "DowngradeData-load-" + string);
                }
                c a2 = c.a(new JSONObject(string));
                if (System.currentTimeMillis() < a2.f1899a) {
                    return a2;
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
