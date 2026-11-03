package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;
/* JADX INFO: Add missing generic type declarations: [V, K] */
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
final class zzmc<K, V> implements Iterator<Map.Entry<K, V>> {
    private int zza;
    private boolean zzb;
    private Iterator<Map.Entry<K, V>> zzc;
    private final /* synthetic */ zzlv zzd;

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        int i2;
        Object[] objArr;
        this.zzb = true;
        int i3 = this.zza + 1;
        this.zza = i3;
        i2 = this.zzd.zzb;
        if (i3 < i2) {
            objArr = this.zzd.zza;
            return (zzlz) objArr[this.zza];
        }
        return zza().next();
    }

    private final Iterator<Map.Entry<K, V>> zza() {
        Map map;
        if (this.zzc == null) {
            map = this.zzd.zzc;
            this.zzc = map.entrySet().iterator();
        }
        return this.zzc;
    }

    private zzmc(zzlv zzlvVar) {
        this.zzd = zzlvVar;
        this.zza = -1;
    }

    @Override // java.util.Iterator
    public final void remove() {
        int i2;
        if (!this.zzb) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzb = false;
        this.zzd.zzg();
        int i3 = this.zza;
        i2 = this.zzd.zzb;
        if (i3 < i2) {
            zzlv zzlvVar = this.zzd;
            int i4 = this.zza;
            this.zza = i4 - 1;
            zzlvVar.zzb(i4);
            return;
        }
        zza().remove();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i2;
        Map map;
        int i3 = this.zza + 1;
        i2 = this.zzd.zzb;
        if (i3 >= i2) {
            map = this.zzd.zzc;
            if (map.isEmpty() || !zza().hasNext()) {
                return false;
            }
        }
        return true;
    }
}
