package com.facebook.react.bridge;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.react.common.DebugServerException;
import com.facebook.react.common.JavascriptException;
import java.io.File;
/* loaded from: classes8.dex */
public abstract class JSBundleLoader {
    public abstract String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate);

    public static JSBundleLoader createAssetLoader(final Context context, final String str, final boolean z) {
        return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader.1
            @Override // com.facebook.react.bridge.JSBundleLoader
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                jSBundleLoaderDelegate.loadScriptFromAssets(context.getAssets(), str, z);
                return str;
            }
        };
    }

    public static JSBundleLoader createFileLoader(String str) {
        return createFileLoader(str, str, false);
    }

    public static JSBundleLoader createFileLoader(final String str, final String str2, final boolean z) {
        return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader.2
            @Override // com.facebook.react.bridge.JSBundleLoader
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                if (!TextUtils.isEmpty(str) && new File(str).exists()) {
                    jSBundleLoaderDelegate.loadScriptFromFile(str, str2, z);
                    return str;
                }
                ((CatalystInstanceImpl) jSBundleLoaderDelegate).onNativeException(new JavascriptException("bundle file now found"));
                return str;
            }
        };
    }

    public static JSBundleLoader createCachedBundleFromNetworkLoader(final String str, final String str2) {
        return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader.3
            @Override // com.facebook.react.bridge.JSBundleLoader
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                try {
                    jSBundleLoaderDelegate.loadScriptFromFile(str2, str, false);
                    return str;
                } catch (Exception e2) {
                    throw DebugServerException.makeGeneric(str, e2.getMessage(), e2);
                }
            }
        };
    }

    public static JSBundleLoader createRemoteDebuggerBundleLoader(final String str, final String str2) {
        return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader.4
            @Override // com.facebook.react.bridge.JSBundleLoader
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                jSBundleLoaderDelegate.setSourceURLs(str2, str);
                return str2;
            }
        };
    }
}
