package com.bytedance.mira.plugin.hook.flipped;

import android.os.Build;
import com.bytedance.mira.plugin.hook.flipped.compat.FlippedV2Impl;
import com.bytedance.mira.plugin.hook.flipped.compat.c;
/* compiled from: Flipped.java */
/* loaded from: classes2.dex */
public class b {
    public static void a() {
        b().a();
    }

    private static c b() {
        if (c()) {
            return new FlippedV2Impl();
        }
        if (d()) {
            return new com.bytedance.mira.plugin.hook.flipped.compat.b();
        }
        return new com.bytedance.mira.plugin.hook.flipped.compat.a();
    }

    private static boolean c() {
        return Build.VERSION.SDK_INT >= 30 || (Build.VERSION.SDK_INT == 29 && Build.VERSION.PREVIEW_SDK_INT > 0);
    }

    private static boolean d() {
        return Build.VERSION.SDK_INT >= 28 || (Build.VERSION.SDK_INT == 27 && Build.VERSION.PREVIEW_SDK_INT > 0);
    }
}
