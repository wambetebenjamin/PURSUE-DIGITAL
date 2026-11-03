package com.bytedance.ttgame.module.share.api.entity;
/* loaded from: classes7.dex */
public class TTDYMicroAppInfo {
    private String appId;
    private String appTitle;
    private String appUrl;
    private String description;

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getAppTitle() {
        return this.appTitle;
    }

    public void setAppTitle(String str) {
        this.appTitle = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getAppUrl() {
        return this.appUrl;
    }

    public void setAppUrl(String str) {
        this.appUrl = str;
    }

    public String toString() {
        return "TTDYMicroAppInfo{appId='" + this.appId + "', appTitle='" + this.appTitle + "', description='" + this.description + "', appUrl='" + this.appUrl + "'}";
    }
}
