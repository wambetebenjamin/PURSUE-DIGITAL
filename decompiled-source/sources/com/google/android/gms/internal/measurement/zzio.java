package com.google.android.gms.internal.measurement;
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
final class zzio extends zziv {
    private final int zzc;
    private final int zzd;

    @Override // com.google.android.gms.internal.measurement.zziv, com.google.android.gms.internal.measurement.zzik
    public final byte zza(int i2) {
        int zzb = zzb();
        if (((zzb - (i2 + 1)) | i2) < 0) {
            if (i2 < 0) {
                throw new ArrayIndexOutOfBoundsException("Index < 0: " + i2);
            }
            throw new ArrayIndexOutOfBoundsException("Index > length: " + i2 + ", " + zzb);
        }
        return this.zzb[this.zzc + i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zziv, com.google.android.gms.internal.measurement.zzik
    public final byte zzb(int i2) {
        return this.zzb[this.zzc + i2];
    }

    @Override // com.google.android.gms.internal.measurement.zziv
    protected final int zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zziv, com.google.android.gms.internal.measurement.zzik
    public final int zzb() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzio(byte[] bArr, int i2, int i3) {
        super(bArr);
        zza(i2, i2 + i3, bArr.length);
        this.zzc = i2;
        this.zzd = i3;
    }
}
