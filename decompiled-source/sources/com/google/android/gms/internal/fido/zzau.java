package com.google.android.gms.internal.fido;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;
/* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
/* loaded from: classes5.dex */
public abstract class zzau extends zzaq implements Set {
    @CheckForNull
    private transient zzat zza;

    static int zzf(int i2) {
        int max = Math.max(i2, 2);
        if (max >= 751619276) {
            if (max < 1073741824) {
                return 1073741824;
            }
            throw new IllegalArgumentException("collection too large");
        }
        int highestOneBit = Integer.highestOneBit(max - 1);
        do {
            highestOneBit += highestOneBit;
        } while (highestOneBit * 0.7d < max);
        return highestOneBit;
    }

    public static zzau zzi(Object obj, Object obj2) {
        return zzk(2, obj, obj2);
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzau) && zzj() && ((zzau) obj).zzj() && hashCode() != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        Iterator it = iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i2 += next != null ? next.hashCode() : 0;
        }
        return i2;
    }

    @Override // com.google.android.gms.internal.fido.zzaq, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: zzd */
    public abstract zzaz iterator();

    public final zzat zzg() {
        zzat zzatVar = this.zza;
        if (zzatVar == null) {
            zzat zzh = zzh();
            this.zza = zzh;
            return zzh;
        }
        return zzatVar;
    }

    zzat zzh() {
        Object[] array = toArray();
        int i2 = zzat.zzd;
        return zzat.zzg(array, array.length);
    }

    boolean zzj() {
        return false;
    }

    private static zzau zzk(int i2, Object... objArr) {
        if (i2 != 0) {
            if (i2 != 1) {
                int zzf = zzf(i2);
                Object[] objArr2 = new Object[zzf];
                int i3 = zzf - 1;
                int i4 = 0;
                int i5 = 0;
                for (int i6 = 0; i6 < i2; i6++) {
                    Object obj = objArr[i6];
                    if (obj == null) {
                        throw new NullPointerException("at index " + i6);
                    }
                    int hashCode = obj.hashCode();
                    int zza = zzap.zza(hashCode);
                    while (true) {
                        int i7 = zza & i3;
                        Object obj2 = objArr2[i7];
                        if (obj2 != null) {
                            if (!obj2.equals(obj)) {
                                zza++;
                            }
                        } else {
                            objArr[i5] = obj;
                            objArr2[i7] = obj;
                            i4 += hashCode;
                            i5++;
                            break;
                        }
                    }
                }
                Arrays.fill(objArr, i5, i2, (Object) null);
                if (i5 == 1) {
                    Object obj3 = objArr[0];
                    obj3.getClass();
                    return new zzay(obj3);
                }
                if (zzf(i5) >= zzf / 2) {
                    if (i5 <= 0) {
                        objArr = Arrays.copyOf(objArr, i5);
                    }
                    return new zzax(objArr, i4, objArr2, i3, i5);
                }
                return zzk(i5, objArr);
            }
            Object obj4 = objArr[0];
            obj4.getClass();
            return new zzay(obj4);
        }
        return zzax.zza;
    }
}
