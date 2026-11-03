package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.annotations.JNINamespace;
@JNINamespace("base::android")
/* loaded from: classes9.dex */
public abstract class CpuFeatures {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        int getCoreCount();

        long getCpuFeatures();
    }

    public static int getCount() {
        return CpuFeaturesJni.get().getCoreCount();
    }

    public static long getMask() {
        return CpuFeaturesJni.get().getCpuFeatures();
    }
}
