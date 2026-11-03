package com.google.android.gms.internal.p000authapiphone;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.google.android.gms:play-services-auth-api-phone@@17.5.1 */
/* renamed from: com.google.android.gms.internal.auth-api-phone.zzb  reason: invalid package */
/* loaded from: classes5.dex */
public class zzb extends Binder implements IInterface {
    /* JADX INFO: Access modifiers changed from: protected */
    public zzb(String str) {
        attachInterface(this, str);
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 > 16777215) {
            if (super.onTransact(i2, parcel, parcel2, i3)) {
                return true;
            }
        } else {
            parcel.enforceInterface(getInterfaceDescriptor());
        }
        return zza(i2, parcel, parcel2, i3);
    }

    protected boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        throw null;
    }
}
