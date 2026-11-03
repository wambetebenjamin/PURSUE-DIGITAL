package com.google.android.gms.internal.play_billing;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public class zzht extends AbstractMap {
    private Object[] zza;
    private int zzb;
    private boolean zzd;
    private volatile zzhr zze;
    private Map zzc = Collections.emptyMap();
    private Map zzf = Collections.emptyMap();

    private zzht() {
    }

    private final int zzl(Comparable comparable) {
        int i2 = this.zzb - 1;
        int i3 = 0;
        if (i2 >= 0) {
            int compareTo = comparable.compareTo(((zzhp) this.zza[i2]).zza());
            if (compareTo > 0) {
                return -(i2 + 2);
            }
            if (compareTo == 0) {
                return i2;
            }
        }
        while (i3 <= i2) {
            int i4 = (i3 + i2) / 2;
            int compareTo2 = comparable.compareTo(((zzhp) this.zza[i4]).zza());
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

    /* JADX INFO: Access modifiers changed from: private */
    public final Object zzm(int i2) {
        zzo();
        Object value = ((zzhp) this.zza[i2]).getValue();
        Object[] objArr = this.zza;
        System.arraycopy(objArr, i2 + 1, objArr, i2, (this.zzb - i2) - 1);
        this.zzb--;
        if (!this.zzc.isEmpty()) {
            Iterator it = zzn().entrySet().iterator();
            Object[] objArr2 = this.zza;
            int i3 = this.zzb;
            Map.Entry entry = (Map.Entry) it.next();
            objArr2[i3] = new zzhp(this, (Comparable) entry.getKey(), entry.getValue());
            this.zzb++;
            it.remove();
        }
        return value;
    }

    private final SortedMap zzn() {
        zzo();
        if (this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzc = treeMap;
            this.zzf = treeMap.descendingMap();
        }
        return (SortedMap) this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzo() {
        if (this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzo();
        if (this.zzb != 0) {
            this.zza = null;
            this.zzb = 0;
        }
        if (this.zzc.isEmpty()) {
            return;
        }
        this.zzc.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zzl(comparable) >= 0 || this.zzc.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (this.zze == null) {
            this.zze = new zzhr(this, null);
        }
        return this.zze;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzht) {
            zzht zzhtVar = (zzht) obj;
            int size = size();
            if (size == zzhtVar.size()) {
                int i2 = this.zzb;
                if (i2 == zzhtVar.zzb) {
                    for (int i3 = 0; i3 < i2; i3++) {
                        if (!zzg(i3).equals(zzhtVar.zzg(i3))) {
                            return false;
                        }
                    }
                    if (i2 != size) {
                        return this.zzc.equals(zzhtVar.zzc);
                    }
                    return true;
                }
                return entrySet().equals(zzhtVar.entrySet());
            }
            return false;
        }
        return super.equals(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zzl = zzl(comparable);
        if (zzl >= 0) {
            return ((zzhp) this.zza[zzl]).getValue();
        }
        return this.zzc.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int i2 = this.zzb;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += this.zza[i4].hashCode();
        }
        return this.zzc.size() > 0 ? i3 + this.zzc.hashCode() : i3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        zzo();
        Comparable comparable = (Comparable) obj;
        int zzl = zzl(comparable);
        if (zzl >= 0) {
            return zzm(zzl);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.zzb + this.zzc.size();
    }

    public void zza() {
        Map unmodifiableMap;
        Map unmodifiableMap2;
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

    public final int zzc() {
        return this.zzb;
    }

    public final Iterable zzd() {
        if (this.zzc.isEmpty()) {
            return Collections.emptySet();
        }
        return this.zzc.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* renamed from: zzf */
    public final Object put(Comparable comparable, Object obj) {
        zzo();
        int zzl = zzl(comparable);
        if (zzl >= 0) {
            return ((zzhp) this.zza[zzl]).setValue(obj);
        }
        zzo();
        if (this.zza == null) {
            this.zza = new Object[16];
        }
        int i2 = -(zzl + 1);
        if (i2 >= 16) {
            return zzn().put(comparable, obj);
        }
        if (this.zzb == 16) {
            zzhp zzhpVar = (zzhp) this.zza[15];
            this.zzb = 15;
            zzn().put(zzhpVar.zza(), zzhpVar.getValue());
        }
        Object[] objArr = this.zza;
        int length = objArr.length;
        System.arraycopy(objArr, i2, objArr, i2 + 1, (16 - i2) - 1);
        this.zza[i2] = new zzhp(this, comparable, obj);
        this.zzb++;
        return null;
    }

    public final Map.Entry zzg(int i2) {
        if (i2 >= this.zzb) {
            throw new ArrayIndexOutOfBoundsException(i2);
        }
        return (zzhp) this.zza[i2];
    }

    public final boolean zzj() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzht(zzhs zzhsVar) {
    }
}
