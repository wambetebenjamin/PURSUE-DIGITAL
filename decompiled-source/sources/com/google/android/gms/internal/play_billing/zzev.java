package com.google.android.gms.internal.play_billing;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public abstract class zzev implements Iterable, Serializable {
    public static final zzev zza = new zzet(zzga.zzb);
    private int zzb = 0;

    static {
        int i2 = zzei.zza;
    }

    public static zzev zzk(byte[] bArr, int i2, int i3) {
        try {
            zzj(i2, i2 + i3, bArr.length);
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, i2, bArr2, 0, i3);
            return new zzet(bArr2);
        } catch (zzgc e2) {
            throw new AssertionError("Expected no InvalidProtocolBufferException as data UTF8 validity is not checked.", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean zzl(byte[] bArr, int i2, byte[] bArr2, int i3, int i4) {
        int i5 = i2 + i4;
        zzj(i2, i5, bArr.length);
        zzj(i3, i4 + i3, bArr2.length);
        while (i2 < i5) {
            if (bArr[i2] != bArr2[i3]) {
                return false;
            }
            i2++;
            i3++;
        }
        return true;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzev) {
            zzev zzevVar = (zzev) obj;
            int zze = zze();
            if (zze != zzevVar.zze()) {
                return false;
            }
            if (zze != 0) {
                int i2 = this.zzb;
                int i3 = zzevVar.zzb;
                if (i2 == 0 || i3 == 0 || i2 == i3) {
                    return zzh(zzevVar);
                }
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2 = this.zzb;
        if (i2 == 0) {
            int zze = zze();
            i2 = zzd(zze, 0, zze);
            if (i2 == 0) {
                i2 = 1;
            }
            this.zzb = i2;
        }
        return i2;
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new zzen(this);
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zze());
        objArr[2] = zze() <= 50 ? zzhz.zza(this) : zzhz.zza(zzf(0, 47)).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public abstract byte zza(int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte zzb(int i2);

    protected abstract int zzd(int i2, int i3, int i4);

    public abstract int zze();

    public abstract zzev zzf(int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzg(zzem zzemVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean zzh(zzev zzevVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(int i2, int i3, int i4) {
        int i5 = i3 - i2;
        if ((i2 | i3 | i5 | (i4 - i3)) < 0) {
            if (i2 < 0) {
                throw new IndexOutOfBoundsException("Beginning index: " + i2 + " < 0");
            } else if (i3 < i2) {
                throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i2 + ", " + i3);
            } else {
                throw new IndexOutOfBoundsException("End index: " + i3 + " >= " + i4);
            }
        }
        return i5;
    }
}
