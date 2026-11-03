package com.google.android.gms.internal.play_billing;

import java.util.Arrays;
import java.util.RandomAccess;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzhj extends zzeh implements RandomAccess {
    private static final Object[] zza;
    private static final zzhj zzb;
    private Object[] zzc;
    private int zzd;

    static {
        Object[] objArr = new Object[0];
        zza = objArr;
        zzb = new zzhj(objArr, 0, false);
    }

    zzhj() {
        this(zza, 0, true);
    }

    public static zzhj zze() {
        return zzb;
    }

    private static int zzg(int i2) {
        return Math.max(((i2 * 3) / 2) + 1, 10);
    }

    private final String zzh(int i2) {
        int i3 = this.zzd;
        return "Index:" + i2 + ", Size:" + i3;
    }

    private final void zzi(int i2) {
        if (i2 < 0 || i2 >= this.zzd) {
            throw new IndexOutOfBoundsException(zzh(i2));
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.List
    public final void add(int i2, Object obj) {
        int i3;
        zza();
        if (i2 < 0 || i2 > (i3 = this.zzd)) {
            throw new IndexOutOfBoundsException(zzh(i2));
        }
        int i4 = i2 + 1;
        Object[] objArr = this.zzc;
        int length = objArr.length;
        if (i3 < length) {
            System.arraycopy(objArr, i2, objArr, i4, i3 - i2);
        } else {
            Object[] objArr2 = new Object[zzg(length)];
            System.arraycopy(this.zzc, 0, objArr2, 0, i2);
            System.arraycopy(this.zzc, i2, objArr2, i4, this.zzd - i2);
            this.zzc = objArr2;
        }
        this.zzc[i2] = obj;
        this.zzd++;
        this.modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i2) {
        zzi(i2);
        return this.zzc[i2];
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.List
    public final Object remove(int i2) {
        int i3;
        zza();
        zzi(i2);
        Object[] objArr = this.zzc;
        Object obj = objArr[i2];
        if (i2 < this.zzd - 1) {
            System.arraycopy(objArr, i2 + 1, objArr, i2, (i3 - i2) - 1);
        }
        this.zzd--;
        this.modCount++;
        return obj;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.List
    public final Object set(int i2, Object obj) {
        zza();
        zzi(i2);
        Object[] objArr = this.zzc;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        this.modCount++;
        return obj2;
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
        return new zzhj(i2 == 0 ? zza : Arrays.copyOf(this.zzc, i2), this.zzd, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzf(int i2) {
        int length = this.zzc.length;
        if (i2 <= length) {
            return;
        }
        if (length != 0) {
            while (length < i2) {
                length = zzg(length);
            }
            this.zzc = Arrays.copyOf(this.zzc, length);
            return;
        }
        this.zzc = new Object[Math.max(i2, 10)];
    }

    private zzhj(Object[] objArr, int i2, boolean z) {
        super(z);
        this.zzc = objArr;
        this.zzd = i2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        zza();
        int i2 = this.zzd;
        int length = this.zzc.length;
        if (i2 == length) {
            this.zzc = Arrays.copyOf(this.zzc, zzg(length));
        }
        Object[] objArr = this.zzc;
        int i3 = this.zzd;
        this.zzd = i3 + 1;
        objArr[i3] = obj;
        this.modCount++;
        return true;
    }
}
