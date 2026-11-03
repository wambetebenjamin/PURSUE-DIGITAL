package com.google.android.gms.internal.games_v2;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public abstract class zzab {
    private final Handler zza;
    private boolean zzc;
    final Object zzb = new Object();
    private final HashMap zzd = new HashMap();

    public zzab(Looper looper, int i2) {
        this.zza = new zzfy(looper);
    }

    protected abstract void zza(String str, int i2);

    public final void zzb(String str, int i2) {
        synchronized (this.zzb) {
            if (!this.zzc) {
                this.zzc = true;
                this.zza.postDelayed(new zzaa(this), 1000L);
            }
            HashMap hashMap = this.zzd;
            AtomicInteger atomicInteger = (AtomicInteger) hashMap.get(str);
            if (atomicInteger == null) {
                atomicInteger = new AtomicInteger();
                hashMap.put(str, atomicInteger);
            }
            atomicInteger.addAndGet(i2);
        }
    }

    public final void zzc() {
        synchronized (this.zzb) {
            HashMap hashMap = this.zzd;
            for (Map.Entry entry : hashMap.entrySet()) {
                zza((String) entry.getKey(), ((AtomicInteger) entry.getValue()).get());
            }
            hashMap.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd() {
        synchronized (this.zzb) {
            this.zzc = false;
            zzc();
        }
    }
}
