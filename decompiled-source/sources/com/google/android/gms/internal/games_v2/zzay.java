package com.google.android.gms.internal.games_v2;

import android.app.Application;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzay {
    private static final AtomicReference zza = new AtomicReference();

    public static zzaw zza(Application application) {
        AtomicReference atomicReference = zza;
        zzaw zzawVar = (zzaw) atomicReference.get();
        if (zzawVar != null) {
            return zzawVar;
        }
        zzh zza2 = zzi.zza();
        zza2.zza(9);
        zza2.zzb(application.getPackageName());
        zzi zzc = zza2.zzc();
        com.google.android.gms.games.internal.zzf zza3 = com.google.android.gms.games.internal.zzf.zza(application);
        atomicReference.compareAndSet(null, new zzbq(application, zza3, com.google.android.gms.games.internal.v2.resolution.zzb.zza(), new zzbu(application, zza3, new zzax(application, zzc))));
        return (zzaw) Preconditions.checkNotNull((zzaw) atomicReference.get());
    }
}
