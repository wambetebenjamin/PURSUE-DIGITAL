package com.bytedance.apm.data.type;

import com.bytedance.apm.constant.p;
import net.openid.appauth.AuthorizationRequest;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: UIActionData.java */
/* loaded from: classes.dex */
public class g implements com.bytedance.apm.data.b {
    public String action;
    public JSONObject context;
    public JSONObject extraJson;
    public String page;

    @Override // com.bytedance.apm.data.b
    public String getSubTypeLabel() {
        return "ui_action";
    }

    @Override // com.bytedance.apm.data.b
    public String getTypeLabel() {
        return "ui_action";
    }

    @Override // com.bytedance.apm.data.b
    public boolean isSaveImmediately() {
        return false;
    }

    @Override // com.bytedance.apm.data.b
    public boolean isUploadImmediately() {
        return false;
    }

    @Override // com.bytedance.apm.data.b
    public boolean supportFetch() {
        return true;
    }

    public g(String str, String str2, JSONObject jSONObject, JSONObject jSONObject2) {
        this.action = str;
        this.page = str2;
        this.context = jSONObject;
        this.extraJson = jSONObject2;
    }

    @Override // com.bytedance.apm.data.b
    public JSONObject packLog() {
        try {
            if (this.extraJson == null) {
                this.extraJson = new JSONObject();
            }
            this.extraJson.put("log_type", "ui_action");
            this.extraJson.put("action", this.action);
            this.extraJson.put(AuthorizationRequest.Display.PAGE, this.page);
            this.extraJson.put("context", this.context);
            return this.extraJson;
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // com.bytedance.apm.data.b
    public boolean isSampled(JSONObject jSONObject) {
        return com.bytedance.apm.samplers.c.getPerfAllowSwitch(p.PERF_KEY_UI);
    }
}
