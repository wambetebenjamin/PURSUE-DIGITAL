package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
/* loaded from: classes5.dex */
public abstract class zzaj extends zzb implements zzak {
    public zzaj() {
        super("com.google.android.gms.location.internal.IGeofencerCallbacks");
    }

    @Override // com.google.android.gms.internal.location.zzb
    protected final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 == 1) {
            zzb(parcel.readInt(), parcel.createStringArray());
        } else if (i2 == 2) {
            zzc(parcel.readInt(), parcel.createStringArray());
        } else if (i2 != 3) {
            return false;
        } else {
            zzd(parcel.readInt(), (PendingIntent) zzc.zzb(parcel, PendingIntent.CREATOR));
        }
        return true;
    }
}
