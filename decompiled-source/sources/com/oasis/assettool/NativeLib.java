package com.oasis.assettool;

import android.content.res.AssetManager;
/* loaded from: classes9.dex */
public class NativeLib {
    static {
        System.loadLibrary("OasisAssetTool");
    }

    public static native void SetAssetManager(AssetManager assetManager);
}
