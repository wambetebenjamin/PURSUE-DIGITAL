package com.bytedance.ttgame.module.loccom.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes6.dex */
public class BaseResponse {
    private int code;
    private DataBean data;
    private String log_id;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i2) {
        this.code = i2;
    }

    public String getLog_id() {
        return this.log_id;
    }

    public void setLog_id(String str) {
        this.log_id = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public LocationBean getLocationBean() {
        DataBean dataBean = this.data;
        if (dataBean == null) {
            return null;
        }
        return dataBean.getLocationBean();
    }

    public String getClientIP() {
        DataBean dataBean = this.data;
        if (dataBean == null) {
            return null;
        }
        return dataBean.getLocationClientIP();
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public String toString() {
        return "BaseResponse{code=" + this.code + ", log_id='" + this.log_id + "', message='" + this.message + "', data=" + this.data + '}';
    }

    /* loaded from: classes6.dex */
    public static class DataBean {
        @SerializedName("ClientIP")
        private String clientIP;
        @SerializedName("Location")
        private LocationBean locationBean;

        public LocationBean getLocationBean() {
            return this.locationBean;
        }

        public String getLocationClientIP() {
            return this.clientIP;
        }

        public void setLocationBean(LocationBean locationBean) {
            this.locationBean = locationBean;
        }

        public String toString() {
            return "DataBean{locationBean=" + this.locationBean + "\nclientIP=" + this.clientIP + "\n}";
        }
    }
}
