package com.unity3d.player;

import android.app.Activity;
import android.app.Dialog;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
/* renamed from: com.unity3d.player.e  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
abstract class AbstractC0403e {
    public static OnBackInvokedDispatcher a(Object obj) {
        if (obj instanceof Activity) {
            return ((Activity) obj).getOnBackInvokedDispatcher();
        }
        if (obj instanceof Dialog) {
            return ((Dialog) obj).getOnBackInvokedDispatcher();
        }
        throw new IllegalArgumentException("Unsupported context type when getting '" + obj.getClass().getName() + "' OnBackInvokedDispatcher");
    }

    public static void a(OnBackInvokedDispatcher onBackInvokedDispatcher, int i2, OnBackInvokedCallback onBackInvokedCallback) {
        onBackInvokedDispatcher.registerOnBackInvokedCallback(i2, onBackInvokedCallback);
    }

    public static void a(OnBackInvokedDispatcher onBackInvokedDispatcher, OnBackInvokedCallback onBackInvokedCallback) {
        onBackInvokedDispatcher.unregisterOnBackInvokedCallback(onBackInvokedCallback);
    }
}
