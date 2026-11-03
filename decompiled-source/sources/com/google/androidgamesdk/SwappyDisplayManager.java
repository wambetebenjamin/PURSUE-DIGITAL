package com.google.androidgamesdk;

import android.app.Activity;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
/* loaded from: classes6.dex */
public class SwappyDisplayManager implements DisplayManager.DisplayListener {
    private Activity mActivity;
    private long mCookie;
    private Display.Mode mCurrentMode;
    private d mLooper;
    private WindowManager mWindowManager;
    private final String LOG_TAG = "SwappyDisplayManager";
    private final boolean DEBUG = false;
    private final long ONE_MS_IN_NS = 1000000;
    private final long ONE_S_IN_NS = 1000000000;

    public SwappyDisplayManager(long j2, Activity activity) {
        String string;
        try {
            Bundle bundle = activity.getPackageManager().getActivityInfo(activity.getIntent().getComponent(), 128).metaData;
            if (bundle != null && (string = bundle.getString("android.app.lib_name")) != null) {
                System.loadLibrary(string);
            }
        } catch (Throwable th) {
            Log.e("SwappyDisplayManager", th.getMessage());
        }
        this.mCookie = j2;
        this.mActivity = activity;
        WindowManager windowManager = (WindowManager) activity.getSystemService(WindowManager.class);
        this.mWindowManager = windowManager;
        Display defaultDisplay = windowManager.getDefaultDisplay();
        this.mCurrentMode = defaultDisplay.getMode();
        updateSupportedRefreshRates(defaultDisplay);
        DisplayManager displayManager = (DisplayManager) this.mActivity.getSystemService(DisplayManager.class);
        synchronized (this) {
            d dVar = new d();
            this.mLooper = dVar;
            dVar.start();
            displayManager.registerDisplayListener(this, this.mLooper.f16891a);
        }
    }

    private boolean modeMatchesCurrentResolution(Display.Mode mode) {
        return mode.getPhysicalHeight() == this.mCurrentMode.getPhysicalHeight() && mode.getPhysicalWidth() == this.mCurrentMode.getPhysicalWidth();
    }

    private native void nOnRefreshPeriodChanged(long j2, long j3, long j4, long j5);

    private native void nSetSupportedRefreshPeriods(long j2, long[] jArr, int[] iArr);

    private void updateSupportedRefreshRates(Display display) {
        Display.Mode[] supportedModes = display.getSupportedModes();
        int i2 = 0;
        for (Display.Mode mode : supportedModes) {
            if (modeMatchesCurrentResolution(mode)) {
                i2++;
            }
        }
        long[] jArr = new long[i2];
        int[] iArr = new int[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < supportedModes.length; i4++) {
            if (modeMatchesCurrentResolution(supportedModes[i4])) {
                jArr[i3] = 1.0E9f / supportedModes[i4].getRefreshRate();
                iArr[i3] = supportedModes[i4].getModeId();
                i3++;
            }
        }
        nSetSupportedRefreshPeriods(this.mCookie, jArr, iArr);
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int i2) {
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int i2) {
        synchronized (this) {
            Display defaultDisplay = this.mWindowManager.getDefaultDisplay();
            float refreshRate = defaultDisplay.getRefreshRate();
            Display.Mode mode = defaultDisplay.getMode();
            boolean z = true;
            boolean z2 = (mode.getPhysicalWidth() != this.mCurrentMode.getPhysicalWidth()) | (mode.getPhysicalHeight() != this.mCurrentMode.getPhysicalHeight());
            if (refreshRate == this.mCurrentMode.getRefreshRate()) {
                z = false;
            }
            this.mCurrentMode = mode;
            if (z2) {
                updateSupportedRefreshRates(defaultDisplay);
            }
            if (z) {
                long j2 = 1.0E9f / refreshRate;
                nOnRefreshPeriodChanged(this.mCookie, j2, defaultDisplay.getAppVsyncOffsetNanos(), j2 - (this.mWindowManager.getDefaultDisplay().getPresentationDeadlineNanos() - 1000000));
            }
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int i2) {
    }

    public void setPreferredDisplayModeId(int i2) {
        this.mActivity.runOnUiThread(new c(this, i2));
    }

    public void terminate() {
        this.mLooper.f16891a.getLooper().quit();
    }
}
