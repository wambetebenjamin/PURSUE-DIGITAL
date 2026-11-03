package com.google.android.gms.internal.play_billing;

import java.util.List;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzbv extends zzbw {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzbw zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbv(zzbw zzbwVar, int i2, int i3) {
        Objects.requireNonNull(zzbwVar);
        this.zzc = zzbwVar;
        this.zza = i2;
        this.zzb = i3;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzbj.zza(i2, this.zzb, "index");
        return this.zzc.get(i2 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbw, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i2, int i3) {
        return subList(i2, i3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbt
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzbt
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzbt
    public final boolean zzf() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzbt
    public final Object[] zzg() {
        return this.zzc.zzg();
    }

    @Override // com.google.android.gms.internal.play_billing.zzbw
    public final zzbw zzh(int i2, int i3) {
        zzbj.zzd(i2, i3, this.zzb);
        int i4 = this.zza;
        return this.zzc.subList(i2 + i4, i3 + i4);
    }
}
