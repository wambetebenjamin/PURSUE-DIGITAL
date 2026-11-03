package com.oasis.android;

import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import com.oasis.Logger.Logger;
/* loaded from: classes9.dex */
public final class VibratorManager {

    /* renamed from: a  reason: collision with root package name */
    private static Vibrator f17456a;

    private static Vibrator a() {
        if (f17456a == null) {
            f17456a = (Vibrator) ActivityManager.getActivity().getSystemService("vibrator");
            Logger.i("VibratorManager", "API level:" + Build.VERSION.SDK_INT);
        }
        return f17456a;
    }

    private static boolean b() {
        if (Build.VERSION.SDK_INT >= 26) {
            return a().hasAmplitudeControl();
        }
        return false;
    }

    public static void cancel() {
        a().cancel();
    }

    public static boolean hasVibrator() {
        return a().hasVibrator();
    }

    public static void vibrateOneShot(long j2) {
        a().vibrate(j2);
    }

    public static void vibrateOneShot(long j2, int i2) {
        if (Build.VERSION.SDK_INT < 26) {
            a().vibrate(j2);
            return;
        }
        a().vibrate(VibrationEffect.createOneShot(j2, i2));
    }

    public static boolean vibratePredefined(int i2) {
        if (Build.VERSION.SDK_INT >= 29) {
            a().vibrate(VibrationEffect.createPredefined(i2));
            return true;
        }
        return false;
    }

    public static void vibrateWaveform(long[] jArr, int i2) {
        if (i2 >= jArr.length) {
            Logger.e("VibratorManager", "repeatIndex >= timings.length");
        } else if (Build.VERSION.SDK_INT < 26) {
            a().vibrate(jArr, i2);
        } else {
            a().vibrate(VibrationEffect.createWaveform(jArr, i2));
        }
    }

    public static void vibrateWaveform(long[] jArr, int[] iArr, int i2) {
        String str;
        if (i2 >= jArr.length) {
            str = "repeatIndex >= timings.length";
        } else if (jArr.length == iArr.length) {
            if (Build.VERSION.SDK_INT < 26 || !b()) {
                return;
            }
            a().vibrate(VibrationEffect.createWaveform(jArr, iArr, i2));
            return;
        } else {
            str = "timings.length != timings.length";
        }
        Logger.e("VibratorManager", str);
    }
}
