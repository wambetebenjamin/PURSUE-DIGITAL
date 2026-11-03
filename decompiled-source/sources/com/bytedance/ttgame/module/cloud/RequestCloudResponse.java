package com.bytedance.ttgame.module.cloud;

import com.bytedance.ttgame.base.BaseResponse;
import com.bytedance.ttgame.module.database.api.RequestCloudData;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes4.dex */
public class RequestCloudResponse extends BaseResponse {
    @SerializedName("data")
    public RequestCloudData data;
}
