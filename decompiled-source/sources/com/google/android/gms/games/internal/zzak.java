package com.google.android.gms.games.internal;

import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public abstract class zzak extends com.google.android.gms.internal.games_v2.zzb implements zzal {
    public zzak() {
        super("com.google.android.gms.games.internal.IGamesClient");
    }

    @Override // com.google.android.gms.internal.games_v2.zzb
    protected final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 == 1001) {
            com.google.android.gms.internal.games_v2.zzaf zzb = zzb();
            parcel2.writeNoException();
            int i4 = com.google.android.gms.internal.games_v2.zzc.zza;
            parcel2.writeInt(1);
            zzb.writeToParcel(parcel2, 1);
            return true;
        }
        return false;
    }
}
