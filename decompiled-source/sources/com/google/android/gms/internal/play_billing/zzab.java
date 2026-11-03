package com.google.android.gms.internal.play_billing;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public abstract class zzab extends zzat implements zzac {
    private static final String DESCRIPTOR = "com.android.vending.billing.IInAppBillingDelegateToBackendCallback";
    static final int TRANSACTION_onDelegateToBackendResponse = 1;

    public zzab() {
        super(DESCRIPTOR);
    }

    public static zzac asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
        return queryLocalInterface instanceof zzac ? (zzac) queryLocalInterface : new zzaa(iBinder);
    }

    @Override // com.google.android.gms.internal.play_billing.zzat
    protected boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 == 1) {
            enforceNoDataAvail(parcel);
            onDelegateToBackendResponse((Bundle) zzau.zza(parcel, Bundle.CREATOR));
            return true;
        }
        return false;
    }
}
