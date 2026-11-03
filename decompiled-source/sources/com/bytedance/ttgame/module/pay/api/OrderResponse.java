package com.bytedance.ttgame.module.pay.api;

import com.bytedance.ttgame.base.GSDKError;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes6.dex */
public class OrderResponse {
    @SerializedName("code")
    public int code;
    @SerializedName("data")
    public String encryptMessage;
    @SerializedName("gsdk_error")
    public GSDKError gsdkError;
    @SerializedName("message")
    public String msg;
    @SerializedName("payInfo")
    public String payInfo;

    public OrderResponse setCode(int i2) {
        this.code = i2;
        return this;
    }

    public OrderResponse setMessage(String str) {
        this.msg = str;
        return this;
    }

    public boolean isSuccess() {
        return this.code == 0;
    }
}
