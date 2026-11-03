package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Looper;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzab {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzab(Context context) {
    }

    public static boolean zza() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
