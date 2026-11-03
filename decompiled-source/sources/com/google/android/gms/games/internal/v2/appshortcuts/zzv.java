package com.google.android.gms.games.internal.v2.appshortcuts;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzv extends com.google.android.gms.internal.games_v2.zza implements IInterface {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.games.internal.v2.appshortcuts.IAppShortcutsService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzd(zzx zzxVar, zzr zzrVar, List list, List list2) throws RemoteException {
        Parcel zza = zza();
        int i2 = com.google.android.gms.internal.games_v2.zzc.zza;
        zza.writeStrongBinder(zzxVar);
        com.google.android.gms.internal.games_v2.zzc.zzc(zza, zzrVar);
        zza.writeTypedList(list);
        zza.writeTypedList(list2);
        zzc(3, zza);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zze(zzx zzxVar, zzr zzrVar, zzi zziVar) throws RemoteException {
        Parcel zza = zza();
        int i2 = com.google.android.gms.internal.games_v2.zzc.zza;
        zza.writeStrongBinder(zzxVar);
        com.google.android.gms.internal.games_v2.zzc.zzc(zza, zzrVar);
        com.google.android.gms.internal.games_v2.zzc.zzc(zza, zziVar);
        zzc(4, zza);
    }
}
