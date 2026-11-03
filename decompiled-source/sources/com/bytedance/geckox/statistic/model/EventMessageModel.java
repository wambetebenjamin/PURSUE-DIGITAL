package com.bytedance.geckox.statistic.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes2.dex */
public class EventMessageModel {
    @SerializedName("duration")
    private long duration;
    @SerializedName("err_msg")
    private String errMsg;
    @SerializedName("event_type")
    private int eventType;
    @SerializedName("extra")
    private String extra;
    @SerializedName("sub_type")
    private int subType;

    public void setEventType(int i2) {
        this.eventType = i2;
    }

    public void setSubType(int i2) {
        this.subType = i2;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public void setDuration(long j2) {
        this.duration = j2;
    }
}
