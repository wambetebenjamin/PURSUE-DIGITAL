package com.google.android.gms.internal.games_v2;

import android.app.Application;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzas {
    private static final AtomicReference zza = new AtomicReference();
    private final zzar zzb;
    private final zzaw zzc;

    zzas(Application application, zzaw zzawVar) {
        this.zzb = new zzar(this, application, null);
        this.zzc = zzawVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzas zza(Application application) {
        AtomicReference atomicReference = zza;
        zzas zzasVar = (zzas) atomicReference.get();
        if (zzasVar != null) {
            return zzasVar;
        }
        atomicReference.compareAndSet(null, new zzas(application, zzay.zza(application)));
        return (zzas) Preconditions.checkNotNull((zzas) atomicReference.get());
    }

    public final void zzb() {
        zzfu.zza("AutomaticGamesAuthenticator", "startWatching()");
        this.zzb.zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzaw zzc() {
        return this.zzc;
    }
}
