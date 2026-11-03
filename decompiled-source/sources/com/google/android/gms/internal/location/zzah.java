package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
/* loaded from: classes5.dex */
public abstract class zzah extends zzb implements zzai {
    public zzah() {
        super("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
    }

    @Override // com.google.android.gms.internal.location.zzb
    protected final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 == 1) {
            zzb((zzaa) zzc.zzb(parcel, zzaa.CREATOR));
        } else if (i2 != 2) {
            return false;
        } else {
            zzc();
        }
        return true;
    }
}
