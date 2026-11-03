package com.bytedance.sdk.bridge.js.plugin;

import android.graphics.Bitmap;
import android.webkit.WebView;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: WebViewClientPlugin.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\f"}, d2 = {"Lcom/bytedance/sdk/bridge/js/plugin/WebViewClientPlugin;", "", "onPageStarted", "", ViewHierarchyConstants.VIEW_KEY, "Landroid/webkit/WebView;", "url", "", "favicon", "Landroid/graphics/Bitmap;", "shouldOverrideUrlLoading", "", "js-bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public interface c {

    /* compiled from: WebViewClientPlugin.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
    /* loaded from: classes3.dex */
    public static final class a {
        public static void onPageStarted(c cVar, WebView webView, String str, Bitmap bitmap) {
        }

        public static boolean shouldOverrideUrlLoading(c cVar, WebView view, String url) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(url, "url");
            return false;
        }
    }

    void onPageStarted(WebView webView, String str, Bitmap bitmap);

    boolean shouldOverrideUrlLoading(WebView webView, String str);
}
