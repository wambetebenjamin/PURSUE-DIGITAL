package com.google.android.gms.internal.play_billing;

import java.util.Set;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public abstract class zzca extends zzbt implements Set {
    private transient zzbw zza;

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
        return zzcj.zza(this);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbt
    public zzbw zzd() {
        zzbw zzbwVar = this.zza;
        if (zzbwVar == null) {
            zzbw zzh = zzh();
            this.zza = zzh;
            return zzh;
        }
        return zzbwVar;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbt, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: zze */
    public abstract zzck iterator();

    zzbw zzh() {
        Object[] array = toArray();
        int i2 = zzbw.zzd;
        return zzbw.zzi(array, array.length);
    }
}
