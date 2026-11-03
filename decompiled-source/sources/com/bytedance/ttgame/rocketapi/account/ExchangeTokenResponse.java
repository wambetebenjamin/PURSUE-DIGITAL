package com.bytedance.ttgame.rocketapi.account;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class ExchangeTokenResponse {
    @SerializedName("code")
    public int code;
    @SerializedName("data")
    public ExchangeData data;
    @SerializedName("message")
    public String message;

    public boolean isSuccess() {
        return this.code == 0;
    }
}
