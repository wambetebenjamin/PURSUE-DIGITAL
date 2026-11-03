package gsdk.impl.webview.DEFAULT;

import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.ICoreInternalService;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;
/* compiled from: WebViewAppLogUtils.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001f"}, d2 = {"Lcom/bytedance/ttgame/module/webview/util/WebViewAppLogUtils;", "", "()V", "ERROR_CODE_RENDER_PROCESS_GONE", "", "ERROR_CODE_SSL_ERROR", "ERROR_MSG_RENDER_PROCESS_GONE", "", "WEBVIEW_CLOSE", "WEBVIEW_CLOSE_JS", "WEBVIEW_CLOSE_NATIVE", "WEBVIEW_JS_METHOD_NAME", "WEBVIEW_LOADING_RESULT", "WEBVIEW_OPEN", "WEBVIEW_OPEN_JS", "WEBVIEW_SESSION_ID", "WEBVIEW_START", "WEBVIEW_SUCCESS", "WEBVIEW_TIMESTAMP", "webViewSessionId", "getWebViewSessionId", "()Ljava/lang/String;", "setWebViewSessionId", "(Ljava/lang/String;)V", "getJsMethodName", "methodName", "sendLog", "", "key", "jsonObject", "Lorg/json/JSONObject;", "webview_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class bu {

    /* renamed from: a  reason: collision with root package name */
    public static final bu f21027a = new bu();

    /* renamed from: b  reason: collision with root package name */
    public static final String f21028b = "webview_start";

    /* renamed from: c  reason: collision with root package name */
    public static final String f21029c = "webview_open";

    /* renamed from: d  reason: collision with root package name */
    public static final String f21030d = "webview_loading_result";

    /* renamed from: e  reason: collision with root package name */
    public static final String f21031e = "webview_openjs";

    /* renamed from: f  reason: collision with root package name */
    public static final String f21032f = "webview_close";

    /* renamed from: g  reason: collision with root package name */
    public static final String f21033g = "wv_session_id";

    /* renamed from: h  reason: collision with root package name */
    public static final String f21034h = "timestamp";

    /* renamed from: i  reason: collision with root package name */
    public static final String f21035i = "success";

    /* renamed from: j  reason: collision with root package name */
    public static final String f21036j = "native";
    public static final String k = "js";
    public static final String l = "method_name";
    public static final int m = 1;
    public static final String n = "renderProcessGone";
    public static final int o = 2;
    private static String p;

    private bu() {
    }

    public final String a() {
        return p;
    }

    public final void a(String str) {
        p = str;
    }

    public final void a(String key, JSONObject jsonObject) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        try {
            if (Intrinsics.areEqual(key, f21028b)) {
                p = UUID.randomUUID().toString();
            }
            jsonObject.put(f21033g, p);
            jsonObject.put("timestamp", System.currentTimeMillis());
            ICoreInternalService iCoreInternalService = (ICoreInternalService) ModuleManager.getService$default(ModuleManager.INSTANCE, ICoreInternalService.class, false, (String) null, 6, (Object) null);
            if (iCoreInternalService != null) {
                iCoreInternalService.sendLog(key, jsonObject);
            }
            if (Intrinsics.areEqual(key, f21032f)) {
                p = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final String b(String methodName) {
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        if (StringsKt.startsWith$default(methodName, "view.", false, 2, (Object) null)) {
            String substring = methodName.substring(5);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            return substring;
        }
        return methodName;
    }
}
