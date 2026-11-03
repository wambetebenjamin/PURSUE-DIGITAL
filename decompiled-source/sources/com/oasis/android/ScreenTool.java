package com.oasis.android;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
/* loaded from: classes9.dex */
public final class ScreenTool {

    /* loaded from: classes9.dex */
    class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Activity f17421a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ float f17422b;

        a(Activity activity, float f2) {
            this.f17421a = activity;
            this.f17422b = f2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Window window = this.f17421a.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.screenBrightness = this.f17422b;
            window.setAttributes(attributes);
        }
    }

    public static float getScreenBrightness() {
        return ActivityManager.getActivity().getWindow().getAttributes().screenBrightness;
    }

    public static void setScreenBrightness(float f2) {
        Activity activity = ActivityManager.getActivity();
        activity.runOnUiThread(new a(activity, f2));
    }
}
