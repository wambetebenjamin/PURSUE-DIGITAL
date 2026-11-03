package com.google.android.gms.internal.games_v2;

import java.util.Arrays;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzgo {
    Object[] zza = new Object[8];
    int zzb = 0;
    zzgn zzc;

    public final zzgo zza(Object obj, Object obj2) {
        int i2 = this.zzb + 1;
        Object[] objArr = this.zza;
        int length = objArr.length;
        int i3 = i2 + i2;
        if (i3 > length) {
            this.zza = Arrays.copyOf(objArr, zzgh.zza(length, i3));
        }
        zzge.zza(obj, obj2);
        Object[] objArr2 = this.zza;
        int i4 = this.zzb;
        int i5 = i4 + i4;
        objArr2[i5] = obj;
        objArr2[i5 + 1] = obj2;
        this.zzb = i4 + 1;
        return this;
    }

    public final zzgp zzb() {
        zzgn zzgnVar = this.zzc;
        if (zzgnVar != null) {
            throw zzgnVar.zza();
        }
        zzgx zzd = zzgx.zzd(this.zzb, this.zza, this);
        zzgn zzgnVar2 = this.zzc;
        if (zzgnVar2 == null) {
            return zzd;
        }
        throw zzgnVar2.zza();
    }
}
