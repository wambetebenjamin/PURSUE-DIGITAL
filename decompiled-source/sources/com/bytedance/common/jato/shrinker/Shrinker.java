package com.bytedance.common.jato.shrinker;

import android.os.Build;
/* loaded from: classes2.dex */
public class Shrinker {
    public static final int DEFAULT_DESIRABLE_LOWEST_HEAP_SIZE_POST_O = 2048;
    public static final int DEFAULT_DESIRABLE_LOWEST_HEAP_SIZE_PRE_O = 512;
    private static Shrinker sInstance;

    public static synchronized Shrinker getInstance() {
        Shrinker shrinker;
        synchronized (Shrinker.class) {
            if (sInstance == null) {
                sInstance = new Shrinker();
            }
            shrinker = sInstance;
        }
        return shrinker;
    }

    @Deprecated
    public int doShrink() {
        return doShrink(512, 2048);
    }

    @Deprecated
    public int doShrinkRegion(int i2) {
        if (Build.VERSION.SDK_INT < 26 || !ShrinkerNativeHolder.a()) {
            return 0;
        }
        return ShrinkerNativeHolder.shrinkRegionNative(i2);
    }

    @Deprecated
    public int doShrink(int i2) {
        return doShrink(i2, 2048);
    }

    public int doShrink(int i2, int i3) {
        if (Build.VERSION.SDK_INT > 23 && Build.VERSION.SDK_INT <= 29 && ShrinkerNativeHolder.a()) {
            ShrinkerNativeHolder.shrinkMallocNative(2, 16);
        }
        if (Build.VERSION.SDK_INT <= 19 || !ShrinkerNativeHolder.a()) {
            return -1;
        }
        if (Build.VERSION.SDK_INT < 26) {
            return ShrinkerNativeHolder.shrinkHeapNative(i2);
        }
        return ShrinkerNativeHolder.shrinkRegionNative(i3);
    }

    @Deprecated
    public int shrinkWebviewNative() {
        return ShrinkerNativeHolder.shrinkWebviewNative();
    }
}
