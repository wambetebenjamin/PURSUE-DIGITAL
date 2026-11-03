package com.twitter.sdk.android.core.models;

import com.facebook.share.internal.ShareConstants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes10.dex */
public class TweetEntities {
    static final TweetEntities EMPTY = new TweetEntities(null, null, null, null, null);
    @SerializedName("hashtags")
    public final List<HashtagEntity> hashtags;
    @SerializedName(ShareConstants.WEB_DIALOG_PARAM_MEDIA)
    public final List<MediaEntity> media;
    @SerializedName("symbols")
    public final List<SymbolEntity> symbols;
    @SerializedName("urls")
    public final List<UrlEntity> urls;
    @SerializedName("user_mentions")
    public final List<MentionEntity> userMentions;

    private TweetEntities() {
        this(null, null, null, null, null);
    }

    public TweetEntities(List<UrlEntity> list, List<MentionEntity> list2, List<MediaEntity> list3, List<HashtagEntity> list4, List<SymbolEntity> list5) {
        this.urls = ModelUtils.getSafeList(list);
        this.userMentions = ModelUtils.getSafeList(list2);
        this.media = ModelUtils.getSafeList(list3);
        this.hashtags = ModelUtils.getSafeList(list4);
        this.symbols = ModelUtils.getSafeList(list5);
    }
}
