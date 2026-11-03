package com.twitter.sdk.android.tweetui.internal;

import android.view.View;
/* loaded from: classes10.dex */
public interface HighlightedClickableSpan {
    boolean isSelected();

    void onClick(View view);

    void select(boolean z);
}
