package com.google.android.gms.internal.games_v2;

import java.util.AbstractMap;
import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
final class zzgt extends zzgm {
    final /* synthetic */ zzgu zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgt(zzgu zzguVar) {
        Objects.requireNonNull(zzguVar);
        this.zza = zzguVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i2) {
        zzgu zzguVar = this.zza;
        zzfz.zza(i2, zzguVar.zzi(), "index");
        int i3 = i2 + i2;
        return new AbstractMap.SimpleImmutableEntry(Objects.requireNonNull(zzguVar.zzh()[i3]), Objects.requireNonNull(zzguVar.zzh()[i3 + 1]));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzi();
    }
}
