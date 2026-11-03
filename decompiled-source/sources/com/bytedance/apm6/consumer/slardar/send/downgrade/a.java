package com.bytedance.apm6.consumer.slardar.send.downgrade;

import android.content.Context;
import com.bytedance.apm6.consumer.slardar.send.downgrade.c;
import org.json.JSONObject;
/* compiled from: DowngradeController.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1888a = "log_type";

    /* renamed from: b  reason: collision with root package name */
    public static final String f1889b = "service";

    /* renamed from: c  reason: collision with root package name */
    public volatile c f1890c;

    /* renamed from: d  reason: collision with root package name */
    private b f1891d;

    public void a(Context context) {
        b bVar = new b(context);
        this.f1891d = bVar;
        a(bVar.a(), false);
    }

    public void a(c cVar, boolean z) {
        b bVar;
        if (cVar != null) {
            synchronized (this) {
                this.f1890c = cVar;
            }
            if (!z || (bVar = this.f1891d) == null) {
                return;
            }
            bVar.a(cVar);
        }
    }

    public boolean a(JSONObject jSONObject, int i2) {
        boolean z = true;
        if (this.f1890c == null || System.currentTimeMillis() > this.f1890c.f1899a) {
            return true;
        }
        String valueOf = String.valueOf(i2);
        String optString = jSONObject.optString("log_type");
        synchronized (this) {
            if (c.a.SERVICE_MONITOR.f1904c.equals(optString)) {
                String optString2 = jSONObject.optString("service");
                c.b bVar = this.f1890c.f1900b.get(c.a.SERVICE_MONITOR);
                if (bVar != null) {
                    JSONObject jSONObject2 = bVar.f1907c.get(valueOf);
                    if (jSONObject2 != null && jSONObject2.optInt(optString2, -1) != -1) {
                        if (jSONObject2.optInt(optString2, -1) != 1) {
                            z = false;
                        }
                        return z;
                    }
                    return bVar.f1906b;
                }
            } else {
                c.b bVar2 = this.f1890c.f1900b.get(c.a.OTHER_LOG_TYPE);
                if (bVar2 != null) {
                    JSONObject jSONObject3 = bVar2.f1907c.get(valueOf);
                    if (jSONObject3 != null && jSONObject3.optInt(optString, -1) != -1) {
                        if (jSONObject3.optInt(optString, -1) != 1) {
                            z = false;
                        }
                        return z;
                    }
                    return bVar2.f1906b;
                }
            }
            return true;
        }
    }

    /* compiled from: DowngradeController.java */
    /* renamed from: com.bytedance.apm6.consumer.slardar.send.downgrade.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0029a {

        /* renamed from: a  reason: collision with root package name */
        private static final a f1892a = new a();

        private C0029a() {
        }
    }

    public static a a() {
        return C0029a.f1892a;
    }
}
