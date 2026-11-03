package com.google.android.gms.internal.games_v2;

import java.util.List;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzgl extends zzgm {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzgm zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgl(zzgm zzgmVar, int i2, int i3) {
        Objects.requireNonNull(zzgmVar);
        this.zzc = zzgmVar;
        this.zza = i2;
        this.zzb = i3;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzfz.zza(i2, this.zzb, "index");
        return this.zzc.get(i2 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.games_v2.zzgm, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i2, int i3) {
        return subList(i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.games_v2.zzgi
    public final Object[] zzb() {
        return this.zzc.zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.games_v2.zzgi
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.games_v2.zzgi
    final int zzd() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.games_v2.zzgm
    public final zzgm zzf(int i2, int i3) {
        zzfz.zzc(i2, i3, this.zzb);
        int i4 = this.zza;
        return this.zzc.subList(i2 + i4, i3 + i4);
    }
}
