package com.google.android.gms.internal.play_billing;

import java.io.IOException;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzgu {
    private final zzgt zza;

    private zzgu(zzir zzirVar, Object obj, zzir zzirVar2, Object obj2) {
        this.zza = new zzgt(zzirVar, "", zzirVar2, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(zzgt zzgtVar, Object obj, Object obj2) {
        return zzfm.zza(zzgtVar.zza, 1, obj) + zzfm.zza(zzgtVar.zzc, 2, obj2);
    }

    public static zzgu zzd(zzir zzirVar, Object obj, zzir zzirVar2, Object obj2) {
        return new zzgu(zzirVar, "", zzirVar2, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zze(zzfc zzfcVar, zzgt zzgtVar, Object obj, Object obj2) throws IOException {
        zzfm.zzi(zzfcVar, zzgtVar.zza, 1, obj);
        zzfm.zzi(zzfcVar, zzgtVar.zzc, 2, obj2);
    }

    public final int zza(int i2, Object obj, Object obj2) {
        zzgt zzgtVar = this.zza;
        int zzy = zzfc.zzy(i2 << 3);
        int zzb = zzb(zzgtVar, obj, obj2);
        return zzy + zzfc.zzy(zzb) + zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzgt zzc() {
        return this.zza;
    }
}
