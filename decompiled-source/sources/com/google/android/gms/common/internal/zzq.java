package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.HashMap;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class zzq extends GmsClientSupervisor {
    private final HashMap zzb = new HashMap();
    private final Context zzc;
    private volatile Handler zzd;
    private final zzp zze;
    private final ConnectionTracker zzf;
    private final long zzg;
    private final long zzh;
    private volatile Executor zzi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzq(Context context, Looper looper, Executor executor) {
        zzp zzpVar = new zzp(this, null);
        this.zze = zzpVar;
        this.zzc = context.getApplicationContext();
        this.zzd = new com.google.android.gms.internal.common.zzg(looper, zzpVar);
        this.zzf = ConnectionTracker.getInstance();
        this.zzg = 5000L;
        this.zzh = 300000L;
        this.zzi = executor;
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    protected final ConnectionResult zza(zzn zznVar, ServiceConnection serviceConnection, String str, Executor executor) {
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        HashMap hashMap = this.zzb;
        synchronized (hashMap) {
            zzo zzoVar = (zzo) hashMap.get(zznVar);
            if (executor == null) {
                executor = this.zzi;
            }
            ConnectionResult connectionResult = null;
            if (zzoVar == null) {
                zzoVar = new zzo(this, zznVar);
                zzoVar.zzb(serviceConnection, serviceConnection, str);
                connectionResult = zzoVar.zzj(str, executor);
                hashMap.put(zznVar, zzoVar);
            } else {
                this.zzd.removeMessages(0, zznVar);
                if (zzoVar.zzf(serviceConnection)) {
                    String obj = zznVar.toString();
                    StringBuilder sb = new StringBuilder(obj.length() + 81);
                    sb.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
                    sb.append(obj);
                    throw new IllegalStateException(sb.toString());
                }
                zzoVar.zzb(serviceConnection, serviceConnection, str);
                int zze = zzoVar.zze();
                if (zze == 1) {
                    serviceConnection.onServiceConnected(zzoVar.zzi(), zzoVar.zzh());
                } else if (zze == 2) {
                    connectionResult = zzoVar.zzj(str, executor);
                }
            }
            if (zzoVar.zzd()) {
                return ConnectionResult.RESULT_SUCCESS;
            }
            if (connectionResult == null) {
                connectionResult = new ConnectionResult(-1);
            }
            return connectionResult;
        }
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    protected final void zzc(zzn zznVar, ServiceConnection serviceConnection, String str) {
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        HashMap hashMap = this.zzb;
        synchronized (hashMap) {
            zzo zzoVar = (zzo) hashMap.get(zznVar);
            if (zzoVar == null) {
                String obj = zznVar.toString();
                StringBuilder sb = new StringBuilder(obj.length() + 50);
                sb.append("Nonexistent connection status for service config: ");
                sb.append(obj);
                throw new IllegalStateException(sb.toString());
            } else if (!zzoVar.zzf(serviceConnection)) {
                String obj2 = zznVar.toString();
                StringBuilder sb2 = new StringBuilder(obj2.length() + 76);
                sb2.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
                sb2.append(obj2);
                throw new IllegalStateException(sb2.toString());
            } else {
                zzoVar.zzc(serviceConnection, str);
                if (zzoVar.zzg()) {
                    this.zzd.sendMessageDelayed(this.zzd.obtainMessage(0, zznVar), this.zzg);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzd(Looper looper) {
        synchronized (this.zzb) {
            this.zzd = new com.google.android.gms.internal.common.zzg(looper, this.zze);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zze(Executor executor) {
        synchronized (this.zzb) {
            this.zzi = executor;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ HashMap zzf() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Context zzg() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Handler zzh() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ ConnectionTracker zzi() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ long zzj() {
        return this.zzh;
    }
}
