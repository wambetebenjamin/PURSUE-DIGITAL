package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
public final class zziz extends zziw {
    private final byte[] zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;

    private final byte zzv() throws IOException {
        int i2 = this.zzg;
        if (i2 == this.zze) {
            throw zzkb.zzi();
        }
        byte[] bArr = this.zzd;
        this.zzg = i2 + 1;
        return bArr[i2];
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final double zza() throws IOException {
        return Double.longBitsToDouble(zzy());
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final float zzb() throws IOException {
        return Float.intBitsToFloat(zzw());
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final int zzc() {
        return this.zzg - this.zzh;
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final int zzb(int i2) throws zzkb {
        if (i2 < 0) {
            throw zzkb.zzf();
        }
        int zzc = i2 + zzc();
        if (zzc < 0) {
            throw zzkb.zzg();
        }
        int i3 = this.zzj;
        if (zzc > i3) {
            throw zzkb.zzi();
        }
        this.zzj = zzc;
        zzaa();
        return i3;
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final int zzd() throws IOException {
        return zzx();
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final int zze() throws IOException {
        return zzw();
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final int zzf() throws IOException {
        return zzx();
    }

    private final int zzw() throws IOException {
        int i2 = this.zzg;
        if (this.zze - i2 < 4) {
            throw zzkb.zzi();
        }
        byte[] bArr = this.zzd;
        this.zzg = i2 + 4;
        return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0066, code lost:
        if (r2[r3] >= 0) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zzx() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.zzg
            int r1 = r5.zze
            if (r1 == r0) goto L6b
            byte[] r2 = r5.zzd
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L11
            r5.zzg = r3
            return r0
        L11:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L6b
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L22
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            goto L68
        L22:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L2f
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L2d:
            r1 = r3
            goto L68
        L2f:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L3d
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L68
        L3d:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L2d
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L68
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2d
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L68
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2d
            int r1 = r3 + 1
            r2 = r2[r3]
            if (r2 < 0) goto L6b
        L68:
            r5.zzg = r1
            return r0
        L6b:
            long r0 = r5.zzm()
            int r0 = (int) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zziz.zzx():int");
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final int zzg() throws IOException {
        return zzw();
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final int zzh() throws IOException {
        return zza(zzx());
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final int zzi() throws IOException {
        if (zzt()) {
            this.zzi = 0;
            return 0;
        }
        int zzx = zzx();
        this.zzi = zzx;
        if ((zzx >>> 3) != 0) {
            return zzx;
        }
        throw zzkb.zzc();
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final int zzj() throws IOException {
        return zzx();
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final long zzk() throws IOException {
        return zzy();
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final long zzl() throws IOException {
        return zzz();
    }

    private final long zzy() throws IOException {
        int i2 = this.zzg;
        if (this.zze - i2 < 8) {
            throw zzkb.zzi();
        }
        byte[] bArr = this.zzd;
        this.zzg = i2 + 8;
        return ((bArr[i2 + 7] & 255) << 56) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 3] & 255) << 24) | ((bArr[i2 + 4] & 255) << 32) | ((bArr[i2 + 5] & 255) << 40) | ((bArr[i2 + 6] & 255) << 48);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b0, code lost:
        if (r2[r0] >= 0) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final long zzz() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 189
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zziz.zzz():long");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zziw
    public final long zzm() throws IOException {
        long j2 = 0;
        for (int i2 = 0; i2 < 64; i2 += 7) {
            byte zzv = zzv();
            j2 |= (zzv & Byte.MAX_VALUE) << i2;
            if ((zzv & 128) == 0) {
                return j2;
            }
        }
        throw zzkb.zze();
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final long zzn() throws IOException {
        return zzy();
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final long zzo() throws IOException {
        return zza(zzz());
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final long zzp() throws IOException {
        return zzz();
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final zzik zzq() throws IOException {
        byte[] bArr;
        int zzx = zzx();
        if (zzx > 0) {
            int i2 = this.zze;
            int i3 = this.zzg;
            if (zzx <= i2 - i3) {
                zzik zza = zzik.zza(this.zzd, i3, zzx);
                this.zzg += zzx;
                return zza;
            }
        }
        if (zzx == 0) {
            return zzik.zza;
        }
        if (zzx > 0) {
            int i4 = this.zze;
            int i5 = this.zzg;
            if (zzx <= i4 - i5) {
                int i6 = zzx + i5;
                this.zzg = i6;
                bArr = Arrays.copyOfRange(this.zzd, i5, i6);
                return zzik.zzb(bArr);
            }
        }
        if (zzx <= 0) {
            if (zzx == 0) {
                bArr = zzjv.zzb;
                return zzik.zzb(bArr);
            }
            throw zzkb.zzf();
        }
        throw zzkb.zzi();
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final String zzr() throws IOException {
        int zzx = zzx();
        if (zzx > 0 && zzx <= this.zze - this.zzg) {
            String str = new String(this.zzd, this.zzg, zzx, zzjv.zza);
            this.zzg += zzx;
            return str;
        } else if (zzx == 0) {
            return "";
        } else {
            if (zzx < 0) {
                throw zzkb.zzf();
            }
            throw zzkb.zzi();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final String zzs() throws IOException {
        int zzx = zzx();
        if (zzx > 0) {
            int i2 = this.zze;
            int i3 = this.zzg;
            if (zzx <= i2 - i3) {
                String zzb = zzmp.zzb(this.zzd, i3, zzx);
                this.zzg += zzx;
                return zzb;
            }
        }
        if (zzx == 0) {
            return "";
        }
        if (zzx <= 0) {
            throw zzkb.zzf();
        }
        throw zzkb.zzi();
    }

    private zziz(byte[] bArr, int i2, int i3, boolean z) {
        super();
        this.zzj = Integer.MAX_VALUE;
        this.zzd = bArr;
        this.zze = i3 + i2;
        this.zzg = i2;
        this.zzh = i2;
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final void zzc(int i2) throws zzkb {
        if (this.zzi != i2) {
            throw zzkb.zzb();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final void zzd(int i2) {
        this.zzj = i2;
        zzaa();
    }

    private final void zzaa() {
        int i2 = this.zze + this.zzf;
        this.zze = i2;
        int i3 = i2 - this.zzh;
        int i4 = this.zzj;
        if (i3 > i4) {
            int i5 = i3 - i4;
            this.zzf = i5;
            this.zze = i2 - i5;
            return;
        }
        this.zzf = 0;
    }

    private final void zzf(int i2) throws IOException {
        if (i2 >= 0) {
            int i3 = this.zze;
            int i4 = this.zzg;
            if (i2 <= i3 - i4) {
                this.zzg = i4 + i2;
                return;
            }
        }
        if (i2 < 0) {
            throw zzkb.zzf();
        }
        throw zzkb.zzi();
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final boolean zzt() throws IOException {
        return this.zzg == this.zze;
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final boolean zzu() throws IOException {
        return zzz() != 0;
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final boolean zze(int i2) throws IOException {
        boolean zze;
        int i3 = i2 & 7;
        int i4 = 0;
        if (i3 == 0) {
            if (this.zze - this.zzg >= 10) {
                while (i4 < 10) {
                    byte[] bArr = this.zzd;
                    int i5 = this.zzg;
                    this.zzg = i5 + 1;
                    if (bArr[i5] < 0) {
                        i4++;
                    }
                }
                throw zzkb.zze();
            }
            while (i4 < 10) {
                if (zzv() < 0) {
                    i4++;
                }
            }
            throw zzkb.zze();
            return true;
        } else if (i3 == 1) {
            zzf(8);
            return true;
        } else if (i3 == 2) {
            zzf(zzx());
            return true;
        } else if (i3 != 3) {
            if (i3 != 4) {
                if (i3 == 5) {
                    zzf(4);
                    return true;
                }
                throw zzkb.zza();
            }
            return false;
        } else {
            do {
                int zzi = zzi();
                if (zzi == 0) {
                    break;
                } else if (this.zza >= this.zzb) {
                    throw zzkb.zzh();
                } else {
                    this.zza++;
                    zze = zze(zzi);
                    this.zza--;
                }
            } while (zze);
            zzc(((i2 >>> 3) << 3) | 4);
            return true;
        }
    }
}
