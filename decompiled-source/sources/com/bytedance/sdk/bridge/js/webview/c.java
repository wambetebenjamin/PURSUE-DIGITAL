package com.bytedance.sdk.bridge.js.webview;

import android.app.Activity;
import android.content.Context;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.bytedance.apm.constant.s;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: WebViewWrapper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0017J\u001a\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\nH\u0017J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\fH\u0016J\u000f\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0000¢\u0006\u0002\b\u0014J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\fH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/bytedance/sdk/bridge/js/webview/WebViewWrapper;", "Lcom/bytedance/sdk/bridge/js/webview/IWebView;", s.NET_CONSUME_TYPE_WEBVIEW, "Landroid/webkit/WebView;", "(Landroid/webkit/WebView;)V", "webviewWeakReference", "Ljava/lang/ref/WeakReference;", "addJavascriptInterface", "", "object", "", "name", "", "evaluateJavascript", "script", "resultCallback", "getActivity", "Landroid/app/Activity;", "getUrl", "getWebView", "getWebView$js_bridge_release", com.bytedance.sdk.bridge.monitor.a.STATUS_MSG_JS_LOAD_URL, "url", "js-bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class c implements a {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<WebView> f13246a;

    public c(WebView webview) {
        Intrinsics.checkParameterIsNotNull(webview, "webview");
        this.f13246a = new WeakReference<>(webview);
    }

    @Override // com.bytedance.sdk.bridge.js.webview.a
    public Activity getActivity() {
        WebView webView = this.f13246a.get();
        Context context = webView != null ? webView.getContext() : null;
        return context instanceof Activity ? context : null;
    }

    @Override // com.bytedance.sdk.bridge.js.webview.a
    public String getUrl() {
        WebView webView = this.f13246a.get();
        if (webView != null) {
            return webView.getUrl();
        }
        return null;
    }

    @Override // com.bytedance.sdk.bridge.js.webview.a
    public void loadUrl(String url) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        WebView webView = this.f13246a.get();
        if (webView != null) {
            webView.loadUrl(url);
        }
    }

    @Override // com.bytedance.sdk.bridge.js.webview.a
    public void evaluateJavascript(String script, Object obj) {
        Intrinsics.checkParameterIsNotNull(script, "script");
        if (obj instanceof ValueCallback) {
            WebView webView = this.f13246a.get();
            if (webView != null) {
                webView.evaluateJavascript(script, (ValueCallback) obj);
                return;
            }
            return;
        }
        WebView webView2 = this.f13246a.get();
        if (webView2 != null) {
            webView2.evaluateJavascript(script, null);
        }
    }

    @Override // com.bytedance.sdk.bridge.js.webview.a
    public void addJavascriptInterface(Object object, String name) {
        Intrinsics.checkParameterIsNotNull(object, "object");
        Intrinsics.checkParameterIsNotNull(name, "name");
        WebView webView = this.f13246a.get();
        if (webView != null) {
            webView.addJavascriptInterface(object, name);
        }
    }

    public final WebView getWebView$js_bridge_release() {
        return this.f13246a.get();
    }
}
