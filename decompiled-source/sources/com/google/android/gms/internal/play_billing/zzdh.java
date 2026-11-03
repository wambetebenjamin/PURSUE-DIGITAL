package com.google.android.gms.internal.play_billing;

import com.google.android.gms.internal.play_billing.zzcm;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzdh extends zzct {
    private zzdc zzd;
    private ScheduledFuture zze;

    private zzdh(zzdc zzdcVar) {
        this.zzd = zzdcVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzdc zzs(zzdc zzdcVar, long j2, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        zzdh zzdhVar = new zzdh(zzdcVar);
        zzde zzdeVar = new zzde(zzdhVar);
        zzdhVar.zze = scheduledExecutorService.schedule(zzdeVar, 28500L, timeUnit);
        zzdcVar.zzb(zzdeVar, zzcs.INSTANCE);
        return zzdhVar;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcm
    protected final String zzd() {
        zzdc zzdcVar = this.zzd;
        ScheduledFuture scheduledFuture = this.zze;
        if (zzdcVar != null) {
            String str = "inputFuture=[" + zzdcVar.toString() + "]";
            if (scheduledFuture != null) {
                long delay = scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
                if (delay > 0) {
                    return str + ", remaining delay=[" + delay + " ms]";
                }
                return str;
            }
            return str;
        }
        return null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcm
    protected final void zzg() {
        zzdc zzdcVar = this.zzd;
        boolean z = true;
        if ((this.valueField instanceof zzcm.zza) & (zzdcVar != null)) {
            Object obj = this.valueField;
            if (!(obj instanceof zzcm.zza) || !((zzcm.zza) obj).zzc) {
                z = false;
            }
            zzdcVar.cancel(z);
        }
        ScheduledFuture scheduledFuture = this.zze;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.zzd = null;
        this.zze = null;
    }
}
