package com.bytedance.common.jato.soload;

import android.os.Build;
import com.bytedance.common.jato.d;
/* loaded from: classes2.dex */
public class DlopenPriorityManager {
    private static native void nGuardDlopenThreadPriority(int i2, boolean z);

    public static void a(int i2, boolean z) {
        if (Build.VERSION.SDK_INT >= 23 && d.loadLibrary()) {
            try {
                nGuardDlopenThreadPriority(i2, z);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
