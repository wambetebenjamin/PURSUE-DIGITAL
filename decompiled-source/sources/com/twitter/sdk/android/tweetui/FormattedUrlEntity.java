package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.MentionEntity;
import com.twitter.sdk.android.core.models.SymbolEntity;
import com.twitter.sdk.android.core.models.UrlEntity;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class FormattedUrlEntity {
    final String displayUrl;
    int end;
    final String expandedUrl;
    int start;
    final String url;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FormattedUrlEntity(int i2, int i3, String str, String str2, String str3) {
        this.start = i2;
        this.end = i3;
        this.displayUrl = str;
        this.url = str2;
        this.expandedUrl = str3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FormattedUrlEntity createFormattedUrlEntity(UrlEntity urlEntity) {
        return new FormattedUrlEntity(urlEntity.getStart(), urlEntity.getEnd(), urlEntity.displayUrl, urlEntity.url, urlEntity.expandedUrl);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FormattedUrlEntity createFormattedUrlEntity(HashtagEntity hashtagEntity) {
        String hashtagPermalink = TweetUtils.getHashtagPermalink(hashtagEntity.text);
        return new FormattedUrlEntity(hashtagEntity.getStart(), hashtagEntity.getEnd(), "#" + hashtagEntity.text, hashtagPermalink, hashtagPermalink);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FormattedUrlEntity createFormattedUrlEntity(MentionEntity mentionEntity) {
        String profilePermalink = TweetUtils.getProfilePermalink(mentionEntity.screenName);
        return new FormattedUrlEntity(mentionEntity.getStart(), mentionEntity.getEnd(), "@" + mentionEntity.screenName, profilePermalink, profilePermalink);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FormattedUrlEntity createFormattedUrlEntity(SymbolEntity symbolEntity) {
        String symbolPermalink = TweetUtils.getSymbolPermalink(symbolEntity.text);
        return new FormattedUrlEntity(symbolEntity.getStart(), symbolEntity.getEnd(), "$" + symbolEntity.text, symbolPermalink, symbolPermalink);
    }
}
