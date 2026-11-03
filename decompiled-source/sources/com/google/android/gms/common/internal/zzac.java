package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public abstract class zzac extends com.google.android.gms.internal.common.zzb implements zzad {
    public static zzad zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi");
        return queryLocalInterface instanceof zzad ? (zzad) queryLocalInterface : new zzab(iBinder);
    }
}
