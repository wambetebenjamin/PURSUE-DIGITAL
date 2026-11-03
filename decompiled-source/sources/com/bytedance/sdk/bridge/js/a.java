package com.bytedance.sdk.bridge.js;

import com.bytedance.sdk.bridge.annotation.BridgeContext;
import com.bytedance.sdk.bridge.annotation.BridgeMethod;
import com.bytedance.sdk.bridge.annotation.BridgeParam;
import com.bytedance.sdk.bridge.model.d;
import gsdk.impl.webview.DEFAULT.a;
import kotlin.Deprecated;
import kotlin.Metadata;
import org.json.JSONObject;
/* compiled from: AbsCommonJsBridgeModule.kt */
@Deprecated(message = "")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H'J\u0012\u0010\u0005\u001a\u00020\u00042\b\b\u0001\u0010\u0006\u001a\u00020\u0007H'J\u0012\u0010\b\u001a\u00020\u00042\b\b\u0001\u0010\u0006\u001a\u00020\u0007H'J\b\u0010\t\u001a\u00020\u0004H'J\b\u0010\n\u001a\u00020\u0004H'J\u001e\u0010\u000b\u001a\u00020\u00042\b\b\u0001\u0010\f\u001a\u00020\r2\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000fH'¨\u0006\u0010"}, d2 = {"Lcom/bytedance/sdk/bridge/js/AbsCommonJsBridgeModule;", "", "()V", "close", "", a.e.p, "bridgeContext", "Lcom/bytedance/sdk/bridge/model/IBridgeContext;", a.e.o, a.e.k, a.e.f20850j, a.e.f20849i, "title", "", "totalParams", "Lorg/json/JSONObject;", "js-bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public abstract class a {
    @BridgeMethod(privilege = "public", value = "close")
    public abstract void close();

    @BridgeMethod(privilege = "public", value = a.e.p)
    public abstract void onPageInvisible(@BridgeContext d dVar);

    @BridgeMethod(privilege = "public", value = a.e.o)
    public abstract void onPageVisible(@BridgeContext d dVar);

    @BridgeMethod(privilege = "public", value = a.e.k)
    public abstract void setSwipeDisabled();

    @BridgeMethod(privilege = "public", value = a.e.f20850j)
    public abstract void setSwipeEnabled();

    @BridgeMethod(a.e.f20849i)
    public abstract void setTitle(@BridgeParam("title") String str, @BridgeParam("__all_params__") JSONObject jSONObject);
}
