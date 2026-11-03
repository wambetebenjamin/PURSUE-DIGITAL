package gsdk.impl.webview.DEFAULT;

import com.bytedance.sdk.bridge.annotation.BridgeContext;
import com.bytedance.sdk.bridge.annotation.BridgeMethod;
import gsdk.impl.webview.DEFAULT.a;
import org.json.JSONObject;
/* compiled from: PageEventBridgeModule.java */
/* loaded from: classes10.dex */
public class s {
    @BridgeMethod(a.e.o)
    public void onPageVisible(@BridgeContext com.bytedance.sdk.bridge.model.d dVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("method_name", bu.f21027a.b(a.e.o));
            bu.f21027a.a(bu.f21031e, jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @BridgeMethod(a.e.p)
    public void onPageInvisible(@BridgeContext com.bytedance.sdk.bridge.model.d dVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("method_name", bu.f21027a.b(a.e.p));
            bu.f21027a.a(bu.f21031e, jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @BridgeMethod(a.e.q)
    public void onPageStateChange(@BridgeContext com.bytedance.sdk.bridge.model.d dVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("method_name", bu.f21027a.b(a.e.q));
            bu.f21027a.a(bu.f21031e, jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
