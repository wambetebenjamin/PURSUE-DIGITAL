package com.bytedance.framwork.core.sdklib.apm6.downgrade;

import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.framwork.core.sdklib.apm6.l;
import com.bytedance.framwork.core.sdklib.apm6.safety.d;
import com.bytedance.framwork.core.sdkmonitor.i;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DowngradeDataStorage.java */
/* loaded from: classes2.dex */
class b {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8609a = "SDK-Downgrade";

    /* renamed from: c  reason: collision with root package name */
    private static final String f8610c = "monitor_downgrade";

    /* renamed from: d  reason: collision with root package name */
    private static final String f8611d = "rule";

    /* renamed from: b  reason: collision with root package name */
    private SharedPreferences f8612b;

    public b(Context context) {
        if (this.f8612b == null) {
            synchronized (this) {
                if (this.f8612b == null) {
                    this.f8612b = context.getSharedPreferences(f8610c + i.getShortProcessName(context), 0);
                }
            }
        }
    }

    public void a(c cVar) {
        JSONObject a2;
        if (cVar == null || this.f8612b == null || (a2 = cVar.a()) == null) {
            return;
        }
        String jSONObject = a2.toString();
        if (l.e()) {
            d.c(f8609a, "DowngradeData-save-" + jSONObject);
        }
        this.f8612b.edit().putString(f8611d, jSONObject).apply();
    }

    public c a() {
        String string = this.f8612b.getString(f8611d, null);
        if (string != null) {
            try {
                if (l.e()) {
                    d.c(f8609a, "DowngradeData-load-" + string);
                }
                c a2 = c.a(new JSONObject(string));
                if (System.currentTimeMillis() < a2.f8615a) {
                    return a2;
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
