package com.google.android.gms.common;

import java.util.Arrays;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
final class zzk extends zzj {
    private final byte[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzk(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.zza = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.common.zzj
    public final byte[] zzc() {
        return this.zza;
    }
}
