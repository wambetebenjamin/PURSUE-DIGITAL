package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public abstract class zzw extends com.google.android.gms.internal.common.zzb implements zzx {
    public zzw() {
        super("com.google.android.gms.common.internal.ICertData");
    }

    public static zzx zzg(IBinder iBinder) {
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ICertData");
        return queryLocalInterface instanceof zzx ? (zzx) queryLocalInterface : new zzv(iBinder);
    }

    @Override // com.google.android.gms.internal.common.zzb
    protected final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 == 1) {
            IObjectWrapper zzd = zzd();
            parcel2.writeNoException();
            com.google.android.gms.internal.common.zzc.zze(parcel2, zzd);
        } else if (i2 != 2) {
            return false;
        } else {
            int zze = zze();
            parcel2.writeNoException();
            parcel2.writeInt(zze);
        }
        return true;
    }
}
