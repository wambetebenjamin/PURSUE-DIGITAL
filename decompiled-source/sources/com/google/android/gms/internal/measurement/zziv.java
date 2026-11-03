package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
public class zziv extends zzis {
    protected final byte[] zzb;

    @Override // com.google.android.gms.internal.measurement.zzik
    public byte zza(int i2) {
        return this.zzb[i2];
    }

    protected int zzc() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzik
    public byte zzb(int i2) {
        return this.zzb[i2];
    }

    @Override // com.google.android.gms.internal.measurement.zzik
    protected final int zzb(int i2, int i3, int i4) {
        return zzjv.zza(i2, this.zzb, zzc(), i4);
    }

    @Override // com.google.android.gms.internal.measurement.zzik
    public int zzb() {
        return this.zzb.length;
    }

    @Override // com.google.android.gms.internal.measurement.zzik
    public final zzik zza(int i2, int i3) {
        int zza = zza(0, i3, zzb());
        if (zza == 0) {
            return zzik.zza;
        }
        return new zzio(this.zzb, zzc(), zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zziv(byte[] bArr) {
        super();
        Objects.requireNonNull(bArr);
        this.zzb = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzik
    public final void zza(zzil zzilVar) throws IOException {
        zzilVar.zza(this.zzb, zzc(), zzb());
    }

    @Override // com.google.android.gms.internal.measurement.zzik
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzik) && zzb() == ((zzik) obj).zzb()) {
            if (zzb() == 0) {
                return true;
            }
            if (obj instanceof zziv) {
                zziv zzivVar = (zziv) obj;
                int zza = zza();
                int zza2 = zzivVar.zza();
                if (zza == 0 || zza2 == 0 || zza == zza2) {
                    return zza(zzivVar, 0, zzb());
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzis
    final boolean zza(zzik zzikVar, int i2, int i3) {
        if (i3 > zzikVar.zzb()) {
            throw new IllegalArgumentException("Length too large: " + i3 + zzb());
        } else if (i3 > zzikVar.zzb()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + i3 + ", " + zzikVar.zzb());
        } else if (zzikVar instanceof zziv) {
            zziv zzivVar = (zziv) zzikVar;
            byte[] bArr = this.zzb;
            byte[] bArr2 = zzivVar.zzb;
            int zzc = zzc() + i3;
            int zzc2 = zzc();
            int zzc3 = zzivVar.zzc();
            while (zzc2 < zzc) {
                if (bArr[zzc2] != bArr2[zzc3]) {
                    return false;
                }
                zzc2++;
                zzc3++;
            }
            return true;
        } else {
            return zzikVar.zza(0, i3).equals(zza(0, i3));
        }
    }
}
