package com.google.android.gms.internal.measurement;

import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzam implements Iterator<zzaq> {
    private final /* synthetic */ Iterator zza;

    @Override // java.util.Iterator
    public final /* synthetic */ zzaq next() {
        return new zzas((String) this.zza.next());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzam(Iterator it) {
        this.zza = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }
}
