package com.bytedance.ttgame.module.account.toutiao.account.pojo;

import com.bytedance.apm.constant.l;
import com.bytedance.ttgame.base.BaseResponse;
import com.bytedance.ttgame.module.database.api.UserInfoData;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes4.dex */
public class UserInfoResponse extends BaseResponse implements Serializable {
    @SerializedName("data")
    public UserInfoData data;
    public int detailErrorCode;
    public int errorCode;
    @SerializedName(l.KEY_LOG_ID)
    public String logId;
    public String requestUrl;
    public Throwable throwable;
    public String errorMsg = "";
    public String detailErrorMsg = "";
    public boolean isServerError = true;

    public UserInfoResponse() {
    }

    public UserInfoResponse(int i2, String str) {
        this.code = i2;
        this.message = str;
    }

    public boolean isSharkProcessingError() {
        return this.code == -30001 || this.code == -30002;
    }

    public static UserInfoResponse newUserInfoResponse(int i2, String str) {
        return new UserInfoResponse(i2, str);
    }

    public String toString() {
        return "UserInfoResponse{, code=" + this.code + ", message='" + this.message + "', errorCode=" + this.errorCode + ", errorMsg='" + this.errorMsg + "', detailErrorCode=" + this.detailErrorCode + ", detailErrorMsg='" + this.detailErrorMsg + "', logId=" + this.logId + ", isServerError" + this.isServerError + '}';
    }
}
