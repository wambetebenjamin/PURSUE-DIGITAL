package com.appsflyer.internal;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes.dex */
public final class AFc1cSDK {
    public static Uri AFInAppEventParameterName(Intent intent) {
        if (intent == null) {
            return null;
        }
        final AFi1jSDK aFi1jSDK = new AFi1jSDK(intent);
        Intrinsics.checkNotNullParameter("android.intent.extra.REFERRER", "");
        Uri uri = (Uri) ((Parcelable) aFi1jSDK.AFInAppEventParameterName(new Function0<T>() { // from class: com.appsflyer.internal.AFi1jSDK.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: AFKeystoreWrapper */
            public final Parcelable invoke() {
                return aFi1jSDK.AFKeystoreWrapper.getParcelableExtra(r2);
            }
        }, new StringBuilder("Error while trying to read ").append("android.intent.extra.REFERRER").append(" extra from intent").toString(), null, true));
        if (uri != null) {
            return uri;
        }
        String AFKeystoreWrapper = aFi1jSDK.AFKeystoreWrapper("android.intent.extra.REFERRER_NAME");
        if (AFKeystoreWrapper != null) {
            return Uri.parse(AFKeystoreWrapper);
        }
        return null;
    }
}
