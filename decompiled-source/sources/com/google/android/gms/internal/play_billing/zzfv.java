package com.google.android.gms.internal.play_billing;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.RandomAccess;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzfv extends zzeh implements RandomAccess, zzfy {
    private static final int[] zza;
    private static final zzfv zzb;
    private int[] zzc;
    private int zzd;

    static {
        int[] iArr = new int[0];
        zza = iArr;
        zzb = new zzfv(iArr, 0, false);
    }

    zzfv() {
        this(zza, 0, true);
    }

    public static zzfv zzf() {
        return zzb;
    }

    private static int zzi(int i2) {
        return Math.max(((i2 * 3) / 2) + 1, 10);
    }

    private final String zzj(int i2) {
        int i3 = this.zzd;
        return "Index:" + i2 + ", Size:" + i3;
    }

    private final void zzk(int i2) {
        if (i2 < 0 || i2 >= this.zzd) {
            throw new IndexOutOfBoundsException(zzj(i2));
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i2, Object obj) {
        int i3;
        int intValue = ((Integer) obj).intValue();
        zza();
        if (i2 < 0 || i2 > (i3 = this.zzd)) {
            throw new IndexOutOfBoundsException(zzj(i2));
        }
        int i4 = i2 + 1;
        int[] iArr = this.zzc;
        int length = iArr.length;
        if (i3 < length) {
            System.arraycopy(iArr, i2, iArr, i4, i3 - i2);
        } else {
            int[] iArr2 = new int[zzi(length)];
            System.arraycopy(this.zzc, 0, iArr2, 0, i2);
            System.arraycopy(this.zzc, i2, iArr2, i4, this.zzd - i2);
            this.zzc = iArr2;
        }
        this.zzc[i2] = intValue;
        this.zzd++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzga.zzb;
        Objects.requireNonNull(collection);
        if (!(collection instanceof zzfv)) {
            return super.addAll(collection);
        }
        zzfv zzfvVar = (zzfv) collection;
        int i2 = zzfvVar.zzd;
        if (i2 == 0) {
            return false;
        }
        int i3 = this.zzd;
        if (Integer.MAX_VALUE - i3 >= i2) {
            int i4 = i3 + i2;
            int[] iArr = this.zzc;
            if (i4 > iArr.length) {
                this.zzc = Arrays.copyOf(iArr, i4);
            }
            System.arraycopy(zzfvVar.zzc, 0, this.zzc, this.zzd, zzfvVar.zzd);
            this.zzd = i4;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzfv) {
            zzfv zzfvVar = (zzfv) obj;
            if (this.zzd != zzfvVar.zzd) {
                return false;
            }
            int[] iArr = zzfvVar.zzc;
            for (int i2 = 0; i2 < this.zzd; i2++) {
                if (this.zzc[i2] != iArr[i2]) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i2) {
        zzk(i2);
        return Integer.valueOf(this.zzc[i2]);
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.Collection, java.util.List
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
            int i2 = this.zzd;
            for (int i3 = 0; i3 < i2; i3++) {
                if (this.zzc[i3] == intValue) {
                    return i3;
                }
            }
            return -1;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i2) {
        int i3;
        zza();
        zzk(i2);
        int[] iArr = this.zzc;
        int i4 = iArr[i2];
        if (i2 < this.zzd - 1) {
            System.arraycopy(iArr, i2 + 1, iArr, i2, (i3 - i2) - 1);
        }
        this.zzd--;
        this.modCount++;
        return Integer.valueOf(i4);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i2, int i3) {
        zza();
        if (i3 >= i2) {
            int[] iArr = this.zzc;
            System.arraycopy(iArr, i3, iArr, i2, this.zzd - i3);
            this.zzd -= i3 - i2;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i2, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zza();
        zzk(i2);
        int[] iArr = this.zzc;
        int i3 = iArr[i2];
        iArr[i2] = intValue;
        return Integer.valueOf(i3);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.play_billing.zzfz
    public final /* bridge */ /* synthetic */ zzfz zzd(int i2) {
        if (i2 < this.zzd) {
            throw new IllegalArgumentException();
        }
        return new zzfv(i2 == 0 ? zza : Arrays.copyOf(this.zzc, i2), this.zzd, true);
    }

    public final int zze(int i2) {
        zzk(i2);
        return this.zzc[i2];
    }

    public final void zzg(int i2) {
        zza();
        int i3 = this.zzd;
        int length = this.zzc.length;
        if (i3 == length) {
            int[] iArr = new int[zzi(length)];
            System.arraycopy(this.zzc, 0, iArr, 0, this.zzd);
            this.zzc = iArr;
        }
        int[] iArr2 = this.zzc;
        int i4 = this.zzd;
        this.zzd = i4 + 1;
        iArr2[i4] = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzh(int i2) {
        int length = this.zzc.length;
        if (i2 <= length) {
            return;
        }
        if (length != 0) {
            while (length < i2) {
                length = zzi(length);
            }
            this.zzc = Arrays.copyOf(this.zzc, length);
            return;
        }
        this.zzc = new int[Math.max(i2, 10)];
    }

    private zzfv(int[] iArr, int i2, boolean z) {
        super(z);
        this.zzc = iArr;
        this.zzd = i2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzg(((Integer) obj).intValue());
        return true;
    }
}
