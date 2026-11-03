package com.bytedance.ttgame.module.share.api.entity;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class TTShareConfig {
    public String appId;
    @SerializedName("dimAmount")
    public String dimAmount;
    @SerializedName("enable")
    public boolean enable;
    @SerializedName("fbTimeout")
    public int fbTimeout;
    @SerializedName("sdk_lazy_init")
    public boolean isLazyInit;
    @SerializedName("share_keys")
    public JsonObject keys;
    @SerializedName("panelId")
    public String panelId;

    public String toString() {
        return "TTShareConfig{enable=" + this.enable + ", appId='" + this.appId + "', panelId='" + this.panelId + "', keys=" + this.keys + ", isLazyInit=" + this.isLazyInit + '}';
    }
}
