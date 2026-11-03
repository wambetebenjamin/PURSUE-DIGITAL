package com.bytedance.ttgame.sdk.module.account.pojo;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class BaseResponse {
    @SerializedName("code")
    public int code;
    @SerializedName("message")
    public String message;

    public boolean isSuccess() {
        return this.code == 0;
    }
}
