package gsdk.impl.webview.DEFAULT;

import org.json.JSONObject;
/* compiled from: PageTopBridgeModuleImpl.java */
/* loaded from: classes10.dex */
public abstract class h extends f {
    @Override // gsdk.impl.webview.DEFAULT.f
    public void setTitle(com.bytedance.sdk.bridge.model.d dVar, String str) {
        if (dVar == null) {
            return;
        }
        if (!k.f21046a.b(dVar)) {
            dVar.callback(com.bytedance.sdk.bridge.model.b.Companion.createNoPrivilegeResult());
        }
        if (this.f21043a != null) {
            this.f21043a.a(str);
            dVar.callback(com.bytedance.sdk.bridge.model.b.Companion.createSuccessResult(new JSONObject(), "success"));
            return;
        }
        dVar.callback(com.bytedance.sdk.bridge.model.b.Companion.createErrorResult("BrowserJsCallback is null", new JSONObject()));
    }
}
