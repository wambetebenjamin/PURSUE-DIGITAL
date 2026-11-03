package com.google.android.gms.internal.play_billing;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzaw extends zzas implements zzay {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaw(IBinder iBinder) {
        super(iBinder, "com.google.android.apps.play.billingtestcompanion.aidl.IBillingOverrideService");
    }

    @Override // com.google.android.gms.internal.play_billing.zzay
    public final void zza(String str, String str2, zzba zzbaVar) throws RemoteException {
        Parcel zzt = zzt();
        zzt.writeString(str);
        zzt.writeString(str2);
        zzau.zzc(zzt, zzbaVar);
        zzw(1, zzt);
    }
}
