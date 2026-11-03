package com.just.agentweb;

import android.view.ViewGroup;
import android.webkit.WebView;
/* loaded from: classes9.dex */
public interface IWebLayout<T extends WebView, V extends ViewGroup> {
    V getLayout();

    T getWebView();
}
