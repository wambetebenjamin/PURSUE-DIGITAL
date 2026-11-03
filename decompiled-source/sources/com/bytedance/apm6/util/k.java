package com.bytedance.apm6.util;

import android.os.Handler;
import android.os.Looper;
import java.io.UnsupportedEncodingException;
/* compiled from: Tools.java */
/* loaded from: classes.dex */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    private static Handler f2407a = new Handler(Looper.getMainLooper());

    public static byte[] safeGetBytes(String str) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    return str.getBytes("UTF-8");
                }
            } catch (UnsupportedEncodingException unused) {
                return str.getBytes();
            }
        }
        return null;
    }

    public static void runOnUiThread(Runnable runnable) {
        f2407a.post(runnable);
    }
}
