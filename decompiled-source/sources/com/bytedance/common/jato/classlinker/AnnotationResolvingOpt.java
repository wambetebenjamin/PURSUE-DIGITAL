package com.bytedance.common.jato.classlinker;

import android.os.Build;
import android.util.Log;
import com.bytedance.common.jato.d;
/* loaded from: classes.dex */
public class AnnotationResolvingOpt {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4644a = "AnnotationResolvingOpt";

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f4645b = false;

    private static native boolean nativeOptimize();

    public static boolean a() {
        if (!f4645b && Build.VERSION.SDK_INT >= 33 && Build.VERSION.SDK_INT <= 34) {
            d.loadLibrary();
            try {
                f4645b = true;
                return nativeOptimize();
            } catch (NoSuchMethodError e2) {
                Log.e(f4644a, "NoSuchMethodError", e2);
            } catch (UnsatisfiedLinkError e3) {
                Log.e(f4644a, "UnsatisfiedLinkError", e3);
            }
        }
        return false;
    }
}
