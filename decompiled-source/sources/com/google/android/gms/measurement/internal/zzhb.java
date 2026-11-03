package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzhb {
    private final String zza;
    private final long zzb;
    private boolean zzc;
    private long zzd;
    private final /* synthetic */ zzha zze;

    public final long zza() {
        if (!this.zzc) {
            this.zzc = true;
            this.zzd = this.zze.zzg().getLong(this.zza, this.zzb);
        }
        return this.zzd;
    }

    public zzhb(zzha zzhaVar, String str, long j2) {
        this.zze = zzhaVar;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
        this.zzb = j2;
    }

    public final void zza(long j2) {
        SharedPreferences.Editor edit = this.zze.zzg().edit();
        edit.putLong(this.zza, j2);
        edit.apply();
        this.zzd = j2;
    }
}
