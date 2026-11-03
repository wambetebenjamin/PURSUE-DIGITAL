package gsdk.impl.webview.DEFAULT;

import com.bytedance.sdk.bridge.annotation.BridgeContext;
import com.bytedance.sdk.bridge.annotation.BridgeMethod;
import com.bytedance.sdk.bridge.annotation.BridgeParam;
import com.bytedance.sdk.bridge.annotation.BridgeSyncType;
import gsdk.impl.webview.DEFAULT.a;
import org.json.JSONObject;
/* compiled from: AbsImageBridgeModule.java */
/* loaded from: classes10.dex */
public abstract class n {
    @BridgeMethod(privilege = "public", sync = BridgeSyncType.SYNC, value = a.InterfaceC0381a.f20829c)
    public abstract com.bytedance.sdk.bridge.model.b gallery(@BridgeContext com.bytedance.sdk.bridge.model.d dVar, @BridgeParam("__all_params__") JSONObject jSONObject);
}
