package com.google.android.gms.games.internal.v2.appshortcuts;

import android.content.Context;
import android.os.Build;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public class zzf {
    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzf(byte[] bArr) {
    }

    public static zzf zzd(Context context) {
        return Build.VERSION.SDK_INT < 25 ? new zza() : new zze(context);
    }

    public void zza() {
    }
}
