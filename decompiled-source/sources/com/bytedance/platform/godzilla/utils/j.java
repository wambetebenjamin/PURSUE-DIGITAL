package com.bytedance.platform.godzilla.utils;

import android.os.Build;
import android.text.TextUtils;
import java.util.Locale;
/* compiled from: RomUtils.java */
/* loaded from: classes2.dex */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    private static final CharSequence f11100a = "amigo";

    private j() {
    }

    public static boolean isColorOS() {
        String str = Build.MANUFACTURER;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.toLowerCase(Locale.getDefault()).contains("oppo");
    }

    public static boolean isAmigo() {
        return !TextUtils.isEmpty(Build.DISPLAY) && Build.DISPLAY.toLowerCase(Locale.getDefault()).contains(f11100a);
    }
}
