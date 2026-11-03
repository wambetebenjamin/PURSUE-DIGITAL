package com.twitter.sdk.android.tweetui;

import java.util.List;
/* loaded from: classes10.dex */
public class TimelineResult<T> {
    public final List<T> items;
    public final TimelineCursor timelineCursor;

    public TimelineResult(TimelineCursor timelineCursor, List<T> list) {
        this.timelineCursor = timelineCursor;
        this.items = list;
    }
}
