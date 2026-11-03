package com.ttnet.org.chromium.base;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.TransactionTooLargeException;
import java.util.Collections;
import java.util.List;
/* loaded from: classes9.dex */
public class PackageManagerUtils {
    public static final Intent BROWSER_INTENT = new Intent().setAction("android.intent.action.VIEW").addCategory("android.intent.category.BROWSABLE").setData(Uri.fromParts("http", "", null));
    private static final String TAG = "PackageManagerUtils";

    public static ResolveInfo resolveActivity(Intent intent, int i2) {
        try {
            StrictModeContext allowDiskWrites = StrictModeContext.allowDiskWrites();
            ResolveInfo resolveActivity = ContextUtils.getApplicationContext().getPackageManager().resolveActivity(intent, i2);
            if (allowDiskWrites != null) {
                allowDiskWrites.close();
            }
            return resolveActivity;
        } catch (RuntimeException e2) {
            handleExpectedExceptionsOrRethrow(e2, intent);
            return null;
        }
    }

    public static List<ResolveInfo> queryIntentActivities(Intent intent, int i2) {
        try {
            StrictModeContext allowDiskReads = StrictModeContext.allowDiskReads();
            List<ResolveInfo> queryIntentActivities = ContextUtils.getApplicationContext().getPackageManager().queryIntentActivities(intent, i2);
            if (allowDiskReads != null) {
                allowDiskReads.close();
            }
            return queryIntentActivities;
        } catch (RuntimeException e2) {
            handleExpectedExceptionsOrRethrow(e2, intent);
            return Collections.emptyList();
        }
    }

    public static boolean canResolveActivity(Intent intent, int i2) {
        return !queryIntentActivities(intent, i2).isEmpty();
    }

    public static boolean canResolveActivity(Intent intent) {
        return canResolveActivity(intent, 0);
    }

    public static Intent getQueryInstalledHomeLaunchersIntent() {
        return new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME");
    }

    public static ResolveInfo resolveDefaultWebBrowserActivity() {
        return resolveActivity(BROWSER_INTENT, 65536);
    }

    public static List<ResolveInfo> queryAllWebBrowsersInfo() {
        return queryIntentActivities(BROWSER_INTENT, 983040);
    }

    public static List<ResolveInfo> queryAllLaunchersInfo() {
        return queryIntentActivities(getQueryInstalledHomeLaunchersIntent(), 131072);
    }

    private static void handleExpectedExceptionsOrRethrow(RuntimeException runtimeException, Intent intent) {
        if ((runtimeException instanceof NullPointerException) || (runtimeException.getCause() instanceof TransactionTooLargeException)) {
            Log.e(TAG, "Could not resolve Activity for intent " + intent.toString(), runtimeException);
            return;
        }
        throw runtimeException;
    }
}
