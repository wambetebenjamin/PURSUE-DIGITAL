package com.google.android.gms.internal.games_v2;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzaj extends zza implements zzal {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.games.internal.recall.IRecallService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.games_v2.zzal
    public final void zzd(zzai zzaiVar, String str) throws RemoteException {
        Parcel zza = zza();
        int i2 = zzc.zza;
        zza.writeStrongBinder(zzaiVar);
        zza.writeString("unusedServerClientId");
        zzc(2, zza);
    }
}
