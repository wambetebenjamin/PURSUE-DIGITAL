package com.bytedance.framwork.core.sdklib.apm6.downgrade;

import android.content.Context;
import com.bytedance.framwork.core.sdklib.apm6.downgrade.c;
import org.json.JSONObject;
/* compiled from: DowngradeController.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8604a = "log_type";

    /* renamed from: b  reason: collision with root package name */
    public static final String f8605b = "service";

    /* renamed from: c  reason: collision with root package name */
    private volatile c f8606c;

    /* renamed from: d  reason: collision with root package name */
    private b f8607d;

    public void a(Context context) {
        b bVar = new b(context);
        this.f8607d = bVar;
        a(bVar.a(), false);
    }

    public void a(c cVar, boolean z) {
        b bVar;
        if (cVar != null) {
            synchronized (this) {
                this.f8606c = cVar;
            }
            if (!z || (bVar = this.f8607d) == null) {
                return;
            }
            bVar.a(cVar);
        }
    }

    public boolean a(JSONObject jSONObject, int i2) {
        boolean z = true;
        if (this.f8606c == null || System.currentTimeMillis() > this.f8606c.f8615a) {
            return true;
        }
        String valueOf = String.valueOf(i2);
        String optString = jSONObject.optString("log_type");
        if (c.a.SERVICE_MONITOR.f8620c.equals(optString)) {
            String optString2 = jSONObject.optString("service");
            synchronized (this) {
                c.b bVar = this.f8606c.f8616b.get(c.a.SERVICE_MONITOR);
                if (bVar != null) {
                    JSONObject jSONObject2 = bVar.f8623c.get(valueOf);
                    if (jSONObject2 != null && jSONObject2.optInt(optString2, -1) != -1) {
                        if (jSONObject2.optInt(optString2, -1) != 1) {
                            z = false;
                        }
                        return z;
                    }
                    return bVar.f8622b;
                }
            }
        } else {
            synchronized (this) {
                c.b bVar2 = this.f8606c.f8616b.get(c.a.OTHER_LOG_TYPE);
                if (bVar2 != null) {
                    JSONObject jSONObject3 = bVar2.f8623c.get(valueOf);
                    if (jSONObject3 != null && jSONObject3.optInt(optString, -1) != -1) {
                        if (jSONObject3.optInt(optString, -1) != 1) {
                            z = false;
                        }
                        return z;
                    }
                    return bVar2.f8622b;
                }
            }
        }
        return true;
    }

    /* compiled from: DowngradeController.java */
    /* renamed from: com.bytedance.framwork.core.sdklib.apm6.downgrade.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    private static class C0123a {

        /* renamed from: a  reason: collision with root package name */
        private static final a f8608a = new a();

        private C0123a() {
        }
    }

    public static a a() {
        return C0123a.f8608a;
    }
}
