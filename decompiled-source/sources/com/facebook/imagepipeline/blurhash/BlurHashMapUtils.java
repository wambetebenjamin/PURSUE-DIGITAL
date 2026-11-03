package com.facebook.imagepipeline.blurhash;

import android.net.Uri;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public class BlurHashMapUtils {
    private static Map<String, WeakReference<BlurHashListener>> requestUriMap = new HashMap();
    private static boolean OpenBlurhash = false;

    public static void setOpenBlurhash(boolean z) {
        OpenBlurhash = z;
    }

    public static boolean isEnableBlurHash() {
        return OpenBlurhash;
    }

    public static synchronized void putListener(Uri uri, WeakReference<BlurHashListener> weakReference) {
        synchronized (BlurHashMapUtils.class) {
            if (isEnableBlurHash() && uri != null) {
                requestUriMap.put(uri.toString(), weakReference);
            }
        }
    }

    public static WeakReference<BlurHashListener> getListener(Uri uri) {
        if (uri == null) {
            return null;
        }
        return requestUriMap.get(uri.toString());
    }

    public static void removeListener(Uri uri) {
        if (!isEnableBlurHash() || uri == null) {
            return;
        }
        requestUriMap.remove(uri.toString());
    }
}
