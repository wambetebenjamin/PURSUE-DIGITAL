package com.google.android.gms.internal.play_billing;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public abstract class zzax extends zzat implements zzay {
    public static zzay zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.apps.play.billingtestcompanion.aidl.IBillingOverrideService");
        return queryLocalInterface instanceof zzay ? (zzay) queryLocalInterface : new zzaw(iBinder);
    }

    @Override // com.google.android.gms.internal.play_billing.zzat
    protected final boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        throw null;
    }
}
