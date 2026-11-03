package com.ttnet.org.chromium.base.compat;

import android.app.ForegroundServiceStartNotAllowedException;
import android.app.Notification;
import android.app.PictureInPictureParams;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.view.Display;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextLinks;
import android.view.textclassifier.TextSelection;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ttnet.org.chromium.base.ApiCompatibilityUtils;
import com.ttnet.org.chromium.base.ContextUtils;
import com.ttnet.org.chromium.base.Log;
/* loaded from: classes9.dex */
public final class ApiHelperForS {
    private static final String TAG = "ApiHelperForS";

    public static int getPendingIntentMutableFlag() {
        return DownloadExpSwitchCode.BACK_CLEAR_DATA;
    }

    private ApiHelperForS() {
    }

    public static boolean isStyleText(ClipDescription clipDescription) {
        return clipDescription.isStyledText();
    }

    public static float getConfidenceScore(ClipDescription clipDescription, String str) {
        return clipDescription.getConfidenceScore(str);
    }

    public static boolean isGetClassificationStatusIsComplete(ClipDescription clipDescription) {
        return clipDescription.getClassificationStatus() == 3;
    }

    public static TextLinks getTextLinks(ClipData.Item item) {
        return item.getTextLinks();
    }

    public static boolean hasBluetoothConnectPermission() {
        return ApiCompatibilityUtils.checkPermission(ContextUtils.getApplicationContext(), "android.permission.BLUETOOTH_CONNECT", Process.myPid(), Process.myUid()) == 0;
    }

    public static void setAutoEnterEnabled(PictureInPictureParams.Builder builder, boolean z) {
        builder.setAutoEnterEnabled(z);
    }

    public static TextSelection.Request.Builder setIncludeTextClassification(TextSelection.Request.Builder builder, boolean z) {
        return builder.setIncludeTextClassification(z);
    }

    public static TextClassification getTextClassification(TextSelection textSelection) {
        return textSelection.getTextClassification();
    }

    public static Context createWindowContext(Context context, Display display, int i2, Bundle bundle) {
        return context.createWindowContext(display, i2, bundle);
    }

    public static void startForeground(Service service, int i2, Notification notification, int i3) {
        try {
            service.startForeground(i2, notification, i3);
        } catch (ForegroundServiceStartNotAllowedException e2) {
            Log.e(TAG, "Cannot run service as foreground: " + e2 + " for notification channel " + notification.getChannelId() + " notification id " + i2, new Object[0]);
        }
    }
}
