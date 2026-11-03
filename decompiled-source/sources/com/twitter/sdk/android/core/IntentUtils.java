package com.twitter.sdk.android.core;

import android.content.Context;
import android.content.Intent;
/* loaded from: classes10.dex */
public class IntentUtils {
    public static boolean isActivityAvailable(Context context, Intent intent) {
        return !context.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
    }

    public static boolean safeStartActivity(Context context, Intent intent) {
        if (isActivityAvailable(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        return false;
    }
}
