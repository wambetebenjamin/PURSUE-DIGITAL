package com.bytedance.pipo.game.impl.monitor;

import android.os.SystemClock;
import com.bytedance.pipo.game.api.l;
import org.json.JSONObject;
/* compiled from: ConsumeProductMonitor.java */
/* loaded from: classes2.dex */
public class c extends b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10409a = "pipo_consume_product_start";

    /* renamed from: b  reason: collision with root package name */
    private static final String f10410b = "pipo_consume_product_end";

    /* renamed from: c  reason: collision with root package name */
    private long f10411c = 0;

    /* renamed from: d  reason: collision with root package name */
    private String f10412d;

    /* renamed from: h  reason: collision with root package name */
    private String f10413h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f10414i;

    /* renamed from: j  reason: collision with root package name */
    private int f10415j;

    public c(String str, String str2, l lVar, boolean z) {
        this.f10412d = str;
        this.f10413h = str2;
        this.f10414i = z;
        if (lVar == null) {
            this.f10415j = -1;
        } else {
            this.f10415j = lVar.ordinal();
        }
    }

    public void a() {
        this.f10411c = SystemClock.uptimeMillis();
        b();
    }

    private void b() {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject, "channel_product_id", this.f10412d);
        a(jSONObject, "request_id", this.f10413h);
        JSONObject jSONObject2 = new JSONObject();
        a(jSONObject2, "pay_type", this.f10415j);
        a(jSONObject2, "is_subscription", this.f10414i);
        g.a(f10409a, jSONObject2, null, jSONObject);
    }

    private long c() {
        long uptimeMillis = this.f10411c > 0 ? SystemClock.uptimeMillis() - this.f10411c : 0L;
        this.f10411c = 0L;
        return uptimeMillis;
    }

    public void a(boolean z, com.bytedance.pipo.game.api.b bVar) {
        long c2 = c();
        JSONObject jSONObject = new JSONObject();
        a(jSONObject, "status", z ? 0L : 1L);
        a(jSONObject, "pay_type", this.f10415j);
        a(jSONObject, "is_subscription", this.f10414i);
        if (bVar != null) {
            a(jSONObject, "result_code", bVar.e());
            a(jSONObject, "result_detail_code", bVar.d());
            a(jSONObject, "result_message", bVar.f());
        } else {
            a(jSONObject, "result_code", -1L);
            a(jSONObject, "result_detail_code", -1L);
            a(jSONObject, "result_message", "unknown");
        }
        JSONObject jSONObject2 = new JSONObject();
        a(jSONObject2, "time_consuming", c2);
        JSONObject jSONObject3 = new JSONObject();
        a(jSONObject3, "channel_product_id", this.f10412d);
        a(jSONObject3, "request_id", this.f10413h);
        g.a(f10410b, jSONObject, jSONObject2, jSONObject3);
    }
}
