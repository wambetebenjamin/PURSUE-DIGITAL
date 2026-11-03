package com.bytedance.sdk.bridge.method;

import com.bytedance.sdk.bridge.annotation.BridgeContext;
import com.bytedance.sdk.bridge.annotation.BridgeMethod;
import com.bytedance.sdk.bridge.annotation.BridgeParam;
import com.bytedance.sdk.bridge.annotation.BridgeSyncType;
import com.bytedance.sdk.bridge.model.d;
import gsdk.impl.main.DEFAULT.g;
import gsdk.impl.webview.DEFAULT.a;
import kotlin.Metadata;
import org.json.JSONObject;
/* compiled from: AbsDeviceBridgeModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\bH'J\b\u0010\n\u001a\u00020\u000bH'J&\u0010\f\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\r\u001a\u00020\b2\b\b\u0001\u0010\u000e\u001a\u00020\u000fH'¨\u0006\u0010"}, d2 = {"Lcom/bytedance/sdk/bridge/method/AbsDeviceBridgeModule;", "", "()V", "checkAppInstalled", "", "bridgeContext", "Lcom/bytedance/sdk/bridge/model/IBridgeContext;", "pkgName", "", "openUrl", g.a.f20440e, "Lcom/bytedance/sdk/bridge/model/BridgeResult;", "setClipboardDataBridge", "content", "totalParams", "Lorg/json/JSONObject;", "bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public abstract class b {
    @BridgeMethod("checkAppInstalled")
    public abstract void checkAppInstalled(@BridgeContext d dVar, @BridgeParam("pkg_name") String str, @BridgeParam("open_url") String str2);

    @BridgeMethod(privilege = "public", sync = BridgeSyncType.SYNC, value = g.a.f20440e)
    public abstract com.bytedance.sdk.bridge.model.b getAppInfo();

    @BridgeMethod(a.b.f20833a)
    public abstract void setClipboardDataBridge(@BridgeContext d dVar, @BridgeParam("content") String str, @BridgeParam("__all_params__") JSONObject jSONObject);
}
