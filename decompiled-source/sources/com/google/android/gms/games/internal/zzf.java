package com.google.android.gms.games.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskExecutors;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzf {
    public static final /* synthetic */ int zza = 0;
    private static final AtomicReference zzb = new AtomicReference();
    private final Application zzc;
    private WeakReference zzg;
    private final Application.ActivityLifecycleCallbacks zzd = new zze(this, null);
    private final Object zze = new Object();
    private final Set zzf = Collections.newSetFromMap(new WeakHashMap());
    private boolean zzh = false;

    public zzf(Application application) {
        this.zzc = application;
    }

    public static zzf zza(Application application) {
        Preconditions.checkNotNull(application);
        AtomicReference atomicReference = zzb;
        zzf zzfVar = (zzf) atomicReference.get();
        if (zzfVar != null) {
            return zzfVar;
        }
        atomicReference.compareAndSet(null, new zzf(application));
        return (zzf) atomicReference.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zzh */
    public final void zze(zzc zzcVar) {
        Activity zzd = zzd();
        if (zzd == null) {
            return;
        }
        zzcVar.zza(zzd);
    }

    public final void zzb() {
        synchronized (this.zze) {
            if (!this.zzh) {
                this.zzc.registerActivityLifecycleCallbacks(this.zzd);
                this.zzh = true;
            }
        }
    }

    public final void zzc(final zzc zzcVar) {
        Preconditions.checkNotNull(zzcVar);
        synchronized (this.zze) {
            this.zzf.add(zzcVar);
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            TaskExecutors.MAIN_THREAD.execute(new Runnable() { // from class: com.google.android.gms.games.internal.zzd
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    zzf.this.zze(zzcVar);
                }
            });
        } else {
            zze(zzcVar);
        }
    }

    public final Activity zzd() {
        Activity activity;
        synchronized (this.zze) {
            WeakReference weakReference = this.zzg;
            activity = weakReference == null ? null : (Activity) weakReference.get();
        }
        return activity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(Activity activity) {
        Preconditions.checkNotNull(activity);
        synchronized (this.zze) {
            if (zzd() == activity) {
                return;
            }
            this.zzg = new WeakReference(activity);
            for (zzc zzcVar : this.zzf) {
                zzcVar.zza(activity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzg(Activity activity) {
        synchronized (this.zze) {
            WeakReference weakReference = this.zzg;
            if (weakReference == null) {
                return;
            }
            if (weakReference.get() == activity) {
                this.zzg = null;
            }
        }
    }
}
