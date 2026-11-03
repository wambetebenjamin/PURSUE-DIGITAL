package com.google.android.gms.internal.games_v2;

import java.util.Set;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public abstract class zzgq extends zzgi implements Set {
    private transient zzgm zza;

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (obj == this || obj == this) {
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
    public final int hashCode() {
        return zzgy.zza(this);
    }

    @Override // com.google.android.gms.internal.games_v2.zzgi, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: zza */
    public abstract zzgz iterator();

    public final zzgm zzf() {
        zzgm zzgmVar = this.zza;
        if (zzgmVar == null) {
            zzgm zzg = zzg();
            this.zza = zzg;
            return zzg;
        }
        return zzgmVar;
    }

    zzgm zzg() {
        Object[] array = toArray();
        int i2 = zzgm.zzd;
        return zzgm.zzj(array, array.length);
    }
}
