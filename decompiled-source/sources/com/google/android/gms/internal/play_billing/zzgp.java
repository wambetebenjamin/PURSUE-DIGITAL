package com.google.android.gms.internal.play_billing;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.RandomAccess;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzgp extends zzeh implements RandomAccess, zzfz {
    private static final long[] zza;
    private long[] zzb;
    private int zzc;

    static {
        long[] jArr = new long[0];
        zza = jArr;
        new zzgp(jArr, 0, false);
    }

    zzgp() {
        this(zza, 0, true);
    }

    private static int zzh(int i2) {
        return Math.max(((i2 * 3) / 2) + 1, 10);
    }

    private final String zzi(int i2) {
        int i3 = this.zzc;
        return "Index:" + i2 + ", Size:" + i3;
    }

    private final void zzj(int i2) {
        if (i2 < 0 || i2 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzi(i2));
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i2, Object obj) {
        int i3;
        long longValue = ((Long) obj).longValue();
        zza();
        if (i2 < 0 || i2 > (i3 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzi(i2));
        }
        int i4 = i2 + 1;
        long[] jArr = this.zzb;
        int length = jArr.length;
        if (i3 < length) {
            System.arraycopy(jArr, i2, jArr, i4, i3 - i2);
        } else {
            long[] jArr2 = new long[zzh(length)];
            System.arraycopy(this.zzb, 0, jArr2, 0, i2);
            System.arraycopy(this.zzb, i2, jArr2, i4, this.zzc - i2);
            this.zzb = jArr2;
        }
        this.zzb[i2] = longValue;
        this.zzc++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzga.zzb;
        Objects.requireNonNull(collection);
        if (!(collection instanceof zzgp)) {
            return super.addAll(collection);
        }
        zzgp zzgpVar = (zzgp) collection;
        int i2 = zzgpVar.zzc;
        if (i2 == 0) {
            return false;
        }
        int i3 = this.zzc;
        if (Integer.MAX_VALUE - i3 >= i2) {
            int i4 = i3 + i2;
            long[] jArr = this.zzb;
            if (i4 > jArr.length) {
                this.zzb = Arrays.copyOf(jArr, i4);
            }
            System.arraycopy(zzgpVar.zzb, 0, this.zzb, this.zzc, zzgpVar.zzc);
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
        if (obj instanceof zzgp) {
            zzgp zzgpVar = (zzgp) obj;
            if (this.zzc != zzgpVar.zzc) {
                return false;
            }
            long[] jArr = zzgpVar.zzb;
            for (int i2 = 0; i2 < this.zzc; i2++) {
                if (this.zzb[i2] != jArr[i2]) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i2) {
        zzj(i2);
        return Long.valueOf(this.zzb[i2]);
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.zzc; i3++) {
            long j2 = this.zzb[i3];
            byte[] bArr = zzga.zzb;
            i2 = (i2 * 31) + ((int) (j2 ^ (j2 >>> 32)));
        }
        return i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Long) {
            long longValue = ((Long) obj).longValue();
            int i2 = this.zzc;
            for (int i3 = 0; i3 < i2; i3++) {
                if (this.zzb[i3] == longValue) {
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
        zzj(i2);
        long[] jArr = this.zzb;
        long j2 = jArr[i2];
        if (i2 < this.zzc - 1) {
            System.arraycopy(jArr, i2 + 1, jArr, i2, (i3 - i2) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Long.valueOf(j2);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i2, int i3) {
        zza();
        if (i3 >= i2) {
            long[] jArr = this.zzb;
            System.arraycopy(jArr, i3, jArr, i2, this.zzc - i3);
            this.zzc -= i3 - i2;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i2, Object obj) {
        long longValue = ((Long) obj).longValue();
        zza();
        zzj(i2);
        long[] jArr = this.zzb;
        long j2 = jArr[i2];
        jArr[i2] = longValue;
        return Long.valueOf(j2);
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
        return new zzgp(i2 == 0 ? zza : Arrays.copyOf(this.zzb, i2), this.zzc, true);
    }

    public final long zze(int i2) {
        zzj(i2);
        return this.zzb[i2];
    }

    public final void zzf(long j2) {
        zza();
        int i2 = this.zzc;
        int length = this.zzb.length;
        if (i2 == length) {
            long[] jArr = new long[zzh(length)];
            System.arraycopy(this.zzb, 0, jArr, 0, this.zzc);
            this.zzb = jArr;
        }
        long[] jArr2 = this.zzb;
        int i3 = this.zzc;
        this.zzc = i3 + 1;
        jArr2[i3] = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzg(int i2) {
        int length = this.zzb.length;
        if (i2 <= length) {
            return;
        }
        if (length != 0) {
            while (length < i2) {
                length = zzh(length);
            }
            this.zzb = Arrays.copyOf(this.zzb, length);
            return;
        }
        this.zzb = new long[Math.max(i2, 10)];
    }

    private zzgp(long[] jArr, int i2, boolean z) {
        super(z);
        this.zzb = jArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzf(((Long) obj).longValue());
        return true;
    }
}
