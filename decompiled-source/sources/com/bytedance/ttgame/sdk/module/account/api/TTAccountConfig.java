package com.bytedance.ttgame.sdk.module.account.api;

import java.util.List;
/* loaded from: classes3.dex */
public class TTAccountConfig {
    private String facebookPlatFormId;
    private boolean facebook_friend_permission;
    private String googleAppId;
    private String googlePlatFormId;
    private String googlePlayId;
    private String googlePlayPlatFormId;
    private String googleWebAppId;
    private boolean gsdkFusionUserNoDBDefault;
    private boolean gsdkUserInfoNoDBDefault;
    private boolean isNeedShowTipsInfo;
    private boolean isSeamlessLoginEnabled;
    private String lineChannelId;
    private String linePlatFormId;
    private boolean loginPanelCanBeClosed;
    private String metaAppId;
    private String picoAppId;
    private String smsChannelId;
    private List<String> sort;
    private String tiktokKey;
    private String tiktokPlatFormId;
    private String twitterKey;
    private String twitterPlatFormId;
    private String twitterSecret;
    private String vkPlatFormId;

    public boolean isFacebook_friend_permission() {
        return this.facebook_friend_permission;
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

    public String getTiktokPlatFormId() {
        return this.tiktokPlatFormId;
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

    public boolean isLoginPanelCanBeClosed() {
        return this.loginPanelCanBeClosed;
    }

    public List<String> getSort() {
        return this.sort;
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

    public boolean getGsdkUserInfoNoDBDefault() {
        return this.gsdkUserInfoNoDBDefault;
    }

    public boolean getGsdkFusionUserNoDBDefault() {
        return this.gsdkFusionUserNoDBDefault;
    }

    public String getSmsChannelId() {
        return this.smsChannelId;
    }

    public boolean isNeedShowTipsInfo() {
        return this.isNeedShowTipsInfo;
    }

    public String getMetaAppId() {
        return this.metaAppId;
    }

    public String getPicoAppId() {
        return this.picoAppId;
    }

    public boolean isSeamlessLoginEnabled() {
        return this.isSeamlessLoginEnabled;
    }

    TTAccountConfig(Builder builder) {
        this.gsdkUserInfoNoDBDefault = true;
        this.gsdkFusionUserNoDBDefault = true;
        this.isNeedShowTipsInfo = true;
        this.googleAppId = builder.googleAppId;
        this.googleWebAppId = builder.googleWebAppId;
        this.lineChannelId = builder.lineChannelId;
        this.facebookPlatFormId = builder.facebookPlatFormId;
        this.linePlatFormId = builder.linePlatFormId;
        this.googlePlatFormId = builder.googlePlatFormId;
        this.twitterPlatFormId = builder.twitterPlatFormId;
        this.vkPlatFormId = builder.vkPlatFormId;
        this.twitterKey = builder.twitterKey;
        this.twitterSecret = builder.twitterSecret;
        this.tiktokPlatFormId = builder.tiktokPlatFormId;
        this.tiktokKey = builder.tiktokKey;
        this.facebook_friend_permission = builder.facebook_friend_permission;
        this.googlePlayPlatFormId = builder.googlePlayPlatFormId;
        this.googlePlayId = builder.googlePlayId;
        this.sort = builder.sort;
        this.loginPanelCanBeClosed = builder.loginPanelCanBeClosed;
        this.gsdkUserInfoNoDBDefault = builder.gsdkUserInfoNoDBDefault;
        this.gsdkFusionUserNoDBDefault = builder.gsdkFusionUserNoDBDefault;
        this.smsChannelId = builder.smsChannelId;
        this.isNeedShowTipsInfo = builder.isNeedShowTipsInfo;
        this.metaAppId = builder.metaAppId;
        this.picoAppId = builder.picoAppId;
        this.isSeamlessLoginEnabled = builder.isSeamlessLoginEnabled;
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        String facebookPlatFormId;
        boolean facebook_friend_permission;
        String googleAppId;
        String googlePlatFormId;
        String googlePlayId;
        String googlePlayPlatFormId;
        String googleWebAppId;
        boolean isSeamlessLoginEnabled;
        String lineChannelId;
        String linePlatFormId;
        boolean loginPanelCanBeClosed;
        String metaAppId;
        String picoAppId;
        String smsChannelId;
        List<String> sort;
        String tiktokKey;
        String tiktokPlatFormId;
        String twitterKey;
        String twitterPlatFormId;
        String twitterSecret;
        String vkPlatFormId;
        boolean gsdkUserInfoNoDBDefault = true;
        boolean gsdkFusionUserNoDBDefault = true;
        boolean isNeedShowTipsInfo = true;

        public Builder setFacebook_friend_permission(boolean z) {
            this.facebook_friend_permission = z;
            return this;
        }

        public Builder setFacebookPlatFormId(String str) {
            this.facebookPlatFormId = str;
            return this;
        }

        public Builder setVkPlatFormId(String str) {
            this.vkPlatFormId = str;
            return this;
        }

        public Builder setLinePlatFormId(String str) {
            this.linePlatFormId = str;
            return this;
        }

        public Builder setGooglePlatFormId(String str) {
            this.googlePlatFormId = str;
            return this;
        }

        public Builder setTwitterPlatFormId(String str) {
            this.twitterPlatFormId = str;
            return this;
        }

        public Builder setTwitterKey(String str) {
            this.twitterKey = str;
            return this;
        }

        public Builder setTwitterSecret(String str) {
            this.twitterSecret = str;
            return this;
        }

        public Builder setTiktokPlatFormId(String str) {
            this.tiktokPlatFormId = str;
            return this;
        }

        public Builder setTiktokKey(String str) {
            this.tiktokKey = str;
            return this;
        }

        public Builder setSort(List<String> list) {
            this.sort = list;
            return this;
        }

        public Builder setLoginPanelCanBeClosed(boolean z) {
            this.loginPanelCanBeClosed = z;
            return this;
        }

        public Builder setGoogleAppId(String str) {
            this.googleAppId = str;
            return this;
        }

        public Builder setGoogleWebAppId(String str) {
            this.googleWebAppId = str;
            return this;
        }

        public Builder setLineChannelId(String str) {
            this.lineChannelId = str;
            return this;
        }

        public Builder setGooglePlayPlatFormId(String str) {
            this.googlePlayPlatFormId = str;
            return this;
        }

        public Builder setGooglePlayId(String str) {
            this.googlePlayId = str;
            return this;
        }

        public Builder setGsdkUserInfoNoDBDefault(boolean z) {
            this.gsdkUserInfoNoDBDefault = z;
            return this;
        }

        public Builder setGsdkFusionUserNoDBDefault(boolean z) {
            this.gsdkFusionUserNoDBDefault = z;
            return this;
        }

        public Builder setSmsChannelId(String str) {
            this.smsChannelId = str;
            return this;
        }

        public Builder setNeedShowTipsInfo(boolean z) {
            this.isNeedShowTipsInfo = z;
            return this;
        }

        public Builder setMetaAppId(String str) {
            this.metaAppId = str;
            return this;
        }

        public Builder setPicoAppId(String str) {
            this.picoAppId = str;
            return this;
        }

        public Builder setSeamlessLoginEnabled(boolean z) {
            this.isSeamlessLoginEnabled = z;
            return this;
        }

        public TTAccountConfig build() {
            return new TTAccountConfig(this);
        }
    }
}
