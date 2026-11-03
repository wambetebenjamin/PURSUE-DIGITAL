package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.location.LocationSettingsResult;
/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
/* loaded from: classes5.dex */
public abstract class zzan extends zzb implements zzao {
    public zzan() {
        super("com.google.android.gms.location.internal.ISettingsCallbacks");
    }

    @Override // com.google.android.gms.internal.location.zzb
    protected final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 == 1) {
            zzb((LocationSettingsResult) zzc.zzb(parcel, LocationSettingsResult.CREATOR));
            return true;
        }
        return false;
    }
}
