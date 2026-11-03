package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.RandomAccess;
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
final class zzlp<E> extends zzie<E> implements RandomAccess {
    private static final Object[] zza;
    private static final zzlp<Object> zzb;
    private E[] zzc;
    private int zzd;

    private static int zzc(int i2) {
        return Math.max(((i2 * 3) / 2) + 1, 10);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzkc
    public final /* synthetic */ zzkc zza(int i2) {
        if (i2 < this.zzd) {
            throw new IllegalArgumentException();
        }
        return new zzlp(i2 == 0 ? zza : Arrays.copyOf(this.zzc, i2), this.zzd, true);
    }

    public static <E> zzlp<E> zzd() {
        return (zzlp<E>) zzb;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int i2) {
        zze(i2);
        return this.zzc[i2];
    }

    @Override // com.google.android.gms.internal.measurement.zzie, java.util.AbstractList, java.util.List
    public final E remove(int i2) {
        int i3;
        zza();
        zze(i2);
        E[] eArr = this.zzc;
        E e2 = eArr[i2];
        if (i2 < this.zzd - 1) {
            System.arraycopy(eArr, i2 + 1, eArr, i2, (i3 - i2) - 1);
        }
        this.zzd--;
        this.modCount++;
        return e2;
    }

    @Override // com.google.android.gms.internal.measurement.zzie, java.util.AbstractList, java.util.List
    public final E set(int i2, E e2) {
        zza();
        zze(i2);
        E[] eArr = this.zzc;
        E e3 = eArr[i2];
        eArr[i2] = e2;
        this.modCount++;
        return e3;
    }

    private final String zzd(int i2) {
        return "Index:" + i2 + ", Size:" + this.zzd;
    }

    static {
        Object[] objArr = new Object[0];
        zza = objArr;
        zzb = new zzlp<>(objArr, 0, false);
    }

    zzlp() {
        this(zza, 0, true);
    }

    private zzlp(E[] eArr, int i2, boolean z) {
        super(z);
        this.zzc = eArr;
        this.zzd = i2;
    }

    @Override // com.google.android.gms.internal.measurement.zzie, java.util.AbstractList, java.util.List
    public final void add(int i2, E e2) {
        int i3;
        zza();
        if (i2 < 0 || i2 > (i3 = this.zzd)) {
            throw new IndexOutOfBoundsException(zzd(i2));
        }
        E[] eArr = this.zzc;
        if (i3 < eArr.length) {
            System.arraycopy(eArr, i2, eArr, i2 + 1, i3 - i2);
        } else {
            E[] eArr2 = (E[]) new Object[zzc(eArr.length)];
            System.arraycopy(this.zzc, 0, eArr2, 0, i2);
            System.arraycopy(this.zzc, i2, eArr2, i2 + 1, this.zzd - i2);
            this.zzc = eArr2;
        }
        this.zzc[i2] = e2;
        this.zzd++;
        this.modCount++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(int i2) {
        E[] eArr = this.zzc;
        if (i2 <= eArr.length) {
            return;
        }
        if (eArr.length == 0) {
            this.zzc = (E[]) new Object[Math.max(i2, 10)];
            return;
        }
        int length = eArr.length;
        while (length < i2) {
            length = zzc(length);
        }
        this.zzc = (E[]) Arrays.copyOf(this.zzc, length);
    }

    private final void zze(int i2) {
        if (i2 < 0 || i2 >= this.zzd) {
            throw new IndexOutOfBoundsException(zzd(i2));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzie, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(E e2) {
        zza();
        int i2 = this.zzd;
        E[] eArr = this.zzc;
        if (i2 == eArr.length) {
            this.zzc = (E[]) Arrays.copyOf(this.zzc, zzc(eArr.length));
        }
        E[] eArr2 = this.zzc;
        int i3 = this.zzd;
        this.zzd = i3 + 1;
        eArr2[i3] = e2;
        this.modCount++;
        return true;
    }
}
