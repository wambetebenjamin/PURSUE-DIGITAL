package com.bytedance.common.jato.shrinker;

import com.bytedance.common.jato.d;
/* loaded from: classes2.dex */
class ShrinkerNativeHolder {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f5012a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int shrinkHeapNative(int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int shrinkMallocNative(int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int shrinkRegionNative(int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int shrinkWebviewNative();

    ShrinkerNativeHolder() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized boolean a() {
        boolean z;
        synchronized (ShrinkerNativeHolder.class) {
            if (!f5012a && d.loadLibrary()) {
                f5012a = true;
            }
            z = f5012a;
        }
        return z;
    }
}
