package com.google.android.gms.internal.auth;
/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes5.dex */
final class zzfn implements zzfu {
    private final zzfu[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfn(zzfu... zzfuVarArr) {
        this.zza = zzfuVarArr;
    }

    @Override // com.google.android.gms.internal.auth.zzfu
    public final zzft zzb(Class cls) {
        zzfu[] zzfuVarArr = this.zza;
        for (int i2 = 0; i2 < 2; i2++) {
            zzfu zzfuVar = zzfuVarArr[i2];
            if (zzfuVar.zzc(cls)) {
                return zzfuVar.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    @Override // com.google.android.gms.internal.auth.zzfu
    public final boolean zzc(Class cls) {
        zzfu[] zzfuVarArr = this.zza;
        for (int i2 = 0; i2 < 2; i2++) {
            if (zzfuVarArr[i2].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
