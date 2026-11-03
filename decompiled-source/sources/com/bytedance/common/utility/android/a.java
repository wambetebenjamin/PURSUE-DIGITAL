package com.bytedance.common.utility.android;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.Settings;
import android.view.WindowManager;
/* compiled from: BrightnessUtil.java */
/* loaded from: classes2.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static final float f5182a = 0.003921569f;

    private a() {
    }

    public static boolean isAutoBrightness(Activity activity) {
        if (activity == null) {
            return false;
        }
        try {
            return Settings.System.getInt(activity.getContentResolver(), "screen_brightness_mode") == 1;
        } catch (Settings.SettingNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static float getScreenBrightness(Activity activity) {
        if (activity == null) {
            return 0.0f;
        }
        try {
            return activity.getWindow().getAttributes().screenBrightness;
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    public static void setScreenBrightness(Activity activity, int i2) {
        if (activity == null) {
            return;
        }
        try {
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            attributes.screenBrightness = Float.valueOf(i2).floatValue() * f5182a;
            activity.getWindow().setAttributes(attributes);
        } catch (Exception unused) {
        }
    }

    public static int getBrightness(Activity activity) {
        if (activity == null) {
            return 0;
        }
        try {
            return Settings.System.getInt(activity.getContentResolver(), "screen_brightness");
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static void stopAutoBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(), "screen_brightness_mode", 0);
    }

    public static void startAutoBrightness(Activity activity) {
        if (activity == null) {
            return;
        }
        Settings.System.putInt(activity.getContentResolver(), "screen_brightness_mode", 1);
    }

    public static void saveBrightness(Activity activity, int i2) {
        if (activity == null) {
            return;
        }
        Uri uriFor = Settings.System.getUriFor("screen_brightness");
        ContentResolver contentResolver = activity.getContentResolver();
        Settings.System.putInt(contentResolver, "screen_brightness", i2);
        contentResolver.notifyChange(uriFor, null);
    }
}
