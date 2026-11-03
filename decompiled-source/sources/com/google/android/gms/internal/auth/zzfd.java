package com.google.android.gms.internal.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes5.dex */
public final class zzfd extends zzdq implements RandomAccess, zzfe {
    public static final zzfe zza;
    private static final zzfd zzb;
    private final List zzc;

    static {
        zzfd zzfdVar = new zzfd(10);
        zzb = zzfdVar;
        zzfdVar.zzb();
        zza = zzfdVar;
    }

    public zzfd() {
        this(10);
    }

    private static String zzh(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzee) {
            return ((zzee) obj).zzm(zzez.zzb);
        }
        return zzez.zzh((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.auth.zzdq, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i2, Object obj) {
        zza();
        this.zzc.add(i2, (String) obj);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.auth.zzdq, java.util.AbstractList, java.util.List
    public final boolean addAll(int i2, Collection collection) {
        zza();
        if (collection instanceof zzfe) {
            collection = ((zzfe) collection).zzg();
        }
        boolean addAll = this.zzc.addAll(i2, collection);
        this.modCount++;
        return addAll;
    }

    @Override // com.google.android.gms.internal.auth.zzdq, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zza();
        this.zzc.clear();
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.auth.zzdq, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i2) {
        zza();
        Object remove = this.zzc.remove(i2);
        this.modCount++;
        return zzh(remove);
    }

    @Override // com.google.android.gms.internal.auth.zzdq, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i2, Object obj) {
        zza();
        return zzh(this.zzc.set(i2, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.gms.internal.auth.zzey
    public final /* bridge */ /* synthetic */ zzey zzd(int i2) {
        if (i2 < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i2);
        arrayList.addAll(this.zzc);
        return new zzfd(arrayList);
    }

    @Override // com.google.android.gms.internal.auth.zzfe
    public final zzfe zze() {
        return zzc() ? new zzhd(this) : this;
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: zzf */
    public final String get(int i2) {
        Object obj = this.zzc.get(i2);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzee) {
            zzee zzeeVar = (zzee) obj;
            String zzm = zzeeVar.zzm(zzez.zzb);
            if (zzeeVar.zzh()) {
                this.zzc.set(i2, zzm);
            }
            return zzm;
        }
        byte[] bArr = (byte[]) obj;
        String zzh = zzez.zzh(bArr);
        if (zzez.zzi(bArr)) {
            this.zzc.set(i2, zzh);
        }
        return zzh;
    }

    @Override // com.google.android.gms.internal.auth.zzfe
    public final List zzg() {
        return Collections.unmodifiableList(this.zzc);
    }

    public zzfd(int i2) {
        this.zzc = new ArrayList(i2);
    }

    private zzfd(ArrayList arrayList) {
        this.zzc = arrayList;
    }

    @Override // com.google.android.gms.internal.auth.zzdq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
