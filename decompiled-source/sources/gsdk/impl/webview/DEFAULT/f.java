package gsdk.impl.webview.DEFAULT;

import com.bytedance.sdk.bridge.annotation.BridgeContext;
import com.bytedance.sdk.bridge.annotation.BridgeMethod;
import com.bytedance.sdk.bridge.annotation.BridgeParam;
import gsdk.impl.webview.DEFAULT.a;
/* compiled from: AbsPageTopBridgeModule.java */
/* loaded from: classes10.dex */
public abstract class f {

    /* renamed from: a  reason: collision with root package name */
    protected g f21043a;

    @BridgeMethod(a.e.f20849i)
    public abstract void setTitle(@BridgeContext com.bytedance.sdk.bridge.model.d dVar, @BridgeParam("title") String str);

    public void a(g gVar) {
        this.f21043a = gVar;
    }
}
