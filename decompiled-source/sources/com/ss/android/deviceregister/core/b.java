package com.ss.android.deviceregister.core;

import com.bytedance.bdinstall.util.u;
import com.bytedance.push.utils.n;
import org.json.JSONException;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HarmonyLoader.java */
/* loaded from: classes9.dex */
public final class b {
    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(JSONObject jSONObject) {
        boolean isHarmonyUI = com.ss.android.deviceregister.utils.e.isHarmonyUI();
        try {
            jSONObject.put("os", isHarmonyUI ? "Harmony" : com.bytedance.bdturing.utils.b.f4370d);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (isHarmonyUI) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("os_api", u.a(n.HARMONY_API_VERSION));
                jSONObject2.put("os_version", u.a(n.HARMONY_VERSION));
                jSONObject2.put("release_type", u.a(n.HARMONY_RELEASE_TYPE));
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
