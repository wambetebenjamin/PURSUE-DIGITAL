package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.models.Identifiable;
import java.util.List;
/* loaded from: classes10.dex */
public class TimelineCursor {
    public final Long maxPosition;
    public final Long minPosition;

    public TimelineCursor(Long l, Long l2) {
        this.minPosition = l;
        this.maxPosition = l2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimelineCursor(List<? extends Identifiable> list) {
        this.minPosition = list.size() > 0 ? Long.valueOf(list.get(list.size() - 1).getId()) : null;
        this.maxPosition = list.size() > 0 ? Long.valueOf(list.get(0).getId()) : null;
    }
}
