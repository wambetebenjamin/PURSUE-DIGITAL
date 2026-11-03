package com.bytedance.pipo.game.impl.monitor;

import android.os.SystemClock;
import org.json.JSONObject;
/* compiled from: ServiceInitMonitor.java */
/* loaded from: classes2.dex */
public class f extends b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10432a = "pipo_init_start";

    /* renamed from: b  reason: collision with root package name */
    private static final String f10433b = "pipo_init_end";

    /* renamed from: c  reason: collision with root package name */
    private long f10434c = 0;

    public void a() {
        this.f10434c = SystemClock.uptimeMillis();
        g.a(f10432a, null, null, null);
        com.bytedance.pipo.game.impl.event.a.a().a("intl");
    }

    public void a(boolean z, int i2) {
        a(z, i2, new com.bytedance.pipo.game.impl.model.e(0));
    }

    public void a(boolean z, int i2, com.bytedance.pipo.game.impl.model.e eVar) {
        long uptimeMillis = this.f10434c > 0 ? SystemClock.uptimeMillis() - this.f10434c : 0L;
        JSONObject a2 = com.bytedance.pipo.game.impl.event.b.a(eVar);
        a(a2, "status", z ? 0L : 1L);
        a(a2, "detail_code", i2);
        JSONObject jSONObject = new JSONObject();
        a(jSONObject, "time_consuming", uptimeMillis);
        g.a(f10433b, a2, jSONObject, null);
        com.bytedance.pipo.game.impl.event.a.a().a("intl", eVar);
    }
}
