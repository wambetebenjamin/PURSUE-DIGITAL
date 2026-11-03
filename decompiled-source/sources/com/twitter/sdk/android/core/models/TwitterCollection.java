package com.twitter.sdk.android.core.models;

import com.bytedance.apm.perf.traffic.a;
import com.facebook.react.uimanager.ViewProps;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class TwitterCollection {
    @SerializedName("objects")
    public final Content contents;
    @SerializedName(a.TTNET_RESPONSE)
    public final Metadata metadata;

    public TwitterCollection(Content content, Metadata metadata) {
        this.contents = content;
        this.metadata = metadata;
    }

    /* loaded from: classes10.dex */
    public static final class Content {
        @SerializedName("tweets")
        public final Map<Long, Tweet> tweetMap;
        @SerializedName("users")
        public final Map<Long, User> userMap;

        public Content(Map<Long, Tweet> map, Map<Long, User> map2) {
            this.tweetMap = ModelUtils.getSafeMap(map);
            this.userMap = ModelUtils.getSafeMap(map2);
        }
    }

    /* loaded from: classes10.dex */
    public static final class Metadata {
        @SerializedName(ViewProps.POSITION)
        public final Position position;
        @SerializedName("timeline_id")
        public final String timelineId;
        @SerializedName("timeline")
        public final List<TimelineItem> timelineItems;

        public Metadata(String str, Position position, List<TimelineItem> list) {
            this.timelineId = str;
            this.position = position;
            this.timelineItems = list;
        }

        /* loaded from: classes10.dex */
        public static final class Position {
            @SerializedName("max_position")
            public final Long maxPosition;
            @SerializedName("min_position")
            public final Long minPosition;

            public Position(Long l, Long l2) {
                this.maxPosition = l;
                this.minPosition = l2;
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class TimelineItem {
        @SerializedName("tweet")
        public final TweetItem tweetItem;

        public TimelineItem(TweetItem tweetItem) {
            this.tweetItem = tweetItem;
        }

        /* loaded from: classes10.dex */
        public static final class TweetItem {
            @SerializedName("id")
            public final Long id;

            public TweetItem(Long l) {
                this.id = l;
            }
        }
    }
}
