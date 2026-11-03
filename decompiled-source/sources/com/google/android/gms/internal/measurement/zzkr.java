package com.google.android.gms.internal.measurement;
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
final class zzkr implements zzkz {
    private zzkz[] zza;

    @Override // com.google.android.gms.internal.measurement.zzkz
    public final zzla zza(Class<?> cls) {
        zzkz[] zzkzVarArr;
        for (zzkz zzkzVar : this.zza) {
            if (zzkzVar.zzb(cls)) {
                return zzkzVar.zza(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: " + cls.getName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkr(zzkz... zzkzVarArr) {
        this.zza = zzkzVarArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzkz
    public final boolean zzb(Class<?> cls) {
        for (zzkz zzkzVar : this.zza) {
            if (zzkzVar.zzb(cls)) {
                return true;
            }
        }
        return false;
    }
}
