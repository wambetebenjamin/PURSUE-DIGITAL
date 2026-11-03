package com.bytedance.ttgame.channel.account.pojo;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes3.dex */
public class VerifyResponse {
    @SerializedName("code")
    public int code;
    @SerializedName("message")
    public String message;

    public boolean isSuccess() {
        return this.code == 0;
    }
}
