package com.bytedance.ttgame.module.account.toutiao.account.pojo;

import com.bytedance.apm.constant.l;
import com.bytedance.ttgame.base.BaseResponse;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes4.dex */
public class LogoutDeviceResponse extends BaseResponse implements Serializable {
    @SerializedName(l.KEY_LOG_ID)
    public String logId;
}
