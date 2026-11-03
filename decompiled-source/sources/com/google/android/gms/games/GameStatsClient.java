package com.google.android.gms.games;

import com.google.android.gms.games.playergameevent.PlayerGameEvent;
import java.util.List;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public interface GameStatsClient {
    void recordEvent(PlayerGameEvent playerGameEvent);

    void recordEvents(List<PlayerGameEvent> list);

    void requestEventsUpload();
}
