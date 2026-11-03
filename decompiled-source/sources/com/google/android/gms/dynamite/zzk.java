package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
final class zzk implements DynamiteModule.VersionPolicy {
    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) throws DynamiteModule.LoadingException {
        int zza;
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        int zzb = iVersions.zzb(context, str);
        selectionResult.localVersion = zzb;
        int i2 = 1;
        int i3 = 0;
        if (zzb != 0) {
            zza = iVersions.zza(context, str, false);
            selectionResult.remoteVersion = zza;
        } else {
            zza = iVersions.zza(context, str, true);
            selectionResult.remoteVersion = zza;
        }
        int i4 = selectionResult.localVersion;
        if (i4 != 0) {
            i3 = i4;
        } else if (zza == 0) {
            i2 = 0;
            selectionResult.selection = i2;
            return selectionResult;
        }
        if (i3 >= zza) {
            i2 = -1;
        }
        selectionResult.selection = i2;
        return selectionResult;
    }
}
