package com.google.android.gms.internal.games_v2;

import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.PlayerStatsClient;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzea implements PlayerStatsClient {
    private final zzaq zza;

    public zzea(zzaq zzaqVar) {
        this.zza = zzaqVar;
    }

    @Override // com.google.android.gms.games.PlayerStatsClient
    public final Task<AnnotatedData<PlayerStats>> loadPlayerStats(final boolean z) {
        return this.zza.zzb(new zzap() { // from class: com.google.android.gms.internal.games_v2.zzdz
            @Override // com.google.android.gms.internal.games_v2.zzap
            public final /* synthetic */ Task zza(GoogleApi googleApi) {
                TaskApiCall.Builder builder = TaskApiCall.builder();
                final boolean z2 = z;
                return googleApi.doRead(builder.run(new RemoteCall() { // from class: com.google.android.gms.internal.games_v2.zzdy
                    @Override // com.google.android.gms.common.api.internal.RemoteCall
                    public final /* synthetic */ void accept(Object obj, Object obj2) {
                        ((com.google.android.gms.games.internal.zzah) obj).zzO((TaskCompletionSource) obj2, z2);
                    }
                }).setMethodKey(6708).build());
            }
        });
    }
}
