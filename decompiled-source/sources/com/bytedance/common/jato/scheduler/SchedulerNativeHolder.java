package com.bytedance.common.jato.scheduler;

import android.content.Context;
import com.bytedance.common.jato.d;
/* loaded from: classes2.dex */
class SchedulerNativeHolder {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f5008a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int nativeInit(int i2, int i3, Context context);

    SchedulerNativeHolder() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized boolean a() {
        boolean z;
        synchronized (SchedulerNativeHolder.class) {
            if (!f5008a && d.loadLibrary()) {
                f5008a = true;
            }
            z = f5008a;
        }
        return z;
    }
}
