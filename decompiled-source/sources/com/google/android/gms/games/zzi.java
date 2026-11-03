package com.google.android.gms.games;

import android.text.TextUtils;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import java.util.ArrayList;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzi implements Api.ApiOptions.Optional {
    public final int zze;
    public final ArrayList zzg;
    public final String zzm;
    public final com.google.android.gms.games.internal.zzi zzn;
    public final boolean zza = false;
    public final boolean zzb = true;
    public final int zzc = 17;
    public final boolean zzd = false;
    public final String zzf = null;
    public final boolean zzh = false;
    public final boolean zzi = false;
    public final boolean zzj = false;
    public final String zzk = null;
    private final int zzp = 0;
    public final int zzl = 9;
    public final boolean zzo = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzi(boolean z, boolean z2, int i2, boolean z3, int i3, String str, ArrayList arrayList, boolean z4, boolean z5, boolean z6, String str2, int i4, int i5, String str3, com.google.android.gms.games.internal.zzi zziVar, boolean z7, byte[] bArr) {
        this.zze = i3;
        this.zzg = arrayList;
        this.zzm = str3;
        this.zzn = zziVar;
    }

    public static zzh zza() {
        return new zzh(null);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzi) {
            zzi zziVar = (zzi) obj;
            boolean z = zziVar.zza;
            boolean z2 = zziVar.zzb;
            int i2 = zziVar.zzc;
            boolean z3 = zziVar.zzd;
            if (this.zze == zziVar.zze) {
                String str = zziVar.zzf;
                if (this.zzg.equals(zziVar.zzg)) {
                    boolean z4 = zziVar.zzh;
                    boolean z5 = zziVar.zzi;
                    boolean z6 = zziVar.zzj;
                    String str2 = zziVar.zzk;
                    if (TextUtils.equals(null, null)) {
                        int i3 = zziVar.zzp;
                        int i4 = zziVar.zzl;
                        if (Objects.equal(this.zzm, zziVar.zzm)) {
                            boolean z7 = zziVar.zzo;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = ((this.zze + 486741695) * 961) + this.zzg.hashCode();
        String str = this.zzm;
        return ((((hashCode * 887503681) + 9) * 31) + (str == null ? 0 : str.hashCode())) * 31;
    }
}
