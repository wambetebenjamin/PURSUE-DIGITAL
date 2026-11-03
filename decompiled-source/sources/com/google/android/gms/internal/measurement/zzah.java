package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzah implements Iterator<zzaq> {
    private int zza = 0;
    private final /* synthetic */ zzaf zzb;

    @Override // java.util.Iterator
    public final /* synthetic */ zzaq next() {
        if (this.zza >= this.zzb.zzb()) {
            throw new NoSuchElementException("Out of bounds index: " + this.zza);
        }
        zzaf zzafVar = this.zzb;
        int i2 = this.zza;
        this.zza = i2 + 1;
        return zzafVar.zza(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzah(zzaf zzafVar) {
        this.zzb = zzafVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza < this.zzb.zzb();
    }
}
