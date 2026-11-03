package com.google.android.gms.internal.games_v2;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzw extends zza implements IInterface {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.games.internal.connect.IGamesConnectService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzd(zzv zzvVar, zzq zzqVar) throws RemoteException {
        Parcel zza = zza();
        int i2 = zzc.zza;
        zza.writeStrongBinder(zzvVar);
        zzc.zzc(zza, zzqVar);
        zzc(2, zza);
    }
}
