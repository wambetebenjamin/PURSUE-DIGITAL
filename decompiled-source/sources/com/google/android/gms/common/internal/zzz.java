package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public abstract class zzz extends com.google.android.gms.internal.common.zzb implements IGmsCallbacks {
    public zzz() {
        super("com.google.android.gms.common.internal.IGmsCallbacks");
    }

    @Override // com.google.android.gms.internal.common.zzb
    protected final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 == 1) {
            com.google.android.gms.internal.common.zzc.zzf(parcel);
            onPostInitComplete(parcel.readInt(), parcel.readStrongBinder(), (Bundle) com.google.android.gms.internal.common.zzc.zzb(parcel, Bundle.CREATOR));
        } else if (i2 == 2) {
            com.google.android.gms.internal.common.zzc.zzf(parcel);
            zzb(parcel.readInt(), (Bundle) com.google.android.gms.internal.common.zzc.zzb(parcel, Bundle.CREATOR));
        } else if (i2 != 3) {
            return false;
        } else {
            com.google.android.gms.internal.common.zzc.zzf(parcel);
            zzc(parcel.readInt(), parcel.readStrongBinder(), (zzj) com.google.android.gms.internal.common.zzc.zzb(parcel, zzj.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
