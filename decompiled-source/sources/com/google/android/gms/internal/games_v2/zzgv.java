package com.google.android.gms.internal.games_v2;

import java.util.Iterator;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
final class zzgv extends zzgq {
    private final transient zzgp zza;
    private final transient zzgm zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgv(zzgp zzgpVar, zzgm zzgmVar) {
        this.zza = zzgpVar;
        this.zzb = zzgmVar;
    }

    @Override // com.google.android.gms.internal.games_v2.zzgi, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.games_v2.zzgq, com.google.android.gms.internal.games_v2.zzgi, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.games_v2.zzgq, com.google.android.gms.internal.games_v2.zzgi
    public final zzgz zza() {
        return this.zzb.listIterator(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.games_v2.zzgi
    public final int zze(Object[] objArr, int i2) {
        return this.zzb.zze(objArr, 0);
    }
}
