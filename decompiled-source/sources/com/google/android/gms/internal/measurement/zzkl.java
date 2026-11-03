package com.google.android.gms.internal.measurement;

import java.util.List;
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
final class zzkl implements zzkm {
    private static <E> zzkc<E> zzc(Object obj, long j2) {
        return (zzkc) zzml.zze(obj, j2);
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final <L> List<L> zza(Object obj, long j2) {
        zzkc zzc = zzc(obj, j2);
        if (zzc.zzc()) {
            return zzc;
        }
        int size = zzc.size();
        zzkc zza = zzc.zza(size == 0 ? 10 : size << 1);
        zzml.zza(obj, j2, zza);
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final void zzb(Object obj, long j2) {
        zzc(obj, j2).zzb();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.gms.internal.measurement.zzkc] */
    @Override // com.google.android.gms.internal.measurement.zzkm
    public final <E> void zza(Object obj, Object obj2, long j2) {
        zzkc<E> zzc = zzc(obj, j2);
        zzkc<E> zzc2 = zzc(obj2, j2);
        int size = zzc.size();
        int size2 = zzc2.size();
        zzkc<E> zzkcVar = zzc;
        zzkcVar = zzc;
        if (size > 0 && size2 > 0) {
            boolean zzc3 = zzc.zzc();
            zzkc<E> zzkcVar2 = zzc;
            if (!zzc3) {
                zzkcVar2 = zzc.zza(size2 + size);
            }
            zzkcVar2.addAll(zzc2);
            zzkcVar = zzkcVar2;
        }
        if (size > 0) {
            zzc2 = zzkcVar;
        }
        zzml.zza(obj, j2, zzc2);
    }
}
