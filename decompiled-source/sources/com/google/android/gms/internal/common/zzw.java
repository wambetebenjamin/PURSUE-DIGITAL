package com.google.android.gms.internal.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes5.dex */
public final class zzw {
    private final zzp zza;
    private final boolean zzb;
    private final zzu zzc;

    private zzw(zzu zzuVar, boolean z, zzp zzpVar, int i2) {
        this.zzc = zzuVar;
        this.zzb = z;
        this.zza = zzpVar;
    }

    public static zzw zza(zzp zzpVar) {
        return new zzw(new zzu(zzpVar), false, zzo.zza, Integer.MAX_VALUE);
    }

    public final zzw zzb() {
        return new zzw(this.zzc, true, this.zza, Integer.MAX_VALUE);
    }

    public final Iterable zzc(CharSequence charSequence) {
        return new zzt(this, charSequence);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Iterator zze(CharSequence charSequence) {
        return this.zzc.zza(this, charSequence);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzp zzf() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ boolean zzg() {
        return this.zzb;
    }

    public final List zzd(CharSequence charSequence) {
        Objects.requireNonNull(charSequence);
        Iterator zza = this.zzc.zza(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (zza.hasNext()) {
            arrayList.add((String) zza.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
