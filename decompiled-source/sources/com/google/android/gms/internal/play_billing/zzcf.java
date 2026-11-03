package com.google.android.gms.internal.play_billing;

import java.util.Iterator;
import java.util.Map;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzcf extends zzca {
    private final transient zzbz zza;
    private final transient Object[] zzb;
    private final transient int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcf(zzbz zzbzVar, Object[] objArr, int i2, int i3) {
        this.zza = zzbzVar;
        this.zzb = objArr;
        this.zzc = i3;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbt, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zza.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.play_billing.zzca, com.google.android.gms.internal.play_billing.zzbt, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return zzd().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzbt
    public final int zza(Object[] objArr, int i2) {
        return zzd().zza(objArr, 0);
    }

    @Override // com.google.android.gms.internal.play_billing.zzca, com.google.android.gms.internal.play_billing.zzbt
    public final zzck zze() {
        return zzd().listIterator(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzbt
    public final boolean zzf() {
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzca
    final zzbw zzh() {
        return new zzce(this);
    }
}
