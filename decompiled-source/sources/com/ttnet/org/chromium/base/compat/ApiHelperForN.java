package com.ttnet.org.chromium.base.compat;

import android.app.Activity;
import android.app.Notification;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ClipData;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.media.MediaCodec;
import android.net.ConnectivityManager;
import android.os.Process;
import android.security.NetworkSecurityPolicy;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RemoteViews;
/* loaded from: classes9.dex */
public final class ApiHelperForN {
    private ApiHelperForN() {
    }

    public static boolean shouldOverrideUrlLoading(WebViewClient webViewClient, WebView webView, WebResourceRequest webResourceRequest) {
        return webViewClient.shouldOverrideUrlLoading(webView, webResourceRequest);
    }

    public static JobInfo getPendingJob(JobScheduler jobScheduler, int i2) {
        return jobScheduler.getPendingJob(i2);
    }

    public static boolean startDragAndDrop(View view, ClipData clipData, View.DragShadowBuilder dragShadowBuilder, Object obj, int i2) {
        return view.startDragAndDrop(clipData, dragShadowBuilder, obj, i2);
    }

    public static void setPointerIcon(View view, PointerIcon pointerIcon) {
        view.setPointerIcon(pointerIcon);
    }

    public static PointerIcon createPointerIcon(Bitmap bitmap, float f2, float f3) {
        return PointerIcon.create(bitmap, f2, f3);
    }

    public static void setCryptoInfoPattern(MediaCodec.CryptoInfo cryptoInfo, int i2, int i3) {
        cryptoInfo.setPattern(new MediaCodec.CryptoInfo.Pattern(i2, i3));
    }

    public static void setVrModeEnabled(Activity activity, boolean z, ComponentName componentName) {
        activity.setVrModeEnabled(z, componentName);
    }

    public static boolean isCleartextTrafficPermitted(String str) {
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }

    public static PointerIcon onResolvePointerIcon(View view, MotionEvent motionEvent, int i2) {
        return view.onResolvePointerIcon(motionEvent, i2);
    }

    public static long getStartUptimeMillis() {
        return Process.getStartUptimeMillis();
    }

    public static Notification.Builder setCustomContentView(Notification.Builder builder, RemoteViews remoteViews) {
        return builder.setCustomContentView(remoteViews);
    }

    public static Notification.Builder setCustomBigContentView(Notification.Builder builder, RemoteViews remoteViews) {
        return builder.setCustomBigContentView(remoteViews);
    }

    public static int getRestrictBackgroundStatus(ConnectivityManager connectivityManager) {
        return connectivityManager.getRestrictBackgroundStatus();
    }
}
