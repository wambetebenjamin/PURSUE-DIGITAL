package com.google.android.gms.internal.games_v2;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public abstract class zzah extends zzb implements zzai {
    public zzah() {
        super("com.google.android.gms.games.internal.recall.IRecallCallbacks");
    }

    @Override // com.google.android.gms.internal.games_v2.zzb
    protected final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 == 2) {
            zzc.zze(parcel);
            zzb((zzam) zzc.zzb(parcel, zzam.CREATOR));
            return true;
        } else if (i2 != 3) {
            return false;
        } else {
            zzc.zze(parcel);
            zzc((Status) zzc.zzb(parcel, Status.CREATOR));
            return true;
        }
    }
}
