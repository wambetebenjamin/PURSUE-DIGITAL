package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.scribe.DefaultScribeClient;
import com.twitter.sdk.android.core.internal.scribe.EventNamespace;
import com.twitter.sdk.android.core.internal.scribe.ScribeItem;
import java.util.List;
/* loaded from: classes10.dex */
public class TweetUi {
    private static final String KIT_SCRIBE_NAME = "TweetUi";
    static final String LOGTAG = "TweetUi";
    static volatile TweetUi instance;
    Context context;
    GuestSessionProvider guestSessionProvider;
    private Picasso imageLoader;
    DefaultScribeClient scribeClient;
    SessionManager<TwitterSession> sessionManager;
    private TweetRepository tweetRepository;

    public String getIdentifier() {
        return "com.twitter.sdk.android:tweet-ui";
    }

    public String getVersion() {
        return "3.2.0.11";
    }

    public static TweetUi getInstance() {
        if (instance == null) {
            synchronized (TweetUi.class) {
                if (instance == null) {
                    instance = new TweetUi();
                }
            }
        }
        return instance;
    }

    TweetUi() {
        TwitterCore twitterCore = TwitterCore.getInstance();
        this.context = Twitter.getInstance().getContext(getIdentifier());
        this.sessionManager = twitterCore.getSessionManager();
        this.guestSessionProvider = twitterCore.getGuestSessionProvider();
        this.tweetRepository = new TweetRepository(new Handler(Looper.getMainLooper()), twitterCore.getSessionManager());
        this.imageLoader = Picasso.with(Twitter.getInstance().getContext(getIdentifier()));
        setUpScribeClient();
    }

    private void setUpScribeClient() {
        this.scribeClient = new DefaultScribeClient(this.context, this.sessionManager, this.guestSessionProvider, Twitter.getInstance().getIdManager(), DefaultScribeClient.getScribeConfig("TweetUi", getVersion()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void scribe(EventNamespace... eventNamespaceArr) {
        if (this.scribeClient == null) {
            return;
        }
        for (EventNamespace eventNamespace : eventNamespaceArr) {
            this.scribeClient.scribe(eventNamespace);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void scribe(EventNamespace eventNamespace, List<ScribeItem> list) {
        DefaultScribeClient defaultScribeClient = this.scribeClient;
        if (defaultScribeClient == null) {
            return;
        }
        defaultScribeClient.scribe(eventNamespace, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TweetRepository getTweetRepository() {
        return this.tweetRepository;
    }

    void setTweetRepository(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public Picasso getImageLoader() {
        return this.imageLoader;
    }

    void setImageLoader(Picasso picasso) {
        this.imageLoader = picasso;
    }
}
