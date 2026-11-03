package com.ss.android.common.applog;
/* loaded from: classes9.dex */
public class GlobalConfig {
    private String googleId;
    private String language;
    private String region;

    public GlobalConfig(String str, String str2, String str3) {
        this.googleId = str;
        this.language = str2;
        this.region = str3;
    }

    public String getGoogleId() {
        return this.googleId;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getRegion() {
        return this.region;
    }
}
