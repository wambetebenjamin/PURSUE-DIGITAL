package com.google.android.gms.internal.play_billing;

import sun.misc.Unsafe;
/* compiled from: D8$$SyntheticClass */
/* loaded from: classes5.dex */
public final /* synthetic */ class zzcp$$ExternalSyntheticBackportWithForwarding0 {
    public static /* synthetic */ boolean m(Unsafe unsafe, Object obj, long j2, Object obj2, Object obj3) {
        while (!unsafe.compareAndSwapObject(obj, j2, obj2, obj3)) {
            if (unsafe.getObject(obj, j2) != obj2) {
                return false;
            }
        }
        return true;
    }
}
