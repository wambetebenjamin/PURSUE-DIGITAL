package com.bytedance.sdk.bridge.js.plugin;

import android.graphics.Bitmap;
import android.webkit.WebView;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: JSBridgePluginHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\r"}, d2 = {"Lcom/bytedance/sdk/bridge/js/plugin/JSBridgePluginHelper;", "", "()V", "iterateOnPageStarted", "", ViewHierarchyConstants.VIEW_KEY, "Landroid/webkit/WebView;", "url", "", "favicon", "Landroid/graphics/Bitmap;", "iterateshouldOverrideUrlLoading", "", "js-bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class a {
    public static final a INSTANCE = new a();

    private a() {
    }

    public final boolean iterateshouldOverrideUrlLoading(WebView view, String url) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(url, "url");
        if (b.INSTANCE.getPluginEnable()) {
            while (true) {
                for (c cVar : b.INSTANCE.getJsWebViewClientPlugins()) {
                    z = z || cVar.shouldOverrideUrlLoading(view, url);
                }
                return z;
            }
        }
        return false;
    }

    public final void iterateOnPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (b.INSTANCE.getPluginEnable()) {
            for (c cVar : b.INSTANCE.getJsWebViewClientPlugins()) {
                cVar.onPageStarted(webView, str, bitmap);
            }
        }
    }
}
