package com.google.android.gms.internal.games_v2;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzar implements Application.ActivityLifecycleCallbacks {
    final /* synthetic */ zzas zza;
    private final Application zzb;
    private boolean zzc;
    private boolean zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzar(zzas zzasVar, Application application, byte[] bArr) {
        Objects.requireNonNull(zzasVar);
        this.zza = zzasVar;
        this.zzc = false;
        this.zzb = application;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        String className = activity.getComponentName().getClassName();
        if (!this.zzd) {
            if (Objects.equals(className, "com.epicgames.unreal.SplashActivity") && zzat.zza.zza(activity)) {
                this.zzd = true;
                return;
            }
        } else if (!Objects.equals(className, "com.epicgames.unreal.GameActivity")) {
            return;
        }
        this.zzb.unregisterActivityLifecycleCallbacks(this);
        if (this.zzc) {
            this.zzc = false;
            zzfu.zza("AutomaticGamesAuthenticator", "Automatic connection attempt triggered");
            this.zza.zzc().zzd();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza() {
        if (this.zzc) {
            return;
        }
        this.zzb.registerActivityLifecycleCallbacks(this);
        this.zzc = true;
    }
}
