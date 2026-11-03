package com.google.android.gms.internal.play_billing;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.RandomAccess;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzfo extends zzeh implements RandomAccess, zzfz {
    private static final float[] zza;
    private float[] zzb;
    private int zzc;

    static {
        float[] fArr = new float[0];
        zza = fArr;
        new zzfo(fArr, 0, false);
    }

    zzfo() {
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
        float floatValue = ((Float) obj).floatValue();
        zza();
        if (i2 < 0 || i2 > (i3 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzi(i2));
        }
        int i4 = i2 + 1;
        float[] fArr = this.zzb;
        int length = fArr.length;
        if (i3 < length) {
            System.arraycopy(fArr, i2, fArr, i4, i3 - i2);
        } else {
            float[] fArr2 = new float[zzh(length)];
            System.arraycopy(this.zzb, 0, fArr2, 0, i2);
            System.arraycopy(this.zzb, i2, fArr2, i4, this.zzc - i2);
            this.zzb = fArr2;
        }
        this.zzb[i2] = floatValue;
        this.zzc++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzga.zzb;
        Objects.requireNonNull(collection);
        if (!(collection instanceof zzfo)) {
            return super.addAll(collection);
        }
        zzfo zzfoVar = (zzfo) collection;
        int i2 = zzfoVar.zzc;
        if (i2 == 0) {
            return false;
        }
        int i3 = this.zzc;
        if (Integer.MAX_VALUE - i3 >= i2) {
            int i4 = i3 + i2;
            float[] fArr = this.zzb;
            if (i4 > fArr.length) {
                this.zzb = Arrays.copyOf(fArr, i4);
            }
            System.arraycopy(zzfoVar.zzb, 0, this.zzb, this.zzc, zzfoVar.zzc);
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
        if (obj instanceof zzfo) {
            zzfo zzfoVar = (zzfo) obj;
            if (this.zzc != zzfoVar.zzc) {
                return false;
            }
            float[] fArr = zzfoVar.zzb;
            for (int i2 = 0; i2 < this.zzc; i2++) {
                if (Float.floatToIntBits(this.zzb[i2]) != Float.floatToIntBits(fArr[i2])) {
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
        return Float.valueOf(this.zzb[i2]);
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.zzc; i3++) {
            i2 = (i2 * 31) + Float.floatToIntBits(this.zzb[i3]);
        }
        return i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Float) {
            float floatValue = ((Float) obj).floatValue();
            int i2 = this.zzc;
            for (int i3 = 0; i3 < i2; i3++) {
                if (this.zzb[i3] == floatValue) {
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
        float[] fArr = this.zzb;
        float f2 = fArr[i2];
        if (i2 < this.zzc - 1) {
            System.arraycopy(fArr, i2 + 1, fArr, i2, (i3 - i2) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Float.valueOf(f2);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i2, int i3) {
        zza();
        if (i3 >= i2) {
            float[] fArr = this.zzb;
            System.arraycopy(fArr, i3, fArr, i2, this.zzc - i3);
            this.zzc -= i3 - i2;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i2, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zza();
        zzj(i2);
        float[] fArr = this.zzb;
        float f2 = fArr[i2];
        fArr[i2] = floatValue;
        return Float.valueOf(f2);
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
        return new zzfo(i2 == 0 ? zza : Arrays.copyOf(this.zzb, i2), this.zzc, true);
    }

    public final float zze(int i2) {
        zzj(i2);
        return this.zzb[i2];
    }

    public final void zzf(float f2) {
        zza();
        int i2 = this.zzc;
        int length = this.zzb.length;
        if (i2 == length) {
            float[] fArr = new float[zzh(length)];
            System.arraycopy(this.zzb, 0, fArr, 0, this.zzc);
            this.zzb = fArr;
        }
        float[] fArr2 = this.zzb;
        int i3 = this.zzc;
        this.zzc = i3 + 1;
        fArr2[i3] = f2;
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
        this.zzb = new float[Math.max(i2, 10)];
    }

    private zzfo(float[] fArr, int i2, boolean z) {
        super(z);
        this.zzb = fArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeh, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzf(((Float) obj).floatValue());
        return true;
    }
}
