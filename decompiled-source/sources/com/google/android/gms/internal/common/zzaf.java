package com.google.android.gms.internal.common;

import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes5.dex */
public final class zzaf extends zzah {
    private final transient zzah zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaf(zzah zzahVar) {
        this.zza = zzahVar;
    }

    private final int zzs(int i2) {
        return (this.zza.size() - 1) - i2;
    }

    @Override // com.google.android.gms.internal.common.zzah, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return this.zza.contains(obj);
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzah zzahVar = this.zza;
        zzr.zzb(i2, zzahVar.size(), "index");
        return zzahVar.get(zzs(i2));
    }

    @Override // com.google.android.gms.internal.common.zzah, java.util.List
    public final int indexOf(Object obj) {
        int lastIndexOf = this.zza.lastIndexOf(obj);
        if (lastIndexOf >= 0) {
            return zzs(lastIndexOf);
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.common.zzah, java.util.List
    public final int lastIndexOf(Object obj) {
        int indexOf = this.zza.indexOf(obj);
        if (indexOf >= 0) {
            return zzs(indexOf);
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.common.zzah, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i2, int i3) {
        return subList(i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.common.zzac
    public final boolean zzf() {
        return this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.common.zzah
    public final zzah zzh() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.common.zzah
    public final zzah zzi(int i2, int i3) {
        zzah zzahVar = this.zza;
        zzr.zzd(i2, i3, zzahVar.size());
        return zzahVar.subList(zzahVar.size() - i3, zzahVar.size() - i2).zzh();
    }
}
