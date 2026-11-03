package com.google.android.gms.internal.play_billing;

import java.util.AbstractMap;
import java.util.Objects;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzce extends zzbw {
    final /* synthetic */ zzcf zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzce(zzcf zzcfVar) {
        Objects.requireNonNull(zzcfVar);
        this.zza = zzcfVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i2) {
        int i3;
        Object[] objArr;
        Object[] objArr2;
        zzcf zzcfVar = this.zza;
        i3 = zzcfVar.zzc;
        zzbj.zza(i2, i3, "index");
        objArr = zzcfVar.zzb;
        int i4 = i2 + i2;
        Object requireNonNull = Objects.requireNonNull(objArr[i4]);
        objArr2 = zzcfVar.zzb;
        return new AbstractMap.SimpleImmutableEntry(requireNonNull, Objects.requireNonNull(objArr2[i4 + 1]));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i2;
        i2 = this.zza.zzc;
        return i2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbt
    public final boolean zzf() {
        return true;
    }
}
