package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
public abstract class zzjc extends zzil {
    private static final Logger zzb = Logger.getLogger(zzjc.class.getName());
    private static final boolean zzc = zzml.zzc();
    zzjf zza;

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
    /* loaded from: classes5.dex */
    private static class zza extends zzjc {
        private final byte[] zzb;
        private final int zzc;
        private int zzd;

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final int zza() {
            return this.zzc - this.zzd;
        }

        zza(byte[] bArr, int i2, int i3) {
            super();
            Objects.requireNonNull(bArr, "buffer");
            if ((i3 | 0 | (bArr.length - i3)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), 0, Integer.valueOf(i3)));
            }
            this.zzb = bArr;
            this.zzd = 0;
            this.zzc = i3;
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(byte b2) throws IOException {
            int i2 = this.zzd;
            try {
                int i3 = i2 + 1;
                try {
                    this.zzb[i2] = b2;
                    this.zzd = i3;
                } catch (IndexOutOfBoundsException e2) {
                    e = e2;
                    i2 = i3;
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(i2), Integer.valueOf(this.zzc), 1), e);
                }
            } catch (IndexOutOfBoundsException e3) {
                e = e3;
            }
        }

        private final void zzc(byte[] bArr, int i2, int i3) throws IOException {
            try {
                System.arraycopy(bArr, i2, this.zzb, this.zzd, i3);
                this.zzd += i3;
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), Integer.valueOf(i3)), e2);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(int i2, boolean z) throws IOException {
            zzc(i2, 0);
            zza(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzb(byte[] bArr, int i2, int i3) throws IOException {
            zzc(i3);
            zzc(bArr, 0, i3);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(int i2, zzik zzikVar) throws IOException {
            zzc(i2, 2);
            zza(zzikVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(zzik zzikVar) throws IOException {
            zzc(zzikVar.zzb());
            zzikVar.zza(this);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(int i2, int i3) throws IOException {
            zzc(i2, 5);
            zza(i3);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(int i2) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i3 = this.zzd;
                int i4 = i3 + 1;
                this.zzd = i4;
                bArr[i3] = (byte) i2;
                int i5 = i4 + 1;
                this.zzd = i5;
                bArr[i4] = (byte) (i2 >> 8);
                int i6 = i5 + 1;
                this.zzd = i6;
                bArr[i5] = (byte) (i2 >> 16);
                this.zzd = i6 + 1;
                bArr[i6] = (byte) (i2 >>> 24);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(int i2, long j2) throws IOException {
            zzc(i2, 1);
            zza(j2);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(long j2) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i2 = this.zzd;
                int i3 = i2 + 1;
                this.zzd = i3;
                bArr[i2] = (byte) j2;
                int i4 = i3 + 1;
                this.zzd = i4;
                bArr[i3] = (byte) (j2 >> 8);
                int i5 = i4 + 1;
                this.zzd = i5;
                bArr[i4] = (byte) (j2 >> 16);
                int i6 = i5 + 1;
                this.zzd = i6;
                bArr[i5] = (byte) (j2 >> 24);
                int i7 = i6 + 1;
                this.zzd = i7;
                bArr[i6] = (byte) (j2 >> 32);
                int i8 = i7 + 1;
                this.zzd = i8;
                bArr[i7] = (byte) (j2 >> 40);
                int i9 = i8 + 1;
                this.zzd = i9;
                bArr[i8] = (byte) (j2 >> 48);
                this.zzd = i9 + 1;
                bArr[i9] = (byte) (j2 >> 56);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzb(int i2, int i3) throws IOException {
            zzc(i2, 0);
            zzb(i3);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzb(int i2) throws IOException {
            if (i2 >= 0) {
                zzc(i2);
            } else {
                zzb(i2);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzil
        public final void zza(byte[] bArr, int i2, int i3) throws IOException {
            zzc(bArr, i2, i3);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        final void zza(int i2, zzlc zzlcVar, zzlu zzluVar) throws IOException {
            zzc(i2, 2);
            zzc(((zzib) zzlcVar).zza(zzluVar));
            zzluVar.zza((zzlu) zzlcVar, (zznb) this.zza);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(zzlc zzlcVar) throws IOException {
            zzc(zzlcVar.zzcb());
            zzlcVar.zza(this);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(int i2, zzlc zzlcVar) throws IOException {
            zzc(1, 3);
            zzd(2, i2);
            zzc(3, 2);
            zza(zzlcVar);
            zzc(1, 4);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzb(int i2, zzik zzikVar) throws IOException {
            zzc(1, 3);
            zzd(2, i2);
            zza(3, zzikVar);
            zzc(1, 4);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(int i2, String str) throws IOException {
            zzc(i2, 2);
            zza(str);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(String str) throws IOException {
            int i2 = this.zzd;
            try {
                int zzj = zzj(str.length() * 3);
                int zzj2 = zzj(str.length());
                if (zzj2 == zzj) {
                    int i3 = i2 + zzj2;
                    this.zzd = i3;
                    int zza = zzmp.zza(str, this.zzb, i3, zza());
                    this.zzd = i2;
                    zzc((zza - i2) - zzj2);
                    this.zzd = zza;
                    return;
                }
                zzc(zzmp.zza(str));
                this.zzd = zzmp.zza(str, this.zzb, this.zzd, zza());
            } catch (zzmt e2) {
                this.zzd = i2;
                zza(str, e2);
            } catch (IndexOutOfBoundsException e3) {
                throw new zzb(e3);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzc(int i2, int i3) throws IOException {
            zzc((i2 << 3) | i3);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzd(int i2, int i3) throws IOException {
            zzc(i2, 0);
            zzc(i3);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzc(int i2) throws IOException {
            while ((i2 & (-128)) != 0) {
                try {
                    byte[] bArr = this.zzb;
                    int i3 = this.zzd;
                    this.zzd = i3 + 1;
                    bArr[i3] = (byte) (i2 | 128);
                    i2 >>>= 7;
                } catch (IndexOutOfBoundsException e2) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
                }
            }
            byte[] bArr2 = this.zzb;
            int i4 = this.zzd;
            this.zzd = i4 + 1;
            bArr2[i4] = (byte) i2;
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzb(int i2, long j2) throws IOException {
            zzc(i2, 0);
            zzb(j2);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzb(long j2) throws IOException {
            if (zzjc.zzc && zza() >= 10) {
                while ((j2 & (-128)) != 0) {
                    byte[] bArr = this.zzb;
                    int i2 = this.zzd;
                    this.zzd = i2 + 1;
                    zzml.zza(bArr, i2, (byte) (((int) j2) | 128));
                    j2 >>>= 7;
                }
                byte[] bArr2 = this.zzb;
                int i3 = this.zzd;
                this.zzd = i3 + 1;
                zzml.zza(bArr2, i3, (byte) j2);
                return;
            }
            while ((j2 & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.zzb;
                    int i4 = this.zzd;
                    this.zzd = i4 + 1;
                    bArr3[i4] = (byte) (((int) j2) | 128);
                    j2 >>>= 7;
                } catch (IndexOutOfBoundsException e2) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
                }
            }
            byte[] bArr4 = this.zzb;
            int i5 = this.zzd;
            this.zzd = i5 + 1;
            bArr4[i5] = (byte) j2;
        }
    }

    public static int zza(double d2) {
        return 8;
    }

    public static int zza(float f2) {
        return 4;
    }

    public static int zza(boolean z) {
        return 1;
    }

    public static int zzc(long j2) {
        return 8;
    }

    public static int zze(int i2) {
        return 4;
    }

    public static int zze(long j2) {
        return 8;
    }

    public static int zzg(int i2) {
        return 4;
    }

    private static long zzi(long j2) {
        return (j2 >> 63) ^ (j2 << 1);
    }

    private static int zzl(int i2) {
        return (i2 >> 31) ^ (i2 << 1);
    }

    public abstract int zza();

    public abstract void zza(byte b2) throws IOException;

    public abstract void zza(int i2) throws IOException;

    public abstract void zza(int i2, int i3) throws IOException;

    public abstract void zza(int i2, long j2) throws IOException;

    public abstract void zza(int i2, zzik zzikVar) throws IOException;

    public abstract void zza(int i2, zzlc zzlcVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(int i2, zzlc zzlcVar, zzlu zzluVar) throws IOException;

    public abstract void zza(int i2, String str) throws IOException;

    public abstract void zza(int i2, boolean z) throws IOException;

    public abstract void zza(long j2) throws IOException;

    public abstract void zza(zzik zzikVar) throws IOException;

    public abstract void zza(zzlc zzlcVar) throws IOException;

    public abstract void zza(String str) throws IOException;

    public abstract void zzb(int i2) throws IOException;

    public abstract void zzb(int i2, int i3) throws IOException;

    public abstract void zzb(int i2, long j2) throws IOException;

    public abstract void zzb(int i2, zzik zzikVar) throws IOException;

    public abstract void zzb(long j2) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(byte[] bArr, int i2, int i3) throws IOException;

    public abstract void zzc(int i2) throws IOException;

    public abstract void zzc(int i2, int i3) throws IOException;

    public abstract void zzd(int i2, int i3) throws IOException;

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
    /* loaded from: classes5.dex */
    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        zzb(String str, Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + str, th);
        }
    }

    public static int zzb(int i2, boolean z) {
        return zzj(i2 << 3) + 1;
    }

    public static int zza(byte[] bArr) {
        int length = bArr.length;
        return zzj(length) + length;
    }

    public static int zzc(int i2, zzik zzikVar) {
        int zzj = zzj(i2 << 3);
        int zzb2 = zzikVar.zzb();
        return zzj + zzj(zzb2) + zzb2;
    }

    public static int zzb(zzik zzikVar) {
        int zzb2 = zzikVar.zzb();
        return zzj(zzb2) + zzb2;
    }

    public static int zza(int i2, double d2) {
        return zzj(i2 << 3) + 8;
    }

    public static int zze(int i2, int i3) {
        return zzj(i2 << 3) + zzg(i3);
    }

    public static int zzd(int i2) {
        return zzg(i2);
    }

    public static int zzf(int i2, int i3) {
        return zzj(i2 << 3) + 4;
    }

    public static int zzc(int i2, long j2) {
        return zzj(i2 << 3) + 8;
    }

    public static int zza(int i2, float f2) {
        return zzj(i2 << 3) + 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static int zzb(int i2, zzlc zzlcVar, zzlu zzluVar) {
        return (zzj(i2 << 3) << 1) + ((zzib) zzlcVar).zza(zzluVar);
    }

    @Deprecated
    public static int zzb(zzlc zzlcVar) {
        return zzlcVar.zzcb();
    }

    public static int zzg(int i2, int i3) {
        return zzj(i2 << 3) + zzg(i3);
    }

    public static int zzf(int i2) {
        return zzg(i2);
    }

    public static int zzd(int i2, long j2) {
        return zzj(i2 << 3) + zzg(j2);
    }

    public static int zzd(long j2) {
        return zzg(j2);
    }

    public static int zza(int i2, zzkk zzkkVar) {
        return (zzj(8) << 1) + zzj(2, i2) + zzb(3, zzkkVar);
    }

    public static int zzb(int i2, zzkk zzkkVar) {
        int zzj = zzj(i2 << 3);
        int zza2 = zzkkVar.zza();
        return zzj + zzj(zza2) + zza2;
    }

    public static int zza(zzkk zzkkVar) {
        int zza2 = zzkkVar.zza();
        return zzj(zza2) + zza2;
    }

    public static int zzb(int i2, zzlc zzlcVar) {
        return (zzj(8) << 1) + zzj(2, i2) + zzj(24) + zzc(zzlcVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int i2, zzlc zzlcVar, zzlu zzluVar) {
        return zzj(i2 << 3) + zza(zzlcVar, zzluVar);
    }

    public static int zzc(zzlc zzlcVar) {
        int zzcb = zzlcVar.zzcb();
        return zzj(zzcb) + zzcb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(zzlc zzlcVar, zzlu zzluVar) {
        int zza2 = ((zzib) zzlcVar).zza(zzluVar);
        return zzj(zza2) + zza2;
    }

    public static int zzd(int i2, zzik zzikVar) {
        return (zzj(8) << 1) + zzj(2, i2) + zzc(3, zzikVar);
    }

    public static int zzh(int i2, int i3) {
        return zzj(i2 << 3) + 4;
    }

    public static int zze(int i2, long j2) {
        return zzj(i2 << 3) + 8;
    }

    public static int zzi(int i2, int i3) {
        return zzj(i2 << 3) + zzj(zzl(i3));
    }

    public static int zzh(int i2) {
        return zzj(zzl(i2));
    }

    public static int zzf(int i2, long j2) {
        return zzj(i2 << 3) + zzg(zzi(j2));
    }

    public static int zzf(long j2) {
        return zzg(zzi(j2));
    }

    public static int zzb(int i2, String str) {
        return zzj(i2 << 3) + zzb(str);
    }

    public static int zzb(String str) {
        int length;
        try {
            length = zzmp.zza(str);
        } catch (zzmt unused) {
            length = str.getBytes(zzjv.zza).length;
        }
        return zzj(length) + length;
    }

    public static int zzi(int i2) {
        return zzj(i2 << 3);
    }

    public static int zzj(int i2, int i3) {
        return zzj(i2 << 3) + zzj(i3);
    }

    public static int zzj(int i2) {
        return (352 - (Integer.numberOfLeadingZeros(i2) * 9)) >>> 6;
    }

    public static int zzg(int i2, long j2) {
        return zzj(i2 << 3) + zzg(j2);
    }

    public static int zzg(long j2) {
        return (640 - (Long.numberOfLeadingZeros(j2) * 9)) >>> 6;
    }

    public static zzjc zzb(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    private zzjc() {
    }

    public final void zzb() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    final void zza(String str, zzmt zzmtVar) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzmtVar);
        byte[] bytes = str.getBytes(zzjv.zza);
        try {
            zzc(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzb(e2);
        }
    }

    public final void zzb(boolean z) throws IOException {
        zza(z ? (byte) 1 : (byte) 0);
    }

    public final void zzb(int i2, double d2) throws IOException {
        zza(i2, Double.doubleToRawLongBits(d2));
    }

    public final void zzb(double d2) throws IOException {
        zza(Double.doubleToRawLongBits(d2));
    }

    public final void zzb(int i2, float f2) throws IOException {
        zza(i2, Float.floatToRawIntBits(f2));
    }

    public final void zzb(float f2) throws IOException {
        zza(Float.floatToRawIntBits(f2));
    }

    public final void zzk(int i2, int i3) throws IOException {
        zzd(i2, zzl(i3));
    }

    public final void zzk(int i2) throws IOException {
        zzc(zzl(i2));
    }

    public final void zzh(int i2, long j2) throws IOException {
        zzb(i2, zzi(j2));
    }

    public final void zzh(long j2) throws IOException {
        zzb(zzi(j2));
    }
}
