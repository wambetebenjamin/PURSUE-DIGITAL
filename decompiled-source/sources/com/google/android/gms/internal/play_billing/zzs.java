package com.google.android.gms.internal.play_billing;

import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzs extends zzo {
    final /* synthetic */ zzt zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzs(zzt zztVar) {
        Objects.requireNonNull(zztVar);
        this.zzg = zztVar;
    }

    @Override // com.google.android.gms.internal.play_billing.zzo
    protected final String zza() {
        zzp zzpVar = (zzp) this.zzg.zza.get();
        if (zzpVar == null) {
            return "Completer object has been garbage collected, future will fail soon";
        }
        String valueOf = String.valueOf(zzpVar.zza);
        return "tag=[" + valueOf + "]";
    }
}
