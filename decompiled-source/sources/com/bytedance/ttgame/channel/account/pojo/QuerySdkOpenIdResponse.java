package com.bytedance.ttgame.channel.account.pojo;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes3.dex */
public class QuerySdkOpenIdResponse {
    @SerializedName("code")
    public int code;
    @SerializedName("data")
    public SdkOpenIdData data;
    @SerializedName("message")
    public String message;

    public boolean isSuccess() {
        return this.code == 0;
    }

    /* loaded from: classes3.dex */
    public class SdkOpenIdData {
        @SerializedName("sdk_open_id")
        public String sdkOpenId;

        public SdkOpenIdData() {
        }
    }
}
