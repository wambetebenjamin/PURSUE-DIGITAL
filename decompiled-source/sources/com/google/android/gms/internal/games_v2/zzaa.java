package com.google.android.gms.internal.games_v2;

import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
final class zzaa implements Runnable {
    final /* synthetic */ zzab zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(zzab zzabVar) {
        Objects.requireNonNull(zzabVar);
        this.zza = zzabVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzd();
    }
}
