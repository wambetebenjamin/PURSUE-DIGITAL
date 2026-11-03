package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdy;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzeh extends zzdy.zza {
    private final /* synthetic */ zzdy zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzeh(zzdy zzdyVar) {
        super(zzdyVar);
        this.zzc = zzdyVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdy.zza
    final void zza() throws RemoteException {
        zzdj zzdjVar;
        zzdjVar = this.zzc.zzj;
        ((zzdj) Preconditions.checkNotNull(zzdjVar)).resetAnalyticsData(this.zza);
    }
}
