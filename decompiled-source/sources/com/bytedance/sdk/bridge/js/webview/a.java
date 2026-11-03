package com.bytedance.sdk.bridge.js.webview;

import android.app.Activity;
import kotlin.Metadata;
/* compiled from: IWebview.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001a\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0001H&J\n\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\n\u0010\f\u001a\u0004\u0018\u00010\u0006H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0006H&¨\u0006\u000f"}, d2 = {"Lcom/bytedance/sdk/bridge/js/webview/IWebView;", "", "addJavascriptInterface", "", "object", "name", "", "evaluateJavascript", "script", "resultCallback", "getActivity", "Landroid/app/Activity;", "getUrl", com.bytedance.sdk.bridge.monitor.a.STATUS_MSG_JS_LOAD_URL, "url", "js-bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public interface a {
    void addJavascriptInterface(Object obj, String str);

    void evaluateJavascript(String str, Object obj);

    Activity getActivity();

    String getUrl();

    void loadUrl(String str);
}
