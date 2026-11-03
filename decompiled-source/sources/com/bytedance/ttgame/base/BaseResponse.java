package com.bytedance.ttgame.base;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes3.dex */
public class BaseResponse {
    @SerializedName("code")
    public int code;
    @SerializedName("message")
    public String message;

    public boolean isSuccess() {
        return this.code == 0;
    }
}
