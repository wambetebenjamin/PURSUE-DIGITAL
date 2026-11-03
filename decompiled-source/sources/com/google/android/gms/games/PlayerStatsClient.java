package com.google.android.gms.games;

import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.tasks.Task;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public interface PlayerStatsClient {
    Task<AnnotatedData<PlayerStats>> loadPlayerStats(boolean z);
}
