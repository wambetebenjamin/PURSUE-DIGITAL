package com.google.android.gms.internal.location;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
/* loaded from: classes5.dex */
public final class zzbq<E> extends zzbo<E> {
    private final zzbs<E> zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbq(zzbs<E> zzbsVar, int i2) {
        super(zzbsVar.size(), i2);
        this.zza = zzbsVar;
    }

    @Override // com.google.android.gms.internal.location.zzbo
    protected final E zza(int i2) {
        return this.zza.get(i2);
    }
}
