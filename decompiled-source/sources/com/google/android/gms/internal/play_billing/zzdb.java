package com.google.android.gms.internal.play_billing;

import java.util.logging.Logger;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzdb {
    private final zzbp zza = new zzbp();
    private final String zzb;
    private volatile Logger zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdb(Class cls) {
        this.zzb = cls.getName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Logger zza() {
        Logger logger = this.zzc;
        if (logger != null) {
            return logger;
        }
        synchronized (this.zza) {
            Logger logger2 = this.zzc;
            if (logger2 != null) {
                return logger2;
            }
            Logger logger3 = Logger.getLogger(this.zzb);
            this.zzc = logger3;
            return logger3;
        }
    }
}
