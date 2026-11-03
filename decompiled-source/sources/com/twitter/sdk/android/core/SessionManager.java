package com.twitter.sdk.android.core;

import com.twitter.sdk.android.core.Session;
import java.util.Map;
/* loaded from: classes10.dex */
public interface SessionManager<T extends Session> {
    void clearActiveSession();

    void clearSession(long j2);

    T getActiveSession();

    T getSession(long j2);

    Map<Long, T> getSessionMap();

    void setActiveSession(T t);

    void setSession(long j2, T t);
}
