package com.ss.android.deviceregister.core;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONObject;
/* compiled from: RealRegisterServiceController.java */
/* loaded from: classes9.dex */
public class d extends e {
    public d(Context context, boolean z) {
        super(context, z);
    }

    @Override // com.ss.android.deviceregister.core.e
    protected void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            String openUdid = this.f18198b.getOpenUdid(true);
            String clientUDID = this.f18198b.getClientUDID();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("old_id", str);
            jSONObject.put("new_id", str2);
            if (a()) {
                jSONObject.put("openudid", openUdid);
            }
            jSONObject.put("clientudid", clientUDID);
            a("did_change", jSONObject);
        } catch (Exception unused) {
        }
    }
}
