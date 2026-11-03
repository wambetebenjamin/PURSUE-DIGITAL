package com.bytedance.common.jato.boost.sched;

import com.bytedance.common.jato.d;
/* loaded from: classes.dex */
public class ThreadPrioProtector {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4638a = -1000;

    /* renamed from: b  reason: collision with root package name */
    public static final int f4639b = -1001;

    public static native void addThreadPrioProtect(int i2, int i3, int i4);

    public static native void nativeInit();

    public static native void setPrioProtectEnabled(boolean z);

    static {
        d.loadLibrary();
    }
}
