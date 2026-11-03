package com.just.agentweb;

import android.webkit.ValueCallback;
/* loaded from: classes9.dex */
public interface QuickCallJs {
    void quickCallJs(String str);

    void quickCallJs(String str, ValueCallback<String> valueCallback, String... strArr);

    void quickCallJs(String str, String... strArr);
}
