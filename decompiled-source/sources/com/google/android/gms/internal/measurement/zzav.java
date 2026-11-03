package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes5.dex */
final class zzav implements Iterator<zzaq> {
    private int zza = 0;
    private final /* synthetic */ zzas zzb;

    @Override // java.util.Iterator
    public final /* synthetic */ zzaq next() {
        String str;
        int i2 = this.zza;
        str = this.zzb.zza;
        if (i2 >= str.length()) {
            throw new NoSuchElementException();
        }
        int i3 = this.zza;
        this.zza = i3 + 1;
        return new zzas(String.valueOf(i3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzav(zzas zzasVar) {
        this.zzb = zzasVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        String str;
        int i2 = this.zza;
        str = this.zzb.zza;
        return i2 < str.length();
    }
}
