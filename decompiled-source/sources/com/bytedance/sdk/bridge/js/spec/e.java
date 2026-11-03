package com.bytedance.sdk.bridge.js.spec;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import android.webkit.WebView;
import com.bytedance.bdturing.methods.l;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: JsBridgeContext.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003J\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005J\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/bytedance/sdk/bridge/js/spec/JsBridgeContext;", "Lcom/bytedance/sdk/bridge/model/IBridgeContext;", "webView", "Lcom/bytedance/sdk/bridge/js/webview/IWebView;", "callBackId", "", "currentUrl", "(Lcom/bytedance/sdk/bridge/js/webview/IWebView;Ljava/lang/String;Ljava/lang/String;)V", "getCallBackId", "()Ljava/lang/String;", "webViewRef", "Ljava/lang/ref/WeakReference;", l.VALUE_CALLBACK, "", "bridgeResult", "Lcom/bytedance/sdk/bridge/model/BridgeResult;", "getActivity", "Landroid/app/Activity;", "getIWebView", "getUri", "getWebView", "Landroid/webkit/WebView;", "js-bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public class e implements com.bytedance.sdk.bridge.model.d {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<com.bytedance.sdk.bridge.js.webview.a> f13237a;

    /* renamed from: b  reason: collision with root package name */
    private final String f13238b;

    /* renamed from: c  reason: collision with root package name */
    private final String f13239c;

    public e(com.bytedance.sdk.bridge.js.webview.a webView, String str, String currentUrl) {
        Intrinsics.checkParameterIsNotNull(webView, "webView");
        Intrinsics.checkParameterIsNotNull(currentUrl, "currentUrl");
        this.f13238b = str;
        this.f13239c = currentUrl;
        this.f13237a = new WeakReference<>(webView);
    }

    public /* synthetic */ e(com.bytedance.sdk.bridge.js.webview.a aVar, String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(aVar, str, (i2 & 4) != 0 ? "" : str2);
    }

    public final String getCallBackId() {
        return this.f13238b;
    }

    public final WebView getWebView() {
        com.bytedance.sdk.bridge.js.webview.a aVar = this.f13237a.get();
        if (!(aVar instanceof com.bytedance.sdk.bridge.js.webview.c)) {
            aVar = null;
        }
        com.bytedance.sdk.bridge.js.webview.c cVar = (com.bytedance.sdk.bridge.js.webview.c) aVar;
        if (cVar != null) {
            return cVar.getWebView$js_bridge_release();
        }
        return null;
    }

    public final com.bytedance.sdk.bridge.js.webview.a getIWebView() {
        return this.f13237a.get();
    }

    public final String getUri() {
        if (TextUtils.isEmpty(this.f13239c)) {
            com.bytedance.sdk.bridge.js.webview.a aVar = this.f13237a.get();
            if (aVar != null) {
                return aVar.getUrl();
            }
            return null;
        }
        return this.f13239c;
    }

    @Override // com.bytedance.sdk.bridge.model.d
    public Activity getActivity() {
        com.bytedance.sdk.bridge.js.webview.a aVar = this.f13237a.get();
        Activity activity = aVar != null ? aVar.getActivity() : null;
        if (activity != null) {
            return activity;
        }
        WebView webView = getWebView();
        for (Context context = webView != null ? webView.getContext() : null; context != null && (context instanceof ContextWrapper); context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        WebView webView2 = getWebView();
        ViewParent parent = webView2 != null ? webView2.getParent() : null;
        while (parent != null) {
            View view = (View) (!(parent instanceof View) ? null : parent);
            if ((view != null ? view.getContext() : null) instanceof Activity) {
                break;
            }
            parent = parent.getParent();
        }
        if (!(parent instanceof View)) {
            parent = null;
        }
        View view2 = (View) parent;
        for (Context context2 = view2 != null ? view2.getContext() : null; context2 != null && (context2 instanceof ContextWrapper); context2 = ((ContextWrapper) context2).getBaseContext()) {
            if (context2 instanceof Activity) {
                return (Activity) context2;
            }
        }
        return null;
    }

    @Override // com.bytedance.sdk.bridge.model.d
    public void callback(com.bytedance.sdk.bridge.model.b bridgeResult) {
        Intrinsics.checkParameterIsNotNull(bridgeResult, "bridgeResult");
        com.bytedance.sdk.bridge.js.webview.a iWebView = getIWebView();
        if (TextUtils.isEmpty(this.f13238b) || iWebView == null) {
            return;
        }
        com.bytedance.sdk.bridge.js.delegate.b bVar = com.bytedance.sdk.bridge.js.delegate.b.INSTANCE;
        String str = this.f13238b;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        com.bytedance.sdk.bridge.js.delegate.b.sendCallbackMsg$default(bVar, str, bridgeResult.toJSON(), iWebView, false, null, 16, null);
    }
}
