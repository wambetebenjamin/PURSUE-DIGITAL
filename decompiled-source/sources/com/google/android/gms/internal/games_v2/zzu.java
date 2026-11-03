package com.google.android.gms.internal.games_v2;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public abstract class zzu extends zzb implements zzv {
    public zzu() {
        super("com.google.android.gms.games.internal.connect.IGamesConnectCallbacks");
    }

    @Override // com.google.android.gms.internal.games_v2.zzb
    protected final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 == 2) {
            zzc.zze(parcel);
            zzb((Status) zzc.zzb(parcel, Status.CREATOR), (zzs) zzc.zzb(parcel, zzs.CREATOR));
            return true;
        }
        return false;
    }
}
