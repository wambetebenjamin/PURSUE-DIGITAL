package com.ttnet.org.chromium.base.compat;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.PowerManager;
import android.os.Process;
import android.os.UserManager;
import android.security.NetworkSecurityPolicy;
import android.view.ActionMode;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
/* loaded from: classes9.dex */
public final class ApiHelperForM {
    public static int getPendingIntentImmutableFlag() {
        return DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS;
    }

    public static void setAlarmManagerExactAndAllowWhileIdle(AlarmManager alarmManager, int i2, long j2, PendingIntent pendingIntent) {
    }

    private ApiHelperForM() {
    }

    public static void onPageCommitVisible(WebViewClient webViewClient, WebView webView, String str) {
        webViewClient.onPageCommitVisible(webView, str);
    }

    public static boolean isProcess64Bit() {
        return Process.is64Bit();
    }

    public static Network getBoundNetworkForProcess(ConnectivityManager connectivityManager) {
        return connectivityManager.getBoundNetworkForProcess();
    }

    public static long getNetworkHandle(Network network) {
        if (network == null) {
            return 0L;
        }
        return network.getNetworkHandle();
    }

    public static Network getActiveNetwork(ConnectivityManager connectivityManager) {
        return connectivityManager.getActiveNetwork();
    }

    public static NetworkInfo getNetworkInfo(ConnectivityManager connectivityManager, Network network) {
        return connectivityManager.getNetworkInfo(network);
    }

    public static void requestActivityPermissions(Activity activity, String[] strArr, int i2) {
        activity.requestPermissions(strArr, i2);
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
        return activity.shouldShowRequestPermissionRationale(str);
    }

    public static boolean isPermissionRevokedByPolicy(Activity activity, String str) {
        return activity.getPackageManager().isPermissionRevokedByPolicy(str, activity.getPackageName());
    }

    public static boolean isCleartextTrafficPermitted() {
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
    }

    public static boolean isSystemUser(UserManager userManager) {
        return userManager.isSystemUser();
    }

    public static void invalidateContentRectOnActionMode(ActionMode actionMode) {
        actionMode.invalidateContentRect();
    }

    public static void onWindowFocusChangedOnActionMode(ActionMode actionMode, boolean z) {
        actionMode.onWindowFocusChanged(z);
    }

    public static int getActionModeType(ActionMode actionMode) {
        return actionMode.getType();
    }

    public static long getDefaultActionModeHideDuration() {
        return ViewConfiguration.getDefaultActionModeHideDuration();
    }

    public static void hideActionMode(ActionMode actionMode, long j2) {
        actionMode.hide(j2);
    }

    public static void reportNetworkConnectivity(ConnectivityManager connectivityManager, Network network, boolean z) {
        connectivityManager.reportNetworkConnectivity(network, z);
    }

    public static int getActionButton(MotionEvent motionEvent) {
        return motionEvent.getActionButton();
    }

    public static Display.Mode[] getDisplaySupportedModes(Display display) {
        return display.getSupportedModes();
    }

    public static Display.Mode getDisplayMode(Display display) {
        return display.getMode();
    }

    public static int getModePhysicalWidth(Display.Mode mode) {
        return mode.getPhysicalWidth();
    }

    public static int getModePhysicalHeight(Display.Mode mode) {
        return mode.getPhysicalHeight();
    }

    public static boolean isCharging(BatteryManager batteryManager) {
        return batteryManager.isCharging();
    }

    public static Icon createIconWithBitmap(Bitmap bitmap) {
        return Icon.createWithBitmap(bitmap);
    }

    public static boolean isDeviceIdleMode(PowerManager powerManager) {
        return powerManager.isDeviceIdleMode();
    }

    public static Notification.Builder setSmallIcon(Notification.Builder builder, Icon icon) {
        return builder.setSmallIcon(icon);
    }

    public static Icon createIconWithResource(Context context, int i2) {
        return Icon.createWithResource(context, i2);
    }

    public static <T> T getSystemService(Context context, Class<T> cls) {
        return (T) context.getSystemService(cls);
    }

    public static Notification.Action.Builder newNotificationActionBuilder(Icon icon, CharSequence charSequence, PendingIntent pendingIntent) {
        return new Notification.Action.Builder(icon, charSequence, pendingIntent);
    }
}
