package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzca extends zzbu implements zzbz {
    @Override // com.google.android.gms.internal.measurement.zzbz
    public final Bundle zza(Bundle bundle) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, bundle);
        Parcel zza = zza(1, a_);
        Bundle bundle2 = (Bundle) zzbw.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzca(IBinder iBinder) {
        super(iBinder, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
    }
}
