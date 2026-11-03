package com.oasis.android;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
/* loaded from: classes9.dex */
public final class ConfigHelper {
    public static String getConfig(Context context, String str) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null || !bundle.containsKey(str)) {
                return "";
            }
            Object obj = bundle.get(str);
            if (obj != null) {
                return obj.toString();
            }
            return null;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
