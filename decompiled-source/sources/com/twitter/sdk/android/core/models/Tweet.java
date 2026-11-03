package com.twitter.sdk.android.core.models;

import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.exceptions.VKApiCodes;
import java.util.List;
/* loaded from: classes10.dex */
public class Tweet implements Identifiable {
    public static final long INVALID_ID = -1;
    @SerializedName("card")
    public final Card card;
    @SerializedName("coordinates")
    public final Coordinates coordinates;
    @SerializedName("created_at")
    public final String createdAt;
    @SerializedName("current_user_retweet")
    public final Object currentUserRetweet;
    @SerializedName("display_text_range")
    public final List<Integer> displayTextRange;
    @SerializedName("entities")
    public final TweetEntities entities;
    @SerializedName("extended_entities")
    public final TweetEntities extendedEntities;
    @SerializedName("favorite_count")
    public final Integer favoriteCount;
    @SerializedName("favorited")
    public final boolean favorited;
    @SerializedName("filter_level")
    public final String filterLevel;
    @SerializedName("id")
    public final long id;
    @SerializedName("id_str")
    public final String idStr;
    @SerializedName("in_reply_to_screen_name")
    public final String inReplyToScreenName;
    @SerializedName("in_reply_to_status_id")
    public final long inReplyToStatusId;
    @SerializedName("in_reply_to_status_id_str")
    public final String inReplyToStatusIdStr;
    @SerializedName("in_reply_to_user_id")
    public final long inReplyToUserId;
    @SerializedName("in_reply_to_user_id_str")
    public final String inReplyToUserIdStr;
    @SerializedName(VKApiCodes.PARAM_LANG)
    public final String lang;
    @SerializedName("place")
    public final Place place;
    @SerializedName("possibly_sensitive")
    public final boolean possiblySensitive;
    @SerializedName("quoted_status")
    public final Tweet quotedStatus;
    @SerializedName("quoted_status_id")
    public final long quotedStatusId;
    @SerializedName("quoted_status_id_str")
    public final String quotedStatusIdStr;
    @SerializedName("retweet_count")
    public final int retweetCount;
    @SerializedName("retweeted")
    public final boolean retweeted;
    @SerializedName("retweeted_status")
    public final Tweet retweetedStatus;
    @SerializedName(ParamKeyConstants.WebViewConstants.REDIRECT_QUERY_SCOPE)
    public final Object scopes;
    @SerializedName("source")
    public final String source;
    @SerializedName(alternate = {"full_text"}, value = "text")
    public final String text;
    @SerializedName("truncated")
    public final boolean truncated;
    @SerializedName("user")
    public final User user;
    @SerializedName("withheld_copyright")
    public final boolean withheldCopyright;
    @SerializedName("withheld_in_countries")
    public final List<String> withheldInCountries;
    @SerializedName("withheld_scope")
    public final String withheldScope;

    private Tweet() {
        this(null, null, null, TweetEntities.EMPTY, TweetEntities.EMPTY, 0, false, null, 0L, "0", null, 0L, "0", 0L, "0", null, null, false, null, 0L, "0", null, 0, false, null, null, null, null, false, null, false, null, null, null);
    }

    public Tweet(Coordinates coordinates, String str, Object obj, TweetEntities tweetEntities, TweetEntities tweetEntities2, Integer num, boolean z, String str2, long j2, String str3, String str4, long j3, String str5, long j4, String str6, String str7, Place place, boolean z2, Object obj2, long j5, String str8, Tweet tweet, int i2, boolean z3, Tweet tweet2, String str9, String str10, List<Integer> list, boolean z4, User user, boolean z5, List<String> list2, String str11, Card card) {
        this.coordinates = coordinates;
        this.createdAt = str;
        this.currentUserRetweet = obj;
        this.entities = tweetEntities == null ? TweetEntities.EMPTY : tweetEntities;
        this.extendedEntities = tweetEntities2 == null ? TweetEntities.EMPTY : tweetEntities2;
        this.favoriteCount = num;
        this.favorited = z;
        this.filterLevel = str2;
        this.id = j2;
        this.idStr = str3;
        this.inReplyToScreenName = str4;
        this.inReplyToStatusId = j3;
        this.inReplyToStatusIdStr = str5;
        this.inReplyToUserId = j4;
        this.inReplyToUserIdStr = str6;
        this.lang = str7;
        this.place = place;
        this.possiblySensitive = z2;
        this.scopes = obj2;
        this.quotedStatusId = j5;
        this.quotedStatusIdStr = str8;
        this.quotedStatus = tweet;
        this.retweetCount = i2;
        this.retweeted = z3;
        this.retweetedStatus = tweet2;
        this.source = str9;
        this.text = str10;
        this.displayTextRange = ModelUtils.getSafeList(list);
        this.truncated = z4;
        this.user = user;
        this.withheldCopyright = z5;
        this.withheldInCountries = ModelUtils.getSafeList(list2);
        this.withheldScope = str11;
        this.card = card;
    }

    @Override // com.twitter.sdk.android.core.models.Identifiable
    public long getId() {
        return this.id;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Tweet) && this.id == ((Tweet) obj).id;
    }

    public int hashCode() {
        return (int) this.id;
    }
}
