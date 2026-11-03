package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Logger;
import com.twitter.sdk.android.core.TwitterException;
/* loaded from: classes10.dex */
abstract class LoggingCallback<T> extends Callback<T> {
    private final Callback cb;
    private final Logger logger;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LoggingCallback(Callback callback, Logger logger) {
        this.cb = callback;
        this.logger = logger;
    }

    @Override // com.twitter.sdk.android.core.Callback
    public void failure(TwitterException twitterException) {
        this.logger.e("TweetUi", twitterException.getMessage(), twitterException);
        Callback callback = this.cb;
        if (callback != null) {
            callback.failure(twitterException);
        }
    }
}
