package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class zzo implements DynamiteModule.VersionPolicy.IVersions {
    private final int zza;

    public zzo(int i2, int i3) {
        this.zza = i2;
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
    public final int zza(Context context, String str, boolean z) {
        return 0;
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
    public final int zzb(Context context, String str) {
        return this.zza;
    }
}
