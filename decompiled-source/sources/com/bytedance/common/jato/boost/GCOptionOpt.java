package com.bytedance.common.jato.boost;

import android.os.Build;
import android.util.Log;
/* loaded from: classes.dex */
public class GCOptionOpt {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4538a = "GCOptionOpt";

    /* renamed from: b  reason: collision with root package name */
    private static GCOptionOpt f4539b = null;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f4540c = false;

    public static native int adjustGCOption(int i2, long j2, long j3);

    private static native int adjustGCOptionEnd(int i2, boolean z, boolean z2);

    private static native int adjustGCOptionStart(int i2, boolean z, long j2, boolean z2, long j3);

    private GCOptionOpt() {
    }

    public static GCOptionOpt a() {
        if (f4539b == null) {
            synchronized (GCOptionOpt.class) {
                if (f4539b == null) {
                    f4539b = new GCOptionOpt();
                }
            }
        }
        return f4539b;
    }

    public synchronized void a(long j2, long j3) {
        if (com.bytedance.common.jato.d.loadLibrary() && Build.VERSION.SDK_INT >= 21 && !f4540c) {
            adjustGCOption(Build.VERSION.SDK_INT, j2, j3);
            f4540c = true;
        }
    }

    public synchronized void a(String str, boolean z, long j2, boolean z2, long j3) {
        Log.d(f4538a, "adjustGCOptionStart scene: " + str + ", clearSoftRef: " + z + ", clearSoftRefLimitBytes: " + j2 + ", adjGCType: " + z2 + ", adjGCTypeLimitBytes: " + j3);
        if (com.bytedance.common.jato.d.loadLibrary() && Build.VERSION.SDK_INT >= 21) {
            adjustGCOptionStart(Build.VERSION.SDK_INT, z, j2, z2, j3);
        }
    }

    public synchronized void a(String str, boolean z, boolean z2) {
        Log.d(f4538a, "adjustGCOptionEnd scene: " + str + ", clearSoftRef: " + z + ", adjGCType: " + z2);
        if (com.bytedance.common.jato.d.loadLibrary() && Build.VERSION.SDK_INT >= 21) {
            adjustGCOptionEnd(Build.VERSION.SDK_INT, z, z2);
        }
    }
}
