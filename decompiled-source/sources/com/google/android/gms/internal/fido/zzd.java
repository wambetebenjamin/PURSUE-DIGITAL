package com.google.android.gms.internal.fido;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
/* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
/* loaded from: classes5.dex */
public abstract class zzd extends zzb implements zze {
    public zzd() {
        super("com.google.android.gms.fido.fido2.api.IBooleanCallback");
    }

    @Override // com.google.android.gms.internal.fido.zzb
    protected final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 == 1) {
            int i4 = zzc.zza;
            int readInt = parcel.readInt();
            zzc.zzc(parcel);
            zzb(readInt != 0);
        } else if (i2 != 2) {
            return false;
        } else {
            zzc.zzc(parcel);
            zzc((Status) zzc.zza(parcel, Status.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
