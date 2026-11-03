package com.bytedance.pipo.game.impl.monitor;

import com.bytedance.pipo.game.n;
import org.json.JSONObject;
/* compiled from: ServiceMonitorManger.java */
/* loaded from: classes2.dex */
public final class g {
    private g() {
    }

    public static void a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        if (jSONObject3 == null) {
            jSONObject3 = new JSONObject();
        }
        com.bytedance.pipo.game.impl.util.e.a(jSONObject3, "timestamp", System.currentTimeMillis());
        n.a(str, jSONObject, jSONObject2, jSONObject3);
    }
}
