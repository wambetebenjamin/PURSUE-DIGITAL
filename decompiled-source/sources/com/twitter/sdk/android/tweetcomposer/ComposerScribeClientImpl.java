package com.twitter.sdk.android.tweetcomposer;

import com.bytedance.push.interfaze.ah;
import java.util.Collections;
import java.util.Objects;
/* loaded from: classes10.dex */
class ComposerScribeClientImpl implements ComposerScribeClient {
    private final ScribeClient scribeClient;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ComposerScribeClientImpl(ScribeClient scribeClient) {
        Objects.requireNonNull(scribeClient, "scribeClient must not be null");
        this.scribeClient = scribeClient;
    }

    @Override // com.twitter.sdk.android.tweetcomposer.ComposerScribeClient
    public void impression() {
        this.scribeClient.scribe(ScribeConstants.ComposerEventBuilder.setComponent("").setElement("").setAction("impression").builder(), Collections.EMPTY_LIST);
    }

    @Override // com.twitter.sdk.android.tweetcomposer.ComposerScribeClient
    public void click(String str) {
        this.scribeClient.scribe(ScribeConstants.ComposerEventBuilder.setComponent("").setElement(str).setAction(ah.f11397b).builder(), Collections.EMPTY_LIST);
    }
}
