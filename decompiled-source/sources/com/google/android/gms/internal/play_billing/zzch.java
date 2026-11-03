package com.google.android.gms.internal.play_billing;

import java.util.Objects;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzch extends zzbw {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzch(Object[] objArr, int i2, int i3) {
        this.zza = objArr;
        this.zzb = i2;
        this.zzc = i3;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzbj.zza(i2, this.zzc, "index");
        return Objects.requireNonNull(this.zza[i2 + i2 + this.zzb]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzbt
    public final boolean zzf() {
        return true;
    }
}
