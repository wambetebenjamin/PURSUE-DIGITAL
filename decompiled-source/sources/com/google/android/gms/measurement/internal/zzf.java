package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
final class zzf {
    private final zzjh zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzf zza(String str) {
        zzjh zzjhVar;
        if (TextUtils.isEmpty(str) || str.length() > 1) {
            zzjhVar = zzjh.UNINITIALIZED;
        } else {
            zzjhVar = zzje.zza(str.charAt(0));
        }
        return new zzf(zzjhVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzjh zza() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzb() {
        return String.valueOf(zzje.zza(this.zza));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(zzjh zzjhVar) {
        this.zza = zzjhVar;
    }
}
