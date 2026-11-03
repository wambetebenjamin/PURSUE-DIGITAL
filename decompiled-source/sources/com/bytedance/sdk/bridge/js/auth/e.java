package com.bytedance.sdk.bridge.js.auth;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.WebView;
import com.bytedance.sdk.bridge.js.plugin.c;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: JSBridgeAuthWebViewClientPlugin.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/bytedance/sdk/bridge/js/auth/JSBridgeAuthWebViewClientPlugin;", "Lcom/bytedance/sdk/bridge/js/plugin/WebViewClientPlugin;", "()V", "onPageStarted", "", ViewHierarchyConstants.VIEW_KEY, "Landroid/webkit/WebView;", "url", "", "favicon", "Landroid/graphics/Bitmap;", "authjs-bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class e implements com.bytedance.sdk.bridge.js.plugin.c {
    public static final e INSTANCE = new e();

    private e() {
    }

    @Override // com.bytedance.sdk.bridge.js.plugin.c
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(url, "url");
        return c.a.shouldOverrideUrlLoading(this, view, url);
    }

    @Override // com.bytedance.sdk.bridge.js.plugin.c
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        c.a.onPageStarted(this, webView, str, bitmap);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        d dVar = d.INSTANCE;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        dVar.clearConfig(str);
    }
}
