package com.google.android.gms.internal.games_v2;

import android.app.Application;
import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzbw {
    private static final AtomicReference zza = new AtomicReference();
    private static boolean zzb = false;
    private final Application zzc;
    private final zzas zzd;
    private final com.google.android.gms.games.internal.zzf zze;
    private final com.google.android.gms.games.internal.v2.appshortcuts.zzf zzf;

    private zzbw(Application application, zzas zzasVar, com.google.android.gms.games.internal.zzf zzfVar, com.google.android.gms.games.internal.v2.appshortcuts.zzf zzfVar2) {
        this.zzc = application;
        this.zzd = zzasVar;
        this.zze = zzfVar;
        this.zzf = zzfVar2;
    }

    public static void zza(Context context) {
        Application application;
        Preconditions.checkArgument(context != null);
        AtomicReference atomicReference = zza;
        if (atomicReference.get() == null) {
            synchronized (atomicReference) {
                if (atomicReference.get() == null) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        application = (Application) applicationContext;
                    } else {
                        application = (Application) context;
                    }
                    zzbw zzbwVar = new zzbw(application, zzas.zza(application), com.google.android.gms.games.internal.zzf.zza(application), com.google.android.gms.games.internal.v2.appshortcuts.zzf.zzd(context));
                    atomicReference.set(zzbwVar);
                    zzbwVar.zzf.zza();
                    zzbwVar.zzd.zzb();
                    zzbwVar.zze.zzb();
                }
            }
        }
        zzb = true;
    }

    public static void zzb() {
        Preconditions.checkState(zzb, "PlayGamesSdk has not been initialized. Ensure that PlayGamesSdk.initialize() has been called.");
    }

    public static Application zzc() {
        zzb();
        return ((zzbw) zza.get()).zzc;
    }
}
