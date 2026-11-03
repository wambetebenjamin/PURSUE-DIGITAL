package com.google.android.gms.internal.play_billing;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzbu extends zzbq {
    private final zzbw zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbu(zzbw zzbwVar, int i2) {
        super(zzbwVar.size(), i2);
        this.zza = zzbwVar;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbq
    protected final Object zza(int i2) {
        return this.zza.get(i2);
    }
}
