package com.just.agentweb;

import android.webkit.ValueCallback;
/* loaded from: classes9.dex */
public interface JsAccessEntrace extends QuickCallJs {
    void callJs(String str);

    void callJs(String str, ValueCallback<String> valueCallback);
}
