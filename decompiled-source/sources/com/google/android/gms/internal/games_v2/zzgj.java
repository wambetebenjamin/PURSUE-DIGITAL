package com.google.android.gms.internal.games_v2;

import java.util.Arrays;
import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzgj extends zzgg {
    public zzgj() {
        super(4);
    }

    public final zzgm zzc() {
        this.zzc = true;
        return zzgm.zzj(this.zza, this.zzb);
    }

    public final zzgj zzb(Object obj) {
        Objects.requireNonNull(obj);
        int length = this.zza.length;
        int zza = zzgg.zza(length, this.zzb + 1);
        if (zza > length || this.zzc) {
            this.zza = Arrays.copyOf(this.zza, zza);
            this.zzc = false;
        }
        Object[] objArr = this.zza;
        int i2 = this.zzb;
        this.zzb = i2 + 1;
        objArr[i2] = obj;
        return this;
    }
}
