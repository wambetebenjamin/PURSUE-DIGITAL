package com.appsflyer.internal;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* loaded from: classes.dex */
public final class AFi1uSDK implements AFh1aSDK {
    private String AFInAppEventParameterName;

    @Override // com.appsflyer.internal.AFh1aSDK
    public final void values(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "");
        String str = this.AFInAppEventParameterName;
        if (str == null || str.length() == 0) {
            this.AFInAppEventParameterName = AFKeystoreWrapper(activity);
        }
    }

    @Override // com.appsflyer.internal.AFh1aSDK
    public final String valueOf(Activity activity) {
        String str = this.AFInAppEventParameterName;
        this.AFInAppEventParameterName = null;
        String str2 = str;
        return !(str2 == null || str2.length() == 0) ? str : AFKeystoreWrapper(activity);
    }

    private static String AFKeystoreWrapper(Activity activity) {
        Uri AFInAppEventParameterName = AFc1cSDK.AFInAppEventParameterName(activity != null ? activity.getIntent() : null);
        String obj = AFInAppEventParameterName != null ? AFInAppEventParameterName.toString() : null;
        if (obj == null) {
            obj = "";
        }
        if (AFKeystoreWrapper(obj)) {
            return null;
        }
        return obj;
    }

    private static boolean AFKeystoreWrapper(String str) {
        return StringsKt.startsWith$default(str, "android-app://", false, 2, (Object) null);
    }

    @Override // com.appsflyer.internal.AFh1aSDK
    public final String AFInAppEventParameterName(Activity activity) {
        Uri uri;
        if (activity == null || activity.getIntent() == null) {
            uri = null;
        } else if (Build.VERSION.SDK_INT >= 22) {
            uri = activity.getReferrer();
        } else {
            uri = AFc1cSDK.AFInAppEventParameterName(activity.getIntent());
        }
        String obj = uri != null ? uri.toString() : null;
        return obj == null ? "" : obj;
    }
}
