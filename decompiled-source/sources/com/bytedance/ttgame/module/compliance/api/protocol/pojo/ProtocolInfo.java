package com.bytedance.ttgame.module.compliance.api.protocol.pojo;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes4.dex */
public class ProtocolInfo implements Serializable {
    @SerializedName("protocolTitle")
    public String protocolTitle;
    @SerializedName("protocolUrl")
    public String protocolUrl;

    public String getProtocolTitle() {
        return this.protocolTitle;
    }

    public void setProtocolTitle(String str) {
        this.protocolTitle = str;
    }

    public String getProtocolUrl() {
        return this.protocolUrl;
    }

    public void setProtocolUrl(String str) {
        this.protocolUrl = str;
    }
}
