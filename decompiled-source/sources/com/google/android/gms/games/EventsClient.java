package com.google.android.gms.games;

import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.tasks.Task;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public interface EventsClient {
    void increment(String str, int i2);

    Task<AnnotatedData<EventBuffer>> load(boolean z);

    Task<AnnotatedData<EventBuffer>> loadByIds(boolean z, String... strArr);
}
