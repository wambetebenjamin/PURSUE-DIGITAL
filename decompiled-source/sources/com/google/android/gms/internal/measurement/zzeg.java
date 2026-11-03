package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdy;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzeg extends zzdy.zza {
    private final /* synthetic */ Boolean zzc;
    private final /* synthetic */ zzdy zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzeg(zzdy zzdyVar, Boolean bool) {
        super(zzdyVar);
        this.zzc = bool;
        this.zzd = zzdyVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdy.zza
    final void zza() throws RemoteException {
        zzdj zzdjVar;
        zzdj zzdjVar2;
        if (this.zzc != null) {
            zzdjVar2 = this.zzd.zzj;
            ((zzdj) Preconditions.checkNotNull(zzdjVar2)).setMeasurementEnabled(this.zzc.booleanValue(), this.zza);
            return;
        }
        zzdjVar = this.zzd.zzj;
        ((zzdj) Preconditions.checkNotNull(zzdjVar)).clearMeasurementEnabled(this.zza);
    }
}
