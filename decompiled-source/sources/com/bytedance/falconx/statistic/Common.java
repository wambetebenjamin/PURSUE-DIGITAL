package com.bytedance.falconx.statistic;

import android.os.Build;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes2.dex */
public class Common {
    @SerializedName("app_version")
    public String appVersion;
    @SerializedName("device_id")
    public String deviceId;
    @SerializedName("region")
    public String region;
    @SerializedName("sdk_version")
    public String sdkVersion = "5.2.6-16k_page";
    @SerializedName("device_model")
    public String deviceModel = Build.MODEL;
    @SerializedName("os")
    public int os = 0;
}
