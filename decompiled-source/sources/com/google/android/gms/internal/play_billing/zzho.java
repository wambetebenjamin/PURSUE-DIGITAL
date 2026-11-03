package com.google.android.gms.internal.play_billing;

import java.util.Collections;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzho extends zzht {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzho() {
        super(null);
    }

    @Override // com.google.android.gms.internal.play_billing.zzht
    public final void zza() {
        if (!zzj()) {
            for (int i2 = 0; i2 < zzc(); i2++) {
                Map.Entry zzg = zzg(i2);
                if (((zzfl) ((zzhp) zzg).zza()).zze()) {
                    zzg.setValue(Collections.unmodifiableList((List) zzg.getValue()));
                }
            }
            for (Map.Entry entry : zzd()) {
                if (((zzfl) entry.getKey()).zze()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
