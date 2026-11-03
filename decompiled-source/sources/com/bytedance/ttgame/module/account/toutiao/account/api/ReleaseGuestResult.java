package com.bytedance.ttgame.module.account.toutiao.account.api;

import com.bytedance.ttgame.base.BaseResponse;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.database.api.UserInfoData;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes4.dex */
public class ReleaseGuestResult extends BaseResponse {
    @SerializedName("data")
    public Data data;
    public GSDKError gsdkError;

    /* loaded from: classes4.dex */
    public class Data {
        @SerializedName("is_visitor")
        public boolean is_visitor;
        @SerializedName("user_info")
        public UserInfoData userInfo;

        public Data() {
        }

        public String toString() {
            return "Data{is_visitor=" + this.is_visitor + ", userInfo=" + this.userInfo + '}';
        }
    }

    public String toString() {
        return "ReleaseGuestResult{data=" + this.data + ", code=" + this.code + ", message='" + this.message + "'}";
    }
}
