package com.ttnet.org.chromium.base.metrics;

import com.ttnet.org.chromium.base.annotations.JNINamespace;
@JNINamespace("base::android")
/* loaded from: classes9.dex */
public final class StatisticsRecorderAndroid {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        String toJson(int i2);
    }

    private StatisticsRecorderAndroid() {
    }

    public static String toJson(int i2) {
        return StatisticsRecorderAndroidJni.get().toJson(i2);
    }
}
