package com.google.android.gms.internal.measurement;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
public class zzlv<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private Object[] zza;
    private int zzb;
    private Map<K, V> zzc;
    private boolean zzd;
    private volatile zzmb zze;
    private Map<K, V> zzf;

    private final int zza(K k) {
        int i2 = this.zzb - 1;
        if (i2 >= 0) {
            int compareTo = k.compareTo((Comparable) ((zzlz) this.zza[i2]).getKey());
            if (compareTo > 0) {
                return -(i2 + 2);
            }
            if (compareTo == 0) {
                return i2;
            }
        }
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) / 2;
            int compareTo2 = k.compareTo((Comparable) ((zzlz) this.zza[i4]).getKey());
            if (compareTo2 < 0) {
                i2 = i4 - 1;
            } else if (compareTo2 <= 0) {
                return i4;
            } else {
                i3 = i4 + 1;
            }
        }
        return -(i3 + 1);
    }

    public final int zza() {
        return this.zzb;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int i2 = this.zzb;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += this.zza[i4].hashCode();
        }
        return this.zzc.size() > 0 ? i3 + this.zzc.hashCode() : i3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.zzb + this.zzc.size();
    }

    public final Iterable<Map.Entry<K, V>> zzb() {
        if (this.zzc.isEmpty()) {
            return Collections.emptySet();
        }
        return this.zzc.entrySet();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza((zzlv<K, V>) comparable);
        if (zza >= 0) {
            return (V) ((zzlz) this.zza[zza]).getValue();
        }
        return this.zzc.get(comparable);
    }

    public final V zza(K k, V v) {
        zzg();
        int zza = zza((zzlv<K, V>) k);
        if (zza >= 0) {
            return (V) ((zzlz) this.zza[zza]).setValue(v);
        }
        zzg();
        if (this.zza == null) {
            this.zza = new Object[16];
        }
        int i2 = -(zza + 1);
        if (i2 >= 16) {
            return zzf().put(k, v);
        }
        int i3 = this.zzb;
        if (i3 == 16) {
            zzlz zzlzVar = (zzlz) this.zza[15];
            this.zzb = i3 - 1;
            zzf().put((K) zzlzVar.getKey(), (V) zzlzVar.getValue());
        }
        Object[] objArr = this.zza;
        System.arraycopy(objArr, i2, objArr, i2 + 1, (objArr.length - i2) - 1);
        this.zza[i2] = new zzlz(this, k, v);
        this.zzb++;
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public /* synthetic */ Object put(Object obj, Object obj2) {
        return zza((zzlv<K, V>) ((Comparable) obj), (Comparable) obj2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        zzg();
        Comparable comparable = (Comparable) obj;
        int zza = zza((zzlv<K, V>) comparable);
        if (zza >= 0) {
            return (V) zzb(zza);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final V zzb(int i2) {
        zzg();
        V v = (V) ((zzlz) this.zza[i2]).getValue();
        Object[] objArr = this.zza;
        System.arraycopy(objArr, i2 + 1, objArr, i2, (this.zzb - i2) - 1);
        this.zzb--;
        if (!this.zzc.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = zzf().entrySet().iterator();
            this.zza[this.zzb] = new zzlz(this, it.next());
            this.zzb++;
            it.remove();
        }
        return v;
    }

    public final Map.Entry<K, V> zza(int i2) {
        if (i2 >= this.zzb) {
            throw new ArrayIndexOutOfBoundsException(i2);
        }
        return (zzlz) this.zza[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set<Map.Entry<K, V>> zzc() {
        return new zzma(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zze == null) {
            this.zze = new zzmb(this);
        }
        return this.zze;
    }

    private final SortedMap<K, V> zzf() {
        zzg();
        if (this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzc = treeMap;
            this.zzf = treeMap.descendingMap();
        }
        return (SortedMap) this.zzc;
    }

    private zzlv() {
        this.zzc = Collections.emptyMap();
        this.zzf = Collections.emptyMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzg() {
        if (this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        zzg();
        if (this.zzb != 0) {
            this.zza = null;
            this.zzb = 0;
        }
        if (this.zzc.isEmpty()) {
            return;
        }
        this.zzc.clear();
    }

    public void zzd() {
        Map<K, V> unmodifiableMap;
        Map<K, V> unmodifiableMap2;
        if (this.zzd) {
            return;
        }
        if (this.zzc.isEmpty()) {
            unmodifiableMap = Collections.emptyMap();
        } else {
            unmodifiableMap = Collections.unmodifiableMap(this.zzc);
        }
        this.zzc = unmodifiableMap;
        if (this.zzf.isEmpty()) {
            unmodifiableMap2 = Collections.emptyMap();
        } else {
            unmodifiableMap2 = Collections.unmodifiableMap(this.zzf);
        }
        this.zzf = unmodifiableMap2;
        this.zzd = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza((zzlv<K, V>) comparable) >= 0 || this.zzc.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzlv)) {
            return super.equals(obj);
        }
        zzlv zzlvVar = (zzlv) obj;
        int size = size();
        if (size != zzlvVar.size()) {
            return false;
        }
        int i2 = this.zzb;
        if (i2 != zzlvVar.zzb) {
            return entrySet().equals(zzlvVar.entrySet());
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (!zza(i3).equals(zzlvVar.zza(i3))) {
                return false;
            }
        }
        if (i2 != size) {
            return this.zzc.equals(zzlvVar.zzc);
        }
        return true;
    }

    public final boolean zze() {
        return this.zzd;
    }
}
