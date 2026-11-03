package com.google.android.gms.internal.play_billing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzfh {
    static final zzfh zza = new zzfh(true);
    public static final /* synthetic */ int zzb = 0;
    private static volatile boolean zzc = false;
    private final Map zzd;

    zzfh() {
        this.zzd = new HashMap();
    }

    public final zzft zza(zzhb zzhbVar, int i2) {
        return (zzft) this.zzd.get(new zzfg(zzhbVar, i2));
    }

    zzfh(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}
