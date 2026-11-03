package com.bytedance.sdk.account;

import com.bytedance.sdk.account.d;
import org.json.JSONObject;
/* compiled from: AccountSdkResponse.java */
/* loaded from: classes2.dex */
public class c<D extends d> {
    public int api;
    public D data;
    public int detailErrorCode;
    public String detailErrorMessage;
    public int errorCode;
    public String errorMessage;
    public boolean isSuccess;
    public String logId;
    public String requestUrl;
    public JSONObject result;

    public c(com.bytedance.sdk.account.api.call.b bVar, D d2) {
        this.api = bVar.api;
        this.logId = bVar.logId;
        this.requestUrl = bVar.mRequestUrl;
        this.isSuccess = bVar.success;
        this.errorCode = bVar.error;
        this.detailErrorCode = bVar.mDetailErrorCode;
        this.errorMessage = bVar.errorMsg;
        this.detailErrorMessage = bVar.mDetailErrorMsg;
        this.result = bVar.result;
        this.data = d2;
    }

    public String toString() {
        return "AccountSdkResponse{api=" + this.api + ", logId='" + this.logId + "', requestUrl='" + this.requestUrl + "', isSuccess=" + this.isSuccess + ", errorCode=" + this.errorCode + ", detailErrorCode=" + this.detailErrorCode + ", errorMessage='" + this.errorMessage + "', detailErrorMessage='" + this.detailErrorMessage + "', result=" + this.result + ", data=" + this.data + '}';
    }
}
