package com.bytedance.bdinstall.loader;

import org.json.JSONException;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HarmonyLoader.java */
/* loaded from: classes.dex */
public final class o {
    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(JSONObject jSONObject) {
        boolean E = com.bytedance.bdinstall.util.p.E();
        try {
            jSONObject.put("os", E ? "Harmony" : com.bytedance.bdturing.utils.b.f4370d);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (E) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("os_api", com.bytedance.bdinstall.util.u.a(com.bytedance.push.utils.n.HARMONY_API_VERSION));
                jSONObject2.put("os_version", com.bytedance.bdinstall.util.u.a(com.bytedance.push.utils.n.HARMONY_VERSION));
                jSONObject2.put("release_type", com.bytedance.bdinstall.util.u.a(com.bytedance.push.utils.n.HARMONY_RELEASE_TYPE));
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                jSONObject.put("harmony", jSONObject2);
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
    }
}
