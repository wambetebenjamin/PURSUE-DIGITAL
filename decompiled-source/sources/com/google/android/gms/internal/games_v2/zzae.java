package com.google.android.gms.internal.games_v2;

import android.os.Bundle;
import android.os.IBinder;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzae {
    public final int zzb;
    public int zzc = -1;
    public int zzd = 0;
    public int zze = 0;
    public int zzf = 0;
    public int zzg = 0;
    public IBinder zza = null;

    public zzae(int i2, IBinder iBinder) {
        this.zzb = i2;
    }

    public final Bundle zza() {
        Bundle bundle = new Bundle();
        bundle.putInt("popupLocationInfo.gravity", this.zzb);
        bundle.putInt("popupLocationInfo.displayId", this.zzc);
        bundle.putInt("popupLocationInfo.left", this.zzd);
        bundle.putInt("popupLocationInfo.top", this.zze);
        bundle.putInt("popupLocationInfo.right", this.zzf);
        bundle.putInt("popupLocationInfo.bottom", this.zzg);
        return bundle;
    }
}
