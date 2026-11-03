package com.google.android.gms.internal.play_billing;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzgr implements zzgz {
    private final zzgz[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgr(zzgz... zzgzVarArr) {
        this.zza = zzgzVarArr;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgz
    public final zzgy zzb(Class cls) {
        for (int i2 = 0; i2 < 2; i2++) {
            zzgz zzgzVar = this.zza[i2];
            if (zzgzVar.zzc(cls)) {
                return zzgzVar.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    @Override // com.google.android.gms.internal.play_billing.zzgz
    public final boolean zzc(Class cls) {
        for (int i2 = 0; i2 < 2; i2++) {
            if (this.zza[i2].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
