package com.google.android.gms.internal.games_v2;

import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
final class zzgw extends zzgm {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgw(Object[] objArr, int i2, int i3) {
        this.zza = objArr;
        this.zzb = i2;
        this.zzc = i3;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzfz.zza(i2, this.zzc, "index");
        return Objects.requireNonNull(this.zza[i2 + i2 + this.zzb]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }
}
