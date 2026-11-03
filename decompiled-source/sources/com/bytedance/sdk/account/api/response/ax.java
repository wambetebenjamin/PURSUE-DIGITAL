package com.bytedance.sdk.account.api.response;

import com.bytedance.sdk.account.C0392r;
import org.json.JSONObject;
/* compiled from: UpdateAuthorizeInfoResponse.java */
/* loaded from: classes2.dex */
public class ax extends com.bytedance.sdk.account.api.call.j {
    public String m;

    public ax(boolean z, String str, int i2) {
        super(z, (int) C0392r.o, str, i2);
    }

    public ax(boolean z, String str, String str2) {
        super(z, (int) C0392r.o, str, str2);
    }

    @Override // com.bytedance.sdk.account.api.call.j
    public void a(JSONObject jSONObject, JSONObject jSONObject2) {
        super.a(jSONObject, jSONObject2);
        this.m = jSONObject2.optString("extra");
    }
}
