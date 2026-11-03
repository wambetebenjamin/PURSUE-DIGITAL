package com.bytedance.pipo.game.impl.monitor;

import android.os.SystemClock;
import com.bytedance.pipo.game.api.l;
import org.json.JSONObject;
/* compiled from: UploadTokenMonitor.java */
/* loaded from: classes2.dex */
public class h extends b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10435a = "pipo_pay_upload_token_start";

    /* renamed from: b  reason: collision with root package name */
    private static final String f10436b = "pipo_pay_upload_token_end";

    /* renamed from: c  reason: collision with root package name */
    private long f10437c = 0;

    /* renamed from: d  reason: collision with root package name */
    private String f10438d;

    /* renamed from: h  reason: collision with root package name */
    private String f10439h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f10440i;

    /* renamed from: j  reason: collision with root package name */
    private int f10441j;

    public h(com.bytedance.pipo.game.impl.model.h hVar, l lVar) {
        if (hVar != null) {
            this.f10438d = hVar.h();
            this.f10439h = hVar.g();
            this.f10440i = hVar.e();
            if (lVar != null) {
                this.f10441j = lVar.ordinal();
            } else {
                this.f10441j = -1;
            }
        }
    }

    public void a() {
        this.f10437c = SystemClock.uptimeMillis();
        b();
    }

    private void b() {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject, "channel_product_id", this.f10438d);
        a(jSONObject, "request_id", this.f10439h);
        JSONObject jSONObject2 = new JSONObject();
        a(jSONObject2, "pay_type", this.f10441j);
        a(jSONObject2, "is_subscription", this.f10440i);
        g.a(f10435a, jSONObject2, null, jSONObject);
    }

    private long c() {
        long uptimeMillis = this.f10437c > 0 ? SystemClock.uptimeMillis() - this.f10437c : 0L;
        this.f10437c = 0L;
        return uptimeMillis;
    }

    public void a(boolean z, com.bytedance.pipo.game.api.b bVar) {
        long c2 = c();
        JSONObject jSONObject = new JSONObject();
        a(jSONObject, "pay_type", this.f10441j);
        a(jSONObject, "status", z ? 0L : 1L);
        a(jSONObject, "is_subscription", this.f10440i);
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
        a(jSONObject3, "channel_product_id", this.f10438d);
        a(jSONObject3, "request_id", this.f10439h);
        g.a(f10436b, jSONObject, jSONObject2, jSONObject3);
    }
}
