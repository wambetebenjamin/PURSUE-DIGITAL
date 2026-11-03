package com.google.android.gms.internal.games_v2;

import java.util.Iterator;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzgu extends zzgq {
    private final transient zzgp zza;
    private final transient Object[] zzb;
    private final transient int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgu(zzgp zzgpVar, Object[] objArr, int i2, int i3) {
        this.zza = zzgpVar;
        this.zzb = objArr;
        this.zzc = i3;
    }

    @Override // com.google.android.gms.internal.games_v2.zzgi, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zza.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.games_v2.zzgq, com.google.android.gms.internal.games_v2.zzgi, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return zzf().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.games_v2.zzgq, com.google.android.gms.internal.games_v2.zzgi
    public final zzgz zza() {
        return zzf().listIterator(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.games_v2.zzgi
    public final int zze(Object[] objArr, int i2) {
        return zzf().zze(objArr, 0);
    }

    @Override // com.google.android.gms.internal.games_v2.zzgq
    final zzgm zzg() {
        return new zzgt(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object[] zzh() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ int zzi() {
        return this.zzc;
    }
}
