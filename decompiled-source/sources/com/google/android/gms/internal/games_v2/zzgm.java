package com.google.android.gms.internal.games_v2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.RandomAccess;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public abstract class zzgm extends zzgi implements List, RandomAccess {
    private static final zzha zza = new zzgk(zzgs.zza, 0);
    public static final /* synthetic */ int zzd = 0;

    public static zzgm zzg() {
        return zzgs.zza;
    }

    public static zzgm zzh(Object obj) {
        Object[] objArr = {obj};
        zzgr.zza(objArr, 1);
        return zzj(objArr, 1);
    }

    public static zzgm zzi(Collection collection) {
        Object[] array = collection.toArray();
        int length = array.length;
        zzgr.zza(array, length);
        return zzj(array, length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgm zzj(Object[] objArr, int i2) {
        if (i2 == 0) {
            return zzgs.zza;
        }
        return new zzgs(objArr, i2);
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i2, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int i2, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.games_v2.zzgi, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int i2 = 0; i2 < size; i2++) {
                        if (Objects.equals(get(i2), list.get(i2))) {
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it2.hasNext()) {
                            if (!Objects.equals(it.next(), it2.next())) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else if (!it2.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int i2 = 1;
        for (int i3 = 0; i3 < size; i3++) {
            i2 = (i2 * 31) + get(i3).hashCode();
        }
        return i2;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (obj.equals(get(i2))) {
                return i2;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.games_v2.zzgi, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    @Deprecated
    public final Object remove(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final Object set(int i2, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.games_v2.zzgi
    public final zzgz zza() {
        return listIterator(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.games_v2.zzgi
    public int zze(Object[] objArr, int i2) {
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            objArr[i3] = get(i3);
        }
        return size;
    }

    @Override // java.util.List
    /* renamed from: zzf */
    public zzgm subList(int i2, int i3) {
        zzfz.zzc(i2, i3, size());
        int i4 = i3 - i2;
        if (i4 == size()) {
            return this;
        }
        if (i4 != 0) {
            return new zzgl(this, i2, i4);
        }
        return zzgs.zza;
    }

    @Override // java.util.List
    /* renamed from: zzk */
    public final zzha listIterator(int i2) {
        zzfz.zzb(i2, size(), "index");
        return isEmpty() ? zza : new zzgk(this, i2);
    }
}
