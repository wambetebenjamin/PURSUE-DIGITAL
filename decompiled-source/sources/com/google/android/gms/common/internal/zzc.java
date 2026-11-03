package com.google.android.gms.common.internal;

import android.util.Log;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public abstract class zzc {
    private Object zza;
    private boolean zzb;
    final /* synthetic */ BaseGmsClient zzd;

    public zzc(BaseGmsClient baseGmsClient, Object obj) {
        java.util.Objects.requireNonNull(baseGmsClient);
        this.zzd = baseGmsClient;
        this.zza = obj;
        this.zzb = false;
    }

    protected abstract void zzc(Object obj);

    public final void zzd() {
        Object obj;
        synchronized (this) {
            obj = this.zza;
            if (this.zzb) {
                String obj2 = toString();
                StringBuilder sb = new StringBuilder(obj2.length() + 47);
                sb.append("Callback proxy ");
                sb.append(obj2);
                sb.append(" being reused. This is not safe.");
                Log.w("GmsClient", sb.toString());
            }
        }
        if (obj != null) {
            try {
                zzc(obj);
            } catch (RuntimeException e2) {
                throw e2;
            }
        }
        synchronized (this) {
            this.zzb = true;
        }
        zze();
    }

    public final void zze() {
        zzf();
        BaseGmsClient baseGmsClient = this.zzd;
        synchronized (baseGmsClient.zzj()) {
            baseGmsClient.zzj().remove(this);
        }
    }

    public final void zzf() {
        synchronized (this) {
            this.zza = null;
        }
    }
}
