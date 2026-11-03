package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class zzp implements Handler.Callback {
    final /* synthetic */ zzq zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzp(zzq zzqVar, byte[] bArr) {
        java.util.Objects.requireNonNull(zzqVar);
        this.zza = zzqVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i2 = message.what;
        if (i2 == 0) {
            zzq zzqVar = this.zza;
            synchronized (zzqVar.zzf()) {
                zzn zznVar = (zzn) message.obj;
                zzo zzoVar = (zzo) zzqVar.zzf().get(zznVar);
                if (zzoVar != null && zzoVar.zzg()) {
                    if (zzoVar.zzd()) {
                        zzoVar.zza("GmsClientSupervisor");
                    }
                    zzqVar.zzf().remove(zznVar);
                }
            }
            return true;
        } else if (i2 != 1) {
            return false;
        } else {
            zzq zzqVar2 = this.zza;
            synchronized (zzqVar2.zzf()) {
                zzn zznVar2 = (zzn) message.obj;
                zzo zzoVar2 = (zzo) zzqVar2.zzf().get(zznVar2);
                if (zzoVar2 != null && zzoVar2.zze() == 3) {
                    String valueOf = String.valueOf(zznVar2);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47);
                    sb.append("Timeout waiting for ServiceConnection callback ");
                    sb.append(valueOf);
                    Log.e("GmsClientSupervisor", sb.toString(), new Exception());
                    ComponentName zzi = zzoVar2.zzi();
                    if (zzi == null) {
                        zzi = zznVar2.zzc();
                    }
                    if (zzi == null) {
                        String zzb = zznVar2.zzb();
                        Preconditions.checkNotNull(zzb);
                        String str = zzb;
                        zzi = new ComponentName(zzb, "unknown");
                    }
                    zzoVar2.onServiceDisconnected(zzi);
                }
            }
            return true;
        }
    }
}
