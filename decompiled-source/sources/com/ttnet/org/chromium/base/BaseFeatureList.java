package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.MainDex;
@JNINamespace("base::android")
@MainDex
/* loaded from: classes9.dex */
public final class BaseFeatureList {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        boolean isEnabled(String str);
    }

    private BaseFeatureList() {
    }

    public static boolean isEnabled(String str) {
        return BaseFeatureListJni.get().isEnabled(str);
    }
}
