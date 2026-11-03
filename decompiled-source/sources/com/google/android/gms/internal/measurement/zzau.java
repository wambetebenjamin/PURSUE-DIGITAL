package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes5.dex */
final class zzau implements Iterator<zzaq> {
    private int zza = 0;
    private final /* synthetic */ zzas zzb;

    @Override // java.util.Iterator
    public final /* synthetic */ zzaq next() {
        String str;
        String str2;
        int i2 = this.zza;
        str = this.zzb.zza;
        if (i2 >= str.length()) {
            throw new NoSuchElementException();
        }
        str2 = this.zzb.zza;
        int i3 = this.zza;
        this.zza = i3 + 1;
        return new zzas(String.valueOf(str2.charAt(i3)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzau(zzas zzasVar) {
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
