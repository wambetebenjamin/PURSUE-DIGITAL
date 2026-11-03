package com.google.android.gms.games;

import com.google.android.gms.tasks.Task;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
@Deprecated
/* loaded from: classes5.dex */
public interface GamesMetadataClient {
    @Deprecated
    Task<Game> getCurrentGame();

    @Deprecated
    Task<AnnotatedData<Game>> loadGame();
}
