package com.google.android.gms.internal.measurement;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzab extends zzal {
    @Override // com.google.android.gms.internal.measurement.zzal
    public final zzaq zza(zzh zzhVar, List<zzaq> list) {
        return new zzai(Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzab(zzy zzyVar, String str) {
        super(str);
    }
}
