package com.bytedance.ttgame.module.cloud;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.QueryMap;
import java.util.Map;
/* loaded from: classes4.dex */
public interface CloudApi {
    @GET("gsdk/account/request_cloud")
    Call<RequestCloudResponse> requestCloudWithCall(@QueryMap Map<String, String> map);

    @GET("v3/account/request_cloud")
    Call<RequestCloudResponse> requestCloudWithCallPrivatization(@QueryMap Map<String, String> map);
}
