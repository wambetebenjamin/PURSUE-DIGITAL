package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.annotations.JNINamespace;
@JNINamespace("base::android")
/* loaded from: classes9.dex */
public abstract class PathService {
    public static final int DIR_MODULE = 3;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        void override(int i2, String str);
    }

    private PathService() {
    }

    public static void override(int i2, String str) {
        PathServiceJni.get().override(i2, str);
    }
}
