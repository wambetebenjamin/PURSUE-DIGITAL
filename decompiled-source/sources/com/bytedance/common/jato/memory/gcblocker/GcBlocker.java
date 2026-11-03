package com.bytedance.common.jato.memory.gcblocker;

import android.os.Build;
import android.util.Log;
import java.util.HashMap;
/* loaded from: classes2.dex */
public final class GcBlocker extends a {

    /* renamed from: a  reason: collision with root package name */
    private static final long f4946a = 0;

    /* renamed from: b  reason: collision with root package name */
    private static final long f4947b = 5000;

    /* renamed from: c  reason: collision with root package name */
    private static long f4948c = 209715200;

    /* renamed from: d  reason: collision with root package name */
    private static long f4949d = 536870912;

    /* renamed from: e  reason: collision with root package name */
    private final HashMap<String, Integer> f4950e = new HashMap<>();

    private static native int nativeCreateToken(boolean z);

    private static native void nativeReleaseToken(int i2);

    private static native void nativeRequestBlockGc(long j2);

    private static native void nativeSetHeapLimit(boolean z, int i2);

    private static native void nativeSetIsSupportApex(boolean z);

    private static native void nativeSetMaxGcBlockTime(int i2);

    private static native int nativeStartBlockGc(int i2);

    private static native void nativeStopBlockGc(int i2);

    public GcBlocker(int i2, boolean z, int i3, boolean z2) {
        nativeSetIsSupportApex(z2);
        nativeSetMaxGcBlockTime(i2);
        if (i3 > 0) {
            nativeSetHeapLimit(z, i3);
        }
    }

    @Override // com.bytedance.common.jato.memory.gcblocker.a
    public void a(long j2) {
        if (!a() || j2 <= 0 || j2 > 5000) {
            return;
        }
        nativeRequestBlockGc(j2);
    }

    @Override // com.bytedance.common.jato.memory.gcblocker.a
    public void a(String str) {
        if (a()) {
            synchronized (this.f4950e) {
                Integer num = this.f4950e.get(str);
                if (num == null) {
                    num = Integer.valueOf(nativeCreateToken(false));
                    this.f4950e.put(str, num);
                }
                if (num != null && num.intValue() >= 0) {
                    nativeStartBlockGc(num.intValue());
                }
            }
        }
    }

    @Override // com.bytedance.common.jato.memory.gcblocker.a
    public void b(String str) {
        synchronized (this.f4950e) {
            Integer remove = this.f4950e.remove(str);
            if (remove != null && remove.intValue() >= 0) {
                nativeStopBlockGc(remove.intValue());
                nativeReleaseToken(remove.intValue());
            }
        }
    }

    @Override // com.bytedance.common.jato.memory.gcblocker.a
    public void a(int i2) {
        nativeSetMaxGcBlockTime(i2);
    }

    @Override // com.bytedance.common.jato.memory.gcblocker.a
    public void a(boolean z, int i2) {
        nativeSetHeapLimit(z, i2);
    }

    private static boolean a() {
        if (f4948c <= 0) {
            return true;
        }
        long j2 = Runtime.getRuntime().totalMemory();
        if (Build.VERSION.SDK_INT < 29 || j2 < f4949d) {
            boolean z = j2 < f4948c;
            if (!z) {
                Log.e(b.f4951a, "total memory over heap size limit: " + j2);
            }
            return z;
        }
        return true;
    }

    public static void b(long j2) {
        f4948c = j2;
    }
}
