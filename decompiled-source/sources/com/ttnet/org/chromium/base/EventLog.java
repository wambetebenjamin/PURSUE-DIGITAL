package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.annotations.JNINamespace;
@JNINamespace("base::android")
/* loaded from: classes9.dex */
public class EventLog {
    public static void writeEvent(int i2, int i3) {
        android.util.EventLog.writeEvent(i2, i3);
    }
}
