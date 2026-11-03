package com.ttnet.org.chromium.base.compat;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.autofill.AutofillManager;
import com.ttnet.org.chromium.base.StrictModeContext;
/* loaded from: classes9.dex */
public final class ApiHelperForO {
    public static void addItem(ClipData clipData, ContentResolver contentResolver, ClipData.Item item) {
    }

    private ApiHelperForO() {
    }

    public static boolean isWideColorGamut(Display display) {
        return display.isWideColorGamut();
    }

    public static void setColorMode(Window window, int i2) {
        window.setColorMode(i2);
    }

    public static boolean isScreenWideColorGamut(Configuration configuration) {
        return configuration.isScreenWideColorGamut();
    }

    public static boolean isInstantApp(PackageManager packageManager) {
        return packageManager.isInstantApp();
    }

    public static void setDefaultFocusHighlightEnabled(View view, boolean z) {
        view.setDefaultFocusHighlightEnabled(z);
    }

    public static long getTimestamp(ClipDescription clipDescription) {
        return clipDescription.getTimestamp();
    }

    public static String[] getSplitNames(ApplicationInfo applicationInfo) {
        return applicationInfo.splitNames;
    }

    public static Context createContextForSplit(Context context, String str) {
        StrictModeContext allowDiskReads = StrictModeContext.allowDiskReads();
        try {
            Context createContextForSplit = context.createContextForSplit(str);
            if (allowDiskReads != null) {
                allowDiskReads.close();
            }
            return createContextForSplit;
        } catch (Throwable th) {
            if (allowDiskReads != null) {
                try {
                    allowDiskReads.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static void cancelAutofillSession(Activity activity) {
        AutofillManager autofillManager = (AutofillManager) activity.getSystemService(AutofillManager.class);
        if (autofillManager != null) {
            autofillManager.cancel();
        }
    }

    public static void notifyValueChangedForAutofill(View view) {
        AutofillManager autofillManager = (AutofillManager) view.getContext().getSystemService(AutofillManager.class);
        if (autofillManager != null) {
            autofillManager.notifyValueChanged(view);
        }
    }

    public static void registerNetworkCallback(ConnectivityManager connectivityManager, NetworkRequest networkRequest, ConnectivityManager.NetworkCallback networkCallback, Handler handler) {
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback, handler);
    }

    public static boolean areAnimatorsEnabled() {
        return ValueAnimator.areAnimatorsEnabled();
    }

    public static Notification.Builder setChannelId(Notification.Builder builder, String str) {
        return builder.setChannelId(str);
    }

    public static Notification.Builder setTimeoutAfter(Notification.Builder builder, long j2) {
        return builder.setTimeoutAfter(j2);
    }

    public static void registerDefaultNetworkCallback(ConnectivityManager connectivityManager, ConnectivityManager.NetworkCallback networkCallback, Handler handler) {
        connectivityManager.registerDefaultNetworkCallback(networkCallback, handler);
    }

    public static String getNotificationChannelId(Notification notification) {
        return notification.getChannelId();
    }

    public static Intent registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i2) {
        return context.registerReceiver(broadcastReceiver, intentFilter, str, handler, i2);
    }
}
