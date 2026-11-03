package com.ttnet.org.chromium.base.compat;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.storage.StorageManager;
import android.view.Display;
import android.view.InputEvent;
import android.view.VerifiedInputEvent;
import android.view.WindowManager;
import java.io.File;
/* loaded from: classes9.dex */
public final class ApiHelperForR {
    private ApiHelperForR() {
    }

    public static Display getDisplay(Context context) {
        return context.getDisplay();
    }

    public static File getVolumeDir(StorageManager storageManager, Uri uri) {
        return storageManager.getStorageVolume(uri).getDirectory();
    }

    public static VerifiedInputEvent verifyInputEvent(InputManager inputManager, InputEvent inputEvent) {
        return inputManager.verifyInputEvent(inputEvent);
    }

    public static void setProcessStateSummary(ActivityManager activityManager, byte[] bArr) {
        activityManager.setProcessStateSummary(bArr);
    }

    public static Rect getMaximumWindowMetricsBounds(WindowManager windowManager) {
        return windowManager.getMaximumWindowMetrics().getBounds();
    }
}
