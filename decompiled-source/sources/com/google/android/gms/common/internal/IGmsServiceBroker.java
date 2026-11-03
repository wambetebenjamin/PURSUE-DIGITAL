package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public interface IGmsServiceBroker extends IInterface {

    /* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IGmsServiceBroker {
        public Stub() {
            attachInterface(this, "com.google.android.gms.common.internal.IGmsServiceBroker");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
            IGmsCallbacks zzyVar;
            if (i2 <= 0 || i2 > 16777215) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder == null) {
                zzyVar = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                zzyVar = queryLocalInterface instanceof IGmsCallbacks ? (IGmsCallbacks) queryLocalInterface : new zzy(readStrongBinder);
            }
            if (i2 == 46) {
                getService(zzyVar, parcel.readInt() != 0 ? GetServiceRequest.CREATOR.createFromParcel(parcel) : null);
                Preconditions.checkNotNull(parcel2);
                parcel2.writeNoException();
                return true;
            } else if (i2 == 47) {
                if (parcel.readInt() != 0) {
                    zzai.CREATOR.createFromParcel(parcel);
                }
                throw new UnsupportedOperationException();
            } else {
                parcel.readInt();
                if (i2 != 4) {
                    parcel.readString();
                    if (i2 != 1) {
                        if (i2 != 2 && i2 != 23 && i2 != 25 && i2 != 27) {
                            if (i2 != 30) {
                                if (i2 == 34) {
                                    parcel.readString();
                                } else if (i2 != 41 && i2 != 43 && i2 != 37 && i2 != 38) {
                                    switch (i2) {
                                        case 9:
                                            parcel.readString();
                                            parcel.createStringArray();
                                            parcel.readString();
                                            parcel.readStrongBinder();
                                            parcel.readString();
                                            if (parcel.readInt() != 0) {
                                                Bundle bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                                                break;
                                            }
                                            break;
                                        case 10:
                                            parcel.readString();
                                            parcel.createStringArray();
                                            break;
                                        case 19:
                                            parcel.readStrongBinder();
                                            if (parcel.readInt() != 0) {
                                                Bundle bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                                                break;
                                            }
                                            break;
                                    }
                                }
                            }
                            parcel.createStringArray();
                            parcel.readString();
                            if (parcel.readInt() != 0) {
                                Bundle bundle3 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                            }
                        }
                        if (parcel.readInt() != 0) {
                            Bundle bundle4 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                    } else {
                        parcel.readString();
                        parcel.createStringArray();
                        parcel.readString();
                        if (parcel.readInt() != 0) {
                            Bundle bundle5 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                    }
                }
                throw new UnsupportedOperationException();
            }
        }
    }

    void getService(IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest) throws RemoteException;
}
