package com.twitter.sdk.android.tweetui;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
class FormattedTweetText {
    String text;
    final List<FormattedUrlEntity> urlEntities = new ArrayList();
    final List<FormattedMediaEntity> mediaEntities = new ArrayList();
    final List<FormattedUrlEntity> hashtagEntities = new ArrayList();
    final List<FormattedUrlEntity> mentionEntities = new ArrayList();
    final List<FormattedUrlEntity> symbolEntities = new ArrayList();
}
