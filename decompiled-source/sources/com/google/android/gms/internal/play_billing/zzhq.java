package com.google.android.gms.internal.play_billing;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzhq implements Iterator {
    final /* synthetic */ zzht zza;
    private int zzb;
    private boolean zzc;
    private Iterator zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhq(zzht zzhtVar, zzhs zzhsVar) {
        Objects.requireNonNull(zzhtVar);
        this.zza = zzhtVar;
        this.zzb = -1;
    }

    private final Iterator zza() {
        Map map;
        if (this.zzd == null) {
            map = this.zza.zzc;
            this.zzd = map.entrySet().iterator();
        }
        return this.zzd;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i2;
        Map map;
        int i3 = this.zzb + 1;
        zzht zzhtVar = this.zza;
        i2 = zzhtVar.zzb;
        if (i3 >= i2) {
            map = zzhtVar.zzc;
            return !map.isEmpty() && zza().hasNext();
        }
        return true;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        int i2;
        Object[] objArr;
        this.zzc = true;
        int i3 = this.zzb + 1;
        this.zzb = i3;
        zzht zzhtVar = this.zza;
        i2 = zzhtVar.zzb;
        if (i3 < i2) {
            objArr = zzhtVar.zza;
            return (zzhp) objArr[i3];
        }
        return (Map.Entry) zza().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        int i2;
        if (!this.zzc) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzc = false;
        zzht zzhtVar = this.zza;
        zzhtVar.zzo();
        int i3 = this.zzb;
        i2 = zzhtVar.zzb;
        if (i3 < i2) {
            this.zzb = i3 - 1;
            zzhtVar.zzm(i3);
            return;
        }
        zza().remove();
    }
}
