package com.bytedance.crash.npth_repair.nativeCrash;

import com.bytedance.crash.npth_repair.NpthRepair;
/* loaded from: classes2.dex */
public class MTKBufferOpt {

    /* renamed from: a  reason: collision with root package name */
    protected static boolean f6949a = false;

    /* renamed from: b  reason: collision with root package name */
    protected static int f6950b = 0;

    /* renamed from: c  reason: collision with root package name */
    private static final String f6951c = "RenderD128Opt";

    private static native int nOptMTKBuffer(int i2);

    public static synchronized int a(int i2) {
        synchronized (MTKBufferOpt.class) {
            if (NpthRepair.a()) {
                if (f6949a) {
                    return -2;
                }
                f6949a = true;
                f6950b = nOptMTKBuffer(i2);
                return f6950b;
            }
            return -1;
        }
    }
}
