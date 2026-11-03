package com.twitter.sdk.android.core.internal.scribe;
/* loaded from: classes10.dex */
public interface EventsManager<T> {
    void deleteAllEvents();

    void recordEvent(T t);

    void sendEvents();
}
