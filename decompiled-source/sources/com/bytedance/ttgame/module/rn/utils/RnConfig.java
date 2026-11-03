package com.bytedance.ttgame.module.rn.utils;

import com.bytedance.apm.constant.r;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes6.dex */
public class RnConfig {
    @SerializedName("bundle_mode")
    public int bundleMode;
    @SerializedName("invite_on")
    public boolean invite_on;
    @SerializedName("loading_init")
    public int loading_init;
    @SerializedName(r.STATS_KEY_MODULE_NAME)
    public String moduleName;
    @SerializedName("old_version")
    public boolean old_version;
    @SerializedName("pre_fetch_off")
    public boolean pre_fetch_off;
}
