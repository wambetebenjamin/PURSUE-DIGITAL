package com.google.android.gms.internal.auth;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes5.dex */
public final class zzdy extends zzeb {
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdy(byte[] bArr, int i2, int i3) {
        super(bArr);
        zzi(0, i3, bArr.length);
        this.zzc = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.auth.zzeb, com.google.android.gms.internal.auth.zzee
    public final byte zzb(int i2) {
        return this.zza[i2];
    }

    @Override // com.google.android.gms.internal.auth.zzeb
    protected final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.auth.zzeb, com.google.android.gms.internal.auth.zzee
    public final int zzd() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.auth.zzeb, com.google.android.gms.internal.auth.zzee
    public final byte zza(int i2) {
        int i3 = this.zzc;
        if (((i3 - (i2 + 1)) | i2) < 0) {
            if (i2 < 0) {
                throw new ArrayIndexOutOfBoundsException("Index < 0: " + i2);
            }
            throw new ArrayIndexOutOfBoundsException("Index > length: " + i2 + ", " + i3);
        }
        return this.zza[i2];
    }
}
