package com.google.android.gms.internal.measurement;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzaf implements zzak, zzaq, Iterable<zzaq> {
    private final SortedMap<Integer, zzaq> zza;
    private final Map<String, zzaq> zzb;

    public final int zza() {
        return this.zza.size();
    }

    public final int hashCode() {
        return this.zza.hashCode() * 31;
    }

    public final int zzb() {
        if (this.zza.isEmpty()) {
            return 0;
        }
        return this.zza.lastKey().intValue() + 1;
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final zzaq zza(String str, zzh zzhVar, List<zzaq> list) {
        if ("concat".equals(str) || "every".equals(str) || "filter".equals(str) || "forEach".equals(str) || "indexOf".equals(str) || "join".equals(str) || "lastIndexOf".equals(str) || "map".equals(str) || "pop".equals(str) || "push".equals(str) || "reduce".equals(str) || "reduceRight".equals(str) || "reverse".equals(str) || "shift".equals(str) || "slice".equals(str) || "some".equals(str) || "sort".equals(str) || "splice".equals(str) || "toString".equals(str) || "unshift".equals(str)) {
            return zzbe.zza(str, this, zzhVar, list);
        }
        return zzan.zza(this, new zzas(str), zzhVar, list);
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final zzaq zzc() {
        zzaf zzafVar = new zzaf();
        for (Map.Entry<Integer, zzaq> entry : this.zza.entrySet()) {
            if (entry.getValue() instanceof zzak) {
                zzafVar.zza.put(entry.getKey(), entry.getValue());
            } else {
                zzafVar.zza.put(entry.getKey(), entry.getValue().zzc());
            }
        }
        return zzafVar;
    }

    public final zzaq zza(int i2) {
        zzaq zzaqVar;
        if (i2 < zzb()) {
            return (!zzc(i2) || (zzaqVar = this.zza.get(Integer.valueOf(i2))) == null) ? zzc : zzaqVar;
        }
        throw new IndexOutOfBoundsException("Attempting to get element outside of current array");
    }

    @Override // com.google.android.gms.internal.measurement.zzak
    public final zzaq zza(String str) {
        zzaq zzaqVar;
        if ("length".equals(str)) {
            return new zzai(Double.valueOf(zzb()));
        }
        return (!zzc(str) || (zzaqVar = this.zzb.get(str)) == null) ? zzc : zzaqVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final Boolean zzd() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final Double zze() {
        if (this.zza.size() == 1) {
            return zza(0).zze();
        }
        if (this.zza.size() <= 0) {
            return Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
        return Double.valueOf(Double.NaN);
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final String zzf() {
        return toString();
    }

    public final String toString() {
        return zzb(",");
    }

    public final String zzb(String str) {
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        if (!this.zza.isEmpty()) {
            for (int i2 = 0; i2 < zzb(); i2++) {
                zzaq zza = zza(i2);
                sb.append(str);
                if (!(zza instanceof zzax) && !(zza instanceof zzao)) {
                    sb.append(zza.zzf());
                }
            }
            sb.delete(0, str.length());
        }
        return sb.toString();
    }

    public final Iterator<Integer> zzg() {
        return this.zza.keySet().iterator();
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final Iterator<zzaq> zzh() {
        return new zzae(this, this.zza.keySet().iterator(), this.zzb.keySet().iterator());
    }

    @Override // java.lang.Iterable
    public final Iterator<zzaq> iterator() {
        return new zzah(this);
    }

    public final List<zzaq> zzi() {
        ArrayList arrayList = new ArrayList(zzb());
        for (int i2 = 0; i2 < zzb(); i2++) {
            arrayList.add(zza(i2));
        }
        return arrayList;
    }

    public zzaf() {
        this.zza = new TreeMap();
        this.zzb = new TreeMap();
    }

    public zzaf(List<zzaq> list) {
        this();
        if (list != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                zzb(i2, list.get(i2));
            }
        }
    }

    public zzaf(zzaq... zzaqVarArr) {
        this(Arrays.asList(zzaqVarArr));
    }

    public final void zza(zzaq zzaqVar) {
        zzb(zzb(), zzaqVar);
    }

    public final void zzj() {
        this.zza.clear();
    }

    public final void zza(int i2, zzaq zzaqVar) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Invalid value index: " + i2);
        }
        if (i2 >= zzb()) {
            zzb(i2, zzaqVar);
            return;
        }
        for (int intValue = this.zza.lastKey().intValue(); intValue >= i2; intValue--) {
            zzaq zzaqVar2 = this.zza.get(Integer.valueOf(intValue));
            if (zzaqVar2 != null) {
                zzb(intValue + 1, zzaqVar2);
                this.zza.remove(Integer.valueOf(intValue));
            }
        }
        zzb(i2, zzaqVar);
    }

    public final void zzb(int i2) {
        int intValue = this.zza.lastKey().intValue();
        if (i2 > intValue || i2 < 0) {
            return;
        }
        this.zza.remove(Integer.valueOf(i2));
        if (i2 == intValue) {
            int i3 = i2 - 1;
            if (this.zza.containsKey(Integer.valueOf(i3)) || i3 < 0) {
                return;
            }
            this.zza.put(Integer.valueOf(i3), zzaq.zzc);
            return;
        }
        while (true) {
            i2++;
            if (i2 > this.zza.lastKey().intValue()) {
                return;
            }
            zzaq zzaqVar = this.zza.get(Integer.valueOf(i2));
            if (zzaqVar != null) {
                this.zza.put(Integer.valueOf(i2 - 1), zzaqVar);
                this.zza.remove(Integer.valueOf(i2));
            }
        }
    }

    @RequiresNonNull({"elements"})
    public final void zzb(int i2, zzaq zzaqVar) {
        if (i2 > 32468) {
            throw new IllegalStateException("Array too large");
        }
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("Out of bounds index: " + i2);
        }
        if (zzaqVar == null) {
            this.zza.remove(Integer.valueOf(i2));
        } else {
            this.zza.put(Integer.valueOf(i2), zzaqVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzak
    public final void zza(String str, zzaq zzaqVar) {
        if (zzaqVar == null) {
            this.zzb.remove(str);
        } else {
            this.zzb.put(str, zzaqVar);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzaf) {
            zzaf zzafVar = (zzaf) obj;
            if (zzb() != zzafVar.zzb()) {
                return false;
            }
            if (this.zza.isEmpty()) {
                return zzafVar.zza.isEmpty();
            }
            for (int intValue = this.zza.firstKey().intValue(); intValue <= this.zza.lastKey().intValue(); intValue++) {
                if (!zza(intValue).equals(zzafVar.zza(intValue))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public final boolean zzc(int i2) {
        if (i2 < 0 || i2 > this.zza.lastKey().intValue()) {
            throw new IndexOutOfBoundsException("Out of bounds index: " + i2);
        }
        return this.zza.containsKey(Integer.valueOf(i2));
    }

    @Override // com.google.android.gms.internal.measurement.zzak
    public final boolean zzc(String str) {
        return "length".equals(str) || this.zzb.containsKey(str);
    }
}
