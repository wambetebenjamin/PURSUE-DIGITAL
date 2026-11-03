package com.google.android.gms.internal.play_billing;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzew extends zzey {
    private int zzb;
    private int zzc;
    private int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzew(byte[] bArr, int i2, int i3, boolean z, zzex zzexVar) {
        super(null);
        this.zzd = Integer.MAX_VALUE;
        this.zzb = 0;
    }

    public final int zza(int i2) throws zzgc {
        int i3 = this.zzd;
        this.zzd = 0;
        int i4 = this.zzb + this.zzc;
        this.zzb = i4;
        if (i4 > 0) {
            this.zzc = i4;
            this.zzb = i4 - i4;
        } else {
            this.zzc = 0;
        }
        return i3;
    }
}
