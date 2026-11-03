package com.google.android.gms.games.internal.v2.appshortcuts;

import android.content.Intent;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public abstract class zzw extends com.google.android.gms.internal.games_v2.zzb implements zzx {
    public zzw() {
        super("com.google.android.gms.games.internal.v2.appshortcuts.IAppShortcutsServiceCallback");
    }

    @Override // com.google.android.gms.internal.games_v2.zzb
    protected final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 == 1) {
            com.google.android.gms.internal.games_v2.zzc.zze(parcel);
            zzd((Status) com.google.android.gms.internal.games_v2.zzc.zzb(parcel, Status.CREATOR));
        } else if (i2 == 2) {
            com.google.android.gms.internal.games_v2.zzc.zze(parcel);
            zzb((zzg) com.google.android.gms.internal.games_v2.zzc.zzb(parcel, zzg.CREATOR));
        } else if (i2 != 3) {
            return false;
        } else {
            com.google.android.gms.internal.games_v2.zzc.zze(parcel);
            zzc((Intent) com.google.android.gms.internal.games_v2.zzc.zzb(parcel, Intent.CREATOR));
        }
        return true;
    }
}
