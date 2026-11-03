package com.google.android.gms.common;

import android.content.Context;
import com.google.android.gms.dynamic.ObjectWrapper;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
final class zzw {
    private final String zza;
    private final boolean zzb;
    private final boolean zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzw(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, byte[] bArr) {
        this.zza = str;
        this.zzb = z;
        this.zzc = z4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.google.android.gms.dynamic.IObjectWrapper, android.os.IBinder] */
    public final zzp zzb(Context context) {
        return new zzp(this.zza, this.zzb, false, ObjectWrapper.wrap(context), false, true, false);
    }
}
