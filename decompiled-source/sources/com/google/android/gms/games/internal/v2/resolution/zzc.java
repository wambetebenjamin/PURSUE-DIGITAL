package com.google.android.gms.games.internal.v2.resolution;

import android.content.Intent;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzc {
    private final boolean zza;
    private final Intent zzb;

    private zzc(boolean z, Intent intent) {
        this.zza = z;
        this.zzb = intent;
    }

    public static zzc zza(Intent intent) {
        return new zzc(true, intent);
    }

    public static zzc zzb(Intent intent) {
        return new zzc(false, intent);
    }

    public final boolean zzc() {
        return this.zza;
    }

    public final Intent zzd() {
        return this.zzb;
    }
}
