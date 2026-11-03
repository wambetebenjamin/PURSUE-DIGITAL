package com.bytedance.ttgame.module.account.toutiao.account.pojo;

import com.bytedance.sdk.account.api.response.aj;
import com.bytedance.ttgame.module.account.toutiao.account.api.TTDetailErrorInfo;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;
/* loaded from: classes4.dex */
public class TTSwitchAccountResponse {
    @SerializedName("data")
    public Data data;
    public TTDetailErrorInfo detailErrorInfo;
    @SerializedName("message")
    public String message;
    public aj originResponse;

    /* loaded from: classes4.dex */
    public class Data {
        @SerializedName("captcha")
        public String captcha;
        @SerializedName("connects")
        public List<Map<String, String>> connects;
        @SerializedName("description")
        public String description;
        @SerializedName("error_code")
        public int error_code;
        @SerializedName("mobile")
        public String mobile;

        public Data() {
        }
    }
}
