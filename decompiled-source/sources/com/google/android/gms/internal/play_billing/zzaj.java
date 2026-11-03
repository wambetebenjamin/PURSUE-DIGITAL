package com.google.android.gms.internal.play_billing;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public abstract class zzaj extends zzat implements zzak {
    public zzaj() {
        super("com.android.vending.billing.IInAppBillingIsAlternativeBillingOnlyAvailableCallback");
    }

    @Override // com.google.android.gms.internal.play_billing.zzat
    protected final boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 == 1) {
            enforceNoDataAvail(parcel);
            zza((Bundle) zzau.zza(parcel, Bundle.CREATOR));
            return true;
        }
        return false;
    }
}
