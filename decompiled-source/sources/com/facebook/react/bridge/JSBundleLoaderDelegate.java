package com.facebook.react.bridge;

import android.content.res.AssetManager;
/* loaded from: classes8.dex */
public interface JSBundleLoaderDelegate {
    void loadScriptFromAssets(AssetManager assetManager, String str, boolean z);

    void loadScriptFromFile(String str, String str2, boolean z);

    void setSourceURLs(String str, String str2);
}
