package com.bytedance.pipo.game.impl.monitor;

import android.os.SystemClock;
import org.json.JSONObject;
/* compiled from: ApiMonitor.java */
/* loaded from: classes2.dex */
public class a extends b {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10399a = "create_order";

    /* renamed from: b  reason: collision with root package name */
    public static final String f10400b = "upload_token";

    /* renamed from: c  reason: collision with root package name */
    public static final String f10401c = "query_order_state";

    /* renamed from: d  reason: collision with root package name */
    public static final String f10402d = "query_subscription_order_state";

    /* renamed from: h  reason: collision with root package name */
    private static final String f10403h = "pipo_api_request_start";

    /* renamed from: i  reason: collision with root package name */
    private static final String f10404i = "pipo_api_request_end";

    /* renamed from: j  reason: collision with root package name */
    private long f10405j = 0;
    private String k;
    private String l;
    private String m;

    public a(String str, String str2, String str3) {
        this.k = str;
        this.l = str2;
        this.m = str3;
    }

    public void a() {
        this.f10405j = SystemClock.uptimeMillis();
        JSONObject jSONObject = new JSONObject();
        a(jSONObject, "api_name", this.k);
        JSONObject jSONObject2 = new JSONObject();
        a(jSONObject2, "request_id", this.l);
        a(jSONObject2, "channel_product_id", this.m);
        g.a(f10403h, jSONObject, null, jSONObject2);
    }

    public void a(boolean z, com.bytedance.pipo.game.api.b bVar) {
        long uptimeMillis = this.f10405j > 0 ? SystemClock.uptimeMillis() - this.f10405j : 0L;
        JSONObject jSONObject = new JSONObject();
        a(jSONObject, "status", z ? 0L : 1L);
        a(jSONObject, "api_name", this.k);
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
        a(jSONObject2, "time_consuming", uptimeMillis);
        JSONObject jSONObject3 = new JSONObject();
        a(jSONObject3, "request_id", this.l);
        a(jSONObject3, "channel_product_id", this.m);
        g.a(f10404i, jSONObject, jSONObject2, jSONObject3);
    }
}
