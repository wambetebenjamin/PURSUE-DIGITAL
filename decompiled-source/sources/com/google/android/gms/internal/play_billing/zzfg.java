package com.google.android.gms.internal.play_billing;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzfg {
    private final Object zza;
    private final int zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfg(Object obj, int i2) {
        this.zza = obj;
        this.zzb = i2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzfg) {
            zzfg zzfgVar = (zzfg) obj;
            return this.zza == zzfgVar.zza && this.zzb == zzfgVar.zzb;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}
