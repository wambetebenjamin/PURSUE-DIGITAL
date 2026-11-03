package com.google.android.gms.internal.games_v2;

import java.util.NoSuchElementException;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
abstract class zzgd extends zzha {
    private final int zza;
    private int zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgd(int i2, int i3) {
        zzfz.zzb(i3, i2, "index");
        this.zza = i2;
        this.zzb = i3;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.zzb < this.zza;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.zzb > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i2 = this.zzb;
        this.zzb = i2 + 1;
        return zza(i2);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.zzb;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i2 = this.zzb - 1;
        this.zzb = i2;
        return zza(i2);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.zzb - 1;
    }

    abstract Object zza(int i2);
}
