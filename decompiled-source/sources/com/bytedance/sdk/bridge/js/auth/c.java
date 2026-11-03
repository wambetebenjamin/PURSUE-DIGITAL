package com.bytedance.sdk.bridge.js.auth;

import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.sdk.bridge.annotation.BridgeContext;
import com.bytedance.sdk.bridge.annotation.BridgeMethod;
import com.bytedance.sdk.bridge.annotation.BridgeParam;
import com.bytedance.sdk.bridge.auth.privilege.c;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: JSAuthenticateModuleImpl.java */
/* loaded from: classes3.dex */
public class c extends com.bytedance.sdk.bridge.auth.privilege.a<String, i> {
    public static final String KEY_CODE = "code";

    public c() {
        super(new b());
    }

    public c(com.bytedance.sdk.bridge.auth.privilege.c<String, i> cVar) {
        super(cVar);
    }

    @Override // com.bytedance.sdk.bridge.auth.privilege.a
    @BridgeMethod(privilege = "public", value = "config")
    public void config(@BridgeContext com.bytedance.sdk.bridge.model.d dVar, @BridgeParam("__all_params__") JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        if (dVar == null || !(dVar instanceof com.bytedance.sdk.bridge.js.spec.e)) {
            return;
        }
        try {
            if (a((com.bytedance.sdk.bridge.js.spec.e) dVar, jSONObject, jSONObject2)) {
                dVar.callback(com.bytedance.sdk.bridge.model.b.Companion.createSuccessResult(jSONObject2, "success"));
            }
        } catch (Exception unused) {
            dVar.callback(com.bytedance.sdk.bridge.model.b.Companion.createErrorResult("error", jSONObject2));
        }
    }

    @BridgeMethod(privilege = "public", value = "app.config")
    public void appConfig(@BridgeContext com.bytedance.sdk.bridge.model.d dVar, @BridgeParam("__all_params__") JSONObject jSONObject) {
        config(dVar, jSONObject);
    }

    private boolean a(com.bytedance.sdk.bridge.js.spec.e eVar, JSONObject jSONObject, JSONObject jSONObject2) {
        String str;
        if (jSONObject == null) {
            str = null;
        } else if (jSONObject.has("client_id")) {
            str = jSONObject.optString("client_id");
        } else {
            str = jSONObject.optString("clientID");
        }
        if (TextUtils.isEmpty(str)) {
            jSONObject2.put("code", 0);
            return true;
        }
        String uri = eVar.getUri();
        if (TextUtils.isEmpty(uri)) {
            jSONObject2.put("code", 0);
            return true;
        }
        try {
            String host = Uri.parse(uri).getHost();
            h privilegeService = d.INSTANCE.getPrivilegeService();
            if (privilegeService != null && privilegeService.checkSafeList((h) uri)) {
                jSONObject2.put("code", 1);
                return true;
            } else if (!com.bytedance.sdk.bridge.js.util.a.isHttpUrl(uri) || this.f13111a == null) {
                jSONObject2.put("code", 0);
                return true;
            } else {
                Map<String, String> hashMap = new HashMap<>(2);
                hashMap.put("partner_domain", host);
                hashMap.put("client_id", str);
                i iVar = (i) this.f13111a.getConfig(uri, hashMap, new a(eVar));
                if ((iVar != null || eVar.getActivity() == null) && iVar != null) {
                    jSONObject2.put("call", iVar.callList);
                    jSONObject2.put("info", iVar.infoList);
                    jSONObject2.put("event", iVar.eventList);
                    jSONObject2.put("code", 1);
                    return true;
                }
                return false;
            }
        } catch (Exception unused) {
            jSONObject2.put("code", 0);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: JSAuthenticateModuleImpl.java */
    /* loaded from: classes3.dex */
    public static class a extends c.a<String, i> {

        /* renamed from: a  reason: collision with root package name */
        private com.bytedance.sdk.bridge.model.d f13179a;

        public a(com.bytedance.sdk.bridge.model.d dVar) {
            this.f13179a = dVar;
        }

        @Override // com.bytedance.sdk.bridge.auth.privilege.c.a
        public void doResult(i iVar) {
            super.doResult((a) iVar);
            com.bytedance.sdk.bridge.model.d dVar = this.f13179a;
            if (dVar != null) {
                if (iVar == null) {
                    dVar.callback(com.bytedance.sdk.bridge.model.b.Companion.createErrorResult("error", new JSONObject()));
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("call", iVar.callList);
                    jSONObject.put("info", iVar.infoList);
                    jSONObject.put("event", iVar.eventList);
                    jSONObject.put("code", 1);
                } catch (JSONException unused) {
                }
                this.f13179a.callback(com.bytedance.sdk.bridge.model.b.Companion.createSuccessResult(jSONObject, "success"));
            }
        }
    }
}
