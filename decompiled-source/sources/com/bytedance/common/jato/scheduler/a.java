package com.bytedance.common.jato.scheduler;

import android.content.Context;
import android.os.Build;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: JatoScheduler.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static a f5009a;

    /* renamed from: b  reason: collision with root package name */
    private AtomicBoolean f5010b = new AtomicBoolean(false);

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (f5009a == null) {
                f5009a = new a();
            }
            aVar = f5009a;
        }
        return aVar;
    }

    public void init(Context context) {
        if (Build.VERSION.SDK_INT > 22) {
            init(context, 0);
        }
    }

    public void init(Context context, int i2) {
        if (this.f5010b.compareAndSet(false, true) && Build.VERSION.SDK_INT > 22 && SchedulerNativeHolder.a()) {
            int i3 = context.getApplicationInfo().targetSdkVersion;
            compatible.standard.a.a();
            if (com.bytedance.common.jato.util.b.isEmulator() || com.bytedance.common.jato.util.b.isEmulatorTrans2Arm()) {
                i2 |= 1088;
            }
            SchedulerNativeHolder.nativeInit(i3, i2, context);
        }
    }
}
