package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
abstract class zzie<E> extends AbstractList<E> implements zzkc<E> {
    private boolean zza;

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int size = size();
        int i2 = 1;
        for (int i3 = 0; i3 < size; i3++) {
            i2 = (i2 * 31) + get(i3).hashCode();
        }
        return i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public E remove(int i2) {
        zza();
        return (E) super.remove(i2);
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int i2, E e2) {
        zza();
        return (E) super.set(i2, e2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzie(boolean z) {
        this.zza = z;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i2, E e2) {
        zza();
        super.add(i2, e2);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        zza();
        super.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza() {
        if (!this.zza) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzkc
    public final void zzb() {
        if (this.zza) {
            this.zza = false;
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e2) {
        zza();
        return super.add(e2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> collection) {
        zza();
        return super.addAll(collection);
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i2, Collection<? extends E> collection) {
        zza();
        return super.addAll(i2, collection);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            if (!(obj instanceof RandomAccess)) {
                return super.equals(obj);
            }
            List list = (List) obj;
            int size = size();
            if (size != list.size()) {
                return false;
            }
            for (int i2 = 0; i2 < size; i2++) {
                if (!get(i2).equals(list.get(i2))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzkc
    public final boolean zzc() {
        return this.zza;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        zza();
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<?> collection) {
        zza();
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<?> collection) {
        zza();
        return super.retainAll(collection);
    }
}
