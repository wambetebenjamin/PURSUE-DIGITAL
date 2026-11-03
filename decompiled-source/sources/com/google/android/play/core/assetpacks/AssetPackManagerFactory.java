package com.google.android.play.core.assetpacks;

import android.content.Context;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class AssetPackManagerFactory {
    private AssetPackManagerFactory() {
    }

    public static synchronized AssetPackManager getInstance(Context context) {
        AssetPackManager a2;
        synchronized (AssetPackManagerFactory.class) {
            a2 = d.a(context).a();
        }
        return a2;
    }
}
