package com.bytedance.pipo.game.impl.monitor;

import android.os.SystemClock;
import com.bytedance.pipo.game.api.l;
import org.json.JSONObject;
/* compiled from: GooglePayMonitor.java */
/* loaded from: classes2.dex */
public class d extends b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10416a = "pipo_pay_google_pay_start";

    /* renamed from: b  reason: collision with root package name */
    private static final String f10417b = "pipo_pay_google_pay_end";

    /* renamed from: c  reason: collision with root package name */
    private long f10418c = 0;

    /* renamed from: d  reason: collision with root package name */
    private long f10419d = 0;

    /* renamed from: h  reason: collision with root package name */
    private long f10420h = 0;

    /* renamed from: i  reason: collision with root package name */
    private long f10421i = 0;

    /* renamed from: j  reason: collision with root package name */
    private String f10422j;
    private String k;
    private boolean l;
    private int m;

    public d(String str, String str2, boolean z, l lVar) {
        this.f10422j = str;
        this.k = str2;
        this.l = z;
        if (lVar != null) {
            this.m = lVar.ordinal();
        } else {
            this.m = -1;
        }
    }

    public void a() {
        this.f10418c = SystemClock.uptimeMillis();
        d();
    }

    private void d() {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject, "channel_product_id", this.f10422j);
        a(jSONObject, "request_id", this.k);
        JSONObject jSONObject2 = new JSONObject();
        a(jSONObject2, "pay_type", this.m);
        a(jSONObject2, "is_subscription", this.l);
        g.a(f10416a, jSONObject2, null, jSONObject);
    }

    private long e() {
        if (this.f10421i == 0) {
            this.f10421i = SystemClock.uptimeMillis();
        }
        long j2 = this.f10418c;
        if (j2 > 0) {
            return this.f10421i - j2;
        }
        return 0L;
    }

    public void a(int i2) {
        a(i2, null);
    }

    private long f() {
        long j2 = this.f10419d - this.f10418c;
        if (j2 > 0) {
            return j2;
        }
        return 0L;
    }

    private long g() {
        long j2 = this.f10420h;
        if (j2 == 0) {
            return SystemClock.uptimeMillis() - this.f10419d;
        }
        long j3 = j2 - this.f10419d;
        if (j3 > 0) {
            return j3;
        }
        return 0L;
    }

    private long h() {
        long j2 = this.f10420h;
        if (j2 == 0) {
            return 0L;
        }
        long j3 = this.f10421i - j2;
        if (j3 > 0) {
            return j3;
        }
        return 0L;
    }

    public void a(int i2, com.bytedance.pipo.game.api.b bVar) {
        long e2 = e();
        long h2 = h();
        JSONObject jSONObject = new JSONObject();
        a(jSONObject, "time_consuming", e2);
        a(jSONObject, "pre_user_time_consuming", f());
        a(jSONObject, "in_user_time_consuming", g());
        if (h2 == 0) {
            h2 = 1;
        }
        a(jSONObject, "aft_user_time_consuming", h2);
        JSONObject jSONObject2 = new JSONObject();
        a(jSONObject2, "purchase_state", i2);
        a(jSONObject2, "is_subscription", this.l);
        if (bVar != null) {
            a(jSONObject2, "result_code", bVar.e());
            a(jSONObject2, "result_detail_code", bVar.d());
            a(jSONObject2, "result_message", bVar.f());
        } else {
            a(jSONObject2, "result_code", -1L);
            a(jSONObject2, "result_detail_code", -1L);
            a(jSONObject2, "result_message", "unknown");
        }
        JSONObject jSONObject3 = new JSONObject();
        a(jSONObject3, "channel_product_id", this.f10422j);
        a(jSONObject3, "request_id", this.k);
        g.a(f10417b, jSONObject2, jSONObject, jSONObject3);
    }

    public void b() {
        this.f10419d = SystemClock.uptimeMillis();
    }

    public void c() {
        this.f10420h = SystemClock.uptimeMillis();
    }
}
