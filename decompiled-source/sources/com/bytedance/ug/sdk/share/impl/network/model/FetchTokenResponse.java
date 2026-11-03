package com.bytedance.ug.sdk.share.impl.network.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class FetchTokenResponse implements Serializable {
    @SerializedName("data")
    private JsonObject data;
    @SerializedName("status")
    private int status;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i2) {
        this.status = i2;
    }

    public JsonObject getData() {
        return this.data;
    }

    public void setData(JsonObject jsonObject) {
        this.data = jsonObject;
    }
}
