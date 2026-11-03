package com.google.android.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: BaseStub.java */
/* loaded from: classes2.dex */
public class b extends Binder implements IInterface {
    /* JADX INFO: Access modifiers changed from: protected */
    public b() {
        attachInterface(this, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
    }

    protected boolean a(int i2, Parcel parcel, Parcel parcel2) throws RemoteException {
        throw null;
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
        return a(i2, parcel, parcel2);
    }
}
