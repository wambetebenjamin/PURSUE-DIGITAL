package com.google.android.gms.internal.play_billing;

import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public abstract class zzaz extends zzat implements zzba {
    public zzaz() {
        super("com.google.android.apps.play.billingtestcompanion.aidl.IBillingOverrideServiceCallback");
    }

    @Override // com.google.android.gms.internal.play_billing.zzat
    protected final boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 == 1) {
            int readInt = parcel.readInt();
            enforceNoDataAvail(parcel);
            zza(readInt);
            return true;
        }
        return false;
    }
}
