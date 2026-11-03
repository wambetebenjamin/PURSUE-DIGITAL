package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
final class zzj implements DynamiteModule.VersionPolicy {
    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) throws DynamiteModule.LoadingException {
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        selectionResult.localVersion = iVersions.zzb(context, str);
        int i2 = 1;
        int zza = iVersions.zza(context, str, true);
        selectionResult.remoteVersion = zza;
        int i3 = selectionResult.localVersion;
        if (i3 == 0) {
            if (zza == 0) {
                i2 = 0;
                selectionResult.selection = i2;
                return selectionResult;
            }
            i3 = 0;
        }
        if (i3 >= zza) {
            i2 = -1;
        }
        selectionResult.selection = i2;
        return selectionResult;
    }
}
