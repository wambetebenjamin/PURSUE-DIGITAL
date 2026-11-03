package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;
/* JADX INFO: Add missing generic type declarations: [V, K] */
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
final class zzlx<K, V> implements Iterator<Map.Entry<K, V>> {
    private int zza;
    private Iterator<Map.Entry<K, V>> zzb;
    private final /* synthetic */ zzlv zzc;

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        Object[] objArr;
        if (zza().hasNext()) {
            return zza().next();
        }
        objArr = this.zzc.zza;
        int i2 = this.zza - 1;
        this.zza = i2;
        return (zzlz) objArr[i2];
    }

    private final Iterator<Map.Entry<K, V>> zza() {
        Map map;
        if (this.zzb == null) {
            map = this.zzc.zzf;
            this.zzb = map.entrySet().iterator();
        }
        return this.zzb;
    }

    private zzlx(zzlv zzlvVar) {
        int i2;
        this.zzc = zzlvVar;
        i2 = zzlvVar.zzb;
        this.zza = i2;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i2;
        int i3 = this.zza;
        if (i3 > 0) {
            i2 = this.zzc.zzb;
            if (i3 <= i2) {
                return true;
            }
        }
        return zza().hasNext();
    }
}
