package com.bytedance.ttgame.rocketapi.account.internal;

import com.bytedance.ttgame.sdk.module.core.internal.Constants;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class VisitorStatusInfo {
    @SerializedName("allow_visitor_login")
    public boolean allowVisitorLogin;
    @SerializedName("bind_err_msg")
    public String bindErrorMessage;
    @SerializedName("bind_status")
    public boolean bindStatus;
    @SerializedName(Constants.BIND_TYPE)
    public int bindType;
    @SerializedName("bind_user_id")
    public String bindUserId;
}
