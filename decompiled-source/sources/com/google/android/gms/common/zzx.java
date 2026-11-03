package com.google.android.gms.common;

import java.util.concurrent.Callable;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
final class zzx extends zzy {
    private final Callable zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzx(Callable callable, byte[] bArr) {
        super(false, 1, 5, null, null, -1L, null);
        this.zze = callable;
    }

    @Override // com.google.android.gms.common.zzy
    final String zza() {
        try {
            return (String) this.zze.call();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }
}
