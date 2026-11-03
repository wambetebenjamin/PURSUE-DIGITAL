package com.google.android.gms.internal.common;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes5.dex */
final class zzae extends zzz {
    private final zzah zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzae(zzah zzahVar, int i2) {
        super(zzahVar.size(), i2);
        this.zza = zzahVar;
    }

    @Override // com.google.android.gms.internal.common.zzz
    protected final Object zza(int i2) {
        return this.zza.get(i2);
    }
}
