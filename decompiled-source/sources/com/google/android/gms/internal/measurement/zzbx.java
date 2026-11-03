package com.google.android.gms.internal.measurement;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
public class zzbx extends Binder implements IInterface {
    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    protected boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public zzbx(String str) {
        attachInterface(this, str);
    }

    @Override // android.os.Binder
    public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        boolean z;
        if (i2 > 16777215) {
            z = super.onTransact(i2, parcel, parcel2, i3);
        } else {
            parcel.enforceInterface(getInterfaceDescriptor());
            z = false;
        }
        if (z) {
            return true;
        }
        return zza(i2, parcel, parcel2, i3);
    }
}
