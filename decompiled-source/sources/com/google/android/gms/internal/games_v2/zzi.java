package com.google.android.gms.internal.games_v2;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzi implements Api.ApiOptions.Optional {
    public final int zza;
    public final String zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzi(int i2, String str, byte[] bArr) {
        this.zza = i2;
        this.zzb = str;
    }

    public static zzh zza() {
        return new zzh(null);
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzi) {
            zzi zziVar = (zzi) obj;
            return this.zza == zziVar.zza && Objects.equal(this.zzb, zziVar.zzb);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), this.zzb);
    }
}
