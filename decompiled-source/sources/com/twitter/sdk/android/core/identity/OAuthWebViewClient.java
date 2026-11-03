package com.twitter.sdk.android.core.identity;

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.twitter.sdk.android.core.internal.network.UrlUtils;
import java.net.URI;
import java.util.Map;
import java.util.TreeMap;
/* loaded from: classes10.dex */
class OAuthWebViewClient extends WebViewClient {
    private final String completeUrl;
    private final Listener listener;

    /* loaded from: classes10.dex */
    interface Listener {
        void onError(WebViewException webViewException);

        void onPageFinished(WebView webView, String str);

        void onSuccess(Bundle bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OAuthWebViewClient(String str, Listener listener) {
        this.completeUrl = str;
        this.listener = listener;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.listener.onPageFinished(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.startsWith(this.completeUrl)) {
            TreeMap<String, String> queryParams = UrlUtils.getQueryParams(URI.create(str), false);
            Bundle bundle = new Bundle(queryParams.size());
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }
            this.listener.onSuccess(bundle);
            return true;
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i2, String str, String str2) {
        super.onReceivedError(webView, i2, str, str2);
        this.listener.onError(new WebViewException(i2, str, str2));
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        this.listener.onError(new WebViewException(sslError.getPrimaryError(), null, null));
    }
}
