package com.ttnet.org.chromium.net.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.ttnet.org.chromium.net.impl.CronetLogger;
/* loaded from: classes9.dex */
public final class CronetManifest {
    static final String METRICS_OPT_IN_META_DATA_STR = "com.ttnet.org.chromium.net.CronetMetricsOptIn";

    private CronetManifest() {
    }

    public static boolean isAppOptedInForTelemetry(Context context, CronetLogger.CronetSource cronetSource) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                return false;
            }
            return applicationInfo.metaData.getBoolean(METRICS_OPT_IN_META_DATA_STR);
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
