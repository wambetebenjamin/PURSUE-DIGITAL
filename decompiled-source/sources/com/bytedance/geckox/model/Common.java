package com.bytedance.geckox.model;

import android.os.Build;
import com.bytedance.bdinstall.e;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes2.dex */
public class Common {
    @SerializedName(e.aE)
    public String ac;
    @SerializedName("aid")
    public long aid;
    @SerializedName("app_name")
    public String appName;
    @SerializedName("app_version")
    public String appVersion;
    @SerializedName("device_id")
    public String deviceId;
    @SerializedName("region")
    public String region;
    @SerializedName("uid")
    public String uid;
    @SerializedName("os")
    public int os = 0;
    @SerializedName("os_version")
    public String osVersion = Build.VERSION.SDK_INT + "";
    @SerializedName("device_model")
    public String deviceModel = Build.MODEL;
    @SerializedName("device_platform")
    public String devicePlatform = "android";
    @SerializedName("sdk_version")
    public String sdkVersion = "2.3.1-rc.1-16kb";

    public Common(long j2, String str, String str2, String str3, String str4) {
        this.aid = j2;
        this.appVersion = str;
        this.deviceId = str2;
        this.appName = str3;
        this.ac = str4;
    }

    public Common(long j2, String str, String str2, String str3, String str4, String str5, String str6) {
        this.aid = j2;
        this.appVersion = str;
        this.deviceId = str2;
        this.appName = str3;
        this.ac = str4;
        this.uid = str5;
        this.region = str6;
    }
}
