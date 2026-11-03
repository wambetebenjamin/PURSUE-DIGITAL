package com.bytedance.ttgame.module.account.toutiao.account.api;

import com.google.gson.annotations.SerializedName;
import gsdk.impl.account.toutiao.dh;
/* loaded from: classes4.dex */
public class ChainPermissionResponse {
    @SerializedName("code")
    public int code;
    @SerializedName("data")
    public Data data;
    @SerializedName("message")
    public String message;

    /* loaded from: classes4.dex */
    public class Data {
        @SerializedName(dh.p)
        public boolean hasPermission;

        public Data() {
        }
    }
}
