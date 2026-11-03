package com.google.android.finsky.externalreferrer;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.a;
import com.google.android.a.b;
import com.google.android.a.c;
/* loaded from: classes4.dex */
public interface IGetInstallReferrerService extends IInterface {

    /* loaded from: classes4.dex */
    public static abstract class Stub extends b implements IGetInstallReferrerService {

        /* loaded from: classes4.dex */
        public static class Proxy extends a implements IGetInstallReferrerService {
            Proxy(IBinder iBinder) {
                super(iBinder);
            }

            @Override // com.google.android.finsky.externalreferrer.IGetInstallReferrerService
            public final Bundle c(Bundle bundle) throws RemoteException {
                Parcel a2 = a();
                c.b(a2, bundle);
                Parcel b2 = b(a2);
                Bundle bundle2 = (Bundle) c.a(b2, Bundle.CREATOR);
                b2.recycle();
                return bundle2;
            }
        }

        public static IGetInstallReferrerService b(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
            if (queryLocalInterface instanceof IGetInstallReferrerService) {
                return (IGetInstallReferrerService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // com.google.android.a.b
        protected final boolean a(int i2, Parcel parcel, Parcel parcel2) throws RemoteException {
            if (i2 == 1) {
                Bundle c2 = c((Bundle) c.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                c.c(parcel2, c2);
                return true;
            }
            return false;
        }
    }

    Bundle c(Bundle bundle) throws RemoteException;
}
