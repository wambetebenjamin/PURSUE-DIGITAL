package com.bytedance.ttgame.sdk.module.account.platform.api;

import com.bytedance.ttgame.sdk.module.account.platform.api.IThirdAuthorizeService;
import com.facebook.share.internal.ShareConstants;
/* loaded from: classes7.dex */
public enum Platform {
    Visitor("guest") { // from class: com.bytedance.ttgame.sdk.module.account.platform.api.Platform.1
        @Override // com.bytedance.ttgame.sdk.module.account.platform.api.Platform
        public int getBackgroundIconId() {
            return R.drawable.shape_login_background_visitor;
        }

        @Override // com.bytedance.ttgame.sdk.module.account.platform.api.Platform
        public int getIconId() {
            return R.drawable.icon_visitor;
        }

        @Override // com.bytedance.ttgame.sdk.module.account.platform.api.Platform
        public int getTextColorId() {
            return R.color.gsdk_account_theme_purple;
        }
    },
    Google("google"),
    Facebook("facebook"),
    Line("line"),
    Twitter("twitter"),
    Vk("vk"),
    Tiktok("tiktok"),
    SuccessionCode(ShareConstants.WEB_DIALOG_PARAM_QUOTE),
    PASSWORD("password"),
    Email("email"),
    GooglePlay(IThirdAuthorizeService.ThirdVariant.GooglePlay),
    Mobile("phone"),
    Apple("apple"),
    Steam("steam"),
    Meta("metaquest"),
    Pico(IThirdAuthorizeService.ThirdVariant.Pico),
    Pass(IThirdAuthorizeService.ThirdVariant.Pass),
    More("more");
    
    private int auth;
    private int backgroundIconId;
    private int iconId;
    private int iconIdWithDarkBackground;
    private String platformId;
    private String platformName;
    private int textColorId;

    public void setPlatformId(String str) {
        this.platformId = str;
    }

    public String getPlatformId() {
        return this.platformId;
    }

    public String getPlatformName() {
        return this.platformName;
    }

    public void setAuth(int i2) {
        this.auth = i2;
    }

    public int getAuth() {
        return this.auth;
    }

    public int getIconId() {
        return this.iconId;
    }

    public void setIconId(int i2) {
        this.iconId = i2;
    }

    public int getIconIdWithDarkBackground() {
        return this.iconIdWithDarkBackground;
    }

    public void setIconIdWithDarkBackground(int i2) {
        this.iconIdWithDarkBackground = i2;
    }

    public int getBackgroundIconId() {
        return this.backgroundIconId;
    }

    public void setBackgroundIconId(int i2) {
        this.backgroundIconId = i2;
    }

    public int getTextColorId() {
        return this.textColorId;
    }

    public void setTextColorId(int i2) {
        this.textColorId = i2;
    }

    Platform(String str) {
        this.backgroundIconId = R.drawable.shape_login_background_default;
        this.textColorId = R.color.black;
        this.platformName = str;
    }
}
