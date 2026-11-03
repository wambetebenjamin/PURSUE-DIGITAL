package com.bytedance.ttgame.module.rn.api.model;

import com.bytedance.react.framework.RNConfig;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes6.dex */
public class RNWindow {
    @SerializedName("inGameId")
    public String inGameId;
    @SerializedName("isShowing")
    public boolean isShowing;
    @SerializedName("type")
    public int type;
    @SerializedName("url")
    public String url;
    @SerializedName(RNConfig.WINDOW_ID)
    public String windowId;
}
