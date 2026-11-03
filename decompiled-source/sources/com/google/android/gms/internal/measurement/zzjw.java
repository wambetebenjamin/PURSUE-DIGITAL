package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzjw extends zzie<Integer> implements zzka, zzlo, RandomAccess {
    private static final int[] zza;
    private static final zzjw zzb;
    private int[] zzc;
    private int zzd;

    public final int zzb(int i2) {
        zzh(i2);
        return this.zzc[i2];
    }

    private static int zzf(int i2) {
        return Math.max(((i2 * 3) / 2) + 1, 10);
    }

    @Override // com.google.android.gms.internal.measurement.zzie, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.zzd; i3++) {
            i2 = (i2 * 31) + this.zzc[i3];
        }
        return i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            int size = size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.zzc[i2] == intValue) {
                    return i2;
                }
            }
            return -1;
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzd;
    }

    public static zzjw zzd() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzkc
    /* renamed from: zzc */
    public final zzka zza(int i2) {
        if (i2 < this.zzd) {
            throw new IllegalArgumentException();
        }
        return new zzjw(i2 == 0 ? zza : Arrays.copyOf(this.zzc, i2), this.zzd, true);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i2) {
        return Integer.valueOf(zzb(i2));
    }

    @Override // com.google.android.gms.internal.measurement.zzie, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i2) {
        int i3;
        zza();
        zzh(i2);
        int[] iArr = this.zzc;
        int i4 = iArr[i2];
        if (i2 < this.zzd - 1) {
            System.arraycopy(iArr, i2 + 1, iArr, i2, (i3 - i2) - 1);
        }
        this.zzd--;
        this.modCount++;
        return Integer.valueOf(i4);
    }

    @Override // com.google.android.gms.internal.measurement.zzie, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i2, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zza();
        zzh(i2);
        int[] iArr = this.zzc;
        int i3 = iArr[i2];
        iArr[i2] = intValue;
        return Integer.valueOf(i3);
    }

    private final String zzg(int i2) {
        return "Index:" + i2 + ", Size:" + this.zzd;
    }

    static {
        int[] iArr = new int[0];
        zza = iArr;
        zzb = new zzjw(iArr, 0, false);
    }

    zzjw() {
        this(zza, 0, true);
    }

    private zzjw(int[] iArr, int i2, boolean z) {
        super(z);
        this.zzc = iArr;
        this.zzd = i2;
    }

    @Override // com.google.android.gms.internal.measurement.zzie, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i2, Object obj) {
        int i3;
        int intValue = ((Integer) obj).intValue();
        zza();
        if (i2 < 0 || i2 > (i3 = this.zzd)) {
            throw new IndexOutOfBoundsException(zzg(i2));
        }
        int[] iArr = this.zzc;
        if (i3 < iArr.length) {
            System.arraycopy(iArr, i2, iArr, i2 + 1, i3 - i2);
        } else {
            int[] iArr2 = new int[zzf(iArr.length)];
            System.arraycopy(this.zzc, 0, iArr2, 0, i2);
            System.arraycopy(this.zzc, i2, iArr2, i2 + 1, this.zzd - i2);
            this.zzc = iArr2;
        }
        this.zzc[i2] = intValue;
        this.zzd++;
        this.modCount++;
    }

    public final void zzd(int i2) {
        zza();
        int i3 = this.zzd;
        int[] iArr = this.zzc;
        if (i3 == iArr.length) {
            int[] iArr2 = new int[zzf(iArr.length)];
            System.arraycopy(this.zzc, 0, iArr2, 0, this.zzd);
            this.zzc = iArr2;
        }
        int[] iArr3 = this.zzc;
        int i4 = this.zzd;
        this.zzd = i4 + 1;
        iArr3[i4] = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zze(int i2) {
        int[] iArr = this.zzc;
        if (i2 <= iArr.length) {
            return;
        }
        if (iArr.length == 0) {
            this.zzc = new int[Math.max(i2, 10)];
            return;
        }
        int length = iArr.length;
        while (length < i2) {
            length = zzf(length);
        }
        this.zzc = Arrays.copyOf(this.zzc, length);
    }

    private final void zzh(int i2) {
        if (i2 < 0 || i2 >= this.zzd) {
            throw new IndexOutOfBoundsException(zzg(i2));
        }
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i2, int i3) {
        zza();
        if (i3 < i2) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        int[] iArr = this.zzc;
        System.arraycopy(iArr, i3, iArr, i2, this.zzd - i3);
        this.zzd -= i3 - i2;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzie, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        zzd(((Integer) obj).intValue());
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzie, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Integer> collection) {
        zza();
        zzjv.zza(collection);
        if (!(collection instanceof zzjw)) {
            return super.addAll(collection);
        }
        zzjw zzjwVar = (zzjw) collection;
        int i2 = zzjwVar.zzd;
        if (i2 == 0) {
            return false;
        }
        int i3 = this.zzd;
        if (Integer.MAX_VALUE - i3 < i2) {
            throw new OutOfMemoryError();
        }
        int i4 = i3 + i2;
        int[] iArr = this.zzc;
        if (i4 > iArr.length) {
            this.zzc = Arrays.copyOf(iArr, i4);
        }
        System.arraycopy(zzjwVar.zzc, 0, this.zzc, this.zzd, zzjwVar.zzd);
        this.zzd = i4;
        this.modCount++;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzie, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzjw)) {
            return super.equals(obj);
        }
        zzjw zzjwVar = (zzjw) obj;
        if (this.zzd != zzjwVar.zzd) {
            return false;
        }
        int[] iArr = zzjwVar.zzc;
        for (int i2 = 0; i2 < this.zzd; i2++) {
            if (this.zzc[i2] != iArr[i2]) {
                return false;
            }
        }
        return true;
    }
}
