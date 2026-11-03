package com.google.android.gms.internal.play_billing;

import sun.misc.Unsafe;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzig extends zzih {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzig(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.play_billing.zzih
    public final double zza(Object obj, long j2) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzih
    public final float zzb(Object obj, long j2) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzih
    public final void zzc(Object obj, long j2, boolean z) {
        if (zzii.zzb) {
            zzii.zzD(obj, j2, r3 ? (byte) 1 : (byte) 0);
        } else {
            zzii.zzE(obj, j2, r3 ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzih
    public final void zzd(Object obj, long j2, byte b2) {
        if (zzii.zzb) {
            zzii.zzD(obj, j2, b2);
        } else {
            zzii.zzE(obj, j2, b2);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzih
    public final void zze(Object obj, long j2, double d2) {
        this.zza.putLong(obj, j2, Double.doubleToLongBits(d2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzih
    public final void zzf(Object obj, long j2, float f2) {
        this.zza.putInt(obj, j2, Float.floatToIntBits(f2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzih
    public final boolean zzg(Object obj, long j2) {
        if (zzii.zzb) {
            return zzii.zzt(obj, j2);
        }
        return zzii.zzu(obj, j2);
    }
}
