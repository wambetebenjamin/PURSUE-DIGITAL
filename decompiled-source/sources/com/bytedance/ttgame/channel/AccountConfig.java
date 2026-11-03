package com.bytedance.ttgame.channel;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes3.dex */
public class AccountConfig {
    @SerializedName("facebook_platform_id")
    private String facebookPlatFormId;
    @SerializedName("facebook_friend_permission")
    public boolean facebook_friend_permission;
    @SerializedName("google_id")
    private String googleAppId;
    @SerializedName("google_platForm_id")
    private String googlePlatFormId;
    @SerializedName("google_play_id")
    public String googlePlayId;
    @SerializedName("google_play_platform_id")
    public String googlePlayPlatFormId;
    @SerializedName("google_web_id")
    private String googleWebAppId;
    @SerializedName("line_channel_id")
    private String lineChannelId;
    @SerializedName("line_platform_id")
    private String linePlatFormId;
    @SerializedName("sort")
    private List<String> sort;
    @SerializedName("tiktok_key")
    private String tiktokKey;
    @SerializedName("tiktok_platform_id")
    private String tiktokPlatformId;
    @SerializedName("twitter_key")
    private String twitterKey;
    @SerializedName("twitter_platform_id")
    private String twitterPlatFormId;
    @SerializedName("twitter_secret")
    private String twitterSecret;
    @SerializedName("vk_platfrom_id")
    private String vkPlatFormId;

    public String getFacebookPlatFormId() {
        return this.facebookPlatFormId;
    }

    public String getLinePlatFormId() {
        return this.linePlatFormId;
    }

    public String getGooglePlatFormId() {
        return this.googlePlatFormId;
    }

    public String getGoogleAppId() {
        return this.googleAppId;
    }

    public String getGoogleWebAppId() {
        return this.googleWebAppId;
    }

    public String getLineChannelId() {
        return this.lineChannelId;
    }

    public String getTwitterPlatFormId() {
        return this.twitterPlatFormId;
    }

    public String getVkPlatFormId() {
        return this.vkPlatFormId;
    }

    public String getTwitterKey() {
        return this.twitterKey;
    }

    public String getTwitterSecret() {
        return this.twitterSecret;
    }

    public String getTiktokPlatformId() {
        return this.tiktokPlatformId;
    }

    public String getTiktokKey() {
        return this.tiktokKey;
    }

    public String getGooglePlayPlatFormId() {
        return this.googlePlayPlatFormId;
    }

    public String getGooglePlayId() {
        return this.googlePlayId;
    }

    public List<String> getSort() {
        return this.sort;
    }

    public boolean isFacebook_friend_permission() {
        return this.facebook_friend_permission;
    }
}
