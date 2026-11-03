package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
/* loaded from: classes10.dex */
public interface Timeline<T> {
    void next(Long l, Callback<TimelineResult<T>> callback);

    void previous(Long l, Callback<TimelineResult<T>> callback);
}
