package gsdk.impl.webview.DEFAULT;

import android.text.TextUtils;
import android.webkit.WebView;
import androidx.exifinterface.media.ExifInterface;
import com.bytedance.ttgame.main.internal.sdkmonitor.IModuleLogger;
import com.bytedance.ttgame.module.webview.WebViewService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: JSBridgePlatformHandler.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u0004\u0018\u0001H\u0004\"\u0004\b\u0000\u0010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u0010\u0007J\u0012\u0010\b\u001a\u00020\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\"\u0010\n\u001a\u00020\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lcom/bytedance/ttgame/module/bridge/api/utils/JSBridgePlatformHandler;", "Lcom/bytedance/ttgame/module/bridge/api/utils/BridgePlatformHandler;", "()V", "getPageIdentification", ExifInterface.GPS_DIRECTION_TRUE, "bridgeContext", "Lcom/bytedance/sdk/bridge/model/IBridgeContext;", "(Lcom/bytedance/sdk/bridge/model/IBridgeContext;)Ljava/lang/Object;", "isSafeIdentification", "", "sendEvent", "event", "", "params", "Lorg/json/JSONObject;", "webview_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class k implements i {

    /* renamed from: a  reason: collision with root package name */
    public static final k f21046a = new k();

    private k() {
    }

    @Override // gsdk.impl.webview.DEFAULT.i
    public boolean b(com.bytedance.sdk.bridge.model.d dVar) {
        String str = (String) a(dVar);
        if (!TextUtils.isEmpty(str)) {
            return j.a(str);
        }
        IModuleLogger iModuleLogger = WebViewService.logUtil;
        if (iModuleLogger != null) {
            iModuleLogger.w("isSafeIdentification", "false");
            return false;
        }
        return false;
    }

    @Override // gsdk.impl.webview.DEFAULT.i
    public <T> T a(com.bytedance.sdk.bridge.model.d dVar) {
        WebView webView;
        if (dVar == null || !(dVar instanceof com.bytedance.sdk.bridge.js.spec.e) || (webView = ((com.bytedance.sdk.bridge.js.spec.e) dVar).getWebView()) == null) {
            return null;
        }
        T t = (T) webView.getUrl();
        if (t == null ? true : t instanceof Object) {
            return t;
        }
        return null;
    }

    @Override // gsdk.impl.webview.DEFAULT.i
    public boolean a(com.bytedance.sdk.bridge.model.d dVar, String event, JSONObject params) {
        WebView webView;
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(params, "params");
        if (dVar == null || !(dVar instanceof com.bytedance.sdk.bridge.js.spec.e) || (webView = ((com.bytedance.sdk.bridge.js.spec.e) dVar).getWebView()) == null) {
            return false;
        }
        return com.bytedance.sdk.bridge.js.spec.g.INSTANCE.sendEvent(event, params, webView);
    }
}
