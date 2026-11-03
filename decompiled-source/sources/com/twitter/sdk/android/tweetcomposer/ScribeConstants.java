package com.twitter.sdk.android.tweetcomposer;

import com.twitter.sdk.android.core.internal.scribe.EventNamespace;
/* loaded from: classes10.dex */
final class ScribeConstants {
    static final String SCRIBE_CANCEL_ELEMENT = "cancel";
    static final String SCRIBE_CLICK_ACTION = "click";
    static final String SCRIBE_COMPONENT = "";
    static final String SCRIBE_IMPRESSION_ACTION = "impression";
    static final String SCRIBE_IMPRESSION_ELEMENT = "";
    static final String SCRIBE_PAGE = "android";
    static final String SCRIBE_TFW_CLIENT = "tfw";
    static final String SCRIBE_TWEET_ELEMENT = "tweet";
    static final String SCRIBE_SECTION = "composer";
    static final EventNamespace.Builder ComposerEventBuilder = new EventNamespace.Builder().setClient("tfw").setPage("android").setSection(SCRIBE_SECTION);

    private ScribeConstants() {
    }
}
