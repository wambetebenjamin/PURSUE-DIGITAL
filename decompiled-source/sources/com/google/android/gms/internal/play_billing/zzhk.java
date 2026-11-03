package com.google.android.gms.internal.play_billing;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzhk implements zzgy {
    private final zzhb zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhk(zzhb zzhbVar, String str, Object[] objArr) {
        this.zza = zzhbVar;
        this.zzb = str;
        this.zzc = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.zzd = charAt;
            return;
        }
        int i2 = charAt & 8191;
        int i3 = 1;
        int i4 = 13;
        while (true) {
            int i5 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 < 55296) {
                this.zzd = i2 | (charAt2 << i4);
                return;
            }
            i2 |= (charAt2 & 8191) << i4;
            i4 += 13;
            i3 = i5;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzgy
    public final zzhb zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgy
    public final boolean zzb() {
        return (this.zzd & 2) == 2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgy
    public final int zzc() {
        int i2 = this.zzd;
        if ((i2 & 1) != 0) {
            return 1;
        }
        return (i2 & 4) == 4 ? 3 : 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzd() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object[] zze() {
        return this.zzc;
    }
}
