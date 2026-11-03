package com.google.android.gms.internal.fido;

import java.util.List;
import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
/* loaded from: classes5.dex */
public final class zzas extends zzat {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzat zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzas(zzat zzatVar, int i2, int i3) {
        this.zzc = zzatVar;
        this.zza = i2;
        this.zzb = i3;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzam.zza(i2, this.zzb, "index");
        return this.zzc.get(i2 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.fido.zzat, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i2, int i3) {
        return subList(i2, i3);
    }

    @Override // com.google.android.gms.internal.fido.zzaq
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.fido.zzaq
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.fido.zzaq
    @CheckForNull
    public final Object[] zze() {
        return this.zzc.zze();
    }

    @Override // com.google.android.gms.internal.fido.zzat
    public final zzat zzf(int i2, int i3) {
        zzam.zze(i2, i3, this.zzb);
        zzat zzatVar = this.zzc;
        int i4 = this.zza;
        return zzatVar.subList(i2 + i4, i3 + i4);
    }
}
