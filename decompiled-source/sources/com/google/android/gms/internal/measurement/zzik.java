package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
public abstract class zzik implements Serializable, Iterable<Byte> {
    public static final zzik zza = new zziv(zzjv.zzb);
    private static final zzir zzb = new zziu();
    private int zzc = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int zza(byte b2) {
        return b2 & 255;
    }

    public abstract boolean equals(Object obj);

    public abstract byte zza(int i2);

    public abstract zzik zza(int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(zzil zzilVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte zzb(int i2);

    public abstract int zzb();

    protected abstract int zzb(int i2, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i2, int i3, int i4) {
        int i5 = i3 - i2;
        if ((i2 | i3 | i5 | (i4 - i3)) < 0) {
            if (i2 >= 0) {
                if (i3 < i2) {
                    throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i2 + ", " + i3);
                }
                throw new IndexOutOfBoundsException("End index: " + i3 + " >= " + i4);
            }
            throw new IndexOutOfBoundsException("Beginning index: " + i2 + " < 0");
        }
        return i5;
    }

    public final int hashCode() {
        int i2 = this.zzc;
        if (i2 == 0) {
            int zzb2 = zzb();
            i2 = zzb(zzb2, 0, zzb2);
            if (i2 == 0) {
                i2 = 1;
            }
            this.zzc = i2;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zza() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzit zzc(int i2) {
        return new zzit(i2);
    }

    public static zzik zza(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    public static zzik zza(byte[] bArr, int i2, int i3) {
        zza(i2, i2 + i3, bArr.length);
        return new zziv(zzb.zza(bArr, i2, i3));
    }

    public static zzik zza(String str) {
        return new zziv(str.getBytes(zzjv.zza));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzik zzb(byte[] bArr) {
        return new zziv(bArr);
    }

    public final String toString() {
        String str;
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zzb());
        if (zzb() <= 50) {
            str = zzmg.zza(this);
        } else {
            str = zzmg.zza(zza(0, 47)) + "...";
        }
        objArr[2] = str;
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzin(this);
    }

    static {
        new zzim();
    }
}
