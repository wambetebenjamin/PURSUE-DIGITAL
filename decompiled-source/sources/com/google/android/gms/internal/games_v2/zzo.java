package com.google.android.gms.internal.games_v2;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzo extends GoogleApi implements zzk {
    private static final Api.ClientKey zza;
    private static final Api.AbstractClientBuilder zzb;
    private static final Api zzc;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zza = clientKey;
        zzl zzlVar = new zzl();
        zzb = zzlVar;
        zzc = new Api("GamesConnect.API", zzlVar, clientKey);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(Context context, zzi zziVar) {
        super(context, zzc, zziVar, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.internal.games_v2.zzk
    public final Task zza(final zzq zzqVar, boolean z) {
        TaskApiCall build = TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.internal.games_v2.zzn
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final /* synthetic */ void accept(Object obj, Object obj2) {
                ((zzw) ((zzp) obj).getService()).zzd(new zzm(zzo.this, (TaskCompletionSource) obj2), zzqVar);
            }
        }).setMethodKey(6737).setAutoResolveMissingFeatures(z).build();
        if (z) {
            return doWrite(build);
        }
        return doBestEffortWrite(build);
    }
}
