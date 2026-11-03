package com.bytedance.ttgame.channel.adapter;

import com.bytedance.ttgame.sdk.module.account.platform.api.AccountConstants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes3.dex */
public class AccountConfig {
    @SerializedName("aweme_platform_key")
    private String awemePlatformKey;
    @SerializedName("aweme_platform_id")
    private String awemePlatfromId;
    @SerializedName("aweme_friend_permission")
    public boolean aweme_friend_permission;
    @SerializedName("aweme_relation_follow")
    public boolean aweme_relation_follow;
    @SerializedName("aweme_video_list_data_permission")
    public boolean aweme_video_list_data_permission;
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
    @SerializedName("seamless_login")
    private boolean isSeamlessLogin;
    @SerializedName("kakao_platfrom_id")
    private String kakaoPlatFormId;
    @SerializedName("line_channel_id")
    private String lineChannelId;
    @SerializedName("line_platform_id")
    private String linePlatFormId;
    @SerializedName("meta_app_id")
    private String meta_app_id;
    @SerializedName("pico_app_id")
    private String pico_app_id;
    @SerializedName("sms_channel_id")
    private String sms_channel_id;
    @SerializedName("sort")
    private List<String> sort;
    @SerializedName("tiktok_key")
    private String tiktokKey;
    @SerializedName("tiktok_platform_id")
    private String tiktokPlatformId;
    @SerializedName("toutiao_platform_id")
    private String toutiaoPlatformId;
    @SerializedName("toutiao_platform_key")
    private String toutiaoPlatformKey;
    @SerializedName("toutiao_friend_permission")
    public boolean toutiao_friend_permission;
    @SerializedName("twitter_key")
    private String twitterKey;
    @SerializedName("twitter_platform_id")
    private String twitterPlatFormId;
    @SerializedName("twitter_secret")
    private String twitterSecret;
    @SerializedName("vk_platfrom_id")
    private String vkPlatFormId;
    @SerializedName("login_panel_can_be_closed")
    private boolean login_panel_can_be_closed = true;
    @SerializedName(AccountConstants.GSDK_USER_INFO_NO_DB_DEFAULT)
    private boolean gsdk_user_info_no_db_default = true;
    @SerializedName(AccountConstants.GSDK_FUSION_USER_NO_DB_DEFAULT)
    private boolean gsdk_fusion_user_no_db_default = true;
    @SerializedName("is_need_show_tips_info")
    private boolean is_need_show_tips_info = true;

    public String getAwemePlatformKey() {
        return this.awemePlatformKey;
    }

    public String getToutiaoPlatformKey() {
        return this.toutiaoPlatformKey;
    }

    public String getAwemePlatfromId() {
        return this.awemePlatfromId;
    }

    public String getToutiaoPlatformId() {
        return this.toutiaoPlatformId;
    }

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

    public String getKakaoPlatFormId() {
        return this.kakaoPlatFormId;
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

    public boolean isLogin_panel_can_be_closed() {
        return this.login_panel_can_be_closed;
    }

    public boolean isToutiao_friend_permission() {
        return this.toutiao_friend_permission;
    }

    public boolean isAweme_friend_permission() {
        return this.aweme_friend_permission;
    }

    public boolean isAweme_relation_follow() {
        return this.aweme_relation_follow;
    }

    public boolean isFacebook_friend_permission() {
        return this.facebook_friend_permission;
    }

    public boolean isAweme_video_list_data_permission() {
        return this.aweme_video_list_data_permission;
    }

    public boolean isGsdk_user_info_no_db_default() {
        return this.gsdk_user_info_no_db_default;
    }

    public boolean isGsdk_fusion_user_no_db_default() {
        return this.gsdk_fusion_user_no_db_default;
    }

    public String getSms_channel_id() {
        return this.sms_channel_id;
    }

    public boolean is_need_show_tips_info() {
        return this.is_need_show_tips_info;
    }

    public String getMetaAppId() {
        return this.meta_app_id;
    }

    public String getPicoAppId() {
        return this.pico_app_id;
    }

    public boolean isSeamlessLogin() {
        return this.isSeamlessLogin;
    }
}
