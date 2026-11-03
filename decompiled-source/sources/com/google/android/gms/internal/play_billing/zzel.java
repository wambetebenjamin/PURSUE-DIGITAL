package com.google.android.gms.internal.play_billing;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.RandomAccess;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzel extends zzeh implements RandomAccess, zzfz {
    private static final boolean[] zza;
    private boolean[] zzb;
    private int zzc;

    static {
        boolean[] zArr = new boolean[0];
        zza = zArr;
        new zzel(zArr, 0, false);
    }

    zzel() {
        this(zza, 0, true);
    }

    private static int zzg(int i2) {
        return Math.max(((i2 * 3) / 2) + 1, 10);
    }

    private final String zzh(int i2) {
        int i3 = this.zzc;
        return "Index:" + i2 + ", Size:" + i3;
    }

    private final void zzi(int i2) {
        if (i2 < 0 || i2 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzh(i2));
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i2, Object obj) {
        int i3;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zza();
        if (i2 < 0 || i2 > (i3 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzh(i2));
        }
        int i4 = i2 + 1;
        boolean[] zArr = this.zzb;
        int length = zArr.length;
        if (i3 < length) {
            System.arraycopy(zArr, i2, zArr, i4, i3 - i2);
        } else {
            boolean[] zArr2 = new boolean[zzg(length)];
            System.arraycopy(this.zzb, 0, zArr2, 0, i2);
            System.arraycopy(this.zzb, i2, zArr2, i4, this.zzc - i2);
            this.zzb = zArr2;
        }
        this.zzb[i2] = booleanValue;
        this.zzc++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzga.zzb;
        Objects.requireNonNull(collection);
        if (!(collection instanceof zzel)) {
            return super.addAll(collection);
        }
        zzel zzelVar = (zzel) collection;
        int i2 = zzelVar.zzc;
        if (i2 == 0) {
            return false;
        }
        int i3 = this.zzc;
        if (Integer.MAX_VALUE - i3 >= i2) {
            int i4 = i3 + i2;
            boolean[] zArr = this.zzb;
            if (i4 > zArr.length) {
                this.zzb = Arrays.copyOf(zArr, i4);
            }
            System.arraycopy(zzelVar.zzb, 0, this.zzb, this.zzc, zzelVar.zzc);
            this.zzc = i4;
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
        if (obj instanceof zzel) {
            zzel zzelVar = (zzel) obj;
            if (this.zzc != zzelVar.zzc) {
                return false;
            }
            boolean[] zArr = zzelVar.zzb;
            for (int i2 = 0; i2 < this.zzc; i2++) {
                if (this.zzb[i2] != zArr[i2]) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i2) {
        zzi(i2);
        return Boolean.valueOf(this.zzb[i2]);
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.zzc; i3++) {
            i2 = (i2 * 31) + zzga.zza(this.zzb[i3]);
        }
        return i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Boolean) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            int i2 = this.zzc;
            for (int i3 = 0; i3 < i2; i3++) {
                if (this.zzb[i3] == booleanValue) {
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
        zzi(i2);
        boolean[] zArr = this.zzb;
        boolean z = zArr[i2];
        if (i2 < this.zzc - 1) {
            System.arraycopy(zArr, i2 + 1, zArr, i2, (i3 - i2) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i2, int i3) {
        zza();
        if (i3 >= i2) {
            boolean[] zArr = this.zzb;
            System.arraycopy(zArr, i3, zArr, i2, this.zzc - i3);
            this.zzc -= i3 - i2;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i2, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zza();
        zzi(i2);
        boolean[] zArr = this.zzb;
        boolean z = zArr[i2];
        zArr[i2] = booleanValue;
        return Boolean.valueOf(z);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzfz
    public final /* bridge */ /* synthetic */ zzfz zzd(int i2) {
        if (i2 < this.zzc) {
            throw new IllegalArgumentException();
        }
        return new zzel(i2 == 0 ? zza : Arrays.copyOf(this.zzb, i2), this.zzc, true);
    }

    public final void zze(boolean z) {
        zza();
        int i2 = this.zzc;
        int length = this.zzb.length;
        if (i2 == length) {
            boolean[] zArr = new boolean[zzg(length)];
            System.arraycopy(this.zzb, 0, zArr, 0, this.zzc);
            this.zzb = zArr;
        }
        boolean[] zArr2 = this.zzb;
        int i3 = this.zzc;
        this.zzc = i3 + 1;
        zArr2[i3] = z;
    }

    public final boolean zzf(int i2) {
        zzi(i2);
        return this.zzb[i2];
    }

    private zzel(boolean[] zArr, int i2, boolean z) {
        super(z);
        this.zzb = zArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zze(((Boolean) obj).booleanValue());
        return true;
    }
}
