package com.google.android.gms.games;

import android.content.Intent;
import com.google.android.gms.tasks.Task;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public interface PlayersClient {
    public static final String EXTRA_PLAYER_SEARCH_RESULTS = "player_search_results";

    Task<Intent> getCompareProfileIntent(Player player);

    Task<Intent> getCompareProfileIntent(String str);

    Task<Intent> getCompareProfileIntentWithAlternativeNameHints(String str, String str2, String str3);

    Task<Player> getCurrentPlayer();

    Task<AnnotatedData<Player>> getCurrentPlayer(boolean z);

    Task<String> getCurrentPlayerId();

    Task<Intent> getPlayerSearchIntent();

    Task<AnnotatedData<PlayerBuffer>> loadFriends(int i2, boolean z);

    Task<AnnotatedData<PlayerBuffer>> loadMoreFriends(int i2);

    @Deprecated
    Task<AnnotatedData<PlayerBuffer>> loadMoreRecentlyPlayedWithPlayers(int i2);

    Task<AnnotatedData<Player>> loadPlayer(String str);

    Task<AnnotatedData<Player>> loadPlayer(String str, boolean z);

    @Deprecated
    Task<AnnotatedData<PlayerBuffer>> loadRecentlyPlayedWithPlayers(int i2, boolean z);
}
