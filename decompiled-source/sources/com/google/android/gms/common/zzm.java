package com.google.android.gms.common;

import java.lang.ref.WeakReference;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
abstract class zzm extends zzj {
    private static final WeakReference zzb = new WeakReference(null);
    private WeakReference zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzm(byte[] bArr) {
        super(bArr);
        this.zza = zzb;
    }

    protected abstract byte[] zzb();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.common.zzj
    public final byte[] zzc() {
        byte[] bArr;
        synchronized (this) {
            bArr = (byte[]) this.zza.get();
            if (bArr == null) {
                bArr = zzb();
                this.zza = new WeakReference(bArr);
            }
        }
        return bArr;
    }
}
